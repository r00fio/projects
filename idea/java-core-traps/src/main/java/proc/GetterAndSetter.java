package proc;

import some.Get;
import some.Set;
import some.Warning;

/**
 * Created by pixel on 4/3/15.
 */
@Warning("TODO SOME STUFF")
public class GetterAndSetter {
//    @Get
//    @Set
    private String id;
    private String name;
    private int n;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GetterAndSetter)) return false;

        GetterAndSetter that = (GetterAndSetter) o;

        if (n != that.n) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + n;
        return result;
    }

    public static void main(String[] args) {
//        MyAnnotation
        GetterAndSetter getterAndSetter = new GetterAndSetter();
        System.out.println(getterAndSetter);
    }
}
