package cn.labsoft.labos.source.quality.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.source.customer.entity.LabCustomer;


@Entity
@Table(name="lab_qua_accident")
public class LabQuaAccident  extends AbstractBasePo{
	
	private LabCustomer labCustomer;//事故单位
	private String accDecs;
	private String accAna;
	private String procOpin;
	private String repPeople;
	private String chePeople;
	private String resPeople;
	private String repTime;
	private String cheTime;
	private String procTime;
	private String accType;
	private String accTime;
	private String accPeople;
	private String accStatus;
	private LabOrg unitOrg;
	private String otherId;
	private String otherName;
	
	
	public String getOtherId() {
		return otherId;
	}


	public void setOtherId(String otherId) {
		this.otherId = otherId;
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
		return "事故报告处理";
	}
	
	public String getAccDecs() {
		return accDecs;
	}
	public void setAccDecs(String accDecs) {
		this.accDecs = accDecs;
	}
	public String getAccAna() {
		return accAna;
	}
	public void setAccAna(String accAna) {
		this.accAna = accAna;
	}
	public String getProcOpin() {
		return procOpin;
	}
	public void setProcOpin(String procOpin) {
		this.procOpin = procOpin;
	}
	public String getRepPeople() {
		return repPeople;
	}
	public void setRepPeople(String repPeople) {
		this.repPeople = repPeople;
	}
	public String getChePeople() {
		return chePeople;
	}
	public void setChePeople(String chePeople) {
		this.chePeople = chePeople;
	}
	public String getResPeople() {
		return resPeople;
	}
	public void setResPeople(String resPeople) {
		this.resPeople = resPeople;
	}
	@ManyToOne
	@JoinColumn(name="customer_id")
	public LabCustomer getLabCustomer() {
		return labCustomer;
	}

	public void setLabCustomer(LabCustomer labCustomer) {
		this.labCustomer = labCustomer;
	}


	public String getRepTime() {
		return repTime;
	}


	public void setRepTime(String repTime) {
		this.repTime = repTime;
	}


	public String getCheTime() {
		return cheTime;
	}


	public void setCheTime(String cheTime) {
		this.cheTime = cheTime;
	}


	public String getProcTime() {
		return procTime;
	}


	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}

	@ManyToOne
	@JoinColumn(name="unit_org_id")
	public LabOrg getUnitOrg() {
		return unitOrg;
	}


	public void setUnitOrg(LabOrg unitOrg) {
		this.unitOrg = unitOrg;
	}


	public String getAccType() {
		return accType;
	}


	public void setAccType(String accType) {
		this.accType = accType;
	}


	public String getAccTime() {
		return accTime;
	}


	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}


	public String getAccPeople() {
		return accPeople;
	}


	public void setAccPeople(String accPeople) {
		this.accPeople = accPeople;
	}


	public String getAccStatus() {
		return accStatus;
	}


	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}


	public String getOtherName() {
		return otherName;
	}


	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	
	
	
}
