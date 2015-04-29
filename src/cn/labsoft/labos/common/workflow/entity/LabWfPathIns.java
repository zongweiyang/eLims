package cn.labsoft.labos.common.workflow.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 条件路径实例
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfPathIns  extends AbstractBasePo{

	private static final long serialVersionUID = 1167822612857725558L;
	private String processInsId;  //实例Id
	private LabWfPath path;
	private LabWfStepIns endStepIns;
	private LabWfStepIns startStepIns;
	private String num;   //该迁移线路通过业务次数
	// Constructors

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	@ManyToOne
	@JoinColumn(name="end_step_id", nullable=false)
	public LabWfStepIns getEndStepIns() {
		return endStepIns;
	}


	public void setEndStepIns(LabWfStepIns endStepIns) {
		this.endStepIns = endStepIns;
	}

	@ManyToOne
	@JoinColumn(name="start_step_id", nullable=false)
	public LabWfStepIns getStartStepIns() {
		return startStepIns;
	}

	public void setStartStepIns(LabWfStepIns startStepIns) {
		this.startStepIns = startStepIns;
	}
	public String getProcessInsId() {
		return processInsId;
	}


	public void setProcessInsId(String processInsId) {
		this.processInsId = processInsId;
	}

	@ManyToOne
	@JoinColumn(name="path_id", nullable=false)
	public LabWfPath getPath() {
		return path;
	}


	public void setPath(LabWfPath path) {
		this.path = path;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "线路实例信息";
	}

}