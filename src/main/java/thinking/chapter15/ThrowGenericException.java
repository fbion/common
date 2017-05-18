package thinking.chapter15;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/5.
 */
public class ThrowGenericException {
    public static void main(String[] args) {
        ProcessRunner<String, Failure1> runner1 = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner1.add(new Process1());
        }
        try{
            System.out.println(runner1.processAll());
        }catch (Failure1 e){
            System.out.println(e);
        }

        ProcessRunner<Integer, Failure2> runner2 = new ProcessRunner<>();
        for (int i = 0; i < 3; i++) {
            runner2.add(new Process2());
        }
        try{
            System.out.println(runner2.processAll());
        }catch (Failure2 e){
            System.out.println(e);
        }
    }
}

interface Process<T,E extends Exception>{
    void process(List<T> resultCollector) throws E;
}

class ProcessRunner<T,E extends Exception> extends ArrayList<Process<T,E>> {
    List<T> processAll() throws E{
        List<T> resultCollector = new ArrayList<>();
        for (Process<T, E> process : this) {
            process.process(resultCollector);
        }
        return resultCollector;
    }
}

class Failure1 extends Exception{}

class Process1 implements Process<String, Failure1> {
    static int count = 3;
    @Override
    public void process(List<String> resultCollector) throws Failure1 {
        if(count-- > 1){
            resultCollector.add("Hep!");
        }else{
            resultCollector.add("Ho");
        }
        if(count < 0){
            throw new Failure1();
        }
    }
}

class Failure2 extends Exception{}

class Process2 implements Process<Integer, Failure2> {
    static int count = 3;
    @Override
    public void process(List<Integer> resultCollector) throws Failure2 {
        if(count-- > 1){
            resultCollector.add(47);
        }else{
            resultCollector.add(11);
        }
        if(count < 0){
            throw new Failure2();
        }
    }
}
