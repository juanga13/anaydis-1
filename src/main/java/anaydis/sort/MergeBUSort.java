package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeBUSort extends AbstractMergeSorter{

    public MergeBUSort() {
        super(SorterType.MERGE_BOTTOM_UP);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }


    protected  <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        for(int mid = 1; mid <= hi-lo; mid *= 2){
            final int midX2 = mid * 2;
            for(int lo1 = lo; lo1 <= hi - mid; lo1 += midX2){
                final int hi1 = Math.min(lo1 + midX2 - 1, hi);
                merge(comparator, list, lo1, lo1 + mid - 1, hi1);
            }
        }
    }

}
