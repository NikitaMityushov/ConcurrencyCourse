package DIYJavaConcurrent.mutexes;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLock implements ILock {
    private final AtomicInteger nextFreeTicket;
    private final AtomicInteger ownerTicket;

    public TicketLock() {
        this.nextFreeTicket = new AtomicInteger(0);
        this.ownerTicket = new AtomicInteger(0);
    }

    @Override
    public void lock() {
        int myTicket = this.nextFreeTicket.getAndIncrement();

        while(this.ownerTicket.get() != myTicket) {
            // backoff
            Thread.onSpinWait();
        }
    }

    @Override
    public void unlock() {
        this.ownerTicket.incrementAndGet();
    }
}
