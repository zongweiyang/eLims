package cn.labsoft.labos.source.consumables.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * title 耗材出库详情
 * 
 * @author Bill
 * 
 */
@Entity
@Table(name="lab_con_out_detail")
public class LabConOutDetail extends AbstractBasePo {

	private LabConOutMain main; // 出库单号
	private LabConsumables consumables;
	private Double amount;
	private Date outDate;
	private String remark;

	@ManyToOne
	@JoinColumn(name="main_id")
	public LabConOutMain getMain() {
		return main;
	}

	public void setMain(LabConOutMain main) {
		this.main = main;
	}

	@ManyToOne
	@JoinColumn(name="consumables_id")
	public LabConsumables getConsumables() {
		return consumables;
	}

	public void setConsumables(LabConsumables consumables) {
		this.consumables = consumables;
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
		return "耗材管理";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "耗材出库";
	}
}
