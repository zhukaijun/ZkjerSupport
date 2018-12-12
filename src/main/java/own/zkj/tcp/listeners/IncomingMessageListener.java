package own.zkj.tcp.listeners;

import java.util.EventListener;

import io.netty.channel.ChannelHandlerContext;
import own.zkj.tcp.entity.ZBaseMessageEntity;

public interface IncomingMessageListener extends EventListener{
	public void messageRec(ZBaseMessageEntity entity,ChannelHandlerContext ctx);
	public void messageRec(ZBaseMessageEntity entity);
	public void onAct(String name,ChannelHandlerContext ctx);
	public void onDisConnect(String name,ChannelHandlerContext ctx);
}
