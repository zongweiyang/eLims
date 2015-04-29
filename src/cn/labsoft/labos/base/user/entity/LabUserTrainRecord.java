package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 用户培训记录信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_user_train_record")
public class LabUserTrainRecord extends AbstractBasePo {
	
	private static final long serialVersionUID = -946800291249134411L;
	
	private String orgId;   //部门
	private String orgName; //部门
	private String teacher; //讲师
	private String station; //培训岗位
	private String tdate;   //培训时间
	private String address; //培训地点
	private String content; //培训内容
	private String remark;  //备注
	private String result;  //培训结果
	
	private LabUserTrain labUserTrain; //培训计划
	private LabUser labUser; //被培训人

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTdate() {
		return this.tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@ManyToOne
	@JoinColumn(name="train_id")
	public LabUserTrain getLabUserTrain() {
		return labUserTrain;
	}
	public void setLabUserTrain(LabUserTrain labUserTrain) {
		this.labUserTrain = labUserTrain;
	}
	@ManyToOne
	@JoinColumn(name="user_id")
	public LabUser getLabUser() {
		return labUser;
	}
	public void setLabUser(LabUser labUser) {
		this.labUser = labUser;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "用户培训关系";
	}
}
