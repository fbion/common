package advance.JVM.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 * VM Args:-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 *
 * -XX:+HeapDumpOnOutOfMemoryError  JVM发生OOM异常自动生成dummp文件
 * -XX:+HeapDumpOnCtrlBreak         Ctrl+Break组合键让JVM生成dump文件
 */
public class HeapOOM {
    static class OOMObject{
        int[] arrays = new int[1024];
    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while(true) {
            list.add(new OOMObject());
        }
    }
}
