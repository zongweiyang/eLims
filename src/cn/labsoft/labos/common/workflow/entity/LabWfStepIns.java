package cn.labsoft.labos.common.workflow.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 步骤实例
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfStepIns  extends AbstractBasePo{
	private static final long serialVersionUID = 5593674505448301905L;
	private LabWfProcessIns process;      //实例Id
	private String busId;            //实例流程的业务Id
	private String stepBusId;        //实例节点的业务Id
	private String busType;           //实例节点的业务类型;
	private LabWfStep step;          //步骤Id
	private String stepName;         //步骤Name
	private String code;             //功能id
	private String name;
	private Date startDate;
	private Date endDate;
	private Long overDate;           //本环节超期天数
	private String status;           // 0初始 1未完成 2完成中  3完成
	private String userName;
	private String userId;
	private Integer num;    //定义流程里的定义节点序号，用于排序
	
	private List<LabWfPathVar> pahtVarList;
	@ManyToOne
	public LabWfProcessIns getProcess() {
		return process;
	}
	public void setProcess(LabWfProcessIns process) {
		this.process = process;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	public Long getOverDate() {
		return overDate;
	}
	public void setOverDate(Long overDate) {
		this.overDate = overDate;
	}
	@ManyToOne
	@JoinColumn(name="step_id", nullable=false)
	public LabWfStep getStep() {
		return step;
	}
	public void setStep(LabWfStep step) {
		this.step = step;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	@Transient
	public List<LabWfPathVar> getPahtVarList() {
		return pahtVarList;
	}
	public void setPahtVarList(List<LabWfPathVar> pahtVarList) {
		this.pahtVarList = pahtVarList;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}
	
	public String getStepBusId() {
		return stepBusId;
	}

	public void setStepBusId(String stepBusId) {
		this.stepBusId = stepBusId;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "节点实例信息";
	}
	
	
}