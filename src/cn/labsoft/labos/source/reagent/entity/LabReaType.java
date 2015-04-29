package cn.labsoft.labos.source.reagent.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * @title 试剂类型表
 * @author Bill
 */
@Entity
@Table(name="lab_rea_type")
public class LabReaType extends AbstractBasePo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LabReaType type;
	private String name;

	@ManyToOne
	@JoinColumn(name="type_id")
	public LabReaType getType() {
		return type;
	}

	public void setType(LabReaType type) {
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
		return "试剂管理";
	}

	@Override
	@Transient
	public String getTableName() {
		// TODO Auto-generated method stub
		return "试剂类型";
	}
}
