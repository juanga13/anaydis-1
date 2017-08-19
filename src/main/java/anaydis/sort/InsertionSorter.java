package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class InsertionSorter extends AbstractSorter {

    public InsertionSorter() {
        super(SorterType.INSERTION);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();
        for(int i = 1; i<n ; i++){
            int j = i;
            T aux = list.get(i);
            while (j > 0 && greater(comparator,list,j-1,j)){
                list.set(j-1, list.get(j));
                j--;
            }
            list.set(j, aux);
        }
    }

}
