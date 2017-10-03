package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Iterator;

public class RWayTrieMap<V> implements Map<String, V> {

    private V previousValue = null;
    private Node<V> head = null;
    private int size = 0;

    private class Node<V>{
        V value;
        Node<V>[] next;
        private Node(V value) {
            next = (Node<V>[]) new Node[256];
            Arrays.fill(next, null);
            this.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(@NotNull String key) {
        return find(head, key, 0) != null;
    }

    @Override
    public V get(@NotNull String key) {
        final Node<V> result = find(head, key, 0);
        return result == null ? null : result.value;
    }

    @Override
    public V put(@NotNull String key, V value) {
        head = put(head, key, value, 0);
        return previousValue;
    }

    private Node<V> put(Node<V> node, @NotNull String key, V value, int level) {
        if (node == null){
            Node<V> result = new Node<V>(null);
            if(level < key.length()){
                int next = (int) key.charAt(level);
                result.next[next] = put(result.next[next], key, value, level+1);
            }
            else {
                size++;
                previousValue = null;
                result.value = value;
            }
            return result;
        }
        if(level == key.length()){
            previousValue = node.value;
            node.value = value;
            return node;
        }
        else{
            int next = (int) key.charAt(level);
            node.next[next] = put(node.next[next], key, value, level+1);
            return node;
        }
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public Iterator<String> keys() {
        return null;
    }

    private Node<V> find(Node<V> node, @NotNull String key, int level){
        if(node == null) return null;
        if(level == key.length()) return node;
        int next = (int) key.charAt(level);
        return find(node.next[next], key, level+1);
    }

}
