package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;

public class SelectionSorter extends  AbstractSorter{

    public SelectionSorter() {
        super(SorterType.SELECTION);
    }


    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        final int n = list.size();
        for(int i = 0; i<n; i++){
            int min = i;
            boolean changed = false;
            for(int j = i+1; j<n; j++){
                if(greater(comparator,list,min,j)) {
                    min = j;
                    changed = true;
                }
            }
            if(changed)
                swap(list,i, min);
        }
    }
}
