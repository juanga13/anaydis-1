package anaydis.immutable;

import org.jetbrains.annotations.NotNull;


public class BankersQueue<T> implements Queue<T>{

    private List<T> front = List.nil();
    private List<T> rear = List.nil();

    public BankersQueue(@NotNull List<T> in, @NotNull List<T> out) {
        this.front = in;
        this.rear = out;
    }

    @NotNull
    @Override
    public Queue<T> enqueue(@NotNull T value) {
        return new BankersQueue<>(List.cons(value, front), rear);
    }

    @NotNull
    @Override
    public Result<T> dequeue() {
        if(front.isEmpty() && !rear.isEmpty()) {
            List<T> rFront = rear.reverse();
            List<T> rRear = List.nil();
            return new Result<>(rFront.head(), new BankersQueue<>(rFront.tail(), rRear));
        }
        return new Result<>(front.head(), new BankersQueue<>(front.tail(), rear));
    }

    @Override
    public boolean isEmpty() {
        return front.isEmpty() && rear.isEmpty();
    }
}
