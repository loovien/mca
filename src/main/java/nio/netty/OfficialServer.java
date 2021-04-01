package nio.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class OfficialServer {
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture sync = new ServerBootstrap()
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(3))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println("receive from client: " + ctx.channel().remoteAddress().toString() + " data: " + ByteBufUtil.getBytes(byteBuf));
                                ctx.channel().writeAndFlush(byteBuf);
                            }
                        });
                    }
                }).childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.SO_BACKLOG, 10)
                .bind(new InetSocketAddress("0.0.0.0", 8889)).sync();
        sync.channel().closeFuture().sync();
    }
}
