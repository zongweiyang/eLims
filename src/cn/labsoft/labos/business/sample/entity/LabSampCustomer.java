package cn.labsoft.labos.business.sample.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.source.customer.entity.LabCustomer;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Entity;
/**
 * 客户关系信息持久化对象
 * @author Quinn
 */
@Entity

public class LabSampCustomer extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	//客户信息
	private LabCustomer labCustomer; //客户表
	private String labCustomerName;    //客户名称
	private String user;     //联系人
	private String telephone;     //电话
	private String email;   //email
	private String fax;     //传真
	private String zipCode; //邮编
	private String address; //地址
	
	@ManyToOne
	public LabCustomer getLabCustomer() {
		return labCustomer;
	}

	public void setLabCustomer(LabCustomer labCustomer) {
		this.labCustomer = labCustomer;
	}

	public String getLabCustomerName() {
		return labCustomerName;
	}

	public void setLabCustomerName(String labCustomerName) {
		this.labCustomerName = labCustomerName;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getTelephone() {
		return telephone;
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

	@Transient
	@Override
	public String getModelName() {
		return "业务检测";
	}
	@Transient
	@Override
	public String getTableName() {
		return "客户信息关联表";
	}
}
