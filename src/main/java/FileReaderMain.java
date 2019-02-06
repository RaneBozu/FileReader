import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.concurrent.*;

public class FileReaderMain {
    private static final Logger log = LogManager.getLogger(FileReaderMain.class.getName());
    private static ConcurrentMap<String, Set<String>> map = new ConcurrentHashMap<>();
    private static String path = ".\\src\\main\\resources\\";

    public static ConcurrentMap<String, Set<String>> getMap() {
        return map;
    }
    public static String getPath() {
        return path;
    }


    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (String fileName : args)
            service.submit(new ReadingFromFile(fileName));
        service.shutdown();

        try {
            log.info("Waiting to read from file");
            service.awaitTermination(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            log.error("The files are not read");
            e.printStackTrace();
        }
        log.info("All files has been read.");

        WriteToFile writeToFile = new WriteToFile();
        writeToFile.writer(map, path);
    }
}