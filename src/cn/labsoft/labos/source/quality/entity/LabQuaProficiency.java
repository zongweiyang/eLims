
package cn.labsoft.labos.source.quality.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**内部比对验证表
 */

@Entity
@Table(name="lab_qua_proficiency")
public class LabQuaProficiency  extends AbstractBasePo{

	private LabQuaProficiencyPlan labQuaProficiencyPlan;
	private LabOrg org;
	private String joinPeople;
	private String planTime;
	private String ratioItem;//验证/对比项目
	private Double payMoney; //经费预算
	private String contents; //比对/验证内容
	private String directorViews; //主任意见
	private String remark;
	
	@Transient
	@Override
	public String getModelName() {
		return "质量管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "比对验证记录";
	}
	@ManyToOne
	@JoinColumn(name="proficiency_plan_id")
	public LabQuaProficiencyPlan getLabQuaProficiencyPlan() {
		return labQuaProficiencyPlan;
	}
	public void setLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan) {
		this.labQuaProficiencyPlan = labQuaProficiencyPlan;
	}
	@ManyToOne
	@JoinColumn(name="org_id")
	public LabOrg getOrg() {
		return org;
	}
	public void setOrg(LabOrg org) {
		this.org = org;
	}
	public String getJoinPeople() {
		return joinPeople;
	}
	public void setJoinPeople(String joinPeople) {
		this.joinPeople = joinPeople;
	}
	public String getPlanTime() {
		return planTime;
	}
	public void setPlanTime(String planTime) {
		this.planTime = planTime;
	}
	public String getRatioItem() {
		return ratioItem;
	}
	public void setRatioItem(String ratioItem) {
		this.ratioItem = ratioItem;
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
	public String getDirectorViews() {
		return directorViews;
	}
	public void setDirectorViews(String directorViews) {
		this.directorViews = directorViews;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

