import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;

public class MyHashMap<K, V> implements Map61B<K, V> {
    private int size;
    private int entries;
    private double loadFactor;
    private HashSet<K> keys;
    private ArrayList<ArrayList<Entry>> buckets;

    private class Entry {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        this.size = 16;
        this.loadFactor = 0.75;
        keys = new HashSet<>();
        buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    public MyHashMap(int initialSize) {
        this.size = initialSize;
        this.loadFactor = 0.75;
        keys = new HashSet<>();
        buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.size = initialSize;
        this.loadFactor = loadFactor;
        keys = new HashSet<>();
        buckets = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        keys = new HashSet<>();
        buckets = new ArrayList<>();
        entries = 0;
        size = 0;
    }


    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int index = hashToIndex(key, size);
        for (Entry e: buckets.get(index)) {
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /**
     * Converts hash code to an index of buckets.
     */
    private int hashToIndex(K key, int capacity) {
        return Math.floorMod(key.hashCode(), capacity);
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return entries;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if (containsKey(key)) {
            replace(key, value);
            return;
        }
        if (((double) entries) / size > loadFactor) {
            resize(size * 2);
        }
        int index = hashToIndex(key, size);
        buckets.get(index).add(new Entry(key, value));
        keys.add(key);
        entries++;
    }

    /**
     * Overwrites the value of a key with the param value.
     */
    private void replace(K key, V value) {
        int index = hashToIndex(key, size);
        for (Entry e: buckets.get(index)) {
            if (e.key.equals(key)) {
                e.value = value;
            }
        }
    }

    /**
     * Resizes buckets to length capacity.
     */
    private void resize(int capacity) {
        ArrayList<ArrayList<Entry>> newBuckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            newBuckets.add(new ArrayList<>());
        }
        for (K key: keys) {
            int index = hashToIndex(key, capacity);
            newBuckets.get(index).add(new Entry(key, get(key)));
        }
        this.size = capacity;
        this.buckets = newBuckets;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keys;
    }

    /**
     * Uses the HashSet iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

}
