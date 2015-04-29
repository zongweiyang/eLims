package cn.labsoft.labos.source.appara.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_appara_check")
public class LabApparaCheck extends AbstractBasePo{
	
	private LabAppara labAppara;
	private String appName;
	private String appNo;
	private String appspec;
	private String personId;
	private String personName;  //检查人
	private Date verDate;
	private String checkId;
	private String checkName;
	private Date checkDate;
	private String auditId;
	private String auditName;   //审核人
	private Date auditDate;
	private String verInfo;
	private String verCon;
	private String comment;
	private String ext01;
	private String ext02;
	private String ext03;
	private String ext04;
	private String ext05;//核查方法
	private String user2;//审核人
	private String date2;//审核时间
	private String user3;//审批人
	private String date3;//审批时间
	private LabWfProcessIns processIns;
	private String auditMessage;//审核意见
	private String pauditMessage;//审批意见
	private String status;//核查状态
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
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

	public String getPersonId() {
		return this.personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return this.personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getCheckId() {
		return this.checkId;
	}

	public void setCheckId(String checkId) {
		this.checkId = checkId;
	}

	public String getCheckName() {
		return this.checkName;
	}

	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}

	public Date getCheckDate() {
		return this.checkDate;
	}

	public void setCheckDate(Date checkDate) {
		this.checkDate = checkDate;
	}

	public String getAuditId() {
		return this.auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Date getAuditDate() {
		return this.auditDate;
	}

	public void setAuditDate(Date auditDate) {
		this.auditDate = auditDate;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getExt01() {
		return this.ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}

	public String getExt02() {
		return this.ext02;
	}

	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}

	public String getExt03() {
		return this.ext03;
	}

	public void setExt03(String ext03) {
		this.ext03 = ext03;
	}

	public String getExt04() {
		return this.ext04;
	}

	public void setExt04(String ext04) {
		this.ext04 = ext04;
	}

	public String getExt05() {
		return this.ext05;
	}

	public void setExt05(String ext05) {
		this.ext05 = ext05;
	}
	@ManyToOne
	@JoinColumn(name="app_id")
	public LabAppara getLabAppara() {
		return labAppara;
	}
	public void setLabAppara(LabAppara labAppara) {
		this.labAppara = labAppara;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public String getDate2() {
		return date2;
	}
	public void setDate2(String date2) {
		this.date2 = date2;
	}
	public String getUser3() {
		return user3;
	}
	public void setUser3(String user3) {
		this.user3 = user3;
	}
	public String getDate3() {
		return date3;
	}
	public void setDate3(String date3) {
		this.date3 = date3;
	}
	public String getAppspec() {
		return appspec;
	}
	public void setAppspec(String appspec) {
		this.appspec = appspec;
	}
	public Date getVerDate() {
		return verDate;
	}
	public void setVerDate(Date verDate) {
		this.verDate = verDate;
	}
	public String getVerInfo() {
		return verInfo;
	}
	public void setVerInfo(String verInfo) {
		this.verInfo = verInfo;
	}
	public String getVerCon() {
		return verCon;
	}
	public void setVerCon(String verCon) {
		this.verCon = verCon;
	}
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器核查信息";
	}
}