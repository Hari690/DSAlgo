import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class LogParser implements Runnable {

    private String path;
    private List<LogProcessor> processors;

    public LogParser(String path) {
        this.path = path;
        this.processors = new ArrayList<>();
        this.processors.add(new StatsProcessor());
        this.processors.add(new AlertProcessor());
    }

    public static void main(String argv[]) {

        // Currently processing on a single dedicated Thread for a single file.
        // We could use a larger Threadpool to process multiple files.
        ExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        String filePath = "resources/sample_csv.txt";
        LogParser logParser = new LogParser(filePath);

        executor.execute(logParser);

    }

    public void run() {
        try(Stream<String> lines = Files.lines(Paths.get(path))) {
            lines.skip(1).forEach(line -> {
                String[] fields = line.split(",");
                LogLine logLine = new LogLine(fields[0],fields[1],fields[2],fields[3], fields[4], fields[5], fields[6]);
                processors.stream().forEach(processor->processor.process(logLine));
            });
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}
