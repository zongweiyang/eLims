package cn.labsoft.labos.source.supplier.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSupEvaluateVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "供应商id")
	private String labSupplierId;
	@ExcelAnnotation(exportName = "供应商名称")
	private String labSupplierName;
	@ExcelAnnotation(exportName = "评价时间")
	private String evaluateDate;
	@ExcelAnnotation(exportName = "评价人")
	private String person;
	@ExcelAnnotation(exportName = "价额")
	private String price;
	@ExcelAnnotation(exportName = "交货期")
	private String delivery;
	@ExcelAnnotation(exportName = "产品质量")
	private String quality;
	@ExcelAnnotation(exportName = "服务质量")
	private String server;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getLabSupplierId() {
		return labSupplierId;
	}

	public void setLabSupplierId(String labSupplierId) {
		this.labSupplierId = labSupplierId;
	}

	public String getLabSupplierName() {
		return labSupplierName;
	}

	public void setLabSupplierName(String labSupplierName) {
		this.labSupplierName = labSupplierName;
	}

}
