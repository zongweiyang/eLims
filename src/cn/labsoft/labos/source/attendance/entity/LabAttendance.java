package cn.labsoft.labos.source.attendance.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import javax.persistence.Transient;

import javax.persistence.Entity;

/**
 * 用户考勤信息持久化对象
 * @author Quinn
 */
@Entity
public class LabAttendance extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String orgId;//部门
	private String orgName;//部门
	private String userId;//用户Id
	private String userName;//姓名
	private String workDate;//打卡日期
	private String startTime;//签到时间
	private String startFlag;//是否迟到
	private long startMin; //迟到分钟数
	private String endTime;//签退时间
	private String endFlag;//是否早退
	private long endMin; //早退分钟数
	private String afterFlag;//是否补卡
	private String isOk;//是否正常
	private String remark;//备注

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

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartFlag() {
		return this.startFlag;
	}

	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndFlag() {
		return this.endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}

	public String getAfterFlag() {
		return this.afterFlag;
	}

	public void setAfterFlag(String afterFlag) {
		this.afterFlag = afterFlag;
	}

	public String getIsOk() {
		return this.isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	
	public long getStartMin() {
		return startMin;
	}

	public void setStartMin(long startMin) {
		this.startMin = startMin;
	}

	public long getEndMin() {
		return endMin;
	}

	public void setEndMin(long endMin) {
		this.endMin = endMin;
	}

	@Transient
	@Override
	public String getModelName() {
		return "考勤管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "考勤记录";
	}
}
