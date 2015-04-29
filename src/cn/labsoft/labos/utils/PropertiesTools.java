package cn.labsoft.labos.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : PropertiesTools </strong>. <br>
 * <strong>Description : 读写Properties文件</strong> <br>
 * <strong>Create on : 2009-12-24 下午01:31:52 </strong>. <br>
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
public class PropertiesTools {
	/**
	 * 读Properties文件
	 * 
	 * @param path
	 *            文件类路径
	 * @return 返回类型 Properties 返回一个Properties类型的文件
	 * @throws GlobalException 
	 * 
	 */
	public Properties read(String path) throws GlobalException {
		java.util.Properties properties = null;
		try {
			java.io.InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream(path);
			properties = new java.util.Properties();
			properties.load(in);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}

		return properties;
	}

	public static String getPropertiesValueByFileAndKey(
			String propertiesFileName, String propertiesKey) {
		try {
			String returnStr = "";
			PropertiesTools tools = new PropertiesTools();
			Properties properties = tools.read(propertiesFileName);
			returnStr = properties.getProperty(propertiesKey);
			return returnStr;
		} catch (Exception e) {
			return "";
		}

	}

	public static String getPropertiesValueByFilePathAndKey(
			String propertiesFilePath, String propertiesKey) {
		try {
			String returnStr = "";
			java.util.Properties properties = null;
			try {
				InputStream in = new FileInputStream(new File(propertiesFilePath));
				properties = new java.util.Properties();
				properties.load(in);
			} catch (Exception e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}

			returnStr = properties.getProperty(propertiesKey);
			return returnStr;
		} catch (Exception e) {
			return "";
		}
	}
	public static Set<Entry<Object, Object>> getPropertiesValueByFilePathAndKey(
			String propertiesFilePath) {
		try {
			java.util.Properties properties = null;
			try {
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
						new FileInputStream(new File(propertiesFilePath)), "UTF-8"));
				properties = new java.util.Properties();
				properties.load(bufferedReader);
			} catch (Exception e) {
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
			Set<Entry<Object, Object>> propSet = properties.entrySet();
			return propSet;
		} catch (Exception e) {
			//e.printStackTrace();
			return new HashSet<Entry<Object, Object>>() ;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		/*Connection conn = JDBCTools.getConnection();
		Statement st = null;
		try {
			st = conn.createStatement();
			st.execute("INSERT INTO `com_type` (`ID`,`NAME`,`CODE`,`REMARK`,`SORT`,`TENANTID`) VALUES ('295cf00322d440728c3a757ac7b4f110','样品登记','YPDJ','0','2',1090)");
		} catch (Exception x) {
			x.printStackTrace();
		}
		if (null != st) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}*/
		Set<Entry<Object,Object>> propSet = PropertiesTools.getPropertiesValueByFilePathAndKey("F:\\CharlesWorkspace\\Demo02\\Client\\WebRoot\\sqltemplate\\rolefun.properties");
		Iterator iterator = propSet.iterator();
		while(iterator.hasNext()){
			Entry<Object,Object> entryMap = (Entry<Object, Object>) iterator.next();
			System.out.println(entryMap.getKey()+"**********"+entryMap.getValue());
		}
		
	}

	
}
