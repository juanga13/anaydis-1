package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class ShellSorter extends AbstractSorter{

    public ShellSorter() {
        super(SorterType.SHELL);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final HSorter hSorter = new HSorter();
        final int n = list.size();
        int h;
        for (h = 1; h <= (n)/9; h = 3*h+1);
        for ( ; h > 0; h /= 3) {
            hSorter.sort(comparator, list, h);
        }
    }
}
