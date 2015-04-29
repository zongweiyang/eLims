package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 上传
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
public class Upload extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String fileTypes;//可上传文件类型
	private String callFunction;//回调函数
	private String busId;//导入action
	private String busType;//模板名称
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new AComponent(arg0);
	}

	@Override
	protected void populateParams() {
		AComponent jhref = (AComponent) component;
		onclick = BuildJs.jsUpload(value,busId, busType, fileTypes);
		img = "/img/export.gif";
		A labTag = new A( id,  type,  "export",  value, title,  
			disabled,   onclick,  img,  cssClass, 
			cssStyle, key,  tooltips,  alt,  null,null,html);
		jhref.setLabTag(labTag);
	}
	
	public Upload(){
		
	}
	public Upload(String id, String type, String value,
			String title, boolean disabled,
			String onclick, String img, String cssClass, String cssStyle,
			String key, String tooltips, String alt, 
			String busId,String callFunction,String fileTypes, String busType) {
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
		this.fileTypes = fileTypes;
		this.callFunction = callFunction;
		this.busId = busId;
		this.busType = busType;
	}

	public String getFileTypes() {
		return fileTypes;
	}

	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}

	public String getCallFunction() {
		return callFunction;
	}

	public void setCallFunction(String callFunction) {
		this.callFunction = callFunction;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

}