package cn.labsoft.labos.source.quality.entity;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.source.customer.entity.LabCustomer;

/**客户回访登记表
 */

@Entity
@Table(name="lab_qua_cus_visit")
public class LabQuaCusVisit  extends AbstractBasePo{

	private String name;
	private String no;
	private String suggestion;
	private String handle;
	private String phone;
	private String visitTime;
	private LabOrg unitOrg;
	private LabOrg labOrg;
	private LabCustomer labCustomer;
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
		return "客户回访登记";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getVisitTime() {
		return visitTime;
	}


	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}

	@ManyToOne
	@JoinColumn(name="unit_org_id")
	public LabOrg getUnitOrg() {
		return unitOrg;
	}


	public void setUnitOrg(LabOrg unitOrg) {
		this.unitOrg = unitOrg;
	}

	@ManyToOne
	@JoinColumn(name="customer_id")
	public LabCustomer getLabCustomer() {
		return labCustomer;
	}


	public void setLabCustomer(LabCustomer labCustomer) {
		this.labCustomer = labCustomer;
	}


	public String getNo() {
		return no;
	}


	public void setNo(String no) {
		this.no = no;
	}

	@ManyToOne
	@JoinColumn(name="lab_org_id")
	public LabOrg getLabOrg() {
		return labOrg;
	}


	public void setLabOrg(LabOrg labOrg) {
		this.labOrg = labOrg;
	}

	
	
}
