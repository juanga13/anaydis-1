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
        Stack<Integer> S = new Stack<>();
        int l = 0;
        int r = list.size()-1;
        S.push(l);
        S.push(r);
        while (!S.empty()) {
            r = S.pop();
            l = S.pop();
            if (r <= l) {
                continue;
            }
            int i = partition(comparator, list, l, r);
            if (i - l > r - i) {
                S.push(l);
                S.push(i - 1);
            }
            S.push(i + 1);
            S.push(r);
            if (r-i >= i-l) {
                S.push(l);
                S.push(i - 1);
            }
        }
    }
}
