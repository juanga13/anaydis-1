package anaydis.search;

import org.assertj.core.util.Iterables;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(Parameterized.class)
public class AllMapsTest {


    @Parameterized.Parameter public Map<String, Integer> map;

    @Parameterized.Parameters(name = "Map {0}")
    public static List<Object[]> maps(){
        return Arrays.stream(Iterables.toArray(getAllMaps()))
                .map(map -> new Object[]{map})
                .collect(Collectors.toList());
    }

    private static Iterable<Map<String, Integer>> getAllMaps(){
        Comparator<String> comparator = String::compareTo;
        List<Map<String, Integer>> maps = new ArrayList<>();

        maps.add(new ArrayMap<>(comparator));
        maps.add(new RandomizedTreeMap<>(comparator));
        maps.add(new RWayTrieMap<>());
        maps.add(new TSTMap<>());

        return maps;
    }

    @Test
    public void containsTest(){
        //Map<Integer,String> map = new ArrayMap<>(new IntegerComparator(), 10);

        assertThat(map.size()).isEqualTo(0);

        map.put("1", 1);
        map.put("8", 8);
        map.put("2", 2);
        map.put("4", 4);
        map.put("0", 0);
        map.put("5", 5);
        map.put("3", 3);
        map.put("9", 9);
        map.put("7", 7);
        map.put("6", 6);

        assertThat(map.size()).isEqualTo(10);
    }

    @Test
    public void getTest(){
        //Map<String, Integer> map = new BinaryTrieMap<>();

        map.put("1", 1);
        map.put("8", 8);
        map.put("2", 2);
        map.put("4", 4);
        map.put("0", 0);
        map.put("5", 5);
        map.put("3", 3);
        map.put("9", 9);

        assertThat(map.get("0")).isEqualTo(0);
        assertThat(map.get("5")).isEqualTo(5);

        map.put("1", 10);
        map.put("5", 50);
        map.put("9", 90);

        assertThat(map.get("1")).isEqualTo(10);
        assertThat(map.get("5")).isEqualTo(50);
    }


    @Test
    public void clearTest(){

        map.put("1", 1);
        map.put("8", 8);
        map.put("2", 2);
        map.put("4", 4);
        map.put("0", 0);
        map.put("5", 5);
        map.put("3", 3);
        map.put("9", 9);
        map.put("7", 7);
        map.put("6", 6);

        map.clear();
        assertThat(map.isEmpty()).isTrue();
    }

    @Test
    public void keysTest(){
        ArrayList<String> keys = new ArrayList<>();
        for(int i=0; i < 10; i++) keys.add("" + i);
        Iterator<String> keysI = keys.iterator();

        map.put("a", 1);
        map.put("b", 8);
        map.put("c", 2);
        map.put("d", 4);
        map.put("e", 0);
        map.put("f", 5);
        map.put("g", 3);
        map.put("h", 9);
        map.put("i", 7);
        map.put("j", 6);

        Iterator<String> mapKeys = map.keys();

        boolean b = true;
        while(keysI.hasNext()){
            if(!mapKeys.next().equals(keysI.next())){
                b = false;
                break;
            }
        }
        assertThat(b).isTrue();
    }
}
