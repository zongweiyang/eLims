
package cn.labsoft.labos.source.quality.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.source.customer.entity.LabCustomer;

/**
 * 投诉处理表
 */

@Entity
@Table(name="lab_qua_complain")
public class LabQuaComplain  extends AbstractBasePo{

	private LabCustomer labCustomer;
	private String no;
	private String name;
	private String appTime;
	private String content;
	private String result;
	private String sampName;
	private String measures;
	private String skillPerson;
	private String qualityPerson;
	private String suggestion;
	private String skillTime;
	private String qualityTime;
	private String itemId;
	private String itemName;
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
		return "业务投诉";
	}
	@Column(name="[name]")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSampName() {
		return sampName;
	}
	public void setSampName(String sampName) {
		this.sampName = sampName;
	}
	public String getMeasures() {
		return measures;
	}
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	public String getSkillPerson() {
		return skillPerson;
	}
	public void setSkillPerson(String skillPerson) {
		this.skillPerson = skillPerson;
	}
	public String getQualityPerson() {
		return qualityPerson;
	}
	public void setQualityPerson(String qualityPerson) {
		this.qualityPerson = qualityPerson;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getSkillTime() {
		return skillTime;
	}
	public void setSkillTime(String skillTime) {
		this.skillTime = skillTime;
	}
	public String getQualityTime() {
		return qualityTime;
	}
	public void setQualityTime(String qualityTime) {
		this.qualityTime = qualityTime;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	@ManyToOne
	@JoinColumn(name="customer_id")
	public LabCustomer getLabCustomer() {
		return labCustomer;
	}
	public void setLabCustomer(LabCustomer labCustomer) {
		this.labCustomer = labCustomer;
	}
	
}

