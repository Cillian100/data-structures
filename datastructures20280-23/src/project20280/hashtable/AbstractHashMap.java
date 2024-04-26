package project20280.hashtable;
import project20280.interfaces.AbstractMap;
import java.util.Random;

import java.util.*;

public abstract class AbstractHashMap<K, V> extends AbstractMap<K, V>{
    private class Entry<K, V>{
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value){
            this.key=key;
            this.value=value;
        }

        public K getKey(){
            return this.key;
        }

        public V getValue(){
            return this.value;
        }
        
        public void setValue(V newValue){
            this.value=newValue;
        }
    }
    private final int SIZE=10000;

    private Entry<K, V> table[];

    private final int prime;                   // prime factor
    private final long scale;
    private final long shift;           // the shift and scaling factors
    protected int n = 0;                 // number of entries in the dictionary
    protected int capacity;              // length of the table

    public AbstractHashMap(int cap, int p) {
        prime = p;
        capacity = cap;
        Random rand = new Random();
        scale = rand.nextInt(prime - 1) + 1;
        shift = rand.nextInt(prime);
        table = new Entry[SIZE];
        createTable();
    }

   public AbstractHashMap(int cap) {
        this(cap, 109345121);
    }

    public AbstractHashMap() {
        this(17);
    }

   @Override
    public int size() {
        return n;
    }

    @Override
    public V get(K key) {
        int hash=key.hashCode()%SIZE;
        Entry<K,V> e = table[hash];

        if(e==null){
            return null;
        }

        while(e!=null){
            if(e.getKey().equals(key)){
                return e.getValue();
            }
            e = e.next;
        }

        return null;
    }

    @Override
    public V remove(K key){
        int hash = key.hashCode() % SIZE;
        V holder = table[hash].getValue();
        table[hash]=null;
        n--;
        return holder;
    }

    @Override
    public V put(K key, V value) {
        int hash = key.hashCode() % SIZE;
        Entry<K, V> e = table[hash];
        n++;

        if(e==null){
            table[hash]=new Entry<K, V>(key, value);
        }else{
            while(e.next != null){
                if(e.getKey() == key){
                    e.setValue(value);
                    return null;
                }
                e=e.next;
            }

            if(e.getKey() == key){
                e.setValue(value);
                return null;
            }

            e.next = new Entry<K, V>(key, value);
        }
        return null;
    }

    public ArrayList<V> values(){
        ArrayList<V> returnValues = new ArrayList<>();
        for(int a=0;a<SIZE;a++){
            if(table[a]!=null){
                returnValues.add(table[a].getValue());
            }
        }
        return returnValues;
    }

    public V last(){
        V holder=null;
        for(int a=0;a<SIZE;a++){
            if(table[a]!=null){
                holder=table[a].getValue();
            }
        }
        return holder;
    }

    public ArrayList<K> keySet(){
        ArrayList<K> returnValue = new ArrayList<K>();
        for(int a=0;a<SIZE;a++){
            if(table[a]!=null){
                returnValue.add(table[a].getKey());
            }
        }
        return returnValue;
    }

    public boolean containsKey(K key){
        for(int a=0;a<SIZE;a++){
            if(table[a]!=null){
                if(table[a].getKey()==key){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int a=0;a<SIZE;a++){
            if(table[a]!=null){
                str.append(table[a].getKey() + "=" + table[a].getValue() + ", ");
            }
        }
    
        str.insert(str.length()-1, "]");
        //System.out.println("willy");
        return str.toString();
    }

    private int hashValue(K key) {
        // TODO
        return 0;
    }

    /**
     * Updates the size of the hash table and rehashes all entries.
     */
    private void resize(int newCap) {
        // TODO
    }

    // protected abstract methods to be implemented by subclasses

    /**
     * Creates an empty table having length equal to current capacity.
     */
    protected abstract void createTable();

    /**
     * Returns value associated with key k in bucket with hash value h.
     * If no such entry exists, returns null.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return associate value (or null, if no such entry)
     */
    protected abstract V bucketGet(int h, K k);

    /**
     * Associates key k with value v in bucket with hash value h, returning
     * the previously associated value, if any.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @param v the value to be associated
     * @return previous value associated with k (or null, if no such entry)
     */
    protected abstract V bucketPut(int h, K k, V v);

    /**
     * Removes entry having key k from bucket with hash value h, returning
     * the previously associated value, if found.
     *
     * @param h the hash value of the relevant bucket
     * @param k the key of interest
     * @return previous value associated with k (or null, if no such entry)
     */
    protected abstract V bucketRemove(int h, K k);
}
