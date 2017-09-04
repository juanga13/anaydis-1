package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

abstract class AbstractQuickSorter extends AbstractSorter{

    AbstractQuickSorter(SorterType type) {
        super(type);
    }

    protected <T> int partition(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi){
        int i = lo;
        int j = hi-1;

        while(true) {
            while(!greater(comparator, list, i, hi) && i != hi) //find item left to swap, ! because lesser or equals
                i++;
            while(!greater(comparator, list, hi, j) && j != lo) //find item right to swap
                j--;
            if (i >= j) break; //check if pointers cross
            swap(list, i, j);
        }
        swap(list, i, hi);//swap with partitioning item
        return i; //return index of item now known to be in place
    }
}
