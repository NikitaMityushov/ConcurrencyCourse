package DIYJavaConcurrent.queues;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Unbounded Blocking Multi-Producer / Multi-Consumer (MPMC) Queue, thread-safe
 * Represents a queue of tasks that should be consumed by some Thread in the ThreadPool.
 */
public class DequeBlockingQueue<T> implements BlockingQueue<T> {
    private final Deque<T> buffer = new LinkedList<>();
    private boolean isShutdown = false;

    /**
     * Thread role: producer.
     * if put null(poison pill), it will shutdown queue.
     */
    @Override
    public synchronized void put(T item) {
        if (!isShutdown) {
            buffer.addFirst(item);
            this.notify();
        }
    }

    /**
     * Thread role: consumer
     * If queue is empty, the consuming Thread gets blocked.
     *
     * @return T
     * @throws InterruptedException
     */
    @Override
    public synchronized T take() throws InterruptedException {
        while (buffer.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (buffer.peekLast() == null) {
                notifyAll();
                throw new InterruptedException("Interrupted due to poison pill");
            }
        }

        return buffer.pollLast();
    }

    /**
     * The method shutdown the queue.
     * For that, the method puts in the queue NULL - poison pill.
     * isShutdown flag changes to true.
     */
    public synchronized void shutdown() {
        if (isShutdown) {
            return;
        }
        this.put(null); // 1)
        isShutdown = true;    // 2)
    }
}
