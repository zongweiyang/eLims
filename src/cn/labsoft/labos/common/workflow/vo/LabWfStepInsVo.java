package cn.labsoft.labos.common.workflow.vo;


import javax.persistence.Entity;
import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * WfStepIns entity.
 * 步骤实例
 * @author Quinn
 */
@Entity
public class LabWfStepInsVo  extends BaseVo{
	private static final long serialVersionUID = 5593674505448301905L;
	private String busId;        //实例节点的业务Id
	private String processInsId; //流程实例Id
	private String stepId;         //步骤Id
	private String stepName;         //步骤Name
	private String code;             //编码
	private String name;
	private Long overDate; //本环节超期天数
	private String status;  // 0初始  1 未完成 2完成中  3完成
	private String userName;
	private String userId;
	private Integer num;
	
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getProcessInsId() {
		return processInsId;
	}
	public void setProcessInsId(String processInsId) {
		this.processInsId = processInsId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getOverDate() {
		return overDate;
	}
	public void setOverDate(Long overDate) {
		this.overDate = overDate;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	

	
	
}