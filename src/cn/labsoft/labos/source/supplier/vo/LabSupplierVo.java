package cn.labsoft.labos.source.supplier.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSupplierVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "供应商编号")
	private String code;
	@ExcelAnnotation(exportName = "供应商名称")
	private String name;
	@ExcelAnnotation(exportName = "公司类型")
	private String companyType;
	@ExcelAnnotation(exportName = "成立时间")
	private String bulidDate;
	@ExcelAnnotation(exportName = "供应产品类型")
	private String goodsType;
	@ExcelAnnotation(exportName = "关系建立时间")
	private String startDate;
	@ExcelAnnotation(exportName = "联系人")
	private String person;
	@ExcelAnnotation(exportName = "联系电话")
	private String phone;
	@ExcelAnnotation(exportName = "公司地址")
	private String address;
	@ExcelAnnotation(exportName = "邮编")
	private String zipCode;
	@ExcelAnnotation(exportName = "邮箱")
	private String email;
	@ExcelAnnotation(exportName = "传真")
	private String fax;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "开户行")
	private String account;
	@ExcelAnnotation(exportName = "账户名称")
	private String accountName;
	@ExcelAnnotation(exportName = "账号")
	private String accountCode;
	@ExcelAnnotation(exportName = "供应产品详情")
	private String goods;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompanyType() {
		return this.companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getBulidDate() {
		return this.bulidDate;
	}

	public void setBulidDate(String bulidDate) {
		this.bulidDate = bulidDate;
	}

	public String getGoodsType() {
		return this.goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	@Override
	public String getStartDate() {
		return this.startDate;
	}

	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return this.zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getAccount() {
		return this.account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountCode() {
		return this.accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getGoods() {
		return this.goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

}
