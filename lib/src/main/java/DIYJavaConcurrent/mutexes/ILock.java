package DIYJavaConcurrent.mutexes;

/**
 * Common interface for all mutexes.
 * Can be held by at most ONE Thread at a time.
 * Only the thread that last acquired a lock is
 * allowed to release that lock.
 * Useful for guarding critical sections.
 */
public interface ILock {
    void lock();

    void unlock();
}
