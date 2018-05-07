package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BinaryTrieMap<V> extends TreeMap<String,V> {

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
        head = put(head, new Node<>(key, value), 0);
        return previousValue;
    }

    private Node<V> put(Node<V> node, Node<V> value, int level) {
        if(node == null){
            size++;
            previousValue = null;
            return value;
        }
        if(node.isLeaf()){
            if(node.key.equals(value.key)){
                previousValue = node.value;
                node.value = value.value;
                return node;
            }
            else{
                previousValue = null;
                size++;
                return split(value, node, level);
            }
        }
        else {
            if(bitAt(value.key, level)) node.right = put(node.right, value, level+1);
            else node.left = put(node.left, value, level+1);
            return node;
        }
    }

    private boolean bitAt(String s, int nth) {
        int pos = nth/8;
        return pos < s.length() && ((int) s.charAt(pos) >> 7-(nth % 8) & 1) != 0;
    }

    private Node<V> split(Node<V> a, Node<V> b, int level) {
        Node<V> result = new Node<>("", null);
        switch ((bitAt(a.key, level) ? 1 : 0) * 2 + (bitAt(b.key, level) ? 1 : 0)){
            case 0: result.left = split(a, b, level+1); break;
            case 3: result.right = split(a, b, level+1); break;
            case 1: result.left = a;
                    result.right = b;
                    break;
            case 2: result.right = a;
                    result.left = b;
                    break;
        }
        return result;
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
        String nodeKey = currentKey + node.key;
        keysInOrder(node.left, nodeKey, keys);
        if(node.value != null) keys.add(nodeKey);
        keysInOrder(node.right, nodeKey, keys);
    }

    private Node<V> find(Node<V> node, @NotNull String key, int level){
        if (node == null) return null;
        if (node.isLeaf()) return key.equals(node.key) ? node : null;
        return find(bitAt(key, level) ? node.right : node.left, key, level + 1);
    }

    private class Node<V>{
        private String key;
        private V value = null;
        private Node<V> left;
        private Node<V> right;

        private Node(String key, V value){
            this.key = key;
            this.value = value;
        }

        private boolean isLeaf(){
            return left == null && right == null;
        }
    }
}
