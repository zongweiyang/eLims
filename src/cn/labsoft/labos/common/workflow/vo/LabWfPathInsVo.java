package cn.labsoft.labos.common.workflow.vo;

import javax.persistence.Entity;
import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * WfConditionpathIns entity.
 * 条件路径实例
 * @author Quinn
 */
@Entity
public class LabWfPathInsVo  extends BaseVo{

	private static final long serialVersionUID = 1167822612857725558L;
	private String processInsId;  //实例Id
	private String pathId;
	private String endStepInsId;
	private String startStepInsId;
	private String num;   //该迁移线路通过业务次数
	// Constructors

	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getProcessInsId() {
		return processInsId;
	}
	public void setProcessInsId(String processInsId) {
		this.processInsId = processInsId;
	}
	public String getPathId() {
		return pathId;
	}
	public void setPathId(String pathId) {
		this.pathId = pathId;
	}
	public String getEndStepInsId() {
		return endStepInsId;
	}
	public void setEndStepInsId(String endStepInsId) {
		this.endStepInsId = endStepInsId;
	}
	public String getStartStepInsId() {
		return startStepInsId;
	}
	public void setStartStepInsId(String startStepInsId) {
		this.startStepInsId = startStepInsId;
	}


}