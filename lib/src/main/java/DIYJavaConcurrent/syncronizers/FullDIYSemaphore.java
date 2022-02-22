package DIYJavaConcurrent.syncronizers;

import DIYJavaConcurrent.condvars.CondVar;
import DIYJavaConcurrent.mutexes.ILock;
import DIYJavaConcurrent.mutexes.TicketLock;

import java.util.concurrent.atomic.AtomicInteger;

public class FullDIYSemaphore implements ISemaphore {
    //    private volatile int counter;
    AtomicInteger atomic;
    // monitor
    private ILock lock;
    private CondVar condVar;

    public FullDIYSemaphore(int permits) {
//        this.counter = permits;
        this.atomic = new AtomicInteger(permits);
        this.lock = new TicketLock();
        this.condVar = new CondVar(this.lock);
    }

    @Override
    public void acquire() {
//        if (--counter < 0) {
        lock.lock();
        while (atomic.get() == 0) {
            System.out.println("Thread " + Thread.currentThread().getName() + " In the if: atomic is " + atomic.get());
            condVar.waitThread();
        }
        atomic.decrementAndGet();
        System.out.println("Thread " + Thread.currentThread().getName() + ", counter is " + atomic.get());
        lock.unlock();
    }

    @Override
    public void release() {
        lock.lock();
//        ++counter;
        atomic.incrementAndGet();
        System.out.println("condvar signal");
        condVar.broadcast();
        lock.unlock();
    }
}
