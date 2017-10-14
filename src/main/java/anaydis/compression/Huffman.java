package anaydis.compression;

import anaydis.search.Map;
import anaydis.search.RandomizedTreeMap;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

public class Huffman implements Compressor{
    @Override
    public void encode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        final Map<Integer, Integer> freqMap = new RandomizedTreeMap<>(Integer::compareTo);
        final Queue<Integer> bytes = new LinkedList<>();
        fillMaps(inputStream, freqMap, bytes);
        final TreeCodeLengthPair pair = createHuffmanTable(createHuffmanTree(createPriorityQueue(freqMap)));
        final Map<Integer, Bits> table = pair.table;
        int codeLength = pair.length;
        writeTable(outputStream, table);
        outputStream.write(0xFF);
        writeCodeLenght(outputStream, codeLength);
        outputStream.write(0xFF);
        writeCode(outputStream, table, bytes);
    }

    private void writeCodeLenght(@NotNull OutputStream outputStream, int codeLength) throws IOException {
        while(codeLength - 127 > 0) {
            outputStream.write(127);
            codeLength -= 127;
        }
        outputStream.write(codeLength);
    }

    private void writeCode(@NotNull OutputStream outputStream, @NotNull Map<Integer, Bits> table, @NotNull Queue<Integer> bytes) throws IOException {
        Bits nextByte = new Bits();
        Bits current = new Bits();
        while (!bytes.isEmpty()) {
            if(current.isFull()) {
                outputStream.write(current.key);
                current = nextByte;
            }
            nextByte = current.add(table.get(bytes.poll()));
        }
        if(!current.isEmpty()) {
            outputStream.write(current.key << 8 - current.count);
            if(current.isFull())
                outputStream.write(nextByte.key << 8 - nextByte.count);
        }
    }

    private void writeTable(@NotNull OutputStream outputStream,@NotNull Map<Integer, Bits> table) throws IOException {
        Iterator<Integer> it = table.keys();
        while (it.hasNext()){
            int value = it.next();
            outputStream.write(value);
            final Bits b = table.get(value);
            outputStream.write(b.count);
            outputStream.write(b.key);
        }
    }

    private Node createHuffmanTree(@NotNull PriorityQueue<Node> queue){
        while (queue.size()>1){
            Node node1 = queue.poll();
            Node node2 = queue.poll();
            queue.add(new Node(node1, node2, null, node1.frequency + node2.frequency));
        }
        return queue.poll();
    }

    private TreeCodeLengthPair createHuffmanTable(Node head){
        final Map<Integer, Bits> map = new RandomizedTreeMap<>(Integer::compareTo);
        final int length = createHuffmanTable(map, head, new Bits());
        return new TreeCodeLengthPair(length, map);
    }

    //return: code length (key length * frequency)
    private int createHuffmanTable(@NotNull Map<Integer, Bits> map, Node node, Bits key){
        if(node.value != null){
            map.put(node.value, key);
            return node.frequency * key.count;
        }
        int result = 0;
        Bits left = new Bits(key.key, key.count);
        result += createHuffmanTable(map, node.left, left.add(false));
        Bits right = new Bits(key.key, key.count);
        return result + createHuffmanTable(map, node.right, right.add(true));
    }

    private PriorityQueue<Node> createPriorityQueue(@NotNull Map<Integer, Integer> freqMap){
        final PriorityQueue<Node> result = new PriorityQueue<>();
        final Iterator<Integer> it = freqMap.keys();
        while(it.hasNext()) {
            int value = it.next();
            int frequency = freqMap.get(value);
            final Node node = new Node(value, frequency);
            result.add(node);
        }
        return result;
    }

    private void fillMaps(@NotNull InputStream inputStream, @NotNull Map<Integer,Integer> freqMap, @NotNull Queue<Integer> bytes) throws IOException {
        int current = inputStream.read();
        while (current != -1) {
            Integer prevFreq = freqMap.get(current);
            if (prevFreq != null) {
                freqMap.put(current, prevFreq + 1);
            }
            else {
                freqMap.put(current, 1);
            }
            bytes.add(current);
            current = inputStream.read();
        }
    }

    private class Node implements Comparable<Node> {
        Node left;
        Node right;
        Integer value;
        Integer frequency;
        Integer height;

        Node(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
            this.height = 0;
        }

        Node(Node left, Node right, Integer value, int frequency) {
            this.left = left;
            this.right = right;
            this.value = value;
            this.frequency = frequency;
            this.height = right.height > left.height ? right.height + 1 : left.height + 1;
        }

        public int compareTo(@NotNull Node node) {
            int cmp = frequency.compareTo(node.frequency);

            int f1 = cmp > 0 ? frequency : node.frequency;
            int f2 = cmp > 0 ? node.frequency : frequency;

            if(f2*4 < f1) return cmp;

            int r = height - node.height;
            int hcmp = r > 1 ? 1 : r < -1 ? -1 : 0;
            return hcmp != 0 ? hcmp : cmp;
        }
    }

    private class TreeCodeLengthPair{
        Map<Integer, Bits> table;
        int length;

        TreeCodeLengthPair(int length, Map<Integer, Bits> table) {
            this.table = table;
            this.length = length;
        }
    }

    @Override
    public void decode(@NotNull InputStream inputStream, @NotNull OutputStream outputStream) throws IOException {
        final Map<Bits, Integer> table = readTable(inputStream);
        int codeLength = readCodeLength(inputStream);

        int current = inputStream.read();
        Bits nextByte = new Bits();

        int j = 0;
        while (current != -1 && j < codeLength){
            for (int i = 7; i >= 0 && j < codeLength; i--) {
                j++;
                nextByte.add(bitAt(current, i));
                Integer value = table.get(nextByte);
                if(value != null){
                    outputStream.write(value);
                    nextByte = new Bits();
                }
            }
            current = inputStream.read();
        }
    }

    private int readCodeLength(@NotNull InputStream inputStream) throws IOException {
        int length = 0;
        int current = inputStream.read();
        while (current != 0xFF){
            length += current;
            current = inputStream.read();
        }
        return length;
    }

    private boolean bitAt(int abyte, int pos) {//pos from 0 to 7, right to left
        return pos < 8 && (abyte >>> pos & 1) != 0;
    }

    private Map<Bits,Integer> readTable(@NotNull InputStream inputStream) throws IOException {
        final Map<Bits, Integer> table = new RandomizedTreeMap<>(new BitsComparator());
        int current = inputStream.read();
        while(current != 0xFF){
            int count = inputStream.read();
            int key = inputStream.read();
            table.put(new Bits(key,count), current);
            current = inputStream.read();
        }
        return table;
    }

    private class BitsComparator implements Comparator<Bits> {

        @Override
        public int compare(Bits b1, Bits b2) {
            int cmp = Integer.compare(b1.key, b2.key);
            return cmp == 0 ? Integer.compare(b1.count, b2.count) : cmp;
        }
    }

    private class Bits{
        int count = 0;
        int key = 0;

        Bits(int key, int count) {
            this.count = count;
            this.key = key;
        }

        Bits() { }

        Bits add(Bits b){
            if(count == 8) return b;
            int emptyBits = 8 - count;
            if(emptyBits >= b.count){
                key = key << b.count | b.key;
                count += b.count;
                return new Bits();
            }
            int k = b.count - emptyBits;//bits to take from b
            count = 8;//full
            key = key << emptyBits | b.key >>> k;//add an emptyBits amount of left-most significant bits from b
            return new Bits((b.key << (8-k)) >>> (8-k) , k);//turn off every used bit from b
        }

        Bits add(boolean bit){ //if already full, overrides first bit and count stays the same.
            if(count < 8) count++;
            key = key << 1 | (bit ? 1 : 0);
            return this;
        }

        boolean isFull(){
            return count == 8;
        }

        boolean isEmpty(){
            return count == 0;
        }
    }


}
