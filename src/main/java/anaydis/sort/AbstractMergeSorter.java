package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

abstract class AbstractMergeSorter extends AbstractSorter{
    protected AbstractMergeSorter(SorterType type) {
        super(type);
    }

    protected <T> void merge(@NotNull Comparator<T> comparator, @NotNull List<T> list, int lo, int mid, int hi){
        final List<T> auxList = new ArrayList<>();
        for(int i = lo; i <= mid; i++)
            add(list, i, auxList, true);
        for(int j = hi; j >= mid+1 ; j--)
            add(list, j,auxList, true);
        for(int k = lo, i = 0, j = auxList.size()-1; k <= hi; k++){
            if(greater(comparator, auxList,i, j))
                copy(auxList, j--, list, k, false);
            else
                copy(auxList, i++, list, k, false);
        }

    }
}
