package cn.labsoft.labos.business.science.action;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.business.sample.service.ILabSampRegisterService;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.business.science.service.ILabSciFundsService;
import cn.labsoft.labos.business.science.service.ILabSciProcessService;
import cn.labsoft.labos.business.science.service.ILabSciScienceService;
import cn.labsoft.labos.business.science.vo.LabSciAchievementVo;
import cn.labsoft.labos.business.science.vo.LabSciFundsVo;
import cn.labsoft.labos.business.science.vo.LabSciProcessVo;
import cn.labsoft.labos.business.science.vo.LabSciScienceVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.constants.Constants_Business;

@Controller
@Scope("prototype")
public class LabSciProcessAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabSciProcessService labSciProcessService;
	private ILabSciScienceService labSciScienceService;
	private ILabWfProcessInsService labWfProcessInsService;
	private ILabSciFundsService labSciFundsService;
	private ILabCodeService labCodeService;
	private ILabSampRegisterService labSampRegisterService;
	private ILabSamTypeService labSamTypeService;
	private ILabUploadService labUploadService;
	@Resource
	public void setLabSciProcessService(ILabSciProcessService labSciProcessService) {
		this.labSciProcessService = labSciProcessService;
	}
	@Resource
	public void setLabSampRegisterService(
			ILabSampRegisterService labSampRegisterService) {
		this.labSampRegisterService = labSampRegisterService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabSamTypeService(ILabSamTypeService labSamTypeService) {
		this.labSamTypeService = labSamTypeService;
	}

	@Resource
	public void setLabSciScienceService(ILabSciScienceService labSciScienceService) {
		this.labSciScienceService = labSciScienceService;
	}
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
	@Resource
	public void setLabSciFundsService(ILabSciFundsService labSciFundsService) {
		this.labSciFundsService = labSciFundsService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	private LabSciProcessVo labSciProcessVo;
	private LabSciScienceVo labSciScienceVo;
	private LabFunctionVo labFunctionVo;
	private LabSciFundsVo labSciFundsVo;
	private LabSciAchievementVo labSciAchievementVo;
	private LabSampRegisterVo labSampRegisterVo;
	private List<LabSamVo> sampList;
	
	public LabSciProcessVo getLabSciProcessVo() {
		return labSciProcessVo;
	}
	public void setLabSciProcessVo(LabSciProcessVo labSciProcessVo) {
		this.labSciProcessVo = labSciProcessVo;
	}
	public LabSciScienceVo getLabSciScienceVo() {
		return labSciScienceVo;
	}
	public void setLabSciScienceVo(LabSciScienceVo labSciScienceVo) {
		this.labSciScienceVo = labSciScienceVo;
	}
	public LabFunctionVo getLabFunctionVo() {
		return labFunctionVo;
	}
	public void setLabFunctionVo(LabFunctionVo labFunctionVo) {
		this.labFunctionVo = labFunctionVo;
	}
	public String listLabScience4Process()throws GlobalException{
		if(null==labSciScienceVo){
			labSciScienceVo=new LabSciScienceVo();
			labSciScienceVo.setStatus(getSessionContainer().getFunId());
		}
		labSciScienceVo.setIsApply(Constants_Business.N);
		pageResult = labSciScienceService.getLabSciSciencePR(pageResult, labSciScienceVo);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = new ArrayList<LabWfFunStepVo>();
		funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	public String showLabScience4Process()throws GlobalException{
		if(null==labSciScienceVo)labSciScienceVo=new LabSciScienceVo();
		if(null==labSciFundsVo)labSciFundsVo=new LabSciFundsVo();
		String auditResult = labSciScienceVo.getAuditResult(); 
		if(!StrUtils.isBlankOrNull(labSciScienceVo.getId())){
			labSciScienceVo = labSciScienceService.getLabSciScienceVoById(labSciScienceVo.getId());
			labSciFundsVo.setType("1");
			labSciFundsVo.setLabSciScienceId(labSciScienceVo.getId());
			List<LabSciFundsVo> labSciFundsList = labSciFundsService.getLabSciFundsList(labSciFundsVo);
			labSciScienceVo.setLabSciFundsList(labSciFundsList);
		}
		
		List<LabUploadVo> fileList=labUploadService.getLabUploadList(labSciScienceVo.getId(), "lab-science");
		setAttribute("uplodeList", fileList);
		if(auditResult.equals("tab2")){
			return "show2";
		}if(auditResult.equals("tab3")){
			return "show3";
		}if(auditResult.equals("tab4")){
			return "show4";
		}else {
			return SHOW;
		}
	}
	public String listLabSciProcess() throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		pageResult=labSciProcessService.getLabSciProcessPR(labSciProcessVo, pageResult);
		return LIST;
	}
	public String preAddLabSciProcess() throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		List<LabCodeVo> labCodeVoList = labCodeService.getLabCodeByTypeCode("GCLX");
		setAttribute("labCodeVoList", labCodeVoList);
		labSciProcessVo.setWriteUser(getSessionContainer().getUserName());
		return PREADD;
	}
	public String addLabSciProcess() throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		labSciProcessVo=labSciProcessService.addLabSciProcess(labSciProcessVo);
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getAuditResult()) && labSciProcessVo.getAuditResult().equals(Constants_Business.TRUE)){
			return ADD;
		}else
		return PREUPDATE;
	}
	public String preUpdateLabSciProcess() throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		if(null==labSampRegisterVo)labSampRegisterVo=new LabSampRegisterVo();
		labSciProcessVo=labSciProcessService.getLabSciProcess(labSciProcessVo.getId());
		List<LabCodeVo> labCodeVoList = labCodeService.getLabCodeByTypeCode("GCLX");
		setAttribute("labCodeVoList", labCodeVoList);
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getSampRegisterNum())){
			labSampRegisterVo.setId(labSciProcessVo.getSampRegisterId());
			labSampRegisterVo.setSampNo(labSciProcessVo.getSampRegisterSampNo());
			labSampRegisterVo.setSampNum(labSciProcessVo.getSampRegisterNum());
			sampList = labSampRegisterService.getLabSamList(labSampRegisterVo);
			//样品类型列表
			LabSamTypeVo labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
			List<LabSamTypeVo> samTypeList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
			setAttribute("samTypeList", samTypeList);
		}
		return PREUPDATE;
	}
	public String updateLabSciProcess() throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		if(null==labSampRegisterVo)labSampRegisterVo=new LabSampRegisterVo();
		boolean key=labSciProcessService.updateLabSciProcess(labSciProcessVo);
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getSampRegisterId())&&!StrUtils.isBlankOrNull(labSciProcessVo.getSampRegisterNum())&&!labSciProcessVo.getSampRegisterNum().equals("0") ){
			labSampRegisterVo.setId(labSciProcessVo.getSampRegisterId());
			labSampRegisterVo=labSampRegisterService.saveLabSampRegister4Other(labSampRegisterVo,sampList);
		}
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getAuditResult()) && labSciProcessVo.getAuditResult().equals(Constants_Business.TRUE)){
			return UPDATE;
		}else
		return PREUPDATE;
	}
	public String showLabSciProcess()  throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		labSciProcessVo=labSciProcessService.getLabSciProcess(labSciProcessVo.getId());
		return SHOW;
	}
	public String deleteLabSciProcess() throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		if(null==labSciScienceVo)labSciScienceVo=new LabSciScienceVo();
		labSciProcessService.deleteLabSciProcess(labSciProcessVo.getIds());
		return DELETE;
	}
	public String updateLabSciProcess4Del()throws GlobalException{
		if(null==labSciProcessVo)labSciProcessVo=new LabSciProcessVo();
		labSciProcessService.updateLabSciProcess4Del(labSciProcessVo.getIds());
		return DELETE;
	}
	public LabSciFundsVo getLabSciFundsVo() {
		return labSciFundsVo;
	}
	public void setLabSciFundsVo(LabSciFundsVo labSciFundsVo) {
		this.labSciFundsVo = labSciFundsVo;
	}
	public LabSciAchievementVo getLabSciAchievementVo() {
		return labSciAchievementVo;
	}
	public void setLabSciAchievementVo(LabSciAchievementVo labSciAchievementVo) {
		this.labSciAchievementVo = labSciAchievementVo;
	}
	public LabSampRegisterVo getLabSampRegisterVo() {
		return labSampRegisterVo;
	}
	public void setLabSampRegisterVo(LabSampRegisterVo labSampRegisterVo) {
		this.labSampRegisterVo = labSampRegisterVo;
	}
	public List<LabSamVo> getSampList() {
		return sampList;
	}
	public void setSampList(List<LabSamVo> sampList) {
		this.sampList = sampList;
	}
	
}
