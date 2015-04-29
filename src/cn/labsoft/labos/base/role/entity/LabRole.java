package cn.labsoft.labos.base.role.entity;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;

@Entity
@Table(name="lab_sys_role")
public class LabRole extends AbstractBasePo {
	
	@Translator
	private String name;//角色名称
	private String isUsed;//是否使用
	@Translator
	private String remark;//备注
	
	private String show; //显示 0前台,1后台
	private String isDefault;
	private String portlet; 
	
	public String getPortlet() {
		return portlet;
	}

	public void setPortlet(String portlet) {
		this.portlet = portlet;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "角色信息";
	}



	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	
	@Column(name="role_show")
	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}
	
	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
}