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
public class Down extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String fileUri;//文件路径
	private String fileName;//文件名称
	private String href;
	private String uri;

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new AComponent(arg0);
	}

	@Override
	protected void populateParams() {
		AComponent jhref = (AComponent) component;
		img = "/img/export.gif";
		onclick = BuildJs.jsDown(fileName, fileUri);
		A labTag = new A( id,  type, uri,  value, title,  
			disabled,  onclick,  img,  cssClass, 
			cssStyle, key,  tooltips,  alt, null,href,html);
		jhref.setLabTag(labTag);
	}
	
	public Down(){
		
	}
	public Down(String id, String type, String value,
			String title, boolean disabled,
			String onclick, String img, String cssClass, String cssStyle,
			String key, String tooltips, String alt, 
			String fileUri,String fileName,String href,String uri) {
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
		this.fileUri = fileUri;
		this.fileName = fileName;
		this.href = href;
		this.uri = uri;
		
	}

	public String getFileUri() {
		return fileUri;
	}

	public void setFileUri(String fileUri) {
		this.fileUri = fileUri;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}



}