package own.zkj.tcp.listeners;

import io.netty.channel.ChannelHandlerContext;
import own.zkj.tcp.entity.ZBaseMessageEntity;

/**
 * @author zhukaijun
 */
public abstract class AbIncomingMessageListener implements IncomingMessageListener{
	@Override
	public void messageRec(ZBaseMessageEntity entity) {
	}
	@Override
	public void messageRec(ZBaseMessageEntity entity, ChannelHandlerContext ctx) {
	}
}
