package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.constants.Constants_Base;
/**
 * 
 * <strong>Title : GenerateIService </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 12, 2014 3:38:54 PM  </strong>. <br>
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

public class GenerateIService {

	public static String serviceUpdateCode="	public boolean updateMould4MM(MouldVo mouldVo) throws GlobalException;";
	
	  public static String serviceListCode="     public PageResult getMouldPR4MM(MouldVo mouldVo,PageResult pageResult) throws GlobalException;";

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
	public static void createEntityIService(Packagee packagee,List<Prop> listProp,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		List<String> copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\IMouldService.java");
		String pageSrc="cn.labsoft.labos."+packagee.getName()+".service";
		String voSrc="cn.labsoft.labos."+packagee.getName()+".vo."+packagee.getClazz().getClazzName()+"Vo";
		if (null != copyList && 0 < copyList.size()) {
			for (String lineStr : copyList) {
				lineStr = lineStr.replace("packageSrc", pageSrc);
				lineStr = lineStr.replace("voSrc", voSrc);
				lineStr = lineStr.replace("Mould",packagee.getClazz().getClazzName());
				lineStr = lineStr.replace("mouldVo",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()))+"Vo");
				if(packagee.getIsFlow().equals(Constants_Base.Y)&&lineStr.trim().replace(" ", "").equals("}")){
					if(packagee.stepNameList!=null&&packagee.stepNameList.size()>0){
						for(String name:packagee.stepNameList){
							if(!StrUtils.isBlankOrNull(name)){
								String serviceUpdate=serviceUpdateCode;
								serviceUpdate = serviceUpdate.replace("Mould",packagee.getClazz().getClazzName());
								serviceUpdate = serviceUpdate.replace("mouldVo",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()))+"Vo");
								serviceUpdate=serviceUpdate.replace("MM", name);
								String serviceList=serviceListCode;
								serviceList = serviceList.replace("Mould",packagee.getClazz().getClazzName());
								serviceList = serviceList.replace("mouldVo",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()))+"Vo");
								serviceList=serviceList.replace("MM", name);
								list.add(serviceUpdate);
								list.add("");
								list.add(serviceList);
								list.add("");
							}
						}
					}
				}
				list.add(lineStr);
			}
		}
		path+="\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\service\\I"+packagee.getClazz().getClazzName()+"Service.java";
		FileOperater.writeFileByLines(path, list);
	}
	

}
