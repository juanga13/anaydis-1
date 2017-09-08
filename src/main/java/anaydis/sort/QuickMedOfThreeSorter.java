package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickMedOfThreeSorter extends AbstractHybridQuickSorter{

    final private static int M = 10;

    public QuickMedOfThreeSorter() {
        super(SorterType.QUICK_MED_OF_THREE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
        cut.sort(comparator, list);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if(hi - lo <= M) return;
        swap(list, (lo+hi)/2, hi-1);
        compareAndExchange(comparator, list, lo, hi-1);
        compareAndExchange(comparator, list, lo, hi);
        compareAndExchange(comparator, list, hi-1, hi);
        int i = partition(comparator, list, lo, hi);
        sort(comparator, list, lo, i-1);
        sort(comparator, list, i+1, hi);
    }

    private <T> void compareAndExchange(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i1, int i2){
        if(greater(comparator, list, i1, i2)) swap(list, i1, i2);
    }
}
