package nio.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class OfficalClient {
    public static void main(String[] args) throws InterruptedException {
        ChannelFuture connect = new Bootstrap().group(new NioEventLoopGroup(1))
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ctx.channel().writeAndFlush(Unpooled.copiedBuffer("luwoen".getBytes()));
                                ByteBuf byteBuf = (ByteBuf) msg;
                                System.out.println("receive from server: " + new String(ByteBufUtil.getBytes(byteBuf)));
                                ctx.channel().writeAndFlush(byteBuf);
                            }
                        });
                    }
                }).connect(new InetSocketAddress("0.0.0.0", 8889));
        connect.sync().channel().closeFuture().sync();
    }
}
