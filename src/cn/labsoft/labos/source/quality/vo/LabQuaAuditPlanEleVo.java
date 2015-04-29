package cn.labsoft.labos.source.quality.vo;


import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * 内部评审计划表(按要素)
 */

@SuppressWarnings("serial")
public class LabQuaAuditPlanEleVo extends BaseVo{
	
	private String id;
	@ExcelAnnotation(exportName = "制表人")
    private String createPeople;
	@ExcelAnnotation(exportName = "中心主任")
    private String auditPeople;
    private String auditPeopleSearch;
	private String orgId;
	@ExcelAnnotation(exportName = "单位")
	private String orgName;
	private String orgIdSearch;
	@ExcelAnnotation(exportName = "审核目的")
	private String purpose;  //审核目的
	@ExcelAnnotation(exportName = "审核范围")
	private String range;    //审核范围
	@ExcelAnnotation(exportName = "审核依据")
	private String implement;  //审核依据
	@ExcelAnnotation(exportName = "审核组长")
	private String checkHead;
	@ExcelAnnotation(exportName = "审核地点")
	private String address;
	@ExcelAnnotation(exportName = "审核组人员")
	private String groupMember; //审核组人员
	@ExcelAnnotation(exportName = "批准日期")
	private String auditTime;//批准日期
	@ExcelAnnotation(exportName = "审核方式")
	private String auditType;//审核方式
	private List<LabQuaAuditPlanEleDetailVo> labQuaAuditPlanEleDetailVoList;
	private String accStatus;//0未转为事故  1已转为事故   2事故已处理
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatePeople() {
		return createPeople;
	}
	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}
	public String getAuditPeople() {
		return auditPeople;
	}
	public void setAuditPeople(String auditPeople) {
		this.auditPeople = auditPeople;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getImplement() {
		return implement;
	}
	public void setImplement(String implement) {
		this.implement = implement;
	}
	public String getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}
	@Override
	public String getAuditTime() {
		return auditTime;
	}
	@Override
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getOrgIdSearch() {
		return orgIdSearch;
	}
	public void setOrgIdSearch(String orgIdSearch) {
		this.orgIdSearch = orgIdSearch;
	}
	public String getAuditPeopleSearch() {
		return auditPeopleSearch;
	}
	public void setAuditPeopleSearch(String auditPeopleSearch) {
		this.auditPeopleSearch = auditPeopleSearch;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public List<LabQuaAuditPlanEleDetailVo> getLabQuaAuditPlanEleDetailVoList() {
		return labQuaAuditPlanEleDetailVoList;
	}
	public void setLabQuaAuditPlanEleDetailVoList(
			List<LabQuaAuditPlanEleDetailVo> labQuaAuditPlanEleDetailVoList) {
		this.labQuaAuditPlanEleDetailVoList = labQuaAuditPlanEleDetailVoList;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public String getCheckHead() {
		return checkHead;
	}
	public void setCheckHead(String checkHead) {
		this.checkHead = checkHead;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	
}
