package thinking.chapter21.cooperation;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/4/16.
 */
public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}

class Sender implements Runnable {
    private Random rand = new Random();
    private PipedWriter out = new PipedWriter();
    public PipedWriter getOut() {
        return out;
    }

    @Override
    public void run() {
        try{
            while (true) {
                for (char c = '!'; c < 'z'; c++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (IOException e) {
            System.out.println(e + "Sender write exception");
        } catch (InterruptedException e) {
            System.out.println(e + "Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private PipedReader in;
    public PipedReader getIn() {
        return in;
    }

    public Receiver(Sender sender) throws IOException {
        in = new PipedReader(sender.getOut());
    }

    @Override
    public void run() {
        try{
            while (true) {
                for (char c = '!'; c < 'z'; c++) {
                    System.out.print("Read : " + (char) in.read()+ " , ");
                }
            }
        } catch (IOException e) {
            System.out.println(e + "Receiver read exception");
        }
    }
}
