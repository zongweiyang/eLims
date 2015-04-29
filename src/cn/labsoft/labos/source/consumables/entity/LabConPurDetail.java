package cn.labsoft.labos.source.consumables.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 
 * <strong>Title : LabConPurDetail </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Cconte on : Feb 22, 2014 2:33:14 PM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Entity
@Table(name="lab_con_pur_detail")
public class LabConPurDetail extends AbstractBasePo {

	private LabConPurMain main; //采购单号
	private LabConsumables consumables; //耗材
	private Double num; //采购数量
	private String remark; //备注

	@ManyToOne
	@JoinColumn(name="mainid")
	public LabConPurMain getMain() {
		return main;
	}


	public void setMain(LabConPurMain main) {
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
		return "耗材采购";
	}

}
