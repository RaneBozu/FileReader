import java.util.Set;
import java.util.concurrent.*;

public class FileReaderMain {

    public static void main(String[] args) {
        ConcurrentMap<String, Set<String>> map = new ConcurrentHashMap<>();
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