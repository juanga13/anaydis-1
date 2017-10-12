package anaydis.immutable;

import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class BankersQueueTest {

    @Test
    public void testBankersQueue(){
        BankersQueue<Integer> queue = new BankersQueue<>(List.nil(), List.nil());

        ArrayList<Queue<Integer>> queues = new ArrayList<>();

        queues.add(queue.enqueue(1));
        queues.add(queues.get(0).enqueue(2));
        queues.add(queues.get(1).enqueue(3));
        queues.add(queues.get(2).enqueue(4));
        queues.add(queues.get(3).enqueue(5));
        queues.add(queues.get(4).enqueue(6));
        queues.add(queues.get(5).enqueue(7));

        assertThat(queue.isEmpty()).isTrue();

        boolean b = true;
        for (int i = 0; i < queues.size(); i++) {
            if(!dequeue(queues.get(i), i+1, 1)) {
                b = false;
                break;
            }
        }
        assertThat(b).isTrue();
    }

    private boolean dequeue(Queue<Integer> q, int value, int i) {
        if (i > value) return true;
        final Queue.Result<Integer> result = q.dequeue();
        return result.value == i && dequeue(result.queue, value,i +1);
    }
}
