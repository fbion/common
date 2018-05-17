package ebook.thinking.chapter15.dynamicproxy;

/**
 * Created by Administrator on 2016/3/5.
 */
public class SerialNumberedImpl implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;
    public long getSerialNumber(){
        return serialNumber;
    }
}
