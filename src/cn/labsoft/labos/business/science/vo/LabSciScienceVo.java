package cn.labsoft.labos.business.science.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


@SuppressWarnings("serial")
public class LabSciScienceVo extends BaseVo {
	
	private String id;
	@ExcelAnnotation(exportName = "编码")
	private String code;// 编码
	@ExcelAnnotation(exportName = "名称")
	private String name;// 名称
	@ExcelAnnotation(exportName = "课题")
	private String topic;// 课题
	
	@ExcelAnnotation(exportName = "负责人名称")
	private String masterName;// 负责人
	@ExcelAnnotation(exportName = "负责人id")
	private String masterId;// 负责人
	@ExcelAnnotation(exportName = "负责人职务")
	private String duty;
	@ExcelAnnotation(exportName = "负责人职称")
	private String techTitle;
	@ExcelAnnotation(exportName = "负责人电话")
	private String telephone;
	@ExcelAnnotation(exportName = "负责人邮箱")
	private String email;
	@ExcelAnnotation(exportName = "性别")
	private String sex;
	private List<LabSciAuthorVo> authorList;
	private List<LabSciFundsVo> labSciFundsList;
	
	
	@ExcelAnnotation(exportName = "开始日期")
	private String startDate;
	@ExcelAnnotation(exportName = "结束日期")
	private String endDate;
	@ExcelAnnotation(exportName = "来源类型")
	private String source;// 来源类型
	@ExcelAnnotation(exportName = "依托科室ID")
	private String labOrgId;// 依托科室
	@ExcelAnnotation(exportName = "依托科室名称")
	private String labOrgName;
	@ExcelAnnotation(exportName = "学科")
	private String subject;// 学科
	@ExcelAnnotation(exportName = "领域")
	private String domain;// 领域
	@ExcelAnnotation(exportName = "参与人员")
	private String member;// 参与人员
	@ExcelAnnotation(exportName = "背景")
	private String backdrop;// 背景
	@ExcelAnnotation(exportName = "可行性")
	private String feasibility;// 可行性
	@ExcelAnnotation(exportName = "预期目标")
	private String goal;// 预期目标
	@ExcelAnnotation(exportName = "研究基础")
	private String foundation;// 研究基础
	@ExcelAnnotation(exportName = "方案或计划")
	private String plan;// 方案或计划
	@ExcelAnnotation(exportName = "成就")
	private String achievement;// 成就
	@ExcelAnnotation(exportName = "风险")
	private String risk;// 风险
	@ExcelAnnotation(exportName = "审核人")
	private String auditor;
	@ExcelAnnotation(exportName = "审核时间")
	private String auditDate;
	@ExcelAnnotation(exportName = "审批人")
	private String approver;
	@ExcelAnnotation(exportName = "审批时间")
	private String approveDate;
	@ExcelAnnotation(exportName = "立项时间")
	private String approvalDate;// 立项时间
	@ExcelAnnotation(exportName = "流程id")
	private String processInsId;// 立项时间
	
	
	@ExcelAnnotation(exportName = "申请经费")
	private double applyFunds;// 申请经费
	@ExcelAnnotation(exportName = "批准经费")
	private double ratifyFunds;// 批准经费
	@ExcelAnnotation(exportName = "到账经费")
	private double inFunds;// 到账经费
	@ExcelAnnotation(exportName = "支出经费")
	private double outFunds;// 支出经费
	@ExcelAnnotation(exportName = "账面经费")
	private double paperFunds;// 账面经费
	
	@ExcelAnnotation(exportName = "是否按计划")
	private String isPlan;//是否按计划
	@ExcelAnnotation(exportName = "进度")
	private String schedule;//进度
	@ExcelAnnotation(exportName = "取得的成就")
	private String achieved;//取得的成就
	@ExcelAnnotation(exportName = "存在的问题")
	private String question;//存在的问题
	@ExcelAnnotation(exportName = "下面的计划")
	private String nextPlan;//下面的计划
	@ExcelAnnotation(exportName = "审核人员")
	private String auditer;//审核人员
	@ExcelAnnotation(exportName = "审核时间")
	private String checkAuditDate;//审核时间
	
	@ExcelAnnotation(exportName = "实际结束时间")
	private String trueEndDate;//结束时间
	@ExcelAnnotation(exportName = "实到资金")
	private double trueFunds;//实到资金
	@ExcelAnnotation(exportName = "发现")
	private String discovery;//发现
	@ExcelAnnotation(exportName = "达到的目标")
	private String quota;//达到的目标
	@ExcelAnnotation(exportName = "意义")
	private String meaning;//意义
	@ExcelAnnotation(exportName = "应用前景")
	private String prospect;//应用前景
	@ExcelAnnotation(exportName = "存在的问题")
	private String problem;//存在的问题
	@ExcelAnnotation(exportName = "任务分配")
	private String tasking;//任务分配
	@ExcelAnnotation(exportName = "审核人")
	private String endAuditer;//审核人
	@ExcelAnnotation(exportName = "审核时间")
	private String endAuditDate;//审核时间
	
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	
	//传值备用字段
	private String index;
	private String messageInfo;
	
