package cn.labsoft.labos.source.reference.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_ref_reference")
public class LabReference extends AbstractBasePo {

	
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 7092835015748036311L;
	private String code;
	private LabRefType referenceType; // 标准品类型
	private String name;
	private String unit; // 标准品单位
	private String size; // 规格
	private int safeDate; // 标准品有效期
	private Double safeAmount; // 警戒数量
	private Double amount;// 实际数量
	private String remark;

	private String purity;// 纯度

	private String dangerSize;// 危险等级

	private String saveOrg;// 保管科室

	private String saveUser;// 保管人

	private String ext01;


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@ManyToOne
	@JoinColumn(name="type_id")
	public LabRefType getReferenceType() {
		return referenceType;
	}

	public void setReferenceType(LabRefType referenceType) {
		this.referenceType = referenceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSafeDate() {
		return safeDate;
	}

	public void setSafeDate(int safeDate) {
		this.safeDate = safeDate;
	}

	public Double getSafeAmount() {
		return safeAmount;
	}

	public void setSafeAmount(Double safeAmount) {
		this.safeAmount = safeAmount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public String getDangerSize() {
		return dangerSize;
	}

	public void setDangerSize(String dangerSize) {
		this.dangerSize = dangerSize;
	}

	public String getSaveOrg() {
		return saveOrg;
	}

	public void setSaveOrg(String saveOrg) {
		this.saveOrg = saveOrg;
	}

	public String getSaveUser() {
		return saveUser;
	}

	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}

	public String getExt01() {
		return ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}

	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "标准品管理";
	}

	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "标准品清单";
	}
}
