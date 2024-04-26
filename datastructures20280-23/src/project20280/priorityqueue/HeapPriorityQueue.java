package project20280.priorityqueue;
import project20280.interfaces.Entry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;

public class HeapPriorityQueue<K, V> extends AbstractPriorityQueue<K, V> {
    protected ArrayList<Entry<K, V>> heap = new ArrayList<>();
    int size=0;
    public HeapPriorityQueue() {
        super();
    }

    /**
     * Creates an empty priority queue using the given comparator to order keys.
     *
     * @param comp comparator defining the order of keys in the priority queue
     */
    public HeapPriorityQueue(Comparator<K> comp) {
        super(comp);
    }

    /**
     * Creates a priority queue initialized with the respective key-value pairs. The
     * two arrays given will be paired element-by-element. They are presumed to have
     * the same length. (If not, entries will be created only up to the length of
     * the shorter of the arrays)
     *
     * @param keys   an array of the initial keys for the priority queue
     * @param values an array of the initial values for the priority queue
     */
    public HeapPriorityQueue(K[] keys, V[] values) {
        // TODO
    }

    // protected utilities
    protected int parent(int j) {
        // TODO
        return 0;
    }

    protected int left(int j) {
        // TODO
        return 0;
    }

    protected int right(int j) {
        // TODO
        return 0;
    }

    protected boolean hasLeft(int j) {
        // TODO
        return false;
    }

    protected boolean hasRight(int j) {
        // TODO
        return false;
    }

    /**
     * Moves the entry at index j higher, if necessary, to restore the heap
     * property.
     */
    protected void upheap(int j) {
        // TODO
    }

    /**
     * Moves the entry at index j lower, if necessary, to restore the heap property.
     */
    protected void downheap(int j) {
        // TODO
    }

    /**
     * Performs a bottom-up construction of the heap in linear time.
     */
    protected void heapify() {
        // TODO
    }

    // public methods

    /**
     * Returns the number of items in the priority queue.
     *
     * @return number of items
     */
    @Override
    public int size() {
        return size;
    }
    /**
     * Returns (but does not remove) an entry with minimal key.
     *
     * @return entry having a minimal key (or null if empty)
     */
    @Override
    public Entry<K, V> min(){
        return heap.get(0);
    }

    /**
     * Inserts a key-value pair and return the entry created.
     *
     * @param key   the key of the new entry
     * @param value the associated value of the new entry
     * @return the entry storing the new key-value pair
     * @throws IllegalArgumentException if the key is unacceptable for this queue
     */
    @Override
    public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
        Entry<K,V> newest = new PQEntry<>(key, value);
        heap.add(newest);
        // for(int a=0;a<size-1;a++){
        //     if((int)heap.get(a).getKey()>(int)heap.get(size).getKey()){
        //         swap(a, size);
        //     }
        // }
        percolateUp(heap.size() - 1);
        size++;
        return newest;
    }

    private void percolateUp(int index){
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (compare(heap.get(index), heap.get(parentIndex)) >= 0) {
                break; // Parent is smaller, heap property satisfied
            }
            // Swap the current entry with its parent
            swap(index, parentIndex);
            index = parentIndex; // Move up to the parent
        }

    }

    public void swap(int newer, int older){
        Entry<K, V> temp = heap.get(older);
        heap.set(older, heap.get(newer));
        heap.set(newer, temp);
        return;
    }

    /**
     * Removes and returns an entry with minimal key.
     *
     * @return the removed entry (or null if empty)
     */
    @Override
    public Entry<K, V> removeMin() {
        Entry<K, V> holder = heap.get(0);
        for(int a=0;a<size-1;a++){
            swap(a, a+1);
        }
        heap.remove(heap.get(size-1));
        size--;
        return holder;
    }

    public String toString() {
        return heap.toString();
    }

    /**
     * Used for debugging purposes only
     */
    private void sanityCheck() {
        for (int j = 0; j < heap.size(); j++) {
            int left = left(j);
            int right = right(j);
            //System.out.println("-> " +left + ", " + j + ", " + right);
            Entry<K, V> e_left, e_right;
            e_left = left < heap.size() ? heap.get(left) : null;
            e_right = right < heap.size() ? heap.get(right) : null;
            if (left < heap.size() && compare(heap.get(left), heap.get(j)) < 0) {
                System.out.println("Invalid left child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
            if (right < heap.size() && compare(heap.get(right), heap.get(j)) < 0) {
                System.out.println("Invalid right child relationship");
                System.out.println("=> " + e_left + ", " + heap.get(j) + ", " + e_right);
            }
        }
    }

    public static void main(String[] args){
        HeapPriorityQueue<Integer, String> pq = new HeapPriorityQueue<>();
        pq.insert(35, "35");
        pq.insert(26, "26");
        pq.insert(3, "3");
        pq.insert(60, "60");
        pq.insert(60, "60");
        // Integer[] rands = new Integer[]{35, 26, 15, 24, 33, 4, 12, 1, 23, 21, 2, 5};
        // HeapPriorityQueue<Integer, Integer> pq = new HeapPriorityQueue<>(rands, rands);

        // System.out.println("elements: " + rands);
        // System.out.println("after adding elements: " + pq);

        // System.out.println("min element: " + pq.min());

        // pq.removeMin();
        // System.out.println("after removeMin: " + pq);
        // [             1,
        //        2,            4,
        //   23,     21,      5, 12,
        // 24, 26, 35, 33, 15]
    }
}
