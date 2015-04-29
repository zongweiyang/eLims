package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



public class LabQuaProficiencyVo extends BaseVo {

	
	private String id;
	@ExcelAnnotation(exportName = "参加人")
	private String joinPeople;
	@ExcelAnnotation(exportName = "实施日期")
	private String planTime;
	@ExcelAnnotation(exportName = "比对/验证项目")
	private String ratioItem;//验证/对比项目
	@ExcelAnnotation(exportName = "计划实施日期")
	private String orgId;
	private String orgName;
	private String orgSearch;
	@ExcelAnnotation(exportName = "比对计划")
	private String proficiencyPlanId;
	private String proficiencyPlanName;
	@ExcelAnnotation(exportName = "经费")
	private Double payMoney; //经费预算
	@ExcelAnnotation(exportName = "比对/验证内容")
	private String contents; //比对/验证内容
	@ExcelAnnotation(exportName = "主任意见")
	private String directorViews; //主任意见
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getJoinPeople() {
		return joinPeople;
	}
	public void setJoinPeople(String joinPeople) {
		this.joinPeople = joinPeople;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getRatioItem() {
		return ratioItem;
	}
	public void setRatioItem(String ratioItem) {
		this.ratioItem = ratioItem;
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
	public String getOrgSearch() {
		return orgSearch;
	}
	public void setOrgSearch(String orgSearch) {
		this.orgSearch = orgSearch;
	}
	public String getProficiencyPlanId() {
		return proficiencyPlanId;
	}
	public void setProficiencyPlanId(String proficiencyPlanId) {
		this.proficiencyPlanId = proficiencyPlanId;
	}
	public String getProficiencyPlanName() {
		return proficiencyPlanName;
	}
	public void setProficiencyPlanName(String proficiencyPlanName) {
		this.proficiencyPlanName = proficiencyPlanName;
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
	public String getDirectorViews() {
		return directorViews;
	}
	public void setDirectorViews(String directorViews) {
		this.directorViews = directorViews;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}