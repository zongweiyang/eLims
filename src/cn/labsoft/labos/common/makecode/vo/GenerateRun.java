package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;


public class GenerateRun {
	public static boolean makeCode(String path,Packagee packagee) throws GlobalException{
		boolean key=true;
		//entity
		List<Prop> listProp1=packagee.getClazz().getProp();
		List<Prop> listProp=new ArrayList<Prop>();
		if(listProp1!=null&&listProp1.size()>0){
			for(Prop prop:listProp1){
				if(prop!=null)
					listProp.add(prop);
			}
		}
		
		packagee.getClazz().setProp(listProp);
		String srcPath=path+"\\src\\cn\\labsoft\\labos";
		String packURl=packagee.getName();
		String packName="";
		for(String name:packagee.getName().split("/")){
			packName+=name;
			packName+=".";
		}
		if(packName.indexOf(".")>-1)packName=packName.substring(0,packName.length()-1);
		packagee.setNamePath(packagee.getName().replace("/","\\"));
		packagee.setName(packName);
		GenerateEntityJava.createEntityJava(packagee, listProp,srcPath);
		GenerateEntityVo.createEntityVo(packagee,listProp,srcPath);
		GenerateIDAO.createEntityIDAO(packagee, listProp, path);
		GenerateDAOImpl.createEntityDAOImpl(packagee, listProp, path);
		GenerateIService.createEntityIService(packagee, listProp, path);
		GenerateServiceImpl.createServiceImpl(packagee, path);
		GenerateAction.createAction(packagee, path);
		GenerateStrtusXml.createStrtusXml(packagee, path);
		List<String> templateContentList = new ArrayList<String>();
		List<String> contentList = new ArrayList<String>();
		if(packagee.getIsFlow().equals("Y")){
			templateContentList = FileOperater.readFileByLines(path
					+ "\\WebRoot\\utils\\gmt\\read\\flow\\MouldList.jsp");
		}else{
			templateContentList = FileOperater.readFileByLines(path
					+ "\\WebRoot\\utils\\gmt\\read\\mouldList.jsp");
		}
		contentList = GenerateListJsp.buildContent(templateContentList,
				packagee, packagee.getClazz(), listProp,"");
		String clazzNameLower = packagee.getClazz().getClazzName().substring(0,1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length());
		String packPath=packagee.getNamePath()+"\\"+packagee.getClazz().getJspName();
		FileOperater.writeFileByLines(path
				+ "\\WebRoot\\jsp\\"+packPath+"\\"+clazzNameLower+"List.jsp", contentList);
		if(packagee.getIsFlow().equals("Y"))
		GenerateListJsp.runFlowList(packagee,path,listProp);
		
		if(packagee.getIsFlow().equals("Y")){
			templateContentList = FileOperater.readFileByLines(path
					+ "\\WebRoot\\utils\\gmt\\read\\flow\\MouldAdd.jsp");
		}else{
			templateContentList = FileOperater.readFileByLines(path
					+ "\\WebRoot\\utils\\gmt\\read\\mouldAdd.jsp");
		}
		contentList = GenerateAddJsp.buildContent(templateContentList,
				packagee, packagee.getClazz(), listProp);
		FileOperater.writeFileByLines(path
				+ "\\WebRoot\\jsp\\"+packagee.getNamePath()+"\\"+clazzNameLower+"Add.jsp", contentList);
		if(packagee.getIsFlow().equals("Y")){
			templateContentList = FileOperater.readFileByLines(path
					+ "\\WebRoot\\utils\\gmt\\read\\flow\\mouldUpdate.jsp");
		}else{
			templateContentList = FileOperater.readFileByLines(path
					+ "\\WebRoot\\utils\\gmt\\read\\mouldUpdate.jsp");
		}
		contentList = GenerateUpdateJsp.buildContent(templateContentList,
				packagee, packagee.getClazz(), listProp,"","");
		FileOperater.writeFileByLines(path
				+ "\\WebRoot\\jsp\\"+packagee.getNamePath()+"\\"+clazzNameLower+"Update.jsp", contentList);
		if(packagee.getIsFlow().equals("Y"))
		GenerateUpdateJsp.runFlowList(packagee, path, listProp);
		
		
		
		templateContentList = FileOperater.readFileByLines(path
				+ "\\WebRoot\\utils\\gmt\\read\\mouldShow.jsp");
		contentList = GenerateShowJsp.buildContent(templateContentList,
				packagee, packagee.getClazz(), listProp);
		FileOperater.writeFileByLines(path
				+ "\\WebRoot\\jsp\\"+packagee.getNamePath()+"\\"+clazzNameLower+"Show.jsp", contentList);
		packagee.setName(packURl);
		templateContentList = FileOperater.readFileByLines(path
				+ "\\src\\struts.xml");
		contentList = GenerateConfigStruts.buildContent(templateContentList,packagee);
		FileOperater.writeFileByLines(path+"\\src\\struts.xml",contentList);
		return key;
	}
	public static void main(String[] args) throws GlobalException {
		Prop prop=new Prop();
		prop.setName("name");
		prop.setIsShowOnList("1");
		prop.setNameChinese("中文名");
		Prop prop1=new Prop();
		prop1.setName("name1");
		prop1.setIsShowOnList("1");
		prop1.setNameChinese("中文名1");
		prop1.setIsSortList("1");
		Prop prop2=new Prop();
		prop2.setName("name2");
		prop2.setIsShowOnList("1");
		prop2.setNameChinese("中文名2");
		List<Prop> listProp=new ArrayList<Prop>();
		listProp.add(prop);
		listProp.add(prop1);
		listProp.add(prop2);
		Clazz clazz=new Clazz();
		clazz.setClazzName("Test");
		clazz.setProp(listProp);
		clazz.setClazzCommon("测试");
		clazz.setJspName("sss");
		clazz.addColEnterNum=3;
		Packagee  packagee=new Packagee();
		packagee.setName("common\number");
		packagee.setNameChinese("演示");
		packagee.setClazz(clazz);
		GenerateRun.makeCode("E:\\workspace\\LABOS-CORE", packagee);
		
	}
	
	
}
