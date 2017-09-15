package anaydis.search.practice.tp5;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

public class FileReader {

    private BufferedReader br;
    private Queue<String> wordsLeft;
    private String path;

    public FileReader(String path) {
        this.path = path;
        wordsLeft = new LinkedList<>();
        reset();
    }

    public String getNextWord(){
        if(wordsLeft.isEmpty()){
            try {
                while (wordsLeft.isEmpty()) {
                    String line = br.readLine();
                    if(line == null)
                        return null;
                    wordsLeft = Arrays.stream(line.toLowerCase().split("[^(a-z|ñ|á|é|í|ó|ú)]"))
                            .filter(word -> word.length() > 0)
                            .collect(Collectors.toCollection(LinkedList::new));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return wordsLeft.poll();
    }

    public void reset(){
        try {
            br = new BufferedReader(new java.io.FileReader(new File(path)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
