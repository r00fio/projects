/**
 * Created by bebe on 2/18/15.
 */
public class Attachment<A, B> {
    private A a;
    private B b;

    public Attachment(A b, B channel) {
        this.a = b;
        this.b = channel;
    }

    public B getB() {
        return b;
    }

    public A getA() {
        return a;
    }
}
