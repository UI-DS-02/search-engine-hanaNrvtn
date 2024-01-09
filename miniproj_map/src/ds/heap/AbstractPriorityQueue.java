package ds.heap;

import java.util.Comparator;

abstract class AbstractPriorityQueue<K, V> implements PriorityQueue<K, V> {

    // inner class
    protected static class PQEntry<K, V> implements Entry<K, V> {

        // def var
        private K k;
        private V v;

        // cons
        public PQEntry(K key, V value) {
            k = key;
            v = value;
        }

        // method
        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        public void setK(K k) {
            this.k = k;
        }

        public void setV(V v) {
            this.v = v;
        }
    }

    // def var
    private Comparator<K> comp;

    // cons
    protected AbstractPriorityQueue(Comparator<K> c) {
        comp = c;
    }

    protected AbstractPriorityQueue() {
    }

    // method
    protected int compare(Entry<K, V> a, Entry<K, V> b) {
        return comp.compare(a.getKey(), b.getKey());
    }

    protected boolean checkKey(K key) throws IllegalArgumentException {
        try {
            return comp.compare(key, key) == 0;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Incompatible key");
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}
