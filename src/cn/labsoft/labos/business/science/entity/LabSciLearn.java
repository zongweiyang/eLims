package cn.labsoft.labos.business.science.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
@Table(name="lab_sci_learn")
public class LabSciLearn extends AbstractBasePo{
	private static final long serialVersionUID = 1L;
	private String type;//学术交流类型 1--会议   2--讲座  3--出差
	private String labOrgId;
	private String labOrgName;

	private String name;//名称
	private String learnType;//类型
	private LabSciScience labSciScience;
	private String startTime;
	private String endTime;
	private String place;
	private String participant; //参加人
	private String participantId;
	private String summary;//简介
	
    private String speaker;//主讲人
    private String speakerIntroduced;//主讲人介绍
    private double funds;//经费
    
	private String remark;//备注
	
	@Column(length = 64)
	public String getLearnType() {
		return learnType;
	}

	public void setLearnType(String learnType) {
		this.learnType = learnType;
	}

	@Column(length = 64)
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 512)
    public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Column(length = 32)
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	@Column(length = 32)
	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Column(length = 64)
	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@Column(length = 128)
	public String getParticipant() {
		return participant;
	}

	public void setParticipant(String participant) {
		this.participant = participant;
	}

	@Column(length = 512)
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(length = 32)
	public String getSpeaker() {
		return speaker;
	}

	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}

	@Column(length = 512)
	public String getSpeakerIntroduced() {
		return speakerIntroduced;
	}

	public void setSpeakerIntroduced(String speakerIntroduced) {
		this.speakerIntroduced = speakerIntroduced;
	}

	@Column(length = 11)
	public double getFunds() {
		return funds;
	}

	public void setFunds(double funds) {
		this.funds = funds;
	}

	@Column(length = 64)
	public String getLabOrgName() {
		return labOrgName;
	}

	public void setLabOrgName(String labOrgName) {
		this.labOrgName = labOrgName;
	}

	@Column(length = 64)
	public String getLabOrgId() {
		return labOrgId;
	}

	public void setLabOrgId(String labOrgId) {
		this.labOrgId = labOrgId;
	}
	
	@ManyToOne
	@JoinColumn(name="science_id")
	public LabSciScience getLabSciScience() {
		return labSciScience;
	}

	public void setLabSciScience(LabSciScience labSciScience) {
		this.labSciScience = labSciScience;
	}

	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "科研管理";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "学术交流";
	}

	public String getParticipantId() {
		return participantId;
	}

	public void setParticipantId(String participantId) {
		this.participantId = participantId;
	}

	

	
}
