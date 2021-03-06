package anaydis.search;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class RandomizedTreeMap<K,V> extends TreeMap<K,V>{//TODO remove

    private Node<K,V> head;
    private final Random random = new Random();
    private static final double PROBABILITY = 0.5;
    private final Comparator<K> comparator;

    public RandomizedTreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
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
    public V put(@NotNull K key, V value) {
        if(random.nextDouble() < PROBABILITY)
            head = rootPut(head, key, value);
        else head = put(head, key, value);
        final V aux = previousValue;
        previousValue = null;
        return aux;
    }

    private Node<K,V> put(Node<K,V> node, K key, V value){
         if(node == null){
            size++;
            return new Node<K,V>(key, value);
         }
         else {
             int cmp = comparator.compare(key, node.key);
             if(cmp < 0) node.left = put(node.left, key, value);
             else if(cmp > 0) node.right = put(node.right, key, value);
             else {
                 previousValue = node.value;
                 node.value = value;
             }
             return node;
         }
    }

    private Node<K,V> rootPut(Node<K,V> node, @NotNull K key, V value) {
        if(node == null){
            size++;
            return new Node<K,V>(key, value);
        }
        else{
            int cmp = comparator.compare(key, node.key);
            if(cmp < 0){
                node.left = rootPut(node.left, key, value);
                return rotateRight(node);
            }
            else if(cmp > 0){
                node.right = rootPut(node.right, key, value);
                return rotateLeft(node);
            }
            else{
                previousValue = node.value;
                node.value = value;
                return node;
            }
        }
    }

    private Node<K,V> rotateRight(Node<K, V> node) {
        Node<K,V> result = node.left.getCopy();
        node.left = result.right;
        result.right = node;
        return result;
    }

    private Node<K,V> rotateLeft(Node<K, V> node) {
        Node<K, V> result = node.right.getCopy();
        node.right = result.left;
        result.left = node;
        return result;
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
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
