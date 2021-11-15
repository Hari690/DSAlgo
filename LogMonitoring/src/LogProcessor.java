import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public interface LogProcessor {
    // Lag acceptable for processing to allow late data.
    int LAG_DURATIONS_SECS = 3;

    void process(LogLine logLine);

    default LocalDateTime epochToLocalDateTime(long epoch) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(epoch),
                TimeZone.getDefault().toZoneId());
    }
}
