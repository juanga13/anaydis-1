package anaydis.sort.provider;

import anaydis.sort.*;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class SorterProviderImpl implements SorterProvider{

    private final Map<SorterType, Sorter> sorters = new EnumMap<>(SorterType.class);

    public SorterProviderImpl(){
        sorters.put(SorterType.BUBBLE, new BubbleSorter());
        sorters.put(SorterType.INSERTION, new InsertionSorter());
        sorters.put(SorterType.SELECTION, new SelectionSorter());
        sorters.put(SorterType.H, new HSorter());
        sorters.put(SorterType.SHELL, new ShellSorter());
        sorters.put(SorterType.QUICK, new QuickSorter());
        sorters.put(SorterType.QUICK_NON_RECURSIVE, new QuickNonRecursiveSorter());
        sorters.put(SorterType.QUICK_CUT, new QuickCutSorter());
        sorters.put(SorterType.QUICK_MED_OF_THREE, new QuickMedOfThreeSorter());
        sorters.put(SorterType.QUICK_THREE_PARTITION, new QuickThreePartitionSorter());
        sorters.put(SorterType.MERGE, new MergeSort());
        sorters.put(SorterType.MERGE_BOTTOM_UP, new MergeBUSort());
    }
    
    @NotNull
    @Override
    public Iterable<Sorter> getAllSorters() {
        return sorters.values();
    }

    @NotNull
    @Override
    public Sorter getSorterForType(@NotNull SorterType type) {
        if(!sorters.containsKey(type))
            throw new IllegalArgumentException();
        return sorters.get(type);
    }
}
