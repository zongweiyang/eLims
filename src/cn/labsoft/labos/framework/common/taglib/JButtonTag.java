package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class JButtonTag extends ComponentTagSupport {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String btFunctionUrl;
	private String btFunctionType;
	private String btJsFunctions;
	private String btAttribute;
	private String btFunctionValue;
	private boolean btIsDeafultImg;
	private String btImgSrc;
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new JButtonComponent(arg0);
	}
	@Override
	protected void populateParams() {
		super.populateParams();
		JButtonComponent jbutton = (JButtonComponent) component;
		jbutton.setBtFunctionType(btFunctionType);
		jbutton.setBtFunctionUrl(btFunctionUrl);
		jbutton.setBtJsFunctions(btJsFunctions);
		jbutton.setBtAttribute(btAttribute);
		jbutton.setBtFunctionValue(btFunctionValue);
		jbutton.setBtIsDeafultImg(btIsDeafultImg);
		jbutton.setBtImgSrc(btImgSrc);
	}
	
	public String getBtFunctionUrl() {
		return btFunctionUrl;
	}
	public void setBtFunctionUrl(String btFunctionUrl) {
		this.btFunctionUrl = btFunctionUrl;
	}
	public String getBtFunctionType() {
		return btFunctionType;
	}
	public void setBtFunctionType(String btFunctionType) {
		this.btFunctionType = btFunctionType;
	}
	public String getBtAttribute() {
		return btAttribute;
	}
	public void setBtAttribute(String btAttribute) {
		this.btAttribute = btAttribute;
	}
	public String getBtJsFunctions() {
		return btJsFunctions;
	}
	public void setBtJsFunctions(String btJsFunctions) {
		this.btJsFunctions = btJsFunctions;
	}
	public String getBtFunctionValue() {
		return btFunctionValue;
	}
	public void setBtFunctionValue(String btFunctionValue) {
		this.btFunctionValue = btFunctionValue;
	}
	public boolean getBtIsDeafultImg() {
		return btIsDeafultImg;
	}
	public void setBtIsDeafultImg(boolean btIsDeafultImg) {
		this.btIsDeafultImg = btIsDeafultImg;
	}
	public String getBtImgSrc() {
		return btImgSrc;
	}
	public void setBtImgSrc(String btImgSrc) {
		this.btImgSrc = btImgSrc;
	}

}