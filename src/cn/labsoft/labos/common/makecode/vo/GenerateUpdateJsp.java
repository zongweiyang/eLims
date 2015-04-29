package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

public class GenerateUpdateJsp {
	public static List<String> buildContent(List<String> templateContentList,
			Packagee packagee, Clazz clazz, List<Prop> propList,String stepName,String isFlows) {
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
							if(packagee.getIsFlow().equals("Y")&&isFlows.equals("Y")){
								tdValue+="												<td>"+"${"+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"}</td>";
							}else{
							String domain = "<input type=\"text\" value=\"${"+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"}\" name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" id=\""+prop.getName()+"\" "+valType+" />";
							if(null!=prop.getShowType()&&"2".equals(prop.getShowType())){//日期控件
								domain = "<input type=\"text\" class=\"Wdate\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true})\" value=\"${"+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"}\" id=\""+prop.getName()+"\" "+valType+" name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" id=\""+prop.getName()+"\" "+valType+" />";
							}else if(null!=prop.getShowType()&&"3".equals(prop.getShowType())){//时间控件
								domain = "<input type=\"text\" class=\"Wdate\" onFocus=\"WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true})\" value=\"${"+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"}\" id=\""+prop.getName()+"\" "+valType+" name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" id=\""+prop.getName()+"\" "+valType+" />";
							}else if(null!=prop.getShowType()&&"4".equals(prop.getShowType())){//文本域
								domain = "<textarea name=\""+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"\" cols=\"30\" rows=\"3\" id=\""+prop.getName()+"\" "+valType+">${"+clazz.getClazzName().substring(0,1).toLowerCase()+clazz.getClazzName().substring(1, clazz.getClazzName().length())+"Vo."+prop.getName()+"}</textarea>";
							}
							tdValue+="												<td>"+domain+"</td>";
							}
							contentList.add(tdValue);
						}
						contentList.add("											</tr>");
					}
				}  else {
					lineStr = lineStr.replace("pageName", packagee.getName().substring(packagee.getName().lastIndexOf(".")+1,packagee.getName().length()));
					lineStr = lineStr.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
					lineStr = lineStr.replace("Mould",(packagee.getClazz().getClazzName()));
					lineStr = lineStr.replace("MM",stepName);
					contentList.add(lineStr);
				}

			}
		}
		return contentList;
	}
	public static void runFlowList(Packagee packagee,String path,List<Prop> listProp) throws GlobalException{
		if(!StrUtils.isBlankOrNull(packagee.getIsFlow())&&packagee.getIsFlow().equals("Y")&&packagee.getStepNameList()!=null&&packagee.getStepNameList().size()>0){
			for(String stempName:packagee.getStepNameList()){
				if(!StrUtils.isBlankOrNull(stempName)){
				List templateContentList = templateContentList = FileOperater.readFileByLines(path+ "\\WebRoot\\utils\\gmt\\read\\flow\\mouldUpdate4Flow.jsp");
				List contentList = GenerateUpdateJsp.buildContent(templateContentList,
						packagee, packagee.getClazz(), listProp,stempName,"Y");
				String clazzNameLower = packagee.getClazz().getClazzName().substring(0,1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length());
				String packPath=packagee.getNamePath()+"\\"+packagee.getClazz().getJspName()+"\\"+stempName;
				FileOperater.writeFileByLines(path
						+ "\\WebRoot\\jsp\\"+packPath+"\\"+clazzNameLower+"Update.jsp", contentList);
				}
			}
		}
	}
	public static void main(String[] args) {
	

	}
}
