package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class QuickThreePartitionSorter extends AbstractQuickSorter{
    public QuickThreePartitionSorter() {
        super(SorterType.QUICK_THREE_PARTITION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 0, list.size()-1);
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int hi) {
        if(hi <= lo)return;

        int lt = lo;//lt is the index of the left-most equal pivot
        int gt = hi;//gt is the index of the right-most equal pivot
        T v = list.get(lo);
        int i = lo;
        while(i <= gt){
            int cmp = comparator.compare(list.get(i), v);// use greater
            if(cmp < 0)
                swap(list, lt++, i++);
            else if (cmp > 0)
                swap(list, i, gt--);
            else i++;
        }

        sort(comparator, list, lo, lt-1);
        sort(comparator, list, gt+1, hi);
    }

}
