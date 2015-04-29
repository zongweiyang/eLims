package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

public class GenerateShowJsp {
	public static List<String> buildContent(List<String> templateContentList,
			Packagee packagee, Clazz clazz, List<Prop> propList) {
		List<String> contentList = new ArrayList<String>();
		if (null != templateContentList && 0 < templateContentList.size()) {
			for (String lineStr : templateContentList) {
				if (lineStr.trim().equals(
						Constants.TEMP_JSP_TABLE_BODY_INSERT_TAG)) {
					if (null != propList && 0 < propList.size()) {
						contentList.add("											<tr>");
						for (int i=0;i<propList.size();i++) {
							Prop prop = propList.get(i);
							if(i%clazz.getAddColEnterNum()==0&&i!=0&&i!=propList.size()-1){
								contentList.add("											</tr>");
								contentList.add("											<tr>");
							}
							contentList.add("												<td><label>"+prop.getNameChinese()+"：</label></td>");
							String tdValue = "";
							String nameOrValue=clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName();
							tdValue+="												<td>${"+nameOrValue+"}"; 
							tdValue+="</td>";
							contentList.add(tdValue);
						}
						contentList.add("											</tr>");
					}
				} else {
					contentList.add(lineStr);
				}

			}
		}
		return contentList;
	}

	public static void main(String[] args) {

	}
}
