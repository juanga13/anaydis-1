package anaydis.sort.practice.listeners.comparators;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer integer1, Integer integer2) {
        return integer1.compareTo(integer2);
    }
}
