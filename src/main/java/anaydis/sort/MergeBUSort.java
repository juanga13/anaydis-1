package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class MergeBUSort extends AbstractSorter{
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
                final int hi1 = Math.min(lo1 - lo + midX2 - 1, hi);
                merge(comparator, list, lo1, lo1 + mid - 1, hi1);
            }
        }
    }

    private <T> void merge(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int mid, int hi){
        final List<T> auxList = new ArrayList<>();
        for(int i = lo; i <= mid; i++)
            add(list, i, auxList, true);
        for(int j = hi; j >= mid+1 ; j--)
            add(list, j,auxList, true);
        //for(int k = lo, i = lo, j = hi; k <= hi; k++){
        for(int k = lo, i = 0, j = auxList.size()-1; k <= hi; k++){
            if(greater(comparator, auxList,i, j))
                copy(auxList, j--, list, k, false);
            else
                copy(auxList, i++, list, k, false);
        }

    }
}
