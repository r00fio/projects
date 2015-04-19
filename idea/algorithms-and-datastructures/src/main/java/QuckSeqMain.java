import util.QuickSeq;
import util.Sequence;
import util.Values;

/**
 * Created by pixel on 4/14/15.
 */
public class QuckSeqMain {
    public static void main(String[] args) {
        Sequence sequence = new QuickSeq(100);
        Values.show(sequence, 100);
    }
}
