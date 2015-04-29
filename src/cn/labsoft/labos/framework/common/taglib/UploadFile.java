package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 查看上传文件
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
public class UploadFile extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private boolean delFlag;//是否可以删除
	private String busId;//busId
	private String busType;//busType
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new AComponent(arg0);
	}

	@Override
	protected void populateParams() {
		AComponent jhref = (AComponent) component;
		html = gethtml();
		img = "/img/export.gif";
		A labTag = new A( id,  type,  "html",  value, title,  
			disabled,  onclick,  img,  cssClass, 
			cssStyle, key,  tooltips,  alt,  null,null,html);
		jhref.setLabTag(labTag);
	}
	
	private String gethtml(){
		String html = "<span id=\"upload_"+busId+"\"></span>";	
		html+="<script>uploadList('upload_"+busId+"','"+busId+"','"+busType+"','"+delFlag+"');</script>";
		return html;
	}
	
	public UploadFile(){
		
	}
	public UploadFile(String id, String type, String value,
			String title, boolean disabled,
			String onclick, String img, String cssClass, String cssStyle,
			String key, String tooltips, String alt, 
			String busId,boolean delFlag, String busType) {
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
		this.delFlag = delFlag;
		this.busId = busId;
		this.busType = busType;
	}


	public boolean isDelFlag() {
		return delFlag;
	}

	public void setDelFlag(boolean delFlag) {
		this.delFlag = delFlag;
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