package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 用户培训计划信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_user_train")
public class LabUserTrain extends AbstractBasePo {
	private static final long serialVersionUID = 1L;

	private String title;
	private String orgId;  //部门
	private String orgName;//部门
	private String userId;  //创建人
	private String userName;//创建人
	private String teacher;//讲师
	private String station;//培训岗位
	private String tdate;//培训时间
	private String address;//培训地点
	private String content;//培训内容
	private String remark;//备注
	private String status; //状态 0 为计划阶段 1为实施阶段 2 为完结阶段
	private String isMsg;//是否消息
	private String isSms;//短信通知
	

	public String getIsMsg() {
		return isMsg;
	}

	public void setIsMsg(String isMsg) {
		this.isMsg = isMsg;
	}

	public String getIsSms() {
		return isSms;
	}
	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}

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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
