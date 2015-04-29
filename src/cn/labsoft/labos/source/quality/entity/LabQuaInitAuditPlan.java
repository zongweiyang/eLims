package cn.labsoft.labos.source.quality.entity;




import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;


/**初始化评审计划
 */

@Entity
@Table(name="lab_qua_init_plan")
public class LabQuaInitAuditPlan extends AbstractBasePo{

	private LabQuaInitAuditPlan quaInitAuditPlan;
    private String name;
	private String rank;   //层级结构  1：一级 2： 二级  3：三级
	
	
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "初始内审要素";
	}


	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "初始化内部评审计划(按要素)";
	}
	@ManyToOne
	@JoinColumn(name="init_plan_id")
	public LabQuaInitAuditPlan getQuaInitAuditPlan() {
		return quaInitAuditPlan;
	}
	public void setQuaInitAuditPlan(LabQuaInitAuditPlan quaInitAuditPlan) {
		this.quaInitAuditPlan = quaInitAuditPlan;
	}
	@Column(name="[name]")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}
