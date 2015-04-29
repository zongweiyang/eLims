package cn.labsoft.labos.source.quality.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 内部评审计划表(按要素)
 */

@Entity
@Table(name="lab_qua_plan_ele")
public class LabQuaAuditPlanEle extends AbstractBasePo{
	
    private String createPeople;//制表人
    private String auditPeople;//中心主任
	private LabOrg org;//单位
	private String purpose;  //审核目的
	private String range;    //审核范围
	private String implement;  //审核依据
	private String groupMember; //审核组人员
	private String auditTime;//批准日期
	private String status; //00 可修改，删除，查看， 10可修改，查看 20可查看
	private String updateDate;
	private String auditType;//审核方式
	private String checkHead;
	private String address;
	private String accStatus;//0未转为事故  1已转为事故   2事故已处理
	
	
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
		return "内部评审计划";
	}
	@Column(name="[status]")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatePeople() {
		return createPeople;
	}
	public void setCreatePeople(String createPeople) {
		this.createPeople = createPeople;
	}
	public String getAuditPeople() {
		return auditPeople;
	}
	public void setAuditPeople(String auditPeople) {
		this.auditPeople = auditPeople;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	@Column(name="[range]")
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public String getImplement() {
		return implement;
	}
	public void setImplement(String implement) {
		this.implement = implement;
	}
	public String getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	@ManyToOne
	@JoinColumn(name="org_id", nullable=false)
	public LabOrg getOrg() {
		return org;
	}
	public void setOrg(LabOrg org) {
		this.org = org;
	}
	
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public String getCheckHead() {
		return checkHead;
	}
	public void setCheckHead(String checkHead) {
		this.checkHead = checkHead;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
