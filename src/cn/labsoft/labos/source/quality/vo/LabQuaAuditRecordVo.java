package cn.labsoft.labos.source.quality.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * 实验室现场内审记录表
 */

public class LabQuaAuditRecordVo extends BaseVo{

	private String id;
	private String quaAuditPlanEleId;
	private String quaAuditPlanEleName;
	private String reportNo;//报告编号
	@ExcelAnnotation(exportName = "被审核部门")
	private String labOrgId;
	private String labOrgName;
	@ExcelAnnotation(exportName = "审核月份")
	private String month;
	@ExcelAnnotation(exportName = "审核员")
	private String auditPeople;
	@ExcelAnnotation(exportName = "审核时间")
	private String auditTime;
	@ExcelAnnotation(exportName = "审核要素")
	private String auditPart;
	@ExcelAnnotation(exportName = "编制人员")
	private String writeMember;
	@ExcelAnnotation(exportName = "审核组长")
	private String checkHead;
	@ExcelAnnotation(exportName = "编制日期")
	private String writeTime;
	@ExcelAnnotation(exportName = "审核地点")
	private String address;
	@ExcelAnnotation(exportName = "审核方式")
	private String auditType;//审核方式
	@ExcelAnnotation(exportName = "审核情况概述")
	private String auditCondition;//审核情况概述
	@ExcelAnnotation(exportName = "内审结论")
	private String auditRecord;//内审结论
	@ExcelAnnotation(exportName = "审核报告发放部门")
	private String reportUnit;//审核报告发放部门
	@ExcelAnnotation(exportName = "审核人")
	private String auditPerson;//审核人
	@ExcelAnnotation(exportName = "审核日期")
	private String auditDate;
	private String auditStatus;
	private String purpose;  //审核目的
	private String implement;  //审核依据
	private String range;    //审核范围
	private String filePeople;
	private String fileTime;
	private String remark;
	private String isFile;
	
	private String reportTempId;
	private String reportPath;
	
	private List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getQuaAuditPlanEleId() {
		return quaAuditPlanEleId;
	}
	public void setQuaAuditPlanEleId(String quaAuditPlanEleId) {
		this.quaAuditPlanEleId = quaAuditPlanEleId;
	}
	public String getQuaAuditPlanEleName() {
		return quaAuditPlanEleName;
	}
	public void setQuaAuditPlanEleName(String quaAuditPlanEleName) {
		this.quaAuditPlanEleName = quaAuditPlanEleName;
	}
	public String getLabOrgId() {
		return labOrgId;
	}
	public void setLabOrgId(String labOrgId) {
		this.labOrgId = labOrgId;
	}
	public String getLabOrgName() {
		return labOrgName;
	}
	public void setLabOrgName(String labOrgName) {
		this.labOrgName = labOrgName;
	}
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
	@Override
	public String getAuditTime() {
		return auditTime;
	}
	@Override
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
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
	public List<LabQuaAuditRecordDetailVo> getLabQuaAuditRecordDetailVoList() {
		return labQuaAuditRecordDetailVoList;
	}
	public void setLabQuaAuditRecordDetailVoList(
			List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList) {
		this.labQuaAuditRecordDetailVoList = labQuaAuditRecordDetailVoList;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
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
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getImplement() {
		return implement;
	}
	public void setImplement(String implement) {
		this.implement = implement;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
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
