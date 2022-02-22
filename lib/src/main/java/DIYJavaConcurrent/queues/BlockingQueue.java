package DIYJavaConcurrent.queues;

/**
 * API for a queue that will contain tasks for associated ThreadPool.
 */
public interface BlockingQueue<T> {
    void put(T item);

    T take() throws InterruptedException;

    void shutdown();
}
