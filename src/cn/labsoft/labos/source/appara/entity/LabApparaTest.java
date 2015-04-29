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
@Table(name="lab_appara_test")
public class LabApparaTest extends AbstractBasePo {

	
	private Date testDate;//检定日期
	private String testFun;//检定方法
	private String testRecord;
	private String testResult;
	private String reposPerson;//检定人
	private String ext01;
	private String ext02;
	private String ext03;
	private String view2;
	private String user2;//审核人
	private String date2;
	private String view3;
	private String user3;//审批人
	private String date3;
	private LabAppara labAppara;
	private LabWfProcessIns processIns;
	private String auditMessage;//审核意见
	private String pauditMessage;//审批意见
	private String status;//检定状态
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

	public Date getTestDate() {
		return testDate;
	}
	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}
	public String getTestFun() {
		return testFun;
	}
	public void setTestFun(String testFun) {
		this.testFun = testFun;
	}
	public String getTestRecord() {
		return testRecord;
	}
	public void setTestRecord(String testRecord) {
		this.testRecord = testRecord;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getReposPerson() {
		return reposPerson;
	}
	public void setReposPerson(String reposPerson) {
		this.reposPerson = reposPerson;
	}
	public String getExt01() {
		return ext01;
	}
	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}
	public String getExt02() {
		return ext02;
	}
	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}
	public String getExt03() {
		return ext03;
	}
	public void setExt03(String ext03) {
		this.ext03 = ext03;
	}

	@ManyToOne
	@JoinColumn(name="LABAPPARA_ID")
	public LabAppara getLabAppara() {
		return labAppara;
	}

	public void setLabAppara(LabAppara labAppara) {
		this.labAppara = labAppara;
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


	public String getDate2() {
		return date2;
	}


	public void setDate2(String date2) {
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


	public String getDate3() {
		return date3;
	}


	public void setDate3(String date3) {
		this.date3 = date3;
	}
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器校验信息";
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
