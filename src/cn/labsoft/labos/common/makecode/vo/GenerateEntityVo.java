package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;


public class GenerateEntityVo {

	/** 
	 * @Title:  
	 * @Description: TODO
	 * @param @param args  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws 
	 */
	public static void createEntityVo(Packagee packagee,List<Prop> listProp,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		list.add("package cn.labsoft.labos."+packagee.getName()+".vo;");
		list.add("");
		list.add("import cn.labsoft.labos.framework.common.vo.BaseVo;");
		list.add("");
		list.add("import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;");
		list.add("");
		list.add("public class "+packagee.getClazz().getClazzName()+"Vo extends BaseVo {");
		list.add("	private static final long serialVersionUID = 1L;");
		list.add("	private String id;");
		for (Prop prop :listProp) {
			list.add("	@ExcelAnnotation(exportName = \""+prop.getNameChinese()+"\")");
			list.add("	private String "+prop.getName()+";");
		}
		list.add("");
		list.add("	public String getId() {");
		list.add("		return this.id;");
		list.add("	}");
		list.add("	public void setId(String id) {");
		list.add("		this.id = id;");
		list.add("	}");
		list.add("");
		for (Prop prop : listProp) {
			list.add("	public String get"+prop.getName().substring(0,1).toUpperCase()+prop.getName().substring(1,prop.getName().length())+"() {");
			list.add("		return this."+prop.getName()+";");
			list.add("	}");
			list.add("");
			list.add("	public void set"+prop.getName().substring(0,1).toUpperCase()+prop.getName().substring(1,prop.getName().length())+"(String "+prop.getName()+") {");
			list.add("		this."+prop.getName()+" = "+prop.getName()+";");
			list.add("	}");
			list.add("");
		}
		list.add("}");
		path+="\\"+packagee.getNamePath()+"\\vo\\"+packagee.getClazz().getClazzName()+"Vo.java";
		FileOperater.writeFileByLines(path, list);
	}
}
