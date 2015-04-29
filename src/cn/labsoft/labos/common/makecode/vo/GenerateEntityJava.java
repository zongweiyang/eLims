package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;


public class GenerateEntityJava {

	/** 
	 * @Title:  
	 * @Description: TODO
	 * @param @param args  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws 
	 */
	public static void createEntityJava(Packagee packagee,List<Prop> listProp,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		list.add("package cn.labsoft.labos."+packagee.getName()+".entity;");
		list.add("");
		list.add("import cn.labsoft.labos.framework.common.po.AbstractBasePo;");
		list.add("");
		list.add("import org.apache.commons.lang.builder.ToStringBuilder;");
		list.add("import javax.persistence.Transient;");
		list.add("");
		list.add("import javax.persistence.JoinColumn;");
		list.add("");
		list.add("import javax.persistence.ManyToOne;");
		list.add("");
		list.add("import javax.persistence.Entity;");
		list.add("");
		list.add("import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;");
		list.add("");
		list.add("@Entity");
		list.add("public class "+packagee.getClazz().getClazzName()+" extends AbstractBasePo {");
		list.add("	private static final long serialVersionUID = 1L;");
		for (Prop prop :listProp) {
			String nameChin="";
			if(!StrUtils.isBlankOrNull(prop.getNameChinese())){
				nameChin=prop.getNameChinese();
			}
			list.add("	private String "+prop.getName()+";//"+nameChin+"");
		}
		list.add("");
		if(packagee.getIsFlow().equals("Y")){
			list.add("	private LabWfProcessIns processIns;");
			list.add("");
			list.add("	@ManyToOne");
			list.add("	@JoinColumn(name=\"process_ins_id\")");
			list.add("	public LabWfProcessIns getProcessIns() {");
			list.add("		return processIns;");
			list.add("	}");
			list.add("	public void setProcessIns(LabWfProcessIns processIns) {");
			list.add("		this.processIns = processIns;");
			list.add("	}");
		}
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
		
		
		list.add("	@Transient");
		list.add("	@Override");
		list.add("	public String getModelName() {");
		if(!StrUtils.isBlankOrNull(packagee.getNameChinese()))
			list.add("		return \""+packagee.getNameChinese()+"\";");
		else
			list.add("		return \"\";");
		list.add("	}");
		list.add("	@Transient");
		list.add("	@Override");
		list.add("	public String getTableName() {");
		if(!StrUtils.isBlankOrNull(packagee.getClazz().getClazzCommon()))
			list.add("		return \""+packagee.getClazz().getClazzCommon()+"\";");
		else
			list.add("		return \"\";");
		list.add("	}");
		list.add("}");
		path+="\\"+packagee.getNamePath()+"\\entity\\"+packagee.getClazz().getClazzName()+".java";
		FileOperater.writeFileByLines(path, list);
	}
	public static void main(String[] args) throws GlobalException {
		// TODO Auto-generated method stub
		Packagee  packagee=new Packagee();
		packagee.setName("student");
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
		createEntityJava(packagee,listProp,"E:\\workspace\\LABOS-CORE\\src\\cn\\labsoft\\labos");
	}

}
