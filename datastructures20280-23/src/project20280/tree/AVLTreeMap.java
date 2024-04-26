package project20280.tree;
import project20280.interfaces.Entry;
import project20280.interfaces.Position;
import java.util.Comparator;

public class AVLTreeMap<K, V> extends TreeMap<K, V> {
    public AVLTreeMap() {
        super();
    }

    public AVLTreeMap(Comparator<K> comp) {
        super(comp);
    }

    protected int height(Position<Entry<K, V>> p) {
        // TODO
        return 0;
    }

    protected void recomputeHeight(Position<Entry<K, V>> p) {
        // TODO
    }

    protected boolean isBalanced(Position<Entry<K, V>> p) {
        // TODO
        return false;
    }

    protected Position<Entry<K, V>> tallerChild(Position<Entry<K, V>> p) {
        // TODO
        return null;
    }

    protected void rebalance(Position<Entry<K, V>> p) {
        // TODO
    }

    @Override
    protected void rebalanceInsert(Position<Entry<K, V>> p) {
        rebalance(p);
    }

    @Override
    protected void rebalanceDelete(Position<Entry<K, V>> p) {
        // TODO
    }

    private boolean sanityCheck() {
        for (Position<Entry<K, V>> p : tree.positions()) {
            if (isInternal(p)) {
                if (p.getElement() == null)
                    System.out.println("VIOLATION: Internal node has null entry");
                else if (height(p) != 1 + Math.max(height(left(p)), height(right(p)))) {
                    System.out.println("VIOLATION: AVL unbalanced node with key " + p.getElement().getKey());
                    dump();
                    return false;
                }
            }
        }
        return true;
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<Entry<K, V>> btp = new BinaryTreePrinter<>(this.tree);
        return btp.print();
    }

    public static void main(String[] args) {
        AVLTreeMap<Integer, String> avl = new AVLTreeMap<>();
        avl.put(1, Integer.toString(1));

        // Integer[] arr = new Integer[]{5, 3, 10, 2, 4, 7, 11, 1, 6, 9, 12, 8};

        // for (Integer i : arr) {
        //     if (i != null) avl.put(i, i);
        //     System.out.println("root " + avl.root());
        // }
        // System.out.println(avl.toBinaryTreeString());

        // avl.remove(5);
        // System.out.println(avl.toBinaryTreeString());

    }
}
