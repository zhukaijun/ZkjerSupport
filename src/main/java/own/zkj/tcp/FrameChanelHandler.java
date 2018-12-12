package own.zkj.tcp;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import own.zkj.tcp.entity.ZBaseMessageEntity;
import own.zkj.tcp.listeners.IncomingMessageListener;

@Sharable
public class FrameChanelHandler extends SimpleChannelInboundHandler<ZBaseMessageEntity>{

	private IncomingMessageListener listener;
	private String type;
	
	public FrameChanelHandler(String type,IncomingMessageListener listener) {
		this.listener=listener;
		this.type=type;
	}
	
	
	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ZBaseMessageEntity msg) throws Exception {
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		this.listener.messageRec((ZBaseMessageEntity)msg,ctx);
		this.listener.messageRec((ZBaseMessageEntity)msg);
	}
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
//		ChannelsUtil.add(ctx.channel());
	}
	
	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//		ChannelsUtil.removeChannel(ctx.channel());
	}
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ChannelsUtil.add(type, ctx.channel());
		this.listener.onAct(type,ctx);
		super.channelActive(ctx);
	}
    @Override
    public void disconnect(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
    	ChannelsUtil.removeChannel(ctx.channel());
    	super.disconnect(ctx, promise);
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	ChannelsUtil.removeChannel(ctx.channel());
    	this.listener.onDisConnect(type,ctx);
    	super.channelInactive(ctx);
    }


}
