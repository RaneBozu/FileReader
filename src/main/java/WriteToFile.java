import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class WriteToFile {
    private Map<String, Set<String>> map;
    private static final Logger log = LogManager.getLogger(WriteToFile.class);

    public WriteToFile(Map<String, Set<String>> map) {
        this.map = map;
    }

    public void writer() {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(".\\src\\main\\resources\\" + entry.getKey() + ".csv"))) {
                Set<String> sValues = entry.getValue();
                for (String str : sValues) {
                    bw.write(str + ";");
                }
                log.info("File \"" + entry.getKey() + "\"  was created.");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}