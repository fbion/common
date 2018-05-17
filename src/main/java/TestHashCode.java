/**
 * description: <br>
 * createTime: 2018/5/1714:08 <br>
 *
 * @author zzh
 */
public class TestHashCode {

    public static void main(String[] args) {
        Object o = new byte[1024*1024];
        int hashcode = o.hashCode();
        String toString = o.toString();
        for (int i = 0; i < 20; i++) {
            System.out.println("------------------------------" + i + "------------------------------");
            System.gc();
        }
        System.out.println("hashcode == hashCode() : " + (hashcode == o.hashCode()));
        System.out.println("toString.equals(o.toString()) : " + toString.equals(o.toString()));
    }
}
