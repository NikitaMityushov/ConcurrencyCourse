package DIYJavaConcurrent.threadpools;

public interface ThreadPool {
    void submit(Runnable task);

    void join();

    boolean isShutdown();
}
