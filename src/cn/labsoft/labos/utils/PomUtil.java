package cn.labsoft.labos.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class PomUtil {
	private static final String PATH;
	static{
		PATH = new File(PomUtil.class.getClassLoader().getResource("").getFile()).getParentFile()+File.separator+"lib";
	}
	public static void main(String[] args){
		try{
			System.out.println("starting......");
			File file = new File(System.getProperty("user.dir")+File.separator+"pom.xml");
			if(!file.exists()){
				file.createNewFile();
			}
			Element root = DocumentHelper.createElement("project");
			Document doc = DocumentHelper.createDocument(root);
			File dir = new File(PATH);
			Element dependencies = root.addElement("dependencies");
			if(dir.isDirectory()){
				File [] files = dir.listFiles();
				for(File f: files){
					String name =  f.getName();
					System.out.println("Add "+name+" to pom.");
					name = name.substring(0, name.length()-4);
					Element dependency = dependencies.addElement("dependency");
					dependency.addElement("groupId").addText("elims");
					dependency.addElement("artifactId").addText(name);
					dependency.addElement("version").addText("1.0");
					dependency.addElement("scope").addText("system");
					dependency.addElement("systemPath").addText("${basedir}/src/main/webapp/WEB-INF/lib/"+f.getName());
				}
			}
			OutputFormat mat = new OutputFormat("	",true);
			mat.setEncoding("UTF-8");
			XMLWriter w = new XMLWriter(new FileOutputStream(file),mat);
			w.write(doc);
			w.close();
			System.out.println("ending......");
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
