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
import cn.labsoft.labos.source.appara.service.ILabApparaService;
import cn.labsoft.labos.source.appara.service.ILabApparaTestService;
import cn.labsoft.labos.source.appara.vo.LabApparaTestVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Controller
@Scope("prototype")
public class LabApparaTestAction extends BaseAction {
	private ILabApparaTestService labApparaTestService;
	private ILabApparaService labApparaService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabApparaTestVo labApparaTestVo;
	private LabApparaVo labApparaVo;
	@Resource
	public void setLabApparaTestService(ILabApparaTestService labApparaTestService) {
		this.labApparaTestService = labApparaTestService;
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
	 * 仪器 检定 清单
	 * @throws GlobalException 
	 * */
	public String listLabApparaTest4View() throws GlobalException{
		if (null == labApparaTestVo){
			labApparaTestVo = new LabApparaTestVo();
		}
		pageResult = labApparaTestService.getLabApparaTestList(labApparaTestVo,pageResult);
		return LIST;
	}
	/**
	 * 检定 列表
	 * */
	public String listLabApparaTest() throws GlobalException {
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		if(labApparaVo!=null&&!StrUtils.isBlankOrNull(labApparaVo.getId())){
			labApparaTestVo.setAppId(labApparaVo.getId());
		}
		if(null==labApparaTestVo.getStatus()){
			labApparaTestVo.setStatus(getSessionContainer().getFunId());
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		pageResult = labApparaTestService.getLabApparaTestList(labApparaTestVo,pageResult);
		return LIST;
	}
	/**
	 * 检定 查看
	 * @throws GlobalException 
	 * */
	public String showLabApparaTest() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestVo=labApparaTestService.getLabApparaTestById(labApparaTestVo.getId());
		return SHOW;
	}
	/**
	 * 检定 新增
	 * */
	public String preAddLabAppTest() throws GlobalException{
 		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		return PREADD;
	}
	/**
	 * 检定 新增 保存
	 * */
	public String addLabApparaTest() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestVo = labApparaTestService.addLabApparaTest(labApparaTestVo);
		if(LabWfConstant.BUS_GO.equals(labApparaTestVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}
	/**
	 * 仪器检定修改
	 * */
	public String preUpdateLabApparaTest() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestVo = labApparaTestService.getLabApparaTestById(labApparaTestVo.getId());
		labApparaVo = labApparaService.getLabAppara(labApparaTestVo.getAppId());
		return PREUPDATE;
	}
	/**
	 * 检定 修改 保存
	 * */
	public String updateLabApparaTest() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestService.updateLabApparaTest(labApparaTestVo);
		if(LabWfConstant.BUS_GO.equals(labApparaTestVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}
	/**
	 * 检定 审核 列表
	 * */
	public String listLabAppTestAudit()throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		if(null==labApparaTestVo.getStatus()){
			labApparaTestVo.setStatus(getSessionContainer().getFunId());
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		pageResult = labApparaTestService.getLabApparaTestList(labApparaTestVo, pageResult);
		return LIST;
	}
	/**
	 * 检定 审核
	 * @throws GlobalException 
	 * */
	public String preUpdateLabAppTestAudit() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestVo=labApparaTestService.getLabApparaTestById(labApparaTestVo.getId());
	    labApparaTestVo.setDate2(DateUtils.getCurrDateTimeStr());
	    labApparaTestVo.setUser2(getSessionContainer().getUserName());
		return PREUPDATE;
	}
	/**
	 * 检定 审批 列表
	 * */
	public String listLabAppTestPaudit()throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		if(null==labApparaTestVo.getStatus()){
			labApparaTestVo.setStatus(getSessionContainer().getFunId());
		}
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		pageResult = labApparaTestService.getLabApparaTestList(labApparaTestVo, pageResult);
		return LIST;
	}
	/**
	 * 检定 审批
	 * @throws GlobalException 
	 * */
	public String preUpdateLabAppTestPaudit() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestVo=labApparaTestService.getLabApparaTestById(labApparaTestVo.getId());
		labApparaTestVo.setDate3(DateUtils.getCurrDateTimeStr());
	    labApparaTestVo.setUser3(getSessionContainer().getUserName());
		return PREUPDATE;
	}
	
	/**
	 * 检定 审核 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaTestAudit() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestService.updateLabApparaTest(labApparaTestVo);
		return UPDATE;
	}
	/**
	 * 检定 审批 保存
	 * @throws GlobalException 
	 * */
	public String updateLabApparaTestPaudit() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestService.updateLabApparaTest(labApparaTestVo);
		return UPDATE;
	}
	/**
	 * 打印
	 * @throws GlobalException 
	 * */
	public String printLabApparaTest() throws GlobalException{
		if (null == labApparaTestVo) {
			labApparaTestVo = new LabApparaTestVo();
		}
		labApparaTestVo=labApparaTestService.getLabApparaTestById(labApparaTestVo.getId());
		return "print";
	}
	public LabApparaTestVo getLabApparaTestVo() {
		return labApparaTestVo;
	}
	public void setLabApparaTestVo(LabApparaTestVo labApparaTestVo) {
		this.labApparaTestVo = labApparaTestVo;
	}
	public LabApparaVo getLabApparaVo() {
		return labApparaVo;
	}
	public void setLabApparaVo(LabApparaVo labApparaVo) {
		this.labApparaVo = labApparaVo;
	}
}
