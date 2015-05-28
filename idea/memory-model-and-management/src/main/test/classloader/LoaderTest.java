package classloader;

import classloader.v1.Loader;
import data.Car;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.*;

/**
 * Remember NoSuchMethodError. Not updated version of class.
 */
public class LoaderTest {
    private String clazz = "data.Car";
    private String compiledClassPath = "file:/home/bebe/work/project/idea/memory-model-and-management/src/main/resources/memory-model-and-management-1.0.jar";
    private Loader loader;
    private Class c1;
    private Class c2;
    @Before
    public void setUp() throws Exception {
        loader = new Loader();
        c1 = loader.load(clazz, compiledClassPath);
        c2 = loader.load(clazz, compiledClassPath);
    }

    @Test(expected = ClassCastException.class)
    public void testOneClassTwoClassloadersV1() throws Exception {
        c1.cast(c2.newInstance());// Why i can cast???
    }

    @Test(expected = ClassCastException.class)
    public void testOneClassTwoClassloadersV2() throws Exception {
        Car c //this class is loaded now. And created instance of Class<JavaLanguage>
                = (Car) c2  //loaded by other classloader
                            .newInstance();
    }
    @Test()
    public void testOneClassTwoClassloadersV3() throws Exception {
        assertNotSame(c1, c2);
    }

    @Test()
    public void testOneClassTwoClassloadersV4() throws Exception {
        assertNotSame(c1.getClassLoader(), c2.getClassLoader());
    }

}