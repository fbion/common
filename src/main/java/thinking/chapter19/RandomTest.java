package thinking.chapter19;

/**
 * Created by Administrator on 2016/3/26.
 */
public class RandomTest {
    public static void main(String[] args) {
        for (int i = 0; i < 20; i++) {
            System.out.println(Enums.random(Activity.class));
        }
    }
}

enum Activity
{
    SITTING, LYING, STANDING, HOPPING, RUNNING, DOGGING, JUMPING, FALLING, FLYING
}