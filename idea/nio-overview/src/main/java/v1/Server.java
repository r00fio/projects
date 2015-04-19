package v1;

import com.google.common.collect.Lists;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by pixel on 4/1/15.
 */
public class Server {
    public static void main(String[] args) {
        try {
            new Reactor().start(8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
