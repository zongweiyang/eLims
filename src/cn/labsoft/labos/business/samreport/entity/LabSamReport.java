package cn.labsoft.labos.business.samreport.entity;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import javax.persistence.Entity;
/**
 * 报告信息持久化对象
 * @author Quinn
 */
@Entity
public class LabSamReport extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String busId;      //任务Id
	private String busNo;      //任务编号
	private String reportNo;   //报告编号
	private String title;      //报告名称
	private String reportDate;//报告日期
	private String finishDate;//出报告日期
	private String reportNum; 
	private String reportPost;//出报告方式
	private String sampType;  //报告类型 水，土壤，等
	private String reportType;//报告性质 检测，检验
	private String testResult;//检验结论
	private String testStand; //检验依据
	private String remark;    //备注
	
	private String reportTempId;
	private String reportPath;
	
	private String labCustomerName;//客户名称
	private String user;//联系人
	private String telephone;//联系电话
	private String email;//联系电话
	private String fax;
	private String zipCode;
	private String address;
	
	private LabWfProcessIns processIns;
	
	
	@ManyToOne
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}

	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
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

	@Transient
	@Override
	public String getModelName() {
		return "业务检测";
	}
	@Transient
	@Override
	public String getTableName() {
		return "检测报告";
	}
}
