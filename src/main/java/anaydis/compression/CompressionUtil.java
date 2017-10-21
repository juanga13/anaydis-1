package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class CompressionUtil {
    public static int byteAt(int offset, ArrayList<Integer> array, int pos){
        return array.get((pos + offset) % array.size());
    }

    public static int lastByte(int offset, ArrayList<Integer> array){
        return array.get((array.size()-1 + offset) % array.size());
    }

    public static void writeInteger(@NotNull OutputStream outputStream, int number) throws IOException {
        while(number - 127 > 0) {
            outputStream.write(127);
            number -= 127;
        }
        outputStream.write(number);
    }

    public static int readInteger(@NotNull InputStream inputStream, int stopByte) throws IOException {
        int number = 0;
        int current = inputStream.read();
        while (current != stopByte){
            number += current;
            current = inputStream.read();
        }
        return number;
    }
}

