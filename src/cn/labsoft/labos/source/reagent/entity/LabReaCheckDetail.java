package cn.labsoft.labos.source.reagent.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_rea_check_detail")
public class LabReaCheckDetail extends AbstractBasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LabReaCheckMain labReaCheckMain;
	private LabReagent labReagent;
	private Double amount;
	private Double lastAmount;
	private Double thisInAmount;
	private Double thisOutAmount;
	private String remark;

	@ManyToOne
	@JoinColumn(name="main_id")
	public LabReaCheckMain getLabReaCheckMain() {
		return labReaCheckMain;
	}

	public void setLabReaCheckMain(LabReaCheckMain labReaCheckMain) {
		this.labReaCheckMain = labReaCheckMain;
	}

	@ManyToOne
	@JoinColumn(name="reagent_id")
	public LabReagent getLabReagent() {
		return labReagent;
	}

	public void setLabReagent(LabReagent labReagent) {
		this.labReagent = labReagent;
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
		return "试剂盘点";
	}

	@Override
	@Transient
	public String getTableName() {
		return "试剂盘点详情";
	}
}