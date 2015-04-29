package cn.labsoft.labos.source.consumables.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * @title 耗材表
 * @author Bill
 */
@Entity
@Table(name="lab_con_consumables")
public class LabConsumables extends AbstractBasePo {
    
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 2913597349552537832L;
	private String code;
	private LabConType consumablesType; // 耗材类型
	private String name;
	private String unit; // 供应商
	private String size; // 规格
	private int safeDate; // 耗材有效期
	private Double safeAmount; // 警戒数量
	private Double amount;// 实际数量
	private String remark;

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
	public LabConType getConsumablesType() {
		return consumablesType;
	}

	public void setConsumablesType(LabConType consumablesType) {
		this.consumablesType = consumablesType;
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

	@Column(name="safeDate")
	public int getSafeDate() {
		return safeDate;
	}

	public void setSafeDate(int safeDate) {
		this.safeDate = safeDate;
	}

	@Column(name="safeAmount")
	public Double getSafeAmount() {
		return safeAmount;
	}

	public void setSafeAmount(Double safeAmount) {
		this.safeAmount = safeAmount;
	}

	public Double getAmount() {
		if(amount==null)amount=0.0;
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

	@Column(name="dangerSize")
	public String getDangerSize() {
		return dangerSize;
	}

	public void setDangerSize(String dangerSize) {
		this.dangerSize = dangerSize;
	}

    @Column(name="saveOrg")
	public String getSaveOrg() {
		return saveOrg;
	}

	public void setSaveOrg(String saveOrg) {
		this.saveOrg = saveOrg;
	}

	@Column(name="saveUser")
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

	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "耗材管理";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "耗材清单";
	}

}
