import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * description: <br>
 * createTime: 2018/5/210:30 <br>
 * linux关闭信号：SEGV, ILL, FPE, BUS, SYS, CPU, FSZ, ABRT, INT, TERM, HUP, USR1, USR2, QUIT, BREAK, TRAP, PIPE
 * windows关闭信号：SEGV, ILL, FPE, ABRT, INT, TERM, BREAK
 * @author zzh
 */
@SuppressWarnings("restriction")
public class TestSignal implements SignalHandler {

    private void signalCallback(Signal sn) {
        System.out.println(sn.getName() + "is recevied.");
    }

    @Override
    public void handle(Signal signalName) {
        signalCallback(signalName);
    }

    public static void main(String[] args) throws InterruptedException {
        TestSignal testSignalHandler = new TestSignal();
        // install signals
        Signal.handle(new Signal("TERM"), testSignalHandler);
        Signal.handle(new Signal("SEGV"), testSignalHandler);
        Signal.handle(new Signal("ILL"), testSignalHandler);

        //Signal already used by VM or OS: SIGFPE
//        Signal.handle(new Signal("FPE"), testSignalHandler);
        Signal.handle(new Signal("ABRT"), testSignalHandler);

        //Signal already used by VM or OS: SIGBREAK
//        Signal.handle(new Signal("BREAK"), testSignalHandler);

        //Windows Ctrl+c信号
        Signal.handle(new Signal("INT"), testSignalHandler);//SEGV, ILL, FPE, ABRT, INT, TERM, BREAK
        Thread.sleep(3000);
        System.out.println("running......");
    }

}