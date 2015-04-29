package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * 自定义标签,控制按钮和超链
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
public class A extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	
	private String uri;//uri
	private String target;//target
	private String href;//href

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new AComponent(arg0);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		AComponent jhref = (AComponent) component;
		A labTag = new A( id,  type,  uri,  value, title,  
				disabled, onclick,  img,  cssClass, 
				cssStyle, key,  tooltips,  alt, target,href,html);
		labTag.setValue(jhref.getText(value));
		jhref.setLabTag(labTag);
	}
	public A(){
		
	}

	public A(String id, String type, String uri, String value,
			String title, boolean disabled, 
			String onclick, String img, String cssClass, String cssStyle,
			String key, String tooltips, String alt, 
			String target,String href,String html) {
		super();
		this.id = id;
		this.type = type;
		this.uri = uri;
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
		this.target = target;
		this.href = href;
		this.html = html;
	}
	
}