package cn.labsoft.labos.utils.exportsql;

import java.io.Closeable;
import java.lang.reflect.Field;

import cn.labsoft.labos.framework.common.exception.GlobalException;

public class CommonUtils {
	@SuppressWarnings("unchecked")
	public static Object getValueFromString(String value, Class toType) {
		if (toType == Double.class)
			return new Double(value);
		if (toType == Integer.class)
			return new Integer(value);
		if (toType == Long.class)
			return new Long(value);
		if (toType == java.util.Date.class) {
			return java.sql.Date.valueOf(value);
		}
		return value;
	}

	public static boolean isValidString(String str) {
		return ((str == null) || (str.trim().length() <= 0));
	}

	public static String getSimpleName(String name) {
		if (name.lastIndexOf(".") != -1) {
			String temp = name.substring(name.lastIndexOf(".") + 1);
			return temp;
		}
		return name;
	}

	public static String getFirstName(String name) {
		if (name.indexOf(".") != -1) {
			String temp = name.substring(0, name.indexOf("."));
			return temp;
		}
		return name;
	}

	@SuppressWarnings("unchecked")
	public static Field getFieldByString(Class clazz, String name) throws GlobalException {
		try {
			if (name.indexOf(".") == -1) {
				return clazz.getDeclaredField(name);
			}
			Field field = clazz.getDeclaredField(name.substring(0, name
					.indexOf(".")));
			Class cla = field.getType();
			return cla.getDeclaredField(name.substring(name.indexOf(".") + 1));
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
	}

	public static String getFirstUpper(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static void closeStream(Closeable steam) throws GlobalException {
		try {
			if (steam != null)
				steam.close();
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
}
