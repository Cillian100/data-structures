package project20280.tree;
import project20280.interfaces.Position;
import java.util.ArrayList;
import java.util.*;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    static java.util.Random rnd = new java.util.Random();
    protected Node<E> root = null;
    private int size = 0;
    public LinkedBinaryTree() {
    }
    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    
    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<Integer>();
        Position<Integer> root = bt.addRoot(12);
    }

    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    public Position<E> addRoot(E e) throws IllegalStateException {
        if(root!=null){
            throw new IllegalArgumentException();
        }
        root = new Node<>(e, null, null, null);
        size=1;
        return root;
    }

    public void insert(E e) {
        // TODO

    }


    // recursively add Nodes to binary tree in proper position
    // private Node<E> addRecursive(Node<E> p, E e) {
    //     // TODO
    //     return null;
    // }

    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException{
        Node<E> parent = validate(p);
        if(parent.getLeft()!=null){
            throw new IllegalArgumentException();
        }
        Node<E> newNode = createNode(e, parent, null, null);
        parent.setLeft(newNode);
        size++;
        return newNode;
    }

    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if(parent.getRight()!=null){
            throw new IllegalArgumentException();
        }
        Node<E> newNode = createNode(e, parent, null, null);
        parent.setRight(newNode);
        size++;
        return newNode;
    }

    public E set(Position<E> p, E e) throws IllegalArgumentException {
        // TODO
        return null;
    }

    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        // TODO
    }

    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> podge = validate(p);
        E holding;
        boolean rightorleft=true;
        while(podge.getRight()!=null || podge.getLeft()!=null){
            if(podge.getRight()!=null){
                podge=podge.getRight();
                rightorleft=true;
            }else if(podge.getLeft()!=null){
                podge=podge.getLeft();
                rightorleft=false;
            }

        }
        validate(p).setElement(podge.getElement());
        holding=validate(p).getElement();
        if(rightorleft){ podge.getParent().setRight(null);}
        else{ podge.getParent().setLeft(null); }
        size--;
        return holding;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        // TODO
    }

    // private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
    //     // TODO
    //     return null;
    // }

    public void createLevelOrder(E[] arr) {
        if(root!=null){
            throw new IllegalArgumentException();
        }
        Queue<Position<E>> queue = new LinkedList<>();
        addRoot(arr[0]);
        int counter=1;
        int arrayLength=arr.length;
        Position<E> holder;
        queue.add(root);
        holder=queue.remove();

        while(counter<arrayLength){
            queue.add(addLeft(holder, arr[counter]));
            counter++;
            if(counter<arrayLength){ queue.add(addRight(holder, arr[counter])); }
            counter++;
            holder=queue.remove();
        }

        System.out.println(arrayLength);
    }



    // private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
    //     // TODO
    //     return null;
    // }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    protected static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}
