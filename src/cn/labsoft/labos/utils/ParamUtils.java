package cn.labsoft.labos.utils;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : ParamUtils </strong>. <br>
 * <strong>Description : 参数转化工具类</strong> <br>
 * <strong>Create on : 2009-12-24 上午09:25:55 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author CharlesXi<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class ParamUtils {

	/**
	 * ParamUtils的构造方法，设定文件
	 */
	public ParamUtils() {
	}

	/**
	 * 
	 * 网页中的表单使用POST方法提交时，数据内容的类型是 application/x-www-form-urlencoded，这种类型会：
	 * 1.字符"a"-"z"，"A"-"Z"，"0"-"9"，"."，"-"，"*"，和"_" 都不会被编码; 2.将空格转换为加号 (+) ;
	 * 3.将非文本内容转换成"%xy"的形式,xy是两位16进制的数值; 4.在每个 name=value 对之间放置 & 符号。
	 * URLEncoder类包含将字符串转换为application/x-www-form-urlencoded MIME 格式的静态方法。
	 * 
	 * @param src
	 *            将要编码的字串
	 * @return 返回类型 String 编码后的字串
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static String encode(String src) {
		if (src == "" || src == null) {
			return "";
		} else {
			return URLEncoder.encode(src);
		}
	}

	/**
	 * 
	 * 网页中的表单使用POST方法提交时，数据内容的类型是 application/x-www-form-urlencoded，这种类型会：
	 * 1.字符"a"-"z"，"A"-"Z"，"0"-"9"，"."，"-"，"*"，和"_" 都不会被编码; 2.将空格转换为加号 (+) ;
	 * 3.将非文本内容转换成"%xy"的形式,xy是两位16进制的数值; 4.在每个 name=value 对之间放置 & 符号。
	 * URLEncoder类包含将字符串转换为application/x-www-form-urlencoded MIME 格式的静态方法。
	 * 
	 * @param src
	 *            将要解码的字串
	 * @return 返回类型 String 解码后的字串
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static String decode(String src) {
		if (src == "" || src == null) {
			return "";
		} else {
			return URLDecoder.decode(src);
		}
	}

	private static String A2C(String s) {
		return s;
		/*
		 * char[] orig = s.toCharArray(); byte[] dest = new byte[orig.length];
		 * for (int i = 0; i < orig.length; i++) { dest[i] = (byte) (orig[i] &
		 * 0xFF); } try { ByteToCharConverter toChar =
		 * ByteToCharConverter.getConverter("GBK"); return new
		 * String(toChar.convertAll(dest)); } catch (Exception e) { (e); return
		 * s; }
		 */
	}

	/**
	 * 
	 * 获取参数数组字符串参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            参数名称
	 * @return 返回类型 String[] 参数值数组
	 * 
	 */
	public static String[] getParameterValues(HttpServletRequest request,
			String paramName) {
		return getParameterValues(request, paramName, false);
	}

	/**
	 * 
	 * 获取参数数组字符串参数
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @param emptyStringsOK
	 *            返回参数值是否为空的布尔值
	 * @return 返回类型 String[] 参数值（数组形式）如果没有找到参数时返回空字符串(emptyStringsOK=true)
	 * 
	 */
	public static String[] getParameterValues(HttpServletRequest request,
			String paramName, boolean emptyStringsOK) {
		String temp[] = request.getParameterValues(paramName);
		if (temp != null) {
			if (temp.length == 0 && !emptyStringsOK) {
				return null;
			} else {
				for (int i = 0; i < temp.length; i++) {
					temp[i] = A2C(temp[i]);
				}
				return temp;
			}
		} else if (!emptyStringsOK) {
			return null;
		} else {
			return new String[] {};
		}
	}

	/**
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @return 返回类型 String 参数值
	 * 
	 */
	public static String getParameter(HttpServletRequest request,
			String paramName) {
		return getParameter(request, paramName, false);
	}

	/**
	 * 
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @param defaultString
	 *            如果为空返回defaultString
	 * @return 返回类型 String 参数值
	 * 
	 */
	public static String getParameter(HttpServletRequest request,
			String paramName, String defaultString) {
		String str = getParameter(request, paramName, true);
		if (str.equals(""))
			str = defaultString;
		return str;
	}

	/**
	 * 
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @param emptyStringsOK
	 *            返回参数值是否为空的布尔值
	 * @return 返回类型 String 参数值
	 * 
	 */
	public static String getParameter(HttpServletRequest request,
			String paramName, boolean emptyStringsOK) {
		String temp = request.getParameter(paramName);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return A2C(temp);
			}
		} else if (!emptyStringsOK) {
			return null;
		} else {
			return "";
		}
	}

	/**
	 * 
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @return 返回类型 boolean 返回参数值是否为空
	 * 
	 */
	public static boolean getBooleanParameter(HttpServletRequest request,
			String paramName) {
		String temp = request.getParameter(paramName);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取整型参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @param defaultNum
	 *            如果为空返回defaultNum
	 * @return 返回类型 int 参数值
	 * @throws GlobalException 
	 * 
	 */
	public static int getIntParameter(HttpServletRequest request,
			String paramName, int defaultNum) throws GlobalException {
		String temp = request.getParameter(paramName);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
				throw new GlobalException("" + ignored.getMessage());
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 
	 * 获取长整型参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @param defaultNum
	 *            如果为空返回defaultNum
	 * @return 返回类型 int 参数值
	 * @throws GlobalException 
	 * 
	 */
	public static long getLongParameter(HttpServletRequest request,
			String paramName, long defaultNum) throws GlobalException {
		String temp = request.getParameter(paramName);
		if (temp != null && !temp.equals("")) {
			long num = defaultNum;
			try {
				num = Long.parseLong(temp);
			} catch (Exception ignored) {
				throw new GlobalException("" + ignored.getMessage());
			}
			return num;
		} else {
			return defaultNum;
		}
	}

	/**
	 * 
	 * 获取checkbox类型的布尔值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param paramName
	 *            要获取的参数
	 * @return 返回类型 boolean 布尔值
	 * 
	 */
	public static boolean getCheckboxParameter(HttpServletRequest request,
			String paramName) {
		String temp = request.getParameter(paramName);
		if (temp != null && temp.equals("on")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param attribName
	 *            要获取的参数
	 * @return 返回类型 String 参数值
	 * 
	 */
	public static String getAttribute(HttpServletRequest request,
			String attribName) {
		return getAttribute(request, attribName, false);
	}

	/**
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param attribName
	 *            要获取的参数
	 * @param emptyStringsOK
	 *            如果为空返回defaultNum
	 * @return 返回类型 String 参数值
	 * 
	 */
	public static String getAttribute(HttpServletRequest request,
			String attribName, boolean emptyStringsOK) {
		String temp = (String) request.getAttribute(attribName);
		if (temp != null) {
			if (temp.equals("") && !emptyStringsOK) {
				return null;
			} else {
				return A2C(temp);
			}
		} else if (!emptyStringsOK) {
			return null;
		} else {
			return "";
		}
	}

	/**
	 * 获取参数值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param attribName
	 *            要获取的参数
	 * @return 返回类型 boolean 返回获取的参数值是否为空
	 * 
	 */
	public static boolean getBooleanAttribute(HttpServletRequest request,
			String attribName) {
		String temp = (String) request.getAttribute(attribName);
		if (temp != null && temp.equals("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 获取属性的整型值
	 * 
	 * @param request
	 *            HttpServletRequest对象
	 * @param attribName
	 *            要获取的参数值属性名
	 * @param defaultNum
	 *            该参数属
	 * @return 返回类型 int 整型值
	 * @throws GlobalException 
	 * 
	 */
	public static int getIntAttribute(HttpServletRequest request,
			String attribName, int defaultNum) throws GlobalException {
		String temp = (String) request.getAttribute(attribName);
		if (temp != null && !temp.equals("")) {
			int num = defaultNum;
			try {
				num = Integer.parseInt(temp);
			} catch (Exception ignored) {
				throw new GlobalException("" + ignored.getMessage());
			}
			return num;
		} else {
			return defaultNum;
		}
	}

}
