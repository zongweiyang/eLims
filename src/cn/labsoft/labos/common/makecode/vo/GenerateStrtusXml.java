package cn.labsoft.labos.common.makecode.vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;
/**
 * 
 * <strong>Title : GenerateAction </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 13, 2014 9:13:22 AM  </strong>. <br>
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

public class GenerateStrtusXml {

	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param packagee
	 * @param @param listProp
	 * @param @param path  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	public static void createStrtusXml(Packagee packagee,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		List<String> copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\Mould.xml");
		String actionSrc=packagee.getClazz().getClazzName().substring(0,1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())+"Action";
		String jspName="";
		if(!StrUtils.isBlankOrNull(packagee.getClazz().getJspName())){
			jspName="/"+packagee.getClazz().getJspName();
		}
		if (null != copyList && 0 < copyList.size()) {
			for (String lineStr : copyList) {
				lineStr = lineStr.replace("pageName", packagee.getName().replace(".", "/")+jspName);
				lineStr = lineStr.replace("packName", packagee.getName().substring(packagee.getName().lastIndexOf(".")+1,packagee.getName().length()));
				lineStr = lineStr.replace("actionSrc", actionSrc);
				lineStr = lineStr.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
				lineStr = lineStr.replace("Mould",(packagee.getClazz().getClazzName()));
				if(packagee.getIsFlow().equals(Constants_Base.Y)&&lineStr.trim().replace(" ", "").equals("</package>")){
					for(String name:packagee.stepNameList){
						if(!StrUtils.isBlankOrNull(name)){
							List<String> xmlList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\flow\\mouldStruts.xml");
							if(xmlList!=null&&xmlList.size()>0){
								for(String xml:xmlList){
									xml = xml.replace("pageName", packagee.getName().replace(".", "/")+jspName+"/"+name);
									xml = xml.replace("packName", packagee.getName().substring(packagee.getName().lastIndexOf(".")+1,packagee.getName().length()));
									xml = xml.replace("actionSrc", actionSrc);
									xml = xml.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
									xml = xml.replace("Mould",(packagee.getClazz().getClazzName()));
									xml=xml.replace("MM", name);
									list.add(xml);
								}
							}
						}
					}
				}
				list.add(lineStr);
			}
		}
		path+="\\src\\strutsconfigs\\"+packagee.getNamePath()+".xml";
		if(new File(path).exists()){
			List<String> listRead=FileOperater.readFileByLines(path);
			List<String> writeLine=new ArrayList<String>();
			if(listRead!=null&&listRead.size()>0){
				for(int i=0;i<listRead.size();i++){
					if(listRead.get(i).trim().equals("</struts>")){
						for(int j=0;j<list.size();j++){
							if(list.get(j).trim().equals("<struts>")){
								for(int k=(j+1);k<list.size();k++){
									if(list.get(k).trim().equals("</struts>")){
										break;
									}else{
										writeLine.add(list.get(k));
									}
								}
								break;
							}
						}
					}
					writeLine.add(listRead.get(i));
				}
			}
			FileOperater.writeFileByLines(path, writeLine);
		}else
		FileOperater.writeFileByLines(path, list);
	}
	public static void main(String[] args) {
		String name="common.stone";
		name=name.replaceAll(".", "/");
		System.out.println(name);
	}
	

}
