package own.zkj.tcp.util;


import java.nio.charset.Charset;

/**
 * @author zhukaijun
 */
public class ByteUtil {

	// 7e 30 7d 02 08 7d 01 55 7e

//	@Test
//	public void testTrans() {
//
//		// String val = "7e307d02087d01557e";
//		// for (int i = 0; i < val.length(); i = i+2) {
//		// StringBuilder sb = new StringBuilder();
//		// sb.append(val.charAt(i)).append(val.charAt(i+1));
//		// // System.out.print(val.charAt(i));
//		// // System.out.print("-----");
//		// // System.out.println(val.charAt(i + 1));
//		// System.out.println(sb);
//		// }
//		// byte[] bytes = hex2byte("ff307d02087d01557e");
//		// for (byte b : bytes) {
//		// System.out.println(b);
//		// }
//		//
//
//		// byte byteValue=0;
//		// int temp =194;
//		// if ( temp < 0) {
//		// byteValue = (byte)(temp < -128 ? 256 + temp : temp);
//		// }
//		// else {
//		// byteValue = (byte)(temp > 127 ? temp - 256 : temp);
//		// }
//		// System.out.println(byteValue);
//		byte b = (byte) (255 & 0xFF);
//		System.out.println((int)b);
//		// System.out.println(b+"---"+(b & 0xFF));
//		// System.out.println(algorismToHEXString((b & 0xFF)));
//		// System.out.println(algorismToHEXString(22,4));
//		String phone = "19";
//		// System.out.println(BCD(phone));
//		
////		System.out.println(hexStringToBinary("16"));
////		System.out.println(Integer.toHexString(34));
////		System.out.println(algorismToHEXString(binaryToAlgorism("00010110")));
//	}
 

	/**
	 * 数字字符串转ASCII码字符串
	 * 
	 * @param String
	 *            字符串
	 * @return ASCII字符串
	 */
	public static String StringToAsciiString(String content) {
		String result = "";
		int max = content.length();
		for (int i = 0; i < max; i++) {
			char c = content.charAt(i);
			String b = Integer.toHexString(c);
			result = result + b;
		}
		return result;
	}

