/**
 * description： <br>
 * createTime: 2018/1/816:08 <br>
 *
 * @author zzh
 */
public class TestLoop {

    public static void main(String[] args) throws InterruptedException {
        int count;
        count = 0;
        boolean flag = true;
        outer: while(flag) {
            inner: while(flag) {
                count++;
                if(true) {
                    continue outer;
                }
                if(count == 10) {
                    break outer;
                }
            }
            System.out.println("outer looper");
        }
    }
}