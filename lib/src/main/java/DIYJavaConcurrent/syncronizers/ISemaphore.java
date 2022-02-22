package DIYJavaConcurrent.syncronizers;

/**
 * Semaphore is an abstract data type used to control access to a common resource by multiple threads and avoid critical section problems
 * in a concurrent system. Semaphores are a type of synchronization primitives.
 */
public interface ISemaphore {
    void acquire();

    void release();
}
