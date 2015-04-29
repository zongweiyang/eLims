package cn.labsoft.labos.common.workflow.entity;


import javax.persistence.Entity;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 线路关联变量
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfPathVar  extends AbstractBasePo{

	private static final long serialVersionUID = -3858499659584569093L;

	private String processId;
	private String pathId;
	private String pathName;
	private String variableId;  //变量
	private String variableName;
	private String operator;             //符号
	private String value;                //值

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
	public String getVariableId() {
		return variableId;
	}
	public void setVariableId(String variableId) {
		this.variableId = variableId;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "线路变量信息";
	}

}