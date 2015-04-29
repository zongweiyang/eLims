package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 导出
 * <strong>Title : Tag </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 1, 2014 10:36:55 AM  </strong>. <br>
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
public class Import extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String source;//模板路径
	private String fileName;//模板名称
	private String callFunction;//回调函数
	private String execAction;//导入action
	private String params;//模板名称
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new AComponent(arg0);
	}

	@Override
	protected void populateParams() {
		AComponent jhref = (AComponent) component;
		if(null==params)
			params = "";
		params += "execAction="+execAction;
		params +="&callFunction="+callFunction;
		onclick = BuildJs.jsImport(fileName, source, params);
		img = "/img/export.gif";
		A labTag = new A( id,  type,  "export",  value, title,  
			disabled,  onclick,  img,  cssClass, 
			cssStyle, key,  tooltips,  alt, null,null,html);
		jhref.setLabTag(labTag);
	}
	
	public Import(){
		
	}
	public Import(String id, String type, String value,
			String title, boolean disabled, 
			String onclick, String img, String cssClass, String cssStyle,
			String key, String tooltips, String alt, 
			String source,String callFunction,String params, String execAction) {
		super();
		this.id = id;
		this.type = type;
		this.value = value;
		this.title = title;
		this.disabled = disabled;
		this.onclick = onclick;
		this.img = img;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.key = key;
		this.tooltips = tooltips;
		this.alt = alt;
		this.source = source;
		this.callFunction = callFunction;
		this.execAction = execAction;
		this.params = params;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCallFunction() {
		return callFunction;
	}

	public void setCallFunction(String callFunction) {
		this.callFunction = callFunction;
	}

	public String getExecAction() {
		return execAction;
	}

	public void setExecAction(String execAction) {
		this.execAction = execAction;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	
}