package thinking.chapter21.elements.semaphore;

/**
 * Created by Administrator on 2016/4/20.
 */
public class Fat {
    private volatile double d;
    private static int counter = 0;
    private final int id = counter++;

    public Fat() {
        for (int i = 0; i < 10000; i++) {
            d += (Math.PI + Math.E) / (double)i;
        }
    }

    public void operation() {
        System.out.println(this);
    }
    public String toString() {
        return "Fat id : " + id;
    }
}
