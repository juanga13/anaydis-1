package anaydis.search;

import anaydis.sort.comparators.IntegerComparator;
import org.assertj.core.util.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ArrayMapTest {


    @Parameterized.Parameter public Map<Integer, String> map;

    @Parameterized.Parameters(name = "Map {0}")
    public static List<Object[]> maps(){
        return Arrays.stream(Iterables.toArray(getAllMaps()))
                .map(map -> new Object[]{map})
                .collect(Collectors.toList());
    }

    private static Iterable<Map<Integer, String>> getAllMaps(){
        Comparator<Integer> comparator = new IntegerComparator();
        List<Map<Integer, String>> maps = new ArrayList<>();

        maps.add(new ArrayMap<>(comparator));
        maps.add(new RandomizedTree<>(comparator));

        return maps;
    }

    @Test
    public void containsTest(){
        //Map<Integer,String> map = new ArrayMap<>(new IntegerComparator(), 10);

        assertThat(map.size()).isEqualTo(0);

        map.put(1, "value1");
        map.put(8, "value8");
        map.put(2, "value2");
        map.put(4, "value4");
        map.put(0, "value0");
        map.put(5, "value5");
        map.put(3, "value3");
        map.put(9, "value9");
        map.put(7, "value7");
        map.put(6, "value6");

        assertThat(map.size()).isEqualTo(10);
    }


    @Test
    public void getTest(){
        //Map<Integer,String> map = new ArrayMap<>(new IntegerComparator(), 10);

        map.put(1, "value1");
        map.put(8, "value8");
        map.put(2, "value2");
        map.put(0, "value0");
        map.put(4, "value4");
        map.put(5, "value5");
        map.put(3, "value3");
        map.put(9, "value9");

        assertThat(map.get(0)).isEqualTo("value0");
        assertThat(map.get(5)).isEqualTo("value5");

        map.put(1, "newvalue1");
        map.put(5, "newvalue5");
        map.put(9, "newvalue9");

        assertThat(map.get(1)).isEqualTo("newvalue1");
        assertThat(map.get(5)).isEqualTo("newvalue5");
    }


    @Test
    public void clearTest(){
        //Map<Integer,String> map = new ArrayMap<>(new IntegerComparator(), 10);

        map.put(1, "value1");
        map.put(8, "value8");
        map.put(2, "value2");
        map.put(0, "value0");
        map.put(4, "value4");
        map.put(5, "value5");
        map.put(3, "value3");
        map.put(9, "value9");

        map.clear();
        assertThat(map.isEmpty()).isTrue();
    }
}
