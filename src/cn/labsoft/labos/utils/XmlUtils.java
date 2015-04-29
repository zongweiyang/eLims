package cn.labsoft.labos.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;

import cn.labsoft.labos.framework.common.exception.GlobalException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.Dom4JDriver;

public class XmlUtils {
	
	/**
	 * 将XML字符串转换成Document对象
	 * @param xml
	 * @return
	 * @throws GlobalException 
	 */
	public static Document toDocument(String xml) throws GlobalException{
		Document doc=null;
		try {
			doc=DocumentHelper.parseText(xml);
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
		return doc;
	}
	
	/**
	 * 将Document对象转换成XML字符串
	 * @param doc
	 * @return
	 * @throws GlobalException 
	 */
	public static String toXMLString(Document doc) throws GlobalException{
		String xml=null;
		try {
			doc.asXML();
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
		return xml;
	}
	
	/**
	 * Retrieves a bean object for the received XML and matching bean class
	 */
	public static Object fromXML(String xml) {
		XStream xstream = new XStream(new Dom4JDriver());
		return xstream.fromXML(xml);
	}

	/**
	 * Returns an XML document String for the received bean
	 */
	public static String toXML(Object bean) {
		XStream xstream = new XStream(new Dom4JDriver());
		return xstream.toXML(bean);
	}

	public static String getFileContent(String filePath) throws GlobalException {
		String fileContent = "";
		FileReader fileReader = null;
		BufferedReader reader = null;
		File f = null;
		try {
			f = new File(filePath);
			fileReader = new FileReader(f);
			reader = new BufferedReader(fileReader);
			String line = "";
			while (null != (line = reader.readLine())) {
				fileContent = fileContent + line;
			}

			return fileContent;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return null;
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
			if (null != fileReader) {
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
					throw new GlobalException("" + e.getMessage());
				}
			}
		}

	}

	/**
	 * 
	 * @Title: 用来获取WebService 返回的结果 显示为String  
	 * @Description: TODO
	 * @param @param obj
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public static String getString(Object obj){
		if(null==obj)
			return "";
		if(obj.toString()==null)
			return "";
		if(obj.toString().length()==1)
			return "";
		
		String resultStr=obj.toString().replaceAll("&lt;","<");
		resultStr=resultStr.replaceAll("&gt;",">");
		
		String startIndex="<ns:return>";
		Integer startIndexLength=startIndex.length();
		String endIndex="</ns:return>";
		
		Integer startL=resultStr.indexOf(startIndex)+startIndexLength;
		Integer endL=resultStr.indexOf(endIndex);
		
		if(endL<=startL)
			return "";
		return resultStr.substring(startL,endL);
	}
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws DocumentException, GlobalException {
		try {
			//XMLWriter writer = null; // 声明写XML的对象
			SAXReader reader = new SAXReader();

			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8"); // 设置XML文件的编码格式

			String filePath = "F:\\2011_03\\workspace\\ANT1104\\WebRoot\\jsp\\search\\data\\1301554359390.xml";
			File file = new File(filePath);
			if (file.exists()) {
				Document document = reader.read(file); // 读取XML文件
				Element root = document.getRootElement(); // 得到根节点
				//boolean bl = false;
				for (Iterator i = root.elementIterator("ROW"); i.hasNext();) {
					Element row = (Element) i.next();
					String ics = row.elementText("ICS" );
					System.out.println(ics);
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		
	}
	
}