import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

public class WriteToFile {
    private static final Logger log = LogManager.getLogger(WriteToFile.class.getName());

    public void writer(ConcurrentMap<String, Set<String>> map, String path) {
        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + entry.getKey() + ".csv"))) {
                Set<String> sValues = entry.getValue();
                for (String str : sValues) {
                    bw.write(str + ";");
                }
            } catch (IOException ex) {
                log.error("The file \"" + entry.getKey() + "\" was not created.");
                ex.printStackTrace();
            }
            log.info("File \"" + entry.getKey() + "\"  was created.");
        }
    }
}