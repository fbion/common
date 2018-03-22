package advance.JVM.concurrent.test.test4;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

/**
 * description：非阻塞栈实现 <br>
 * createTime: 2018/3/219:08 <br>
 *
 * @author zzh
 */
@ThreadSafe
public class ConcurrentStack<V> {

    private AtomicReference<Node<V>> topNodeVeference = new AtomicReference();


    public void push(V v) {
        Node<V> newTopNode = new Node<>(v);
        Node<V> oldTopeNode;
        do {
            oldTopeNode = topNodeVeference.get();
            newTopNode.prevNode = topNodeVeference.get();
        } while (!topNodeVeference.compareAndSet(oldTopeNode, newTopNode));

    }


    public V pull() {
        Node<V> oldTopeNode;
        Node<V> newTopeNode;
        do {
            oldTopeNode = topNodeVeference.get();
            if (oldTopeNode == null) {
//                throw new IllegalArgumentException("Stack is empty,can't pull");
                return null;
            }
            newTopeNode = oldTopeNode.prevNode;
        } while (!topNodeVeference.compareAndSet(oldTopeNode, newTopeNode));

        return oldTopeNode.item;
    }


    private static class Node<V> {

        private V item;

        private Node<V> prevNode;


        public Node(V v) {
            this.item = v;
        }
    }
}
