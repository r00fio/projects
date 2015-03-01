import java.io.*;
import java.nio.channels.FileChannel;

/**
 * Created by bebe on 1/16/15.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        String in = "/media/INST&BOOT/SpyHard.avi";
        String out = "/home/bebe/Documents/SpyHard.avi";
        final long start = System.nanoTime();
        zeroCopy(in, out);
        System.out.println((System.nanoTime() - start) / 1000000);
    }

    private static void zeroCopy(String in, String out) throws IOException {
        FileChannel fin = new FileInputStream(in).getChannel();
        FileChannel fout = new FileOutputStream(out).getChannel();

        fout.transferFrom(fin, 0, new File(in).length());
        fin.close();  fout.close();
    }

    public static void copy(String in, String out) throws IOException {
        InputStream inStream = new FileInputStream(new File(in));
        OutputStream outStream = new FileOutputStream(new File(out));
        byte[] buffer = new byte[1024];
        int length;
        while ((length = inStream.read(buffer)) > 0) {
            outStream.write(buffer, 0, length);
        }
        inStream.close(); outStream.close();
    }
}
