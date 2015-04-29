package cn.labsoft.labos.base.user.vo;

import java.util.List;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@SuppressWarnings("serial")
public class LabUserFunVo  extends BaseVo{

	private String id;
	@ExcelAnnotation(exportName = "用户Id")
	private String userId;
	@ExcelAnnotation(exportName = "用户名称")
	private String userName;
	@ExcelAnnotation(exportName = "功能Id")
	private String functionId;
	@ExcelAnnotation(exportName = "功能名称")
	private String functionName;
	@ExcelAnnotation(exportName = "角色Id")
	private String roleId;
	@ExcelAnnotation(exportName = "角色名称")
	private String roleName;
	@ExcelAnnotation(exportName = "部门Id")
	private String orgId;
	@ExcelAnnotation(exportName = "部门名称")
	private String orgName;
	@ExcelAnnotation(exportName = "功能权限-增")
	private String isAdd;
	@ExcelAnnotation(exportName = "功能权限-改")
	private String isUpdate;
	@ExcelAnnotation(exportName = "功能权限-删")
	private String isDelete;
	@ExcelAnnotation(exportName = "功能权限-查")
	private String isShow;
	@ExcelAnnotation(exportName = "数据权限")
	private String tenantStr;//数据权限
	private String dataStr;  //功能设定权限
	private String funType;  //功能类型
	private List<LabUserFunVo> userFunList;
	@ExcelAnnotation(exportName = "功能的父id")
	private String parentFunId;
	
	private List<LabOrgVo> orgDataList;
	public List<LabOrgVo> getOrgDataList() {
		return orgDataList;
	}

	public void setOrgDataList(List<LabOrgVo> orgDataList) {
		this.orgDataList = orgDataList;
	}

	public String getParentFunId() {
		return parentFunId;
	}

	public void setParentFunId(String parentFunId) {
		this.parentFunId = parentFunId;
	}

	public String getId() {
		return id;
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

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setId(String id) {
		this.id = id;
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

	public List<LabUserFunVo> getUserFunList() {
		return userFunList;
	}

	public void setUserFunList(List<LabUserFunVo> userFunList) {
		this.userFunList = userFunList;
	}

	public String getTenantStr() {
		return tenantStr;
	}

	public void setTenantStr(String tenantStr) {
		this.tenantStr = tenantStr;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	public String getFunType() {
		return funType;
	}

	public void setFunType(String funType) {
		this.funType = funType;
	}
	

}
