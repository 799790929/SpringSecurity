package cn.sf.ss.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class SSUtil {
	public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String test = encoderPwdByMd5("super");
		System.out.println(test);
	}
	
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	private static String getFormattedText(byte[] bytes)
	  {
	    StringBuilder buf = new StringBuilder(bytes.length * 2);

	    for (int j = 0; j < bytes.length; j++) {
	      buf.append(HEX_DIGITS[(bytes[j] >> 4 & 0xF)]);
	      buf.append(HEX_DIGITS[(bytes[j] & 0xF)]);
	    }
	    return buf.toString();
	  }
	
	/**
	 * MD5加密
	 * 
	 * @param str
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static final String encoderPwdByMd5(String str)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		BASE64Encoder base64en = new BASE64Encoder();
		// 加密后的字符串
		String newstr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		return newstr;
	}
	
	public static String encoderPwdByMd52(String s) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String strBuf = s;
		if (s.length() % 2 == 0) {
			strBuf = s.substring(s.length() / 2, s.length());
			strBuf = strBuf + s.substring(0, s.length() / 2);
			s = strBuf;
		} else {
			strBuf = s.substring(s.length() / 2, s.length() / 2 + 1);
			strBuf = strBuf + s.substring(s.length() / 2 + 1, s.length());
			strBuf = strBuf + s.substring(0, s.length() / 2);
			s = strBuf;
		}
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
