package anaydis.compression;


import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class MoveToFront implements Compressor{

    private List<Integer> generateDictionary(){
        final List<Integer> dict = new LinkedList<>();
        for (int i = 0; i <= 255; i++) {
            dict.add(i);
        }
        return dict;
    }

    @Override
    public void encode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        final List<Integer> dict = generateDictionary();
        int current = inputStream.read();
        while (current != -1){
            int i = dict.indexOf(current);
            outputStream.write(i);
            dict.remove(i);
            dict.add(0, current);
            current = inputStream.read();
        }
    }

    @Override
    public void decode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        final List<Integer> dict = generateDictionary();
        int current = inputStream.read();
        while (current != -1){
            int i = dict.get(current);
            outputStream.write(i);
            dict.remove(current);
            dict.add(0, i);
            current = inputStream.read();
        }
    }
}
