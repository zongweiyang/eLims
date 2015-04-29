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
import cn.labsoft.labos.source.appara.service.ILabApparaEditService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaEditVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
@Controller
@Scope("prototype")
public class LabApparaEditAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaEditService labApparaEditService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaVo labApparaVo;
	private LabApparaEditVo labApparaEditVo;
	@Resource
	public void setLabApparaEditService(ILabApparaEditService labApparaEditService) {
		this.labApparaEditService = labApparaEditService;
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
	 * 仪器 维修 清单
	 * */
	public String listLabApparaEdit4View() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		pageResult = labApparaEditService.getLabApparaEditPR(labApparaEditVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器维修列表
	 * */
	public String listLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaEditVo.getStatus()){
			labApparaEditVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaEditService.getLabApparaEditPR(labApparaEditVo, pageResult);
		return LIST;
	}
	/**
	 * 维修  查看
	 * @throws GlobalException 
	 * */
	public String showLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditVo = labApparaEditService.getLabApparaEdit(labApparaEditVo);
		return SHOW;
	}
	/**
	 * 维修 新增 
	 * */
	public String preAddLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		return PREADD;
	}
	/**
	 * 维修 新增  保存
	 * */
	public String addLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditVo=labApparaEditService.addLabApparaEdit(labApparaEditVo);
		if(LabWfConstant.BUS_GO.equals(labApparaEditVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 维修 修改
	 * */
	public String preUpdateLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditVo = labApparaEditService.getLabApparaEdit(labApparaEditVo);
		return PREUPDATE;
	}
	
	/**
	 * 维修 修改 保存
	 * */
	public String updateLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditService.updateLabApparaEdit(labApparaEditVo);
		if(LabWfConstant.BUS_GO.equals(labApparaEditVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 维修 审核 列表
	 * */
	public String listLabApparaEditAudit()throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaEditVo.getStatus()){
			labApparaEditVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaEditService.getLabApparaEditPR(labApparaEditVo, pageResult);
		return LIST;
	}
	/**
	 * 维修 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateLabApparaEditAudit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditVo = labApparaEditService.getLabApparaEdit(labApparaEditVo);
		labApparaEditVo.setUser2(getSessionContainer().getUserName());
		labApparaEditVo.setDate2(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 审核 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaEditAudit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditService.updateLabApparaEdit(labApparaEditVo);
		return UPDATE;
	}
	/**
	 * 维修 审批 列表
	 * */
	public String listLabApparaEditPaudit()throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaEditVo.getStatus()){
			labApparaEditVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaEditService.getLabApparaEditPR(labApparaEditVo, pageResult);
		return LIST;
	}
	/**
	 * 维修 审批
	 * @throws GlobalException 
	 * */
	public String preUpdateLabApparaEditPaudit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditVo = labApparaEditService.getLabApparaEdit(labApparaEditVo);
		labApparaEditVo.setUser3(getSessionContainer().getUserName());
		labApparaEditVo.setDate3(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 维修 审批 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaEditPaudit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditService.updateLabApparaEdit(labApparaEditVo);
		return UPDATE;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printLabApparaEdit() throws GlobalException{
		if(null == labApparaEditVo){
			labApparaEditVo = new LabApparaEditVo();
		}
		labApparaEditVo = labApparaEditService.getLabApparaEdit(labApparaEditVo);
		return "print";
	}
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	public LabApparaEditVo getLabApparaEditVo() {
		return labApparaEditVo;
	}
	public void setLabApparaEditVo(LabApparaEditVo labApparaEditVo) {
		this.labApparaEditVo = labApparaEditVo;
	}
}
