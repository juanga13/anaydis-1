package anaydis.sort.listeners;

import anaydis.sort.gui.SorterListener;

public class CounterListener implements SorterListener {

    //Counters
    private int amtOfSwaps;
    private int amtOfGreater;
    private int amtOfEquals;
    private int amtOfCopy;
    private long startTime;
    private long endTime;

    public CounterListener() {
        reset();
    }

    @Override
    public void box(int i, int i1) {

    }

    @Override
    public void copy(int i, int i1, boolean b) {
        amtOfCopy++;
    }

    @Override
    public void equals(int i, int i1) {
        //System.out.println("Compared: [" + i + "] amtOfEquals [" + i1 + "]");
        amtOfEquals++;
    }

    @Override
    public void greater(int i, int i1) {
        //System.out.println("Compared: [" + i + "] > [" + i1 + "]");
        amtOfGreater++;
    }

    @Override
    public void swap(int i, int i1) {
        //System.out.println("Swapped: [" + i + "] with [" + i1 + "]");
        amtOfSwaps++;
    }

    public long getElapsedTime(){
        return endTime - startTime;
    }

    public double getElapsedTimeInMillis(){
        return (endTime - startTime)/1000000;
    }

    public int getAmtOfSwaps() {
        return amtOfSwaps;
    }

    public int getAmtOfComparisons() {
        return amtOfGreater;
    }

    public int getAmtOfEquals() {
        return amtOfEquals;
    }

    public int getAmtOfCopy() {
        return amtOfCopy;
    }

    public void start(){
        startTime = System.nanoTime();
    }

    public void stop() {
        endTime = System.nanoTime();
    }

    public void reset() {
        amtOfSwaps = 0;
        amtOfGreater = 0;
        amtOfEquals = 0;
        amtOfCopy = 0;
        startTime = 0;
        endTime = 0;
    }
}
