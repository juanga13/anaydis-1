package anaydis.immutable;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

public class BinaryTreeTest {

    @Test
    public void testPut(){
        BinaryTree<Integer, String> bt = new BinaryTree<>(Integer::compareTo);
        ArrayList<Map<Integer, String>> bts = new ArrayList<>();

        bts.add(bt.put(5, "5"));
        bts.add(bts.get(0).put(3, "3"));
        bts.add(bts.get(1).put(9, "9"));
        bts.add(bts.get(2).put(6, "6"));
        bts.add(bts.get(3).put(5, "50"));
        bts.add(bts.get(4).put(2, "2"));

        System.out.println(bts.get(5).toString());

        assertThat(bts.get(0).get(3)).isNull();
        assertThat(bts.get(5).get(5)).isEqualTo("50");
        assertThat(bts.get(5).get(9)).isEqualTo("9");
        assertThat(bts.get(5).get(3)).isEqualTo("3");
    }

    @Test
    public void testImmutability(){
        BinaryTree<Integer, String> bt = new BinaryTree<>(Integer::compareTo);
        ArrayList<Map<Integer, String>> bts = new ArrayList<>();

        bts.add(bt.put(1, "1"));
        bts.add(bts.get(0).put(2, "2"));
        bts.add(bts.get(1).put(3, "3"));
        bts.add(bts.get(2).put(4, "4"));
        bts.add(bts.get(3).put(5, "5"));
        bts.add(bts.get(4).put(6, "6"));
        bts.add(bts.get(5).put(7, "7"));
        bts.add(bts.get(6).put(8, "8"));
        bts.add(bts.get(7).put(9, "9"));

        assertThat(bt.isEmpty()).isTrue();

        ArrayList<Integer> array = new ArrayList<>();

        boolean b = true;
        for (int i = 0; i < bts.size(); i++) {
            array.add(i+1);
            Iterator<Integer> keys = bts.get(i).keys();
            for (int j = 0; j < array.size(); j++) {
                if (!array.get(j).equals(keys.next())) {
                    b = false;
                    break;
                }
            }
        }
        assertThat(b).isTrue();
    }
}
