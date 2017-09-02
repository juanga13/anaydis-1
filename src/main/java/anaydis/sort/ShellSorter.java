package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class ShellSorter extends AbstractSorter{

    private final HSorter hSorter;

    public ShellSorter() {
        super(SorterType.SHELL);
        hSorter = new HSorter();
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();
        int h;
        for (h = 1; h <= (n)/9; h = 3*h+1);
        for ( ; h > 0; h /= 3) {
            hSorter.sort(comparator, list, h);
        }
    }


    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, @NotNull int hset[]){
        for(int i = 0; i < hset.length; i++)
            hSorter.sort(comparator, list, hset[i]);
    }


    @Override
    public void addSorterListener(@NotNull SorterListener sorterListener) {
        hSorter.addSorterListener(sorterListener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener sorterListener) {
        hSorter.removeSorterListener(sorterListener);
    }



}
