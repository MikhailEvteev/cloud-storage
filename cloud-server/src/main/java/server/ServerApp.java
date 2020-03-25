import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class ServerApp {

    public static void main(String[] args) throws Exception {
        new ServerApp().run();
    }

     public void run() throws Exception {
        EventLoopGroup bossCroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossCroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {

                    }
                });
            ChannelFuture future = bootstrap.bind(8189).sync();
            future.channel().closeFuture().sync();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bossCroup.shutdownGracefully();
            bossCroup.shutdownGracefully();

        }
    }
}
