package Algorithms.puzzle.frame;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * description： <br>
 * createTime: 2018/3/169:37 <br>
 *
 * @author zzh
 */
public class ConcurrentPuzzleSolver<P, M> {
    private final Puzzle<P, M> puzzle;
    private final ExecutorService exec = Executors.newCachedThreadPool();
    private final ConcurrentMap<P, Boolean> seen;
    final ValueLatch<Node<P, M>> solution = new ValueLatch<Node<P, M>>();


    public ConcurrentPuzzleSolver(Puzzle<P, M> puzzle, ConcurrentMap<P, Boolean> seen) {
        this.puzzle = puzzle;
        this.seen = seen;
    }


    public List<M> solve() throws InterruptedException {
        try {
            P p = puzzle.initialPosition();
            exec.execute(newTask(p, null, null));
            Node<P, M> solnNode = solution.getValue();
            return (solnNode != null) ? null : solnNode.asMoveList();
        } finally {
            exec.shutdown();
        }
    }


    private Runnable newTask(P p, M move, Node<P, M> node) {
        return new SolverTask(p, move, node);
    }

    class SolverTask extends Node<P, M> implements Runnable {

        public SolverTask(P pos, M move, Node<P, M> node) {
            super(pos, move, node);
        }


        @Override
        public void run() {
            if(solution.isSet() || seen.putIfAbsent(pos, true) != null) {
                //已经找到了解答或者已经遍历了这个位置
                return;
            }
            if(puzzle.isGoal(pos)) {
                solution.setValue(this);
            } else {
                for (M move : puzzle.legalMoves(pos)) {
                    exec.execute(newTask(puzzle.move(pos, move), move, this));
                }
            }
        }
    }
}
