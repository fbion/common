package advance.JVM;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * 描述： <br>
 * 创建时间: 2017/9/517:37 <br>
 *
 * @author 周志辉
 */
public class Test {

    class GrandFather {

        void thinking() {
            System.out.println("GrandFather");
        }
    }

    class Father extends GrandFather {

        @Override
        void thinking() {
            System.out.println("Father");
        }
    }

    class Son extends Father {

        @Override
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class,
                        "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        (new Test().new Son()).thinking();
    }
}

