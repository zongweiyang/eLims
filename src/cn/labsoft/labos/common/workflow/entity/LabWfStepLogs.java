package cn.labsoft.labos.common.workflow.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 节点实例化操作日志
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfStepLogs  extends AbstractBasePo{

	private static final long serialVersionUID = 5593674505448301905L;
	private String busId;
	private String processInsId; //流程实例Id
	private LabWfStepIns stepins;      //实例步骤Id
	private String stepName;        //步骤Name
	private String code;            //编码
	private String userName;
	private String userId;
	private String auditMessage;  
	private String auditResult;  
	private String auditTime;
	private String useTime; //耗时
	public String getProcessInsId() {
		return processInsId;
	}
	public void setProcessInsId(String processInsId) {
		this.processInsId = processInsId;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAuditMessage() {
		return auditMessage;
	}
	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	@ManyToOne
	@JoinColumn(name="step_ins_id", nullable=false)
	public LabWfStepIns getStepins() {
		return stepins;
	}
	public void setStepins(LabWfStepIns stepins) {
		this.stepins = stepins;
	}
	
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "节点日志信息";
	}
	
	
}