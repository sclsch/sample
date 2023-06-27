package work.hdjava.sample.common.utils;

import java.nio.ByteBuffer;

public class ByteUtil {

	/**
	 * Byte[2] 转换为 int
	 * @param b
	 * @return
	 */
	public static int byte2ArrayToInt(byte[] b) {
		return   b[1] & 0xFF |
				(b[0] & 0xFF) << 8 ;
	}
	/**
	 * Byte[2] 转换为 double
	 * @param arr
	 * @return
	 */
	public static double bytes2Double(byte[] arr) {
		long value = 0;
		for (int i = 0; i < 8; i++) {
			value |= ((long) (arr[i] & 0xff)) << (8 * i);
		}
		return Double.longBitsToDouble(value);
	}
	/**
	 * Byte[2] 转换为 short
	 * @param b
	 * @return
	 */
	public static short byte2short(byte[] b){
		short l = 0;
		for (int i = 0; i < 2; i++) {
			l<<=8; //<<=和我们的 +=是一样的，意思就是 l = l << 8
			l |= (b[i] & 0xff); //和上面也是一样的  l = l | (b[i]&0xff)
		}
		return l;
	}
	/**
	 * int转换为Byte[2]
	 * @param a
	 * @return
	 */
	public static byte[] intToByte2Array(int a) {
		return new byte[] {
				(byte) ((a >> 8) & 0xFF),
				(byte) (a & 0xFF)
		};
	}

	/**
	 * Byte[4] 转换为 int
	 * @param b
	 * @return
	 */
	public static int byteArrayToInt(byte[] b) {  
	    return   b[3] & 0xFF |  
	            (b[2] & 0xFF) << 8 |  
	            (b[1] & 0xFF) << 16 |  
	            (b[0] & 0xFF) << 24;  
	}

	/**
	 * int转换为Byte[4]
	 * @param a
	 * @return
	 */
	public static byte[] intToByteArray(int a) {  
	    return new byte[] {  
	        (byte) ((a >> 24) & 0xFF),  
	        (byte) ((a >> 16) & 0xFF),     
	        (byte) ((a >> 8) & 0xFF),     
	        (byte) (a & 0xFF)  
	    };  
	}

	/**
	 * long 转换为 Byte[8]
	 * @param x
	 * @return
	 */
	public static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(8);
		buffer.putLong(0, x);
		return buffer.array();
	}

	/**
	 * Byte[8] 转换为 long
	 * @param bytes
	 * @return
	 */
	public static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.put(bytes, 0, bytes.length);
		buffer.flip();//need flip
		return buffer.getLong();
	}
	static long bytes2long(byte[] bs)  throws Exception {
		int bytes = bs.length;
		if(bytes > 1) {
			if((bytes % 2) != 0 || bytes > 8) {
				throw new Exception("not support");
			}}
		switch(bytes) {
			case 0:
				return 0;
			case 1:
				return (long)((bs[0] & 0xff));
			case 2:
				return (long)((bs[0] & 0xff) <<8 | (bs[1] & 0xff));
			case 4:
				return (long)((bs[0] & 0xffL) <<24 | (bs[1] & 0xffL) << 16 | (bs[2] & 0xffL) <<8 | (bs[3] & 0xffL));
			case 8:
				return (long)((bs[0] & 0xffL) <<56 | (bs[1] & 0xffL) << 48 | (bs[2] & 0xffL) <<40 | (bs[3] & 0xffL)<<32 |
						(bs[4] & 0xffL) <<24 | (bs[5] & 0xffL) << 16 | (bs[6] & 0xffL) <<8 | (bs[7] & 0xffL));
			default:
				throw new Exception("not support");
		}
		//return 0;
	}


	/**
	 * 从Byte数组中查找子数组所在下标位置
	 * @param sources 原数组
	 * @param src 子数组
	 * @return
	 */
	public static int getByteIndexOf(byte[] sources, byte[] src){
		return getByteIndexOf(sources, src, 0, sources == null ? 0 : sources.length);
	}
	/**
	 * 从Byte数组中查找子数组所在下标位置
	 * @param sources 原数组
	 * @param src 子数组
	 * @param startIndex 开始查找位置
	 * @return
	 */
	public static int getByteIndexOf(byte[] sources, byte[] src, int startIndex){
		return getByteIndexOf(sources, src, startIndex, sources == null ? 0 : sources.length);
	}

	/**
	 * 从Byte数组中查找子数组所在下标位置
	 * @param sources 原数组
	 * @param src 子数组
	 * @param startIndex 开始查找位置
	 * @param endIndex 最后位置
	 * @return
	 */
	public static int getByteIndexOf(byte[] sources, byte[] src, int startIndex, int endIndex){

		if (sources == null || src == null || sources.length == 0 || src.length == 0) {
			return -1;
		}

		if (endIndex > sources.length) {
			endIndex = sources.length;
		}

		int i, j;
		for (i = startIndex; i < endIndex; i++) {
			if (sources[i] == src[0] && i + src.length < endIndex) {
				for (j = 1; j < src.length; j++) {
					if (sources[i + j] != src[j]) {
						break;
					}
				}

				if (j == src.length) {
					return i;
				}
			}
		}
		return -1;
	}
	/**
	 * 字符串转换成十六进制字符串
	 */
	public static String str2HexStr(String str) {
		char[] chars = "0123456789ABCDEF".toCharArray();
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;
		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(chars[bit]);
			bit = bs[i] & 0x0f;
			sb.append(chars[bit]);
		}
		return sb.toString();
	}

	/**
	 * 把16进制字符串转换成字节数组
	 * @param hex
	 * @return byte[]
	 */
	public static byte[] hexStringToByte(String hex) {
		int len = (hex.length() / 2);
		byte[] result = new byte[len];
		char[] achar = hex.toCharArray();
		for (int i = 0; i < len; i++) {
			int pos = i * 2;
			result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
		}
		return result;
	}
	private static int toByte(char c) {
		byte b = (byte) "0123456789ABCDEF".indexOf(c);
		return b;
	}

	/**
	 * 数组转换成十六进制字符串
	 * @param bArray
	 * @return HexString
	 */
	public static final String bytesToHexString(byte[] bArray) {
		StringBuffer sb = new StringBuffer(bArray.length);
		String sTemp;
		for (int i = 0; i < bArray.length; i++) {
			sTemp = Integer.toHexString(0xFF & bArray[i]);
			if (sTemp.length() < 2)
				sb.append(0);
			sb.append(sTemp.toUpperCase());
		}
		return sb.toString();
	}
	/**
	 * byte数组转换为二进制字符串,每个字节以","隔开
	 **/
	public static String bytesToBinString(byte[] b) {
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			result.append(Long.toString(b[i] & 0xff, 2) + ",");
		}
		return result.toString().substring(0, result.length() - 1);
	}

	/**
	 * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
	 */
	public static byte[] getBooleanArray(byte b) {
		byte[] array = new byte[8];
		for (int i = 7; i >= 0; i--) {
			array[i] = (byte)(b & 1);
			b = (byte) (b >> 1);
		}
		return array;
	}
}
