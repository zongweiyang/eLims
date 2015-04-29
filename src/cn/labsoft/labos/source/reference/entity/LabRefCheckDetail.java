package cn.labsoft.labos.source.reference.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;


@Entity
@Table(name="lab_ref_check_detail")
public class LabRefCheckDetail extends AbstractBasePo {

	private LabRefCheckMain labRefCheckMain;
	private LabReference labReference;
	private Double amount;
	private Double lastAmount;
	private Double thisInAmount;
	private Double thisOutAmount;
	private String remark;
	
	@ManyToOne
	@JoinColumn(name="main_id", nullable=false)
	public LabRefCheckMain getLabRefCheckMain() {
		return labRefCheckMain;
	}
	public void setLabRefCheckMain(LabRefCheckMain labRefCheckMain) {
		this.labRefCheckMain = labRefCheckMain;
	}
	@ManyToOne
	@JoinColumn(name="reference_id", nullable=false)
	public LabReference getLabReference() {
		return labReference;
	}
	public void setLabReference(LabReference labReference) {
		this.labReference = labReference;
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
	public Double getThisInAmount() {
		return thisInAmount;
	}
	public void setThisInAmount(Double thisInAmount) {
		this.thisInAmount = thisInAmount;
	}
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
		return "标准品盘点";
	}

    
}