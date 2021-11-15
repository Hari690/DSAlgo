import java.util.StringJoiner;

public class LogLine {
    private String remoteHost;
    private String rfc;
    private String authUser;
    private long date;
    private String requestType;
    private int status;
    private int bytes;

    public LogLine(String remoteHost, String rfc, String authUser, String date, String requestType, String status, String bytes) {
        this.remoteHost = remoteHost;
        this.rfc = rfc;
        this.authUser = authUser;
        this.date = Long.valueOf(date);
        this.requestType = requestType;
        this.status = Integer.valueOf(status);
        this.bytes = Integer.valueOf(bytes);
    }

    public Long getDate() {
        return date;
    }

    public String getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", LogLine.class.getSimpleName() + "[", "]")
            .add("remoteHost='" + remoteHost + "'")
            .add("rfc='" + rfc + "'")
            .add("authUser='" + authUser + "'")
            .add("date=" + date)
            .add("request='" + requestType + "'")
            .add("status=" + status)
            .add("bytes=" + bytes)
            .toString();
    }
}
