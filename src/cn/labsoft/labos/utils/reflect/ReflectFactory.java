package cn.labsoft.labos.utils.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : ReflectFactory </strong>. <br>
 * <strong>Description : 通过反射获得类属性的一些方法集合</strong> <br>
 * <strong>Create on : 2009-12-30 下午05:24:30 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class ReflectFactory {

	/**
	 * 
	 * 通过传入类名获得该类所有可以读写的基础属性
	 * 
	 * @param classType
	 *            类名：格式是 com.test.testpackage.Test
	 * @throws GlobalException
	 *             里面转了 name和type 可以通过迭代map获得属性名和该属性的数据类型
	 * @return 返回类型 Map
	 */
	@SuppressWarnings("unchecked")
	public static Map getObejctProperty(String classType)
			throws GlobalException {
		Map resultMap = new HashMap();
		Class objectCopy;
		try {
			objectCopy = Class.forName(classType);
		} catch (Exception e) {
			// TODO: handle exception
			//e.printStackTrace();
			throw new GlobalException(
					"ReflectFactory:getObejctProperty:Class Not Found!", e);
		}
		Field[] fields = objectCopy.getDeclaredFields();
		Method[] methods = objectCopy.getDeclaredMethods();
		for (int i = 0; i < fields.length; i++) {
			String name = fields[i].getName();
			boolean getFlag = false;
			boolean setFlag = false;
			for (int j = 0; j < methods.length; j++) {

				String methodName = methods[j].getName().toString();

				if ((methodName.replace("get", "").toLowerCase().equals(name
						.toLowerCase()))) {
					getFlag = true;
				}
				if ((methodName.replace("set", "").toLowerCase().equals(name
						.toLowerCase()))) {
					setFlag = true;
				}
			}// 如果set 和get方法都有,才能被认为是可读写属性
			if (getFlag && setFlag) {
				if (fields[i].getType().toString().endsWith("String")) {
					resultMap.put(name, "String");
				}
				if (fields[i].getType().toString().endsWith("Long")) {
					resultMap.put(name, "Long");
				}
				if (fields[i].getType().toString().endsWith("Integer")) {
					resultMap.put(name, "Integer");

				}
				if (fields[i].getType().toString().endsWith("Double")) {
					resultMap.put(name, "Double");

				}

			}

		}
		return resultMap;
	}

	// Map resultMap=ReflectFactory.getObejctProperty(PupupDivVo.class);
	// Iterator keyValuePairs = resultMap.entrySet().iterator();
	// while (keyValuePairs.hasNext()) {
	// (keyValuePairs.next());
	// }
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws GlobalException {
		try {
			Map resultMap = ReflectFactory
					.getObejctProperty("cn.labsoft.labos.base.cm.vo.BuTableDVo");
			Iterator keyValuePairs = resultMap.entrySet().iterator();
			while (keyValuePairs.hasNext()) {
			}

		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
			// TODO: handle exception
		}

	}
}
