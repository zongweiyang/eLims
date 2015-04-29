package cn.labsoft.labos.source.quality.entity;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**内审记录(详细)
 */

@Entity
@Table(name="lab_qua_audit_record_detail")
public class LabQuaAuditRecordDetail  extends AbstractBasePo{

	private LabQuaAuditRecord labQuaAuditRecord;
	private LabQuaInitAuditPlan labQuaInitAuditPlan;
	private LabQuaInitAuditPlan labQuaInitAuditPlanContent;
	private LabQuaInitAuditPlan labQuaInitAuditPlanKey;
	private String problem;
    private String meetStatus;  //符合情况  0 不符合  1 基本符合 2符合
	private String trackPeople;
	private String recTime;
	
	
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "质量管理";
	}


	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "内审记录(详细)";
	}

	@ManyToOne
	@JoinColumn(name="audit_record_id")
	public LabQuaAuditRecord getLabQuaAuditRecord() {
		return labQuaAuditRecord;
	}


	public void setLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord) {
		this.labQuaAuditRecord = labQuaAuditRecord;
	}

	@ManyToOne
	@JoinColumn(name="init_audit_plan_id")
	public LabQuaInitAuditPlan getLabQuaInitAuditPlan() {
		return labQuaInitAuditPlan;
	}

	
	public void setLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan) {
		this.labQuaInitAuditPlan = labQuaInitAuditPlan;
	}

	@ManyToOne
	@JoinColumn(name="init_audit_plan_content_id")
	public LabQuaInitAuditPlan getLabQuaInitAuditPlanContent() {
		return labQuaInitAuditPlanContent;
	}


	public void setLabQuaInitAuditPlanContent(
			LabQuaInitAuditPlan labQuaInitAuditPlanContent) {
		this.labQuaInitAuditPlanContent = labQuaInitAuditPlanContent;
	}

	@ManyToOne
	@JoinColumn(name="init_audit_plan_key_id")
	public LabQuaInitAuditPlan getLabQuaInitAuditPlanKey() {
		return labQuaInitAuditPlanKey;
	}


	public void setLabQuaInitAuditPlanKey(LabQuaInitAuditPlan labQuaInitAuditPlanKey) {
		this.labQuaInitAuditPlanKey = labQuaInitAuditPlanKey;
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
	
	
}
