import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileReaderMain {

    public static void main(String[] args) {
        Map<String, Set<String>> map = new HashMap<>();
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (String fileName : args)
            service.submit(new ReadingFromFile(map, fileName));
        service.shutdown();

        try {
            service.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WriteToFile writeToFile = new WriteToFile(map);
        writeToFile.writer();
    }
}