package anaydis.sort.provider;

import anaydis.sort.Sorter;
import anaydis.sort.SorterType;
import org.jetbrains.annotations.NotNull;

public class SorterProviderImpl implements SorterProvider{
    @NotNull
    @Override
    public Iterable<Sorter> getAllSorters() {
        return null;
    }

    @NotNull
    @Override
    public Sorter getSorterForType(@NotNull SorterType type) {
        return null;
    }
}
