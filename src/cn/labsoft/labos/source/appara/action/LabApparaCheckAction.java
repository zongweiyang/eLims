package cn.labsoft.labos.source.appara.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.service.ILabApparaCheckService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaCheckVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
@Controller
@Scope("prototype")
public class LabApparaCheckAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabApparaCheckService labApparaCheckService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaVo labApparaVo;
	private LabApparaCheckVo labApparaCheckVo;
	
	public LabApparaCheckVo getLabApparaCheckVo() {
		return labApparaCheckVo;
	}

	public void setLabApparaCheckVo(LabApparaCheckVo labApparaCheckVo) {
		this.labApparaCheckVo = labApparaCheckVo;
	}
	@Resource
	public void setLabApparaCheckService(
			ILabApparaCheckService labApparaCheckService) {
		this.labApparaCheckService = labApparaCheckService;
	}
	@Resource
	public void setLabApparaService(ILabApparaService labApparaService) {
		this.labApparaService = labApparaService;
	}
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}

	/**
	 * 核查 审核 列表
	 * */
	public String listLabAppCheckAudit()throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaCheckVo.getStatus()){
			labApparaCheckVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaCheckService.getLabApparaCheckPR(labApparaCheckVo, pageResult);
		return LIST;
	}
	/**
	 * 核查 查看
	 * @throws GlobalException 
	 * */
	public String showLabApparaCheck() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckVo=labApparaCheckService.getLabApparaCheck(labApparaCheckVo);
		return SHOW;
	}
	/**
	 * 核查预新增
	 * */
	public String preAddLabApparaCheck() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		return PREADD;
	}
	/**
	 * 核查 新增 保存
	 * */
	public String addLabApparaCheck() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckVo=labApparaCheckService.addLabApparaCheck(labApparaCheckVo);
		if(LabWfConstant.BUS_GO.equals(labApparaCheckVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 核查预修改
	 * */
	public String preUpdateLabApparaCheck() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckVo = labApparaCheckService.getLabApparaCheck(labApparaCheckVo);
		labApparaVo=labApparaService.getLabAppara(labApparaCheckVo.getAppId());
		return PREUPDATE;
	}
	/**
	 * 审核 新增 保存
	 * */
	public String updateLabApparaCheck() throws GlobalException{
		if(null==labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckService.updateLabApparaCheck(labApparaCheckVo);
		if(LabWfConstant.BUS_GO.equals(labApparaCheckVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 核查 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateLabAppCheckAudit() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckVo=labApparaCheckService.getLabApparaCheck(labApparaCheckVo);
		labApparaCheckVo.setUser2(getSessionContainer().getUserName());
		labApparaCheckVo.setDate2(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 核查 审核 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaCheckAudit() throws GlobalException{
		if(null==labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckService.updateLabApparaCheck(labApparaCheckVo);
		return UPDATE;
	}
	/**
	 * 核查 审批 列表
	 * */
	public String listLabAppCheckPaudit()throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
			pageResult.setOrder(PageResult.ORDER_ASC);
			pageResult.setOrderColumn("verDate");
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaCheckVo.getStatus()){
			labApparaCheckVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaCheckService.getLabApparaCheckPR(labApparaCheckVo, pageResult);
		return LIST;
	}
	/**
	 * 核查 审批
	 * @throws GlobalException 
	 * */
	public String preUpdateLabAppCheckPaudit() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckVo=labApparaCheckService.getLabApparaCheck(labApparaCheckVo);
		labApparaCheckVo.setUser3(getSessionContainer().getUserName());
		labApparaCheckVo.setDate3(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 核查 审批 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaCheckPaudit() throws GlobalException{
		if(null==labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckService.updateLabApparaCheck(labApparaCheckVo);
		return UPDATE;
	}
	public String listLabAppCheck() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaCheckVo.getStatus()){
			labApparaCheckVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaCheckService.getLabApparaCheckPR(labApparaCheckVo, pageResult);
		return LIST;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printLabApparaCheck() throws GlobalException{
		if(null == labApparaCheckVo){
			labApparaCheckVo = new LabApparaCheckVo();
		}
		labApparaCheckVo=labApparaCheckService.getLabApparaCheck(labApparaCheckVo);
		return "print";
	}
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
}
