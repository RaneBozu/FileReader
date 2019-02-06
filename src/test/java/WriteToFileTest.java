import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class WriteToFileTest {

    @Test
    public void writerShouldCreateFilesFromMapKeys() {
        ConcurrentMap<String, Set<String>> map = new ConcurrentHashMap<>();
        map.put("FirstFile", new HashSet<>());
        map.put("SecondFile", new HashSet<>());
        map.put("ThirdFile", new HashSet<>());
        String path = ".\\src\\test\\resources\\";
        WriteToFile writeToFile = new WriteToFile();
        writeToFile.writer(map, path);
        for (Map.Entry<String, Set<String>> entry : map.entrySet()){
            File file = new File(path + entry.getKey() + ".csv");
            Assert.assertTrue(file.exists());
        }
    }
}