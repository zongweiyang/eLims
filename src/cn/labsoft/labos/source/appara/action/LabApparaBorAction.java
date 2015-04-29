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
import cn.labsoft.labos.source.appara.service.ILabApparaBorService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaBorVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
@Controller
@Scope("prototype")
public class LabApparaBorAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private ILabApparaBorService labApparaBorService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaBorVo labApparaBorVo;
	private LabApparaVo labApparaVo;

	public LabApparaBorVo getLabApparaBorVo() {
		return labApparaBorVo;
	}

	public void setLabApparaBorVo(LabApparaBorVo labApparaBorVo) {
		this.labApparaBorVo = labApparaBorVo;
	}

	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}

	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	@Resource
	public void setLabApparaBorService(ILabApparaBorService labApparaBorService) {
		this.labApparaBorService = labApparaBorService;
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
	 * 仪器 清单 查看
	 * @throws GlobalException 
	 * */
	public String listLabApparaBor4View() throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		pageResult = labApparaBorService.getLabApparaBorPR(labApparaBorVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器借用 列表
	 * */
	public String listLabApparaBor() throws GlobalException {
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaBorVo.getStatus()){
			labApparaBorVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaBorService.getLabApparaBorPR(labApparaBorVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器 借用 查看
	 * */
	public String showLabApparaBor() throws GlobalException {
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo = labApparaBorService.getLabApparaBor(labApparaBorVo);
		return SHOW;
	}
	/**
	 * 借用预新增
	 * */
	public String preAddLabApparaBor() throws GlobalException {
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		return PREADD;
	}
	/**
	 * 借用 新增 保存
	 * */
	public String addLabApparaBor() throws GlobalException {
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo=labApparaBorService.addLabApparaBor(labApparaBorVo);
		if(LabWfConstant.BUS_GO.equals(labApparaBorVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 仪器 借用 修改
	 * */
	public String preUpdateLabApparaBor() throws GlobalException {
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo = labApparaBorService.getLabApparaBor(labApparaBorVo);
		return PREUPDATE;
	}
	/**
	 * 借用 修改 保存
	 * */
	public String updateLabApparaBor() throws GlobalException {
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo = labApparaBorService.updateLabApparaBor(labApparaBorVo);
		if(LabWfConstant.BUS_GO.equals(labApparaBorVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 借用 审核 列表
	 * */
	public String listLabApparaBorAudit()throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaBorVo.getStatus()){
			labApparaBorVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaBorService.getLabApparaBorPR(labApparaBorVo, pageResult);
		return LIST;
	}
	/**
	 * 借用 审核 
	 * @throws GlobalException 
	 * */
	public String preUpdateLabApparaBorAudit() throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo = labApparaBorService.getLabApparaBor(labApparaBorVo);
		labApparaBorVo.setUser2(getSessionContainer().getUserName());
		labApparaBorVo.setDate2(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 审核 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaBorAudit() throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo=labApparaBorService.updateLabApparaBor(labApparaBorVo);
		return UPDATE;
	}
	/**
	 * 借用 审批 列表
	 * */
	public String listLabApparaBorPaudit()throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaBorVo.getStatus()){
			labApparaBorVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaBorService.getLabApparaBorPR(labApparaBorVo, pageResult);
		return LIST;
	}
	/**
	 * 借用 审批 
	 * @throws GlobalException 
	 * */
	public String preUpdateLabApparaBorPaudit() throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo = labApparaBorService.getLabApparaBor(labApparaBorVo);
		labApparaBorVo.setUser3(getSessionContainer().getUserName());
		labApparaBorVo.setDate3(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 审批 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaBorPaudit() throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo=labApparaBorService.updateLabApparaBor(labApparaBorVo);
		return UPDATE;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printLabApparaBor() throws GlobalException{
		if (labApparaBorVo == null){
			labApparaBorVo = new LabApparaBorVo();
		}
		labApparaBorVo = labApparaBorService.getLabApparaBor(labApparaBorVo);
		return "print";
	}
}
