package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class MergeSort extends AbstractMergeSorter{

    public MergeSort() {
        super(SorterType.MERGE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if(hi <= lo) return;
        int mid = (lo + hi)/2;
        sort(comparator, list, lo, mid);
        sort(comparator, list, mid+1, hi);
        merge(comparator, list, lo, mid, hi);
    }

}
