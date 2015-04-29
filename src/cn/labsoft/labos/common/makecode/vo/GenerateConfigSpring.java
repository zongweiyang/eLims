package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

public class GenerateConfigSpring {
	public static List<String> buildContent(List<String> templateContentList,Packagee packagee) {
		List<String> contentList = new ArrayList<String>();
		if (null != templateContentList && 0 < templateContentList.size()) {
			for (int i=0;i<templateContentList.size();i++) {
				String lineStr = templateContentList.get(i);
				if(i==templateContentList.size()-2&&!isExsitLineStr(templateContentList,"<import resource=\"cn/labsoft/labos/"+packagee.getName()+"/config/applicationContext_DAO.xml\" />")){
					contentList.add(lineStr);
					contentList.add("	 <!-- "+packagee.getNameChinese()+"业务 -->");
					contentList.add("	 <import resource=\"cn/labsoft/labos/"+packagee.getName()+"/config/applicationContext_DAO.xml\" />");
					contentList.add("	 <import resource=\"cn/labsoft/labos/"+packagee.getName()+"/config/applicationContext_Service.xml\" />");
				}else{
					contentList.add(lineStr);
				}
			}
		}
		return contentList;
	}
	
	public static boolean isExsitLineStr(List<String> templateContentList,String lineStr){
		if(null!=templateContentList&&0<templateContentList.size()){
			for(String lineString : templateContentList){
				if(lineStr.trim().equals(lineString.trim())){
					return true;
				}
			}
		}
		return false;
	}
	
	public static void main(String[] args){
	}
	
}
