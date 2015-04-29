package cn.labsoft.labos.utils;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 
 * <strong>Title : NumberUtils </strong>. <br>
 * <strong>Description : 数据工具</strong> <br>
 * <strong>Create on : 2009-12-23 下午05:47:32 </strong>. <br>
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
public class NumberUtils {
	/**
	 * 把指定Object对象转化为Integer对象
	 * 
	 * @param obj
	 *            指定Object对象
	 * @return 返回类型 Integer 转化后的Integer对象
	 * 
	 */
	public static Integer toInteger(Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		} else {
			return new Integer(toNumber(obj).intValue());
		}
	}

	/**
	 * 把指定Object对象转化为Float对象
	 * 
	 * @param obj
	 *            指定Object对象
	 * @return 返回类型 Float 转化后的Float对象
	 * 
	 */
	public static Float toFloat(Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		} else {
			return new Float(toNumber(obj).floatValue());
		}

	}

	/**
	 * 把指定Object对象转化为Short对象
	 * 
	 * @param obj
	 *            指定Object对象
	 * @return 返回类型 Short 转化后的Short对象
	 * 
	 */
	public static Short toShort(Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		} else {
			return new Short(toNumber(obj).shortValue());
		}

	}

	/**
	 * 把指定Object对象转化为Long对象
	 * 
	 * @param obj
	 *            指定Object对象
	 * @return 返回类型 Long 转化后的Long对象
	 * 
	 */
	public static Long toLong(Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		} else {
			return new Long(toNumber(obj).longValue());
		}

	}

	/**
	 * 把指定Object对象转化为Double对象
	 * 
	 * @param obj
	 *            指定Object对象
	 * @return 返回类型 Double 转化后的Double对象
	 * 
	 */

	public static Double toDouble(Object obj) {
		if (obj == null || obj.equals("")) {
			return null;
		} else {
			return new Double(toNumber(obj).doubleValue());
		}

	}

	/**
	 * 把指定Object对象转化为Number对象
	 * 
	 * @param obj
	 *            指定Object对象
	 * @return 返回类型 Number 转化后的Number对象
	 * 
	 */
	public static Number toNumber(Object obj) {
		if (obj == null) {
			return null;
		}
		if (obj instanceof Number) {
			return (Number) obj;
		}
		try {
			NumberFormat parser = getNumberFormat(Locale.getDefault());
			return parser.parse(String.valueOf(obj));
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 把指定locale对象转化为NumberFormat对象
	 * 
	 * @param locale
	 *            指定locale对象
	 * @return 返回类型 NumberFormat 转化后的NumberFormat对象
	 * 
	 */
	private static NumberFormat getNumberFormat(Locale locale) {
		return NumberFormat.getNumberInstance(locale);
	}

	/**
	 * 将指定double类型的数据四舍五入,并保留两位小数
	 * 
	 * @param instance
	 *            指定double类型的数据
	 * @return 返回类型 double 指定double类型的数据四舍五入,并保留两位小数
	 * 
	 */
	public static double changeToDecimal2(double instance) {
		BigDecimal bd = new BigDecimal(instance);
		bd = bd.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		instance = bd.doubleValue();
		return instance;
	}
}
