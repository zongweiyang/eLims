package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.utils.StrUtils;

public class GenerateAddJsp {
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
							if(i%clazz.getAddColEnterNum()==0&&i!=0){
								contentList.add("											</tr>");
								contentList.add("											<tr>");
							}
							contentList.add("												<td><label>"+prop.getNameChinese()+"：</label></td>");
							String tdValue = "";
							String valType="";
							if(!StrUtils.isBlankOrNull(prop.getValidateName())){
								valType=prop.getValidateName();
								valType="valType=\""+valType+"\"";
							}
							//默认为文本框
							String domain = "<input type=\"text\" name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" id=\""+prop.getName()+"\" "+valType+" />";
							if(null!=prop.getShowType()&&"2".equals(prop.getShowType())){//日期控件
								domain = "<input type=\"text\" class=\"Wdate\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})\" name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" id=\""+prop.getName()+"\" "+valType+" />";
							}else if(null!=prop.getShowType()&&"3".equals(prop.getShowType())){//时间控件
								domain = "<input type=\"text\" class=\"Wdate\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})\" name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" id=\""+prop.getName()+"\" "+valType+" />";
							}else if(null!=prop.getShowType()&&"4".equals(prop.getShowType())){//文本域
								domain = "<textarea name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" cols=\"30\" rows=\"3\" id=\""+prop.getName()+"\" "+valType+"></textarea>";
							}
							tdValue+="												<td>"+domain+"</td>"; 
							contentList.add(tdValue);
						}
						contentList.add("											</tr>");
					}
				} else {
					lineStr = lineStr.replace("pageName", packagee.getName().substring(packagee.getName().lastIndexOf(".")+1,packagee.getName().length()));
					lineStr = lineStr.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
					lineStr = lineStr.replace("Mould",(packagee.getClazz().getClazzName()));
					contentList.add(lineStr);
				}

			}
		}
		return contentList;
	}
	public static void main(String[] args) {
		int count=0;
		for(int i=0;i<=3;i++){
			if(i%2==0&&i!=0){
				System.out.println("tr");
			}
		}
	}
	
}
