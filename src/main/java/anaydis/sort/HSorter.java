package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class HSorter extends AbstractSorter{
    public HSorter() {
        super(SorterType.H);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        sort(comparator, list, 1);
    }

    //Insertion sort implementation.
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int h) {
        final int n = list.size();
        for (int i = h; i < n; i++) {
            int j = i;
            while (j >= h && greater(comparator, list, j-h, j)) {
                swap(list,j,j-h);
                j -= h;
            }
        }
    }
}
