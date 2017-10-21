package anaydis.compression;

import java.util.ArrayList;
import java.util.Comparator;

public class RotationComparator implements Comparator<Integer> {

    private ArrayList<Integer> bytes;

    RotationComparator(ArrayList<Integer> bytes) {
        this.bytes = bytes;
    }

    @Override
    public int compare(Integer i1, Integer i2) {
        for(int i = 0; i < bytes.size(); i++){
            int cmp = Integer.compare(CompressionUtil.byteAt(i1, bytes, i), CompressionUtil.byteAt(i2, bytes, i));
            if (cmp != 0)
                return cmp;
        }
        return 0;
    }
}
