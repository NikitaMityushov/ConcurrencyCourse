package DIYJavaConcurrent.condvars;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The ThreadQueue contains a Queue of Threads that can be suspended or resumed.
 */
public class ThreadQueue implements IThreadQueue {
    private final Queue<Thread> queue = new LinkedList<>();

    /**
     * The method put a Thread in the queue, and after suspends it
     */
    @Override
    public void sleep() {
//        System.out.println("Inside sleep(). Thread # " + Thread.currentThread().getName());
        queue.offer(Thread.currentThread());
        Thread.currentThread().suspend();
    }

    /**
     * The method poll a Thread out of the queue, and after resumes it
     */
    @Override
    public void wake() {
        if (!queue.isEmpty()) {
            if (queue.peek() != null) {
                var thread = queue.poll();
                System.out.println("Inside wake(). Name of awake thread is  " + thread.getName());
                thread.resume();
            }
        }
    }

    /**
     * The method poll all Threads out of the queue, and resumes all
     */
    @Override
    public void wakeAll() {
        while (!queue.isEmpty()) {
            if (queue.peek() != null) {
                queue.poll().resume();
            }
        }
    }
}
