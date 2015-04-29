package cn.labsoft.labos.common.formula.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabFormulaVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "公式名")
	private String name;
	@ExcelAnnotation(exportName = "公式定义人")
	private String defineUser;
	@ExcelAnnotation(exportName = "公式")
	private String editor;
	@ExcelAnnotation(exportName = "参数")
	private String parameter;//参数
	@ExcelAnnotation(exportName = "页面显示")
	private String showHtml;
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getShowHtml() {
		return showHtml;
	}
	public void setShowHtml(String showHtml) {
		this.showHtml = showHtml;
	}

}
