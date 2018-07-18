package test.innerouter;

/**
 * Created by Administrator on 2016/5/25.
 */
public class TestInner {
    private int i;
    Inner inner;
    class Inner {
        private int j;
        void test() {
            System.out.println(i);
        }
    }

    static class StaticInner {
        private StaticInner() {
        }
    }

    void test() {
        System.out.println(inner.j);
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int temp = 2;
        Inner in = new TestInner().new Inner() {
            @Override
            void test() {
                System.out.println(temp);
            }
        };

        System.out.println(new StaticInner());
        System.out.println(Inner.class.isSynthetic());
        System.out.println(in.getClass().isSynthetic());
        System.out.println(StaticInner.class.isSynthetic());
        System.out.println(TestInner.class.isSynthetic());
    }
}
