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
        sort(comparator, list, 0, list.size());
    }

    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list, int l, int r) {
        box(l,r);
        for(int i = l; i<r ; i++){
            for(int j = i; j>l ; j--){
                if(greater(comparator,list,j-1,j)){
                    swap(list,j,j-1);
                }
                else break;
            }
        }
    }

}
