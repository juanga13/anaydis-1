package anaydis.sort;

import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Stack;

public class QuickNonRecursiveSorter extends AbstractQuickSorter{
    public QuickNonRecursiveSorter() {
        super(SorterType.QUICK_NON_RECURSIVE);
    }

    @Override
    public <T> void sort(@NotNull Comparator<T> comparator, @NotNull List<T> list) {
        Stack<Integer> s = new Stack<>();
        int l = 0;
        int r = list.size()-1;
        s.push(l);
        s.push(r);
        while (!s.empty()) {
            r = s.pop();
            l = s.pop();
            if (r <= l) {
                continue;
            }
            int i = partition(comparator, list, l, r);
            final boolean condition = i - l > r - i;
            if (condition) {
                s.push(l);
                s.push(i - 1);
            }
            s.push(i + 1);
            s.push(r);
            if (!condition) {
                s.push(l);
                s.push(i - 1);
            }
        }
    }
}
