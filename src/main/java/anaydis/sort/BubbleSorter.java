package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class BubbleSorter extends AbstractSorter{


    public BubbleSorter() {
        super(SorterType.BUBBLE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();
        boolean stop = false;
        for(int i = 0; i < n-1 && !stop; i++){
            stop = true;
            for(int j = n-1; j > i; j--){
                if(greater(comparator, list,j-1, j)) {
                    swap(list, j, j - 1);
                    stop = false;
                }
            }
        }
    }

}
