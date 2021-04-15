package nio.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        NioSocketChannel nioSocketChannel = new NioSocketChannel();
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        eventExecutors.register(nioSocketChannel);

        ChannelFuture connect = nioSocketChannel.connect(new InetSocketAddress("192.168.163.184", 8888));
        ChannelFuture sync = connect.sync();

        nioSocketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf byteBuf = (ByteBuf) msg;
                System.out.println(new String(ByteBufUtil.getBytes(byteBuf)));
            }
        });

        nioSocketChannel.writeAndFlush(Unpooled.copiedBuffer("hello world".getBytes()));

        sync.channel().closeFuture().sync();
        System.out.println("netty client starting");
    }
}
