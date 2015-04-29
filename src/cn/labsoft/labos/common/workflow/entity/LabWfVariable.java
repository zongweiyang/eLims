package cn.labsoft.labos.common.workflow.entity;


import javax.persistence.Entity;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 变量定义
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfVariable  extends AbstractBasePo{

	private static final long serialVersionUID = -3858499659584569093L;

	private String processId;
	private String processName;
	private String name;
	private String value;
	private String comment;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "变量信息";
	}
}