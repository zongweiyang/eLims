package cn.labsoft.labos.common.workflow.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



/**
 * WfVariabledef entity.
 * 线路上关联的变量定义
 * @author Quinn
 */

@SuppressWarnings("serial")
public class LabWfPathVarVo extends BaseVo{

	@ExcelAnnotation(exportName = "主键id")
	private String id;
	@ExcelAnnotation(exportName = "流程")
	private String processId;
	@ExcelAnnotation(exportName = "线路")
	private String pathId;
	@ExcelAnnotation(exportName = "线路")
	private String pathName;
	@ExcelAnnotation(exportName = "变量")
	private String variableId;  //变量
	@ExcelAnnotation(exportName = "变量")
	private String variableName; //变量
	@ExcelAnnotation(exportName = "符合")
	private String operator;    //符号 '1':'=','2':'>','3':'>=','4':'<','5':'<=','6':'!='
	@ExcelAnnotation(exportName = "值")
	private String value;                //值
	public String getId() {
		return id;
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
	
	public String getVariableName() {
		return variableName;
	}
	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getPathId() {
		return pathId;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getVariableId() {
		return variableId;
	}
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	


}