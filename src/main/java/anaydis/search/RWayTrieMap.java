package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RWayTrieMap<V> extends TreeMap<String, V> {

    private Node<V> head = null;

    private class Node<V>{
        private V value;
        private Node<V>[] next;
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
        ArrayList<String> keys = new ArrayList<>();
        traverse(head, "", keys);
        return keys.iterator();
    }

    private void traverse(Node<V> node, String originKey, List<String> target){
        Node<V>[] array = node.next;
        for(int i = 0; i < array.length; i++){
            Node<V> currentNode = array[i];
            if(array[i] != null){
                String nodeKey = originKey + (char) i;
                if(currentNode.value != null){
                    target.add(nodeKey);
                }
                traverse(currentNode,nodeKey,target);
            }
        }
    }

    private Node<V> find(Node<V> node, @NotNull String key, int level){
        if(node == null) return null;
        if(level == key.length()) return node;
        int next = (int) key.charAt(level);
        return find(node.next[next], key, level+1);
    }

}
