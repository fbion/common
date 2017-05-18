package thinking.chapter21.shareresources;

/**
 * Created by Administrator on 2016/4/1.
 */
public class SerialNumberGenertor {
    private static volatile int serialNumber = 0;
    public static int nextSerialNumber()
    {
        return serialNumber++;
    }
}
