package ebook.datastructure;

/**
 * description: <br>
 * createTime: 2018/5/159:17 <br>
 *
 * @author zzh
 */
public class MyStack<E> {
    private static final int DEFAULT_CAPACITY = 20;

    private int capacity = DEFAULT_CAPACITY;

    private E[] elements;

    private int top = -1;


    public MyStack(int capacity) {
        this.capacity = capacity;
        elements = (E[]) new Object[capacity];
    }


    public MyStack() {
        elements = (E[]) new Object[capacity];
    }

    public boolean push(E e) {
        if(isFull()) {
            System.out.println("Stack is full");
            return false;
        }
        elements[++top] = e;
        return true;
    }

    public E pop() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return elements[top--];
    }

    public E peek(){
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        return elements[top];
    }

    public boolean isFull() {
        return top == capacity - 1;
    }

    public boolean isEmpty() {
        return top < 0;
    }


    public static void main(String[] args) {
        MyStack<String> myStack = new MyStack(3);
        myStack.push("a");
        myStack.push("b");
        myStack.push("c");

        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
        System.out.println(myStack.peek());
        System.out.println(myStack.pop());
    }
}
