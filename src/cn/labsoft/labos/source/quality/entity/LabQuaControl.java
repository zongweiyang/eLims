package cn.labsoft.labos.source.quality.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 质量监督抽查
 */

@Entity
@Table(name="lab_qua_control")
public class LabQuaControl  extends AbstractBasePo{

	private String anaNo;
	private String place;
	private String standardId;
	private String standardName;
	private String appCode;
	private String appId;
	private String appNo;
	private String testTime;
	private String evaluation;
	private String anaPeople;
	private String conPeople;
	private String conTime;
	private LabOrg unitOrg;
	private LabOrg labOrg;
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
		return "质量监督抽查";
	}


	public String getAnaNo() {
		return anaNo;
	}


	public void setAnaNo(String anaNo) {
		this.anaNo = anaNo;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public String getStandardId() {
		return standardId;
	}


	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}


	public String getStandardName() {
		return standardName;
	}


	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}


	public String getAppCode() {
		return appCode;
	}


	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}


	public String getAppId() {
		return appId;
	}


	public void setAppId(String appId) {
		this.appId = appId;
	}


	public String getAppNo() {
		return appNo;
	}


	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}


	public String getTestTime() {
		return testTime;
	}


	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}


	public String getEvaluation() {
		return evaluation;
	}


	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}


	public String getAnaPeople() {
		return anaPeople;
	}


	public void setAnaPeople(String anaPeople) {
		this.anaPeople = anaPeople;
	}


	public String getConPeople() {
		return conPeople;
	}


	public void setConPeople(String conPeople) {
		this.conPeople = conPeople;
	}


	public String getConTime() {
		return conTime;
	}


	public void setConTime(String conTime) {
		this.conTime = conTime;
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
	@JoinColumn(name="lab_org_id")
	public LabOrg getLabOrg() {
		return labOrg;
	}


	public void setLabOrg(LabOrg labOrg) {
		this.labOrg = labOrg;
	}
	
}
