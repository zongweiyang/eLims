package cn.labsoft.labos.source.ambient.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabAmbientVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "参数名称")
	private String name;
	@ExcelAnnotation(exportName = "上限值")
	private double upValue;
	@ExcelAnnotation(exportName = "下限值")
	private double downValue;
	@ExcelAnnotation(exportName = "单位")
	private String unit;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUpValue() {
		return upValue;
	}
	public void setUpValue(double upValue) {
		this.upValue = upValue;
	}
	public double getDownValue() {
		return downValue;
	}
	public void setDownValue(double downValue) {
		this.downValue = downValue;
	}
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

}
