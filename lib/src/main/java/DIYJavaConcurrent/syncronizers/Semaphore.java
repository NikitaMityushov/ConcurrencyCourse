package DIYJavaConcurrent.syncronizers;

/**
 * Semaphore is an abstract data type used to control access to a common resource by multiple threads and avoid critical section problems
 * in a concurrent system. Semaphores are a type of synchronization primitives.
 */
public class Semaphore implements ISemaphore {
    private volatile int counter;

    public Semaphore(int permits) {
        this.counter = permits;
    }

    @Override
    public synchronized void acquire() {
        System.out.println("Thread " + Thread.currentThread().getName() + ",  counter is " + counter);
        while (counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        --counter;
    }

    @Override
    public synchronized void release() {
        ++counter;
        notifyAll();
    }
}
