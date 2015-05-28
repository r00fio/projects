package classloader.v1;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by pixel on 4/19/15.
 */
public class Loader {
    public Class load(String clazz, String compiledClassPath) {
        Class c = null;
        try {
            ClassLoader parent = ClassLoader.getSystemClassLoader().getParent();
            ClassLoader cl = new URLClassLoader(new URL[]{new URL(compiledClassPath)}, parent);
            c = cl.loadClass(clazz);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return c;
    }
}
