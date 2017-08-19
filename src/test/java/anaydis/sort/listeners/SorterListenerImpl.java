package anaydis.sort.listeners;

import anaydis.sort.gui.SorterListener;

public class SorterListenerImpl implements SorterListener {

    //Counters
    private int swaps;
    private int greater;
    private int equals;
    private int copy;

    public SorterListenerImpl() {
        swaps = 0;
        greater = 0;
        equals = 0;
        copy = 0;
    }

    @Override
    public void box(int i, int i1) {

    }

    @Override
    public void copy(int i, int i1, boolean b) {
        copy++;
    }

    @Override
    public void equals(int i, int i1) {
        //System.out.println("Compared: [" + i + "] equals [" + i1 + "]");
        equals++;
    }

    @Override
    public void greater(int i, int i1) {
        //System.out.println("Compared: [" + i + "] > [" + i1 + "]");
        greater++;
    }

    @Override
    public void swap(int i, int i1) {
        //System.out.println("Swapped: [" + i + "] with [" + i1 + "]");
        swaps++;
    }

    public int getSwaps() {
        return swaps;
    }

    public int getGreater() {
        return greater;
    }

    public int getEquals() {
        return equals;
    }

    public int getCopy() {
        return copy;
    }
}
