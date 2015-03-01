import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.example.http.snoop.HttpSnoopClientHandler;
import io.netty.example.http.snoop.HttpSnoopClientInitializer;
import io.netty.handler.codec.http.*;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * Created by bebe on 2/22/15.
 */
public class ClientBootstrap {
    private String host;
    private int port;
    private Channel channel;
    private SimpleChannelInboundHandler handler;

    public ClientBootstrap(String host, int port, SimpleChannelInboundHandler handler) {
        this.host = host;
        this.port = port;
        this.handler = handler;
    }

    public Channel connect() throws URISyntaxException, InterruptedException {

        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.option(ChannelOption.SO_KEEPALIVE, true);
        b.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {

                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        final ChannelPipeline p = ch.pipeline();
                        p.addLast("codec", new HttpClientCodec());
                        p.addLast("aggregator", new HttpObjectAggregator(1048576));
                        p.addLast("handler", handler);

                    }
                });

        // Make the connection attempt.
        return b.connect(host, port).sync().channel();

    }

//    public ChannelFuture send(HttpRequest request) throws InterruptedException {
//        final ChannelFuture requestFuture = channel.writeAndFlush(request).sync();
//        if (!requestFuture.isSuccess()) {
//            requestFuture.cause().printStackTrace();
//        }
//
//        // Waits for the complete HTTP response
//        ChannelFuture responseFuture = handler.queue().poll(5, TimeUnit.SECONDS);
//
//        if (!responseFuture.isSuccess()) {
//            responseFuture.cause().printStackTrace();
//        }
//        return requestFuture;
//    }

    public DefaultFullHttpRequest createRequest(String query) {
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, host + "?" + query);
        request.headers().set(HttpHeaders.Names.HOST, host);
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.CLOSE);
        request.headers().set(HttpHeaders.Names.ACCEPT_ENCODING, HttpHeaders.Values.GZIP);
        return request;
    }
}