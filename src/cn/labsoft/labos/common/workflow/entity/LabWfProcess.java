package cn.labsoft.labos.common.workflow.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 流程定义
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfProcess  extends AbstractBasePo{

	// Fields
	private static final long serialVersionUID = -7004882861689094056L;
	private String funId;
	private String funName;
	private String funCode;
	private String name;
	private String code;
	private String userId;
	private String userName;
	private String createDate;
	private String modifyDate;
	private String startDate; //启用时间
	private String endDate;   //截止时间
	private String comment;
	private String status;  //0编辑状态  1只读状态 2关闭
	
	private LabWfProcess parProcess;//父流程
	private LabWfFunStep funStep;    //父流程里面当前流程所对应的节点
	private String isStop;     //干预性：Y表示等待子流程 Ｎ表示正常流转


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

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
	@JoinColumn(name="parent_id")
	public LabWfProcess getParProcess() {
		return parProcess;
	}

	public void setParProcess(LabWfProcess parProcess) {
		this.parProcess = parProcess;
	}
	public String getIsStop() {
		return isStop;
	}

	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
	@ManyToOne
	public LabWfFunStep getFunStep() {
		return funStep;
	}

	public void setFunStep(LabWfFunStep funStep) {
		this.funStep = funStep;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "流程信息";
	}
	
}