package anaydis.search;

abstract class TreeMap<K,V> implements Map<K,V>{

    protected V previousValue = null;
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


}
