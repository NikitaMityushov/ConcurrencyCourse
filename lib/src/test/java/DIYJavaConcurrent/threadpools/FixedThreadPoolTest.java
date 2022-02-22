package DIYJavaConcurrent.threadpools;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FixedThreadPoolTest {
    private final ThreadPool pool = new FixedThreadPool(4);

    @Before
    public void setUp() throws Exception {
        pool.submit(() -> {
            System.out.println("Trulala1");
        });
        pool.submit(() -> {
            System.out.println("Trulala2");
        });
        pool.submit(() -> {
            System.out.println("Trulala3");
        });
        pool.submit(() -> {
            System.out.println("Trulala4");
        });
        pool.submit(() -> {
            System.out.println("Trulala5");
        });
        pool.submit(() -> {
            System.out.println("Trulala6");
        });
        pool.submit(() -> {
            System.out.println("Trulala7");
        });
        pool.submit(() -> {
            System.out.println("Trulala8");
        });
    }

    @Test
    public void whenJoinIsShutdownTrue() {
        // given is isShutdown == false
        assertFalse(pool.isShutdown());
        // when
        pool.join();
        // then isShutdown == true
        assertTrue(pool.isShutdown());
    }


    @After
    public void tearDown() throws Exception {
    }
}