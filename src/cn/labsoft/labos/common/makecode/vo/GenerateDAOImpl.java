package cn.labsoft.labos.common.makecode.vo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * <strong>Title : GenerateIDAO </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 12, 2014 3:55:37 PM  </strong>. <br>
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

public class GenerateDAOImpl {

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
	public static void createEntityDAOImpl(Packagee packagee,List<Prop> listProp,String path) throws GlobalException{
		List<String> list=new ArrayList<String>();
		List<String> copyList=FileOperater.readFileByLines(path+"\\WebRoot\\utils\\gmt\\read\\MouldDAOImpl.java");
		String pageSrc="cn.labsoft.labos."+packagee.getName()+".dao.impl";
		String entitySrc="cn.labsoft.labos."+packagee.getName()+".entity."+packagee.getClazz().getClazzName()+"";
		String iDaoSrc="cn.labsoft.labos."+packagee.getName()+".dao.I"+packagee.getClazz().getClazzName()+"DAO";
		if (null != copyList && 0 < copyList.size()) {
			for (String lineStr : copyList) {
				lineStr = lineStr.replace("packSrc", pageSrc);
				lineStr = lineStr.replace("entitySrc", entitySrc);
				lineStr = lineStr.replace("iDaoSrc", iDaoSrc);
				lineStr = lineStr.replace("Mould",packagee.getClazz().getClazzName());
				lineStr = lineStr.replace("mould",packagee.getClazz().getClazzName().substring(0, 1).toLowerCase()+packagee.getClazz().getClazzName().substring(1,packagee.getClazz().getClazzName().length()));
				list.add(lineStr);
			}
		}
		path+="\\src\\cn\\labsoft\\labos\\"+packagee.getNamePath()+"\\dao\\impl\\"+packagee.getClazz().getClazzName()+"DAOImpl.java";
		FileOperater.writeFileByLines(path, list);
	}
	

}
