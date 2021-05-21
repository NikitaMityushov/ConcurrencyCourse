package DIYJavaConcurrent;

import java.util.concurrent.atomic.AtomicBoolean;

// Test-and-Set spinlock
public class SpinLock implements Mutex{
    private final AtomicBoolean isLocked;

    public SpinLock() {
        this.isLocked = new AtomicBoolean(false);
    }

    @Override
    public void lock() {
        while (this.isLocked.compareAndExchange(false, true)) {
            // backoff
        }
    }

    @Override
    public void unlock() {
        this.isLocked.set(false);
    }
}
