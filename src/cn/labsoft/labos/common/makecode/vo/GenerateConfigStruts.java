package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

public class GenerateConfigStruts {
	public static List<String> buildContent(List<String> templateContentList,Packagee packagee) {
		List<String> contentList = new ArrayList<String>();
		if (null != templateContentList && 0 < templateContentList.size()) {
			for (int i=0;i<templateContentList.size();i++) {
				String lineStr = templateContentList.get(i);
				if((i==templateContentList.size()-2)&&!isExsitLineStr(templateContentList,"<include file=\"strutsconfigs/"+packagee.getName()+".xml\"></include>")){
					contentList.add(lineStr);
					contentList.add("	<!-- "+packagee.getNameChinese()+"管理 -->");
					contentList.add("	<include file=\"strutsconfigs/"+packagee.getName()+".xml\"></include>");
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
