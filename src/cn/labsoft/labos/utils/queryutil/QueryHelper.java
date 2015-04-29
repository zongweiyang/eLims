package cn.labsoft.labos.utils.queryutil;

import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import cn.labsoft.labos.utils.DateUtils;

public class QueryHelper {
	public QueryHelper() {
	}

	/**
	 * 
	 * 通过用户定义的类型获得系统类型并组装为查询条件的方法
	 * 
	 * @param queryCond
	 *            用户定义查询列
	 * @param temp
	 *            用户定义条件
	 * @param columnName
	 *            字段名
	 * @return 返回类型 String 把用户定义的日期类型转换过的系统类类型
	 */
	@SuppressWarnings("static-access")
	public String getDateMappingToString(QueryCond queryCond, String temp,
			String columnName) {
		DateUtils dateUtils = new DateUtils();
		String tempDateCondition = "";
		// 如果等于 昨天 就用like 如果等于 上个月 上周 要取两头 如果大于今天 昨天 明天 上个月 上周要结束始位置取... 如果小于 上月
		// 上周要从开始位置取
		if ((queryCond.getOperate().equals("=") || queryCond.getOperate()
				.equals("like"))) {

			if (temp.trim().equals("今天")) {
				dateUtils.calcToday(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				temp = dateUtils.begin;
				tempDateCondition = columnName + "   like " + " '%" + temp
						+ "%'";
			} else if (temp.trim().equals("昨天")) {

				dateUtils.calcYesterday(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				temp = dateUtils.begin;
				tempDateCondition = columnName + "   like " + " '%" + temp
						+ "%'";
			} else if (temp.trim().equals("上周")) {

				dateUtils.calcLastWeek(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = "(" + columnName + ">=  '"
						+ dateUtils.begin + "' and " + columnName + " <='"
						+ dateUtils.end + "')";
				// 取出两头
			} else if (temp.trim().equals("上月")) {
				dateUtils.calcLastMonth(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = "(" + columnName + ">=  '"
						+ dateUtils.begin + "' and " + columnName + " <='"
						+ dateUtils.end + "')";
				// 取出两头
			} else {

				tempDateCondition = temp;
			}

		}
		if (queryCond.getOperate().equals("<=")
				|| queryCond.getOperate().equals(">=")
				|| queryCond.getOperate().equals("<")
				|| queryCond.getOperate().equals(">")) {
			if (temp.trim().equals("今天") || temp.trim().equals("昨天")) {

				if (temp.trim().equals("今天")) {

					dateUtils.calcToday(dateUtils.begin, dateUtils.end,
							dateUtils.now, new GregorianCalendar());
					temp = dateUtils.begin;

					tempDateCondition = columnName + queryCond.getOperate()
							+ "  '" + temp + "'";
				}
				if (temp.trim().equals("昨天")) {
					dateUtils.calcYesterday(dateUtils.begin, dateUtils.end,
							dateUtils.now, new GregorianCalendar());
					temp = dateUtils.begin;

					tempDateCondition = columnName + queryCond.getOperate()
							+ " '" + temp + "'";
				}

			} else if (temp.trim().equals("上周")
					&& (queryCond.getOperate().equals("<="))) {
				dateUtils.calcLastWeek(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.end
						+ "'";

			} else if (temp.trim().equals("上月")
					&& (queryCond.getOperate().equals("<="))) {

				dateUtils.calcThisMonth(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.end
						+ "'";
				// 取出两头
			} else if (temp.trim().equals("上周")
					&& (queryCond.getOperate().equals("<"))) {
				dateUtils.calcLastWeek(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.begin
						+ "'";

			} else if (temp.trim().equals("上月")
					&& (queryCond.getOperate().equals("<"))) {
				dateUtils.calcLastMonth(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.begin
						+ "'";
				// 取出两头
			} else if (temp.trim().equals("上周")
					&& (queryCond.getOperate().equals(">="))) {
				dateUtils.calcLastWeek(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.begin
						+ "'";

			} else if (temp.trim().equals("上月")
					&& (queryCond.getOperate().equals(">="))) {
				dateUtils.calcLastMonth(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.begin
						+ "'";
				// 取出两头
			} else if (temp.trim().equals("上周")
					&& (queryCond.getOperate().equals(">"))) {
				dateUtils.calcLastWeek(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.end
						+ "'";

			} else if (temp.trim().equals("上月")
					&& (queryCond.getOperate().equals(">"))) {
				dateUtils.calcThisMonth(dateUtils.begin, dateUtils.end,
						dateUtils.now, new GregorianCalendar());
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + dateUtils.end
						+ "'";
				// 取出两头
			} else {
				tempDateCondition = columnName + "    "
						+ queryCond.getOperate() + "       '" + temp + "'";
			}
		}

		return tempDateCondition;
	}

	/**
	 * 
	 * 通过传入的查询条件对象 把他组装成字符串
	 * 
	 * @param queryCond
	 *            查询条件对象
	 * @return 返回类型 String 解析过后的字符串
	 */
	public String getConds(QueryCond queryCond) {
		String tempDateCondition = "";
		String tempString = "";

		if ("likeLeft".equalsIgnoreCase(queryCond.getOperate())) {
			tempString = " and " + queryCond.getFieldName() + " like '"
					+ queryCond.getFieldVal() + "%' ";
			return tempString;
		} else if ("in".equalsIgnoreCase(queryCond.getOperate())) {
			tempString = " and " + queryCond.getFieldName() + " in ("
					+ queryCond.getFieldVal() + ") ";
			return tempString;
		} else if ("not in".equalsIgnoreCase(queryCond.getOperate())) {
			tempString = " and " + queryCond.getFieldName() + " not in ("
					+ queryCond.getFieldVal() + ") ";
			return tempString;
		} else if ("is".equalsIgnoreCase(queryCond.getOperate())) {
			tempString = " and " + queryCond.getFieldName() + " "
					+ queryCond.getOperate() + " " + queryCond.getFieldVal();
			return tempString;
		}
		String[] temp = queryCond.getFieldVal().trim().split(",");
		for (int k = 0; k < temp.length; k++) {// 如果是一个字段里 写了几个值 并且用逗号分隔
			// 这里要完全拆分
			String value = "";
			if (queryCond.getFieldType().equals("String")) {
				if (queryCond.getOperate().toLowerCase().equals("like")) {
					value = "  '%" + temp[k] + "%'";
				} else {
					value = "'" + temp[k] + "'";
				}

			}
			if (queryCond.getFieldType().equals("int")) {

				value = temp[k];
			}
			if (queryCond.getFieldType().equals("date")) {// 如果是日期类型
				tempDateCondition = this.getDateMappingToString(queryCond,
						temp[k].toString(), queryCond.getFieldName());
				if (k == 0)// 为了避免查询出现重复 要把这几个条件作为一个条件查询,所以要两头加扩号
				{
					tempDateCondition = "(" + tempDateCondition;
				}
				if (k == temp.length - 1) {
					tempDateCondition = tempDateCondition + ")";
				}

			}
			if (tempString.equals("")) {// 如果是一个字段内多个等号 用or 把他们拼装起来
				if (queryCond.getFieldType().equals("date"))// 因为日期类型已经拼装好了
				// 所以直接复制就行
				{
					if (tempDateCondition != null) {
						if (!tempDateCondition.equals("")) {

							tempString = tempDateCondition;
						}
					}
				} else {
					tempString = queryCond.getFieldName() + "  "
							+ queryCond.getOperate() + value;
				}
			} else {

				if (queryCond.getFieldType().equals("date")) {
					if (tempDateCondition != null) {

						if (!tempDateCondition.equals("")) {
							tempString = tempString + " or   "
									+ tempDateCondition;
						}
					}
				} else {
					tempString = tempString + " or   "
							+ queryCond.getFieldName() + queryCond.getOperate()
							+ " " + value;
				}
			}
		}
		// tempString="("+tempString+")";

		return tempString;
	}

	/**
	 * 
	 * 通过传入的查询条件对象集合 把他们组装成字符串
	 * 
	 * @param queryConds
	 *            查询条件对象集合
	 * @return 返回类型 String 解析过后的字符串
	 */
	@SuppressWarnings("unchecked")
	public String getQueryWhere(List queryConds) {
		String SQLResult = "";
		String temp = "";
		for (int i = 0; i < queryConds.size(); i++) {
			QueryCond queryCond = new QueryCond();
			queryCond = (QueryCond) queryConds.get(i);
			temp = this.getConds(queryCond);
			if (temp.equals(""))
				SQLResult = temp.toString();

			else
				SQLResult = SQLResult + "  and   " + temp.toString() + "      ";
		}

		return SQLResult;
	}

	@SuppressWarnings("unchecked")
	public static String toOrQuery(Collection Querys) {
		String rtnStr = toQuery(Querys);
		if (rtnStr == null) {
			rtnStr = "";
		}
		if (rtnStr.length() > 1) {
			rtnStr = " or ( " + rtnStr + " )";
		}
		return rtnStr;
	}

	@SuppressWarnings("unchecked")
	public static String toAndQuery(Collection Querys) {
		String rtnStr = toQuery(Querys);
		if (rtnStr == null) {
			rtnStr = "";
		}
		if (rtnStr.length() > 1) {
			rtnStr = " and " + rtnStr + "";
		}
		return rtnStr;
	}

	@SuppressWarnings("unchecked")
	public static String toQuery(Collection Querys) {
		if (Querys == null) {
			return "";
		} else if (Querys.size() < 1) {
			return "";
		}
		String rtnStr = "";
		Iterator it = Querys.iterator();
		QueryCond queryCond = null;
		while (it != null && it.hasNext()) {
			queryCond = (QueryCond) it.next();
			if (queryCond.getFieldVal() == null
					|| "".equals(queryCond.getFieldVal())) {
				continue;
			}
			if ("YYYY_MM_DD_HH24_MI_SS".equalsIgnoreCase(queryCond
					.getFieldType())
					|| "YYYY_MM_DD_HH_MI_SS".equalsIgnoreCase(queryCond
							.getFieldType())) {
				rtnStr += " and "
						+ queryCond.getFieldName()
						+ " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM_DD_HH24_MI_SS(queryCond.getFieldVal());
			} else if ("YYYY_MM".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM(queryCond.getFieldVal());
			} else if ("date".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM_DD(queryCond.getFieldVal());
			} else if ("YYYY_MM_DD".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Date_YYYY_MM_DD(queryCond.getFieldVal());

			} else if ("YYYY".equalsIgnoreCase(queryCond.getFieldType())) {
				rtnStr += " and " + queryCond.getFieldName() + " "
						+ queryCond.getOperate()
						+ to_Char_YYYY(queryCond.getFieldVal());
			} else if ("string".equalsIgnoreCase(queryCond.getFieldType())) {
				if ("like".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " like '%"
							+ queryCond.getFieldVal() + "%' ";
				} else if ("likeLeft".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " like '"
							+ queryCond.getFieldVal() + "%' ";
				} else if ("in".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " in ("
							+ queryCond.getFieldVal() + ") ";
				} else if ("not in".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " not in ("
							+ queryCond.getFieldVal() + ") ";
				} else if ("is".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " "
							+ queryCond.getOperate() + " "
							+ queryCond.getFieldVal();
				} else {
					rtnStr += " and " + queryCond.getFieldName() + " "
							+ queryCond.getOperate() + " '"
							+ queryCond.getFieldVal() + "' ";
				}
			} else if ("number".equalsIgnoreCase(queryCond.getFieldType())) {
				if ("in".equalsIgnoreCase(queryCond.getOperate())) {
					rtnStr += " and " + queryCond.getFieldName() + " in ("
							+ queryCond.getFieldVal() + ") ";
				} else {
					rtnStr += " and " + queryCond.getFieldName() + " "
							+ queryCond.getOperate() + " "
							+ queryCond.getFieldVal() + " ";
				}
			}

		}
		if (rtnStr.length() > 1) {
			rtnStr = rtnStr.substring(5);
		}
		return rtnStr;
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd的日期型数据转换成字符串型SQL select 语句 *
	 * 
	 * @param colName
	 *            数据库字段名
	 * @param pattern
	 *            时间模板例如：yyyy-mm-dd hh24:mi:ss
	 * @return SQL select 语句
	 */
	public static String to_Char(String colName, String pattern) {
		return " to_char(" + colName + ",'" + pattern + "') ";
	}

	public static String to_Date(String dateVal, String pattern) {
		return " to_date('" + dateVal + "','" + pattern + "') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY(String colName) {
		return " to_char(" + colName + ",'yyyy') ";
	}

	public static String to_Date_YYYY(String dateVal) {
		return " to_date('" + dateVal + "','yyyy') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY_MM_DD(String colName) {
		return " to_char(" + colName + ",'yyyy-mm-dd') ";
	}

	public static String to_Date_YYYY_MM_DD(String dateVal) {
		return " to_date('" + dateVal + "','yyyy-mm-dd') ";
	}

	/**
	 * 产生将日期时间样式为hh:mi:ss的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_HH24_MI_SS(String colName) {
		return " to_char(" + colName + ",'hh24:mi:ss') ";
	}

	public static String to_Date_HH24_MI_SS(String dateVal) {
		return " to_date('" + dateVal + "','hh24:mi:ss') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm-dd hh:mi:ss的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY_MM_DD_HH24_MI_SS(String colName) {
		return " to_char(" + colName + ",'yyyy-mm-dd hh24:mi:ss') ";
	}

	public static String to_Date_YYYY_MM_DD_HH24_MI_SS(String dateVal) {
		return " to_date('" + dateVal + "','yyyy-mm-dd hh24:mi:ss') ";
	}

	/**
	 * 产生将日期时间样式为yyyy-mm的日期型数据转换成字符串型SQL select 语句
	 * 
	 * @param colName
	 *            数据库字段名
	 * @return SQL select 语句
	 */
	public static String to_Char_YYYY_MM(String colName) {
		return " to_char(" + colName + ",'yyyy-mm') ";
	}

	public static String to_Date_YYYY_MM(String dateVal) {
		return " to_date('" + dateVal + "','yyyy-mm') ";
	}

}
