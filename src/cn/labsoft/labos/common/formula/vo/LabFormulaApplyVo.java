package cn.labsoft.labos.common.formula.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabFormulaApplyVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "公式参数")
	private String parameter;
	@ExcelAnnotation(exportName = "公式名称")
	private String labFormulaName;
	@ExcelAnnotation(exportName = "公式参数对应页面ID")
	private String labFormulaId;
	@ExcelAnnotation(exportName = "公式内容")
	private String name;
	@ExcelAnnotation(exportName = "参数对应Id")
	private String parameterId;
	@ExcelAnnotation(exportName = "应用页面路径")
	private String jspUrl;
	@ExcelAnnotation(exportName = "公式")
	private String labFormulaEditor;
	@ExcelAnnotation(exportName = "引入页面ID")
	private String labPageEditorId;
	@ExcelAnnotation(exportName = "对应页面显示值ID")
	private String valueId;
	private String labFormulaOldId;
	private String names[];
	private String result;
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
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

	public String getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}

	public String getJspUrl() {
		return this.jspUrl;
	}

	public void setJspUrl(String jspUrl) {
		this.jspUrl = jspUrl;
	}

	public String getLabFormulaId() {
		return labFormulaId;
	}
	public void setLabFormulaId(String labFormulaId) {
		this.labFormulaId = labFormulaId;
	}
	public String getParameter() {
		return parameter;
	}
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
	public String getLabFormulaName() {
		return labFormulaName;
	}
	public void setLabFormulaName(String labFormulaName) {
		this.labFormulaName = labFormulaName;
	}
	public String[] getNames() {
		return names;
	}
	public void setNames(String[] names) {
		this.names = names;
	}
	public String getLabFormulaEditor() {
		return labFormulaEditor;
	}
	public void setLabFormulaEditor(String labFormulaEditor) {
		this.labFormulaEditor = labFormulaEditor;
	}
	public String getLabPageEditorId() {
		return labPageEditorId;
	}
	public void setLabPageEditorId(String labPageEditorId) {
		this.labPageEditorId = labPageEditorId;
	}
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	public String getLabFormulaOldId() {
		return labFormulaOldId;
	}
	public void setLabFormulaOldId(String labFormulaOldId) {
		this.labFormulaOldId = labFormulaOldId;
	}
	

}
