package cn.labsoft.labos.business.sam.entity;


import javax.persistence.Entity;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 
 * <strong>Title : LabReaPurMain </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 22, 2014 2:33:39 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Entity
public class LabSamMain extends AbstractBasePo {
	
	private static final long serialVersionUID = 1L;
	private String sampNo;      //来样编号
	private String busId;	    // 任务Id
	private String registDate;	//登记日期
	private String remark;  	//备注
	private String num;         //样品数量
	private String isDivision;  //是否分样
	private String isDiv;
	private String customer;
	private String sampName;
	
	private String sampSource;  //样品来源
	private String sampUser;     //送样人员
	private String sampDate;     //送样日期
	private String saveUser;    //保管人
	private String saveOrg;     //存放地点
	private String saveMode;     //保存方式
	
	private String sampDesc;    //处理情况
	private String sampPack;    //包装方式
	
	private String handle;//处理人
	private String reason;//原因
	private String contactPhone;
	private String contacts;
	private String isHandle;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getIsDivision() {
		return isDivision;
	}

	public void setIsDivision(String isDivision) {
		this.isDivision = isDivision;
	}

	public String getIsDiv() {
		return isDiv;
	}

	public void setIsDiv(String isDiv) {
		this.isDiv = isDiv;
	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSampName() {
		return sampName;
	}

	public void setSampName(String sampName) {
		this.sampName = sampName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	public String getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}
	
	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}
	
	public String getSampNo() {
		return sampNo;
	}

	public void setSampNo(String sampNo) {
		this.sampNo = sampNo;
	}

	public String getSampSource() {
		return sampSource;
	}

	public void setSampSource(String sampSource) {
		this.sampSource = sampSource;
	}

	public String getSampUser() {
		return sampUser;
	}

	public void setSampUser(String sampUser) {
		this.sampUser = sampUser;
	}

	public String getSampDate() {
		return sampDate;
	}

	public void setSampDate(String sampDate) {
		this.sampDate = sampDate;
	}

	public String getSaveUser() {
		return saveUser;
	}

	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}

	public String getSaveOrg() {
		return saveOrg;
	}

	public void setSaveOrg(String saveOrg) {
		this.saveOrg = saveOrg;
	}

	public String getSampDesc() {
		return sampDesc;
	}

	public void setSampDesc(String sampDesc) {
		this.sampDesc = sampDesc;
	}

	public String getSampPack() {
		return sampPack;
	}

	public void setSampPack(String sampPack) {
		this.sampPack = sampPack;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public String getSaveMode() {
		return saveMode;
	}

	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}

	@Transient
	@Override
	public String getModelName() {
		return "业务管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "样品信息";
	}
}
