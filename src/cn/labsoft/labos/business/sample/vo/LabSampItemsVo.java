package cn.labsoft.labos.business.sample.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSampItemsVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	private String busId;          //业务id
	private String labSamId;
	private String labSamName;
	private String labSamTypeId;
	private String labSamTypeName;
	private String samCode;
	@ExcelAnnotation(exportName = "项目Id")
	private String itemId;
	@ExcelAnnotation(exportName = "项目名称")
	private String itemName;
	@ExcelAnnotation(exportName = "费用")
	private Double price;
	@ExcelAnnotation(exportName = "检测标准")
	private String standardId;
	@ExcelAnnotation(exportName = "检测标准")
	private String standardName;
	@ExcelAnnotation(exportName = "检测方法")
	private String methodId;
	@ExcelAnnotation(exportName = "检测方法")
	private String methodName;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getStandardId() {
		return this.standardId;
	}

	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}

	public String getStandardName() {
		return this.standardName;
	}

	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}

	public String getMethodId() {
		return this.methodId;
	}

	public void setMethodId(String methodId) {
		this.methodId = methodId;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getLabSamId() {
		return labSamId;
	}
	public void setLabSamId(String labSamId) {
		this.labSamId = labSamId;
	}
	
	public String getLabSamName() {
		return labSamName;
	}
	public void setLabSamName(String labSamName) {
		this.labSamName = labSamName;
	}
	public String getLabSamTypeId() {
		return labSamTypeId;
	}
	public void setLabSamTypeId(String labSamTypeId) {
		this.labSamTypeId = labSamTypeId;
	}
	public String getLabSamTypeName() {
		return labSamTypeName;
	}
	public void setLabSamTypeName(String labSamTypeName) {
		this.labSamTypeName = labSamTypeName;
	}
	public String getSamCode() {
		return samCode;
	}
	public void setSamCode(String samCode) {
		this.samCode = samCode;
	}
	
}
