package cn.labsoft.labos.business.sam.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
public class LabSamType extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;
	private String code;
    private LabSamType labSamType;
    private String remark;
    private String isDefault;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}


	@ManyToOne
	@JoinColumn(name="pid")
	public LabSamType getLabSamType() {
		return labSamType;
	}
	public void setLabSamType(LabSamType labSamType) {
		this.labSamType = labSamType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	@Transient
	@Override
	public String getModelName() {
		return "业务管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "样品类型";
	}

}
