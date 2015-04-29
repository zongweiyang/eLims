package cn.labsoft.labos.source.quality.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 内审记录
 */

@Entity
@Table(name="lab_qua_audit_record")
public class LabQuaAuditRecord  extends AbstractBasePo{

	private LabQuaAuditPlanEle labQuaAuditPlanEle;
	private LabWfProcessIns processIns;
	private LabOrg org;
	private String month;
	private String auditPeople;
	private String auditTime;
	private String auditPart;
	private String writeMember;
	private String checkHead;
	private String writeTime;
	private String address;
	private String auditCondition;//审核情况概述
	private String auditRecord;//内审结论
	private String reportUnit;//审核报告发放部门
	private String auditPerson;//审核人
	private String auditStatus;
	private String auditDate;
	private String reportNo;//报告编号
	private String filePeople;
	private String fileTime;
	private String remark;
	private String isFile;
	
	private String reportTempId;
	private String reportPath;
	

	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "质量管理";
	}

 
	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "内审记录";
	}

	@ManyToOne
	@JoinColumn(name="audit_plan_ele_id")
	public LabQuaAuditPlanEle getLabQuaAuditPlanEle() {
		return labQuaAuditPlanEle;
	}


	public void setLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle) {
		this.labQuaAuditPlanEle = labQuaAuditPlanEle;
	}

	@ManyToOne
	@JoinColumn(name="org_id")
	public LabOrg getOrg() {
		return org;
	}


	public void setOrg(LabOrg org) {
		this.org = org;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	
	
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	@Column(name="[month]")
	public String getMonth() {
		return month;
	}


	public void setMonth(String month) {
		this.month = month;
	}


	public String getAuditPeople() {
		return auditPeople;
	}


	public void setAuditPeople(String auditPeople) {
		this.auditPeople = auditPeople;
	}


	public String getAuditTime() {
		return auditTime;
	}


	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditDate() {
		return auditDate;
	}


	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getAuditPart() {
		return auditPart;
	}


	public void setAuditPart(String auditPart) {
		this.auditPart = auditPart;
	}


	public String getWriteMember() {
		return writeMember;
	}


	public void setWriteMember(String writeMember) {
		this.writeMember = writeMember;
	}


	public String getCheckHead() {
		return checkHead;
	}


	public void setCheckHead(String checkHead) {
		this.checkHead = checkHead;
	}


	public String getWriteTime() {
		return writeTime;
	}


	public void setWriteTime(String writeTime) {
		this.writeTime = writeTime;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}
	public String getAuditCondition() {
		return auditCondition;
	}


	public void setAuditCondition(String auditCondition) {
		this.auditCondition = auditCondition;
	}


	public String getAuditRecord() {
		return auditRecord;
	}


	public void setAuditRecord(String auditRecord) {
		this.auditRecord = auditRecord;
	}


	public String getReportUnit() {
		return reportUnit;
	}


	public void setReportUnit(String reportUnit) {
		this.reportUnit = reportUnit;
	}


	public String getAuditPerson() {
		return auditPerson;
	}


	public void setAuditPerson(String auditPerson) {
		this.auditPerson = auditPerson;
	}

	public String getReportNo() {
		return reportNo;
	}


	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	@ManyToOne
	@JoinColumn(name="process_ins_id")
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}


	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}


	public String getFilePeople() {
		return filePeople;
	}


	public void setFilePeople(String filePeople) {
		this.filePeople = filePeople;
	}


	public String getFileTime() {
		return fileTime;
	}


	public void setFileTime(String fileTime) {
		this.fileTime = fileTime;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getReportTempId() {
		return reportTempId;
	}


	public void setReportTempId(String reportTempId) {
		this.reportTempId = reportTempId;
	}


	public String getReportPath() {
		return reportPath;
	}


	public void setReportPath(String reportPath) {
		this.reportPath = reportPath;
	}


	public String getIsFile() {
		return isFile;
	}


	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	
	
}
