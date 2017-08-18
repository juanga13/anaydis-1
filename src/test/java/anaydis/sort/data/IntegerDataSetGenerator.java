package anaydis.sort.data;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class IntegerDataSetGenerator implements DataSetGenerator<Integer> {
    @NotNull
    @Override
    public List<Integer> createAscending(int length) {
        final List<Integer> result = new ArrayList<>(length);
        for(int i = 0; i < length; i++){
            result.add(i);
        }
        return result;
    }

    @NotNull
    @Override
    public List<Integer> createDescending(int length) {
        final List<Integer> result = new ArrayList<>(length);
        for(int i = length; i > 0; i--){
            result.add(i);
        }
        return result;
    }

    @NotNull
    @Override
    public List<Integer> createRandom(int length) {
        final Random random = new Random();
        final List<Integer> result = new ArrayList<>(length);
        for(int i = 0; i < length; i++){
            result.add(random.nextInt());
        }
        return result;
    }

    @NotNull
    @Override
    public Comparator<Integer> getComparator() {
        return Integer::compareTo;
    }
}
