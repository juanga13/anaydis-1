package anaydis.sort.practice;

import anaydis.sort.QuickCutSorter;
import anaydis.sort.data.DataSetGenerator;
import anaydis.sort.data.IntegerDataSetGenerator;
import anaydis.sort.practice.listeners.CounterListener;

import java.util.*;

public class QuickCutMSorterTester {
    final static int RUNS = 200;

    private enum Schema {
        ONE(1000),
        TWO(10000),
        THREE(100000),
        FOUR(1000000);

        int size;

        Schema(int size) {
            this.size = size;
        }
    }

    private enum M{
        ONE(5),
        TWO(10),
        THREE(15),
        FOUR(20),
        FIVE(25);

        int value;

        M(int value){
            this.value = value;
        }

    }

    public static void main(String[] args) {
        QuickCutMSorterTester s = new QuickCutMSorterTester();
        s.Benchmark();
    }

    private void Benchmark(){
        QuickCutSorter sorter = new QuickCutSorter();
        DataSetGenerator<Integer> generator = new IntegerDataSetGenerator();


        final Map<Schema, Map<M, Result>> results = new EnumMap<>(Schema.class);

        for (Schema schema : Schema.values()) {
            results.put(schema,run(sorter, generator, schema));
        }

        for (Map.Entry<Schema, Map<M,Result>> kv: results.entrySet()) {
            System.out.println("Schema: " + kv.getKey().size);
            for (Map.Entry<M, Result> es: kv.getValue().entrySet()) {
                System.out.println("M: " + es.getKey().value + "\n" + es.getValue().toString());
            }
        }

    }

    private <T> Map<M,Result> run(final QuickCutSorter sorter, final DataSetGenerator<T> generator, Schema schema) {

        System.out.println("Running: Schema: " + schema.size);

        final Map<M, Result> results = new EnumMap<>(M.class);

        final CounterListener listener = new CounterListener();
        addListener(sorter, listener);
        final List<T> datum = generator.createRandom(schema.size);

        for (M m: M.values()) {
            System.out.println("\t M: " + m.value);
            final Result result = new Result(RUNS);
            for (int i = 0; i < RUNS; i++) {
                final List<T> copy = new ArrayList<>(datum);
                listener.start();
                sorter.sort(generator.getComparator(), copy, m.value);
                listener.stop();
                result.addResult(listener.getElapsedTime(),listener.getAmtOfSwaps(),listener.getAmtOfComparisons());
                listener.reset();
            }
            results.put(m, result);
        }
        removeListener(sorter, listener);
        return results;
    }

    private void removeListener(QuickCutSorter sorter, CounterListener listener) {
            sorter.removeSorterListener(listener);
        }

    private void addListener(QuickCutSorter sorter, CounterListener listener) {
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
