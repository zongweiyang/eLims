package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 
 * <strong>Title : GenerateEntityXML </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 12, 2014 2:58:57 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Stone Tang <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class GenerateEntityXML {

	/** 
	 * @Title:  
	 * @Description: TODO
	 * @param @param args  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws 
	 */
	public static void createEntityXML(Packagee packagee,List<Prop> listProp,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		List<String> copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\Mould.hbm.xml");
		
		String classPath="cn.labsoft.labos."+packagee.getName()+".entity."+packagee.getClazz().getClazzName()+"";
		if (null != copyList && 0 < copyList.size()) {
			for (String lineStr : copyList) {
				lineStr = lineStr.replace("ClassPath", classPath);
				lineStr = lineStr.replace("TabName", (StrUtils.changeCamelToCol(packagee.getClazz().getClazzName())).toLowerCase());
				if(lineStr.trim().equals("<!-- 基本属性 -->")){
					for (Prop prop : listProp) {
						if(null==prop.getLength()||"".equals(prop.getLength().trim())){
							prop.setLength("64");
						}
						list.add("        <property name=\""+prop.getName()+"\" type=\"java.lang.String\">");
						list.add("           <column name=\""+StrUtils.changeCamelToCol(prop.getName()).toLowerCase()+"\"  length=\""+prop.getLength()+"\">");
						list.add("            	<comment>"+prop.getNameChinese()+"</comment>");
						list.add("            </column>");
						list.add("        </property>");
					}
				}
				list.add(lineStr);
			}
		}
		path+="\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\entity\\"+packagee.getClazz().getClazzName()+".hbm.xml";
		FileOperater.writeFileByLines(path, list);
	}
	public static void main(String[] args) throws GlobalException {
		// TODO Auto-generated method stub
		Packagee  packagee=new Packagee();
		packagee.setName("common/student");
		packagee.setNameChinese("演示");
		Clazz clazz=new Clazz();
		clazz.setClazzName("Test1");
		packagee.setClazz(clazz);
		Prop prop=new Prop();
		prop.setName("name");
		prop.setNameChinese("中文名");
		Prop prop1=new Prop();
		prop1.setName("name1");
		prop1.setNameChinese("中文名1");
		Prop prop2=new Prop();
		prop2.setName("name2");
		prop2.setNameChinese("中文名2");
		List<Prop> listProp=new ArrayList<Prop>();
		listProp.add(prop);
		listProp.add(prop1);
		listProp.add(prop2);
		createEntityXML(packagee,listProp,"E:\\workspace\\LABOS-CORE");
	}

}
