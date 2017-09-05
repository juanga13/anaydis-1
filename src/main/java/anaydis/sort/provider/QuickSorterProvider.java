package anaydis.sort.provider;

import anaydis.sort.*;
import org.jetbrains.annotations.NotNull;

import java.util.EnumMap;
import java.util.Map;

public class QuickSorterProvider implements SorterProvider{

    private final Map<SorterType, Sorter> sorters = new EnumMap<>(SorterType.class);

    public QuickSorterProvider(){
        sorters.put(SorterType.QUICK, new QuickSorter());
        sorters.put(SorterType.QUICK_NON_RECURSIVE, new QuickNonRecursiveSorter());
        sorters.put(SorterType.QUICK_CUT, new QuickCutSorter());
        sorters.put(SorterType.QUICK_MED_OF_THREE, new QuickMedOfThreeSorter());
        sorters.put(SorterType.QUICK_THREE_PARTITION, new QuickThreePartitionSorter());
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
