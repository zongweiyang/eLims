package cn.labsoft.labos.business.samreport.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

import javax.persistence.Entity;
/**
 * 发送并归档(包含发送信息和归档信息)
 * @author quinn
 * 
 */
@Entity
public class LabSamReportEndVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	private String busId;      //任务Id
	@ExcelAnnotation(exportName = "任务编号")
	private String busNo;      //任务编号
	private String reportId;   //报告编号
	@ExcelAnnotation(exportName = "报告编号")
	private String reportNo;   //报告编号
	private String title;      //报告名称
	 //发送信息
	@ExcelAnnotation(exportName = "发送报告方式")
	private String reportPost;//发送报告方式
	@ExcelAnnotation(exportName = "发送日期")
	private String sendDate;  //发送日期
	@ExcelAnnotation(exportName = "收件人")
	private String toUser;    //收件人
	@ExcelAnnotation(exportName = "传真")
	private String fax;       //传真
	@ExcelAnnotation(exportName = "email")
	private String email;    //email
	@ExcelAnnotation(exportName = "快递公司")
	private String express;    //快递公司及单号
	@ExcelAnnotation(exportName = "客户名称")
	private String labCustomerName;//客户名称
	@ExcelAnnotation(exportName = "联系电话")
	private String telephone; //联系电话
	@ExcelAnnotation(exportName = "地址")
	private String address;
	@ExcelAnnotation(exportName = "备注")
	private String remark;    //备注
	
	//归档信息
	private String number;    //编号
	private String place;     //存放地方
	private String saveUser; //保管人
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getBusNo() {
		return busNo;
	}
	public void setBusNo(String busNo) {
		this.busNo = busNo;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReportNo() {
		return reportNo;
	}
	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReportPost() {
		return reportPost;
	}
	public void setReportPost(String reportPost) {
		this.reportPost = reportPost;
	}
	public String getSendDate() {
		return sendDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getFax() {
		return fax;
	}
	public void setFax(String fax) {
		this.fax = fax;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLabCustomerName() {
		return labCustomerName;
	}
	public void setLabCustomerName(String labCustomerName) {
		this.labCustomerName = labCustomerName;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	public String getExpress() {
		return express;
	}
	public void setExpress(String express) {
		this.express = express;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getSaveUser() {
		return saveUser;
	}
	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
