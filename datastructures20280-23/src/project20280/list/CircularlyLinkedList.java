package project20280.list;
import project20280.interfaces.List;
import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {
    private class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        public Node(T e) {
            data = e;
        }

        @SuppressWarnings("unused")
        public T getData() {
            return data;
        }

        @SuppressWarnings("unused")
        public void setNext(Node<T> n) {
            next = n;
        }

        @SuppressWarnings("unused")
        public Node<T> getNext() {
            return next;
        }
    }

    Node<E> tail = null;
    Node<E> head = null;
    int size = 0;

    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i){
        Node<E> holding = head;
        for(int a=0;a<i;a++){
            holding=holding.next;
            if(holding==head){
                return null;
            }
        }
        return holding.data;
    }

    @Override
    public void add(int i, E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> holding = head;
        for(int a=1;a<i;a++){
            holding=holding.next;
            if(holding==head){
                return;
            }
        }
        newNode.next=holding.next;
        holding.next=newNode;
        newNode.prev=holding;
        newNode.next.prev=newNode;
        size++;
    }

    @Override
    public E remove(int i) {
        Node<E> holding = head;
        E wockySlosh;
        for(int a=1;a<i;a++){
            holding=holding.next;
            if(holding==head){
                return null;
            }
        }
        wockySlosh=holding.next.data;
        holding.next=holding.next.next;
        holding.next.prev=holding;
        size--;
        return wockySlosh;
    }

    public void rotate(){
        Node<E> newList = head;
        head=newList.next;
        Node<E> newList2 = tail;
        tail=newList2.next;
    }

    public void rotate2() {
        //this is what I thought rotate was supposed to be and it look me ages so I'll leave it in
        Node<E> top = head;
        Node<E> bottom = tail;
        Node<E> willy = new Node<>(bottom.data);
        Node<E> preveious = willy;
        Node<E> willyHead = willy;

        while(bottom!=top){
            bottom=bottom.prev;
            willy.next = new Node<>(bottom.data);
            willy.prev = preveious;
            preveious = willy;
            willy=willy.next;
        }
        tail=willy;
        willy.next=willyHead;
        head=willy.next;
    }

    @SuppressWarnings("hiding")
    private class CircularlyLinkedListIterator<E> implements Iterator<E> {
        @SuppressWarnings("unchecked")
        Node<E> curr = (Node<E>) tail;

        @Override
        public boolean hasNext() {
            return curr != tail;
        }

        @Override
        public E next() {
            E res = curr.data;
            curr = curr.next;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator<E>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if(isEmpty()){
            return null;
        }
        Node<E> holding = head;
        E wockySlosh = holding.data;
        head=holding.next;
        tail=head;
        size--;
        return wockySlosh;
    }

    @Override
    public E removeLast() {
        E wockySlosh;
        if(isEmpty()){
            return null;
        }else{
            wockySlosh=tail.data;
            tail=tail.prev;
            tail.next=head;
        }
        size--;
        return wockySlosh;
    }

    @Override
    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        if(isEmpty()){
            newNode.next = newNode;
            newNode.prev = newNode;
            tail=newNode;
            head=newNode;
        }else{
            newNode.next=head;
            newNode.prev=head.prev;
            head.prev.next=newNode;
            head.prev=newNode;
            head=newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e);
        if(isEmpty()){
            newNode.next = newNode;
            newNode.prev = newNode;
            tail=newNode;
            head=newNode;
        }else{
            newNode.next=head;
            newNode.prev=tail;
            tail.next=newNode;
            head.prev=newNode;
            tail=newNode;
        }
        size++;
    }


    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail;
        if(curr==null){
            return "[]";
        }
        do {
            curr = curr.next;
            sb.append(curr.data);
            if (curr != tail) {
                sb.append(", ");
            }
        } while (curr != tail);
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        ll.addFirst(20);
        ll.addFirst(30);
        ll.addFirst(70);
        ll.add(2, 1000);
        System.out.println(ll);
        ll.rotate();
        System.out.println(ll);
    }
}
