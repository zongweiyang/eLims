package cn.labsoft.labos.common.workflow.action;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.base.role.service.ILabRoleService;
import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfPathVarVo;
import cn.labsoft.labos.common.workflow.vo.LabWfPathVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessInsVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessVo;
import cn.labsoft.labos.common.workflow.vo.LabWfStepLogsVo;
import cn.labsoft.labos.common.workflow.vo.LabWfStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfVariableVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 流程建模操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabWfProcessAction extends BaseAction {

	private static final long serialVersionUID = 4716141306707580602L;
	private ILabWfProcessService labWfProcessService;
	private ILabFunctionService labFunctionService;
	private ILabUserService labUserService;
	private ILabRoleService labRoleService;
	private ILabWfProcessInsService labWfProcessInsService;
	
	private LabWfProcessVo labWfProcessVo;
	private LabWfStepVo labWfStepVo;
	private LabWfPathVo labWfPathVo;
	private LabWfProcessInsVo labWfProcessInsVo;
	/**
	 * 流程定义Service注入
	 * @param labWfProcessService
	 */
	@Resource
	public void setLabWfProcessService(ILabWfProcessService labWfProcessService) {
		this.labWfProcessService = labWfProcessService;
	}
	/**
	 * 功能维护Service注入
	 * @param labFunctionService
	 */
	@Resource
	public void setLabFunctionService(ILabFunctionService labFunctionService) {
		this.labFunctionService = labFunctionService;
	}
	/**
	 * 用户管理Service注入
	 * @param labUserService
	 */
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	/**
	 * 角色管理Service注入
	 * @param labRoleService
	 */
	@Resource
	public void setLabRoleService(ILabRoleService labRoleService) {
		this.labRoleService = labRoleService;
	}
	/**
	 * 流程实例Service注入
	 * @param labUserService
	 */
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
	public LabWfProcessVo getLabWfProcessVo() {
		return labWfProcessVo;
	}
	public void setLabWfProcessVo(LabWfProcessVo labWfProcessVo) {
		this.labWfProcessVo = labWfProcessVo;
	}
	public LabWfStepVo getLabWfStepVo() {
		return labWfStepVo;
	}
	public void setLabWfStepVo(LabWfStepVo labWfStepVo) {
		this.labWfStepVo = labWfStepVo;
	}
	public LabWfPathVo getLabWfPathVo() {
		return labWfPathVo;
	}
	public void setLabWfPathVo(LabWfPathVo labWfPathVo) {
		this.labWfPathVo = labWfPathVo;
	}
	public LabWfProcessInsVo getLabWfProcessInsVo() {
		return labWfProcessInsVo;
	}
	public void setLabWfProcessInsVo(LabWfProcessInsVo labWfProcessInsVo) {
		this.labWfProcessInsVo = labWfProcessInsVo;
	}
	/**
	 * 获取始流程初信息
	 * @throws GlobalException
	 */
	public String listLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null){
			labWfProcessVo=new LabWfProcessVo();
		}
		pageResult=labWfProcessService.getWfProcessPR(labWfProcessVo, pageResult);
		//获取流程源列表
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsProcess("Y");
		labFunctionVo.setType("0");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		return LIST;
	}
	/**
	 * 获取已经使用流程的业务日志信息
	 * @throws GlobalException
	 */
	public String listLabWfProcess4Logs() throws GlobalException {
		if(labWfProcessVo==null){
			labWfProcessVo=new LabWfProcessVo();
			pageResult.setOrder(PageResult.ORDER_DESC);
			pageResult.setOrderColumn("modifyDate");
		}
		pageResult=labWfProcessService.getWfProcessPR4Logs(labWfProcessVo, pageResult);
		//获取流程源列表
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsProcess("Y");
		labFunctionVo.setType("0");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		return LIST;
	}
	/**
	 * 跳转到流程定义新增页面
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String preAddLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		
		labWfProcessVo.setIsSubWf(Constants_Base.N);
		//获取初始变量列表
		List<LabWfVariableVo> varList=labWfProcessService.getWfVariableList(labWfProcessVo.getId());
		if(varList==null)varList=new ArrayList<LabWfVariableVo>();
		labWfProcessVo.setVarList(varList);
		//获取流程源列表
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsProcess("Y");
		labFunctionVo.setType("0");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		//获取已建模未启用的流程
		LabWfProcessVo processVo=new LabWfProcessVo();
		processVo.setStatus(LabWfConstant.PROCESS_EDIT);
		List<LabWfProcessVo> processList = labWfProcessService.getWfProcessList(processVo);
		setAttribute("processList", processList);
		return PREADD;
	}
	/**
	 * 保存流程初始信息
	 * @throws GlobalException
	 */
	public String addLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		labWfProcessVo.setUserId(getSessionContainer().getUserId());
		labWfProcessVo.setUserName(getSessionContainer().getUserName());
		labWfProcessService.addWfProcess(labWfProcessVo);
	   return ADD;
	}
	/**
	 * 跳转到流程初始信息修改页面
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String preUpdateLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		labWfProcessVo=labWfProcessService.getWfProcess(labWfProcessVo.getId());
		//流程步骤
		List<LabWfFunStepVo> sfListVo=labWfProcessService.getWfFunStepList(labWfProcessVo.getId());
		if(sfListVo==null)sfListVo=new ArrayList<LabWfFunStepVo>();
		labWfProcessVo.setFunStepList(sfListVo);
		//流程变量
		List<LabWfVariableVo> varList=labWfProcessService.getWfVariableList(labWfProcessVo.getId());
		if(varList==null)varList=new ArrayList<LabWfVariableVo>();
		labWfProcessVo.setVarList(varList);
		//流程源
		LabFunctionVo labFunctionVo=new LabFunctionVo();
		labFunctionVo.setIsProcess("Y");
		labFunctionVo.setType("0");
		List<LabFunctionVo> funcList=labFunctionService.getLabFunctionList(labFunctionVo);
		getRequest().setAttribute("funcList", funcList);
		//获取已建模未启用的流程
		LabWfProcessVo processVo=new LabWfProcessVo();
		processVo.setStatus(LabWfConstant.PROCESS_EDIT);
		List<LabWfProcessVo> processList = labWfProcessService.getWfProcessList(processVo);
		setAttribute("processList", processList);
	   return PREUPDATE;
	}
	/**
	 * 修改流程初始信息
	 * @throws GlobalException
	 */
	public String updateLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		labWfProcessVo.setUserId(getSessionContainer().getUserId());
		labWfProcessVo.setUserName(getSessionContainer().getUserName());
		labWfProcessService.updateWfProcess(labWfProcessVo);
	   return UPDATE;
	}
	/**
	 * 初始化信息查看
	 * @throws GlobalException
	 */
	public String getLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		labWfProcessVo=labWfProcessService.getWfProcess(labWfProcessVo.getId());
		List<LabWfFunStepVo> sfListVo=labWfProcessService.getWfFunStepList(labWfProcessVo.getId());
		if(sfListVo==null)sfListVo=new ArrayList<LabWfFunStepVo>();
		labWfProcessVo.setFunStepList(sfListVo);
		List<LabWfVariableVo> varList=labWfProcessService.getWfVariableList(labWfProcessVo.getId());
		if(varList==null)varList=new ArrayList<LabWfVariableVo>();
		labWfProcessVo.setVarList(varList);
	   return SHOW;
	}
	
	/**
	 * 启用流程模型
	 * @throws GlobalException
	 */
	public String updateLabWfProcess2Open() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		boolean b=labWfProcessService.updateProcess2Open(labWfProcessVo);
		if(!b){
			this.getErrorForm().setDisplayErrorName(getText("process.start.error"));
		}
		return UPDATE;
	}
	
	/**
	 * 删除流程信息
	 * @throws GlobalException
	 */
	public String deleteLabWfProcess() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		labWfProcessService.delWfProcess(labWfProcessVo.getId());
	   return DELETE;
	}
	/**
	 * 跳转到建模页面
	 * @throws GlobalException
	 */
	public String preUpdateLabWfProcess4Content() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		String status=labWfProcessVo.getInitStatus();
		labWfProcessVo=labWfProcessService.getWfProcess(labWfProcessVo.getId());
		labWfProcessVo.setInitStatus(status);
	   return PREUPDATE;
	}
	/**
	 * 加载模板内容
	 * @throws GlobalException
	 */
	public String preUpdateLabWfProcess4Flow() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		String flowXml;
		flowXml = labWfProcessService.getFlowDefXmlByBusId(labWfProcessVo.getId(),labWfProcessVo.getInitStatus());
		labWfProcessVo.setFlowXml(flowXml);
	   return PREUPDATE;
	}
	/**
	 * 修改模板内容
	 * @throws GlobalException
	 */
	public String updateLabWfProcess4Flow() throws GlobalException {
		if(labWfProcessVo==null)labWfProcessVo=new LabWfProcessVo();
		labWfProcessService.saveOrUpdateFlowDefXml(labWfProcessVo.getFlowXml(),labWfProcessVo.getId());
		return UPDATE;
	}
	/**
	 * 修改节点属性
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String preUpdateLabWfStep() throws GlobalException {
		if(labWfStepVo==null)labWfStepVo=new LabWfStepVo();
		 
		String inStr=getRequest().getParameter("labWfStepVo.name");
		labWfStepVo.setName(inStr);
		LabUserVo labUserVo=new LabUserVo();
		List<LabUserVo> userList=labUserService.getLabUserList(labUserVo);
		List<LabRoleVo> roleList=labRoleService.getAllLabRoleList();
		if(userList==null)userList=new ArrayList<LabUserVo>();
		if(roleList==null)roleList=new ArrayList<LabRoleVo>();
		getRequest().setAttribute("userList", userList);
		getRequest().setAttribute("roleList", roleList);
		return PREUPDATE;
	}
	/**
	 * 展示节点属性
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String showLabWfStep() throws GlobalException {
		if(labWfStepVo==null)labWfStepVo=new LabWfStepVo();
		String inStr=getRequest().getParameter("labWfStepVo.name");
		labWfStepVo.setName(inStr);
		String type=labWfStepVo.getOperType();
		String[] ids=labWfStepVo.getIds();
		if(null!=type&&type.equals(LabWfConstant.OPER_TYPE_USER)){
			List<LabUserVo> userList=new ArrayList<LabUserVo>();
			if(ids!=null){
				for (String ii : ids) {
					if(ii==null||ii.equals(""))continue;
					LabUserVo labUserVo=labUserService.getLabUser(ii);
					userList.add(labUserVo);
				}
			}else if(ids!=null){
				LabUserVo sysUserVo=labUserService.getLabUser(id);
				userList.add(sysUserVo);
			}
			if(userList==null)userList=new ArrayList<LabUserVo>();
			getRequest().setAttribute("userList", userList);
		}else if(null!=type&&type.equals(LabWfConstant.OPER_TYPE_ROLE)){
			List<LabRoleVo> roleList=new ArrayList<LabRoleVo>();
			if(ids!=null){
				for (String ii : ids) {
					if(ii==null||ii.equals(""))continue;
					LabRoleVo sysRole=labRoleService.getLabRole(ii);
					roleList.add(sysRole);
				}
			}else if(ids!=null){
				LabRoleVo sysRole=labRoleService.getLabRole(ids);
				roleList.add(sysRole);
			}
			if(roleList==null)roleList=new ArrayList<LabRoleVo>();
			getRequest().setAttribute("roleList", roleList);
		}else{
		}
		return SHOW;
	}
	/**
	 * 修改迁移属性
	 * @throws GlobalException
	 */
	public String preUpdateLabWfPath() throws GlobalException {
		if(labWfPathVo==null)labWfPathVo=new LabWfPathVo();
		
			String inStr=getRequest().getParameter("labWfPathVo.name"); 
			labWfPathVo.setName(inStr);
		//获取变量list
		List<LabWfVariableVo> varList=labWfProcessService.getWfVariableList(labWfPathVo.getProcessId());
		if(varList==null)varList=new ArrayList<LabWfVariableVo>();
		getRequest().setAttribute("varList", varList);
		
		return PREUPDATE;
	}
	/**
	 * 展示迁移属性
	 * @throws GlobalException
	 */
	public String showLabWfPath() throws GlobalException {
		if(labWfPathVo==null)labWfPathVo=new LabWfPathVo();
		String inStr=getRequest().getParameter("labWfPathVo.name"); 
		labWfPathVo.setName(inStr);
		String[] ids=labWfPathVo.getIds();
		List<LabWfPathVarVo> varList=labWfProcessService.getWfPathVarList(ids);
		
		//获取变量list
		if(varList==null)varList=new ArrayList<LabWfPathVarVo>();
		getRequest().setAttribute("varList", varList);
		return SHOW;
	}
	@SuppressWarnings("unchecked")
	public void ajaxLabWfStep4Select() throws GlobalException {
		if (null == labWfProcessVo) {
			labWfProcessVo = new LabWfProcessVo();
		}
		HttpServletResponse response=ServletActionContext.getResponse();
		StringBuffer newList = new StringBuffer("[");
		if (null != labWfProcessVo.getFunId() && !"".equals(labWfProcessVo.getFunId())) {
			LabFunctionVo labFunctionVo=new LabFunctionVo();
			labFunctionVo.setParentId(labWfProcessVo.getFunId());
			labFunctionVo.setIsProcess("Y");
			List<LabFunctionVo> funList=labFunctionService.getLabFunctionList(labFunctionVo);
			if(null!=funList&&funList.size()>0){
				for (LabFunctionVo function : funList) {
					newList.append( "{\"id\":\"" + function.getId() + "\"," +
								 "\"name\":\"" + function.getName() + "\","+
					 			 "\"wfcode\":\"" + function.getWfcode() + "\""+
					             "},");
				}
				String newStr=newList.toString();
				if(newStr.length()>1){
					newStr=newStr.substring(0, newStr.length()-1);
				}
				newList=new StringBuffer(newStr);
			}
		} else {
			newList.append("{\"machineNo\":\"\",\"modelStr\":\"\"}");
		}
		newList.append("]");
		outPrint(response, newList);
	}
	/**
	 * ajax开启流程
	 * @throws GlobalException
	 */
	public String ajax4hasOpenedProcess() throws GlobalException {
		String funId=getParameter("funId");
		boolean flag=labWfProcessService.isHasOpenedProcess4Fun(funId);
		try {
			outPutString(flag+"");
		} catch (IOException e) {
			////e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return NONE;
	}
	/**
	 * 查看流程
	 * @throws GlobalException
	 */
	public String showLabWfProgressIns() throws GlobalException {
		if (null == labWfProcessInsVo) {
			labWfProcessInsVo = new LabWfProcessInsVo();
		}
		labWfProcessInsVo=labWfProcessInsService.getWfProcessIns(labWfProcessInsVo);
		return SHOW;
	}
//	public String showLabWfProgressIns4Sub() throws GlobalException {
//		if (null == labWfProcessInsVo) {
//			labWfProcessInsVo = new LabWfProcessInsVo();
//		}
//		List<LabWfProcessInsVo> processInsList=labWfProcessInsService.getSubProcessInsListByPinsIdAndProcessId(labWfProcessInsVo.getParInsId(),labWfProcessInsVo.getProcessId());
//		setAttribute("processInsList", processInsList);
//		return SHOW;
//	}
	/**
	 * 查看流程日志
	 */
	public String ajaxLabWfStepLogs2show() throws GlobalException {
		List<LabWfStepLogsVo> logList=labWfProcessInsService.getLabWfStepLogsList(labWfProcessInsVo);
		super.ajax(logList);
		return NONE;
	}
}