package anaydis.search.practice.tp5;

import java.io.File;
import java.io.IOException;

public class FileWriter {

    java.io.FileWriter fw;

    public FileWriter(String path) {
        try {
            this.fw = new java.io.FileWriter(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeWord(String word){
        try {
            fw.write(word + ",");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
