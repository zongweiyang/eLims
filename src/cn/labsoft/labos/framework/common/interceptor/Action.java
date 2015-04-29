package cn.labsoft.labos.framework.common.interceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * 获取action
 * <strong>Title : Action </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 1:56:27 PM  </strong>. <br>
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
public class Action {
	final static String KEY = "-1";
	
	public static boolean isListPage(String actionName) {
		return !KEY.equals(getAction(actionName,LogCommonInformation.LIST_PAGE_ARRAYS));
	}
	
	public static boolean isTreePage(String actionName) {
		return !KEY.equals(getAction(actionName,LogCommonInformation.OTHER_PAGE_ARRAYS));
	}
	
	public static boolean isSelectPage(String actionName) {
		return !KEY.equals(getAction(actionName,LogCommonInformation.SELECT_ACTION_ARRAYS));
	}
	
	public static boolean isAJaxPage(String actionName) {
		boolean result = false;
		for(String tempAction:LogCommonInformation.AJAX_ACTION_ARRAYS){
			if(actionName.toUpperCase().startsWith(tempAction.toUpperCase())){
				result = true;
				break;
			}
		}
		return result;
	}
	
	public static String getAction(String actionName){
		List<String> list = new ArrayList<String>();
		list.addAll(Arrays.asList(LogCommonInformation.ADD_ACTION_ARRAYS));
		list.addAll(Arrays.asList(LogCommonInformation.DEL_ACTION_ARRAYS));
		list.addAll(Arrays.asList(LogCommonInformation.LIST_PAGE_ARRAYS));
		list.addAll(Arrays.asList(LogCommonInformation.OTHER_PAGE_ARRAYS));
		list.addAll(Arrays.asList(LogCommonInformation.UPDATE_ACTION_ARRAYS));
		
		return getAction(actionName,list.toArray(new String[0]));
	}
	
	public static String getAction(String actionName,String[] params) {
		String result = KEY;
		for(String action:params){
			if(actionName.toUpperCase().startsWith(action.toUpperCase())
					||actionName.toUpperCase().endsWith(action.toUpperCase())){
				result = action;
				break;
			}
		}
		for(Map.Entry<String, String[]> entry: LogCommonInformation.ACTION_MAP.entrySet()) { 
			String[] values = entry.getValue();
			if(null!=values&&0<values.length){
				for(String value : values){
					if(value.equals(result)){
						result = entry.getKey();
						break;
					}
				}
			}
		}
		return result;
	}
	
	public static boolean extNameSpace(String namespace) {
		boolean key = false;
		for(String tempNamespace:LogCommonInformation.EXCEPT_NAMESPACE_ARRAYS){
			if(tempNamespace.equalsIgnoreCase(namespace)){
				key = true;
				break;
			}
		}
		return key;
		
	}
	
	public static boolean isFormAction(String action) {
		boolean result = false;
		for(String tempAction:LogCommonInformation.FORM_FUNCTION_ARRAYS){
			if(action.toUpperCase().startsWith(tempAction.toUpperCase())
					||action.toUpperCase().endsWith(tempAction.toUpperCase())){
				result = true;
				break;
			}
		}
		return result;
	}
}
