package cn.labsoft.labos.base.user.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@SuppressWarnings("serial")
public class LabUserOrgVo extends BaseVo{

	private String id;
	@ExcelAnnotation(exportName = "用户Id")
	private String userId;
	@ExcelAnnotation(exportName = "用户名称")
	private String userName;
	@ExcelAnnotation(exportName = "部门Id")
	private String orgId;
	@ExcelAnnotation(exportName = "部门名称")
	private String orgName;
	@ExcelAnnotation(exportName = "角色Id")
	private String roleIds;  //多个逗号隔开
	@ExcelAnnotation(exportName = "角色名称")
	private String roleNames;//多个逗号隔开
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	
}
