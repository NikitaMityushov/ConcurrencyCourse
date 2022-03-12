package DIYJavaConcurrent.fibers;

import java.util.UUID;

/**
 * Fiber's implementation.
 */
public class Fiber implements FiberAPI {
    private Runnable routine;
    private FiberStackAPI stack;
    private ExecutionContextAPI context;
    private FiberStateAPI state;
    private UUID id;

    @Override
    public void yield() {

    }
}
