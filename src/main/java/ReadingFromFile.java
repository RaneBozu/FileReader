import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ReadingFromFile implements Runnable {
    private Map<String, Set<String>> map;
    private String fileName;

    public ReadingFromFile(Map<String, Set<String>> map, String fileName) {
        this.map = map;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String[] titles = br.readLine().split(";");
            for (int i = 0; i < titles.length; i++) {
                if (!map.containsKey(titles[i])) {
                    map.put(titles[i], new HashSet<>());
                }
            }

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (int i = 0; i < titles.length; i++) {
                    Set<String> sValues = map.get(titles[i]);
                    sValues.add(values[i]);
                    map.put(titles[i], sValues);
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}