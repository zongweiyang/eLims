package cn.labsoft.labos.business.samreport.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSamReportDataVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "业务Id")
	private String busId;
	@ExcelAnnotation(exportName = "业务编号")
	private String busNo;
	@ExcelAnnotation(exportName = "报告Id")
	private String reportId;//报告Id
	@ExcelAnnotation(exportName = "报告编号")
	private String reportNo;
	@ExcelAnnotation(exportName = "样品名称")
	private String labSamName;
	@ExcelAnnotation(exportName = "样品Id")
	private String labSamId;
	@ExcelAnnotation(exportName = "样品编号")
	private String sampCode;
	@ExcelAnnotation(exportName = "样品类型")
	private String labSamTypeName;
	@ExcelAnnotation(exportName = "样品类型Id")
	private String labSamTypeId;
	@ExcelAnnotation(exportName = "检测方法")
	private String methodName;
	@ExcelAnnotation(exportName = "方法Id")
	private String methodId;
	@ExcelAnnotation(exportName = "检测项目")
	private String itemName;
	@ExcelAnnotation(exportName = "项目Id")
	private String itemId;
	@ExcelAnnotation(exportName = "检测标准")
	private String standardName;
	@ExcelAnnotation(exportName = "标准Id")
	private String standardId;
	@ExcelAnnotation(exportName = "检测科室")
	private String orgName;
	@ExcelAnnotation(exportName = "科室Id")
	private String orgId;
	@ExcelAnnotation(exportName = "检测结果")
	private String testResult;
	@ExcelAnnotation(exportName = "判定结果")
	private String result;
	
	private String testUserId;  //检测人
	@ExcelAnnotation(exportName = "检测人")
	private String testUserName;//检测人
	@ExcelAnnotation(exportName = "检测时间")
	private String testTime;   //检测时间
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getBusId() {
		return this.busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusNo() {
		return this.busNo;
	}

	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}

	public String getReportNo() {
		return this.reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

	public String getLabSamName() {
		return this.labSamName;
	}

	public void setLabSamName(String labSamName) {
		this.labSamName = labSamName;
	}

	public String getLabSamId() {
		return this.labSamId;
	}

	public void setLabSamId(String labSamId) {
		this.labSamId = labSamId;
	}

	public String getSampCode() {
		return this.sampCode;
	}

	public void setSampCode(String sampCode) {
		this.sampCode = sampCode;
	}

	public String getLabSamTypeName() {
		return this.labSamTypeName;
	}

	public void setLabSamTypeName(String labSamTypeName) {
		this.labSamTypeName = labSamTypeName;
	}

	public String getLabSamTypeId() {
		return this.labSamTypeId;
	}

	public void setLabSamTypeId(String labSamTypeId) {
		this.labSamTypeId = labSamTypeId;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodId() {
		return this.methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getStandardId() {
		return this.standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTestResult() {
		return this.testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getTestUserId() {
		return testUserId;
	}
	public void setTestUserId(String testUserId) {
		this.testUserId = testUserId;
	}
	public String getTestUserName() {
		return testUserName;
	}
	public void setTestUserName(String testUserName) {
		this.testUserName = testUserName;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	
}
