package cn.labsoft.labos.source.supplier.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_sup_evaluate")
public class LabSupEvaluate extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private LabSupplier labSupplier;
	private String evaluateDate;// 评价时间
	private String person;// 评价人
	private String price;// 价额
	private String delivery;// 交货期
	private String quality;// 产品质量
	private String server;// 服务质量

	public String getEvaluateDate() {
		return this.evaluateDate;
	}

	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	public String getPerson() {
		return this.person;
	}

	public void setPerson(String person) {
		this.person = person;
	}
	@ManyToOne
	@JoinColumn(name="supplier_id", nullable=false)
	public LabSupplier getLabSupplier() {
		return labSupplier;
	}

	public void setLabSupplier(LabSupplier labSupplier) {
		this.labSupplier = labSupplier;
	}

	public String getPrice() {
		return this.price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDelivery() {
		return this.delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getQuality() {
		return this.quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public String getServer() {
		return this.server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "供应商管理";
	}

	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "供应商评价";
	}
}
