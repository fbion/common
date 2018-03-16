package Algorithms.puzzle.frame;

import java.util.Set;

/**
 * description： <br>
 * createTime: 2018/3/168:36 <br>
 *
 * @author zzh
 */
public interface Puzzle<P, M> {

    P initialPosition();
    boolean isGoal(P position);
    Set<M> legalMoves(P position);
    P move(P position, M move);
}
