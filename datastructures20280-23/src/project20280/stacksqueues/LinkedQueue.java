package project20280.stacksqueues;

import project20280.interfaces.Queue;
import project20280.list.DoublyLinkedList;

public class LinkedQueue<E> implements Queue<E> {

    private DoublyLinkedList<E> ll = new DoublyLinkedList<>();

    public static void main(String[] args) {
        LinkedQueue<Integer> s = new LinkedQueue<>();
        System.out.println(s);
        s.enqueue(10);
        s.enqueue(20);
        System.out.println(s);
        s.dequeue();
        s.dequeue();
        System.out.println(s);
    }

    public LinkedQueue() {
        // TODO
    }

    @Override
    public int size() {
        return ll.size();
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        ll.addLast(e);
    }

    @Override
    public E first() {
        return ll.getHead();
    }

    @Override
    public E dequeue() {
        E holder=ll.removeFirst();
        return holder;
    }

    public String toString() {
        return ll.toString();
    }
}
