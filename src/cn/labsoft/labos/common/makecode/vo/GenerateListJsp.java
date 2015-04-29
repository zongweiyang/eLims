package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

public class GenerateListJsp {
	public static List<String> buildContent(List<String> templateContentList,
			Packagee packagee, Clazz clazz, List<Prop> propList,String name) {
		List<String> contentList = new ArrayList<String>();
		if (null != templateContentList && 0 < templateContentList.size()) {
			for (String lineStr : templateContentList) {
				if (lineStr.trim().equals(Constants.TEMP_JSP_TABLE_INSERT_TAG)) {
					if (null != propList && 0 < propList.size()) {
						for (Prop prop : propList) {
							if (null != prop.getIsShowOnList()
									&& "1".equals(prop.getIsShowOnList())) {
								String sort="";
								if(!StrUtils.isBlankOrNull(prop.getIsSortList())&&"1".equals(prop.getIsSortList())){
									sort="property=\""+prop.getName()+"\"";
								}
								contentList.add("													<th "+sort+">"
										+ prop.getNameChinese() + "</th>");
							}
						}
					}
				} else if (lineStr.trim().equals(
						Constants.TEMP_JSP_TABLE_BODY_INSERT_TAG)) {
					if (null != propList && 0 < propList.size()) {
						int i = 0;
						for (Prop prop : propList) {
							if (null != prop.getIsShowOnList()
									&& "1".equals(prop.getIsShowOnList())) {
								if (i == 0) {
									String clazzPropName = clazz.getClazzName()
											.substring(0, 1).toLowerCase()
											+ clazz.getClazzName().substring(
													1,
													clazz.getClazzName()
															.length());
									contentList
											.add("																<td><a href=\"<%=basePath%>"
													+ packagee.getName().substring(packagee.getName().lastIndexOf(".")+1,packagee.getName().length())
													+ "/"
													+ clazzPropName
													+ "/show"
													+ clazz.getClazzName()
													+ ".action?"
													+ clazzPropName
													+ "Vo.id=${id}\">${"
													+ prop.getName()
													+ "}</a></td>");
								} else {
									contentList.add("																<td>${"
											+ prop.getName() + "}</td>");
								}
							}
							i++;
						}
					}
				} else {
					lineStr = lineStr.replace("pageName", packagee.getName().substring(packagee.getName().lastIndexOf(".")+1,packagee.getName().length()));
					lineStr = lineStr.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
					lineStr = lineStr.replace("Mould",(packagee.getClazz().getClazzName()));
					lineStr = lineStr.replace("MM",name);
					lineStr = lineStr.replace(Constants.TEMP_JSP_LIST_COL9999,
							"colspan=\"" + (propList.size() + 3) + "\"");
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
				List templateContentList = FileOperater.readFileByLines(path+ "\\WebRoot\\utils\\gmt\\read\\flow\\MouldList4Flow.jsp");
				List contentList = GenerateListJsp.buildContent(templateContentList,
						packagee, packagee.getClazz(), listProp,stempName);
				String clazzNameLower = packagee.getClazz().getClazzName().substring(0,1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length());
				String packPath=packagee.getNamePath()+"\\"+packagee.getClazz().getJspName()+"\\"+stempName;
				FileOperater.writeFileByLines(path
						+ "\\WebRoot\\jsp\\"+packPath+"\\"+clazzNameLower+"List.jsp", contentList);
				}
			}
		}
	}
	public static void main(String[] args) {

	}
}
