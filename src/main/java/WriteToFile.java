import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class WriteToFile {
    private Map<String, Set<String>> map;

    public WriteToFile(Map<String, Set<String>> map) {
        this.map = map;
    }

    public void writer() {

        for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(entry.getKey() + ".csv"))) {
                Set<String> sValues = entry.getValue();
                for (String str : sValues) {
                    bw.write(str + ";");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}