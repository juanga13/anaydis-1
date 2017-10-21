package anaydis.compression;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class BurrowsWheeler implements Compressor{
    @Override
    public void encode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        ArrayList<Integer> rotations = new ArrayList<>();
        final ArrayList<Integer> bytes = new ArrayList<>();
        fillArrays(inputStream, bytes, rotations);
        rotations.sort(new RotationComparator(bytes));
        writeEncoded(outputStream, bytes, rotations);
    }

    private void writeEncoded(OutputStream outputStream, ArrayList<Integer> bytes, ArrayList<Integer> rotations) throws IOException {
        int n = 0;
        for (int i = 0; i < rotations.size(); i++){
            int r = rotations.get(i);
            if(r == 1){
                n = i;
            }
            outputStream.write(CompressionUtil.lastByte(rotations.get(i), bytes));
        }
        outputStream.write(0xFF);
        CompressionUtil.writeInteger(outputStream, n);
    }


    private void fillArrays(InputStream inputStream, ArrayList<Integer> bytes, ArrayList<Integer> rotations) throws IOException {
        int current = inputStream.read();
        int i = 0;
        while (current != -1) {
            bytes.add(current);
            rotations.add(i++);
            current = inputStream.read();
        }
    }


    @Override
    public void decode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        ArrayList<ByteIndexPair> l = new ArrayList<>();
        int n = readEncoded(inputStream, l);
        l.sort(ByteIndexPair::compareTo);
        writeDecoded(outputStream, l, n);
    }


    private void writeDecoded(OutputStream outputStream, ArrayList<ByteIndexPair> l, int n) throws IOException {
        int k = l.indexOf(new ByteIndexPair(n, -1));
        for (int i = 0; i < l.size(); i++){
            outputStream.write(l.get(k).b);
            k = l.get(k).index;
        }
    }

    private int readEncoded(InputStream inputStream, ArrayList<ByteIndexPair> l) throws IOException {
        int current = inputStream.read();
        int i = 0;
        while(current != 0xFF){
            l.add(new ByteIndexPair(i++, current));
            current = inputStream.read();
        }
        return CompressionUtil.readInteger(inputStream, -1);
    }

    private class ByteIndexPair implements Comparable<ByteIndexPair> {
        int index;
        Integer b;

        ByteIndexPair(int index, int b) {
            this.index = index;
            this.b = b;
        }


        @Override
        public boolean equals(Object o) {
            return index == ((ByteIndexPair)o).index;
        }

        @Override
        public int compareTo(@NotNull ByteIndexPair i) {
            return b.compareTo(i.b);
        }
    }

}
