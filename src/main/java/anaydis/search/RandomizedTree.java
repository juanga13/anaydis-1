package anaydis.search;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class RandomizedTree<K,V> extends TreeMap<K,V>{//TODO remove

    public RandomizedTree(Comparator<K> comparator) {
        super(comparator);
    }

    @Override
    public boolean containsKey(@NotNull K key) {
        return find(head, key) != null;
    }

    @Override
    public V get(@NotNull K key) {
        return find(head, key).value;
    }

    @Override
    public V put(@NotNull K key, V value) {
        head = rootPut(head, key, value);
        return null;
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
             else node.value = value;
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
        inOrderString(head, keys);
        return keys.iterator();
    }

    public void inOrderString(Node<K,V> node,@NotNull ArrayList<K> result){
        if(node != null) {
            inOrderString(node.left, result);
            result.add(node.key);
            inOrderString(node.right, result);
        }
    }

    public Node<K,V> find(Node<K,V> node,@NotNull K key){
        if(node == null) return null;
        int cmp = comparator.compare(key, node.key);
        if(cmp == 0) return node;
        if(cmp < 0) return find(node.left, key);
        return find(node.right, key);
    }

}
