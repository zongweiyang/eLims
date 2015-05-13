package cn.labsoft.labos.coreextend.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.logs.service.ILabLogRecordService;
import cn.labsoft.labos.base.message.service.ILabMsgMainService;
import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.i18n.util.TranslateUtil;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CoreExtendAction extends BaseAction {
	private ILabOrgService labOrgService;
	private ILabUserService labUserService;
	private ILabRoleService labRoleService;
	private ILabMsgMainService labMsgMainService;
	private ILabFunctionService labFunctionService;
	private ILabLogRecordService labLogRecordService;
	private ILabConfigService labConfigService;
	private LabUserVo labUserVo;
	private LabMsgDetailVo labMsgDetailVo;
	private String checkcode;
	private String currentMainmenuId;
	private String tenantId;
	private List<LabFunctionVo> submenuList;
	private LabFunctionVo labFunctionVo;

	public LabFunctionVo getLabFunctionVo() {
		return labFunctionVo;
	}

	public void setLabFunctionVo(LabFunctionVo labFunctionVo) {
		this.labFunctionVo = labFunctionVo;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getCurrentMainmenuId() {
		return currentMainmenuId;
	}

	public void setCurrentMainmenuId(String currentMainmenuId) {
		this.currentMainmenuId = currentMainmenuId;
	}

	public List<LabFunctionVo> getSubmenuList() {
		return submenuList;
	}

	public void setSubmenuList(List<LabFunctionVo> submenuList) {
		this.submenuList = submenuList;
	}

	@SuppressWarnings("static-access")
	public String login() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession tempSession = request.getSession();
		tempSession.removeAttribute(SessionContainer.Session_Container);
		Locale locale = null;
		locale = request.getLocale();
		tempSession.setAttribute(Globals.LOCALE_KEY, locale);
		ActionContext.getContext().setLocale(locale);
		SessionContainer sc = getSessionContainer();
		if (null != sc) {
			tempSession.removeAttribute(SessionContainer.Session_Container);
		}else{
			sc = new SessionContainer();
		}
		 
		initProperty(sc);
		try{
			//session.put(SessionContainer.Session_Container, sc);
		}catch(Exception e){
			
		}
		return "sigleTenant";
	}
	private void initProperty(SessionContainer sessionContainer) throws GlobalException {
		//获取个性参数，配置
		List<LabConfigVo> labConfigVoList=labConfigService.getLabConfigByCategory("1");
		Properties properties=new Properties();
		if(null!=labConfigVoList&&labConfigVoList.size()>0){
			for(LabConfigVo LabConfigVo:labConfigVoList){
				properties.put(LabConfigVo.getCode(), LabConfigVo.getValue());
			}
			sessionContainer.setUserProperties(properties);
		}
		themeType = properties.getProperty(SessionContainer.THEME_TYPE);
		if(themeType==null){
			themeType = "themec";
			properties.setProperty(SessionContainer.THEME_TYPE, themeType);
		}
	}
	@SuppressWarnings( { "static-access", "unchecked" })
	public String loginSystem() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		SessionContainer sc = getSessionContainer();
		if (null != sc && null!=sc.getUserId()) {
			getNewMenu();
			return "loginSuccess";
		}
		if (labUserVo == null) {
			return "sigleTenant";
		}
		SessionContainer sessionContainer = getSessionContainer();
		if(null==sessionContainer) sessionContainer = new SessionContainer();
		initProperty(sessionContainer);
		
		labUserVo = labUserService.getLabUserByLogInNameAndPwd(labUserVo
				.getLoginName(), labUserVo.getPwd());
		if (labUserVo == null || StrUtils.isBlankOrNull(labUserVo.getId())) {
			this.addActionError(getText("user.login.unuser"));
			return "sigleTenant";
		} else if (null == labUserVo || null == labUserVo.getUserType()
				|| !Constants_Base.FRONT.equals(labUserVo.getUserType())) {
			this.addActionError(getText("user.login.unright"));
			return "sigleTenant";
		} else { // 验证成功
			LabOrgVo sysOrgVo = labOrgService.getLabOrgUnit();
			if (null != sysOrgVo && null != sysOrgVo.getLogo()) {
				sessionContainer.setLogoUrl(sysOrgVo.getLogo());
				sessionContainer.setOrgUnit(sysOrgVo.getName());
			}
			// 检验部门
			List<LabOrgVo> orgList = labOrgService.getLabOrgByUserId(labUserVo
					.getId());
			LabOrgVo labOrgVo = new LabOrgVo();
			if (orgList == null || orgList.size() == 0) {
				this.addActionError(getText("user.login.unright"));
				return "sigleTenant";
			} else if (orgList.size() == 1) {
				labOrgVo = orgList.get(0);
				sessionContainer.setOrgId(labOrgVo.getId());
				sessionContainer.setOrgName(labOrgVo.getName());
				sessionContainer.setOrgTenantId(labOrgVo.getTenantId());
			} else if (orgList.size() > 1) {
				labOrgVo = orgList.get(0);
			}
			// 检验角色
			String[] rolesIds = labRoleService.getLabRoleIdsArrayByUserId(
					labUserVo.getId(), labOrgVo.getId());
			String[] rolenames = labRoleService.getLabRoleNamesArrayByUserId(
					labUserVo.getId(), labOrgVo.getId());
			if (0 == rolesIds.length) {
				this.addActionError(getText("user.login.unright"));
				return "sigleTenant";
			} else {
				labUserVo.setRoleName(StrUtils.join(rolenames, ",")); // 设置第一个角色名为用户角色名e
				labUserVo.setRoleId(StrUtils.join(rolesIds, ","));
			}
			// 检测是否绑定ip和验证ip
			if (null != labUserVo.getIp() && !"".equals(labUserVo.getIp())) {
				String ip[] = labUserVo.getIp().split(",");
				for (int i = 0; i < ip.length; i++) {
					if (!ip[i].contains(request.getRemoteHost())) {
						this.addActionError(getText("user.out.login"));
						return "sigleTenant";
					}
				}
			}

			sessionContainer.setUserId(labUserVo.getId());
			sessionContainer.setLoginName(labUserVo.getLoginName());
			sessionContainer.setPassword(labUserVo.getPwd());
			sessionContainer.setUserName(labUserVo.getName());
			sessionContainer.setThemeType(getThemeType());
			sessionContainer.setStyleName(labUserVo.getStyleName());
			sessionContainer.setMenuType(String
					.valueOf(labUserVo.getMenuType()));
			sessionContainer.setFunId("0");
			if (labUserVo.getMenuType() != null
					&& !labUserVo.getMenuType().equals("a")) {
				sessionContainer.setThemeType(labUserVo.getMenuType());
			}

			sessionContainer.setRoleIds(rolesIds);
			sessionContainer.setRoleId(StrUtils.join(rolesIds, ","));
			sessionContainer.setRoleName(StrUtils.join(rolenames, ","));
			sessionContainer.setIp(ServletActionContext.getRequest()
					.getRemoteAddr());
			sessionContainer.setType(labUserVo.getUserType());
			labUserVo.setCurrentDate(DateUtils.getCurrDateStr() + getText("user.week")
					+ DateUtils.getDayOfWeek());

			session.put(SessionContainer.Session_Container, sessionContainer);

			List<LabFunctionVo> funList = labFunctionService
					.getMainmenuByUserIdAndMenuType(labUserVo.getId(), labOrgVo
							.getId(), "0", "",getThemeType());
			sessionContainer.setFunList(funList);// 获取目录集合
			labUserVo.setFunVoList(funList);
			labUserVo.setFunCount(funList.size());
			// 记录日志
			labLogRecordService.addLabLogrecord4Bus(sessionContainer
					.getUserName()
					+ " " + DateUtils.getCurrDateTimeStr() + getText("admin.login.system"), null,
					getText("admin.user.manage"), getText("admin.user.login"), getText("admin.add"));
			// 加巡检验证
			getResponse().setHeader("runStatus", "true");
			return "loginSuccess";
		}
	}

	// 多部门部门弹出部门选择框
	public String showLabOrg4Select() throws GlobalException {
		labUserVo = labUserService
				.getLabUser(getSessionContainer().getUserId());
		labUserVo.setCurrentDate(DateUtils.getCurrDateStr() + getText("user.week")
				+ DateUtils.getDayOfWeek());

		List<LabOrgVo> orgList = labOrgService
				.getLabOrgByUserId(getSessionContainer().getUserId());
		setAttribute("orgList", orgList);
		return SHOW;
	}

	// 用户选择部门登陆
	@SuppressWarnings( { "static-access", "unchecked" })
	public String loginSystem4Org() throws GlobalException {
		SessionContainer sessionContainer = getSessionContainer();
		if (sessionContainer == null) {
			this.addActionError(getText("user.login.unright"));
			return "sigleTenant";
		}
		if (labUserVo.getOrgId() != null && !labUserVo.getOrgId().equals("")) {
			LabOrgVo orgVo = labOrgService.getLabOrg(labUserVo.getOrgId());
			sessionContainer.setOrgId(orgVo.getId());
			sessionContainer.setOrgName(orgVo.getName());
			sessionContainer.setOrgTenantId(orgVo.getTenantId());

			labUserVo = labUserService.getLabUser(getSessionContainer()
					.getUserId());
			List<LabRoleVo> roleList = labRoleService.getLabRoleList(labUserVo
					.getId(), getSessionContainer().getOrgId());
			if (roleList != null && roleList.size() > 0) {
				String roleName = "";
				String roleId = "";
				for (LabRoleVo labRoleVo : roleList) {
					if (!roleId.contains(labRoleVo.getId())) {
						roleName += labRoleVo.getName() + ",";
						roleId += labRoleVo.getId() + ",";
					}
				}
				if (roleId.endsWith(",")) {
					roleId = roleId.substring(0, roleId.length() - 1);
				}
				if (roleName.endsWith(",")) {
					roleName = roleName.substring(0, roleName.length() - 1);
				}
				labUserVo.setRoleName(roleName);
				labUserVo.setRoleId(roleId);
			} else {
				this.addActionError(getText("user.login.unright"));
				return "sigleTenant";
			}
		} else {
			this.addActionError(getText("user.login.unright"));
			return "sigleTenant";

		}
		sessionContainer.setRoleIds(StrUtils.split(labUserVo.getRoleId(), ","));
		sessionContainer.setRoleId(labUserVo.getRoleId());
		sessionContainer.setRoleName(labUserVo.getRoleName());

		List<LabFunctionVo> funList=null;
		try {
			funList = labFunctionService
					.getMainmenuByUserIdAndMenuType(labUserVo.getId(),
							sessionContainer.getOrgId(), "0", "",getThemeType());
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sessionContainer.setFunList(funList);// 获取目录集合
		labUserVo.setFunVoList(funList);
		labUserVo.setFunCount(funList.size());
		return "loginSuccess";
	}

	// 多部门部门弹出部门选择框
	public String ajaxLabRole4User2Org() throws GlobalException, IOException {
		String[] roleNames = labRoleService.getLabRoleNamesArrayByUserId(
				labUserVo.getId(), labUserVo.getOrgId());
		outPutString(StrUtils.join(roleNames, ","));
		return NONE;
	}

	// 系统巡检需要的方法
	public void checkLogin() throws GlobalException {
		if (labUserVo == null)
			labUserVo = new LabUserVo();
		boolean flag = labUserService.isExist4LoginName(labUserVo
				.getLoginName());
		if (flag) {
			getResponse().setHeader("runStatus", "true");
		}
	}

	public String listLabFunction4iconMenu() throws GlobalException {
		if (labFunctionVo == null)
			labFunctionVo = new LabFunctionVo();
		if (!StrUtils.isBlankOrNull(labFunctionVo.getParentId())) {
			System.out.println(labFunctionVo.getParentId());
			submenuList = labFunctionService.getMainmenuByUserIdAndMenuType(
					getSessionContainer().getUserId(), getSessionContainer()
							.getOrgId(), labFunctionVo.getParentId(), "",getThemeType());
		} else {
			submenuList = labFunctionService.getMainmenuByUserIdAndMenuType(
					getSessionContainer().getUserId(), getSessionContainer()
							.getOrgId(), "0", "",getThemeType());
		}
		return "iconMenu";
	}

	// Ajax 判断session 是否在同一台电脑上被覆盖
	public String ajaxSession() throws Exception {
		SessionContainer sc = getSessionContainer();
		if (null == sc)
			return NONE;
		if (SessionContainer.map == null || SessionContainer.map.size() == 0) {
			return NONE;
		}
		String sessionID = ServletActionContext.getRequest().getParameter(
				"sessionID");
		Object value[] = SessionContainer.map.get(sessionID);
		if (null == value || value.length == 0 || value.length < 2) {
			return NONE;
		}
		String trueSessionID = String.valueOf(value[0]);
		String falseSessionID = sc.getSessionId();
		if (StringUtils.isBlank(trueSessionID)) {
			return NONE;
		}
		if (StringUtils.isBlank(falseSessionID)) {
			return NONE;
		}
		if (trueSessionID.equals(falseSessionID)) {
			return NONE;
		}
		// 处理不相同的情况 则就是同一台电脑上已经登录了多个用户
		SessionContainer trueSc = (SessionContainer) value[1];
		if (null == trueSc || StringUtils.isBlank(trueSc.getSessionId())) {
			return NONE;
		}
		ServletActionContext.getRequest().getSession().setAttribute(
				SessionContainer.Session_Container, trueSc);
		return NONE;
	}

	@SuppressWarnings( { "unchecked", "static-access" })
	public String logout() throws GlobalException {
		String tt = SessionContainer.getUserProperties(SessionContainer.THEME_TYPE);
		setThemeType(tt);
		ActionContext ac = ActionContext.getContext();
		Map map = ac.getSession();
		
		if (map != null) {
			map.clear();
		}
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (null != session)
			session.invalidate();
		themeType = "themec";
		if (SystemInstance.getInstance().isMutiTenant()) {
			return "mutiTenant";
		} else {
			return "sigleTenant";
		}
	}

	@SuppressWarnings( { "unchecked", "static-access" })
	public void closeWin() throws GlobalException {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.isNew()
				&& session.getAttribute(SessionContainer.Session_Container) != null) {
			try {
				SessionContainer sessionContainer = (SessionContainer) session
						.getAttribute(SessionContainer.Session_Container);
				if (sessionContainer != null) {
					labLogRecordService.addLabLogrecord4Bus(sessionContainer
							.getUserName()
							+ " " + DateUtils.getCurrDateTimeStr() + getText("admin.logout.system"),
							null, getText("admin.user.manage"), getText("admin.user.logout"), getText("admin.add"));
				}
			} catch (RuntimeException e) {
				Log4J.error(getText("system.out.error") + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
	}

	// 最新的菜单
	private void getNewMenu() throws GlobalException {
		if (null == labUserVo)
			labUserVo = new LabUserVo();
		SessionContainer sessionContainer = getSessionContainer();
		List<LabFunctionVo> funList = null;
		funList = labFunctionService.getMainmenuByUserIdAndMenuType(
				sessionContainer.getUserId(), sessionContainer.getOrgId(), "0",
				"",getThemeType());
		
		sessionContainer.setFunList(funList);// 获取一级菜单

		labUserVo.setFunVoList(funList);
		if (null != funList && 0 < funList.size())
			currentMainmenuId = ((LabFunctionVo) funList.get(0)).getId();
		labUserVo.setFunCount(funList.size());

		// List<LabFunctionVo> wfFunlist =
		// labFunctionService.getLabFunctionListByUserIdAndType(sessionContainer.getUserId(),
		// "1");
		// sessionContainer.setWfFunList(wfFunlist);// 获取功能集合
	}

	// 系统管理扩展业务
	public String leftframe() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		labUserVo = labUserService
				.getLabUser(getSessionContainer().getUserId());
		labUserVo.setCurrentDate(DateUtils.getCurrDateStr() + getText("user.week")
				+ DateUtils.getDayOfWeek());

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
//		Locale locale = (Locale)session.getAttribute(Globals.LOCALE_KEY);
//		System.out.println(locale);
//		System.out.println(ActionContext.getContext().getLocale());
//		ActionContext.getContext().setLocale(locale);
		// 站内信
		int count = labMsgMainService
				.getLabMsgMessage4UnreadCount(getSessionContainer().getUserId());
		session.setAttribute("unreadcount", count);
		int caogaocount = labMsgMainService
				.getLabMsgMessage4CaoGaoCount(getSessionContainer().getUserId());
		session.setAttribute("caogaocount", caogaocount);
		int sendmessage = labMsgMainService
				.getLabMsgMessage4SenderCount(getSessionContainer().getUserId());
		session.setAttribute("sendmessage", sendmessage);
		int deletemessage = labMsgMainService
				.getLabMsgMessage4DeleteCount(getSessionContainer().getUserId());
		session.setAttribute("deletemessage", deletemessage);
		// 待办提示
		List<LabFunctionVo> wFLabFunctionVoList = this.getWFSysFunctionCount();
		request.setAttribute("wFFunList", wFLabFunctionVoList);
		return "leftframe";
	}

	public String topframe() throws GlobalException {
		// 得到该用户授权的功能列表
		// getMainmenu();
		getNewMenu();
		return "topframe";
	}

	public String getSubmenu() throws GlobalException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SessionContainer sessionContainer = getSessionContainer();

		String funid = StrUtils.null2Str(request.getParameter("funId"));
		String currentId = StrUtils.null2Str(request.getParameter("currentId"));
		submenuList = labFunctionService.getLabFunctionListByUserIdAndType(
				sessionContainer.getUserId(), null, funid);
		session.setAttribute("FunCount", submenuList.size());
		currentMainmenuId = funid;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		StringBuffer result = new StringBuffer();
		if (null != submenuList && submenuList.size() > 0) {
			for (int k = 0; k < submenuList.size(); k++) {
				LabFunctionVo functionVo = submenuList.get(k);
				if (null != functionVo) {
					String url = functionVo.getUrl();
					if (null != url && !url.contains("funId=")) {
						if (url.contains("?")) {
							url = url + "&funId=" + functionVo.getId();
						} else {
							url = url + "?funId=" + functionVo.getId();
						}
					}

					if (null != currentId && currentId.length() > 0) {
						if (currentId.equals(functionVo.getId())) {
							result
									.append("<a onclick='menu(this);' class='current' id='MENUA_HREF"
											+ k
											+ "+"
											+ functionVo.getId()
											+ "' href='"
											+ basePath
											+ ""
											+ url
											+ "' target='workarea'>"
											+ functionVo.getName() + "</a>");
						} else {
							result
									.append("<a onclick='menu(this);' class='' id='MENUA_HREF"
											+ k
											+ "+"
											+ functionVo.getId()
											+ "' href='"
											+ basePath
											+ ""
											+ url
											+ "' target='workarea'>"
											+ functionVo.getName() + "</a>");
						}
					} else {
						result
								.append("<a onclick='menu(this);' class='' id='MENUA_HREF"
										+ k
										+ "+"
										+ functionVo.getId()
										+ "' href='"
										+ basePath
										+ ""
										+ url
										+ "' target='workarea'>"
										+ functionVo.getName() + "</a>");
					}
				}
			}
		}
		out.write(result.toString());
		return null;
	}

	// 获取一级菜单top
	/*
	 * private void getMainmenu() throws GlobalException { if (null ==
	 * labUserVo) labUserVo = new LabUserVo(); HttpSession session =
	 * ServletActionContext.getRequest().getSession(); SessionContainer
	 * sessionContainer = getSessionContainer(); List<LabFunctionVo> funList =
	 * labFunctionService.getMainmenuByUserIdAndMenuType(sessionContainer.getUserId(),sessionContainer.getOrgId());
	 * if (null != funList && 0 < funList.size()) currentMainmenuId =
	 * (funList.get(0)).getId(); labUserVo.setFunVoList(funList);
	 * sessionContainer.setFunList(funList); //获取目录集合
	 * session.setAttribute("MainMenuFunCount", funList.size());
	 * labUserVo.setFunCount(funList.size()); }
	 */

	public String getCheckcode() {
		return checkcode;
	}

	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}

	public LabMsgDetailVo getLabMsgDetailVo() {
		return labMsgDetailVo;
	}

	public void setLabMsgDetailVo(LabMsgDetailVo labMsgDetailVo) {
		this.labMsgDetailVo = labMsgDetailVo;
	}

	private List<LabFunctionVo> getWFSysFunctionCount() throws GlobalException {
		List<LabFunctionVo> returnLabFunctionVoList = new ArrayList<LabFunctionVo>();

		List<LabFunctionVo> sysFunctionVoList = labFunctionService
				.getLabWFFunctionListByUserId(
						getSessionContainer().getUserId(),
						getSessionContainer().getOrgId());
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		Locale locale = (Locale)session.getAttribute(Globals.LOCALE_KEY);
		if(locale == null) locale = Locale.getDefault();
		
		if (null != sysFunctionVoList && 0 < sysFunctionVoList.size()) {
			for (LabFunctionVo sysFunctionVo : sysFunctionVoList) {
				
				if(locale != null && locale.equals(Locale.US) ){
					
					String value = TranslateUtil.get(sysFunctionVo.getName());
					sysFunctionVo.setName(value);
				}
				
				String url = sysFunctionVo.getUrl();
				if (null != url && !url.contains("funId=")) {
					if (url.contains("?")) {
						sysFunctionVo.setUrl(url + "&funId="
								+ sysFunctionVo.getId());
					} else {
						sysFunctionVo.setUrl(url + "?funId="
								+ sysFunctionVo.getId());
					}
				} else {
					sysFunctionVo.setUrl("" + url);
				}
				returnLabFunctionVoList.add(sysFunctionVo);
			}
		}
		return returnLabFunctionVoList;
	}

	public String getWaitAuditCount() throws GlobalException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<LabFunctionVo> wFLabFunctionVoList = new ArrayList<LabFunctionVo>();
		wFLabFunctionVoList = this.getWFSysFunctionCount();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path + "/";
		StringBuffer result = new StringBuffer();
		if (null != wFLabFunctionVoList && wFLabFunctionVoList.size() > 0) {
			for (int k = 0; k < wFLabFunctionVoList.size(); k++) {
				LabFunctionVo functionVo = wFLabFunctionVoList.get(k);
				if (null != functionVo) {
					String url = functionVo.getUrl();

					if (null != url && !url.contains("funId=")) {
						if (url.contains("?")) {
							functionVo.setUrl(url + "&funId="
									+ functionVo.getId());
						} else {
							functionVo.setUrl(url + "?funId="
									+ functionVo.getId());
						}
					} else {
						functionVo.setUrl(url + "");
					}

					result.append("<li><a onclick=\"selectCurrentMenu('"
							+ functionVo.getParentId() + "','"
							+ functionVo.getId() + "');\" href='" + basePath
							+ "" + functionVo.getUrl() + "' target='workarea'>"
							+ functionVo.getName() + "(<font color='red'>"
							+ functionVo.getCount() + "</font>)</a></li>");
				}
			}
		}
		out.write(result.toString());
		return null;
	}

	public LabUserVo getLabUserVo() {
		return labUserVo;
	}

	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}

	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}

	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}

	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}

	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}

	@Resource
	public void setLabLogRecordService(ILabLogRecordService labLogRecordService) {
		this.labLogRecordService = labLogRecordService;
	}

	@Resource
	public void setLabMsgMainService(ILabMsgMainService labMsgMainService) {
		this.labMsgMainService = labMsgMainService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
	
}
