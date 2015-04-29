package cn.labsoft.labos.business.sample.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

import javax.persistence.Entity;

@Entity
public class LabSampCustomerVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	//客户信息
	private String labCustomerId; //客户表
	@ExcelAnnotation(exportName = "客户名称")
	private String labCustomerName;    //客户名称
	@ExcelAnnotation(exportName = "联系人")
	private String user;     //联系人
	@ExcelAnnotation(exportName = "电话")
	private String telephone;     //电话
	@ExcelAnnotation(exportName = "email")
	private String email;   //email
	@ExcelAnnotation(exportName = "传真")
	private String fax;     //传真
	@ExcelAnnotation(exportName = "邮编")
	private String zipCode; //邮编
	@ExcelAnnotation(exportName = "地址")
	private String address; //地址
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabCustomerId() {
		return labCustomerId;
	}

	public void setLabCustomerId(String labCustomerId) {
		this.labCustomerId = labCustomerId;
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

}
