package anaydis.compression;

import org.assertj.core.util.Iterables;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class AllCompressorsTest {

    @Parameterized.Parameter public Compressor compressor;

    @Parameterized.Parameters(name = "Compressor {0}")
    public static List<Object[]> maps(){
        return Arrays.stream(Iterables.toArray(getAllCompressors()))
                .map(c -> new Object[]{c})
                .collect(Collectors.toList());
    }

    private static Iterable<Compressor> getAllCompressors(){
        List<Compressor> comps = new ArrayList<>();
        comps.add(new RunLengthEncoding());
        comps.add(new Huffman());
        comps.add(new BurrowsWheeler());
        return comps;
    }




    @Test
    public void CompressorTest() throws IOException {
        String string = "Call me Ishmael.  Some years ago--never mind how long\n" +
                "precisely--having little or no money in my purse, and nothing\n" +
                "particular to interest me on shore, I thought I would sail about a\n" +
                "little and see the watery part of the world.  It is a way I have of\n" +
                "driving off the spleen and regulating the circulation.  Whenever I\n" +
                "find myself growing grim about the mouth; whenever it is a damp,\n" +
                "drizzly November in my soul; whenever I find myself involuntarily\n" +
                "pausing before coffin warehouses, and bringing up the rear of every\n" +
                "funeral I meet; and especially whenever my hypos get such an upper\n" +
                "hand of me, that it requires a strong moral principle to prevent me\n" +
                "from deliberately stepping into the street, and methodically knocking\n" +
                "people's hats off--then, I account it high time to get to sea as soon\n" +
                "as I can.  This is my substitute for pistol and ball.  With a\n" +
                "philosophical flourish Cato throws himself upon his sword; I quietly\n" +
                "take to the ship.  There is nothing surprising in this.  If they but\n" +
                "knew it, almost all men in their degree, some time or other, cherish\n" +
                "very nearly the same feelings towards the ocean with me.\n" +
                "There now is your insular city of the Manhattoes, belted round by\n" +
                "wharves as Indian isles by coral reefs--commerce surrounds it with\n" +
                "her surf.  Right and left, the streets take you waterward.  Its\n" +
                "extreme downtown is the battery, where that noble mole is washed by\n" +
                "waves, and cooled by breezes, which a few hours previous were out of\n" +
                "sight of land.  Look at the crowds of water-gazers there.\n" +
                "Circumambulate the city of a dreamy Sabbath afternoon.  Go from\n" +
                "Corlears Hook to Coenties Slip, and from thence, by Whitehall,\n" +
                "northward.  What do you see?--Posted like silent sentinels all around\n" +
                "the town, stand thousands upon thousands of mortal men fixed in ocean\n" +
                "reveries.  Some leaning against the spiles; some seated upon the\n" +
                "pier-heads; some looking over the bulwarks of ships from China; some\n" +
                "high aloft in the rigging, as if striving to get a still better\n" +
                "seaward peep.  But these are all landsmen; of week days pent up in\n" +
                "lath and plaster--tied to counters, nailed to benches, clinched to\n" +
                "desks.  How then is this?  Are the green fields gone?  What do they here?\n" +
                "But look! here come more crowds, pacing straight for the water, and\n" +
                "seemingly bound for a dive.  Strange!  Nothing will content them but\n" +
                "the extremest limit of the land; loitering under the shady lee of\n" +
                "yonder warehouses will not suffice.  No.  They must get just as nigh\n" +
                "the water as they possibly can without falling in.  And there they\n" +
                "stand--miles of them--leagues.  Inlanders all, they come from lanes\n" +
                "and alleys, streets and avenues--north, east, south, and west.  Yet\n" +
                "here they all unite.  Tell me, does the magnetic virtue of the\n" +
                "needles of the compasses of all those ships attract them thither?";
        InputStream iStream = new ByteArrayInputStream(string.getBytes(StandardCharsets.UTF_8.name()));
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        compressor.encode(iStream, oStream);
        //System.out.println(oStream.toString());
        ByteArrayOutputStream decodedOStream = new ByteArrayOutputStream();
        compressor.decode(new ByteArrayInputStream(oStream.toByteArray()), decodedOStream);
        //System.out.println(decodedOStream.toString());
        assertThat(decodedOStream.toString()).isEqualTo(string);
    }
}