package cn.labsoft.labos.source.reagent.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 
 * <strong>Title : LabReaPurDetail </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Feb 22, 2014 2:33:14 PM  </strong>. <br>
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
@Table(name="lab_rea_pur_detail")
public class LabReaPurDetail extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private LabReaPurMain main; //采购单号
	private LabReagent reagent; //试剂
	private Double num; //采购数量
	private String remark; //备注
	
	@ManyToOne
	@JoinColumn(name="mainid")
	public LabReaPurMain getMain() {
		return main;
	}

	public void setMain(LabReaPurMain main) {
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
		return "试剂采购";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "试剂采购详情";
	}

}
