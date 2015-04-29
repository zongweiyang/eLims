package cn.labsoft.labos.source.appara.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.service.ILabApparaAcceptService;
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.vo.LabApparaAcceptVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
/**
 * 仪器验收控制层
 * @author Administrator
 *
 */
@Controller
@Scope("prototype")
public class LabApparaAcceptAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ILabApparaAcceptService labApparaAcceptService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaAcceptVo labApparaAcceptVo;
	private LabApparaVo labApparaVo;
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}
	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
	public LabApparaAcceptVo getLabApparaAcceptVo() {
		return labApparaAcceptVo;
	}
	public void setLabApparaAcceptVo(LabApparaAcceptVo labApparaAcceptVo) {
		this.labApparaAcceptVo = labApparaAcceptVo;
	}
	@Resource
	public void setLabApparaAcceptService(
			ILabApparaAcceptService labApparaAcceptService) {
		this.labApparaAcceptService = labApparaAcceptService;
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
	 * 仪器验收 清单 列表
	 * @throws GlobalException 
	 * */
	public String listLabAppAccept4View() throws GlobalException{
		if(null == labApparaAcceptVo){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		pageResult=labApparaAcceptService.getLabApparaAcceptPR(labApparaAcceptVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器验收 列表
	 * 
	 * */
	public String listLabAppCheckAudit()throws GlobalException{
		if(null == labApparaAcceptVo){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaAcceptVo.getStatus()){
			labApparaAcceptVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaAcceptService.getLabApparaAcceptPR(labApparaAcceptVo, pageResult);
		return LIST;
	}
	/**
	 * 仪器验收 查看
	 * @throws GlobalException 
	 * */
	public String showLabAppAccept() throws GlobalException{
		if(null == labApparaAcceptVo){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptVo = labApparaAcceptService.getLabApparaAccept(labApparaAcceptVo);
		return SHOW;
	}
	/**
	 * 仪器 验收
	 * @throws GlobalException 
	 * */
	public String preUpdateApparaAccept() throws GlobalException{
		if(null == labApparaAcceptVo){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptVo = labApparaAcceptService.getLabApparaAccept(labApparaAcceptVo);
		return PREUPDATE;
	}
	/**
	 * 仪器 验收 修改保存
	 * @throws GlobalException 
	 * */
	public String updateLabAppAccept() throws GlobalException{
		if(null == labApparaAcceptVo){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptService.updateLabApparaAccept(labApparaAcceptVo);
		return UPDATE;
	}
	/**
	 * 验收 审核 列表
	 * */
	public String listAppAcceptAudit()throws GlobalException{
  		if(labApparaAcceptVo==null){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
  		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaAcceptVo.getStatus()){
			labApparaAcceptVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaAcceptService.getLabApparaAcceptPR(labApparaAcceptVo, pageResult);
		return LIST;
	}
	/**
	 * 验收 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateAppAcceptAudit() throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptVo = labApparaAcceptService.getLabApparaAccept(labApparaAcceptVo);
		labApparaAcceptVo.setUser2(getSessionContainer().getUserName());
		labApparaAcceptVo.setDate2(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 审核 保存
	 * @throws GlobalException 
	 * */
	public String updateLabAppAcceptAudit() throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptService.updateLabAppAccept(labApparaAcceptVo);
		return UPDATE;
	}
	/**
	 * 验收 审批 列表
	 * */
	public String listAppAcceptPaudit()throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if(null==labApparaAcceptVo.getStatus()){
			labApparaAcceptVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labApparaAcceptService.getLabApparaAcceptPR(labApparaAcceptVo, pageResult);
		return LIST;
	}
	/**
	 * 验收 审批
	 * @throws GlobalException 
	 * */
	public String preUpdateAppAcceptPaudit() throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptVo = labApparaAcceptService.getLabApparaAccept(labApparaAcceptVo);
		labApparaAcceptVo.setUser3(getSessionContainer().getUserName());
		labApparaAcceptVo.setDate3(DateUtils.getCurrDateStr());
		return PREUPDATE;
	}
	/**
	 * 审批
	 * @throws GlobalException 
	 * */
	public String updateLabAppAcceptPaudit() throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo = new LabApparaAcceptVo();
		}
		labApparaAcceptService.updateLabAppAccept(labApparaAcceptVo);
		return UPDATE;
	}
	/**
	 * 获取 验收信息
	 * */
	public String labApparaAcceptShow() throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo=new LabApparaAcceptVo();
		}
		if(labApparaVo==null){
			labApparaVo=new LabApparaVo();
		}
		labApparaVo=labApparaService.getLabAppara(labApparaVo.getId());
		if(labApparaVo!=null){
			labApparaAcceptVo.setAppId(labApparaVo.getId());
		}
		labApparaAcceptVo=labApparaAcceptService.getLabApparaAccept(labApparaAcceptVo);
		return SHOW;
	}
	/**
	 * 验收打印
	 * @throws GlobalException 
	 * */
	public String printApparaAccept() throws GlobalException{
		if(labApparaAcceptVo==null){
			labApparaAcceptVo=new LabApparaAcceptVo();
		}
		labApparaAcceptVo=labApparaAcceptService.getLabApparaAccept(labApparaAcceptVo);
		return "print";
	}
}
