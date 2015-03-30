package client;

import io.netty.handler.codec.http.HttpHeaders;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * Created by bebe on 2/18/15.
 */
public final class RequestBuilder {

    private RequestBuilder() {
    }

    public static ByteBuffer buildGetRequest(String url) throws URISyntaxException {
        URI uri = new URI(url);

        // Now query and verify the various parts of the URI
        String scheme = uri.getScheme();
        if (scheme == null || !scheme.equals("http"))
            throw new IllegalArgumentException("Must use 'http:' protocol");

        String hostname = uri.getHost();

        int port = uri.getPort();
        if (port == -1)
            port = 80; // Use default port if none specified

        String path = uri.getRawPath();
        if (path == null || path.length() == 0)
            path = "/";

        String query = uri.getRawQuery();
        query = (query == null) ? "" : '?' + query;


        // Put together the HTTP request we'll send to the server.
        String request = "GET " + path + query + " HTTP/1.1\r\n" + // The request
                "Host: " + hostname + "\r\n" + // Required in HTTP 1.1
                "Connection: " + HttpHeaders.Values.KEEP_ALIVE +"\r\n" + // Don't keep connection open
                "User-Agent: " + "super_geek" + "\r\n" + "\r\n";
        // Now wrap a CharBuffer around that request string
        CharBuffer requestChars = CharBuffer.wrap(request);

        // Get a Charset object to encode the char buffer into bytes
        Charset charset = Charset.forName("ISO-8859-1");

        // Use the charset to encode the request into a byte buffer
        ByteBuffer requestBytes = charset.encode(requestChars);
        return requestBytes;
    }
}
