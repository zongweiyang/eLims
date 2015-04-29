package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



public class LabQuaManageCheckPlanVo extends BaseVo {

	
	private String id;
	@ExcelAnnotation(exportName = "计划名称")
	private String name;//计划名称
	@ExcelAnnotation(exportName = "评审单位")
	private String orgId;
	private String orgName;
	private String orgSearch;
	@ExcelAnnotation(exportName = "评审主持人")
	private String trackPeople;//评审主持人
	@ExcelAnnotation(exportName = "评审时间")
	private String recTime;//评审时间
	@ExcelAnnotation(exportName = "批准人")
	private String checkPeople;//批准人
	@ExcelAnnotation(exportName = "批准时间")
	private String checkTime;//批准时间
	@ExcelAnnotation(exportName = "计划编制日期")
	private String planTime;//计划编制日期
	@ExcelAnnotation(exportName = "计划编制人")
	private String planUser;//计划编制人
	@ExcelAnnotation(exportName = "会议地点")
	private String address;//会议地点
	@ExcelAnnotation(exportName = "评审依据")
	private String foundation;//评审依据
	@ExcelAnnotation(exportName = "组长")
	private String groupLeader;//组长
	@ExcelAnnotation(exportName = "组员")
	private String groupMember; //组员
	@ExcelAnnotation(exportName = "评审内容")
	private String contents;//评审内容
	private String accStatus;
	
	
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
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
	public String getTrackPeople() {
		return trackPeople;
	}
	public void setTrackPeople(String trackPeople) {
		this.trackPeople = trackPeople;
	}
	public String getRecTime() {
		return recTime;
	}
	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}
	public String getCheckPeople() {
		return checkPeople;
	}
	public void setCheckPeople(String checkPeople) {
		this.checkPeople = checkPeople;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getPlanUser() {
		return planUser;
	}
	public void setPlanUser(String planUser) {
		this.planUser = planUser;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFoundation() {
		return foundation;
	}
	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}
	public String getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
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
	
}