package cn.labsoft.labos.base.function.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 系统功能Action类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Controller
@Scope("prototype")
public class LabFunctionAction extends BaseAction {
	private static final long serialVersionUID = -5171676931292581361L;

	private ILabFunctionService labFunctionService;
	private ILabConfigService labConfigService;
	private ILabRoleService labRoleService;
	private ILabOrgService labOrgService;
	private LabFunctionVo labFunctionVo;
	private List<LabFunctionVo> funlist;

	/**
	 * 组织管理Service注入
	 * 
	 * @param labOrgService
	 */
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}

	/**
	 * 角色管理Service注入
	 * 
	 * @param labRoleService
	 */
	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}

	/**
	 * 功能管理Service注入
	 * 
	 * @param labFunctionService
	 */
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}

	/**
	 * 配置管理Service注入
	 * 
	 * @param labConfigService
	 */
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}

	public List<LabFunctionVo> getFunlist() {
		return funlist;
	}

	public void setFunlist(List<LabFunctionVo> funlist) {
		this.funlist = funlist;
	}

	public LabFunctionVo getLabFunctionVo() {
		return labFunctionVo;
	}

	public void setLabFunctionVo(LabFunctionVo labFunctionVo) {
		this.labFunctionVo = labFunctionVo;
	}

	/**
	 * 功能FRAME
	 * 
	 * @throws GlobalException
	 */
	public String frameLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		return FRAME;
	}

	/**
	 * 获得功能树
	 * 
	 * @throws GlobalException
	 */
	public String treeLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		return TREE;
	}

	/**
	 * 获得功能树字符串
	 * 
	 * @throws GlobalException
	 */
	public String ajaxTreeLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		StringBuffer tree = labFunctionService.getLabFunctionListTree(ServletActionContext.getRequest().getParameter("treeNid"));
		outPrint(ServletActionContext.getResponse(), tree);
		return NONE;
	}

	/**
	 * 获得后台功能树字符串
	 * 
	 * @throws GlobalException
	 */
	public String ajaxTreeLabFunction4Admin() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		String treeid = ServletActionContext.getRequest().getParameter("treeNid");
		if (null == treeid || "".equals(treeid)) {
			treeid = labFunctionVo.getId();
		}
		if (null == treeid || treeid.equals("")) {
			outPrint(ServletActionContext.getResponse(), new StringBuffer("[]"));
		} else {
			StringBuffer tree = labFunctionService.getLabFunctionListTree(treeid);
			outPrint(ServletActionContext.getResponse(), tree);
		}

		labFunctionVo.setId(treeid);
		return NONE;
	}

	/**
	 * 获得功能分页对象
	 * 
	 * @throws GlobalException
	 */
	public String listLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
			labFunctionVo.setParentId("0");
		}
		if (StrUtils.isBlankOrNull(labFunctionVo.getParentId())) {
			labFunctionVo.setParentId("0");
		}
		LabFunctionVo functionVo=labFunctionService.getLabFunction(labFunctionVo.getParentId());
		if(functionVo.getType().equals("1")){
			labFunctionVo=functionVo;
			return SHOW;
		}
		pageResult = labFunctionService.getLabFunctionPR(labFunctionVo, pageResult);
		LabConfigVo config = labConfigService.getLabConfigByCode("nodeNum");
		int num = 0;
		try {
			num = Integer.valueOf(config.getValue());
		} catch (NumberFormatException e) {
			num = 10;
		}
		if (pageResult.getPageBean().getTotalRows() >= num) {
			labFunctionVo.setIsOper("N");
		}
		return LIST;
	}

	/**
	 * 新增功能跳转
	 * 
	 * @throws GlobalException
	 */
	public String preAddLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo.setSort(labFunctionService.getMaxSort(labFunctionVo.getParentId()) + 1);
		labFunctionVo.setType("0");
		labFunctionVo.setIsDisplay(Constants_Common.Y);
		labFunctionVo.setIsProcess(Constants_Common.N);
		labFunctionVo.setIsQuery(Constants_Common.N);
		labFunctionVo.setIsTemplate(Constants_Common.N);
		labFunctionVo.setIsFront(Constants_Common.N);
		labFunctionVo.setIsBack(Constants_Common.N);
		labFunctionVo.setIsBarCode(Constants_Common.N);
		labFunctionVo.setIsReport(Constants_Common.N);
		labFunctionVo.setIsQuickFun(Constants_Common.N);
		return PREADD;
	}

	/**
	 * 新增功能
	 * 
	 * @throws GlobalException
	 */
	public String addLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		try {
			labFunctionVo = labFunctionService.addLabFunction(labFunctionVo);
		} catch (Exception e) {
			//e.printStackTrace();
			Log4J.error(getText("add.newobject.error"), e);
			throw new GlobalException(" " + e.getMessage());
		}
		return ADD;
	}

	/**
	 * 修改功能跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdateLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo = labFunctionService.getLabFunction(labFunctionVo.getId());
		if (null == labFunctionVo.getIsFront() || "".equals(labFunctionVo.getIsFront())) {
			labFunctionVo.setIsFront(Constants_Common.N);
		}
		if (null == labFunctionVo.getIsBack() || "".equals(labFunctionVo.getIsBack())) {
			labFunctionVo.setIsBack(Constants_Common.N);
		}
		return PREUPDATE;
	}

	/**
	 * 修改功能
	 * 
	 * @throws GlobalException
	 */
	public String updateLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}

		try {
			labFunctionVo = labFunctionService.updateLabFunction(labFunctionVo);
		} catch (Exception e) {
			Log4J.error(getText("modify.object.error"), e);
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	/**
	 * 删除功能
	 * 
	 * @throws GlobalException
	 */
	public String updateLabFunction4Del() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		try {
			labFunctionService.updateLabFunction4Del(labFunctionVo.getIds());
		} catch (Exception e) {
			Log4J.error(getText("delete.object.error"), e);
			throw new GlobalException("" + e.getMessage());
		}
		return DELETE;
	}

	/**
	 * 查看功能
	 * 
	 * @throws GlobalException
	 */
	public String showLabFunction() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo = labFunctionService.getLabFunction(labFunctionVo.getId());
		return SHOW;
	}

	/**
	 * 查看功能名是否重复
	 * 
	 * @throws GlobalException
	 */
	public String ajaxIsSameFunName() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		boolean flag = labFunctionService.isExist4Name(labFunctionVo.getName(), labFunctionVo.getParentId());
		try {
			outPutString(flag + "");
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查看功能编号是否重复
	 * 
	 * @throws GlobalException
	 */
	public String ajax2IsExistFunCode() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		boolean flag = labFunctionService.isExistFunCode(labFunctionVo.getWfcode(), labFunctionVo.getId());
		try {
			outPutString(flag + "");
		} catch (IOException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 查看功能是否可删除
	 * 
	 * @throws GlobalException
	 */
	public String ajaxIsCouldDelete() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		boolean flag = labFunctionService.isCouldDel(labFunctionVo.getId());
		try {
			outPutString(flag + "");
		} catch (IOException e) {

			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}

	/**
	 * 展示可选功能
	 * 
	 * @throws GlobalException
	 */
	public String showFunction4Select() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		return PREUPDATE;
	}

	/**
	 * 获得功能树字符串
	 * 
	 * @throws GlobalException
	 */
	public String ajaxTreeParent() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo = labFunctionService.getLabFunction(labFunctionVo.getId());
		StringBuffer directory = labFunctionService.getLabFunctionDirectoryExpectSelfAndSubs(ServletActionContext.getRequest().getParameter("treeNid"), labFunctionVo.getId());
		outPrint(ServletActionContext.getResponse(), directory);
		return NONE;
	}

	/**
	 * 修改父功能
	 * 
	 * @throws GlobalException
	 */
	public String updateLabFunction4Parent() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo = labFunctionService.updateLabFunctionParent(labFunctionVo);
		return NONE;
	}

	/**
	 * 获得功能连接
	 * 
	 * @throws GlobalException
	 */
	public String getLabFunctonUrl() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo = labFunctionService.getLabFunction(labFunctionVo.getId());
		if (null != labFunctionVo.getUrl()) {
		} else {
			labFunctionVo.setUrl("/");
		}
		return SHOW;
	}

	/**
	 * 功能数据权限修改跳转
	 * 
	 * @throws GlobalException
	 */
	public String preUpdateLabFunction4Data() throws GlobalException {
		if (null == labFunctionVo) {
			labFunctionVo = new LabFunctionVo();
		}
		labFunctionVo.setType("1");
		labFunctionVo.setIsFront(Constants_Common.Y);
		funlist = labFunctionService.getLabFunctionList(labFunctionVo);
		List<LabRoleVo> roleList = labRoleService.getAllLabRoleList();
		setAttribute("roleList", roleList);
		List<Integer> orgLeveList = labOrgService.getLabOrgLevelList(getSessionContainer().getOrgId());
		setAttribute("orgLeveList", orgLeveList);
		return PREUPDATE;
	}

	/**
	 * 功能数据权限修改
	 * 
	 * @throws GlobalException
	 */
	public String updateLabFunction4Data() throws GlobalException {
		labFunctionService.updateLabFunction4Data(funlist);
		return UPDATE;
	}
}