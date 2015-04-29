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
import cn.labsoft.labos.source.appara.service.ILabApparaProvService;
import cn.labsoft.labos.source.appara.vo.LabApparaProvVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
@Controller
@Scope("prototype")
public class LabApparaProvAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaProvService labApparaProvService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaVo labApparaVo;
	private LabApparaProvVo labApparaProvVo;

	@Resource
	public void setLabApparaProvService(ILabApparaProvService labApparaProvService) {
		this.labApparaProvService = labApparaProvService;
	}
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
	
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}
	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	public LabApparaProvVo getLabApparaProvVo() {
		return labApparaProvVo;
	}
	public void setLabApparaProvVo(LabApparaProvVo labApparaProvVo) {
		this.labApparaProvVo = labApparaProvVo;
	}
	/**
	 * 故障 清单
	 * @throws GlobalException 
	 * */
	public String listApparaProv4View() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		pageResult = labApparaProvService.getLabApparaProvPR(labApparaProvVo, pageResult);
		return LIST;
	}
	/**
	  * 故障 列表
	  * */
	public String listApparaProv() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaProvVo.getStatus()){
			labApparaProvVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labApparaProvService.getLabApparaProvPR(labApparaProvVo, pageResult);
		return LIST;
	}
	/**
	 * 查看
	 * @throws GlobalException 
	 * */
	public String showLabApparaProv() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvVo = labApparaProvService.getLabApparaProv(labApparaProvVo);
		return SHOW;
	}
	/**
	 * 故障 新增
	 * */
	public String preAddLabApparaProv() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvVo.setAuditName(getSessionContainer().getUserName());
		labApparaProvVo.setCheckName(getSessionContainer().getUserName());
		return PREADD;
	}
	/**
	 * 故障 新增 保存
	 * */
	public String addLabApparaProv() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvVo=labApparaProvService.addLabApparaProv(labApparaProvVo);
		if(LabWfConstant.BUS_GO.equals(labApparaProvVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 故障修改
	 * */
	public String preUpdateAppProv() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvVo = labApparaProvService.getLabApparaProv(labApparaProvVo);
		labApparaVo = labApparaProvService.getLabApparaVoById(labApparaProvVo);
		return PREUPDATE;
	}
	/**
	 * 故障修改 保存
	 * */
	public String updateLabApparaProv() throws GlobalException{
		if(null==labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvService.updateLabApparaProv(labApparaProvVo);
		if(LabWfConstant.BUS_GO.equals(labApparaProvVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 故障申请
	 * */
	public String listApparaProvAudit()throws GlobalException{
		if(null==labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaProvVo.getStatus()){
			labApparaProvVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaProvService.getLabApparaProvPR(labApparaProvVo, pageResult);
		return LIST;
	}
	/**
	 * 故障审核
	 * @throws GlobalException 
	 * */
	public String preUpdateApparaProv4Audit() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvVo = labApparaProvService.getLabApparaProv(labApparaProvVo);
		labApparaProvVo.setDate2(DateUtils.getCurrDateStr());
		labApparaProvVo.setUser2(getSessionContainer().getUserName());
		return PREUPDATE;
	}
	/**
	 * 审核
	 * @throws GlobalException 
	 * */
	public String updateAppProvAudit() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvService.updateLabApparaProv(labApparaProvVo);
		return UPDATE;
	}
	/**
	 * 故障 审批 列表
	 * */
	public String listApparaProvPaudit()throws GlobalException{
		if(null==labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaProvVo.getStatus()){
			labApparaProvVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaProvService.getLabApparaProvPR(labApparaProvVo, pageResult);
		return LIST;
	}
	 /**
	  * 故障 审批
	 * @throws GlobalException 
	  * */
	 public String preUpdateApparaProv4Paudit() throws GlobalException{
			if(null == labApparaProvVo){
				labApparaProvVo = new LabApparaProvVo();
			}
			labApparaProvVo = labApparaProvService.getLabApparaProv(labApparaProvVo);
			labApparaProvVo.setDate3(DateUtils.getCurrDateStr());
			labApparaProvVo.setUser3(getSessionContainer().getUserName());
			return PREUPDATE;
	}
	/**
	 * 审批
	 * @throws GlobalException 
	 * */
	public String updateLabAppProvPaudit() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvService.updateLabApparaProv(labApparaProvVo);
		return UPDATE;
	}

	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printApparaProv() throws GlobalException{
		if(null == labApparaProvVo){
			labApparaProvVo = new LabApparaProvVo();
		}
		labApparaProvVo=labApparaProvService.getLabApparaProv(labApparaProvVo);
		return "print";
	}
}
