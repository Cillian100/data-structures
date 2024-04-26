package project20280.hashtable;
import project20280.interfaces.Entry;
import java.util.ArrayList;

public class ChainHashMap<K, V> extends AbstractHashMap<K, V> {
    private UnsortedTableMap<K, V>[] table;

    public ChainHashMap() {
        super();
    }

    public ChainHashMap(int cap) {
        super(cap);
    }

    public ChainHashMap(int cap, int p) {
        super(cap, p);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    protected void createTable() {
        table = new UnsortedTableMap[capacity];
    }

    @Override
    protected V bucketGet(int h, K k) {
        // TODO
        return null;
    }

    @Override
    protected V bucketPut(int h, K k, V v) {
        // TODO
        return null;
    }

    @Override
    protected V bucketRemove(int h, K k) {
        // TODO
        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K, V>> entries = new ArrayList<>();
        for (UnsortedTableMap<K, V> tm : table) {
            if (tm != null) {
                for (Entry<K, V> e : tm.entrySet()) {
                    entries.add(e);
                }
            }
        }
        return entries;
    }

    // public String toString() {
    //     return entrySet().toString();
    // }

    public static void main(String[] args) {
        ChainHashMap<Character, Integer> m = new ChainHashMap<Character, Integer>();
        m.put('a', 10);
        System.out.println(m);
        m.put('a', 11);
        System.out.println(m);


    }
}
