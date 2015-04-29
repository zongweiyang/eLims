package cn.labsoft.labos.common.makecode.vo;

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

public class GenerateAction {

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
	public static void createAction(Packagee packagee,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		List<String> copyList=new ArrayList<String>();
		if(packagee.getIsFlow().equals(Constants_Base.Y)){
			copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\flow\\MouldAction.java");
		}else{
			copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\MouldAction.java");
		}
		String pageSrc="cn.labsoft.labos."+packagee.getName()+".action";
		String IServiceSrc="cn.labsoft.labos."+packagee.getName()+".service.I"+packagee.getClazz().getClazzName()+"Service";
		String voSrc="cn.labsoft.labos."+packagee.getName()+".vo."+packagee.getClazz().getClazzName()+"Vo";
		if (null != copyList && 0 < copyList.size()) {
			for (String lineStr : copyList) {
				lineStr = lineStr.replace("pageSrc", pageSrc);
				lineStr = lineStr.replace("IServiceSrc",IServiceSrc);
				lineStr = lineStr.replace("voSrc",voSrc);
				lineStr = lineStr.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
				lineStr = lineStr.replace("mouldVo",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()))+"Vo");
				lineStr = lineStr.replace("MouldVo",(packagee.getClazz().getClazzName()+"Vo"));
				lineStr = lineStr.replace("Mould",(packagee.getClazz().getClazzName()));
				if(packagee.getIsFlow().equals(Constants_Base.Y)&&lineStr.trim().replace(" ", "").equals("//WF_FLOW_CODE")){
					if(packagee.stepNameList!=null&&packagee.stepNameList.size()>0){
						for(String name:packagee.stepNameList){
							if(!StrUtils.isBlankOrNull(name)){
								List<String> actionList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\flow\\action.java");
								if(actionList!=null&&actionList.size()>0){
									for(String actionLine:actionList){
										actionLine = actionLine.replace("Mould",packagee.getClazz().getClazzName());
										actionLine = actionLine.replace("mouldVo",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()))+"Vo");
										actionLine=actionLine.replace("MM", name);
										actionLine = actionLine.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
										list.add(actionLine);
									}
									
								}
							}
						}
					}
				}
				list.add(lineStr);
			}
		}
		path+="\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\action\\"+packagee.getClazz().getClazzName()+"Action.java";
		FileOperater.writeFileByLines(path, list);
	}
	

}
