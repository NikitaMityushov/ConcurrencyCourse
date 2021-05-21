package DIYJavaConcurrent;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Runnable fun = new Demo(new TicketLock());
        Thread t1 = new Thread(fun, "1");
        Thread t2 = new Thread(fun, "2");
        t1.start();
        t2.start();
    }
}


class Demo implements Runnable {
    private int i = 0;
    private final Mutex mutex;

    public Demo(Mutex mutex) {
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10; j++) {
            mutex.lock(); // synchronized block
            System.out.println("Thread # " + Thread.currentThread().getName() + ", i = " + ++i);
            mutex.unlock();
        }
    }
}