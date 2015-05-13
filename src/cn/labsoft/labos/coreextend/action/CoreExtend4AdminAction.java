package cn.labsoft.labos.coreextend.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.Globals;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.logs.service.ILabLogRecordService;
import cn.labsoft.labos.base.message.vo.LabMsgDetailVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.interceptor.Action;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.servicefactory.SystemInstance;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@SuppressWarnings("serial")
@Controller
@Scope("prototype")
public class CoreExtend4AdminAction extends BaseAction {
	private ILabOrgService labOrgService;
	private ILabUserService labUserService;
	private ILabRoleService labRoleService;
	private ILabFunctionService labFunctionService;
	private ILabLogRecordService labLogRecordService;
	private LabUserVo labUserVo;
	private LabMsgDetailVo labMsgDetailVo;
	private LabFunctionVo labFunctionVo;
	private String checkcode;
	private String currentMainmenuId;
	private String currentSubmenuId;
	private String tenantName;
	private String tenantId;

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

	private List<LabFunctionVo> submenuList;

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getCurrentMainmenuId() {
		return currentMainmenuId;
	}

	public void setCurrentMainmenuId(String currentMainmenuId) {
		this.currentMainmenuId = currentMainmenuId;
	}

	public String getCurrentSubmenuId() {
		return currentSubmenuId;
	}

	public void setCurrentSubmenuId(String currentSubmenuId) {
		this.currentSubmenuId = currentSubmenuId;
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
		HttpSession session = request.getSession();
		Locale locale = null;
		locale = request.getLocale();
		session.setAttribute(Globals.LOCALE_KEY, locale);
		ActionContext.getContext().setLocale(locale);
		SessionContainer sc = getSessionContainer();
		if (null != sc) {
			getMainMenu();
			return "loginSuccess";
		}
		if (SystemInstance.getInstance().isMutiTenant()) {
			return "mutiTenant";
		} else {
			return "sigleTenant";
		}
	}

