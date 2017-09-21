package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorter extends AbstractHybridQuickSorter{

    public QuickCutSorter() {
        super(SorterType.QUICK_CUT);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1, 5);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int m) {
        sort(comparator, list, 0, list.size()-1, M);
    }


    private <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi, int m) {
        if(hi - lo <= m) {
            cut.sort(comparator,list,lo,hi);
            return;
        }
        int i = partition(comparator, list, lo, hi);
        sort(comparator, list, lo, i-1, m);
        sort(comparator, list, i+1, hi, m);
    }

}
