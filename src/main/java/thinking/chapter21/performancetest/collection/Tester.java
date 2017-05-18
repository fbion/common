package thinking.chapter21.performancetest.collection;

import thinking.chapter15.generate.Generated;
import thinking.chapter15.generate.RandomGenerator;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/4/29.
 */
public abstract class Tester<C> {
    static int testReps = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;
    abstract C constainerInitializer();
    abstract void startReaderAndWriter();
    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;
    static ExecutorService exec = Executors.newCachedThreadPool();
    Integer[] writeData;

    public Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " + nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.array(Integer.class, new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    public void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = constainerInitializer();
        startReaderAndWriter();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
        if(readTime != 0 && writeTime != 0) {
            System.out.printf("%-27s %14d\n", "readTime + writeTime = ", readTime + writeTime);
        }
    }

    abstract class TestTask implements Runnable {
        abstract void test();
        abstract void putResults();
        long duration;

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
//        args = new String[]{"", "", ""};
        switch (args.length) {
            case 3:
                containerSize = new Integer(args[2]);
            case 2:
                testCycles = new Integer(args[1]);
            case 1:
                testReps = new Integer(args[0]);
        }
        System.out.printf("%-27s %14s %14s\n", "Type", "read Time", "write Time");
    }
}
