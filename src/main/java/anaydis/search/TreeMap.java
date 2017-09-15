package anaydis.search;

import java.util.Comparator;

abstract class TreeMap<K,V> implements Map<K,V>{

    protected Node<K,V> head;
    protected int size;
    protected Comparator<K> comparator;

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    protected class Node<K,V>{
        K key;
        V value;
        Node<K,V> left = null;
        Node<K,V> right = null;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node<K, V> left, Node<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public Node<K, V> getCopy() {
            return new Node<>(key, value, left, right);
        }

        public String toString(){
            return key + "[" + value + "]";
        }
    }

}