	private String isSeized;//是否中检
	private String isKnot;//是否结项
	private String isApply;
	
	
	public String getIsApply() {
		return isApply;
	}
	public void setIsApply(String isApply) {
		this.isApply = isApply;
	}
	public String getIsSeized() {
		return isSeized;
	}
	public void setIsSeized(String isSeized) {
		this.isSeized = isSeized;
	}
	public String getIsKnot() {
		return isKnot;
	}
	public void setIsKnot(String isKnot) {
		this.isKnot = isKnot;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getProcessInsId() {
		return processInsId;
	}
	public void setProcessInsId(String processInsId) {
		this.processInsId = processInsId;
	}
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public String getMasterName() {
		return masterName;
	}
	public void setMasterName(String masterName) {
		this.masterName = masterName;
	}
	public String getMasterId() {
		return masterId;
	}
	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}
	@Override
	public String getStartDate() {
		return startDate;
	}
	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public String getDuty() {
		return duty;
	}
	public void setDuty(String duty) {
		this.duty = duty;
	}
	public String getTechTitle() {
		return techTitle;
	}
	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getEndDate() {
		return endDate;
	}
	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLabOrgId() {
		return labOrgId;
	}
	public void setLabOrgId(String labOrgId) {
		this.labOrgId = labOrgId;
	}
	
	public double getInFunds() {
		return inFunds;
	}
	public void setInFunds(double inFunds) {
		this.inFunds = inFunds;
	}
	public double getOutFunds() {
		return outFunds;
	}
	public void setOutFunds(double outFunds) {
		this.outFunds = outFunds;
	}
	public double getPaperFunds() {
		return paperFunds;
	}
	public void setPaperFunds(double paperFunds) {
		this.paperFunds = paperFunds;
	}
	public String getLabOrgName() {
		return labOrgName;
	}
	public void setLabOrgName(String labOrgName) {
		this.labOrgName = labOrgName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getBackdrop() {
		return backdrop;
	}
	public void setBackdrop(String backdrop) {
		this.backdrop = backdrop;
	}
	public String getFeasibility() {
		return feasibility;
	}
	public void setFeasibility(String feasibility) {
		this.feasibility = feasibility;
	}
	public String getGoal() {
		return goal;
	}
	public void setGoal(String goal) {
		this.goal = goal;
	}
	public String getFoundation() {
		return foundation;
	}
	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getAchievement() {
		return achievement;
	}
	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}
	public String getRisk() {
		return risk;
	}
	public void setRisk(String risk) {
		this.risk = risk;
	}
	public String getAuditor() {
		return auditor;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
	public String getApprover() {
		return approver;
	}
	public void setApprover(String approver) {
		this.approver = approver;
	}
	public String getApproveDate() {
		return approveDate;
	}
	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public double getApplyFunds() {
		return applyFunds;
	}
	public void setApplyFunds(double applyFunds) {
		this.applyFunds = applyFunds;
	}
	public double getRatifyFunds() {
		return ratifyFunds;
	}
	public void setRatifyFunds(double ratifyFunds) {
		this.ratifyFunds = ratifyFunds;
	}
	public List<LabSciAuthorVo> getAuthorList() {
		return authorList;
	}
	public void setAuthorList(List<LabSciAuthorVo> authorList) {
		this.authorList = authorList;
	}
	public List<LabSciFundsVo> getLabSciFundsList() {
		return labSciFundsList;
	}
	public void setLabSciFundsList(List<LabSciFundsVo> labSciFundsList) {
		this.labSciFundsList = labSciFundsList;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsPlan() {
		return isPlan;
	}
	public void setIsPlan(String isPlan) {
		this.isPlan = isPlan;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getAchieved() {
		return achieved;
	}
	public void setAchieved(String achieved) {
		this.achieved = achieved;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getNextPlan() {
		return nextPlan;
	}
	public void setNextPlan(String nextPlan) {
		this.nextPlan = nextPlan;
	}
	public String getAuditer() {
		return auditer;
	}
	public void setAuditer(String auditer) {
		this.auditer = auditer;
	}
	public String getCheckAuditDate() {
		return checkAuditDate;
	}
	public void setCheckAuditDate(String checkAuditDate) {
		this.checkAuditDate = checkAuditDate;
	}
	public String getTrueEndDate() {
		return trueEndDate;
	}
	public void setTrueEndDate(String trueEndDate) {
		this.trueEndDate = trueEndDate;
	}
	public double getTrueFunds() {
		return trueFunds;
	}
	public void setTrueFunds(double trueFunds) {
		this.trueFunds = trueFunds;
	}
	public String getDiscovery() {
		return discovery;
	}
	public void setDiscovery(String discovery) {
		this.discovery = discovery;
	}
	public String getQuota() {
		return quota;
	}
	public void setQuota(String quota) {
		this.quota = quota;
	}
	public String getMeaning() {
		return meaning;
	}
	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}
	public String getProspect() {
		return prospect;
	}
	public void setProspect(String prospect) {
		this.prospect = prospect;
	}
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public String getTasking() {
		return tasking;
	}
	public void setTasking(String tasking) {
		this.tasking = tasking;
	}
	public String getEndAuditer() {
		return endAuditer;
	}
	public void setEndAuditer(String endAuditer) {
		this.endAuditer = endAuditer;
	}
	public String getEndAuditDate() {
		return endAuditDate;
	}
	public void setEndAuditDate(String endAuditDate) {
		this.endAuditDate = endAuditDate;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getMessageInfo() {
		return messageInfo;
	}
	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}
	
}
