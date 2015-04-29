package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;





/**内部评审计划表(按要素)详细
 */

@SuppressWarnings("serial")
public class LabQuaAuditPlanEleDetailVo  extends BaseVo{

	private String id;
	private String quaInitAuditPlanId;
	private String quaInitAuditPlanName;
	private String quaAuditPlanEleId;
	private String quaAuditPlanEleName;
    private String month;    //月份之间用","号隔开
    private String ruleNum;
	private String groupLeader; //负责人
	private String helpPeople; //协助人
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getQuaInitAuditPlanId() {
		return quaInitAuditPlanId;
	}
	public void setQuaInitAuditPlanId(String quaInitAuditPlanId) {
		this.quaInitAuditPlanId = quaInitAuditPlanId;
	}
	public String getQuaInitAuditPlanName() {
		return quaInitAuditPlanName;
	}
	public void setQuaInitAuditPlanName(String quaInitAuditPlanName) {
		this.quaInitAuditPlanName = quaInitAuditPlanName;
	}
	public String getQuaAuditPlanEleId() {
		return quaAuditPlanEleId;
	}
	public void setQuaAuditPlanEleId(String quaAuditPlanEleId) {
		this.quaAuditPlanEleId = quaAuditPlanEleId;
	}
	public String getQuaAuditPlanEleName() {
		return quaAuditPlanEleName;
	}
	public void setQuaAuditPlanEleName(String quaAuditPlanEleName) {
		this.quaAuditPlanEleName = quaAuditPlanEleName;
	}
	
}
