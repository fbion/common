package Algorithms;

/**
 * description： 有只猴子在树林采了100根香蕉堆成一堆，猴子家离香蕉堆50米，猴子打算把香蕉背回家，
 * 每次最多能背50根，可是猴子嘴馋，每走一米要吃一根香蕉，问猴子最多能背回家几根香
 * 蕉？<br>
 * createTime: 2017/12/2721:37 <br>
 *
 * @author zzh
 */
public class MonkeyCarryBanana {
    private int distance;
    private Monkey monkey;
    private int amountOfBanana;

    class Monkey{
        Position position;

        public void move(int step) {
        }
    }

    class Position{
        int x;
    }

    public MonkeyCarryBanana(int distance, int amountOfBanana) {
        this.distance = distance;
        this.amountOfBanana = amountOfBanana;
    }



    public static void main(String[] args) {
        //初始距离
        int distance = 50;
        //
        int amountOfBanana = 100;
    }
}
