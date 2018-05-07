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
            else
                node.middle = put(node.middle, key, value, level+1);
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
            return key.length() == level + 1 ? node : find(node.middle, key, level + 1);
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


    public ArrayList<String> wildcard(String pattern){
        ArrayList<String> collector = new ArrayList<>();
        wildcard(head, pattern, collector, 0, "");
        return collector;
    }

    public void dotWildcard(Node<V> node, String pattern, List<String> collector, int level, String currentKey){
        if(node == null || level == pattern.length()) return;
        char c = pattern.charAt(level);
        if(node.chr > c || c == '.')
            dotWildcard(node.left, pattern, collector, level, currentKey);
        if(node.chr == c || c == '.') {
            String newKey = currentKey + node.chr;
            if(level + 1 == pattern.length() && node.value != null)
                collector.add(newKey);
            else
                dotWildcard(node.middle, pattern, collector, level + 1, newKey);
        }
        if(node.chr < c || c == '.')
            dotWildcard(node.right, pattern, collector, level, currentKey);
    }

    /*
    public void wildcard(Node<V> node, String pattern, List<String> collector, int level, String currentKey) {
        if (node == null || level == pattern.length()) return;
        char c = pattern.charAt(level);
        String newKey = currentKey + node.chr;
        if (c == '*') {
            if(pattern.length() > level+1) {
                char nextC = pattern.charAt(level + 1);
                if (node.chr == nextC) {
                    if (level + 2 == pattern.length() && node.value != null)
                        collector.add(newKey);
                    else
                        //wildcard(node.middle, pattern, collector, level, newKey);
                        wildcard(node.middle, pattern, collector, level + 2, newKey);
                    return;
                }
            }
            wildcard(node.left, pattern, collector, level, currentKey);
            if (node.value != null)
                collector.add(newKey);
            else
                wildcard(node.middle, pattern, collector, level, newKey);
            wildcard(node.right, pattern, collector, level, currentKey);

        } else {
            if(c < node.chr)
                wildcard(node.left, pattern, collector, level, currentKey);
            else if(node.chr == c) {
                if(level + 1 == pattern.length() && node.value != null)
                    collector.add(newKey);
                else
                    wildcard(node.middle, pattern, collector, level + 1, newKey);
            }
            else if(c > node.chr)
                wildcard(node.right, pattern, collector, level, currentKey);
        }
    }
    */

    public void wildcard(Node<V> node, String pattern, List<String> collector, int level, String currentKey){
        if(node == null || level == pattern.length()) return;
        char c = pattern.charAt(level);
        boolean token = c == '*';
        int cmp = Integer.compare(c, node.chr);

        if (token && pattern.length() > level + 1) {
            char nextC = pattern.charAt(level + 1);
            if (node.chr == nextC) {
               level++;
               cmp = 0;
               token = false;
            }
        }

        if(cmp < 0 || token)
            wildcard(node.left, pattern, collector, level, currentKey);
        if(cmp == 0 || token) {
            String newKey = currentKey + node.chr;
            if(level + 1 == pattern.length() && node.value != null)
                collector.add(newKey);
            else
                wildcard(node.middle, pattern, collector, token ? level : level + 1, newKey);
        }
        if(cmp > 0 || token)
            wildcard(node.right, pattern, collector, level, currentKey);
    }
}
