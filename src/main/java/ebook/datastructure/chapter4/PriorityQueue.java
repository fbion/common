package ebook.datastructure.chapter4;

/**
 * description: <br>
 * createTime: 2018/5/1716:10 <br>
 *
 * @author zzh
 */
public class PriorityQueue<E extends Comparable> {

    private static final int DEFAULT_CAPACITY = 20;

    private int capacity = DEFAULT_CAPACITY;

    private E[] elements;

    private int size = 0;


    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        elements = (E[]) new Comparable[capacity];
    }


    public PriorityQueue() {
        elements = (E[]) new Object[capacity];
    }

    public boolean insert(E e) {
        if(isFull()) {
            System.out.println("queue is full,can't insert!");
            return false;
        }
        int i = size++ - 1;
        while( i >= 0 && elements[i].compareTo(e) > 0) {
            elements[i + 1] = elements[i];
            i--;
        }
        elements[i + 1] = e;
        return true;
    }

    public E remove() {
        if(isEmpty()) {
            System.out.println("queue is empty,can't remove!");
            return null;
        }
        return elements[--size];
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(6);
        queue.insert(7);
        queue.insert(6);
        queue.insert(4);
        queue.insert(2);
        queue.insert(3);
        queue.insert(5);
        queue.insert(1);
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
        System.out.println(queue.remove());
    }
}
