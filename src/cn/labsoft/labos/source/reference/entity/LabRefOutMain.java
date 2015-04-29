package cn.labsoft.labos.source.reference.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_ref_out")
public class LabRefOutMain extends AbstractBasePo{

	private String receiptno; //单据号
	private String outstorer;  //出库人
	
	private Date outstoreDate;//出库时间
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
	public Date getOutstoreDate() {
		return outstoreDate;
	}
	public void setOutstoreDate(Date outstoreDate) {
		this.outstoreDate = outstoreDate;
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
