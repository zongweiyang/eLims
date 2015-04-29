package cn.labsoft.labos.source.ambient.entity;

import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabAmbient extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//参数名称
	private double upValue;//上限值
	private double downValue;//下限值
	private String unit;//单位

	
	@Transient
	@Override
	public String getTableName() {
		return "环境管理";
	}
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
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
	public void setDownValue(Integer downValue) {
		this.downValue = downValue;
	}
	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	

}
