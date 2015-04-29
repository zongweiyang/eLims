package cn.labsoft.labos.source.reagent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * title 试剂出库详情
 * 
 * @author Bill
 * 
 */
@Entity
@Table(name="lab_rea_out_detail")
public class LabReaOutDetail extends AbstractBasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LabReaOutMain main; // 出库单号
	private LabReagent reagent;
	private Double amount;
	private Date outDate;
	private String remark;

	@ManyToOne
	@JoinColumn(name="main_id")
	public LabReaOutMain getMain(){
		return main;
	}

	public void setMain(LabReaOutMain main) {
		this.main = main;
	}

	@ManyToOne
	@JoinColumn(name="reagent_id")
	public LabReagent getReagent() {
		return reagent;
	}

	public void setReagent(LabReagent reagent) {
		this.reagent = reagent;
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

	@Column(name="outDate")
	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "试剂管理";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "试剂出库详情";
	}

}
