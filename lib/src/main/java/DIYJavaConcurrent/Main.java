package DIYJavaConcurrent;

import DIYJavaConcurrent.syncronizers.FullDIYSemaphore;
import DIYJavaConcurrent.syncronizers.ISemaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Demo fun = new Demo();
        Thread t1 = new Thread(fun, "1");
        Thread t2 = new Thread(fun, "2");
        Thread t3 = new Thread(fun, "3");
        Thread t4 = new Thread(fun, "4");
        Thread t7 = new Thread(fun, "7");

        t1.setDaemon(true);
        t2.setDaemon(true);
        t3.setDaemon(true);
        t4.setDaemon(true);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t7.start();
        Thread.sleep(10000);
        Thread t5 = new Thread(fun, "5");
        Thread t6 = new Thread(fun, "6");
        t5.start();
        t6.start();
    }
}

class Demo implements Runnable {
    private volatile int i = 0;
    private final ISemaphore mutex = new FullDIYSemaphore(1);

    @Override
    public void run() {
        sleep();
        mutex.acquire(); // synchronized block
        for (int j = 0; j < 10; j++) {
            System.out.println("Thread # " + Thread.currentThread().getName() + ", i = " + ++i);
        }
        mutex.release();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
