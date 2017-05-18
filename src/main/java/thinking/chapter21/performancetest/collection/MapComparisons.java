package thinking.chapter21.performancetest.collection;

import thinking.chapter15.generate.CountingGenerator;
import thinking.chapter15.generate.MapData;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2016/4/29.
 */
public class MapComparisons {
    public static void main(String[] args) {
        Tester.initMain(args);
        new SynzhronizedHashMapTest(10, 0);
        new SynzhronizedHashMapTest(9, 1);
        new SynzhronizedHashMapTest(5, 5);
        new ConcurrentHashMapTest(10, 0);
        new ConcurrentHashMapTest(9, 1);
        new ConcurrentHashMapTest(5, 5);
    }
}

abstract class MapTest extends Tester<Map<Integer, Integer>> {
    public MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask {
        long result = 0;
        void test() {
            for (long i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    result +=testContainer.get(index);
                }
            }
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask {
        @Override
        void test() {
            for (long i = 0; i < testCycles; i++) {
                for (int index = 0; index < containerSize; index++) {
                    testContainer.put(index, writeData[index]);
                }
            }
        }

        @Override
        void putResults() {
            writeTime += duration;
        }
    }

    @Override
    void startReaderAndWriter() {
        for (int i = 0; i < nReaders; i++) {
            exec.execute(new Reader());
        }
        for (int i = 0; i < nWriters; i++) {
            exec.execute(new Writer());
        }
    }
}

class SynzhronizedHashMapTest extends MapTest {
    public SynzhronizedHashMapTest(int nReaders, int nWriters) {
        super("Syched HashMap", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> constainerInitializer() {
        return Collections.synchronizedMap(new HashMap<>(MapData.map(new CountingGenerator.Integer(), new CountingGenerator.Integer(), containerSize)));
    }
}

class ConcurrentHashMapTest extends MapTest {
    public ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }

    @Override
    Map<Integer, Integer> constainerInitializer() {
        return new ConcurrentHashMap<>(MapData.map(new CountingGenerator.Integer(), new CountingGenerator.Integer(), containerSize));
    }
}