package cn.labsoft.labos.common.formula.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabFormula extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//公式名
	private String defineUser;//公式定义人
	private String editor;//公式
	private String parameter;//参数
	private String showHtml;
	@Column(name="showHtml")
	public String getShowHtml() {
		return showHtml;
	}
	public void setShowHtml(String showHtml) {
		this.showHtml = showHtml;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	
	@Transient
	@Override
	public String getTableName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getModelName() {
		return "公式定义";
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefineUser() {
		return this.defineUser;
	}

	public void setDefineUser(String defineUser) {
		this.defineUser = defineUser;
	}

	public String getEditor() {
		return this.editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}
}
