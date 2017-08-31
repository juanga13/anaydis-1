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
        sorters.put(SorterType.SHELL, new ShellSorter());
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
