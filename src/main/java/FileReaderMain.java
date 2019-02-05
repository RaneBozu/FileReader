import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.concurrent.*;

public class FileReaderMain {
    private static final Logger log = LogManager.getLogger(FileReaderMain.class.getName());
    public static void main(String[] args) {
        ConcurrentMap<String, Set<String>> map = new ConcurrentHashMap<>();
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (String fileName : args)
            service.submit(new ReadingFromFile(map, fileName));
        service.shutdown();

        try {
            service.awaitTermination(5, TimeUnit.MINUTES);
            log.info("Waiting to read from file");
        } catch (InterruptedException e) {
            log.error("The files are not read");
            e.printStackTrace();
        }

        WriteToFile writeToFile = new WriteToFile(map);
        writeToFile.writer();
    }
}