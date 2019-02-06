import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ReadingFromFile implements Runnable {
    private String fileName;
    private static final Logger log = LogManager.getLogger(WriteToFile.class.getName());

    public ReadingFromFile(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(FileReaderMain.getPath() + fileName))) {
            String[] titles = br.readLine().split(";");
            for (int i = 0; i < titles.length; i++) {
                if (!FileReaderMain.getMap().containsKey(titles[i])) {
                    FileReaderMain.getMap().put(titles[i], new HashSet<>());
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < titles.length; i++) {
                    Set<String> sValues = FileReaderMain.getMap().get(titles[i]);
                    sValues.add(values[i]);
                    FileReaderMain.getMap().put(titles[i], sValues);
                }
            }
            log.info("The file \"" + fileName + "\" has been read.");
        } catch (IOException ex) {
            log.error("The file \"" + fileName + "\" has not been read");
            ex.printStackTrace();
        }
    }
}