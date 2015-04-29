package cn.labsoft.labos.source.reagent.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * @Title 试剂出库列表
 * @author Bill
 * 
 */
@Entity
@Table(name="lab_rea_out")
public class LabReaOutMain extends AbstractBasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String receiptno; // 单据号
	private String outstorer; // 出库人
	private Date outstoreDate;// 出库时间


	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

	public String getOutstorer() {
		return outstorer;
	}

	public void setOutstorer(String outstorer) {
		this.outstorer = outstorer;
	}

	@Column(name="outstoreDate")
	public Date getOutstoreDate() {
		return outstoreDate;
	}

	public void setOutstoreDate(Date outstoreDate) {
		this.outstoreDate = outstoreDate;
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
		return "试剂出库";
	}

}
