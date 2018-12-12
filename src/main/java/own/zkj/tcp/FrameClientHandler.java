package own.zkj.tcp;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import own.zkj.tcp.data.DataDecoder;
import own.zkj.tcp.data.DataEncoder;
import own.zkj.tcp.listeners.IncomingMessageListener;
import own.zkj.util.ZDateUtil;

public class FrameClientHandler implements ChannelFutureListener {
	private static Logger logger = LoggerFactory.getLogger(FrameClientHandler.class);
	//TCP连接服务器最大次数及连接间隔时间
	private static final int TCP_CONNECT_COUNTS=3,TCP_CONNECT_DENY=5000;
	private static int hostIndex=0;
	
	/**
	 * @author zhukaijun
	 * host为主备服务器，主服务器不通 尝试连接备服务器
	 * @param host
	 * @param port
	 * @param listener
	 */
	public static void connect(String name,String[] host, int port,IncomingMessageListener listener) {
		connect(name,host, port,TCP_CONNECT_COUNTS,TCP_CONNECT_DENY,listener);
	}
	public static void connect(String name,String[] host, int port,int repeatTimes,int delay,IncomingMessageListener listener) {
		EventLoopGroup group = new NioEventLoopGroup();
		ChannelFuture f = null;
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast(new DataDecoder()).addLast(new DataEncoder())
									.addLast(new FrameChanelHandler(name+"_"+port,listener));
						}
					});
			logger.info("尝试连接(" + host[hostIndex] + ":" + port + ")");
			f = b.connect(host[hostIndex], port).sync().addListener(new FrameClientHandler());
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			String t = ZDateUtil.parseToString(new Date(System.currentTimeMillis() + delay), ZDateUtil.yyyyMMddHHmmssSSS);
			logger.warn("连接服务器(" + host[hostIndex] + ":" + port + ")失败,准备在" + t + "再次尝试连接");
			if (repeatTimes < 1) {
				logger.error("连接服务器(" + host[hostIndex] + ":" + port + ")已超过最大次数，放弃连接");
				return;
			}
		} finally {
			if (repeatTimes < 1) {
				if (f != null && f.channel() != null && f.channel().isOpen()) {
					f.channel().close();
				}
				group.shutdownGracefully();
				return;
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			//TODO !important 无限重连 取消次数递减
//			repeatTimes--;
			if(host.length>1) {
				hostIndex++;
			}
			try {
				logger.warn("再次尝试连接(" + host[hostIndex] + ":" + port + ")");
			}catch (IndexOutOfBoundsException e) {
				hostIndex=0;
			}
			delay = delay>60000?delay:delay * 2;
			connect(name,host, port, repeatTimes,delay,listener);
		}
	}

	@Override
	public void operationComplete(ChannelFuture future) throws Exception {
		if (!future.isSuccess()) {
//			System.out.println("Reconnection");
		}
	}
}
