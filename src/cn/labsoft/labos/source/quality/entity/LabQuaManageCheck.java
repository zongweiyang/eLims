package cn.labsoft.labos.source.quality.entity;




import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 管理评审记录表
 */

@Entity
@Table(name="lab_qua_manage")
public class LabQuaManageCheck extends AbstractBasePo{
	
	private LabOrg org;
	private LabWfProcessIns processIns;
	private LabQuaManageCheckPlan labQuaManageCheckPlan;
	private String reportNo;
	private String recordPeople;
	private String recordTime;
	private String reportPeople;
	private String reportTime;
	private String filePeople;
	private String fileTime;
	private String measureCheck;
	private String problem;
	private String measure;
	private String record;//管理评审记录
	private String remark;//存档说明
	private String checkPeople;//批准人
	private String checkTime;//批准时间
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
		return "管理评审记录";
	}
	
	@ManyToOne
	@JoinColumn(name="org_id", nullable=false)
	public LabOrg getOrg() {
		return org;
	}
	public void setOrg(LabOrg org) {
		this.org = org;
	}
	@ManyToOne
	@JoinColumn(name="manage_plan_id", nullable=false)
	public LabQuaManageCheckPlan getLabQuaManageCheckPlan() {
		return labQuaManageCheckPlan;
	}
	public void setLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan) {
		this.labQuaManageCheckPlan = labQuaManageCheckPlan;
	}
	@ManyToOne
	@JoinColumn(name="process_ins_id")
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}
	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getRecordPeople() {
		return recordPeople;
	}
	public void setRecordPeople(String recordPeople) {
		this.recordPeople = recordPeople;
	}
	public String getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	public String getReportPeople() {
		return reportPeople;
	}
	public void setReportPeople(String reportPeople) {
		this.reportPeople = reportPeople;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
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
	public String getMeasureCheck() {
		return measureCheck;
	}
	public void setMeasureCheck(String measureCheck) {
		this.measureCheck = measureCheck;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getMeasure() {
		return measure;
	}
	public void setMeasure(String measure) {
		this.measure = measure;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCheckPeople() {
		return checkPeople;
	}
	public void setCheckPeople(String checkPeople) {
		this.checkPeople = checkPeople;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
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
