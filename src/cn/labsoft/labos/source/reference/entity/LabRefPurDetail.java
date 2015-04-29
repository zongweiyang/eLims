package cn.labsoft.labos.source.reference.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_ref_pur_detail")
public class LabRefPurDetail extends AbstractBasePo {

	private LabRefPurMain main; // 采购单号
	private LabReference reference; // 标准品
	private Double num; // 采购数量
	private String remark; // 备注


	@ManyToOne
	@JoinColumn(name="mainid", nullable=false)
	public LabRefPurMain getMain() {
		return main;
	}

	public void setMain(LabRefPurMain main) {
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

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
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
		return "标准品采购";
	}

}
