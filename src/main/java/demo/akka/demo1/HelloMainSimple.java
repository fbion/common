package demo.akka.demo1;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * description： <br>
 * createTime: 2017/10/1611:29 <br>
 *
 * @author zzh
 */
public class HelloMainSimple {

    public static void main(String[] args) {
        Config config = ConfigFactory.load("samplehello.conf");
        ActorSystem system = ActorSystem.create("Hello", ConfigFactory.load("samplehello.conf"));
        ActorRef a = system.actorOf(Props.create(HelloWorld.class), "helloWorld");
        System.out.println("HelloWorld Actor Path:" + a.path());
    }
}