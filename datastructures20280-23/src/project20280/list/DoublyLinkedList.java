package project20280.list;
import project20280.interfaces.List;
import java.util.Iterator;

public class DoublyLinkedList<E> implements List<E> {

    private static class Node<E> {
        private final E data;
        private Node<E> next;
        private Node<E> prev;

        public Node(E e){
            data = e;

        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }

        @SuppressWarnings("unused")
        public Node<E> getPrev() {
            return prev;
        }

        @SuppressWarnings("unused")
        public void setNext(Node<E> n){
            next = n;
        }

    }

    private Node<E> head;
    private Node<E> tail;
    private int size = 0;

    public DoublyLinkedList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.next = tail;
    }

    @SuppressWarnings("unused")
    private void addBetween(E e, Node<E> pred, Node<E> succ) {
        // TODO
    }

    @Override
    public int size() {
        // TODO
        return size;
    }

    public E getTail(){
        return tail.data;
    }

    public E getHead(){
        return head.data;
    }

    @Override
    public boolean isEmpty() {
        // TODO
        return size==0;
    }

    @Override
    public E get(int i) {
        Node<E> podge = head;
        for(int a=0;a<i;a++){
            if(podge==null){
                return null;
            }
            podge=podge.next;
        }
        return podge.data;
    }

    @Override
    public void add(int i, E e) {
        Node<E> newNode = new Node<>(e);
        Node<E> podge = head;
        for(int a=1;a<i;a++){
            if(podge==null){
                return;
            }
            podge=podge.next;
        }
        newNode.next=podge.next;
        newNode.prev=podge;
        podge.next=newNode;
        size++;
        return;
    }

    @Override
    public E remove(int i) {
        Node<E> podge = head;
        E holding;
        for(int a=1;a<i;a++){
            if(podge==null){
                return null;
            }
            podge=podge.next;
        }
        holding=podge.data;
        podge.next=podge.next.next;
        return holding;
    }

    @SuppressWarnings("hiding")
    private class DoublyLinkedListIterator<E> implements Iterator<E> {
        @SuppressWarnings("unchecked")
        Node<E> curr = (Node<E>) head.next;

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
        return new DoublyLinkedListIterator<E>();
    }

    @SuppressWarnings("unused")
    private E remove(Node<E> n) {
        // TODO
        return null;
    }

    public E first() {
        if (isEmpty()) {
            return null;
        }
        return head.getData();
    }

    public E last() {
        Node<E> podge = head;
        while(podge.getNext()!=null){
            podge=podge.next;
        }
        return podge.data;
    }

    @Override
    public E removeFirst() {
        Node<E> podge = head;
        E holding=podge.data;
        head=head.next;
        size--;
        return holding;
    }

    @Override
    public E removeLast(){
        Node<E> podge = head;
        E  holding;
        while(podge.next!=null){
            podge=podge.next;
        }
        holding=podge.data;
        if(podge.prev!=null){
            podge.prev.next=null;
            tail=podge.prev;
        }else{
            podge=null;
            tail=null;
            head=null;
        }
        size--;
        return holding;
    }

    @Override
    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        if(isEmpty()){
            head=newNode;
            tail=newNode;
        }else{
            tail.next=newNode;
            newNode.prev=tail;
            tail=newNode;
        }
        size++;
    }

    @Override
    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        if(head==null){
            return "[]";
        }
        while(curr.next!=null){
            sb.append(curr.data);
            sb.append(", ");
            curr=curr.next;
        }
        sb.append(curr.data);
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
    }
}