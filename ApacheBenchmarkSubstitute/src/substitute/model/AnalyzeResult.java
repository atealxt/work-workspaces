package substitute.model;

public class AnalyzeResult {

    private String threadName;
    private String StartTime;
    private int during;
    private int status;
    private long bytes;

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(final String threadName) {
        this.threadName = threadName;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(final String startTime) {
        StartTime = startTime;
    }

    public int getDuring() {
        return during;
    }

    public void setDuring(final int during) {
        this.during = during;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(final int status) {
        this.status = status;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(final long bytes) {
        this.bytes = bytes;
    }
}
