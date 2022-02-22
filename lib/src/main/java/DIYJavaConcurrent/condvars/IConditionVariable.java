package DIYJavaConcurrent.condvars;

/**
 * A condition variable is an object used in combination with its associated lock
 * to allow a thread to wait for some condition while it is inside a critical section.
 */
public interface IConditionVariable {
    /**
     * Method blocks the calling thread, waiting for the condition specified by cond to be signaled or broadcast to.
     * Must release the Lock go to sleep automatically.
     * Call waitThread() ONLY in while loop.
     */
    void waitThread();

    /**
     * Method wakes up at least one thread that is currently waiting on the condition variable specified by cond.
     * If no threads are currently blocked on the condition variable, this call has no effect.
     */
    void signal();

    /**
     * Method wakes up all threads that are currently waiting on the condition variable specified by cond.
     * If no threads are currently blocked on the condition variable, this call has no effect.
     */
    void broadcast();
}
