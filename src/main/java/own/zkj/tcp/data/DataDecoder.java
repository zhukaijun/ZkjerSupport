package own.zkj.tcp.data;

import java.util.List;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import own.zkj.tcp.entity.ZBaseMessageEntity;
import own.zkj.tcp.util.ByteUtil;

/**
 * @author zhukaijun
 */
public class DataDecoder extends ByteToMessageDecoder {

	// private static final String Key = "key";
	public static final AttributeKey<String> NETTY_CHANNEL_KEY = AttributeKey.valueOf("key");

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf bb, List<Object> out) throws Exception {

		// String remoteIp = ctx.channel().remoteAddress().toString();
		// System.out.println(remoteIp);
		int len = bb.readableBytes();
		if (len <= 0)
			return;
//		 String str;
		 String hexdata = ByteBufUtil.hexDump(bb).toUpperCase();
		 bb.readerIndex(len);
		 handleHexData(ctx, hexdata, out);
//		if (bb.hasArray()) {
//			str = new String(bb.array(), bb.arrayOffset() + bb.readerIndex(), bb.readableBytes());
//		} else { // 处理直接缓冲区以及复合缓冲区
//			byte[] bytes = new byte[bb.readableBytes()];
//			bb.getBytes(bb.readerIndex(), bytes);
//			String head = new String(bytes, 0, 4);
//			String src = new String(bytes, 3, 1);
//			String des = new String(bytes, 4, 1);
//			String ft = new String(bytes, 5, 4);
//			String mt = new String(bytes, 9, 8);
//		}
	}

	private void handleHexData(ChannelHandlerContext ctx, String hexdata, List<Object> out) {
		if (hexdata.contains(ZBaseMessageEntity.TAIL_DATA)) {
			int end = hexdata.indexOf(ZBaseMessageEntity.TAIL_DATA);
			String data = hexdata.substring(0, end + 8);
			if (data.startsWith(ZBaseMessageEntity.HEAD_DATA)) {
				ZBaseMessageEntity msg = parseToZBaseMessageEntity(data);
				if (msg != null)
					out.add(msg);
				handleHexData(ctx, hexdata.substring(end + 8), out);
			} else {
				handleHexData(ctx, getCacheData(ctx) + hexdata, out);
			}
		} else {
			cacheData(ctx, hexdata);
		}
	}

	private void cacheData(ChannelHandlerContext ctx, String hexdata) {
		if (hexdata == null || "".equals(hexdata))
			return;
		Attribute<String> attr = ctx.channel().attr(NETTY_CHANNEL_KEY);
		String data = attr.get();
		if (data == null || "".equals(data)) {
			attr.set(hexdata);
		} else {
			attr.set(data + hexdata);
		}
		// 绑定数据
		// 获取数据

	}

	private String getCacheData(ChannelHandlerContext ctx) {
		String data = ctx.channel().attr(NETTY_CHANNEL_KEY).getAndRemove();
		return data;
	}

	private ZBaseMessageEntity parseToZBaseMessageEntity(String data) {
		data = data.toUpperCase();
		if (data.startsWith(ZBaseMessageEntity.HEAD_DATA) && data.endsWith(ZBaseMessageEntity.TAIL_DATA)) {
			ZBaseMessageEntity msg = new ZBaseMessageEntity();
			msg.setSrcData(data);
			data = data.substring(8, data.length() - 8);
			String[] arrs = ByteUtil.hexStrToArrHex(data);
			int bit = 0;
			String src = arrs[bit++];
			msg.setSrc(src);
			String dest = arrs[bit++];
			msg.setDest(dest);
			String frameType = ByteUtil.ArrStrSubHexStringByReverse(arrs, bit, 4);
			msg.setFrameType(frameType);
			bit += 4;
			String msgType = ByteUtil.ArrStrSubHexStringByReverse(arrs, bit, 8);
			msg.setMsgType(msgType);
			bit += 8;
			int pbdatalen = ByteUtil.hexStringToAlgorism(ByteUtil.ArrStrSubHexStringByReverse(arrs, bit, 4));
			msg.setPblen(pbdatalen);
			bit += 4;
			byte[] pbdata = ByteUtil.hex2byte(ByteUtil.ArrStrSubHexString(arrs, bit, pbdatalen));
			msg.setPbdata(pbdata);
			return msg;
		}
		return null;
	}

}
