package anaydis.sort;

import anaydis.sort.gui.SorterListener;
import org.jetbrains.annotations.NotNull;

abstract class AbstractHybridQuickSorter extends AbstractQuickSorter{
    protected final static int M = 10;
    protected final InsertionSorter cut;

    protected AbstractHybridQuickSorter(SorterType type) {
        super(type);
        cut = new InsertionSorter();
    }

    @Override
    public void addSorterListener(@NotNull SorterListener sorterListener) {
        super.addSorterListener(sorterListener);
        cut.addSorterListener(sorterListener);
    }

    @Override
    public void removeSorterListener(@NotNull SorterListener sorterListener) {
        super.removeSorterListener(sorterListener);
        cut.removeSorterListener(sorterListener);
    }
}
