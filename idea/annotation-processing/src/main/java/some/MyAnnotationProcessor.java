package some;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

import static javax.tools.Diagnostic.Kind.NOTE;
import static javax.tools.Diagnostic.Kind.WARNING;

/**
 * Created by pixel on 4/3/15.
 */
@SupportedAnnotationTypes({"some.Warning", "some.Get", "some.Set"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class MyAnnotationProcessor extends AbstractProcessor {

    @Override
    public boolean process(java.util.Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        java.util.Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(Set.class);
        Thread thread = new Thread(() -> {
            for (Element e : elements) {
                if (e.getKind() == ElementKind.CLASS) {
                    TypeElement classElement = (TypeElement) e;
                    Name qualifiedName = classElement.getQualifiedName();
                    Messager messager = processingEnv.getMessager();
                    try {
                        JavaFileObject jfo = processingEnv.getFiler().createSourceFile(
                                qualifiedName);
                        URI uri = jfo.toUri();
                        messager.printMessage(NOTE, uri.toString().substring(5, uri.toString().length() - 4) + "class", e);
                        File f = new File(uri.toString().substring(5, uri.toString().length() - 4) + "class");
                        while (!f.exists()) ;
                        f.delete();
                        try {

                            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                            DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();

                            StringWriter w = new StringWriter();
                            PrintWriter out = new PrintWriter(w);
                            out.println("public class Hello {");
                            out.println("  public static void main(String args[]) {");
                            out.println("    System.out.println(\"This is in another java file\");");
                            out.println("  }");
                            out.println("}");
                            out.close();
                            JavaFileObject file = new JavaSourceFromString("Hello", w.toString());

                            Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
                            JavaCompiler.CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);

                            boolean success = task.call();
                            for (Diagnostic diagnostic : diagnostics.getDiagnostics()) {
                                System.out.println(diagnostic.getCode());
                                System.out.println(diagnostic.getKind());
                                System.out.println(diagnostic.getPosition());
                                System.out.println(diagnostic.getStartPosition());
                                System.out.println(diagnostic.getEndPosition());
                                System.out.println(diagnostic.getSource());
                                System.out.println(diagnostic.getMessage(null));

                            }

                            System.out.println("Success: " + success);
                            messager.printMessage(NOTE, "Done", e);
                        } catch (Exception e1) {
                            messager.printMessage(NOTE, "EXC", e);
                            messager.printMessage(NOTE, e1.toString(), e);

                        }
//                        writer = jfo.openWriter();

//                        writer.write("package top;\n" +
//                                "public class Main {\n" +
//                                "    private String s = \"Hello world\";\n" +
//                                "}\n");
//                        writer.flush();
                    } catch (IOException e1) {
                        messager.printMessage(NOTE, "eeeeeeeeee;;;", e);
                        e1.printStackTrace();
                    }
                }
            }

        });
        thread.setDaemon(true);
        thread.start();
        return true;
    }

    private JavaFileObject getJavaFileObject() {
        StringWriter w = new StringWriter();
        PrintWriter out = new PrintWriter(w);
        out.print("package top;");
        out.print("public class MainA {");
        out.print("private String s = \"Hello World\";");
        out.print("public String getS() {");
        out.print("return s;");
        out.print("}");
        out.print(" public void setS(String s) {");
        out.print("this.s = s;");
        out.print("}");
        out.close();
        return new JavaSourceFromString("MainA", w.toString());
    }

    private void shit(Element e, File f) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(f);
        while (fileInputStream.available() != -1) {

            byte[] bytes = new byte[fileInputStream.available()];
            fileInputStream.read(bytes);
            int i = 0;
            for (byte b : bytes) {
                i++;
                if (i == 9) {
                    i = 0;
                    processingEnv.getMessager().printMessage(NOTE, Byte.toString(b), e);
                }
            }

        }
    }

    class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    private FileInputStream getFileInputStream(FileInputStream fileInputStream, JavaFileObject jfo) throws IOException {
        try {
            fileInputStream = new FileInputStream(new File(jfo.toUri()));
        } catch (FileNotFoundException e1) {
            getFileInputStream(fileInputStream, jfo);
        }
        while (fileInputStream.available() < 1) ;
        return fileInputStream;
    }

    String strs = "cafe babe 0000 0034 0016 0a00 0500 1108\n" +
            "0012 0900 0400 1307 0014 0700 1501 0001\n" +
            "7301 0012 4c6a 6176 612f 6c61 6e67 2f53\n" +
            "7472 696e 673b 0100 063c 696e 6974 3e01\n" +
            "0003 2829 5601 0004 436f 6465 0100 0f4c\n" +
            "696e 654e 756d 6265 7254 6162 6c65 0100\n" +
            "124c 6f63 616c 5661 7269 6162 6c65 5461\n" +
            "626c 6501 0004 7468 6973 0100 0a4c 746f\n" +
            "702f 4d61 696e 3b01 000a 536f 7572 6365\n" +
            "4669 6c65 0100 094d 6169 6e2e 6a61 7661\n" +
            "0c00 0800 0901 0005 6673 6466 730c 0006\n" +
            "0007 0100 0874 6f70 2f4d 6169 6e01 0010\n" +
            "6a61 7661 2f6c 616e 672f 4f62 6a65 6374\n" +
            "0021 0004 0005 0000 0001 0002 0006 0007\n" +
            "0000 0001 0001 0008 0009 0001 000a 0000\n" +
            "0039 0002 0001 0000 000b 2ab7 0001 2a12\n" +
            "02b5 0003 b100 0000 0200 0b00 0000 0a00\n" +
            "0200 0000 0d00 0400 0f00 0c00 0000 0c00\n" +
            "0100 0000 0b00 0d00 0e00 0000 0100 0f00\n" +
            "0000 0200 10";
}