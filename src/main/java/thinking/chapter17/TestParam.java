package thinking.chapter17;

/**
 * Created by Administrator on 2016/3/19.
 */
public class TestParam {
    public final int size;
    public final int loops;
    public TestParam(int size, int loops){
        this.size = size;
        this.loops = loops;
    }

    /**
     * 根据传入的整型数组生成容量为其一半的TestParam数组
     * @param values
     * @return
     */
    public static TestParam[] array(int...values){
        int size = values.length / 2;
        TestParam[] testParam = new TestParam[size];
        int n = 0;
        for (int i = 0; i < size; i++) {
            testParam[i] = new TestParam(values[n++], values[n++]);
        }
        return testParam;
    }

    /**
     * 根据传入的字符串数组生成容量相同的TestParam数组
     * @param values
     * @return
     */
    public static TestParam[] array(String[] values){
        int[] vals = new int[values.length];
        for (int i = 0; i < vals.length; i++) {
            vals[i] = Integer.decode(values[i]);
        }
        return array(vals);
    }
}
