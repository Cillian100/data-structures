package project20280.stacksqueues;
import project20280.interfaces.Stack;
public class ArrayStack<E> implements Stack<E> {
    public static final int CAPACITY = 100;
    private E[] data;
    private int t = -1;  
    
    public ArrayStack() {
        this(CAPACITY);
    }

    @SuppressWarnings({"unchecked"})
    public ArrayStack(int capacity) {        // constructs stack with given capacity
        data = (E[]) new Object[capacity];
    }
    
    @Override
    public int size() {
        return (t + 1);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void push(E e) {
        data[size()]=e;
        t++;
        return;
    }

    @Override
    public E top(){
        if(size()==0){
            return null;
        }else{
            return data[size()-1];
        }
    }

    @Override
    public E pop(){
        E holding=data[size()-1];
        data[size()-1]=null;
        t--;
        return holding;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        if (!isEmpty()) {
            for (int i = t; i >= 0; --i) {
                sb.append(data[i]);
                if (i != 0) sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> S = new ArrayStack<>();  // contents: ()
        S.push(5);                              // contents: (5)
        S.push(3);                              // contents: (5, 3)
        System.out.println(S.size());           // contents: (5, 3)     outputs 2
        System.out.println(S.pop());            // contents: (5)        outputs 3
        System.out.println(S.isEmpty());        // contents: (5)        outputs false
        System.out.println(S.pop());            // contents: ()         outputs 5
        System.out.println(S.isEmpty());        // contents: ()         outputs true
        System.out.println(S.pop());            // contents: ()         outputs null
        S.push(7);                              // contents: (7)
        S.push(9);                              // contents: (7, 9)
        System.out.println(S.top());            // contents: (7, 9)     outputs 9
        S.push(4);                              // contents: (7, 9, 4)
        System.out.println(S.size());           // contents: (7, 9, 4)  outputs 3
        System.out.println(S.pop());            // contents: (7, 9)     outputs 4
        S.push(6);                              // contents: (7, 9, 6)
        S.push(8);                              // contents: (7, 9, 6, 8)
        System.out.println(S.pop());            // contents: (7, 9, 6)  outputs 8
        System.out.println(S);
    }
}
