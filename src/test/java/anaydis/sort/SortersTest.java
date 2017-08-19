package anaydis.sort;

import anaydis.sort.comparators.StringComparator;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerComparator;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SortersTest extends SorterTest {



    @Test
    public void testBubbleSorter(){
        BubbleSorter bubbleSorter = new BubbleSorter();
        testStringSorter(bubbleSorter);
        testIntegerSorter(bubbleSorter);
    }

    @Test
    public void testSelectionSorter(){
        SelectionSorter selectionSorter = new SelectionSorter();
        testStringSorter(selectionSorter);
        testIntegerSorter(selectionSorter);
    }

    @Test
    public void testInsertionSorter(){
        InsertionSorter insertionSorter = new InsertionSorter();
        testIntegerSorter(insertionSorter);
        testStringSorter(insertionSorter);
    }

    private void testStringSorter(AbstractSorter sorter){
        DataSetGenerator<String> stringDataSetGenerator = createStringDataSetGenerator();
        List<String> list = stringDataSetGenerator.createRandom(100);
        Comparator<String> comparator = new StringComparator();
        sorter.sort(comparator, list);
        assertThat(isSorted(list, comparator)).isTrue();
    }

    private void testIntegerSorter(AbstractSorter sorter){
        DataSetGenerator<Integer> integerDataSetGenerator = createIntegerDataSetGenerator();
        List<Integer> list = integerDataSetGenerator.createRandom(100);
        Comparator<Integer> comparator = new IntegerComparator();
        sorter.sort(comparator, list);
        assertThat(isSorted(list, comparator)).isTrue();
    }

    private <T> boolean isSorted(List<T> list, Comparator<T> comparator){
        boolean result = true;
        for (int i = 0; i < list.size()-1; i++) {
            if(comparator.compare(list.get(i),list.get(i+1)) > 0){
                result = false;
                break;
            }
        }
        return result;
    }


}
