package DIYJavaConcurrent.fibers;

/**
 * Public API for any fiber
 */
public interface FiberAPI {
    void yield();

    enum FiberState {
        STARTING,
        RUNNABLE,  // in run queue
        RUNNING,
        SUSPENDED,  // for example, in wait queue
        TERMINATED
    }
}



