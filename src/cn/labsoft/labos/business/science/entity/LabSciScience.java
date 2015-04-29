package cn.labsoft.labos.business.science.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@SuppressWarnings("serial")
@Entity
public class LabSciScience extends AbstractBasePo {
	private String code;// 编码
	private String name;// 名称
	private String topic;// 课题
	private LabUser master;// 负责人
	private String startDate;
	private String endDate;
	private String source;// 来源类型
	private LabOrg labOrg;// 依托科室
	private String subject;// 学科
	private String domain;// 领域
	private String member;// 参与人员
	private String backdrop;// 背景
	private String feasibility;// 可行性
	private String goal;// 预期目标
	private String foundation;// 研究基础
	private String plan;// 方案或计划
	private String achievement;// 成就
	private String risk;// 风险
	private String remark;//备注

	private String auditor;
	private String auditDate;

	private String approver;
	private String approveDate;

	private String approvalDate;// 立项时间
	private double applyFunds;// 申请经费
	private double ratifyFunds;// 批准经费
	private double inFunds;// 到账经费
	private double outFunds;// 支出经费
	private double paperFunds;// 账面经费
	
	private LabWfProcessIns processIns;
	
	
	private String isPlan;//是否按计划
	private String schedule;//进度
	private String achieved;//取得的成就
	private String question;//存在的问题
	private String nextPlan;//下面的计划
	private String auditer;//审核人员
	private String checkAuditDate;//审核时间
	
	private String trueEndDate;//结束时间
	private double trueFunds;//实到资金
	private String discovery;//发现
	private String quota;//达到的目标
	private String meaning;//意义
	private String prospect;//应用前景
	private String problem;//存在的问题
	private String tasking;//任务分配
	private String endAuditer;//审核人
	private String endAuditDate;//审核时间
	
	private String isSeized;//是否中检
	private String isKnot;//是否结项
	private String isApply;

	public String getIsApply() {
		return isApply;
	}

	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}

	public String getIsKnot() {
		return isKnot;
	}

	public void setIsKnot(String isKnot) {
		this.isKnot = isKnot;
	}

	public String getIsSeized() {
		return isSeized;
	}

	public void setIsSeized(String isSeized) {
		this.isSeized = isSeized;
	}

	@Column(name="[code]",length = 32)
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(length = 64)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 64)
	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	@ManyToOne
	@JoinColumn(name="master_id")
	public LabUser getMaster() {
		return master;
	}

	public void setMaster(LabUser master) {
		this.master = master;
	}

	@Column(length = 32)
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Column(length = 32)
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	@ManyToOne
	@JoinColumn(name="processIns_id")
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}

	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}

	@Column(length = 512)
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(length = 64)
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@ManyToOne
	@JoinColumn(name="org_id")
	public LabOrg getLabOrg() {
		return labOrg;
	}

	public void setLabOrg(LabOrg labOrg) {
		this.labOrg = labOrg;
	}

	@Column(length = 32)
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	@Column(length = 32)
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Column(length = 128)
	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	@Column(length = 512)
	public String getBackdrop() {
		return backdrop;
	}

	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}

	@Column(length = 512)
	public String getFeasibility() {
		return feasibility;
	}

	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}

	@Column(length = 512)
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	@Column(length = 512)
	public String getFoundation() {
		return foundation;
	}

	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}

	@Column(length = 512)
	public String getPlan() {
		return plan;
	}

	public void setPlan(String plan) {
		this.plan = plan;
	}

	@Column(length = 512)
	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	@Column(length = 512)
	public String getRisk() {
		return risk;
	}

	public void setRisk(String risk) {
		this.risk = risk;
	}

	@Column(length = 64)
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	@Column(length = 32)
	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	@Column(length = 32)
	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	@Column(length = 32)
	public String getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}

	@Column(length = 32)
	public String getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Column(length = 11)
	public double getApplyFunds() {
		return applyFunds;
	}

	public void setApplyFunds(double applyFunds) {
		this.applyFunds = applyFunds;
	}

	@Column(length = 11)
	public double getRatifyFunds() {
		return ratifyFunds;
	}

	public void setRatifyFunds(double ratifyFunds) {
		this.ratifyFunds = ratifyFunds;
	}

	@Column(length = 11)
	public double getInFunds() {
		return inFunds;
	}

	public void setInFunds(double inFunds) {
		this.inFunds = inFunds;
	}
	
	@Column(length = 11)
	public double getOutFunds() {
		return outFunds;
	}

	public void setOutFunds(double outFunds) {
		this.outFunds = outFunds;
	}

	@Column(length = 11)
	public double getPaperFunds() {
		return paperFunds;
	}

	public void setPaperFunds(double paperFunds) {
		this.paperFunds = paperFunds;
	}
	
	@Column(length = 512)
	public String getIsPlan() {
		return isPlan;
	}
	public void setIsPlan(String isPlan) {
		this.isPlan = isPlan;
	}
	@Column(length = 512)
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	@Column(length = 512)
	public String getAchieved() {
		return achieved;
	}

	public void setAchieved(String achieved) {
		this.achieved = achieved;
	}
	
	@Column(length = 512)
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	@Column(length = 512)
	public String getNextPlan() {
		return nextPlan;
	}
	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}
	@Column(length = 32)
	public String getAuditer() {
		return auditer;
	}
	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
	@Column(length = 32)
	public String getCheckAuditDate() {
		return checkAuditDate;
	}

	public void setCheckAuditDate(String checkAuditDate) {
		this.checkAuditDate = checkAuditDate;
	}
	
	@Column(length = 32)
	public String getTrueEndDate() {
		return trueEndDate;
	}

	public void setTrueEndDate(String trueEndDate) {
		this.trueEndDate = trueEndDate;
	}

	@Column(length = 32)
	public double getTrueFunds() {
		return trueFunds;
	}

	public void setTrueFunds(double trueFunds) {
		this.trueFunds = trueFunds;
	}

	@Column(length = 512)
	public String getDiscovery() {
		return discovery;
	}

	public void setDiscovery(String discovery) {
		this.discovery = discovery;
	}

	@Column(length = 512)
	public String getQuota() {
		return quota;
	}

	public void setQuota(String quota) {
		this.quota = quota;
	}

	@Column(length = 512)
	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	@Column(length = 512)
	public String getProspect() {
		return prospect;
	}

	public void setProspect(String prospect) {
		this.prospect = prospect;
	}

	@Column(length = 512)
	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}


	@Column(length = 512)
	public String getTasking() {
		return tasking;
	}

	public void setTasking(String tasking) {
		this.tasking = tasking;
	}

	@Column(length = 32)
	public String getEndAuditer() {
		return endAuditer;
	}

	public void setEndAuditer(String endAuditer) {
		this.endAuditer = endAuditer;
	}


	@Column(length = 32)
	public String getEndAuditDate() {
		return endAuditDate;
	}

	public void setEndAuditDate(String endAuditDate) {
		this.endAuditDate = endAuditDate;
	}


	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "科研项目";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "科研项目表";
	}
}
