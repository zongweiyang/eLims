package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



public class LabQuaManageCheckVo extends BaseVo {

	
	private String id;
	private String reportNo;
	@ExcelAnnotation(exportName = "评审单位")
	private String orgId;
	private String orgName;
	private String orgSearch;
	@ExcelAnnotation(exportName = "管理评审计划")
	private String quaManageCheckPlanId;
	private String quaManageCheckPlanName;
	@ExcelAnnotation(exportName = "评审主持人")
	private String trackPeople;//评审主持人
	@ExcelAnnotation(exportName = "评审地点")
	private String address;//评审地点
	@ExcelAnnotation(exportName = "评审时间")
	private String recTime;
	@ExcelAnnotation(exportName = "记录编制人")
	private String recordPeople;
	@ExcelAnnotation(exportName = "记录编制日期")
	private String recordTime;
	@ExcelAnnotation(exportName = "报告编制人")
	private String reportPeople;
	@ExcelAnnotation(exportName = "报告编制日期")
	private String reportTime;
	@ExcelAnnotation(exportName = "批准人")
	private String checkPeople;//批准人
	@ExcelAnnotation(exportName = "批准时间")
	private String checkTime;//批准时间
	@ExcelAnnotation(exportName = "归档员")
	private String filePeople;
	@ExcelAnnotation(exportName = "归档日期")
	private String fileTime;
	@ExcelAnnotation(exportName = "组长")
	private String groupLeader;//组长
	@ExcelAnnotation(exportName = "组员")
	private String groupMember; //组员
	@ExcelAnnotation(exportName = "评审依据")
	private String foundation;//评审依据
	@ExcelAnnotation(exportName = "评审内容")
	private String contents;//评审内容
	@ExcelAnnotation(exportName = "对纠正/改进措施的检查")
	private String measureCheck;//对纠正/改进措施的检查
	@ExcelAnnotation(exportName = "评审中发现的问题和结论")
	private String problem;
	@ExcelAnnotation(exportName = "实施纠正/改进的措施")
	private String measure;
	@ExcelAnnotation(exportName = "管理评审记录")
	private String record;//管理评审记录
	private String remark;//存档说明
	private String isFile;
	
	private String reportTempId;
	private String reportPath;
	
	
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgSearch() {
		return orgSearch;
	}
	public void setOrgSearch(String orgSearch) {
		this.orgSearch = orgSearch;
	}
	public String getQuaManageCheckPlanId() {
		return quaManageCheckPlanId;
	}
	public void setQuaManageCheckPlanId(String quaManageCheckPlanId) {
		this.quaManageCheckPlanId = quaManageCheckPlanId;
	}
	public String getQuaManageCheckPlanName() {
		return quaManageCheckPlanName;
	}
	public void setQuaManageCheckPlanName(String quaManageCheckPlanName) {
		this.quaManageCheckPlanName = quaManageCheckPlanName;
	}
	public String getTrackPeople() {
		return trackPeople;
	}
	public void setTrackPeople(String trackPeople) {
		this.trackPeople = trackPeople;
	}
	public String getRecTime() {
		return recTime;
	}
	public void setRecTime(String recTime) {
		this.recTime = recTime;
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
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}
	public String getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
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
	public String getFoundation() {
		return foundation;
	}
	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
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
	public String getIsFile() {
		return isFile;
	}
	public void setIsFile(String isFile) {
		this.isFile = isFile;
	}
	

	
}