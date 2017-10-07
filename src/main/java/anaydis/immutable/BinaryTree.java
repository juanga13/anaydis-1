package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryTree<K,V> implements Map<K,V>{

    private Node<K,V> head;
    private int size = 0;
    private final Comparator<K> comparator;

    public BinaryTree(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    private BinaryTree(Comparator<K> comparator, Node<K,V> head, int size) {
        this.comparator = comparator;
        this.head = head;
        this.size = size;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        final Node<K, V> result = find(head, key);
        return result == null ? null : result.value;
    }

    @Override
    public Map<K, V> put(@NotNull K key, V value) {
        PutResult<K,V> result = put(head, key, value);
        return new BinaryTree<>(comparator, result.node,
                result.inserted ? size + 1 : size);
    }

    private PutResult<K,V> put(Node<K,V> node, K key, V value){
         if(node == null){
            return new PutResult<>(new Node<>(key, value), true);
         }
         else {
             int cmp = comparator.compare(key, node.key);
             PutResult<K,V> result;
             if(cmp < 0) {
                 PutResult<K,V> leftR = put(node.left, key, value);
                 result = new PutResult<>(new Node<>(node.key, node.value, leftR.node, node.right), leftR.inserted);
             }
             else if(cmp > 0) {
                 PutResult<K,V> rightR = put(node.right, key, value);
                 result = new PutResult<>(new Node<>(node.key, node.value, node.left, rightR.node), rightR.inserted);
             }
             else {
                 Node<K,V> copyNode = node.getCopy();
                 copyNode.value = value;
                 result = new PutResult<>(copyNode, false);
             }
             return result;
         }
    }

    @Override
    public Iterator<K> keys() {
        ArrayList<K> keys = new ArrayList<>();
        keysInOrder(head, keys);
        return keys.iterator();
    }

    private void keysInOrder(Node<K, V> node, @NotNull ArrayList<K> result){
        if(node != null) {
            keysInOrder(node.left, result);
            result.add(node.key);
            keysInOrder(node.right, result);
        }
    }

    private Node<K,V> find(Node<K, V> node, @NotNull K key){
        if(node == null) return null;
        int cmp = comparator.compare(key, node.key);
        if(cmp == 0) return node;
        if(cmp < 0) return find(node.left, key);
        return find(node.right, key);
    }

    private class Node<K,V>{
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

    private class PutResult<K,V>{
        Node<K,V> node;
        boolean inserted;

        PutResult(Node<K, V> node, boolean inserted) {
            this.node = node;
            this.inserted = inserted;
        }
    }
}
