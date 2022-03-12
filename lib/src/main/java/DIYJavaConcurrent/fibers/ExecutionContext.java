package DIYJavaConcurrent.fibers;

public class ExecutionContext implements ExecutionContextAPI {


    @Override
    public void switchTo(ExecutionContextAPI context) {

    }
}

class MachineContext {
    public native void switchContext();
}
