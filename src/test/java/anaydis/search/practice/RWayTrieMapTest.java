package anaydis.search.practice;

import anaydis.search.RWayTrieMap;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RWayTrieMapTest {

    @Test
    public void containsTest(){
        RWayTrieMap<Integer> map = new RWayTrieMap<>();

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
        RWayTrieMap<Integer> map = new RWayTrieMap<>();

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
        RWayTrieMap<Integer> map = new RWayTrieMap<>();

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
}
