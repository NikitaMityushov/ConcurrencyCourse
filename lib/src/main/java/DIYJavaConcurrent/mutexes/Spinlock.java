package DIYJavaConcurrent.mutexes;
import java.util.concurrent.atomic.AtomicBoolean;

public class Spinlock implements ILock {
    private final AtomicBoolean isLocked;

    public Spinlock() {
        this.isLocked = new AtomicBoolean(false);
    }

    @Override
    public void lock() {
        while(!this.isLocked.compareAndExchange(false, true)) {
            //backoff
            Thread.onSpinWait();
        }
    }

    @Override
    public void unlock() {
        this.isLocked.set(false);
    }
}
