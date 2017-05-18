package work.qizhi.test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/30.
 */
public class Application {
    private List<Integer> list = TestSerial.list;

    private Map<Integer, Integer> map = TestSerial.map;

    public static void main(String[] args) throws IOException {
        Application application = new Application();
        System.out.println(application.list);
        System.out.println(application.map);
        for (int i = 0; i < 5; i++) {
            application.list.add(i);
            application.map.put(i, i);
        }

        TestSerial.store();
    }
}
