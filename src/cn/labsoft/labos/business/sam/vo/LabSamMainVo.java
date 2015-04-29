package cn.labsoft.labos.business.sam.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;
/**
 * 
 * <strong>Title : LabSamMain </strong>. <br>
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
public class LabSamMainVo extends BaseVo {
	
	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "样品类型")
	private String labSamTypeId;  
	@ExcelAnnotation(exportName = "样品类型")
	private String labSamTypeName; 
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "任务Id")
	private String busId;	   // 任务Id
	@ExcelAnnotation(exportName = "登记日期")
	private String registDate;
	@ExcelAnnotation(exportName = "备注")
	private String remark;  
	@ExcelAnnotation(exportName = "样品详情集合")
	private List<LabSamVo> labSamVoList;
	@ExcelAnnotation(exportName = "样品编号")
	private List<String> listCode;
	@ExcelAnnotation(exportName = "样品数量")
	private String num;
	@ExcelAnnotation(exportName = "是否分样")
	private String isDivision;
	@ExcelAnnotation(exportName = "客户名称")
	private String customer;
	@ExcelAnnotation(exportName = "联系人")
	private String contacts;
	@ExcelAnnotation(exportName = "联系方式")
	private String contactPhone;
	private String isDiv;
	private String sampName;
	private String receivePerson;
	@ExcelAnnotation(exportName = "处理人")
	private String handle;
	@ExcelAnnotation(exportName = "处理原因")
	private String reason;
	@ExcelAnnotation(exportName = "来样编号")
	private String sampNo;
	@ExcelAnnotation(exportName = "样品来源")
	private String sampSource; 
	@ExcelAnnotation(exportName = "送样人员")
	private String sampUser; 
	@ExcelAnnotation(exportName = "送样日期")
	private String sampDate; 
	@ExcelAnnotation(exportName = "保管人")
	private String saveUser; 
	@ExcelAnnotation(exportName = "存放地点")
	private String saveOrg; 
	@ExcelAnnotation(exportName = "处理情况")
	private String sampDesc; 
	@ExcelAnnotation(exportName = "包装方式")
	private String sampPack;
	@ExcelAnnotation(exportName = "任务编号")
	private String taskCode;
	private String saveMode;     //保存方式
	private String isHandle;//是否已处理
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public List<LabSamVo> getLabSamVoList() {
		return labSamVoList;
	}

	public void setLabSamVoList(List<LabSamVo> labSamVoList) {
		this.labSamVoList = labSamVoList;
	}

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

	public String getSampName() {
		return sampName;
	}

	public void setSampName(String sampName) {
		this.sampName = sampName;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
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

	public String getIsHandle() {
		return isHandle;
	}

	public void setIsHandle(String isHandle) {
		this.isHandle = isHandle;
	}

	public List<String> getListCode() {
		return listCode;
	}

	public void setListCode(List<String> listCode) {
		this.listCode = listCode;
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

	public String getTaskCode() {
		return taskCode;
	}

	public void setTaskCode(String taskCode) {
		this.taskCode = taskCode;
	}

	public String getSaveMode() {
		return saveMode;
	}

	public void setSaveMode(String saveMode) {
		this.saveMode = saveMode;
	}

	public String getLabSamTypeId() {
		return labSamTypeId;
	}

	public void setLabSamTypeId(String labSamTypeId) {
		this.labSamTypeId = labSamTypeId;
	}

	public String getLabSamTypeName() {
		return labSamTypeName;
	}

	public void setLabSamTypeName(String labSamTypeName) {
		this.labSamTypeName = labSamTypeName;
	}
	
}
