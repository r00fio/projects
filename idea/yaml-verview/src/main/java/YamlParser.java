import org.yaml.snakeyaml.Yaml;

/**
 * Created by pixel on 4/7/15.
 */
public class YamlParser {
    public static <T> T parse(String src) {
        Yaml yaml = new Yaml();
        Iterable<Object> objects = yaml.loadAll(src);
        for (Object object : objects) {

            System.out.println(object);
        }

        return (T)objects;
    }
}
