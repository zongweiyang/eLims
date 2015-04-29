package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 用户角色关系信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_sys_user_role")
public class LabUserRole extends AbstractBasePo {

	private static final long serialVersionUID = -7938908125919110768L;

	private LabOrg org;
	private LabRole role;
	private LabUser user;
	@ManyToOne
	@JoinColumn(name="role_id", nullable=false)
	public LabRole getRole() {
		return role;
	}

	public void setRole(LabRole role) {
		this.role = role;
	}
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	public LabUser getUser() {
		return user;
	}

	public void setUser(LabUser user) {
		this.user = user;
	}
	@ManyToOne
	@JoinColumn(name="org_id", nullable=false)
	public LabOrg getOrg() {
		return org;
	}

	public void setOrg(LabOrg org) {
		this.org = org;
	}

	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "用户角色关系";
	}
}