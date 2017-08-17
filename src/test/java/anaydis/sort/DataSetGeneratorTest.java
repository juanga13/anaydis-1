package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;

/**
 * Data set generator test
 */
public class DataSetGeneratorTest extends AbstractDataSetGeneratorTest {

    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        throw new IllegalStateException("To be implemented!");
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        throw new IllegalStateException("To be implemented!");
    }
}
