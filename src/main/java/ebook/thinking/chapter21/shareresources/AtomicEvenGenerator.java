package ebook.thinking.chapter21.shareresources;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016/4/1.
 */
public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger currentValue = new AtomicInteger(0);
    @Override
    public int next() {
        return currentValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}
