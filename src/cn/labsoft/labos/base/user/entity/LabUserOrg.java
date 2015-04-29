package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
/**
 * 用户组织关系信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_sys_user_org")
public class LabUserOrg  extends AbstractBasePo{

	
	private static final long serialVersionUID = -4263397591346547667L;
	private LabUser user;
	private LabOrg org;
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	public LabUser getUser() {
		return user;
	}
	public void setUser(LabUser user) {
		this.user = user;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="org_id",nullable = false)
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
		return "用户组织关系";
	}

}
