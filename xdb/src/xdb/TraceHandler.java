package xdb;

public interface TraceHandler {
    void publish(Trace level, String message, Throwable e);
}
