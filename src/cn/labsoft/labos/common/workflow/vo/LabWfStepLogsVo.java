package cn.labsoft.labos.common.workflow.vo;

import javax.persistence.Entity;
import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * 
 * <strong>Title : 每个节点实例化操作日志 </strong>. <br>
 * <strong>Description :</strong> <br>
 * <strong>Create on : Feb 26, 2014 1:45:03 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Quinn Qu <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Entity
public class LabWfStepLogsVo extends BaseVo{

	private static final long serialVersionUID = 5593674505448301905L;
	private String busId;
	private String processInsId; //流程实例Id
	private String stepinsId;      //实例步骤Id
	private String stepName;        //步骤Name
	private String code;            //编码
	private String userName;
	private String userId;
	private String useTime; //耗时
	
	protected String auditMessage;  
	protected String auditResult;  
	protected String auditTime;
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
	@Override
	public String getAuditMessage() {
		return auditMessage;
	}
	@Override
	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}
	@Override
	public String getAuditResult() {
		return auditResult;
	}
	@Override
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
	@Override
	public String getAuditTime() {
		return auditTime;
	}
	@Override
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getStepinsId() {
		return stepinsId;
	}
	public void setStepinsId(String stepinsId) {
		this.stepinsId = stepinsId;
	}
	public String getUseTime() {
		return useTime;
	}
	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}
}