package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickCutSorter extends AbstractQuickSorter{

    final int m = 10;
    final InsertionSorter insertionSorter = new InsertionSorter();

    QuickCutSorter() {
        super(SorterType.QUICK_CUT);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if(hi - lo <= m){
            insertionSorter.sort(comparator, list);
        }
        if(hi <= lo)return;
        int i = partition(comparator, list, lo, hi);
        sort(comparator, list, lo, i-1);
        sort(comparator, list, i+1, hi);
    }
}
