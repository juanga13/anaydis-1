package anaydis.compression;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import static org.assertj.core.api.Assertions.assertThat;

public class AllCompressorsTest {

    @Test
    public void RLETest() throws IOException {
        Compressor compressor = new Huffman();
        String string = "AABBASDBBBBEEDDSSCC";
        InputStream iStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8.name()));
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        compressor.encode(iStream, oStream);

        ByteArrayOutputStream decodedOStream = new ByteArrayOutputStream();
        compressor.decode(new ByteArrayInputStream(oStream.toByteArray()), decodedOStream);

        assertThat(decodedOStream.toString()).isEqualTo(string);
        System.out.println(decodedOStream.toString());
    }

    @Test
    public void lul(){
        int[] a = new int[1];
        luls(a);
        System.out.println(a[0]);
    }

    private void luls(int[] a){
        a[0] = a[0] + 1;
    }
}