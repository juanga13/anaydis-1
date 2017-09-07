package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.data.StringDataSetGenerator;
import anaydis.sort.provider.SorterProvider;
import anaydis.sort.provider.SorterProviderImpl;
import org.junit.Test;

/**
 * Sorter tests should subclass this abstract implementation
 */
abstract class SorterTest extends AbstractSorterTest {

    private SorterType sorterType;
    private int n;

    public SorterTest(SorterType sorterType){
        this.sorterType = sorterType;
        n = 100;
    }


    @Override protected DataSetGenerator<String> createStringDataSetGenerator() {
        return new StringDataSetGenerator();
    }

    @Override protected DataSetGenerator<Integer> createIntegerDataSetGenerator() {
        return new IntegerDataSetGenerator();
    }

    @Override
    protected SorterProvider getSorterProvider() {
        return new SorterProviderImpl();
    }

    @Test
    public void testStringSorter(){
        testSorter(new StringDataSetGenerator(), sorterType, n);
    }

    @Test
    public void testIntegerSorter() {
        testSorter(new IntegerDataSetGenerator(), sorterType, n);
    }
}