package ebook.datastructure.chapter4;

/**
 * description: <br>
 * createTime: 2018/5/1715:27 <br>
 *
 * @author zzh
 */
public class MyQueue<E> {

    private static final int DEFAULT_CAPACITY = 20;

    private int capacity = DEFAULT_CAPACITY;

    private E[] elements;

    private int head = 0;

    private int tail = capacity - 1;

    private int size = 0;


    public MyQueue(int capacity) {
        this.capacity = capacity;
        elements = (E[]) new Object[capacity];
    }


    public MyQueue() {
        elements = (E[]) new Object[capacity];
    }

    public boolean insert(E e) {
        if(isFull()) {
            System.out.println("The queue is full!");
            return false;
        }
        tail++;
        if(tail == capacity) {
            tail = 0;
        }
        elements[tail] = e;
        size++;
        return true;
    }

    public E remove() {
        if(isEmpty()) {
            System.out.println("The queue is empty!");
            return null;
        }
        E e = elements[head ++];
        if(head == capacity) {
            head = 0;
        }
        size--;
        return e;
    }

    public E peek() {
        if(isEmpty()) {
            System.out.println("The queue is empty!");
            return null;
        }
        return elements[head];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }


    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>(3);
        queue.insert("a");
        queue.insert("b");
        queue.insert("c");
        queue.insert("d");
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.peek());
        System.out.println(queue.remove());
        System.out.println(queue.peek());
        System.out.println(queue.remove());
    }
}
