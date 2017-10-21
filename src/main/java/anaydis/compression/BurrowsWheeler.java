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
        /*
        leer L y num N
        ordenar para tener F
        armo T: T[i] = j con j siendo el char en L e i la pos del mismo en F
        con N sé cual es mi primer char - useless
        empiezo de t[ultimo indice]
        t4 = 0 : t0 = 1: t5 = 2 : t6 = 3 : t2 = 4 : t3 = 5 : t1 = 6
        2 - 5 - 3 - 4 - 0 - 1 - 6
        varias formas: poner N de L, luego N de F, luego empezar con T[N] = K y seguir con T[K] = ....
        */
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
