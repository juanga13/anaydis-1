package anaydis.immutable;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NodeTest {

    @Test
    public void reverseTest(){
        Node<Integer> node = (Node<Integer>) List.cons(3,
                List.cons(2, List.cons(1, List.nil())));

        List<Integer> reverse = node.reverse();
        assertThat(reverse.head()).isEqualTo(1);
        assertThat(reverse.tail().head()).isEqualTo(2);
        assertThat(reverse.tail().tail().head()).isEqualTo(3);
    }
}
