package com.just.lollipop.bbs.util.str;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.*;

/**
 * <p>
 * 实现字符转换的工具类
 * </p>
 * @author zhongliwen
 * @email zhongliwen1981@163.com
 */
public abstract class ByteTools {

	private static final String systemCharSet = "UTF-8";

	public ByteTools() {
	}

	/**
	 * 将字符串由standardFrom编码转换为standarTo编码的格式
	 * @param input
	 * @param standardFrom
	 * @param standardTo
	 * @return
	 */
	public static String codeConver(String input, String standardFrom,
			String standardTo) {
		try {
			byte[] bytes = input.getBytes(standardFrom);
			return new String(bytes, standardTo);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] codeConver(String src, int msgFormat) {
		try {
			switch (msgFormat) {
			case 8:
				return src.getBytes("UTF-16BE");
			case 15:
				return src.getBytes("GBK");
			case 0:
			case 3:
			case 4:
				return src.getBytes("US-ASCII");
			}
		} catch (Exception e) {
			System.out.println("字符串编码转换错误: " + e);
		}
		return src.getBytes();
	}

	public static String StringConver(byte[] bytes, int msgFormat) {
		try {
			switch (msgFormat) {
			case 8:
				return new String(bytes, "UTF-16BE");
			case 15:
				return new String(bytes, "GBK");
			case 0:
			case 3:
			case 4:
				return new String(bytes, "US-ASCII");
			}
		} catch (Exception e) {
			System.out.println("字符串编码转换错误: " + e);
		}
		return null;
	}

	/**
	 * 将String转换成固定长度的byte数组
	 * @param src
	 * @param len
	 * @return
	 */
	public static byte[] getBytes(String src, int len) {
		byte[] result = new byte[len];
		byte[] temp = src.getBytes();
		System.arraycopy(temp, 0, result, 0, temp.length);
		return result;
	}

	/**
	 * 将String数组转换成固定长度的byte数组
	 * @param src
	 * @param len
	 * @return
	 */
	public static byte[] getBytes(String[] src, int len) {
		byte[] result = new byte[len * src.length];
		for (int i = 0; i < src.length; i++) {
			try {
				byte[] temp = URLDecoder.decode(src[i], systemCharSet)
						.getBytes("GB18030");
				System.arraycopy(temp, 0, result, i * len, temp.length);
			} catch (Exception e) {
				System.out.println("将String数组转换成固定长度的byte数组错误:" + e);
			}
		}
		return result;
	}

	/**
	 * 获取MD5加密码后的byte数组
	 * @param b
	 * @param len
	 * @return
	 */
	public static byte[] getMD5(byte[] b, int len) {
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(b, 0, len);
			return digest.digest();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * byte[] 转换成整型
	 * @param b
	 * @return
	 */
	public static int byte2Int(byte b[]) {
		if (b.length == 4) {
			int mask = 0xff;
			int temp = 0;
			int res = 0;
			for (int i = 0; i < 4; i++) {
				res <<= 8;
				temp = b[i] & mask;
				res |= temp;
			}
			return res;
		} else if (b.length < 4) {
			int i1, i2, i3;
			try {
				i1 = (int) (b[0]);
				if (i1 < 0)
					i1 = (int) (256 + i1);
			} catch (Exception e) {
				i1 = 0;
			}
			try {
				i2 = (int) (b[1]);
				if (i2 < 0)
					i2 = (int) (256 + i2);
			} catch (Exception e) {
				i2 = 0;
			}
			try {
				i3 = (int) (b[2]);
				if (i3 < 0)
					i3 = (int) (256 + i3);
			} catch (Exception e) {
				i3 = 0;
			}
			return (int) (65536 * i3 + 256 * i2 + i1);
		} else {
			return 0;
		}
	}

	/**
	 * 将指定长度的byte数组转换成整型
	 * @param b
	 * @param start
	 * @param len
	 * @return
	 */
	public static int byte2Int(byte[] b, int start, int len) {
		byte[] dat = new byte[len];
		System.arraycopy(b, start, dat, 0, len);
		return byte2Int(dat);
	}

	/**
	 * 整型转换成byte[]
	 * @param i
	 * @return
	 */
	public static byte[] int2Byte(int i) {
		byte abyte0[] = new byte[4];
		abyte0[3] = (byte) i;
		i >>>= 8;
		abyte0[2] = (byte) i;
		i >>>= 8;
		abyte0[1] = (byte) i;
		i >>>= 8;
		abyte0[0] = (byte) i;
		return abyte0;
	}

	/**
	 * 整型转换成byte[]
	 * @param i
	 * @return
	 */
	public static byte[] intToByte(int i) {
		byte[] bytes = new byte[4];
		bytes[3] = (byte) (0xff & i);
		bytes[2] = (byte) ((0xff00 & i) >> 8);
		bytes[1] = (byte) ((0xff0000 & i) >> 16);
		bytes[0] = (byte) ((0xff000000 & i) >> 24);
		return bytes;
	}

	/**
	 * 将指定长度的整型转换成byte数组
	 * @param i
	 * @param len
	 * @return
	 */
	public static byte[] int2Byte(int i, int len) {
		byte abyte0[] = new byte[len];
		for (int j = 0; j < len; j++) {
			abyte0[len - j - 1] = (byte) i;
			i >>>= 8;
		}
		return abyte0;
	}

	/**
	 * 获取当前时间,格式MMDDHHMMSS
	 * @return
	 */
	public static String getCurrentTimpStamp() {
		SimpleDateFormat mat = new SimpleDateFormat("MMddHHmmss");
		return mat.format(new Date());
	}

	/**
	 * 返回一个新的byte数组，索引从intStart开始，到intStart+length
	 * @param bytes
	 * @param intStart
	 * @param length
	 * @return
	 */
	public static byte[] subByte(byte[] bytes, int intStart, int length) {
		byte[] result = new byte[length];
		for (int i = 0; i < length; i++)
			result[i] = bytes[intStart + i];
		return result;
	}
}