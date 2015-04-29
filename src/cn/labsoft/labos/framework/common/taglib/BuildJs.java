package cn.labsoft.labos.framework.common.taglib;


/**
 * 
 * <strong>Title : BuildJs </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 1:12:28 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Carson Liu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class BuildJs {
	public static String jsReturn(String function){
		String returnStr = "";
		if(null != function
				&&!function.toUpperCase().replaceAll(" ", "").contains("RETURNFALSE")){
			returnStr = jsReturn();
		}
		return returnStr;
	}
	
	public static String jsReturn(){
		return "return false;";
	}
	
	public static String jsDown(String fileName,String fileUrl){
		return "down('"+fileName+"','"+fileUrl+"');";
	}
	
	public static String jsImport(String fileName,String fileUrl,String params){
		return "importFile('"+fileName+"','"+fileUrl+"','"+params+"');";
	}
	
	public static String jsUpload(String tittle,String busId,String busType,String fileTypes){
		return "uploadFile('"+tittle+"','"+busId+"','"+busType+"','"+fileTypes+"');";
	}
	
	public static String jsGet(String uri){
		return "nextUrl('"+uri+"');";
	}
	
	public static String jsPost(String uri){
		return "goAction('"+uri+"');";
	}
	
	public static String jsSubmitAction(){
		return "submitAction();";
	}
	
	public static String backList(){
		return "backList();";
	}
	
}
