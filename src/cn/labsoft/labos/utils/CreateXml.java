package cn.labsoft.labos.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;



public class CreateXml {
	public static String projectPath  = "E:\\workspace\\RES1304";
	public static List<File> fileList = new ArrayList<File>();
	public static void createXml() throws GlobalException {
		List<String> contentList = new ArrayList<String>();
		contentList.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		contentList.add("<!DOCTYPE beans PUBLIC \"-//SPRING//DTD BEAN//EN\" \"http://www.springframework.org/dtd/spring-beans.dtd\">");
		contentList.add("<beans>");
		for(File f:fileList){
			String classPath = f.getPath().replace("\\", ".").split("src.")[1].replace(".java", "");
			String classId = f.getName().substring(0,1).toLowerCase()+f.getName().substring(1, f.getName().length()).replace(".java", "");
			
			replaceXml(classId,classPath);
			List<String> beanList = new ArrayList<String>();try {
				beanList = getBeanList(classPath.replace(".java", ""));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
			contentList.add("  	<bean id=\""+classId+"\" class=\""+classPath+"\">");
			if(null!=beanList){
				for(String str:beanList){
					contentList.add("		<property name=\""+str+"\">");
					contentList.add("			<ref bean=\""+str+"\"/>");
					contentList.add("		</property>");
				}
			}
			contentList.add("	</bean>");
		}
		contentList.add("</beans>");
		String configPathString = fileList.get(0).getParentFile().getParentFile().getPath()+"\\config\\applicationContext_Action.xml";
		
		FileOperater.writeFileByLines(configPathString,contentList);
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<String> getBeanList(String className) throws ClassNotFoundException {
		Class obj = Class.forName(className);
		List<String> beanList = new ArrayList<String>();
		for(Field file:obj.getDeclaredFields()){
			if(file.getName().endsWith("Service")){
				beanList.add(file.getName());
			}
		}
		return beanList;
	}
	
	
	public static void replaceXml(String classId,String classPath) throws GlobalException{
		String xmlPath = projectPath+"\\src\\strutsconfigs\\";
		File[] fil= new File(xmlPath).listFiles();
		for(File f:fil){
			List<String> contentList = FileOperater.readFileByLines(f.getPath());
			for(int i=0;i<contentList.size();i++){
				contentList.set(i, StrUtils.replace(contentList.get(i), classPath, classId));
			}
			FileOperater.writeFileByLines(f.getPath(),contentList);
		}
		
	}
	
	
	public static void checkXml() throws GlobalException{
		String xmlPath = projectPath+"\\src\\strutsconfigs\\";
		File[] fil= new File(xmlPath).listFiles();
		for(File f:fil){
			List<String> contentList = FileOperater.readFileByLines(f.getPath());
			for(int i=0;i<contentList.size();i++){
				String str = contentList.get(i);
				if(str.indexOf("cn.labsoft.labos")>-1)
					System.out.println(f.getName()+"~~~~~"+ contentList.get(i));
			}
		}
		
	}
	
	public static void getAllAction(File files) {
		File[] fil=files.listFiles();
		for(File f:fil){
			if(f.isDirectory()){
				getAllAction(f);
			}else{
				if(f.getName().endsWith("Action.java"))
					fileList.add(f);
			}
		}
	}
	
	public static void main(String[] args) throws GlobalException {
		//File files = new File(projectPath); 
		//String classPath = projectPath+"\\src\\cn\\labsoft\\labos"+"\\coreextend";
		//File files = new File(classPath); 
		//getAllAction(files); 
		//创建SpringXML文件
		//createXml();
		checkXml();
	}
}
