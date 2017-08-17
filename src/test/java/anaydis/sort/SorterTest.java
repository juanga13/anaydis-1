package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;

/**
 * Sorter tests should subclass this abstract implementation
 */
abstract class SorterTest extends AbstractSorterTest {

    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        throw new IllegalStateException("To be implemented!");
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        throw new IllegalStateException("To be implemented!");
    }
}
