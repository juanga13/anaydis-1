package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class RunLengthEncoding implements Compressor {
    @Override
    public void encode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        int count = 0;
        int current = inputStream.read();
        int previous = current;
        if(current != -1) {
            while (previous != -1) {
                if (current == previous && count < 256) {
                    count++;
                } else {
                    encodeWrite(outputStream, count, previous);
                    count = 1;
                }
                previous = current;
                current = inputStream.read();
            }
        }
    }

    private void encodeWrite(OutputStream outputStream, int number, int data) throws IOException {
        if(number != 1){
            outputStream.write(0xFF);
            outputStream.write(number);
        }
        outputStream.write(data);
    }

    @Override
    public void decode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        int amount = 1;
        boolean amountNext = false;
        int current = inputStream.read();
        while (current!=-1){
            if(amountNext) {
                amount = current;
                amountNext = false;
            }
            else if(current == 0xFF){
                amountNext = true;
            }
            else {
                decodeWrite(outputStream, amount, current);
                amount = 1;
            }
            current = inputStream.read();
        }
    }

    private void decodeWrite(OutputStream outputStream, int number, int data) throws IOException {
        for (int i = 0; i < number; i++) {
            outputStream.write(data);
        }
    }

}
