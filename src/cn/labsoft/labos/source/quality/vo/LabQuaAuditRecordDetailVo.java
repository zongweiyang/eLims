package cn.labsoft.labos.source.quality.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;


/**实验室现场内审记录表
 */

public class LabQuaAuditRecordDetailVo  extends BaseVo{

	private String id;
	private String initAuditPlanId;
	private String initAuditPlanName;
	private String initAuditPlanContentId;
	private String initAuditPlanContentName;
	private String initAuditPlanKeyId;
	private String initAuditPlanKeyName;
	private String problem;
    private String meetStatus;  //符合情况  0 不符合  1 基本符合 2符合
	private String trackPeople;
	private String recTime;
	private List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInitAuditPlanId() {
		return initAuditPlanId;
	}
	public void setInitAuditPlanId(String initAuditPlanId) {
		this.initAuditPlanId = initAuditPlanId;
	}
	public String getInitAuditPlanName() {
		return initAuditPlanName;
	}
	public void setInitAuditPlanName(String initAuditPlanName) {
		this.initAuditPlanName = initAuditPlanName;
	}
	public String getInitAuditPlanContentId() {
		return initAuditPlanContentId;
	}
	public void setInitAuditPlanContentId(String initAuditPlanContentId) {
		this.initAuditPlanContentId = initAuditPlanContentId;
	}
	public String getInitAuditPlanContentName() {
		return initAuditPlanContentName;
	}
	public void setInitAuditPlanContentName(String initAuditPlanContentName) {
		this.initAuditPlanContentName = initAuditPlanContentName;
	}
	public String getInitAuditPlanKeyId() {
		return initAuditPlanKeyId;
	}
	public void setInitAuditPlanKeyId(String initAuditPlanKeyId) {
		this.initAuditPlanKeyId = initAuditPlanKeyId;
	}
	public String getInitAuditPlanKeyName() {
		return initAuditPlanKeyName;
	}
	public void setInitAuditPlanKeyName(String initAuditPlanKeyName) {
		this.initAuditPlanKeyName = initAuditPlanKeyName;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getMeetStatus() {
		return meetStatus;
	}
	public void setMeetStatus(String meetStatus) {
		this.meetStatus = meetStatus;
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
	public List<LabQuaAuditRecordDetailVo> getLabQuaAuditRecordDetailVoList() {
		return labQuaAuditRecordDetailVoList;
	}
	public void setLabQuaAuditRecordDetailVoList(
			List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList) {
		this.labQuaAuditRecordDetailVoList = labQuaAuditRecordDetailVoList;
	}
	
}