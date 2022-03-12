package DIYJavaConcurrent.fibers;

/**
 * public api for Execution Context
 */
public interface ExecutionContextAPI {
    /**
     * Symmetric control transfer:
     * 1) Save the current context to this
     * 2) Activate target context
     */
    void switchTo(ExecutionContextAPI context);
}
