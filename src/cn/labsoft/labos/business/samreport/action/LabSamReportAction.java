package cn.labsoft.labos.business.samreport.action;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.business.samreport.service.ILabSamReportService;
import cn.labsoft.labos.business.samreport.vo.LabSamReportVo;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.vo.LabReportVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
/**
 *报告管理操作类
 *@author Quinn
 *@version 8.0
 *@since 8.0
 */
@Controller
@Scope("prototype")
public class LabSamReportAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabSamReportService labSamReportService;
	private ILabWfProcessInsService labWfProcessInsService;
	private ILabReportService labReportService;
	/**
	 * 流程管理Service注入
	 * @param labWfProcessInsService
	 */
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
	/**
	 * 报告模版Service注入
	 * @param labReportService
	 */
	@Resource
	public void setLabReportService(ILabReportService labReportService) {
		this.labReportService = labReportService;
	}
	/**
	 * 报告管理Service注入
	 * @param labSamReportService
	 */
	@Resource
	public void setLabSamReportService(ILabSamReportService labSamReportService) {
		this.labSamReportService = labSamReportService;
	}
	private LabSamReportVo labSamReportVo;
	private LabReportVo labReportVo;
	
	public LabSamReportVo getLabSamReportVo() {
		return labSamReportVo;
	}
	public void setLabSamReportVo(LabSamReportVo labSamReportVo) {
		this.labSamReportVo = labSamReportVo;
	}
	
	public LabReportVo getLabReportVo() {
		return labReportVo;
	}
	public void setLabReportVo(LabReportVo labReportVo) {
		this.labReportVo = labReportVo;
	}
	/**
	 * 报告管理-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabSamReport() throws GlobalException{
		if(null==labSamReportVo){
			labSamReportVo=new LabSamReportVo();
			labSamReportVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamReportService.getLabSamReportPR(labSamReportVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 报告管理-修改页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabSamReport() throws GlobalException{
		if(null==labSamReportVo){
			return ERROR;
		}
		labSamReportVo=labSamReportService.getLabSamReport(labSamReportVo.getId());
		
		// 获取报告模版信息
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
			labReportVo.setBusId(getSessionContainer().getFunId());
		}
		List<LabReportVo> reportTempList = labReportService.getLabReportList(labReportVo);
		setAttribute("tempList", reportTempList);
		return PREUPDATE;
	}
	/**
	 * 报告管理-修改方法
	 * @throws GlobalException
	 */
	public String updateLabSamReport() throws GlobalException{
		labSamReportService.updateLabSamReport(labSamReportVo);
		if(null!=labSamReportVo.getAuditResult()&&labSamReportVo.getAuditResult().equals("1")){
			return UPDATE;
		}else{
			return "save";
		}
	}
	/**
	 * 报告管理-查看页面方法
	 * @throws GlobalException
	 */
	public String showLabSamReport()  throws GlobalException{
		if(null==labSamReportVo)labSamReportVo=new LabSamReportVo();
		labSamReportVo=labSamReportService.getLabSamReport(labSamReportVo.getId());
		return SHOW;
	}
	/**
	 * 报告管理-删除方法
	 * @throws GlobalException
	 */
	public String deleteLabSamReport() throws GlobalException{
		if(null==labSamReportVo)labSamReportVo=new LabSamReportVo();
		labSamReportService.deleteLabSamReport(labSamReportVo.getIds());
		return DELETE;
	}
	/**
	 * 报告管理-假删除方法
	 * @throws GlobalException
	 */
	public String updateLabSamReport4Del()throws GlobalException{
		if(null==labSamReportVo)labSamReportVo=new LabSamReportVo();
		labSamReportService.updateLabSamReport4Del(labSamReportVo.getIds());
		return DELETE;
	}
	/**
	 * 报告生成
	 */
	public String addLabReport4Bus() throws GlobalException {
		labSamReportVo=labSamReportService.getLabSamReport(labReportVo.getBusInsId());
		// 获取报告模版信息
		labReportVo = labReportService.addLabReport4Bus(labReportVo);
		labSamReportVo.setReportTempId(labReportVo.getId());
		labSamReportVo.setReportPath(labReportVo.getPath());
		labSamReportService.updateLabSamReport4Report(labSamReportVo);
		return ADD;
	}
	/**
	 * 报告查看
	 */
	public String showLabReport4Bus() throws GlobalException {
		labSamReportVo=labSamReportService.getLabSamReport(labReportVo.getBusInsId());
		// 获取报告模版信息
		labReportVo = labReportService.getLabReport4Bus(labReportVo);
		labReportVo.setEditType("1");
		return SHOW;
	}
	/**
	 * 报告管理-审核列表页面
	 * @throws GlobalException
	 */
	public String listLabSamReport4Audit() throws GlobalException{
		if(null==labSamReportVo){
			labSamReportVo=new LabSamReportVo();
			labSamReportVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamReportService.getLabSamReportPR4Audit(labSamReportVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 报告管理-审核页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabSamReport4Audit() throws GlobalException{
		if(null==labSamReportVo){
			return ERROR;
		}
		labSamReportVo=labSamReportService.getLabSamReport(labSamReportVo.getId());
		return PREUPDATE;
	}
	/**
	 *  报告管理-审核方法
	 * @throws GlobalException
	 */
	public String updateLabSamReport4Audit() throws GlobalException{
		labSamReportService.updateLabSamReport4Audit(labSamReportVo);
		return UPDATE;
	}
	/**
	 *  报告管理-审批列表页面方法
	 * @throws GlobalException
	 */
	public String listLabSamReport4Approve() throws GlobalException{
		if(null==labSamReportVo){
			labSamReportVo=new LabSamReportVo();
			labSamReportVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamReportService.getLabSamReportPR4Audit(labSamReportVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 报告管理-审批页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabSamReport4Approve() throws GlobalException{
		labSamReportVo=labSamReportService.getLabSamReport(labSamReportVo.getId());
		return PREUPDATE;
	}
	/**
	 * 报告管理-审批方法
	 * @throws GlobalException
	 */
	public String updateLabSamReport4Approve() throws GlobalException{
		labSamReportService.updateLabSamReport4Audit(labSamReportVo);
		return UPDATE;
	}
	/**
	 * 报告管理-签发列表页面方法
	 * @return
	 * @throws GlobalException
	 */
	public String listLabSamReport4Sign() throws GlobalException{
		if(null==labSamReportVo){
			labSamReportVo=new LabSamReportVo();
			labSamReportVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamReportService.getLabSamReportPR4Audit(labSamReportVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 报告管理-签发页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabSamReport4Sign() throws GlobalException{
		labSamReportVo=labSamReportService.getLabSamReport(labSamReportVo.getId());
		return PREUPDATE;
	}
	/**
	 * 报告管理-签发方法
	 * @throws GlobalException
	 */
	public String updateLabSamReport4Sign() throws GlobalException{
		labSamReportService.updateLabSamReport4Audit(labSamReportVo);
		return UPDATE;
	}
	/**
	 * 报告管理-发送列表页面方法
	 * @throws GlobalException
	 */
	public String listLabSamReport4Send() throws GlobalException{
		if(null==labSamReportVo){
			labSamReportVo=new LabSamReportVo();
			labSamReportVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamReportService.getLabSamReportPR4Send(labSamReportVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 报告管理-发送页面方法
	 * @throws GlobalException
	 */
	public String preUpdateLabSamReport4Send() throws GlobalException{
		labSamReportVo=labSamReportService.getLabSamReport4Send(labSamReportVo.getId());
		return PREUPDATE;
	}
	/**
	 * 报告管理-发送方法
	 * @throws GlobalException
	 */
	public String updateLabSamReport4Send() throws GlobalException{
		labSamReportService.updateLabSamReport4Send(labSamReportVo);
		if(StrUtils.isBlankOrNull(labSamReportVo.getAuditResult())){
			return "save";
		}else{
			return UPDATE;
		}
	}
}
