package advance.JVM.concurrent;

/**
 * description： <br>
 * createTime: 2018/3/810:56 <br>
 *
 * @author zzh
 */
public class PossibleRecordering {

    static int x = 0, y = 0;
    static int a = 0, b= 0;


    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000000; i++) {
            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });
            one.start();
            other.start();
            one.join();
            other.join();
            System.out.print("(" + x + "," + y + ")");
            if(i % 20 == 19) {
                System.out.println();
            }  else {
                System.out.print("\t");
            }
            x = y = a = b = 0;
        }

    }
}

/**
 * (0,1)
 * (1,0)
 */
