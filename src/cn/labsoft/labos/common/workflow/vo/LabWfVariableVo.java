package cn.labsoft.labos.common.workflow.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * WfVariabledef entity.
 * 变量定义
 * @author Quinn
 */

public class LabWfVariableVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4896940590051098325L;
	// Fields
	@ExcelAnnotation(exportName = "主键Id")
	private String id;
	@ExcelAnnotation(exportName = "流程Id")
	private String processId;
	@ExcelAnnotation(exportName = "流程名称")
	private String processName;
	@ExcelAnnotation(exportName = "变量名")
	private String name;
	@ExcelAnnotation(exportName = "备注")
	private String comment;
	@ExcelAnnotation(exportName = "值")
	private String value;
	
	// Property accessors

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}


}