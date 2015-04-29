package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 用户功能关系信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_sys_user_fun")
public class LabUserFun  extends AbstractBasePo{

	
	private static final long serialVersionUID = 1L;
	
	private LabUser user;
	private LabFunction function;
	private LabRole role;
	private LabOrg org;
	
	private String isAdd;
	private String isUpdate;
	private String isDelete;
	private String isShow;
	private String tenantStr;//数据权限
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)	
	public LabUser getUser() {
		return user;
	}
	public void setUser(LabUser user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="fun_id", nullable=false)
	public LabFunction getFunction() {
		return function;
	}
	public void setFunction(LabFunction function) {
		this.function = function;
	}
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	public LabRole getRole() {
		return role;
	}
	public void setRole(LabRole role) {
		this.role = role;
	}
	@ManyToOne
	@JoinColumn(name="org_id", nullable=false)
	public LabOrg getOrg() {
		return org;
	}
	public void setOrg(LabOrg org) {
		this.org = org;
	}
	
	public String getIsAdd() {
		return isAdd;
	}
	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	public String getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}
	public String getIsDelete() {
		return isDelete;
	}
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	public String getIsShow() {
		return isShow;
	}
	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
	
	public String getTenantStr() {
		return tenantStr;
	}
	public void setTenantStr(String tenantStr) {
		this.tenantStr = tenantStr;
	}
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "用户功能关系";
	}

}
