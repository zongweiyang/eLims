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
 * 管理评审计划表
 */

@Entity
@Table(name="lab_qua_manage_plan")
public class LabQuaManageCheckPlan extends AbstractBasePo{
	
	private LabOrg org;
	private String trackPeople;//评审主持人
	private String name;//
	private String recTime;//评审时间
	private String checkPeople;//批准人
	private String checkTime;//批准时间
	private String planTime;//计划编制日期
	private String planUser;//计划编制人
	private String address;//会议地点
	private String foundation;//评审依据
	private String groupMember;//组员
	private String contents;//评审内容
	private String status;
	private String groupLeader;//组长
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
		return "管理评审计划";
	}
	
	@ManyToOne
	@JoinColumn(name="org_id", nullable=false)
	public LabOrg getOrg() {
		return org;
	}
	public void setOrg(LabOrg org) {
		this.org = org;
	}
	@Column(name="[status]")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getCheckPeople() {
		return checkPeople;
	}
	public void setCheckPeople(String checkPeople) {
		this.checkPeople = checkPeople;
	}
	public String getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(String checkTime) {
		this.checkTime = checkTime;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getPlanUser() {
		return planUser;
	}
	public void setPlanUser(String planUser) {
		this.planUser = planUser;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFoundation() {
		return foundation;
	}
	public void setFoundation(String foundation) {
		this.foundation = foundation;
	}
	public String getGroupMember() {
		return groupMember;
	}
	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getGroupLeader() {
		return groupLeader;
	}
	public void setGroupLeader(String groupLeader) {
		this.groupLeader = groupLeader;
	}
	@Column(name="[name]")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
