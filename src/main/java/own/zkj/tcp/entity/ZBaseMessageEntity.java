package own.zkj.tcp.entity;

import java.io.Serializable;

/**
 * @author zhukaijun
 */
public class ZBaseMessageEntity implements Serializable{
//	public static final String HEAD_DATA = "499602D2";
//	public static final String TAIL_DATA = "B669FD2E";
	private static final long serialVersionUID = -1700484634298724064L;
	public static final String HEAD_DATA = "D2029649";
	public static final String TAIL_DATA = "2EFD69B6";
	private String src; //发送源
	private String dest;
	private String frameType;
	private String msgType;
	private int pblen;
	private byte[] pbdata;
	private String srcData; // 原始数据流
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getFrameType() {
		return frameType;
	}

	public void setFrameType(String frameType) {
		this.frameType = frameType;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public int getPblen() {
		return pblen;
	}

	public void setPblen(int pblen) {
		this.pblen = pblen;
	}

	public byte[] getPbdata() {
		return pbdata;
	}

	public void setPbdata(byte[] pbdata) {
		this.pbdata = pbdata;
	}

	public String getSrcData() {
		return srcData;
	}

	public void setSrcData(String srcData) {
		this.srcData = srcData;
	}
}
