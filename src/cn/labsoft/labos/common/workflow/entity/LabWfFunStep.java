package cn.labsoft.labos.common.workflow.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 功能步骤持久
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfFunStep extends AbstractBasePo{

	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = -5104091847260494542L;
	private LabWfProcess process;  //流程定义Id
	private String processName; //流程定义Id
	private String stepId;      //功能Id,用于同步功能的部分信息
	private String stepName;
	private String stepCode;
	private String stepUrl;
	private String userId;
	private String stepType;
	
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getStepCode() {
		return stepCode;
	}
	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStepUrl() {
		return stepUrl;
	}
	public void setStepUrl(String stepUrl) {
		this.stepUrl = stepUrl;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	@ManyToOne
	@JoinColumn(name="process_id", nullable=false)
	public LabWfProcess getProcess() {
		return process;
	}
	public void setProcess(LabWfProcess process) {
		this.process = process;
	}
	public String getStepType() {
		return stepType;
	}
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "功能节点信息";
	}
}