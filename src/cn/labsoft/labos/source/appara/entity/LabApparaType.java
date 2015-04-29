package cn.labsoft.labos.source.appara.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * @title 仪器类型表
 */
@Entity
@Table(name="lab_appara_type")
public class LabApparaType extends AbstractBasePo {

	
	private LabApparaType type;
	private String appName;

	@ManyToOne
	@JoinColumn(name="type_id")
	public LabApparaType getType() {
		return type;
	}

	public void setType(LabApparaType type) {
		this.type = type;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}
	@Transient
	@Override
	public String getModelName() {
		return "资源管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "仪器类型信息";
	}

}
