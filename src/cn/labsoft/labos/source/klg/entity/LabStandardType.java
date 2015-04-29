package cn.labsoft.labos.source.klg.entity;

import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_klg_standard_type")
public class LabStandardType extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
	private String demo;
    private LabStandardType standardType;
    
    

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDemo() {
		return this.demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	
	@ManyToOne
	@JoinColumn(name="parentid")
	public LabStandardType getStandardType() {
		return standardType;
	}
	public void setStandardType(LabStandardType standardType) {
		this.standardType = standardType;
	}
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "标准管理";
	}
	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "标准类型";
	}
}
