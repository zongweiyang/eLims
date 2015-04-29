package cn.labsoft.labos.common.workflow.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 流程实例
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfProcessIns  extends AbstractBasePo{

	private static final long serialVersionUID = 5565535593946271527L;
	private LabWfProcess process;
	private String processName;
	private String busId;    //业务
	private String type;     //业务类型
	private String orgId;
	private String orgName;
	private String userId;
	private String userName;
	private Date startDate;
	private Date endDate;
	private String status;  //funIds
	private Integer num;
	
	private LabWfProcessIns parIns; //父流程
	private String parStepCode;     //对应的父流程节点
	
	private String result;//null为实例化异常 0 为节点进行中  1 为节点完成
	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	@ManyToOne
	@JoinColumn(name="process_id", nullable=false)
	public LabWfProcess getProcess() {
		return process;
	}

	public void setProcess(LabWfProcess process) {
		this.process = process;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	@ManyToOne
	@JoinColumn(name="parent_id")
	public LabWfProcessIns getParIns() {
		return parIns;
	}

	public void setParIns(LabWfProcessIns parIns) {
		this.parIns = parIns;
	}
	
	public String getParStepCode() {
		return parStepCode;
	}

	public void setParStepCode(String parStepCode) {
		this.parStepCode = parStepCode;
	}
	@Transient
	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "流程实例信息";
	}
	
	

}