package server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static final List<Channel> channels = new ArrayList();
    private static int newClientIndex = 1;
    private String clientName;

    public ServerHandler() {
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент подключился: " + ctx);
        channels.add(ctx.channel());
        this.clientName = "Клиент #" + newClientIndex;
        ++newClientIndex;
        this.broadcastMessage("SERVER", "Подключился новый клиент: " + this.clientName);
    }

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
        System.out.println("Получено сообщение: " + s);
        if (s.startsWith("/")) {
            if (s.startsWith("/changename ")) {
                String newNickname = s.split("\\s", 2)[1];
                this.broadcastMessage("SERVER", "Клиент " + this.clientName + " сменил ник на " + newNickname);
                this.clientName = newNickname;
            }

        } else {
            this.broadcastMessage(this.clientName, s);
        }
    }

    public void broadcastMessage(String clientName, String message) {
        String out = String.format("[%s]: %s\n", clientName, message);
        Iterator var4 = channels.iterator();

        while(var4.hasNext()) {
            Channel c = (Channel)var4.next();
            c.writeAndFlush(out);
        }

    }

    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Клиент " + this.clientName + " вышел из сети");
        channels.remove(ctx.channel());
        this.broadcastMessage("SERVER", "Клиент " + this.clientName + " вышел из сети");
        ctx.close();
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Клиент " + this.clientName + " отвалился");
        channels.remove(ctx.channel());
        this.broadcastMessage("SERVER", "Клиент " + this.clientName + " вышел из сети");
        ctx.close();
    }
}

