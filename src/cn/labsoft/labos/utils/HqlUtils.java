package cn.labsoft.labos.utils;

import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * Title: hql
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2008
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author Ant
 * @version 1.0
 */

public class HqlUtils {
	/**
	 * like
	 */
	public final static String LIKE = ",like";
	private final static String SLIKE = "like";
	/**
	 * =
	 */
	public final static String AMOUNT = ",=";
	private final static String SAMOUNT = "=";
	/**
	 * !=
	 */
	public final static String NOTAMOUNT = ",!=";
	private final static String SNOTAMOUNT = "!=";
	/**
	 * >
	 */
	public final static String BIG = ",>";
	/**
	 * >=
	 */
	public final static String BIGAMOUNT = ",>=";
	/**
	 * <
	 */
	public final static String SMALL = ",<";
	/**
	 * <=
	 */
	public final static String SMALLAMOUNT = ",<=";

	/**
	 * is null
	 */
	public final static String ISNULL = ", is null ";
	private final static String SISNULL = " is null ";

	/**
	 * is not null
	 */
	public final static String ISNOTNULL = ", is not null ";
	private final static String SISNOTNULL = " is not null ";

	/**
	 * 
	 * 拼装HQL
	 * 
	 * @param field
	 *            表别名
	 * @param map
	 *            条件集
	 * @return 返回类型 String 返回拼装好的hql语句
	 */
	@SuppressWarnings("unchecked")
	public static String getMapInfo(String field, Map map) {
		if (map == null) {
			return "";
		} else {
			StringBuilder hql = new StringBuilder();
			Set<String> set = map.keySet();
			if (set.size() > 0) {
				hql.append(" where ");

				for (String elem : set) {
					String[] key = StringUtils.split(elem, ",");

					if (key.length == 2) {
						Object value = map.get(elem);
						if (!(value == null || value.equals(""))) {
							if (value instanceof String) {
								if (key[1].equals(SAMOUNT)) {
									hql.append(" " + field + "." + key[0]
											+ SAMOUNT + "'" + value + "'"
											+ " and ");
								} else {
									if (key[1].equals(SLIKE)) {
										hql.append(" " + field + "." + key[0]
												+ " like " + "lower('%" + value
												+ "%')" + " and ");
									} else {
										if (key[1].equals(SNOTAMOUNT)) {
											hql.append(" " + field + "."
													+ key[0] + SNOTAMOUNT + "'"
													+ value + "'" + " and ");
										}
									}
								}
								if (key[1].equals(SISNULL)) {
									hql.append(" " + field + "." + key[0]
											+ SISNULL + " and ");
								} else {
									if (key[1].equals(SISNOTNULL)) {
										hql.append(" " + field + "." + key[0]
												+ SISNOTNULL + " and ");
									}
								}
							} else {
								if (value instanceof Number) {
									hql.append(" " + field + "." + key[0]
											+ key[1] + value + " and ");
								}
							}
						}
					}
				}
			}
			String sql = hql.toString();
			String temp = sql.substring(sql.length() - 6, sql.length() - 1);
			if (temp.equals("where")) {
				return sql.substring(0, sql.length() - 6);
			} else {
				return sql.substring(0, sql.length() - 4);
			}

		}

	}
	
}
