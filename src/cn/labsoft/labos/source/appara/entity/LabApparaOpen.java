package cn.labsoft.labos.source.appara.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 仪器设备启用申请表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name="lab_appara_open")
public class LabApparaOpen extends AbstractBasePo{

	private String appId;
	private String appName;
	private String appNo;
	
	private String view1;
	private String user1;//提出人
	private Date date1; 
	private String view2;
	private String user2;//审核人
	private Date date2;
	private String view3;
	private String user3;//审批人
	private Date date3;
	
	private String userId;
	private String userName;
	private String remark;
	private String status;
	private LabWfProcessIns processIns;
	private String auditMessage;//审核意见
	private String pauditMessage;//审批意见
	@ManyToOne
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}


	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}


	public String getAuditMessage() {
		return auditMessage;
	}


	public void setAuditMessage(String auditMessage) {
		this.auditMessage = auditMessage;
	}


	public String getPauditMessage() {
		return pauditMessage;
	}


	public void setPauditMessage(String pauditMessage) {
		this.pauditMessage = pauditMessage;
	}

	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getView1() {
		return view1;
	}
	public void setView1(String view1) {
		this.view1 = view1;
	}
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public Date getDate1() {
		return date1;
	}
	public void setDate1(Date date1) {
		this.date1 = date1;
	}
	public String getView2() {
		return view2;
	}
	public void setView2(String view2) {
		this.view2 = view2;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public Date getDate2() {
		return date2;
	}
	public void setDate2(Date date2) {
		this.date2 = date2;
	}
	public String getView3() {
		return view3;
	}
	public void setView3(String view3) {
		this.view3 = view3;
	}
	public String getUser3() {
		return user3;
	}
	public void setUser3(String user3) {
		this.user3 = user3;
	}
	public Date getDate3() {
		return date3;
	}
	public void setDate3(Date date3) {
		this.date3 = date3;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器开放信息";
	}
}
