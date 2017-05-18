package advance.JVM.oom;

/**
 * Created by Administrator on 2016/5/30.
 * VM Args:-Xss1M
 */
public class JavaVMStackOOM {
    private void dontStop() {
        while(true) {}
    }

    public void stackLeakByThread () {
        while(true) {
            new Thread(() ->
                dontStop()
            ).start();
        }
    }

    public static void main(String[] args) {
        JavaVMStackOOM oom = new JavaVMStackOOM();
        oom.stackLeakByThread();
    }
}
