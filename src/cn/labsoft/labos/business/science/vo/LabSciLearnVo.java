package cn.labsoft.labos.business.science.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@SuppressWarnings("serial")
public class LabSciLearnVo extends BaseVo {

	@ExcelAnnotation(exportName = "id")
	private String id;
	@ExcelAnnotation(exportName = "学术交流类型")
	private String type;//学术交流类型
	@ExcelAnnotation(exportName = "组织id")
	private String labOrgId;
	@ExcelAnnotation(exportName = "组织名称")
	private String labOrgName;
	
	
	@ExcelAnnotation(exportName = "名称")
	private String name;//名称
	@ExcelAnnotation(exportName = "科研项目id")
	private String labSciScienceId;//科研项目id
	@ExcelAnnotation(exportName = "科研项目名称")
	private String labSciScienceName;//科研项目名称
	@ExcelAnnotation(exportName = "科研项目编号")
	private String labSciScienceCode;//科研项目编号
	
	@ExcelAnnotation(exportName = "学术类型")
	private String learnType;//学术类型
	@ExcelAnnotation(exportName = "开始时间")
	private String startTime;
	@ExcelAnnotation(exportName = "结束时间")
	private String endTime;
	@ExcelAnnotation(exportName = "地点")
	private String place;
	@ExcelAnnotation(exportName = "参加人")
	private String participant; //参加人
	private String participantId;
	@ExcelAnnotation(exportName = "简介")
	private String summary;//简介
	
	@ExcelAnnotation(exportName = "主讲人")
    private String speaker;//主讲人
	@ExcelAnnotation(exportName = "主讲人介绍")
    private String speakerIntroduced;//主讲人介绍
	@ExcelAnnotation(exportName = "经费")
    private double funds;//经费
	
	@ExcelAnnotation(exportName = "备注")
	private String remark;//备注
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getLabSciScienceId() {
		return labSciScienceId;
	}
	public void setLabSciScienceId(String labSciScienceId) {
		this.labSciScienceId = labSciScienceId;
	}
	public String getLabSciScienceName() {
		return labSciScienceName;
	}
	public void setLabSciScienceName(String labSciScienceName) {
		this.labSciScienceName = labSciScienceName;
	}
	
	public String getLabSciScienceCode() {
		return labSciScienceCode;
	}
	public void setLabSciScienceCode(String labSciScienceCode) {
		this.labSciScienceCode = labSciScienceCode;
	}
	public String getLabOrgId() {
		return labOrgId;
	}
	public void setLabOrgId(String labOrgId) {
		this.labOrgId = labOrgId;
	}
	public String getLabOrgName() {
		return labOrgName;
	}
	public void setLabOrgName(String labOrgName) {
		this.labOrgName = labOrgName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getParticipant() {
		return participant;
	}
	public void setParticipant(String participant) {
		this.participant = participant;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSpeaker() {
		return speaker;
	}
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	public String getSpeakerIntroduced() {
		return speakerIntroduced;
	}
	public void setSpeakerIntroduced(String speakerIntroduced) {
		this.speakerIntroduced = speakerIntroduced;
	}
	public double getFunds() {
		return funds;
	}
	public void setFunds(double funds) {
		this.funds = funds;
	}
	public String getLearnType() {
		return learnType;
	}
	public void setLearnType(String learnType) {
		this.learnType = learnType;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getParticipantId() {
		return participantId;
	}
	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}
	
	
}
