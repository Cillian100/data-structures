package project20280.list;
import project20280.interfaces.List;
import java.util.Iterator;

public class SinglyLinkedList<E> implements List<E> {
    private static class Node<E> {
        private final E element;
        private Node<E> next;

        public Node(E e, Node<E> n) {
            element = e;
            next = n;
        }

        public E getElement() {
            return element;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> n) {
            next = n;
        }
    }

    private Node<E> head = null;
    private int size = 0;

    public SinglyLinkedList(){
    }

    //@Override
    public int size() {
        return size;
    }

    //@Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public E get(int position){
        Node<E> dummy = head;
        for(int a=0;a<position;a++){
            if(dummy.getNext()==null){
                return null;
            }
            dummy=dummy.getNext();
        }
        return dummy.getElement();
    }

    @Override
    public void add(int position, E e){
        Node<E> dummy = head;
        for(int a=0;a<position-1;a++){
            if(dummy.getNext()==null){
                return;
            }
            dummy=dummy.getNext();
        }
        Node<E> newNode = new Node<>(e, dummy.getNext());
        dummy.next = newNode;
        size++;
    }


    @Override
    public void addFirst(E e){
        Node<E> newNode = new Node<>(e, head);
        head = newNode;
        size++;
    }

    @Override
    public void addLast(E e){
        Node<E> newest = new Node<E>(e, null);
        Node<E> last = head;
        if(last == null){
            head = newest;
        }else{
            while(last.getNext() != null){
                last = last.getNext();
            }
            last.setNext(newest);
        }
        size++;
    }

    @Override
    public E remove(int position){
        Node<E> dummy = head;
        E holding;
        for(int a=0;a<position-1;a++){
            if(dummy.getNext()==null){
                return null;
            }
            dummy=dummy.getNext();
        }
        holding=dummy.next.element;
        dummy.next=dummy.next.getNext();
        size--;
        return holding;
    }

    @Override
    public E removeFirst() {
        Node<E> dummy = head;
        if(head==null){
            return null;
        }
        E holding;
        holding=head.element;
        head=dummy.next;
        size--;
        return holding;
    }

    @Override
    public E removeLast(){
        Node<E> dummy = head;
        E holding;
        if(dummy.next==null){
            return null;
        }
        while(dummy.next.next!=null){
            dummy = dummy.next;
        }
        holding=dummy.next.element;
        dummy.next=null;
        size--;
        return holding;
    }

    //@Override
    public Iterator<E> iterator() {
        return new SinglyLinkedListIterator<E>();
    }

    @SuppressWarnings("hiding")
    private class SinglyLinkedListIterator<E> implements Iterator<E> {
        @SuppressWarnings("unchecked")
        Node<E> curr = (Node<E>) head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public E next() {
            E res = curr.getElement();
            curr = curr.next;
            return res;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = head;
        while (curr != null) {
            sb.append(curr.getElement());
            if (curr.getNext() != null)
                sb.append(", ");
            curr = curr.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> ll = new SinglyLinkedList<Integer>();
        //System.out.println("ll " + ll + " isEmpty: " + ll.isEmpty());
        //LinkedList<Integer> ll = new LinkedList<Integer>();
        ll.addLast(1);
        ll.addLast(2);
        ll.addLast(3);
        ll.addFirst(10);
        ll.addFirst(20);
        ll.addFirst(30);
        ll.add(3, 70);
        ll.removeLast();

        System.out.println(ll);
    }
}
