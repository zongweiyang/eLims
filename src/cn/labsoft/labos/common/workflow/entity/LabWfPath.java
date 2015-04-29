package cn.labsoft.labos.common.workflow.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 条件路径定义
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfPath extends AbstractBasePo{

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 3855023776485691634L;
	private String name;//标题
	private String processId;    //流程
	private LabWfStep endStep;      //指向步骤
	private String endStepName;  //指向步骤
	private LabWfStep startStep;    //起始步骤
	private String startStepName; //起始步骤
	
	
	private String comment;    //备注
	private String status;  // 0初始  1 未完成 2完成 
	private String points;  
	private String textpos;
	@Transient
	private List<LabWfPathVar> pathVarList;


	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getTextpos() {
		return textpos;
	}

	public void setTextpos(String textpos) {
		this.textpos = textpos;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEndStepName() {
		return endStepName;
	}


	public void setEndStepName(String endStepName) {
		this.endStepName = endStepName;
	}
	public String getStartStepName() {
		return startStepName;
	}
	public void setStartStepName(String startStepName) {
		this.startStepName = startStepName;
	}
	@ManyToOne
	@JoinColumn(name="end_step", nullable=false)
	public LabWfStep getEndStep() {
		return endStep;
	}

	public void setEndStep(LabWfStep endStep) {
		this.endStep = endStep;
	}

	@ManyToOne
	@JoinColumn(name="start_step", nullable=false)
	public LabWfStep getStartStep() {
		return startStep;
	}

	public void setStartStep(LabWfStep startStep) {
		this.startStep = startStep;
	}
	@Transient
	public List<LabWfPathVar> getPathVarList() {
		return pathVarList;
	}
	@Transient
	public void setPathVarList(List<LabWfPathVar> pathVarList) {
		this.pathVarList = pathVarList;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "流程线路信息";
	}
}