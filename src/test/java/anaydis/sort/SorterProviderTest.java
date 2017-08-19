package anaydis.sort;

import anaydis.sort.provider.SorterProvider;
import anaydis.sort.provider.SorterProviderImpl;

/**
 * Sorter provider test
 */
public class SorterProviderTest extends AbstractSorterProviderTest {

    @Override protected SorterProvider createSorterProvider() {
        return new SorterProviderImpl();
    }
}
