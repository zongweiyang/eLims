package cn.labsoft.labos.source.consumables.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 *
 * <strong>Title : LabConPurMain </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Cconte on : Feb 22, 2014 2:33:39 PM  </strong>. <br>
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
@Table(name="lab_con_pur")
public class LabConPurMain extends AbstractBasePo {

	private LabWfProcessIns processIns;
	private String purchaser;// 采购人
	private String purTime;// 采购时间
	private String applicant;// 申请人
	private String receiptno;//单据号
	private String remark; //备注
	private String type; //0 采购单  1入库单
	private String inPerson;//入库人
	private String inTime;//入库时间


	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getPurTime() {
		return purTime;
	}

	public void setPurTime(String purTime) {
		this.purTime = purTime;
	}

	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@ManyToOne
	@JoinColumn(name="process_ins_id")
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}

	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	public String getInPerson() {
		return inPerson;
	}

	public void setInPerson(String inPerson) {
		this.inPerson = inPerson;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "耗材采购";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "耗材采购";
	}
}
