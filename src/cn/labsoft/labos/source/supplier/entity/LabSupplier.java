package cn.labsoft.labos.source.supplier.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_supplier")
public class LabSupplier extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	
	private String code;// 供应商编号
	private String name;// 供应商名称
	private String companyType;// 公司类型
	private String bulidDate;// 成立时间
	private String goodsType;// 供应产品类型
	private String startDate;// 关系建立时间
	private String person;// 联系人
	private String phone;// 联系电话
	private String address;// 公司地址
	private String zipCode;// 邮编
	private String email;// 邮箱
	private String fax;// 传真
	private String remark;// 备注
	private String account;// 开户行
	private String accountName;// 账户名称
	private String accountCode;// 账号
	private String goods;// 供应产品详情


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

	public String getStartDate() {
		return this.startDate;
	}

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

	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "供应商管理";
	}

	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "供应商管理";
	}

}
