package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements ObservableSorter, Sorter {

    private final SorterType type;
    private final List<SorterListener> listeners;

    AbstractSorter(SorterType type) {
        this.type = type;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addSorterListener(@NotNull SorterListener sorterListener) {
        listeners.add(sorterListener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener sorterListener) {
        listeners.remove(sorterListener);
    }

    @NotNull
    @Override
    public final SorterType getType() {
        return type;
    }

    protected <T> boolean greater(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i, int j){
        for (SorterListener listener: listeners) {
            listener.greater(i,j);
        }
        return comparator.compare(list.get(i), list.get(j)) > 0;
    }

    protected <T> boolean equals(@NotNull Comparator<T> comparator, @NotNull List<T> list, int i, int j){
        listeners.forEach(l -> l.equals(i,j));
        return comparator.compare(list.get(i),list.get(j)) == 0;
    }

    protected <T> void swap(@NotNull List<T> list, int i, int j) {
        listeners.forEach(l -> l.swap(i,j));
        T aux = list.get(i);
        list.set(i, list.get(j));
        list.set(j, aux);
    }

    protected void box(int from, int to) {
        listeners.forEach(l -> l.box(from,to));
    }

    protected <T> void copy(List<T> list, int from, List<T> aux, int to, boolean copyToAux){
        aux.set(to, list.get(from));
        listeners.forEach(l -> l.copy(from, to, copyToAux));
    }


    protected <T> void add(List<T> list, int from, List<T> aux, boolean addToAux){
        listeners.forEach(l -> l.copy(from, list.size()-1,addToAux));
        aux.add(list.get(from));
    }
}