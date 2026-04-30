import java.util.ArrayList;
import java.util.Iterator;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {

    private Node root;
    private int size;

    public static class Entry<K, V> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node current, K key, V value) {
        if (current == null) {
            size++;
            return new Node(key, value);
        }

        int result = key.compareTo(current.key);

        if (result < 0) {
            current.left = put(current.left, key, value);
        } else if (result > 0) {
            current.right = put(current.right, key, value);
        } else {
            current.value = value;
        }

        return current;
    }

    public V get(K key) {
        Node current = root;

        while (current != null) {
            int result = key.compareTo(current.key);

            if (result < 0) {
                current = current.left;
            } else if (result > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }

        return null;
    }

    public void delete(K key) {
        if (containsKey(key)) {
            root = delete(root, key);
            size--;
        }
    }

    private Node delete(Node current, K key) {
        if (current == null) {
            return null;
        }

        int result = key.compareTo(current.key);

        if (result < 0) {
            current.left = delete(current.left, key);
        } else if (result > 0) {
            current.right = delete(current.right, key);
        } else {
            if (current.left == null) {
                return current.right;
            }

            if (current.right == null) {
                return current.left;
            }

            Node smallest = findSmallest(current.right);

            current.key = smallest.key;
            current.value = smallest.value;

            current.right = deleteSmallest(current.right);
        }

        return current;
    }

    private Node findSmallest(Node current) {
        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    private Node deleteSmallest(Node current) {
        if (current.left == null) {
            return current.right;
        }

        current.left = deleteSmallest(current.left);
        return current;
    }

    private boolean containsKey(K key) {
        Node current = root;

        while (current != null) {
            int result = key.compareTo(current.key);

            if (result < 0) {
                current = current.left;
            } else if (result > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        ArrayList<Entry<K, V>> list = new ArrayList<>();

        inOrder(root, list);

        return list.iterator();
    }

    private void inOrder(Node current, ArrayList<Entry<K, V>> list) {
        if (current == null) {
            return;
        }

        inOrder(current.left, list);

        list.add(new Entry<>(current.key, current.value));

        inOrder(current.right, list);
    }
}
