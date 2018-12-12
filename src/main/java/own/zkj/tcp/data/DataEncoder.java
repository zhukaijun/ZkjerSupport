package own.zkj.tcp.data;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import own.zkj.tcp.entity.ZBaseMessageEntity;
import own.zkj.tcp.util.ByteUtil;

/**
 * @author zhukaijun
 */
public class DataEncoder extends MessageToByteEncoder<ZBaseMessageEntity>{
	public static String encodeData(ZBaseMessageEntity obj){
		
		String hexData = obj.getSrc() + obj.getDest() +
				ByteUtil.hexStrReverse(obj.getFrameType())
		+ ByteUtil.hexStrReverse(obj.getMsgType()) + ByteUtil.hexStrReverse(ByteUtil.algorismToHEXString(obj.getPblen(),8));
		hexData += ByteUtil.byte2hex(obj.getPbdata()); 
		byte[] head= ByteUtil.hex2byte(ZBaseMessageEntity.HEAD_DATA),
				body=ByteUtil.hex2byte(hexData),
				tail=ByteUtil.hex2byte(ZBaseMessageEntity.TAIL_DATA),
				all=new byte[head.length+body.length+tail.length];
		System.arraycopy(head, 0, all,0,head.length);
		System.arraycopy(body, 0, all,head.length,body.length);
		System.arraycopy(tail, 0, all,body.length,tail.length);
//		String data=ByteBufUtil.hexDump(all).toUpperCase();
		String data = ByteUtil.byte2hex(all);
		return data;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, ZBaseMessageEntity obj, ByteBuf buf) throws Exception {
		buf.writeBytes(ByteUtil.hex2byte(ZBaseMessageEntity.HEAD_DATA));
		String hexData = obj.getSrc() + obj.getDest() + 
				ByteUtil.hexStrReverse(obj.getFrameType())
		+ ByteUtil.hexStrReverse(obj.getMsgType()) + ByteUtil.hexStrReverse(ByteUtil.algorismToHEXString(obj.getPblen(),8));
		hexData += ByteUtil.byte2hex(obj.getPbdata());
		
//		buf.writeBytes(obj.getSrc().getBytes());
//		buf.writeBytes(obj.getDest().getBytes());
//		buf.writeBytes(obj.getFrameType().getBytes());
//		buf.writeBytes(obj.getMsgType().getBytes());
//		buf.writeBytes(ByteUtil.algorismToHEXString(obj.getPblen(),8).getBytes());
//		buf.writeBytes(obj.getPbdata());
		
//		System.out.println("send src data:>>" + hexData);
		buf.writeBytes(ByteUtil.hex2byte(hexData));
		buf.writeBytes(ByteUtil.hex2byte(ZBaseMessageEntity.TAIL_DATA));

	}

}
