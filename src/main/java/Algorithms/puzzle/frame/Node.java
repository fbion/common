package Algorithms.puzzle.frame;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * description： <br>
 * createTime: 2018/3/169:21 <br>
 *
 * @author zzh
 */
@Immutable
public class Node<P, M> {

    final P pos;

    final M move;

    final Node<P, M> prev;


    public Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }


    List<M> asMoveList() {
        List<M> solutions = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev) {
            solutions.add(0, n.move);
        }
        return solutions;
    }
}
