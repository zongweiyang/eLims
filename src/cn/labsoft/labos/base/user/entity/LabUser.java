package cn.labsoft.labos.base.user.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.base.desktop.entity.LabDesktopstyle;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 用户信息持久化对象
 * @author Quinn
 */
@Entity
@Table(name="lab_sys_user")
public class LabUser extends AbstractBasePo {

	private static final long serialVersionUID = -4816187960545065633L;
	// Fields
	private String name; //用户名称
	private String pwd; //用户密码
	private String loginName; //登陆名称
	private String sex; //性别
	private String mobile; //手机
	private String email; //邮箱
	private String telephone; //电话
	private String birthday; //出生日期
	private String address; //住址
	private String nation; //民族
	private String maritalStatus;//婚姻状况
	private String personalHabit;//个人习惯

	private String no; //编号
	private String workDate; //到岗日期
	private String education; //学历
	private String profession; //专业
	private String duty; //职务
	private String techTitle; //职称
	
	private String token; //绑定U盾
	private String ip; //绑定IP
	private String isUsed; //是否使用
	private String sessionId; //当前登陆session
	private String remark;
	private String menuType;////菜单风格 0 级联 1图标  
	private LabDesktopstyle destStyle;
	private String userType; //用户类型属于前台还是后台

	
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getName() {
		return name;
	}

	public void setName(String userName) {
		this.name = userName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	@ManyToOne
	@JoinColumn(name="STYLEID")
	public LabDesktopstyle getDestStyle() {
		return destStyle;
	}
	public void setDestStyle(LabDesktopstyle destStyle) {
		this.destStyle = destStyle;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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
	@Transient
	@Override
	public String getModelName() {
		return "系统管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "用户信息";
	}
}