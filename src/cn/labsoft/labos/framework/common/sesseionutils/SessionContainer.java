package cn.labsoft.labos.framework.common.sesseionutils;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

import cn.labsoft.labos.base.function.vo.LabFunctionVo;

import com.opensymphony.xwork2.ActionContext;

/**
 * 
 * <strong>Title : SpringContext </strong>. <br>
 * <strong>Description : 封装session存放的基本业务信息的对象</strong> <br>
 * <strong>Create on : Nov 13, 2009 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author <br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 * <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 * <br>
 * <br>
 */
public class SessionContainer implements Serializable {

	private static final long serialVersionUID = -7146476712048036641L;
	public static Map<String, Object[]> map = null;
	public static final String Session_Container = "SessionContainer";
	public static final String THEME_TYPE = "themeType";
	static {
		if (null == map)
			map = new LinkedHashMap<String, Object[]>();
	}

	public static void setMap(String key, Object value[]) {
		map.put(key, value);
	}

	public static void reSetMap(String key, Object value[]) {
		Object[] valueTemp = map.get(key);
		if (null != valueTemp) {
			map.remove(key);
			setMap(key, value);
		} else
			setMap(key, value);
	}

	public static void reMoveMap(String key, Object value[]) {
		Object[] valueTemp = map.get(key);
		if (null != valueTemp) {
			map.remove(key);
		}
	}

	private String sessionId;// 当前session
	private String userId; // 用户Id
	private String loginName; // 登录名
	private String userName; // 用户名
	private String themeType; // 主题类型
	private String menuType; // 菜单类型
	private String styleName;
	private String ip = ""; // 登陆ip地址
	private String password = "";// 登陆密码
	private String logoUrl; // 当前用户logo
	private int maxUserCount; // 最大登陆用户数量

	private String orgId; // 当前组织
	private String orgName; // 当前组织Id

	private String roleId; // 角色Ids多个用英文逗号隔开
	private String roleName;// 角色names多个用英文逗号隔开
	private String[] roleIds;
	private List<LabFunctionVo> funList; // 目录功能列表
	private List<LabFunctionVo> wfFunList;// 连接功能列表
	private String tenantId;
	private String multiTenantId;
	private String funId; // 当前功能Id
	private String funCode; // 当前功能编码
	private List<String[]> parametersList;
	private String lastUrl; // 最后一个有效URL
	private String createTime;// 在线最后一次SessionContainer更新时间
	private String intervalTimeStr;// 查看在线人数刷新间隔毫秒数
	private String type; // 用户类型，前台FRONT 后台BACK
	private String orgUnit; // 系统最高级单位

	private String orgTenantId;// 当前组织级数据权限（用于存数据）

	private String funDataStr; // 当前功能的数据权限（用户取数据时限制）
	private Properties userProperties;// 个性参数

	public String getOrgUnit() {
		return orgUnit;
	}

	public void setOrgUnit(String orgUnit) {
		this.orgUnit = orgUnit;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public static SessionContainer getSessionContainer() {
		SessionContainer sessionContainer = null;
		try {
			if (null != ActionContext.getContext()) {
				if (null != ActionContext.getContext().getSession()
						.get(SessionContainer.Session_Container))
					sessionContainer = (SessionContainer) ActionContext
							.getContext().getSession()
							.get(SessionContainer.Session_Container);
			}
			if (null == sessionContainer
					&& null != ServletActionContext.getRequest().getSession()
							.getAttribute(SessionContainer.Session_Container)) {
				sessionContainer = (SessionContainer) ServletActionContext
						.getRequest().getSession()
						.getAttribute(SessionContainer.Session_Container);
			}
			if (sessionContainer == null)
				sessionContainer = new SessionContainer();
		} catch (Exception e) {
			sessionContainer = new SessionContainer();
		}
		return sessionContainer;
	}

	public static void setSessionContainer(SessionContainer sessionContainer) {
		ActionContext.getContext().getSession()
				.put(SessionContainer.Session_Container, sessionContainer);
	}

	public String getLastUrl() {
		return lastUrl;
	}

	public void setLastUrl(String lastUrl) {
		this.lastUrl = lastUrl;
	}

	public String getThemeType() {
		return themeType;
	}

	public void setThemeType(String themeType) {
		this.themeType = themeType;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public int getMaxUserCount() {
		return maxUserCount;
	}

	public void setMaxUserCount(int maxUserCount) {
		this.maxUserCount = maxUserCount;
	}

	public List<LabFunctionVo> getFunList() {
		return funList;
	}

	public void setFunList(List<LabFunctionVo> funList) {
		this.funList = funList;
	}

	public List<LabFunctionVo> getWfFunList() {
		return wfFunList;
	}

	public void setWfFunList(List<LabFunctionVo> wfFunList) {
		this.wfFunList = wfFunList;
	}

	public List<String[]> getParametersList() {
		return parametersList;
	}

	public void setParametersList(List<String[]> parametersList) {
		this.parametersList = parametersList;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
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

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getIntervalTimeStr() {
		return intervalTimeStr;
	}

	public void setIntervalTimeStr(String intervalTimeStr) {
		this.intervalTimeStr = intervalTimeStr;
	}

	public String getFunCode() {
		return funCode;
	}

	public void setFunCode(String funCode) {
		this.funCode = funCode;
	}

	public String getOrgTenantId() {
		return orgTenantId;
	}

	public void setOrgTenantId(String orgTenantId) {
		this.orgTenantId = orgTenantId;
	}

	public String getFunDataStr() {
		return funDataStr;
	}

	public void setFunDataStr(String funDataStr) {
		this.funDataStr = funDataStr;
	}

	public static String getUserProperties(String key) {
		SessionContainer sc = getSessionContainer();
		if (null == sc)
			return null;
		Properties properties = sc.getUserProperties();
		if (null!=properties&&null != properties.get(key))
			return String.valueOf(properties.get(key));
		else
			return null;
	}

	public Properties getUserProperties() {
		return userProperties;
	}

	public void setUserProperties(Properties userProperties) {
		this.userProperties = userProperties;
	}

	public String getMultiTenantId() {
		return multiTenantId;
	}

	public void setMultiTenantId(String multiTenantId) {
		this.multiTenantId = multiTenantId;
	}

}
