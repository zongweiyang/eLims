package cn.labsoft.labos.source.consumables.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;

/**
 * @title 耗材类型表
 * @author Bill
 */
@Entity
@Table(name="lab_con_type")
public class LabConType extends AbstractBasePo {

	private LabConType type;
	@Translator
	private String name;


	@ManyToOne
	@JoinColumn(name="type_id")
	public LabConType getType() {
		return type;
	}

	public void setType(LabConType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	@Override
	@Transient
	public String getModelName() {
		// TODO Auto-generated method stub
		return "耗材管理";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "耗材类型";
	}

}
