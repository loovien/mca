package nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        NioServerSocketChannel nioServerSocketChannel = new NioServerSocketChannel();
        eventExecutors.register(nioServerSocketChannel);

        nioServerSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                NioSocketChannel nioSocketChannel = (NioSocketChannel) msg;
                System.out.println(nioSocketChannel.remoteAddress().toString());
                nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf byteBuf = (ByteBuf) msg;
                        byte[] bytes = ByteBufUtil.getBytes(byteBuf);
                        System.out.println("receive from client: " + ctx.channel().remoteAddress().toString() + " data: " + new String(bytes));
                        ctx.channel().writeAndFlush(byteBuf);
                    }

                    @Override
                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                        super.exceptionCaught(ctx, cause);
                    }
                });
                eventExecutors.register(nioSocketChannel);
            }

            @Override
            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                cause.printStackTrace();
            }
        });
        ChannelFuture server = nioServerSocketChannel.bind(new InetSocketAddress("0.0.0.0", 8988));
        System.out.println("server listan at: " + "0.0.0.0:8988");
        server.sync().channel().closeFuture().sync();
    }
}
