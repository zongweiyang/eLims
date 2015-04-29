package cn.labsoft.labos.common.workflow.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import javax.persistence.Entity;
/**
 * 流程配置
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfConfig extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//名称
	private String code;//编码
	private LabWfProcess wfProcess;//流程Id
	private String remark;//备注
	private String funId;//业务
	private String funName;//业务
	private String processName;//流程

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
	
	@ManyToOne
	@JoinColumn(name="process_id", nullable=false)
	public LabWfProcess getWfProcess() {
		return wfProcess;
	}
	public void setWfProcess(LabWfProcess wfProcess) {
		this.wfProcess = wfProcess;
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

	@Transient
	@Override
	public String getModelName() {
		return "流程管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "流程配置";
	}
}
