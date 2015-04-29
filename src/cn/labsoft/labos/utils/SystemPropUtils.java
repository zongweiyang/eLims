package cn.labsoft.labos.utils;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <strong>Title : SystemPropUtils </strong>. <br>
 * <strong>Description : 系统属性工具</strong> <br>
 * <strong>Create on : 2009-12-25 上午10:52:32 </strong>. <br>
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
public class SystemPropUtils {
	/**
	 * 获得系统所有的属性名列表
	 * 
	 * @return 返回类型 List 系统所有的属性名列表
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List getSystemPropertyNames() {
		Enumeration hlistkey = System.getProperties().propertyNames();
		List name = new ArrayList();
		while (hlistkey.hasMoreElements()) {
			name.add(hlistkey.nextElement());
		}
		return name;
	}

	/**
	 * 获得系统所有的属性名MAP
	 * 
	 * @return 返回类型 Map 系统所有的属性名MAP
	 * 
	 */
	@SuppressWarnings("unchecked")
	public Map getSystemProperty() {
		Enumeration hlistkey = System.getProperties().propertyNames();
		Map name = new HashMap();
		while (hlistkey.hasMoreElements()) {
			String skey = (String) hlistkey.nextElement();
			name.put(skey, System.getProperty(skey));
		}
		return name;
	}

	/**
	 * 获得系统某属性的属性值
	 * 
	 * @param key
	 *            属性
	 * @return 返回类型 String 系统某属性的属性值
	 * 
	 */
	public String getPropertyValue(String key) {
		return System.getProperty(key);
	}
}
