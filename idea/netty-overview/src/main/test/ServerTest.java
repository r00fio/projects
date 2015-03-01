import junit.framework.TestCase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class ServerTest extends TestCase {

    public void testCall() throws Exception {
        String prevCall;
        for (int i = 0; i < 10; i++) {

            prevCall = call();
            System.out.println(prevCall);
            String call = call();
            assertEquals(prevCall, call);
        }
    }
    public String call() {
        StringBuilder inputLine = new StringBuilder();
        try {
            URL url = new URL("http://localhost:8080?q=green%20flower&image_type=photo");
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                            connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                inputLine.append(line);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return inputLine.toString();
    }
}