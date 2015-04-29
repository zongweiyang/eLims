package cn.labsoft.labos.framework.common.interceptor;

import java.util.HashMap;
import java.util.Map;

public class LogCommonInformation {

	public static final String OTHER="other";
	public static final String QUERY="query";
	public static final String ADD = "add";
	public static final String UPDATE = "update";
	public static final String DELETE = "delete";
	public static final String SAVE = "save";
	public static final String COPY = "copy";
	public static final String LIST = "list";
	public static final String TREE = "tree";
	public static final String FRAME = "frame";
	
	public static final String SAVEORUPDATE = "saveOrUpdate";

	public static final String ADD1 = "添加";
	public static final String UPDATE1 = "更新";
	public static final String DELETE1 = "删除";
	public static final String SAVE1 = "添加";
	public static final String SAVEORUPDATE1 = "更新";
	
	public static final String[] LIST_PAGE_ARRAYS = {"LIST"};
	public static final String[] OTHER_PAGE_ARRAYS = {"FRAME","preTree","TREE","ajaxTree"};
	public static final String[] ADD_ACTION_ARRAYS = {"preAdd",ADD,SAVE,COPY};
	public static final String[] UPDATE_ACTION_ARRAYS = {SAVEORUPDATE,UPDATE};
	public static final String[] DEL_ACTION_ARRAYS = {DELETE,"DEL"};
	
	public static final String[] FORM_FUNCTION_ARRAYS = {ADD,UPDATE,SAVE,SAVEORUPDATE,COPY};
	
	public static final String[] LIST_PAGE_ACTION = {LIST,TREE,FRAME};
	
	//例外的namespace
	public static final String[] EXCEPT_NAMESPACE_ARRAYS = {"/message/messageMain","/lab/user"};
	//弹层
	public static final String[] SELECT_ACTION_ARRAYS = {"select"};
	//ajax
	public static final String[] AJAX_ACTION_ARRAYS = {"ajax"};
	
	public static final Map<String,String[]> ACTION_MAP = new HashMap<String,String[]>();
	static{
		ACTION_MAP.put(QUERY, LIST_PAGE_ARRAYS);
		ACTION_MAP.put(OTHER, OTHER_PAGE_ARRAYS);
		ACTION_MAP.put(ADD, ADD_ACTION_ARRAYS);
		ACTION_MAP.put(UPDATE, UPDATE_ACTION_ARRAYS);
		ACTION_MAP.put(DELETE, DEL_ACTION_ARRAYS);
	}
}
