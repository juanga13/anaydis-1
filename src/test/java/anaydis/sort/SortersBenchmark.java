package anaydis.sort;

import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.comparators.IntegerComparator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.listeners.SorterListenerImpl;
import anaydis.sort.provider.SorterProvider;
import anaydis.sort.provider.SorterProviderImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortersBenchmark{

    private final static int N = 10;
    private final static int REPETITION = 200;


    @Test
    public void fullBenchmark() {

        //Hardcoded integer generator
        final DataSetGenerator generator = new IntegerDataSetGenerator();
        final Comparator comparator = new IntegerComparator();
        //

        final SorterProvider sorterProvider = new SorterProviderImpl();
        Iterable<Sorter> sorters = sorterProvider.getAllSorters();
        ArrayList<Result> ascendingResults = new ArrayList<>();
        ArrayList<Result> descendingResults = new ArrayList<>();
        ArrayList<Result> randomResults = new ArrayList<>();
        for (Sorter sorter: sorters) {
            ascendingResults.add(benchmark((AbstractSorter)sorter, generator, comparator, 1, N, REPETITION));
            descendingResults.add(benchmark((AbstractSorter)sorter, generator, comparator, -1, N, REPETITION));
            randomResults.add(benchmark((AbstractSorter)sorter, generator, comparator, 0, N, REPETITION));
        }
        int i = 0;
        for (Sorter sorter: sorters) {
            System.out.println(sorter.getType());
            System.out.println("Ascending results");
            System.out.println(ascendingResults.get(i).toString());
            System.out.println("Descending results");
            System.out.println(descendingResults.get(i).toString());
            System.out.println("Random results");
            System.out.println(randomResults.get(i).toString());
            i++;
        }
    }

    private <T> Result benchmark(@NotNull AbstractSorter sorter, @NotNull DataSetGenerator<T> generator, @NotNull Comparator<T> comparator, int order,int n, int r) {
        final SorterListenerImpl listener = new SorterListenerImpl();
        sorter.addSorterListener(listener);
        double time = 0;
        for(int i = 0; i<r; i++) {
            List<T> list;
            if(order == 0)
                list = generator.createRandom(n);
            else if(order > 0)
                list = generator.createAscending(n);
            else list = generator.createDescending(n);
            time += timedSort(sorter, comparator, list);
        }
        sorter.removeSorterListener(listener);
        return new Result(N, REPETITION,time/ REPETITION,
                listener.getSwaps()/ REPETITION, listener.getGreater()/ REPETITION);
    }

    private <T> double timedSort(@NotNull AbstractSorter sorter, Comparator<T> comparator, @NotNull List<T> list){
        double start = System.nanoTime();
        sorter.sort(comparator, list);
        double end = System.nanoTime();
        double time = (end - start)/1000000;
        return time;
    }

    private class Result{
        private int listSize;
        private int repetitions;
        private double avgTime;
        private double avgSwaps;
        private double avgComparisons;

        public Result(int listSize, int repetitions, double avgTime, double avgSwaps, double avgComparisons) {
            this.listSize = listSize;
            this.repetitions = repetitions;
            this.avgTime = avgTime;
            this.avgSwaps = avgSwaps;
            this.avgComparisons = avgComparisons;
        }

        public String toString(){
            return new StringBuilder()
                    .append("Repetitions: ").append(repetitions).append("\n")
                    .append("List size: ").append(listSize).append("\n")
                    .append("Average time: ").append(avgTime).append(" ms\n")
                    .append("Average swaps: ").append(avgSwaps).append("\n")
                    .append("Average comparisons ").append(avgComparisons).append("\n")
                    .toString();

        }
    }
}

