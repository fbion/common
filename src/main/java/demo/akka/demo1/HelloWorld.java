package demo.akka.demo1;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * description： <br>
 * createTime: 2017/10/1611:28 <br>
 *
 * @author zzh
 */
public class HelloWorld extends UntypedActor {

    ActorRef greeter;


    @Override
    public void preStart() throws Exception {
        greeter = getContext().actorOf(Props.create(Greeter.class), "greeter");
        System.out.println("Thread " + Thread.currentThread().getName() + "\tGreeter Actor Path:" + greeter.path());
        greeter.tell(Greeter.Msg.GREET, getSelf());
    }


    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg == Greeter.Msg.DONE) {
            System.out.println("Thread " + Thread.currentThread().getName() + "\treceived message");
            greeter.tell(Greeter.Msg.DONE, getSelf());
            getContext().stop(getSelf());
        } else {
            unhandled(msg);
        }

    }
}