	@SuppressWarnings( { "static-access", "unchecked" })
	public String loginSystem() throws GlobalException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SessionContainer sc = getSessionContainer();
		if (null != sc&&null!=sc.getUserId()) {
			getNewMenu();
			return "loginSuccess";
		}
		SessionContainer sessionContainer = new SessionContainer();
		try {
			if (null == labUserVo) {
				return "sigleTenant";
			}
			labUserVo = labUserService.getLabUserByLogInNameAndPwd(labUserVo.getLoginName(), labUserVo.getPwd());
			if (labUserVo == null || StrUtils.isBlankOrNull(labUserVo.getId())) {
				this.addActionError(getText("admin.login.unuser"));
				return "sigleTenant";
			} else if (null == labUserVo || null == labUserVo.getUserType() || "".equals(labUserVo.getUserType()) || Constants_Base.FRONT.equals(labUserVo.getUserType())) {
				this.addActionError(getText("admin.login.unpassed"));
				return "sigleTenant";
			} else { // 验证成功
				LabOrgVo sysOrgVo = new LabOrgVo();
				sysOrgVo = labOrgService.getLabOrgUnit();
				if (null != sysOrgVo && null != sysOrgVo.getLogo()) {
					sessionContainer.setLogoUrl(sysOrgVo.getLogo());
					sessionContainer.setOrgUnit(sysOrgVo.getName());
				}
				List<LabOrgVo> orgList = labOrgService.getLabOrgByUserId(labUserVo.getId());
				LabOrgVo labOrgVo=null;
				if(orgList==null||orgList.size()==0){
					this.addActionError(getText("admin.login.unpassed"));
					return "sigleTenant";
				}else if(orgList.size()==1){
					labOrgVo=orgList.get(0);
					sessionContainer.setOrgId(labOrgVo.getId());
					sessionContainer.setOrgName(labOrgVo.getName());
					sessionContainer.setOrgTenantId(labOrgVo.getTenantId());
				}else if(orgList.size()>1){
					labOrgVo=orgList.get(0);
					sessionContainer.setOrgId(labOrgVo.getId());
					sessionContainer.setOrgName(labOrgVo.getName());
					sessionContainer.setOrgTenantId(labOrgVo.getTenantId());
				}
				// 获取角色
				String[] rolesIds = new String[20];
				String[] rolenames = new String[20];
				rolesIds = labRoleService.getLabRoleIdsArrayByUserId(labUserVo.getId(),labOrgVo.getId());
				rolenames = labRoleService.getLabRoleNamesArrayByUserId(labUserVo.getId(),labOrgVo.getId());
				if (0 == rolesIds.length) {
					this.addActionError(getText("admin.login.unpassed"));
					return "sigleTenant";
				} else {
					labUserVo.setRoleName(StrUtils.join(rolenames, ",")); // 设置第一个角色名为用户角色名e
				}
				if (null != labUserVo.getIp() && !"".equals(labUserVo.getIp())) {
					String ip[] = labUserVo.getIp().split(",");
					for (int i = 0; i < ip.length; i++) {
						if (!ip[i].contains(request.getRemoteHost())) {
							this.addActionError(getText("admin.login.outnetwork"));
							return "sigleTenant";
						}
					}
				}

				labUserVo.setRoleId(StrUtils.join(rolesIds, ","));
				String sessionID = StrUtils.replace(UUID.randomUUID().toString(), "-", "");
				sessionContainer.setSessionId(sessionID);// 自动生成一个sessionid
				sessionContainer.setUserId(labUserVo.getId());
				sessionContainer.setLoginName(labUserVo.getLoginName());
				sessionContainer.setPassword(labUserVo.getPwd());
				sessionContainer.setUserName(labUserVo.getName());
				sessionContainer.setStyleName("admin");
				sessionContainer.setFunId("0");

				sessionContainer.setRoleIds(rolesIds);
				sessionContainer.setRoleId(StrUtils.join(rolesIds, ","));
				sessionContainer.setRoleName(StrUtils.join(rolenames, ","));
				sessionContainer.setIp(ServletActionContext.getRequest().getRemoteAddr());
				sessionContainer.setType(labUserVo.getUserType());
				labUserVo.setCurrentDate(DateUtils.getCurrDateStr() + getText("global.week") + DateUtils.getDayOfWeek());

				// 获取本机的（客户端）的计算机名称
				Object mapValues[] = new Object[2];
				mapValues[0] = sessionID;
				mapValues[1] = sessionContainer;
				SessionContainer.setMap(sessionID, mapValues);
				
				
				List<LabFunctionVo> funList = labFunctionService.getMainmenuByUserIdAndType(labUserVo.getId(), labUserVo.getUserType(),null);
				sessionContainer.setFunList(funList);// 获取目录集合
				labUserVo.setFunVoList(funList);
				labUserVo.setFunCount(funList.size());
				session.setAttribute(SessionContainer.Session_Container, sessionContainer);
				labLogRecordService.addLabLogrecord4Bus(getSessionContainer().getUserName() + " " + DateUtils.getCurrDateTimeStr() + getText("admin.login.system"), null, getText("admin.user.manage"),getText("admin.user.login") ,getText("admin.add"));
				return "loginSuccess";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
	}
	//系统巡检需要的方法
	public void checkLogin()throws GlobalException {
		if(labUserVo==null)labUserVo=new LabUserVo();
		boolean flag= labUserService.isExist4LoginName(labUserVo.getLoginName());
		if(flag){
			getResponse().setHeader("runStatus", "true");
		}
	}
	// Ajax 判断session 是否在同一台电脑上被覆盖
	public String ajaxSession() throws Exception {
		SessionContainer sc = getSessionContainer();
		if (null == sc)
			return NONE;
		if (SessionContainer.map == null || SessionContainer.map.size() == 0) {
			return NONE;
		}
		String sessionID = ServletActionContext.getRequest().getParameter("sessionID");
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
		ServletActionContext.getRequest().getSession().setAttribute(SessionContainer.Session_Container, trueSc);
		return NONE;
	}

	@SuppressWarnings( { "unchecked", "static-access" })
	public String logout() throws GlobalException {
		ActionContext ac = ActionContext.getContext();
		Map map = ac.getSession();
		if (map != null) {
			map.clear();
		}
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(null!=session)
			session.invalidate();
		if (SystemInstance.getInstance().isMutiTenant()) {
			return "mutiTenant";
		} else {
			return "sigleTenant";
		}
	}

	@SuppressWarnings( { "unchecked", "static-access" })
	public void closeWin() throws GlobalException {
		HttpSession session = ServletActionContext.getRequest().getSession();
		if (!session.isNew() && session.getAttribute(SessionContainer.Session_Container) != null) {
			try {
				SessionContainer sessionContainer = (SessionContainer) session.getAttribute(SessionContainer.Session_Container);
				if (sessionContainer != null){
					labLogRecordService.addLabLogrecord4Bus(sessionContainer.getUserName() + " " + DateUtils.getCurrDateTimeStr() + getText("admin.logout.system"), null, getText("admin.user.manage"), getText("admin.user.logout"), getText("admin.add"));
				}
			} catch (RuntimeException e) {
				Log4J.error("系统退出异常！" + e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}

	}

	// 最新的菜单
	private void getNewMenu() throws GlobalException {
		if (null == labUserVo)
			labUserVo = new LabUserVo();
		SessionContainer sessionContainer = getSessionContainer();
		List<LabFunctionVo> funList = labFunctionService.getMainmenuByUserIdAndType(sessionContainer.getUserId(), sessionContainer.getType(),null);
		sessionContainer.setFunList(funList);// 获取一级菜单
		labUserVo.setFunVoList(funList);
		labUserVo.setFunCount(funList.size());

		List<LabFunctionVo> wfFunlist = labFunctionService.getLabFunctionListByUserIdAndType(sessionContainer.getUserId(), "1");
		sessionContainer.setWfFunList(wfFunlist);// 获取功能集合

	}

	/**
	 * 获得第一级菜单
	 * 
	 * @throws GlobalException
	 */
	private void getMainMenu() throws GlobalException {
		if (null == labUserVo)
			labUserVo = new LabUserVo();
		SessionContainer sessionContainer = getSessionContainer();
		List<LabFunctionVo> funList = labFunctionService.getMainmenuByUserIdAndType(sessionContainer.getUserId(), sessionContainer.getType(),null);
		sessionContainer.setFunList(funList);// 获取一级菜单z
		labUserVo.setFunVoList(funList);
		labUserVo.setFunCount(funList.size());
	}

	// 系统管理扩展业务
	public String leftframe() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		SessionContainer sessionContainer = getSessionContainer();
		if (null != sessionContainer) {
			List<LabFunctionVo> funlist = labFunctionService.getMainmenuByUserIdAndType(sessionContainer.getUserId(), sessionContainer.getType(),null);
			if (null != funlist && funlist.size() > 0) {
				if (null == labFunctionVo.getId() || "".equals(labFunctionVo.getId())) {
					labFunctionVo.setId(funlist.get(0).getId());
				} else {
					labFunctionVo.setId(labFunctionVo.getId());
				}
			}
		}
		return "leftframe";
	}
	public String mainframe() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		SessionContainer sessionContainer = getSessionContainer();
		if (null != sessionContainer) {
			List<LabFunctionVo> funlist = labFunctionService.getMainmenuByUserIdAndType(sessionContainer.getUserId(), sessionContainer.getType(),null);
			if (null != funlist && funlist.size() > 0) {
				if (null == labFunctionVo.getId() || "".equals(labFunctionVo.getId())) {
					labFunctionVo.setId(funlist.get(0).getId());
				} else {
					labFunctionVo.setId(labFunctionVo.getId());
				}
			}
		}
		return "mainframe";
	}
	public String topframe() throws GlobalException {
		// 得到该用户授权的功能列表
		getMainmenu();
		return "topframe";
	}

	public String getSubmenu() throws GlobalException, IOException {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		SessionContainer sessionContainer = getSessionContainer();

		String funid = StrUtils.null2Str(request.getParameter("funId"));
		String currentId = StrUtils.null2Str(request.getParameter("currentId"));
		submenuList = labFunctionService.getLabFunctionListByUserIdAndType(sessionContainer.getUserId(), null, funid);
		session.setAttribute("FunCount", submenuList.size());
		currentMainmenuId = funid;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
							result.append("<a onclick='menu(this);' class='current' id='MENUA_HREF" + k + "+" + functionVo.getId() + "' href='" + basePath + "" + url + "' target='workarea'>" + functionVo.getName() + "</a>");
						} else {
							result.append("<a onclick='menu(this);' class='' id='MENUA_HREF" + k + "+" + functionVo.getId() + "' href='" + basePath + "" + url + "' target='workarea'>" + functionVo.getName() + "</a>");
						}
					} else {
						result.append("<a onclick='menu(this);' class='' id='MENUA_HREF" + k + "+" + functionVo.getId() + "' href='" + basePath + "" + url + "' target='workarea'>" + functionVo.getName() + "</a>");
					}
				}
			}
		}
		out.write(result.toString());
		return null;
	}

	//获取一级菜单top
	private void getMainmenu() throws GlobalException {
		if (null == labUserVo)
			labUserVo = new LabUserVo();
		HttpSession session = ServletActionContext.getRequest().getSession();
		SessionContainer sessionContainer = getSessionContainer();
		List<LabFunctionVo> funlist = labFunctionService.getMainmenuByUserIdAndType(sessionContainer.getUserId(), sessionContainer.getType(),null);
		if (null != funlist && 0 < funlist.size())
			currentMainmenuId = (funlist.get(0)).getId();
		labUserVo.setFunVoList(funlist);
		sessionContainer.setFunList(funlist);// 获取目录集合
		session.setAttribute("MainMenuFunCount", funlist.size());
		labUserVo.setFunCount(funlist.size());
	}

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

		List<LabFunctionVo> sysFunctionVoList = labFunctionService.getLabWFFunctionListByUserId(getSessionContainer().getUserId(),null);

		if (null != sysFunctionVoList && 0 < sysFunctionVoList.size()) {
			for (LabFunctionVo sysFunctionVo : sysFunctionVoList) {
				String url = sysFunctionVo.getUrl();
				if (null != url && !url.contains("funId=")) {
					if (url.contains("?")) {
						sysFunctionVo.setUrl(url + "&funId=" + sysFunctionVo.getId());
					} else {
						sysFunctionVo.setUrl(url + "?funId=" + sysFunctionVo.getId());
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
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		StringBuffer result = new StringBuffer();
		if (null != wFLabFunctionVoList && wFLabFunctionVoList.size() > 0) {
			for (int k = 0; k < wFLabFunctionVoList.size(); k++) {
				LabFunctionVo functionVo = wFLabFunctionVoList.get(k);
				if (null != functionVo) {
					String url = functionVo.getUrl();

					if (null != url && !url.contains("funId=")) {
						if (url.contains("?")) {
							functionVo.setUrl(url + "&funId=" + functionVo.getId());
						} else {
							functionVo.setUrl(url + "?funId=" + functionVo.getId());
						}
					} else {
						functionVo.setUrl(url + "");
					}

					result.append("<li><a onclick=\"selectCurrentMenu('" + functionVo.getParentId() + "','" + functionVo.getId() + "');\" href='" + basePath + "" + functionVo.getUrl() + "' target='workarea'>" + functionVo.getName() + "(<font color='red'>" + functionVo.getCount() + "</font>)</a></li>");
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

	public LabFunctionVo getLabFunctionVo() {
		return labFunctionVo;
	}

	public void setLabFunctionVo(LabFunctionVo labFunctionVo) {
		this.labFunctionVo = labFunctionVo;
	}

}
