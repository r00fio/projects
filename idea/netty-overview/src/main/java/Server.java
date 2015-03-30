import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.example.http.snoop.HttpSnoopClient;
import io.netty.example.http.snoop.HttpSnoopServer;
import io.netty.example.http.websocketx.server.WebSocketServer;
import io.netty.example.http.websocketx.server.WebSocketServerHandler;
import io.netty.example.http.websocketx.server.WebSocketServerInitializer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by bebe on 2/15/15.
 * ServerSocketImpl used for read and write by Netty
 * HttpClientCOdec.encode - > Request to bytebuf
 * Example LiginningHandler.wirte -> DefaultChannelCOntext.write -> next.invoker.write .
 * ChannelHandlerContext keeps pipeline and operates it. Tail and head is Handlers called in begining and at the end
 * HandlerInvoker invokes handler
 * HeadHandler adapter to unsafe. AbstarctNioByteCHannel.NioByteUnsafe -> AbstractChannel.AbstractUnsafe
 * unsafe.write only adds message to outboundBuffer(Entry e = buffer[tail++];
 e.msg = msg;). Flush does real write to remote peer through NioSocketChannel ->
 * UnpooledUnsafeDirectByteBuf -> WritableByteChannel.write(ByteBuf) throught SocketChannelImpl.write(ByteBuf)
 *
 *
 *
 * IMPORTANT DOn't do response or buf as global variable in handler!!!!!!!! In example bug
 */
public class Server {
    public static void main(String[] args) throws Exception {
//        runServer(args);
        runMyServerf();

    }

    private static void runMyServerf() throws InterruptedException {
        int port = 8080;

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                            .addLast("codec-http", new HttpServerCodec())
                            .addLast("aggregator", new HttpObjectAggregator(65536))
                            .addLast("multiplexer", new FlowMultiplexer())
                            .addLast("handler", new Handler());
                        }
                    });

            Channel ch = b.bind(port).sync().channel();
            System.out.println("Web socket server started at port " + port + '.');
            System.out.println("Open your browser and navigate to http://localhost:" + port + '/');

            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private static void runServer(String ... args) {
                new Thread(() -> {
            try {
//                HttpSnoopServer.main(args);
                TstHTTPServer.main(args);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }


}
