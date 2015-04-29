package cn.labsoft.labos.base.role.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_sys_role_fun")
public class LabRoleFun extends AbstractBasePo {

	private LabRole role;
	private LabFunction function;


	@ManyToOne
	@JoinColumn(name="ROLE_ID")
	public LabRole getRole() {
		return role;
	}

	public void setRole(LabRole role) {
		this.role = role;
	}
	
	@ManyToOne
	@JoinColumn(name="FUN_ID")
	public LabFunction getFunction() {
		return function;
	}

	public void setFunction(LabFunction function) {
		this.function = function;
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "角色功能关系";
	}
}