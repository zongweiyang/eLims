package cn.labsoft.labos.base.configs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 系统参数配置持久化对象
 * @author Quinn
 */
@SuppressWarnings("serial")
@Entity
@Table(name="lab_sys_config")
public class LabConfig extends AbstractBasePo {

	private String name;
	private String code;
	private String initValue;//初始值
	private String value;
	private String isUsed;
	private String desc;
	private String remark;
	private String category;//参数类别
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
	public String getValue() {
		return this.value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="[desc]")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "参数管理";
	}
	public String getInitValue() {
		return initValue;
	}
	public void setInitValue(String initValue) {
		this.initValue = initValue;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}