package client;

import java.nio.channels.AsynchronousSocketChannel;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by bebe on 3/6/15.
 * TODO: OPTIMISE THIS
 */
public class HttpSupport {
    private static final String CONTENT_BEGIN = "\r\n\r\n";

    public static String parseHeaders(String response) {
        String headers = null;
        if (response.contains(CONTENT_BEGIN)) {
            int contentBeginIndex = response.indexOf(CONTENT_BEGIN);
            headers = response.substring(0, contentBeginIndex);
        }
        return headers;
    }

    public static String parseContent(String response) {
        String content = null;
        if (response.contains(CONTENT_BEGIN)) {
            int contentBeginIndex = response.indexOf(CONTENT_BEGIN);
            content = response.substring(contentBeginIndex, response.length());
        }
        return content;
    }

    public static Integer parseContentLength(String response) {
        Integer contentLength = null;
        String str = "Content-Length";
        if (response.contains(str)) {
//                acc.split()byline
            int i1 = response.indexOf(str);
            String line = response.substring(i1 + str.length());
            if (line.contains("\r")) {
                String substring1 = line.substring(line.indexOf(":") + 2, line.indexOf("\r"));
                contentLength = Integer.parseInt(substring1);
            }
        }
        return contentLength;
    }
}
