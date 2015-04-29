package cn.labsoft.labos.common.number.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabNumberRecord extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//编号名称
	private String typeCode;
	private String clazz;
	private String busId;
	private String status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name="typeCode")
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	@Column(name="busId")
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "编号定义";
	}
}
