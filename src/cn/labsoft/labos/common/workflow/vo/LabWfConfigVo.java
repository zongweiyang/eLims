package cn.labsoft.labos.common.workflow.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabWfConfigVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "名称")
	private String name;
	@ExcelAnnotation(exportName = "编码")
	private String code;
	@ExcelAnnotation(exportName = "流程Id")
	private String processId;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "业务")
	private String funId;
	@ExcelAnnotation(exportName = "业务")
	private String funName;
	@ExcelAnnotation(exportName = "流程")
	private String processName;

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

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getProcessId() {
		return this.processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFunId() {
		return this.funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return this.funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getProcessName() {
		return this.processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

}
