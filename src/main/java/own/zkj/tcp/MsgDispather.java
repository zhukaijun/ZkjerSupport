package own.zkj.tcp;

import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import io.netty.channel.Channel;
import own.zkj.tcp.entity.ZBaseMessageEntity;

/**
 * @author zhukj 与其他系统TCP通讯交互类 包括4个接收站
 */
public class MsgDispather {

	private Map<String, LinkedBlockingQueue<ZBaseMessageEntity>> queues = new Hashtable<String, LinkedBlockingQueue<ZBaseMessageEntity>>();

	public boolean sendMsg(String name, ZBaseMessageEntity msg) throws Exception{
		if(getQueue(name)==null) {
			return false;
		}
		getQueue(name).offer(msg);
		return true;
	}

	public boolean sendMsg(String name, String dest, String frameType, String msgType, byte[] pbdata) throws Exception{
		//TODO zkj 
		return false;
	}

	public void send(String name, ZBaseMessageEntity msg) throws Exception{
		Channel ch = ChannelsUtil.get(name);
		if (ch != null && ch.isActive())
			ch.writeAndFlush(msg);
		else
			sendMsg(name, msg); // 重发 if消息顺序问题 应该阻塞等待ch恢复
	}

	public Map<String, LinkedBlockingQueue<ZBaseMessageEntity>> getQueues(){
		return queues;
	}
	public LinkedBlockingQueue<ZBaseMessageEntity> getQueue(String name) {
		return queues.get(name);
	}

}
