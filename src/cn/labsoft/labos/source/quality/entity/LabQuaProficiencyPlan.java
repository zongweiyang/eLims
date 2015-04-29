  package cn.labsoft.labos.source.quality.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**内部比对验证计划表
 */
@Entity
@Table(name="lab_qua_proficiency_plan")
public class LabQuaProficiencyPlan  extends AbstractBasePo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3461318269973433466L;
	private LabOrg org;
	private String planTime;//实施日期
	private String proficiencyType;//比对验证类型
	private String name;
	private String remark;
	private String auditPeople;//批准人/日期
	private String auditDate;
	private String contPeople;//计划人/日期
	private String contDate;
	private Double payMoney; //经费预算
	private String contents; //比对/验证内容
	private String status;
	private String isTest;    //生成检测业务
	private String accStatus;
	
	public String getAccStatus() {
		return accStatus;
	}


	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}


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
		return "能力验证";
	}

	@ManyToOne
	@JoinColumn(name="org_id")
	public LabOrg getOrg() {
		return org;
	}


	public void setOrg(LabOrg org) {
		this.org = org;
	}


	public String getPlanTime() {
		return planTime;
	}


	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}

	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getAuditPeople() {
		return auditPeople;
	}


	public void setAuditPeople(String auditPeople) {
		this.auditPeople = auditPeople;
	}


	public String getAuditDate() {
		return auditDate;
	}


	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}


	public String getContPeople() {
		return contPeople;
	}


	public void setContPeople(String contPeople) {
		this.contPeople = contPeople;
	}


	public String getContDate() {
		return contDate;
	}


	public void setContDate(String contDate) {
		this.contDate = contDate;
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
	@Column(name="plan_status")
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getProficiencyType() {
		return proficiencyType;
	}


	public void setProficiencyType(String proficiencyType) {
		this.proficiencyType = proficiencyType;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIsTest() {
		return isTest;
	}


	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}
	
}