	public static String toStringHex2(String s) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, "GBK");//协议上是GBK
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}
	
	public static String toStringHex2(String s,String encode) {
		byte[] baKeyword = new byte[s.length() / 2];
		for (int i = 0; i < baKeyword.length; i++) {
			try {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						s.substring(i * 2, i * 2 + 2), 16));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		try {
			s = new String(baKeyword, encode);//协议上是GBK
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return s;
	}

	/**
	 * 十六进制转字符串
	 * 
	 * @param hexString
	 *            十六进制字符串
	 * @param encodeType
	 *            编码类型4：Unicode，2：普通编码
	 * @return 字符串
	 */
	public static String hexStringToString(String hexString, int encodeType) {
		String result = "";
		int max = hexString.length() / encodeType;
		for (int i = 0; i < max; i++) {
			char c = (char) ByteUtil.hexStringToAlgorism(hexString.substring(i
					* encodeType, (i + 1) * encodeType));
			result += c;
		}
		return result;
	}

	/**
	 * 十六进制字符串装十进制
	 * 
	 * @param hex
	 *            十六进制字符串
	 * @return 十进制数值
	 */
	public static int hexStringToAlgorism(String hex) {
		hex = hex.toUpperCase();
		int max = hex.length();
		int result = 0;
		for (int i = max; i > 0; i--) {
			char c = hex.charAt(i - 1);
			int algorism = 0;
			if (c >= '0' && c <= '9') {
				algorism = c - '0';
			} else {
				algorism = c - 55;
			}
			result += Math.pow(16, max - i) * algorism;
		}
		return result;
	}
	
	public static double hexStringToDouble(String hex) {
		hex = hex.toUpperCase();
		int max = hex.length();
		double result = 0;
		for (int i = max; i > 0; i--) {
			char c = hex.charAt(i - 1);
			int algorism = 0;
			if (c >= '0' && c <= '9') {
				algorism = c - '0';
			} else {
				algorism = c - 55;
			}
			result += Math.pow(16, max - i) * algorism;
		}
		return result;
	}

	/**
	 * 十六转二进制
	 * 
	 * @param hex
	 *            十六进制字符串
	 * @return 二进制字符串
	 */
	public static String hexStringToBinary(String hex) {
		hex = hex.toUpperCase();
		String result = "";
		int max = hex.length();
		for (int i = 0; i < max; i++) {
			char c = hex.charAt(i);
			switch (c) {
			case '0':
				result += "0000";
				break;
			case '1':
				result += "0001";
				break;
			case '2':
				result += "0010";
				break;
			case '3':
				result += "0011";
				break;
			case '4':
				result += "0100";
				break;
			case '5':
				result += "0101";
				break;
			case '6':
				result += "0110";
				break;
			case '7':
				result += "0111";
				break;
			case '8':
				result += "1000";
				break;
			case '9':
				result += "1001";
				break;
			case 'A':
				result += "1010";
				break;
			case 'B':
				result += "1011";
				break;
			case 'C':
				result += "1100";
				break;
			case 'D':
				result += "1101";
				break;
			case 'E':
				result += "1110";
				break;
			case 'F':
				result += "1111";
				break;
			}
		}
		return result;

	}

	// 4位的bcd码
	public static String BCD(String bin) {
		return String.valueOf(binaryToAlgorism(bin));
	}

	/**
	 * ASCII码字符串转数字字符串
	 * 
	 * @param String
	 *            ASCII字符串
	 * @return 字符串
	 */
	public static String AsciiStringToString(String content) {
		String result = "";
		int length = content.length() / 2;
		for (int i = 0; i < length; i++) {
			String c = content.substring(i * 2, i * 2 + 2);
			int a = hexStringToAlgorism(c);
			char b = (char) a;
			String d = String.valueOf(b);
			result += d;
		}
		return result;
	}

	/**
	 * 将十进制转换为指定长度的十六进制字符串
	 * 
	 * @param algorism
	 *            int 十进制数字
	 * @param maxLength
	 *            int 转换后的十六进制字符串长度
	 * @return String 转换后的十六进制字符串
	 */
	public static String algorismToHEXString(int algorism, int maxLength) {
		String result = "";
		result = Integer.toHexString(algorism);
		if (result.length() % 2 == 1) {
			result = "0" + result;
		}
		return patchHexString(result.toUpperCase(), maxLength);
	}
	
//	public static String doubleToHEXString(double value, int maxLength) {
//		String result = "";
//		result = Double.toHexString(value);
//		if (result.length() % 2 == 1) {
//			result = "0" + result;
//		}
//		return patchHexString(result.toUpperCase(), maxLength);
//	}

	/**
	 * 字节数组转为普通字符串（ASCII对应的字符）
	 * 
	 * @param bytearray
	 *            byte[]
	 * @return String
	 */
	public static String bytetoString(byte[] bytearray) {
		String result = "";
		char temp;

		int length = bytearray.length;
		for (int i = 0; i < length; i++) {
			temp = (char) bytearray[i];
			result += temp;
		}
		return result;
	}

	/**
	 * 二进制字符串转十进制
	 * 
	 * @param binary
	 *            二进制字符串
	 * @return 十进制数值
	 */
	public static int binaryToAlgorism(String binary) {
		int max = binary.length();
		int result = 0;
		for (int i = max; i > 0; i--) {
			char c = binary.charAt(i - 1);
			int algorism = c - '0';
			result += Math.pow(2, max - i) * algorism;
		}
		return result;
	}

	/**
	 * 十进制转换为十六进制字符串
	 * 
	 * @param algorism
	 *            int 十进制的数字
	 * @return String 对应的十六进制字符串
	 */
	public static String algorismToHEXString(int algorism) {
		String result = "";
		result = Integer.toHexString(algorism);

		if (result.length() % 2 == 1) {
			result = "0" + result;

		}
		result = result.toUpperCase();

		return result;
	}

	// // bit是判断是双字还是单字
	// public static String algorismToHEXString(int param, int bit) {
	// String result = algorismToHEXString(param);
	// int length = (2 * bit) - result.length();
	// for (int i = 0; i < length; i++) {
	// result = "00" + result;
	// }
	// return result;
	// }

	/**
	 * HEX字符串前补0，主要用于长度位数不足。
	 * 
	 * @param str
	 *            String 需要补充长度的十六进制字符串
	 * @param maxLength
	 *            int 补充后十六进制字符串的长度
	 * @return 补充结果
	 */
	public static  String patchHexString(String str, int maxLength) {
		String temp = "";
		for (int i = 0; i < maxLength - str.length(); i++) {
			temp = "0" + temp;
		}
		str = (temp + str).substring(0, maxLength);
		return str;
	}

	/**
	 * 将一个字符串转换为int
	 * 
	 * @param s
	 *            String 要转换的字符串
	 * @param defaultInt
	 *            int 如果出现异常,默认返回的数字
	 * @param radix
	 *            int 要转换的字符串是什么进制的,如16 8 10.
	 * @return int 转换后的数字
	 */
	public static int parseToInt(String s, int defaultInt, int radix) {
		int i = 0;
		try {
			i = Integer.parseInt(s, radix);
		} catch (NumberFormatException ex) {
			i = defaultInt;
		}
		return i;
	}

	/**
	 * 将一个十进制形式的数字字符串转换为int
	 * 
	 * @param s
	 *            String 要转换的字符串
	 * @param defaultInt
	 *            int 如果出现异常,默认返回的数字
	 * @return int 转换后的数字
	 */
	public static int parseToInt(String s, int defaultInt) {
		int i = 0;
		try {
			i = Integer.parseInt(s);
		} catch (NumberFormatException ex) {
			i = defaultInt;
		}
		return i;
	}

	// /**
	// * 十六进制字符串转为Byte数组,每两个十六进制字符转为一个Byte
	// *
	// * @param hex
	// * 十六进制字符串
	// * @return byte 转换结果
	// */
	// public static byte[] hexStringToByte(String hex) {
	// int max = hex.length() / 2;
	// byte[] bytes = new byte[max];
	// String binarys = ByteUtil.hexStringToBinary(hex);
	// for (int i = 0; i < max; i++) {
	// bytes[i] = (byte) ByteUtil.binaryToAlgorism(binarys.substring(
	// i * 8 + 1, (i + 1) * 8));
	// if (binarys.charAt(8 * i) == '1') {
	// bytes[i] = (byte) (0 - bytes[i]);
	// }
	// }
	// return bytes;
	// }

	/**
	 * 十六进制串转化为byte数组
	 * 
	 * @return the array of byte
	 */
	public static final byte[] hex2byte(String hex)
			throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		char[] arr = hex.toCharArray();
		byte[] b = new byte[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + arr[i++] + arr[i];
			int byteint = Integer.parseInt(swap, 16) & 0xFF;
			b[j] = new Integer(byteint).byteValue();
		}
		return b;
	}
	
	public static final String[] hexStrToArrHex(String hex)
			throws IllegalArgumentException {
		if (hex.length() % 2 != 0) {
			throw new IllegalArgumentException();
		}
		String[] hexarrs = new String[hex.length() / 2];
		for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
			String swap = "" + hex.charAt(i++) + hex.charAt(i);
			hexarrs[j] = swap;
		}
		return hexarrs;
	}

	
	public static final String ArrStrSubHexString(String[] strs,int beginIndex, int sublen) {
		if(strs.length < sublen || strs.length < beginIndex) 
			throw new IndexOutOfBoundsException();
		int len = beginIndex+sublen;
		String str = "";
		for (int i = beginIndex; i < len; i++) {
			str += strs[i];
		}
		return str;
	}
	public static final String ArrStrSubHexStringByReverse(String[] strs,int beginIndex, int sublen) {
		// 2 -- 5 
		if(strs.length < sublen || strs.length < beginIndex) 
			throw new IndexOutOfBoundsException();
		int len = beginIndex+sublen - 1;
		String str = "";
		for (int i = 0; i < sublen; i++) {
			str += strs[len--];
		}
		return str;
	}
	
	/**
	 * 字节数组转换为十六进制字符串
	 * 
	 * @param b
	 *            byte[] 需要转换的字节数组
	 * @return String 十六进制字符串
	 */
	public static final String byte2hex(byte b[]) {
		if (b == null) {
			throw new IllegalArgumentException(
					"Argument b ( byte array ) is null! ");
		}
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	public static final String byte2hex(byte b[],int offset,int len) {
		if (b == null) {
			throw new IllegalArgumentException(
					"Argument b ( byte array ) is null! ");
		}
		String hs = "";
		String stmp = "";
		for (int n = offset; n < len; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}
	
	public static String stringToHex(String value) {
		return ByteUtil.byte2hex(value.getBytes(Charset.forName("GBK")));
	}
	
	public static final String hexStrReverse(String str) {
		if (str.length() % 2 != 0) 
			throw new IndexOutOfBoundsException();
		int len = str.length() -1;
		String result = "";
		for (int i = 0; i < str.length(); i = i + 2) {
			result += Character.toString(str.charAt(len-1))+Character.toString(str.charAt(len));
			len = len - 2;
		}
		return result;
	}
}
