package cn.labsoft.labos.source.customer.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabCustomerVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "客户编码")
	private String num;
	@ExcelAnnotation(exportName = "客户名称")
	private String name;
	@ExcelAnnotation(exportName = "所处行业")
	private String industry;
	@ExcelAnnotation(exportName = "/客户类型")
	private String type;// 客户类型
	@ExcelAnnotation(exportName = "客户级别")
	private String level;// 客户级别
	@ExcelAnnotation(exportName = "建立时间")
	private String buildDate;// 建立时间
	@ExcelAnnotation(exportName = "短信通知")
	private String inform;// 短信通知
	@ExcelAnnotation(exportName = "联系电话")
	private String phone;// 联系电话
	@ExcelAnnotation(exportName = "邮箱")
	private String email;// 邮箱
	@ExcelAnnotation(exportName = "传真")
	private String fox;// 传真
	@ExcelAnnotation(exportName = "邮编")
	private String zipCode;// 邮编
	@ExcelAnnotation(exportName = "地址")
	private String address;// 地址
	@ExcelAnnotation(exportName = "第一联系人")
	private String firPerson;// 第一联系人
	@ExcelAnnotation(exportName = "第一邮箱")
	private String firEmail;// 第一邮箱
	@ExcelAnnotation(exportName = "第一电话")
	private String firPhone;// 第一电话
	@ExcelAnnotation(exportName = "第二联系人")
	private String secPerson;// 第二联系人
	@ExcelAnnotation(exportName = "第二邮箱")
	private String secEmail;// 第二邮箱
	@ExcelAnnotation(exportName = "第二电话")
	private String secPhone;// 第二电话
	@ExcelAnnotation(exportName = "第三联系人")
	private String thiPerson;// 第三联系人
	@ExcelAnnotation(exportName = "第三邮箱")
	private String thiEmail;// 第三邮箱
	@ExcelAnnotation(exportName = "第三电话")
	private String thiPhone;// 第三电话
	@ExcelAnnotation(exportName = "备注")
	private String remark;// 备注
	private List<LabCustomerVo> listCustomer;
	@ExcelAnnotation(exportName = "业务数")
	private int businessNum;// 业务数
	@ExcelAnnotation(exportName = "账户余额")
	private double balance;// 账户余额
	@ExcelAnnotation(exportName = "已消费金额")
	private double consume;// 已消费金额

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIndustry() {
		return this.industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getBuildDate() {
		return buildDate;
	}

	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}

	public String getInform() {
		return inform;
	}

	public void setInform(String inform) {
		this.inform = inform;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFox() {
		return fox;
	}

	public void setFox(String fox) {
		this.fox = fox;
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

	public String getFirPerson() {
		return firPerson;
	}

	public void setFirPerson(String firPerson) {
		this.firPerson = firPerson;
	}

	public String getFirEmail() {
		return firEmail;
	}

	public void setFirEmail(String firEmail) {
		this.firEmail = firEmail;
	}

	public String getFirPhone() {
		return firPhone;
	}

	public void setFirPhone(String firPhone) {
		this.firPhone = firPhone;
	}

	public String getSecPerson() {
		return secPerson;
	}

	public void setSecPerson(String secPerson) {
		this.secPerson = secPerson;
	}

	public String getSecEmail() {
		return secEmail;
	}

	public void setSecEmail(String secEmail) {
		this.secEmail = secEmail;
	}

	public String getSecPhone() {
		return secPhone;
	}

	public void setSecPhone(String secPhone) {
		this.secPhone = secPhone;
	}

	public String getThiPerson() {
		return thiPerson;
	}

	public void setThiPerson(String thiPerson) {
		this.thiPerson = thiPerson;
	}

	public String getThiEmail() {
		return thiEmail;
	}

	public void setThiEmail(String thiEmail) {
		this.thiEmail = thiEmail;
	}

	public String getThiPhone() {
		return thiPhone;
	}

	public void setThiPhone(String thiPhone) {
		this.thiPhone = thiPhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<LabCustomerVo> getListCustomer() {
		return listCustomer;
	}

	public void setListCustomer(List<LabCustomerVo> listCustomer) {
		this.listCustomer = listCustomer;
	}

	public int getBusinessNum() {
		return businessNum;
	}

	public void setBusinessNum(int businessNum) {
		this.businessNum = businessNum;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getConsume() {
		return consume;
	}

	public void setConsume(double consume) {
		this.consume = consume;
	}

}
