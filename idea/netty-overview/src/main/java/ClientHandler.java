
/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.LastHttpContent;
import io.netty.util.CharsetUtil;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientHandler extends SimpleChannelInboundHandler<HttpObject> {
    private final BlockingQueue<ChannelFuture> queue = new LinkedBlockingQueue<ChannelFuture>();

    @Override
    public void messageReceived(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpResponse) {
            handleResponse((HttpResponse) msg);
        }
        if (msg instanceof HttpContent) {
            handleContent(ctx, (HttpContent) msg);
        }
    }

    private void handleContent(ChannelHandlerContext ctx, HttpContent msg) {
        System.out.print(msg.content().toString(CharsetUtil.UTF_8));
        System.out.flush();

        if (msg instanceof LastHttpContent) {
            System.out.println("} END OF CONTENT");
            queue.add(ctx.channel().newSucceededFuture());
        }
    }

    private void handleResponse(HttpResponse msg) {
        System.out.println("STATUS: " + msg.getStatus());
        System.out.println("VERSION: " + msg.getProtocolVersion());
        System.out.println();

        if (!msg.headers().isEmpty()) {
            for (String name : msg.headers().names()) {
                for (String value : msg.headers().getAll(name)) {
                    System.out.println("HEADER: " + name + " = " + value);
                }
            }
            System.out.println();
        }

        if (HttpHeaders.isTransferEncodingChunked(msg)) {
            System.out.println("CHUNKED CONTENT {");
        } else {
            System.out.println("CONTENT {");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        queue.add(ctx.channel().newFailedFuture(cause));
        cause.printStackTrace();
        ctx.close();
    }

    public BlockingQueue<ChannelFuture> queue() {
        return queue;
    }

}
