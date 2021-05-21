package DIYJavaConcurrent;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PetersonMutex implements Mutex{
    private final AtomicBoolean[] flags;
    private final AtomicInteger victim;
    private int owner;

    public PetersonMutex() {
        this.flags  = new AtomicBoolean[]{new AtomicBoolean(false), new AtomicBoolean(false)};
        this.victim = new AtomicInteger();
    }

    @Override
    public void lock() {
        final int me = (int) Thread.currentThread().getId() - 11;
        final int other = 1 - me;
        this.flags[me].set(true);
        this.victim.set(me);

        while(this.flags[other].get() && victim.get() == me) {
            // backoff
        }
        // Acquired
        this.owner = me;
    }

    @Override
    public void unlock() {
        this.flags[this.owner].set(false);
    }
}
