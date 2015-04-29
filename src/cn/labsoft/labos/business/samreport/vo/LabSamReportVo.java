package cn.labsoft.labos.business.samreport.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSamReportVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "报告名称")
	private String title;
	@ExcelAnnotation(exportName = "出报告日期")
	private String finishDate;//出报告日期
	@ExcelAnnotation(exportName = "报告日期")
	private String reportDate;
	@ExcelAnnotation(exportName = "报告数量")
	private String reportNum;
	@ExcelAnnotation(exportName = "发报告方式")
	private String reportPost;
	@ExcelAnnotation(exportName = "任务Id")
	private String busId;      //任务Id
	@ExcelAnnotation(exportName = "任务编号")
	private String busNo;
	@ExcelAnnotation(exportName = "报告编号")
	private String reportNo;
	@ExcelAnnotation(exportName = "报告性质")
	private String reportType;//报告性质 检测，检验
	@ExcelAnnotation(exportName = "报告类型")
	private String sampType;   //报告类型 水，土壤，等
	@ExcelAnnotation(exportName = "检验结论")
	private String testResult; 
	@ExcelAnnotation(exportName = "检验依据")
	private String testStand; 
	@ExcelAnnotation(exportName = "备注")
	private String remark; 
	
	@ExcelAnnotation(exportName = "客户名称")
	private String labCustomerName;
	@ExcelAnnotation(exportName = "联系人")
	private String user;
	@ExcelAnnotation(exportName = "联系电话")
	private String telephone;
	@ExcelAnnotation(exportName = "Email")
	private String email;
	@ExcelAnnotation(exportName = "客户传真")
	private String fax;
	@ExcelAnnotation(exportName = "客户邮编")
	private String zipCode;
	@ExcelAnnotation(exportName = "客户地址")
	private String address;
	
	@ExcelAnnotation(exportName = "报告数据集合")
	private List<LabSamReportDataVo> reportDataList;
	
	private String reportTempId;//报告模版类型
	private String reportPath;//报告路径
	private String isBack;
	
	private LabSamReportEndVo samReportEndVo;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReportDate() {
		return this.reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getLabCustomerName() {
		return this.labCustomerName;
	}

	public void setLabCustomerName(String labCustomerName) {
		this.labCustomerName = labCustomerName;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
	public String getReportPost() {
		return reportPost;
	}
	public void setReportPost(String reportPost) {
		this.reportPost = reportPost;
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getReportType() {
		return reportType;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getTestResult() {
		return testResult;
	}
	public void setTestResult(String testResult) {
		this.testResult = testResult;
	}
	public String getTestStand() {
		return testStand;
	}
	public void setTestStand(String testStand) {
		this.testStand = testStand;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<LabSamReportDataVo> getReportDataList() {
		return reportDataList;
	}
	public void setReportDataList(List<LabSamReportDataVo> reportDataList) {
		this.reportDataList = reportDataList;
	}
	public String getSampType() {
		return sampType;
	}
	public void setSampType(String sampType) {
		this.sampType = sampType;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
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
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	public LabSamReportEndVo getSamReportEndVo() {
		return samReportEndVo;
	}
	public void setSamReportEndVo(LabSamReportEndVo samReportEndVo) {
		this.samReportEndVo = samReportEndVo;
	}
	
	
}
