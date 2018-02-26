package advance.JVM.concurrent.test;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.Semaphore;

/**
 * description： <br>
 * createTime: 2018/2/2416:50 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class BoundedBuffer<E> {

    private final Semaphore availableItems;

    private final Semaphore availableSpaces;

    @GuardedBy("this")
    private final E[] items;

    @GuardedBy("this")
    private int putPosition = 0;

    @GuardedBy("this")
    private int takePosition = 0;


    public BoundedBuffer(int capacity) {
        this.availableItems = new Semaphore(0);
        this.availableSpaces = new Semaphore(capacity);
        this.items = (E[]) new Object[capacity];
    }


    public boolean isEmpty() {
        return availableItems.availablePermits() == 0;
    }


    public boolean isFull() {
        return availableSpaces.availablePermits() == 0;
    }


    public void put(E item) throws InterruptedException {
        availableSpaces.acquire();
        doInsert(item);
        availableItems.release();
    }


    public E take() throws InterruptedException {
        availableItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }


    private synchronized E doExtract() {
        int i = takePosition;
        E item = items[i];
        items[i++] = null;
        takePosition = (i == items.length)? 0 : i;
        return item;
    }


    private synchronized void doInsert(E item) {
        int i =putPosition;
        items[i++] = item;
        putPosition= (i == items.length)? 0 : i;
    }
}
