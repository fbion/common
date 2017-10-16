package demo.akka.demo1;

import akka.actor.UntypedActor;

/**
 * description： <br>
 * createTime: 2017/10/1611:27 <br>
 *
 * @author zzh
 */
public class Greeter extends UntypedActor {
    public static enum Msg {
        GREET,DONE;
    }
    @Override
    public void onReceive(Object msg) throws Exception {
        if(msg ==Msg.GREET) {
            System.out.println("Hello World!");
            getSender().tell(Msg.DONE, getSelf());
        } else {
            unhandled(msg);
        }
    }
}
