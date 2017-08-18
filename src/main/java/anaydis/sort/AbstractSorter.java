package anaydis.sort;

import anaydis.sort.gui.ObservableSorter;
import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract sorter: all sorter implementations should subclass this class.
 */
abstract class AbstractSorter implements ObservableSorter, Sorter {

    private final SorterType type;
    private final List<SorterListener> listeners;

    public AbstractSorter(SorterType type) {
        this.type = type;
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addSorterListener(@NotNull SorterListener sorterListener) {

    }

    @Override
    public void removeSorterListener(@NotNull SorterListener sorterListener) {

    }

    @NotNull
    @Override
    public SorterType getType() {
        return type;
    }
}
