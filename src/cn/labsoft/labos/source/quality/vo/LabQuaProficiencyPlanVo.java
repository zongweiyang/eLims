package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



public class LabQuaProficiencyPlanVo extends BaseVo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3928446554236015192L;
	private String id;
	@ExcelAnnotation(exportName = "计划实施日期")
	private String planTime;//实施日期
	@ExcelAnnotation(exportName = "比对/验证类型")
	private String ProficiencyType;//比对验证类型
	@ExcelAnnotation(exportName = "计划名称")
	private String name;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "单位")
	private String unitOrgId;
	private String unitOrgName;
	private String unitOrgSearch;
	@ExcelAnnotation(exportName = "批准人")
	private String auditPeople;//批准人/日期
	@ExcelAnnotation(exportName = "批准日期")
	private String auditDate;
	@ExcelAnnotation(exportName = "计划人")
	private String contPeople;
	@ExcelAnnotation(exportName = "计划日期")
	private String contDate;
	private String contPeopleSearch;
	@ExcelAnnotation(exportName = "经费预算")
	private Double payMoney; //经费预算
	@ExcelAnnotation(exportName = "比对/验证内容")
	private String contents; //比对/验证内容
	private String isTest;    //生成检测业务
	
	private String sampRegisterNo;   //任务编号
	private String sampRegisterTitle;//标题或项目
	private String sampRegisterUser; //联系人
	private String sampRegisterTel;  //联系电话
	private String sampRegisterId;//任务Id
	private String sampRegisterNum;
	private String sampRegisterSampNo;
	
	
	private String accStatus;
	
	
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getSampRegisterNum() {
		return sampRegisterNum;
	}
	public void setSampRegisterNum(String sampRegisterNum) {
		this.sampRegisterNum = sampRegisterNum;
	}
	public String getSampRegisterId() {
		return sampRegisterId;
	}
	public void setSampRegisterId(String sampRegisterId) {
		this.sampRegisterId = sampRegisterId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getProficiencyType() {
		return ProficiencyType;
	}
	public void setProficiencyType(String proficiencyType) {
		ProficiencyType = proficiencyType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getUnitOrgId() {
		return unitOrgId;
	}
	public void setUnitOrgId(String unitOrgId) {
		this.unitOrgId = unitOrgId;
	}
	public String getUnitOrgName() {
		return unitOrgName;
	}
	public void setUnitOrgName(String unitOrgName) {
		this.unitOrgName = unitOrgName;
	}
	public String getUnitOrgSearch() {
		return unitOrgSearch;
	}
	public void setUnitOrgSearch(String unitOrgSearch) {
		this.unitOrgSearch = unitOrgSearch;
	}
	public String getAuditPeople() {
		return auditPeople;
	}
	public void setAuditPeople(String auditPeople) {
		this.auditPeople = auditPeople;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getContPeople() {
		return contPeople;
	}
	public void setContPeople(String contPeople) {
		this.contPeople = contPeople;
	}
	public String getContDate() {
		return contDate;
	}
	public void setContDate(String contDate) {
		this.contDate = contDate;
	}
	public String getContPeopleSearch() {
		return contPeopleSearch;
	}
	public void setContPeopleSearch(String contPeopleSearch) {
		this.contPeopleSearch = contPeopleSearch;
	}
	public Double getPayMoney() {
		return payMoney;
	}
	public void setPayMoney(Double payMoney) {
		this.payMoney = payMoney;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsTest() {
		if(isTest==null)isTest=Constants_Source.N;
		return isTest;
	}
	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
	public String getSampRegisterNo() {
		return sampRegisterNo;
	}
	public void setSampRegisterNo(String sampRegisterNo) {
		this.sampRegisterNo = sampRegisterNo;
	}
	public String getSampRegisterTitle() {
		return sampRegisterTitle;
	}
	public void setSampRegisterTitle(String sampRegisterTitle) {
		this.sampRegisterTitle = sampRegisterTitle;
	}
	public String getSampRegisterUser() {
		return sampRegisterUser;
	}
	public void setSampRegisterUser(String sampRegisterUser) {
		this.sampRegisterUser = sampRegisterUser;
	}
	public String getSampRegisterTel() {
		return sampRegisterTel;
	}
	public void setSampRegisterTel(String sampRegisterTel) {
		this.sampRegisterTel = sampRegisterTel;
	}
	public String getSampRegisterSampNo() {
		return sampRegisterSampNo;
	}
	public void setSampRegisterSampNo(String sampRegisterSampNo) {
		this.sampRegisterSampNo = sampRegisterSampNo;
	}
	
}