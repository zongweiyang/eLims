package cn.labsoft.labos.source.reference.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_ref_out_detail")
public class LabRefOutDetail  extends AbstractBasePo{

	private LabRefOutMain main;  //出库单号
	private LabReference reference;
	private Double amount;
	private Date outDate;
	private String remark;
	
	@ManyToOne
	@JoinColumn(name="main_id", nullable=false)
	public LabRefOutMain getMain() {
		return main;
	}

	public void setMain(LabRefOutMain main) {
		this.main = main;
	}
	@ManyToOne
	@JoinColumn(name="reference_id", nullable=false)
	public LabReference getReference() {
		return reference;
	}

	public void setReference(LabReference reference) {
		this.reference = reference;
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

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
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
		return "标准品出库";
	}
}
