package cn.labsoft.labos.utils;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class MoneyUtils {

	/**
	 * 将数值型的钱数转换为大写
	 * 
	 * @param maxWminF
	 *            最大到万 最小到分 型如：2536.50，10.36，0.23，
	 * @return 返回类型 String 数值型的钱数转换为大写 如 #贰伍叁陆伍零，###壹零叁陆
	 * 
	 */
	public static String moneyToUpperCase(double maxWminF) {
		String[] result = new String[7];
		String resultX = "";

		String[] tempD = split(String.valueOf(maxWminF), '.');

		String tempDF = tempD[0];
		String tempDB = tempD[1];

		switch (tempDF.length()) {
		case 1:
			tempDF = "####" + tempDF;
			break;
		case 2:
			tempDF = "###" + tempDF;
			break;
		case 3:
			tempDF = "##" + tempDF;
			break;
		case 4:
			tempDF = "#" + tempDF;
			break;
		default:
			break;
		}
		switch (tempDB.length()) {
		case 1:
			tempDB = tempDB + "0";
			break;
		default:
			break;
		}

		for (int i = 0; i < 5; i++) {
			result[i] = chDigUpCast((tempDF.charAt(i)));
		}
		for (int i = 0; i < 2; i++) {
			result[5 + i] = chDigUpCast((tempDB.charAt(i)));
		}

		for (int i = 0; i < 7; i++) {
			resultX = resultX + result[i];
		}
		return resultX;
	}

	/**
	 * 
	 * 用指定的符号将源字符串拆分成单个字符
	 * 
	 * @param source
	 *            需要被拆分的字符串
	 * @param useChar
	 *            指定符号
	 * @return 返回类型 String[] 返回拆分后的字符数组
	 */
	@SuppressWarnings("unchecked")
	private static String[] split(String source, char useChar) {
		List<String> list = new ArrayList<String>();
		String sub;
		String[] result;

		if (source.charAt(0) == useChar)
			source = source.substring(1, source.length());
		if (source.charAt(source.length() - 1) == useChar)
			source = source.substring(0, source.length() - 1);

		int start = 0;
		int end = source.indexOf(useChar);
		while (end > 0) {
			sub = source.substring(start, end);
			list.add(sub);
			start = end + 1;
			end = source.indexOf(useChar, start);
		}

		sub = source.substring(start, source.length());
		list.add(sub);

		result = new String[list.size()];

		Iterator iter = list.iterator();
		int i = 0;
		while (iter.hasNext()) {
			result[i++] = (String) iter.next();
		}
		return result;
	}

	/**
	 * 将0-9，#，-形式的数值转换为大写形式
	 * 
	 * @param d
	 *            需要转换的数值字符
	 * @return 返回类型 String 0-9的数值转换为大写，如：贰伍叁陆伍零壹
	 * 
	 */
	public static String chDigUpCast(char d) {
		String result = "";
		switch (d) {
		case '0':
			result = "零";
			break;
		case '1':
			result = "壹";
			break;
		case '2':
			result = "贰";
			break;
		case '3':
			result = "叁";
			break;
		case '4':
			result = "肆";
			break;
		case '5':
			result = "伍";
			break;
		case '6':
			result = "陆";
			break;
		case '7':
			result = "柒";
			break;
		case '8':
			result = "捌";
			break;
		case '9':
			result = "玖";
			break;
		case '#':
			result = "#";
			break;
		case '-':
			result = "-";
			break;
		default:
			break;
		}

		return result;
	}

	/**
	 * 将大写形式的数值转换为0-9，#，-形式
	 * 
	 * @param d
	 *            需要转换的大写形式数值
	 * @return 返回类型 String 大写数值转换为0-9的数字，如：贰-2
	 * 
	 */
	private static String chUpCastDig(char d) {
		String result = "";
		switch (d) {
		case '零':
			result = "0";
			break;
		case '壹':
			result = "1";
			break;
		case '贰':
			result = "2";
			break;
		case '叁':
			result = "3";
			break;
		case '肆':
			result = "4";
			break;
		case '伍':
			result = "5";
			break;
		case '陆':
			result = "6";
			break;
		case '柒':
			result = "7";
			break;
		case '捌':
			result = "8";
			break;
		case '玖':
			result = "9";
			break;
		case '#':
			result = "-";
			break;
		default:
			break;
		}

		return result;
	}

	@SuppressWarnings("unused")
	private static String moneyUpperCaseDig(String str) {
		String tempStr = "";
		for (int i = 0; i < str.length(); i++) {
			tempStr = tempStr + chUpCastDig(str.charAt(i));
		}
		return tempStr;
	}

	/**
	 * 
	 * 将double形式的钱数转换为string的形式
	 * 
	 * @param d
	 *            double型的钱数
	 * @return 返回类型 转换后的钱数形式
	 */
	public static String getMoney(double d) {
		NumberFormat nf = NumberFormat.getInstance(Locale.JAPANESE);
		String b;
		b = nf.format(d);
		return b;
	}

}
