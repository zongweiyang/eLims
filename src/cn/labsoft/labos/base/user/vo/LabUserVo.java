package cn.labsoft.labos.base.user.vo;

import java.util.List;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * .
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class LabUserVo extends BaseVo {

	// Fields
	@ExcelAnnotation(exportName = "用户Id")
	private String id; //主键
	@ExcelAnnotation(exportName = "姓名")
	private String name; //用户名称
	@ExcelAnnotation(exportName = "密码")
	private String pwd; //用户密码
	@ExcelAnnotation(exportName = "确认密码")
	private String rePwd; //确认密码
	@ExcelAnnotation(exportName = "登陆")
	private String loginName; //登陆名称
	@ExcelAnnotation(exportName = "性别")
	private String sex; //性别
	@ExcelAnnotation(exportName = "手机")
	private String mobile; //手机
	@ExcelAnnotation(exportName = "email")
	private String email; //邮箱
	@ExcelAnnotation(exportName = "民族")
	private String nation; //民族
	@ExcelAnnotation(exportName = "电话")
	private String telephone; //电话
	@ExcelAnnotation(exportName = "出生日期")
	private String birthday; //出生日期
	@ExcelAnnotation(exportName = "住址")
	private String address; //住址
	@ExcelAnnotation(exportName = "logo")
	private String logo; //用户头像

	@ExcelAnnotation(exportName = "编号")
	private String no; //编号
	@ExcelAnnotation(exportName = "到岗日期")
	private String workDate; //到岗日期
	@ExcelAnnotation(exportName = "学历")
	private String education; //学历
	@ExcelAnnotation(exportName = "专业")
	private String profession; //专业
	@ExcelAnnotation(exportName = "职务")
	private String duty; //职务
	@ExcelAnnotation(exportName = "职称")
	private String techTitle; //职称
	@ExcelAnnotation(exportName = "序号")
	private Integer sort; //序号
	private String token; //绑定U盾
	@ExcelAnnotation(exportName = "IP")
	private String ip; //绑定IP
	@ExcelAnnotation(exportName = "是否使用")
	private String isUsed; //是否使用
	private String sessionId; //当前登陆session
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "婚姻状况")
	private String maritalStatus;
	@ExcelAnnotation(exportName = "个人习惯")
	private String personalHabit;

	private String orgId; //多个用逗号隔开
	@ExcelAnnotation(exportName = "主显示部门")
	private String mainOrgName; //多个用逗号隔开
	@ExcelAnnotation(exportName = "部门")
	private String orgName; //多个用逗号隔开
	private List<LabOrgVo> orgVoList;
	private String roleId; //多个用逗号隔开
	@ExcelAnnotation(exportName = "主显示角色")
	private String mainRoleName;//多个用逗号隔开
	@ExcelAnnotation(exportName = "角色")
	private String roleName;//多个用逗号隔开
	@ExcelAnnotation(exportName = "菜单风格")
	private String menuType;////菜单风格 0 级联 1图标  
	private List<LabRoleVo> roleVoList;
	private String funId; //多个用逗号隔开
	private String funName; //多个用逗号隔开
	private List<LabFunctionVo> funVoList;
	private String currentDate;
	private int funCount; //用于功能数量
	private String styleName;
	@ExcelAnnotation(exportName = "用户角色集合")
	private List<LabUserRoleVo> userRoleList;
	@ExcelAnnotation(exportName = "用户功能集合")
	private List<LabUserFunVo> userFunList;
	@ExcelAnnotation(exportName = "用户部门集合")
	private List<LabUserOrgVo> userOrgList;
	@ExcelAnnotation(exportName = "用户类型")
	private String userType; //用户类型属于前台还是后台

	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getRePwd() {
		return rePwd;
	}

	public void setRePwd(String rePwd) {
		this.rePwd = rePwd;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getTechTitle() {
		return techTitle;
	}

	public void setTechTitle(String techTitle) {
		this.techTitle = techTitle;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
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

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getFunName() {
		return funName;
	}

	public void setFunName(String funName) {
		this.funName = funName;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public int getFunCount() {
		return funCount;
	}

	public void setFunCount(int funCount) {
		this.funCount = funCount;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<LabFunctionVo> getFunVoList() {
		return funVoList;
	}

	public void setFunVoList(List<LabFunctionVo> funVoList) {
		this.funVoList = funVoList;
	}

	public List<LabOrgVo> getOrgVoList() {
		return orgVoList;
	}

	public void setOrgVoList(List<LabOrgVo> orgVoList) {
		this.orgVoList = orgVoList;
	}

	public List<LabRoleVo> getRoleVoList() {
		return roleVoList;
	}

	public void setRoleVoList(List<LabRoleVo> roleVoList) {
		this.roleVoList = roleVoList;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public List<LabUserRoleVo> getUserRoleList() {
		return userRoleList;
	}

	public void setUserRoleList(List<LabUserRoleVo> userRoleList) {
		this.userRoleList = userRoleList;
	}

	public List<LabUserFunVo> getUserFunList() {
		return userFunList;
	}

	public void setUserFunList(List<LabUserFunVo> userFunList) {
		this.userFunList = userFunList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<LabUserOrgVo> getUserOrgList() {
		return userOrgList;
	}

	public void setUserOrgList(List<LabUserOrgVo> userOrgList) {
		this.userOrgList = userOrgList;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getMainOrgName() {
		return mainOrgName;
	}

	public void setMainOrgName(String mainOrgName) {
		this.mainOrgName = mainOrgName;
	}

	public String getMainRoleName() {
		return mainRoleName;
	}

	public void setMainRoleName(String mainRoleName) {
		this.mainRoleName = mainRoleName;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPersonalHabit() {
		return personalHabit;
	}

	public void setPersonalHabit(String personalHabit) {
		this.personalHabit = personalHabit;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

}