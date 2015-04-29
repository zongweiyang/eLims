package cn.labsoft.labos.common.workflow.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.base.function.service.ILabFunctionService;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.common.workflow.service.ILabWfConfigService;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessService;
import cn.labsoft.labos.common.workflow.vo.LabWfConfigVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessVo;
/**
 * 流程配置操作类
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Controller
@Scope("prototype")
public class LabWfConfigAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabWfConfigService labWfConfigService;
	private ILabWfProcessService labWfProcessService;
	private ILabFunctionService labFunctionService;
	/**
	 * 流程配置Service注入
	 * @param labWfConfigService
	 */
	@Resource
	public void setLabWfConfigService(ILabWfConfigService labWfConfigService) {
		this.labWfConfigService = labWfConfigService;
	}
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
	
	private LabWfConfigVo labWfConfigVo;
	private LabWfProcessVo labWfProcessVo;
	
	
	public LabWfProcessVo getLabWfProcessVo() {
		return labWfProcessVo;
	}
	public void setLabWfProcessVo(LabWfProcessVo labWfProcessVo) {
		this.labWfProcessVo = labWfProcessVo;
	}
	public LabWfConfigVo getLabWfConfigVo() {
		return labWfConfigVo;
	}
	public void setLabWfConfigVo(LabWfConfigVo labWfConfigVo) {
		this.labWfConfigVo = labWfConfigVo;
	}
	/**
	 * 流程定义-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabWfConfig() throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		pageResult=labWfConfigService.getLabWfConfigPR(labWfConfigVo, pageResult);
		return LIST;
	}
	/**
	 * 流程定义-新增页面方法
	 * @throws GlobalException
	 */
	public String preAddLabWfConfig() throws GlobalException{
		if(null==labWfConfigVo){
			labWfConfigVo=new LabWfConfigVo();
		}
		LabFunctionVo funVo=labFunctionService.getLabFunction(labWfConfigVo.getFunId());
		labWfConfigVo.setFunId(funVo.getId());
		labWfConfigVo.setFunName(funVo.getName());
		labWfConfigVo.setCode(funVo.getWfcode());
		LabWfProcessVo labWfProcessVo=new LabWfProcessVo();
		labWfProcessVo.setFunId(funVo.getId());
		List<LabWfProcessVo> processList=labWfProcessService.getWfProcessList(labWfProcessVo);
		setAttribute("processList", processList);
		return PREADD;
	}
	/**
	 * 流程定义-新增方法
	 * @throws GlobalException
	 */
	public String addLabWfConfig() throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		labWfConfigVo=labWfConfigService.addLabWfConfig(labWfConfigVo);
		return ADD;
	}
	/**
	 * 流程定义-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabWfConfig() throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		labWfConfigVo=labWfConfigService.getLabWfConfig(labWfConfigVo.getId());
		
		
		LabWfProcessVo labWfProcessVo=new LabWfProcessVo();
		labWfProcessVo.setFunId(labWfConfigVo.getFunId());
		List<LabWfProcessVo> processList=labWfProcessService.getWfProcessList(labWfProcessVo);
		setAttribute("processList", processList);
		return PREUPDATE;
	}
	/**
	 * 流程定义-修改方法
	 * @throws GlobalException
	 */
	public String updateLabWfConfig() throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		labWfConfigService.updateLabWfConfig(labWfConfigVo);
		return UPDATE;
	}
	/**
	 * 流程定义-查询页面方法
	 * @throws GlobalException
	 */
	public String showLabWfConfig()  throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		labWfConfigVo=labWfConfigService.getLabWfConfig(labWfConfigVo.getId());
		return SHOW;
	}
	/**
	 * 流程定义-删除页面方法
	 * @throws GlobalException
	 */
	public String deleteLabWfConfig() throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		labWfConfigService.deleteLabWfConfig(labWfConfigVo.getIds());
		return DELETE;
	}
	/**
	 * 流程定义-假删除页面方法
	 * @throws GlobalException
	 */
	public String updateLabWfConfig4Del()throws GlobalException{
		if(null==labWfConfigVo)labWfConfigVo=new LabWfConfigVo();
		labWfConfigService.updateLabWfConfig4Del(labWfConfigVo.getIds());
		return DELETE;
	}
//	public String isExist4Code()throws GlobalException, IOException{
//		if (!StrUtils.isNull(labWfConfigVo)){
//			boolean flag = labWfConfigService.isExist4Code(labWfConfigVo.getId(), labWfConfigVo.getCode());
//			outPutString(flag + "");
//		}
//		return NONE;
//	}
	/**
	 * 流程定义-ajax选择方法
	 * @throws GlobalException
	 */
	public String ajaxLabWfProcess4Select()throws GlobalException, IOException{
		if (!StrUtils.isNull(labWfProcessVo)){
			List<LabWfProcessVo> processList=labWfProcessService.getWfProcessList(labWfProcessVo);
			ajax(processList);
		}
		return NONE;
	}
	
	
	
}
