import java.util.Set;
import java.util.Iterator;
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        private K key;
        private V value;
        private int size;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.size = 1;
        }
    }

    private Node root;

    public BSTMap() {
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return (get(key) != null);
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        return getHelper(root, key);
    }

    private V getHelper(Node currentRoot, K key) {
        if (currentRoot == null) {
            return null;
        }
        int compare = key.compareTo(currentRoot.key);
        if (compare == 0) {
            return currentRoot.value;
        } else if (compare < 0) {
            return getHelper(currentRoot.left, key);
        } else {
            return getHelper(currentRoot.right, key);
        }

    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        if (root == null) {
            return 0;
        }
        return size(root);
    }

    private int size(Node cR) {
        if (cR == null) {
            return 0;
        }
        return cR.size + size(cR.right) + size(cR.left);
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        root = putHelper(root, key, value);
    }

    private Node putHelper(Node cR, K key, V value) {
        if (cR == null) {
            return new Node(key, value);
        }
        int compare = key.compareTo(cR.key);
        if (compare == 0) {
            cR.value = value;
        } else if (compare < 0) {
            putHelper(cR.left, key, value);
        } else {
            putHelper(cR.right, key, value);
        }
        cR.size = size(cR.left) + size(cR.right) + 1;
        return cR;
    }

    @Override
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
