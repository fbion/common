package advance.JVM.concurrent.test.test4;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * description： <br>
 * createTime: 2018/3/2117:15 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class LinkedQueue<V> {

    private final Node<V> dummy = new Node<>(null, null);

    private AtomicReference<Node<V>> head = new AtomicReference<>(dummy);

    private final AtomicReference<Node<V>> tail = new AtomicReference<>(dummy);


    public boolean put(V v) {
        Node<V> newNode = new Node<>(v, null);
        while(true) {
            Node<V> curTail = tail.get();
            Node<V> tailNext = curTail.next.get();
            if(curTail == tail.get()) {
                if(tailNext != null) {
                    tail.compareAndSet(curTail, tailNext);
                } else {
                    if(curTail.next.compareAndSet(null, newNode)) {
                        tail.compareAndSet(curTail, newNode);
                        return true;
                    }
                }
            }
        }
    }

    private static class Node<V> {

        private V item;

        private AtomicReference<Node<V>> next;


        public Node(V v, Node<V> next) {
            this.item = v;
            this.next = new AtomicReference<>(next);
        }
    }
}
