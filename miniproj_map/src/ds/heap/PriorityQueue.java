package ds.heap;

import ds.heap.Entry;

interface PriorityQueue<K, V> {
    int size();

    Entry<K, V> insert(K key, V value);

    Entry<K, V> top();

    Entry<K, V> pop();

}
