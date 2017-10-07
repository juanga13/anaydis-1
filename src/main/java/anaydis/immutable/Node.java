package anaydis.immutable;

import org.jetbrains.annotations.NotNull;

import java.util.NoSuchElementException;

public class Node<T> implements List<T>{

    private T head;
    private List<T> tail;

    public Node(T head, List<T> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @NotNull
    @Override
    public List<T> tail() {
        return tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @NotNull
    @Override
    public List<T> reverse() {
        return reverse(new Node<>(head, tail), List.nil());
    }

    private List<T> reverse(List<T> current, List<T> target){
        if(current.isEmpty()) return target;
        return reverse(current.tail(), cons(current.head(), target));
    }

    private static <U> List<U> cons(U head, List<U> tail){
        return List.cons(head, tail);
    }

    static final List<Object> NIL = new List<Object>() {
        @Override
        public Object head() {
            throw new NoSuchElementException();
        }

        @NotNull
        @Override
        public List<Object> tail() {
            throw new NoSuchElementException();
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @NotNull
        @Override
        public List<Object> reverse() {
            return this;
        }
    };
}
