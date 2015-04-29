package cn.labsoft.labos.source.consumables.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
@Table(name = "lab_con_check_detail")
public class LabConCheckDetail extends AbstractBasePo {

	private LabConCheckMain labConCheckMain;
	private LabConsumables labConsumables;
	private Double amount;
	private Double lastAmount;
	private Double thisInAmount;
	private Double thisOutAmount;
	private String remark;

	@ManyToOne
	@JoinColumn(name="main_id")
	public LabConCheckMain getLabConCheckMain() {
		return labConCheckMain;
	}

	public void setLabConCheckMain(LabConCheckMain labConCheckMain) {
		this.labConCheckMain = labConCheckMain;
	}

	@ManyToOne
	@JoinColumn(name="consumables_id")
	public LabConsumables getLabConsumables() {
		return labConsumables;
	}

	public void setLabConsumables(LabConsumables labConsumables) {
		this.labConsumables = labConsumables;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getLastAmount() {
		return lastAmount;
	}

	public void setLastAmount(Double lastAmount) {
		this.lastAmount = lastAmount;
	}

	@Column(name="thisIn_amount")
	public Double getThisInAmount() {
		return thisInAmount;
	}

	public void setThisInAmount(Double thisInAmount) {
		this.thisInAmount = thisInAmount;
	}
	
	@Column(name="thisOut_amount")
	public Double getThisOutAmount() {
		return thisOutAmount;
	}

	public void setThisOutAmount(Double thisOutAmount) {
		this.thisOutAmount = thisOutAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
		return "耗材盘点";
	}

}