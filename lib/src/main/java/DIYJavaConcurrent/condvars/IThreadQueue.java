package DIYJavaConcurrent.condvars;

/**
 * This is a useful abstraction for the implementation of synchronization primitives.
 */
public interface IThreadQueue {
    /**
     * The method put a Thread in the queue, and after suspends it
     */
    void sleep();

    /**
     * The method poll a Thread out of the queue, and after resumes it
     */
    void wake();

    /**
     * The method poll all Threads out of the queue, and resumes all
     */
    void wakeAll();
}
