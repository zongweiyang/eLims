package cn.labsoft.labos.framework.common.taglib;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;

import com.opensymphony.xwork2.util.ValueStack;
/**
 * Combox
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
public class Combox extends Base {
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	

	@Override
	public Component getBean(ValueStack arg0, HttpServletRequest arg1,
			HttpServletResponse arg2) {
		return new ComboxComponent(arg0);
	}

	@Override
	protected void populateParams() {
		super.populateParams();
		ComboxComponent combox = (ComboxComponent) component;
		Combox labComboxTag = new Combox(id, name,data, value,
				title, disabled,cssClass, cssStyle,
				key, tooltips, alt);
		labComboxTag.setValue(combox.getText(value));
		combox.setLabComboxTag(labComboxTag);
		
	}
	public Combox(){
		
	}

	public Combox(String id, String name, String data,String value,
			String title, boolean disabled, String cssClass, String cssStyle,
			String key, String tooltips, String alt) {
		super();
		this.id = id;
		this.value = value;
		this.data =data;
		this.title = title;
		this.disabled = disabled;
		this.cssClass = cssClass;
		this.cssStyle = cssStyle;
		this.key = key;
		this.tooltips = tooltips;
		this.alt = alt;
	}

}