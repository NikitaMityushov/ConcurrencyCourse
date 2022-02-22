package DIYJavaConcurrent.threadpools;

import DIYJavaConcurrent.queues.BlockingQueue;
import DIYJavaConcurrent.queues.DequeBlockingQueue;

import java.util.HashSet;
import java.util.Set;

public class FixedThreadPool implements ThreadPool {
    private final BlockingQueue<Runnable> queue;
    private final Set<Thread> workerSet;
    private final int numberOfWorkers;
    private boolean isShutdown = false;

    // public:
    public FixedThreadPool(int numberOfWorkers) {
        if (numberOfWorkers < 1) {
            this.numberOfWorkers = Runtime.getRuntime().availableProcessors() + 1;
        } else {
            this.numberOfWorkers = numberOfWorkers;
        }
        this.queue = new DequeBlockingQueue<>();
        this.workerSet = new HashSet<>();

        startToCreateWorkersAndExecution();
    }

    @Override
    public void submit(Runnable task) {
        // check if the task is null
        if (task == null) {
            return;
        }
        queue.put(task);
    }

    @Override
    public synchronized void join() {
        this.queue.shutdown();
        this.isShutdown = true;

        for (Thread t : workerSet) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized boolean isShutdown() {
        return isShutdown;
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            while (!FixedThreadPool.this.isShutdown) {
                try {
                    FixedThreadPool.this.queue.take().run(); // extract task from queue and launch it
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void startToCreateWorkersAndExecution() {
        for (int i = 0; i < numberOfWorkers; ++i) {
            Worker newWorker = new Worker();
            workerSet.add(newWorker);
            newWorker.start();
        }
    }
}
