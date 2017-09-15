package anaydis.sort.practice;

import anaydis.sort.ShellSorter;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.practice.listeners.CounterListener;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ShellSorterSequenceTester {

    final static int RUNS = 200;

    private enum Schema {
        ONE(100),
        TWO(1000),
        THREE(10000);

        int size;

        Schema(int size) {
            this.size = size;
        }
    }

    private enum Set{
        ONE{
            @Override
            public int[] createSet(){
                final int[] set = {16577,4193,1073,281,77,23,8,1};
                return set;
            }
        },
        TWO{
            @Override
            public int[] createSet(){
                final int[] set = {9841,3280,1093,364,121,40,13,4,1};
                return set;
            }
        };


        abstract int[] createSet();
    }

    public static void main(String[] args) {
        ShellSorterSequenceTester s = new ShellSorterSequenceTester();
        s.Benchmark();
    }

    private void Benchmark(){
        ShellSorter sorter = new ShellSorter();
        DataSetGenerator<Integer> generator = new IntegerDataSetGenerator();


        final Map<Schema, Map<Set, Result>> results = new EnumMap<>(Schema.class);

        for (Schema schema : Schema.values()) {

            results.put(schema,run(sorter, generator, schema));
        }

        for (Map.Entry<Schema, Map<Set,Result>> kv: results.entrySet()) {
            System.out.println("Schema: " + kv.getKey().size);
            for (Map.Entry<Set, Result> es: kv.getValue().entrySet()) {
                System.out.println("Set: " + es.getKey().toString() + "\n" + es.getValue().toString());
            }
        }

    }

    private <T> Map<Set,Result> run(final ShellSorter sorter, final DataSetGenerator<T> generator, Schema schema) {

        final Map<Set, Result> results = new EnumMap<>(Set.class);

        final CounterListener listener = new CounterListener();
        addListener(sorter, listener);
        final List<T> datum = generator.createRandom(schema.size);

        for (Set set: Set.values()) {
            final Result result = new Result(RUNS);
            for (int i = 0; i < RUNS; i++) {
                final List<T> copy = new ArrayList<>(datum);
                listener.start();
                sorter.sort(generator.getComparator(), copy, set.createSet());
                listener.stop();
                result.addResult(listener.getElapsedTime(),listener.getAmtOfSwaps(),listener.getAmtOfComparisons());
                listener.reset();
            }
            results.put(set, result);
        }
        removeListener(sorter, listener);
        return results;
    }

    private void removeListener(ShellSorter sorter, CounterListener listener) {
            sorter.removeSorterListener(listener);
        }

    private void addListener(ShellSorter sorter, CounterListener listener) {
            sorter.addSorterListener(listener);
    }

    private class Result {
        int repetitions;
        double sumTime;
        double sumSwaps;
        double sumComparisons;

        private Result(int repetitions){
            this.repetitions = repetitions;
            sumTime = 0;
            sumSwaps = 0;
            sumComparisons = 0;
        }

        private void addResult(double time, double swaps, double comparisons){
            sumTime += time;
            sumSwaps += swaps;
            sumComparisons += comparisons;
        }

        public String toString() {
            return new StringBuilder()
                    .append("Repetitions: ").append(repetitions).append("\n")
                    .append("Average time: ").append((sumTime/repetitions)/1000000).append(" ms\n")
                    .append("Average swaps: ").append(sumSwaps/repetitions).append("\n")
                    .append("Average comparisons ").append(sumComparisons/repetitions).append("\n")
                    .toString();
        }
    }

}
