package DIYJavaConcurrent.condvars;

import DIYJavaConcurrent.mutexes.ILock;

/**
 * A condition variable is an object used in combination with its associated lock
 * to allow a thread to wait for some condition while it is inside a critical section.
 */
public class CondVar implements IConditionVariable {
    private ILock lock;
    private IThreadQueue waitQueue;

    public CondVar(ILock lock) {
        this.lock = lock;
        waitQueue = new ThreadQueue();
    }

    /**
     * Method blocks the calling thread, waiting for the condition specified by cond to be signaled or broadcast to.
     * Must release the Lock go to sleep automatically.
     * Call waitThread() ONLY in while loop.
     */
    @Override
    public void waitThread() {
        lock.unlock();
        waitQueue.sleep();
        lock.lock();
    }

    /**
     * Method wakes up at least one thread that is currently waiting on the condition variable specified by cond.
     * If no threads are currently blocked on the condition variable, this call has no effect.
     */
    @Override
    public void signal() {
        waitQueue.wake();
    }

    /**
     * Method wakes up all threads that are currently waiting on the condition variable specified by cond.
     * If no threads are currently blocked on the condition variable, this call has no effect.
     */
    @Override
    public void broadcast() {
        waitQueue.wakeAll();
    }
}
