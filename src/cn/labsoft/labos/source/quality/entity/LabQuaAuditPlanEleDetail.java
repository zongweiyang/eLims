package cn.labsoft.labos.source.quality.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**内部评审计划表(按要素)详细
 */

@Entity
@Table(name="lab_qua_plan_ele_detail")
public class LabQuaAuditPlanEleDetail extends AbstractBasePo{

	private LabQuaInitAuditPlan quaInitAuditPlan;
	private LabQuaAuditPlanEle quaAuditPlanEle;
    private String month;
    private String ruleNum;
	private String groupLeader; //负责人
	private String helpPeople; //协助人
	
	
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
		return "内部评审计划(按要素)详细";
	}
	@Column(name="[month]")
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getRuleNum() {
		return ruleNum;
	}
	public void setRuleNum(String ruleNum) {
		this.ruleNum = ruleNum;
	}
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}
	public String getHelpPeople() {
		return helpPeople;
	}
	public void setHelpPeople(String helpPeople) {
		this.helpPeople = helpPeople;
	}

	@ManyToOne
	@JoinColumn(name="init_plan_id", nullable=false)
	public LabQuaInitAuditPlan getQuaInitAuditPlan() {
		return quaInitAuditPlan;
	}


	public void setQuaInitAuditPlan(LabQuaInitAuditPlan quaInitAuditPlan) {
		this.quaInitAuditPlan = quaInitAuditPlan;
	}

	@ManyToOne
	@JoinColumn(name="plan_ele_id", nullable=false)
	public LabQuaAuditPlanEle getQuaAuditPlanEle() {
		return quaAuditPlanEle;
	}

	public void setQuaAuditPlanEle(LabQuaAuditPlanEle quaAuditPlanEle) {
		this.quaAuditPlanEle = quaAuditPlanEle;
	}
	
}
