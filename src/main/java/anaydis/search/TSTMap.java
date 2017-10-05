package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TSTMap<V> extends TreeMap<String,V>{

    private Node<V> head = null;

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

    private Node<V> put(Node<V> node, String key, V value, int level){
        if(node == null){
            Node<V> result = new Node<>(key.charAt(level));
            if(level + 1 < key.length()){
                result.middle = put(result.middle, key, value, level+1);
            }
            else{
                previousValue = null;
                result.value = value;
                size++;
            }
            return result;
        }
        else if(key.charAt(level) == node.chr){
            if(level + 1 == key.length()){
                if(node.value == null) size++;
                previousValue = node.value;
                node.value = value;
            }
            else{
                node.middle = put(node.middle, key, value, level+1);
            }
            return node;
        }
        else if(key.charAt(level) > node.chr){
            node.right = put(node.right, key, value, level);
            return node;
        }
        else {
            node.left = put(node.left, key, value, level);
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
        keysInOrder(head, "", keys);
        return keys.iterator();
    }

    private void keysInOrder(Node<V> node, String currentKey, List<String> keys){
        if(node == null) return;
        keysInOrder(node.left, currentKey, keys);
        String nodeKey = currentKey + node.chr;
        if(node.value != null) keys.add(nodeKey);
        keysInOrder(node.middle, nodeKey, keys);
        keysInOrder(node.right, currentKey, keys);
    }

    private Node<V> find(Node<V> node, @NotNull String key, int level){
        if(node == null || level >= key.length()) return null;
        if(key.charAt(level) == node.chr){
            return key.length() == level + 1 ? node : find(node, key, level + 1);
        }
        else{
            return find(key.charAt(level) > node.chr ? node.right : node.left, key, level);
        }
    }

    private class Node<V>{
        private char chr;
        private V value = null;
        private Node<V> left = null;
        private Node<V> middle = null;
        private Node<V> right = null;

        private Node(char chr){
            this.chr = chr;
        }
    }
}
