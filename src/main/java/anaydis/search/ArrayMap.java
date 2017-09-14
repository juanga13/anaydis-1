package anaydis.search;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/*
public class ArrayMap<K,V> implements Map<K,V>{

    private final List<K> keys;
    private final List<V> values;
    private final Comparator<K> comparator;

    public ArrayMap(Comparator<K> comparator, int initialSize) {
        this.keys = new ArrayList<K>(initialSize);
        this.values = new ArrayList<V>(initialSize);
        this.comparator = comparator;

    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean containsKey(@NotNull final K key) {
        return indexOf(key) >= 0;
    }

    @Override
    public V get(@NotNull K key) {
        final int index = indexOf(key);
        return index < 0 ? null : values.get(index);
    }

    @Override
    public V put(@NotNull K key, V value) {
        int index = indexOf(key);
        if(index < 0){
            index = (-index) -1;
            keys.add(null);
            values.add(null);
            for(int i = size()-1; i >= index+1; i--){
                keys.set(i, keys.get(i-1));
                values.set(i, values.get(i-1));
            }
            keys.set(index, key);
            values.set(index, value);
            return null;//key did not exist
        }
        return values.set(index, value);
    }

    @Override
    public void clear() {

    }

    @Override
    public Iterator<K> keys() {
        return null;
    }

    private int indexOf(@NotNull final K key){
        int index = find(key, 0, size()-1);
        return index < 0 ? -1 : index;
    }

    private int find(@NotNull final K key, int lo,  int hi){
        if(lo > hi) return -(lo+1);
        final int mid = (lo + hi)/2;
        int cmp = compa
    }

}
*/
