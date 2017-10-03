package anaydis.search.practice.tp5;

import anaydis.search.ArrayMap;
import anaydis.search.Map;
import anaydis.search.RWayTrieMap;
import anaydis.search.RandomizedTree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class MapAnalyzer {


    private final static String QUIJOTE = "./src/test/resources/books/quijote.txt";
    private final static String REVERSED = "./src/test/resources/books/reversedQuijote.txt";
    private FileReader reverseReader;
    private List<Map<String, Integer>> maps;

    private enum Schema {
        ONE(5000),
        TWO(50000),
        THREE(100000),
        FOUR(150000),
        FIVE(200000);

        int size;

        Schema(int size) {
            this.size = size;
        }
    }

    private enum MapType{
        ARRAYMAP,
        TREEMAP,
        RWAYTRIEMAP;
    }


    public static void main(String[] args) {
        MapAnalyzer mapAnalyzer = new MapAnalyzer();
        mapAnalyzer.setUp();
        List<Result> results = mapAnalyzer.benchmark();
        results.forEach(System.out::println);
    }

    private void setUp(){

        maps = new ArrayList<>();
        maps.add(new ArrayMap<>(String::compareTo));
        maps.add(new RandomizedTree<>(String::compareTo));
        maps.add(new RWayTrieMap<>());

        FileReader fileReader = new FileReader(QUIJOTE);

        File file = new File(REVERSED);
        if(!file.exists())
            generateReverseFile(REVERSED, fileReader);

        reverseReader = new FileReader(REVERSED);

        fillMaps(fileReader, maps);
    }

    private void fillMaps(FileReader fileReader, List<Map<String, Integer>> maps){
        String word = fileReader.getNextWord();
        while (word != null) {
            Integer amount = maps.get(0).get(word);
            String finalWord = word;
            maps.forEach(map -> map.put(finalWord, amount == null ? 1 : amount + 1));
            word = fileReader.getNextWord();
        }
    }

    private void generateReverseFile(String path, FileReader fileReader){
        FileWriter fileWriter = new FileWriter(path);
        String word = fileReader.getNextWord();
        while (word != null) {
            fileWriter.writeWord(new StringBuilder(word).reverse().toString());
            word = fileReader.getNextWord();
        }
    }

    private List<Result> benchmark(){
        final List<Result> results = new ArrayList<>();
        for (Schema schema : Schema.values()) {
            for(int i = 0; i < maps.size() ; i++){
                results.add(testMap(schema, maps.get(i), MapType.values()[i]));
            }
        }
        return results;
    }

    private Result testMap(Schema schema, Map<String, Integer> map, MapType mapType) {
        double finalTime = 0;
        for(int i = 0; i < schema.size; i++){
            String word = reverseReader.getNextWord();
            final double startTime = System.nanoTime();
                map.get(word);
            final double endTime = System.nanoTime();
            finalTime += (endTime - startTime);
        }
        reverseReader.reset();
        return new Result(mapType, schema, (finalTime)/1000000);
    }

    private class Result{
        Schema schema;
        Double time;
        MapType mapType;

        public Result(MapType mapType, Schema schema, Double time) {
            this.schema = schema;
            this.time = time;
            this.mapType = mapType;
        }

        @Override
        public String toString() {
            return "Map: " + mapType + "   N: " + schema.size + "  time : " + time;
        }
    }
}
