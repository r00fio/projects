import com.google.common.io.Resources;
import com.google.testing.compile.JavaFileObjects;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import javax.tools.JavaFileObject;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

import static com.google.common.truth.Truth.assert_;
import static com.google.testing.compile.JavaSourceSubjectFactory.javaSource;

import static org.junit.Assert.*;

public class MainTest {

   @Test public void someTest(){
       System.out.println("fsdf");
       System.out.println("fsdf");
   }
}