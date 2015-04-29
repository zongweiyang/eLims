package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.opensymphony.xwork2.util.ValueStack;

public class JHrefTag extends ComponentTagSupport {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String hrFunctionUrl;
	private String hrFunctionType;
	private String hrJsFunctions;
	private String hrAttribute;
	private String hrFunctionValue;
	private boolean hrIsInterval;
	
	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new JHrefComponent(arg0);
	}
	@Override
	protected void populateParams() {
		super.populateParams();
		JHrefComponent jhref = (JHrefComponent) component;
		jhref.setHrFunctionType(hrFunctionType);
		jhref.setHrFunctionUrl(hrFunctionUrl);
		jhref.setHrJsFunctions(hrJsFunctions);
		jhref.setHrAttribute(hrAttribute);
		jhref.setHrFunctionValue(hrFunctionValue);
		jhref.setHrIsInterval(hrIsInterval);
	}
	
	public String getHrFunctionUrl() {
		return hrFunctionUrl;
	}
	public void setHrFunctionUrl(String hrFunctionUrl) {
		this.hrFunctionUrl = hrFunctionUrl;
	}
	public String getHrFunctionType() {
		return hrFunctionType;
	}
	public void setHrFunctionType(String hrFunctionType) {
		this.hrFunctionType = hrFunctionType;
	}
	public String getHrAttribute() {
		return hrAttribute;
	}
	public void setHrAttribute(String hrAttribute) {
		this.hrAttribute = hrAttribute;
	}
	public String getHrJsFunctions() {
		return hrJsFunctions;
	}
	public void setHrJsFunctions(String hrJsFunctions) {
		this.hrJsFunctions = hrJsFunctions;
	}
	public String getHrFunctionValue() {
		return hrFunctionValue;
	}
	public void setHrFunctionValue(String hrFunctionValue) {
		this.hrFunctionValue = hrFunctionValue;
	}
	public boolean isHrIsInterval() {
		return hrIsInterval;
	}
	public void setHrIsInterval(boolean hrIsInterval) {
		this.hrIsInterval = hrIsInterval;
	}
	

}