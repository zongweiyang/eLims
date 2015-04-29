package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;

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

public class GenerateServiceImpl {

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
	public static void createServiceImpl(Packagee packagee,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		List<String> copyList=new ArrayList<String>();
		if(packagee.getIsFlow().equals(Constants_Base.Y)){
			copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\flow\\MouldServiceImpl.java");
		}else{
			copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\MouldServiceImpl.java");
		}
		String pageSrc="cn.labsoft.labos."+packagee.getName()+".service.impl";
		String iDaoSrc="cn.labsoft.labos."+packagee.getName()+".dao.I"+packagee.getClazz().getClazzName()+"DAO";
		String entitySrc="cn.labsoft.labos."+packagee.getName()+".entity."+packagee.getClazz().getClazzName();
		String IServiceSrc="cn.labsoft.labos."+packagee.getName()+".service.I"+packagee.getClazz().getClazzName()+"Service";
		String voSrc="cn.labsoft.labos."+packagee.getName()+".vo."+packagee.getClazz().getClazzName()+"Vo";
		if (null != copyList && 0 < copyList.size()) {
			for (String lineStr : copyList) {
				lineStr = lineStr.replace("pageSrc", pageSrc);
				lineStr = lineStr.replace("IDaoSrc", iDaoSrc);
				lineStr = lineStr.replace("entitySrc",entitySrc);
				lineStr = lineStr.replace("IServiceSrc",IServiceSrc);
				lineStr = lineStr.replace("voSrc",voSrc);
				lineStr = lineStr.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
				lineStr = lineStr.replace("Mould",(packagee.getClazz().getClazzName()));
				lineStr = lineStr.replace("WF_CODE_FLOW",""+packagee.getFlowCode()+"");
				if(packagee.getIsFlow().equals(Constants_Base.Y)&&lineStr.trim().replace(" ", "").equals("//WF_FLOW_CODE")){
					if(packagee.stepNameList!=null&&packagee.stepNameList.size()>0){
						for(String name:packagee.stepNameList){
							if(!StrUtils.isBlankOrNull(name)){
								List<String> serviceList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\flow\\serviceImpl.java");
								if(serviceList!=null&&serviceList.size()>0){
									for(String serviceUpdate:serviceList){
										serviceUpdate = serviceUpdate.replace("Mould",packagee.getClazz().getClazzName());
										serviceUpdate = serviceUpdate.replace("mouldVo",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()))+"Vo");
										serviceUpdate = serviceUpdate.replace("mould",(packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length())));
										serviceUpdate=serviceUpdate.replace("MM", name);
										serviceUpdate = serviceUpdate.replace("WF_CODE_FLOW",""+packagee.getFlowCode()+"");
										list.add(serviceUpdate);
									}
									
								}
							}
						}
					}
				}
				list.add(lineStr);
			}
		}
		path+="\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\service\\impl\\"+packagee.getClazz().getClazzName()+"ServiceImpl.java";
		FileOperater.writeFileByLines(path, list);
	}
	

}
