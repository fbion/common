package ebook.thinking.chapter15;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/5.
 */
public class PDecoration {
    public static void main(String[] args) {
        TimeStamped t1 = new TimeStamped(new Basic());
        TimeStamped t2 = new TimeStamped(new SerialNumbered(new Basic()));
//        t2.getSerialNumber();
        SerialNumbered s1 = new SerialNumbered(new TimeStamped(new Basic()));
        SerialNumbered s2 = new SerialNumbered(new Basic());
//        s2.getTimeStamp();

    }
}

class Basic{
    private String value;
    public void setValue(String value){
        this.value = value;
    }
    public String getValue(){
        return this.value;
    }
}

class Decorator extends Basic {
    protected Basic basic;
    public Decorator(Basic basic){
        this.basic = basic;
    }
    public void set(String value){
        basic.setValue(value);
    }
    public String get(){
        return basic.getValue();
    }
}

class TimeStamped extends Decorator {
    private final long timeStamp;
    public TimeStamped(Basic basic) {
        super(basic);
        timeStamp = new Date().getTime();
    }
        public long getStamp(){
        return timeStamp;
    }
}

class SerialNumbered extends Decorator {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public SerialNumbered(Basic basic){
        super(basic);
    }
    public long getSerialNumber(){
        return serialNumber;
    }
}