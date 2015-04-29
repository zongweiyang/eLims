package cn.labsoft.labos.business.science.action;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.business.sample.service.ILabSampRegisterService;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.business.science.service.ILabSciAchievementService;
import cn.labsoft.labos.business.science.service.ILabSciFundsService;
import cn.labsoft.labos.business.science.service.ILabSciProcessService;
import cn.labsoft.labos.business.science.service.ILabSciScienceService;
import cn.labsoft.labos.business.science.vo.LabSciAchievementVo;
import cn.labsoft.labos.business.science.vo.LabSciFundsVo;
import cn.labsoft.labos.business.science.vo.LabSciProcessVo;
import cn.labsoft.labos.business.science.vo.LabSciScienceVo;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.constants.Constants_Business;

@Controller
@Scope("prototype")
public class LabSciScienceAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabSciScienceService labSciScienceService;
	private ILabSciFundsService labSciFundsService;
	private ILabSciAchievementService labSciAchievementService;
	private ILabCodeService labCodeService;
	private ILabOrgService labOrgService;
	private ILabUserService labUserService;
	private ILabNumberService labNumberService;
	private ILabWfProcessInsService labWfProcessInsService;
	private ILabUploadService labUploadService;
	private ILabSciProcessService labSciProcessService;
	private ILabSampRegisterService labSampRegisterService;
	private ILabSamTypeService labSamTypeService;

	private LabSciScienceVo labSciScienceVo;
	private LabSciFundsVo labSciFundsVo;
	private LabSciAchievementVo labSciAchievementVo;
	private LabSciProcessVo labSciProcessVo;
	private LabSampRegisterVo labSampRegisterVo;
	private List<LabSamVo> sampList;
	private String type;
	private String userId;
	private String[] names;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}

	@Resource
	public void setLabSampRegisterService(
			ILabSampRegisterService labSampRegisterService) {
		this.labSampRegisterService = labSampRegisterService;
	}
	@Resource
	public void setLabSamTypeService(ILabSamTypeService labSamTypeService) {
		this.labSamTypeService = labSamTypeService;
	}


	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}

	@Resource
	public void setLabSciScienceService(
			ILabSciScienceService labSciScienceService) {
		this.labSciScienceService = labSciScienceService;
	}

	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}

	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}

	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	
	@Resource
	public void setLabSciFundsService(ILabSciFundsService labSciFundsService) {
		this.labSciFundsService = labSciFundsService;
	}
	@Resource
	public void setLabSciAchievementService(
			ILabSciAchievementService labSciAchievementService) {
		this.labSciAchievementService = labSciAchievementService;
	}
	@Resource
	public void setLabSciProcessService(ILabSciProcessService labSciProcessService) {
		this.labSciProcessService = labSciProcessService;
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


	public LabSciScienceVo getLabSciScienceVo() {
		return labSciScienceVo;
	}

	public void setLabSciScienceVo(LabSciScienceVo labSciScienceVo) {
		this.labSciScienceVo = labSciScienceVo;
	}
	
	public LabSciProcessVo getLabSciProcessVo() {
		return labSciProcessVo;
	}

	public void setLabSciProcessVo(LabSciProcessVo labSciProcessVo) {
		this.labSciProcessVo = labSciProcessVo;
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

	private void init() {
		if (null == labSciScienceVo)
			labSciScienceVo = new LabSciScienceVo();
	}

	public String listLabScience() throws GlobalException {
		init();
		if(!StrUtils.isBlankOrNull(messageInfo)&&null == labSciScienceVo.getStatus()) {
			labSciScienceVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labSciScienceService.getLabSciSciencePR(pageResult,
				labSciScienceVo);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("1")) {
			return "list1";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("2")) {
			return "list2";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("3")) {
			return "list3";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("4")) {
			return "list4";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("5")) {
			return "list5";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("6")) {
			return "list6";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("7")) {
			return "list7";
		}
		if (!StrUtils.isBlankOrNull(treeNid) && treeNid.equals("8")) {
			List<LabUserVo> userList=labUserService.getLabUserList(new LabUserVo());
			setAttribute("userList", userList);
			return "list8";
		}
		return LIST;
	}

	@SuppressWarnings("unchecked")
	public String preAddLabScience() throws GlobalException,
			InterruptedException, ExecutionException {
		init();
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("XMLY");
		setAttribute("sourceList", codeList);

		List<LabOrgVo> labOrgList = labOrgService.getLabOrgListByPId("0");
		setAttribute("orgList", labOrgList);

		if(StrUtils.isBlankOrNull(labSciScienceVo.getUuid()))
		   labSciScienceVo.setUuid(java.util.UUID.randomUUID().toString().replace("-", ""));
		ThreadNumber threadNumber = new ThreadNumber(labNumberService, null,
				Constants_Business.CODE_KYXM, new String[] {},Constants_Business.CODE_USE_INIT);
		labSciScienceVo.setCode(pool.submit(threadNumber).get().toString());
		if(!StrUtils.isBlankOrNull(labSciScienceVo.getId())){
			labSciScienceVo = labSciScienceService.getLabSciScienceVoById(labSciScienceVo.getId());
		}
		List<LabUploadVo> uplodeList = labUploadService.getLabUploadList(labSciScienceVo.getUuid(), "lab-science");
		setAttribute("uplodeList", uplodeList);
		
		LabUserVo labUserVo=labUserService.getLabUser(getSessionContainer().getUserId());
		labSciScienceVo.setMasterId(labUserVo.getId());
		labSciScienceVo.setMasterName(labUserVo.getName());
		labSciScienceVo.setSex(labUserVo.getSex());
		labSciScienceVo.setDuty(labUserVo.getDuty());
		labSciScienceVo.setTechTitle(labUserVo.getTechTitle());
		labSciScienceVo.setTelephone(labUserVo.getTelephone());
		labSciScienceVo.setEmail(labUserVo.getEmail());
		return PREADD;
	}

	@SuppressWarnings("unchecked")
	public String addLabScience() throws GlobalException, InterruptedException,
			ExecutionException {
		init();
		ThreadNumber threadNumber = new ThreadNumber(labNumberService, null,
				Constants_Business.CODE_KYXM, new String[] {},Constants_Business.CODE_USE_RUN);
		labSciScienceVo.setCode(pool.submit(threadNumber).get().toString());
		if(!StrUtils.isBlankOrNull(labSciScienceVo.getId())){
			labSciScienceService.updateLabSciScience(labSciScienceVo);
		}else{
			labSciScienceService.addLabSciScience(labSciScienceVo);
		}
		if(labSciScienceVo.getAuditResult().equals(Constants_Business.TRUE)){
			return ADD;
		}if(labSciScienceVo.getAuditResult().equals("tab2")){
			return "input1";
		}if(labSciScienceVo.getAuditResult().equals("tab3")){
			return "input2";
		}if(labSciScienceVo.getAuditResult().equals("tab4")){
			return "input3";
		}else{
			return INPUT;
		}
	}
	//经费详情
	public String listLabSciFunds4Select()throws GlobalException{
		if( null == labSciFundsVo ) labSciFundsVo = new LabSciFundsVo();
		if( null == labSciScienceVo ) labSciScienceVo = new LabSciScienceVo();
		labSciFundsVo.setLabSciScienceId(labSciScienceVo.getId());
		pageResult = labSciFundsService.getLabSciFundsPR(labSciFundsVo, pageResult);
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("JFLX");
		setAttribute("codeList", codeList);
		return LIST;
	}
	public String showLabSciFunds4Select()throws GlobalException{
		if( null == labSciFundsVo ) labSciFundsVo = new LabSciFundsVo();
		labSciFundsVo = labSciFundsService.getLabSciFundsVoById(labSciFundsVo.getId());
		return SHOW;
	}
	//项目成果
	public String listLabSciAchievement4Select()throws GlobalException{
		if( null == labSciAchievementVo ) labSciAchievementVo = new LabSciAchievementVo();
		if( null == labSciScienceVo ) labSciScienceVo = new LabSciScienceVo();
		labSciAchievementVo.setLabSciScienceId(labSciScienceVo.getId());
		pageResult = labSciAchievementService.getLabSciAchievementPR(pageResult, labSciAchievementVo);
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("XMCG");
		setAttribute("codeList", codeList);
		return LIST;
	}
	public String showLabSciAchievement4Select()throws GlobalException{
		if( null == labSciAchievementVo ) labSciAchievementVo = new LabSciAchievementVo();
		labSciAchievementVo = labSciAchievementService.getLabSciAchievementVo(labSciAchievementVo);
		return SHOW;
	}
	//项目成果
	public String listLabSciProcess4Select()throws GlobalException{
		if( null == labSciProcessVo ) labSciProcessVo = new LabSciProcessVo();
		if( null == labSciScienceVo ) labSciScienceVo = new LabSciScienceVo();
		labSciProcessVo.setLabSciScienceId(labSciScienceVo.getId());
		pageResult = labSciProcessService.getLabSciProcessPR(labSciProcessVo, pageResult);
		return LIST;
	}
	public String showLabSciProcess4Select()throws GlobalException{
		if( null == labSciProcessVo ) labSciProcessVo = new LabSciProcessVo();
		if( null == labSampRegisterVo ) labSampRegisterVo = new LabSampRegisterVo();
		labSciProcessVo = labSciProcessService.getLabSciProcess(labSciProcessVo.getId());
		if(!StrUtils.isBlankOrNull(labSciProcessVo.getSampRegisterNum())){
			labSampRegisterVo.setId(labSciProcessVo.getSampRegisterId());
			labSampRegisterVo.setSampNo(labSciProcessVo.getSampRegisterSampNo());
			labSampRegisterVo.setSampNum(labSciProcessVo.getSampRegisterNum());
			sampList = labSampRegisterService.getLabSamList(labSampRegisterVo);
		}
		List<LabUploadVo> uplodeList = labUploadService.getLabUploadList(
				labSciProcessVo.getId(), "lab-sciProcess");
		setAttribute("uplodeList", uplodeList);
		return SHOW;
	}
	
	public String preUpdateLabScience() throws GlobalException {
		init();
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("XMLY");
		setAttribute("sourceList", codeList);

		List<LabOrgVo> labOrgList = labOrgService.getLabOrgListByPId("0");
		setAttribute("orgList", labOrgList);
		labSciScienceVo = labSciScienceService
				.getLabSciScienceVoById(labSciScienceVo.getId());
		List<LabUploadVo> uplodeList = labUploadService.getLabUploadList(
				labSciScienceVo.getId(), "lab-science");
		setAttribute("uplodeList", uplodeList);
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("1")) {
			return "preUpdate1";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("2")) {
			return "preUpdate2";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("3")) {
			return "preUpdate3";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("4")) {
			LabSciFundsVo labSciFundsVo=new LabSciFundsVo();
			labSciFundsVo.setLabSciScienceId(labSciScienceVo.getId());
			labSciFundsVo.setType("1");
			labSciScienceVo.setLabSciFundsList(labSciFundsService.getLabSciFundsList(labSciFundsVo));
			return "preUpdate4";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("5")) {
			LabSciFundsVo labSciFundsVo=new LabSciFundsVo();
			labSciFundsVo.setLabSciScienceId(labSciScienceVo.getId());
			labSciFundsVo.setType("1");
			labSciScienceVo.setLabSciFundsList(labSciFundsService.getLabSciFundsList(labSciFundsVo));
			return "preUpdate5";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("6")) {
			LabSciFundsVo labSciFundsVo=new LabSciFundsVo();
			labSciFundsVo.setLabSciScienceId(labSciScienceVo.getId());
			labSciFundsVo.setType("1");
			labSciScienceVo.setLabSciFundsList(labSciFundsService.getLabSciFundsList(labSciFundsVo));
			return "preUpdate6";
		}
		if (!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("7")) {
			LabSciFundsVo labSciFundsVo=new LabSciFundsVo();
			labSciFundsVo.setLabSciScienceId(labSciScienceVo.getId());
			labSciFundsVo.setType("1");
			labSciScienceVo.setLabSciFundsList(labSciFundsService.getLabSciFundsList(labSciFundsVo));
			return "preUpdate7";
		}
		return PREUPDATE;
	}

	public String updateLabScience() throws GlobalException, InterruptedException,
	ExecutionException {
		init();
		if(StrUtils.isBlankOrNull(labSciScienceVo.getCode())){
			ThreadNumber threadNumber = new ThreadNumber(labNumberService, null,
					Constants_Business.CODE_KYXM, new String[] {}, "0");
			labSciScienceVo.setCode(pool.submit(threadNumber).get().toString());
		}
		if(!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("5")){
			if(labSciScienceVo.getAuditResult().equals("-2")){
				labSciScienceVo.setIsSeized(Constants_Business.N);
			}else{
				labSciScienceVo.setIsKnot(Constants_Business.N);
			}
		}
		if(!labSciScienceVo.getAuditResult().equals("tab2")&&!labSciScienceVo.getAuditResult().equals("tab3")&&!labSciScienceVo.getAuditResult().equals("tab4")){
			if(!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("4")){
				if(labSciScienceVo.getIsSeized().equals(Constants_Business.Y)){
					labSciScienceVo.setAuditResult("2");
				}else{
					labSciScienceVo.setAuditResult("0");
				}
			}
			if(!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("6")){
				if(labSciScienceVo.getIsKnot().equals(Constants_Business.Y)){
					labSciScienceVo.setAuditResult("1");
				}else{
					labSciScienceVo.setAuditResult("-1");
				}
			}
		}
		if(!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("7")){
			if(labSciScienceVo.getAuditResult().equals("-1")){
				labSciScienceVo.setIsKnot(Constants_Business.N);
			}
		}
		labSciScienceService.updateLabSciScience(labSciScienceVo);
		if(!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("5")|| messageInfo.equals("4")||!StrUtils.isBlankOrNull(messageInfo) && messageInfo.equals("6")){
			if(labSciScienceVo.getAuditResult().equals("tab2") || !StrUtils.isBlankOrNull(formName) && formName.equals("tab2")){
				return "input1";
			}else if(labSciScienceVo.getAuditResult().equals("tab3") ||!StrUtils.isBlankOrNull(formName) && formName.equals("tab3")){
				return "input2";
			}else if(labSciScienceVo.getAuditResult().equals("tab4") ||!StrUtils.isBlankOrNull(formName) && formName.equals("tab4")){
				return "input3";
			}else{
				return "update1";
			}
		} else if(!StrUtils.isNull(labSciScienceVo.getAuditResult())&& labSciScienceVo.getAuditResult().equals(Constants_Business.TRUE)|| labSciScienceVo.getAuditResult().equals("3")|| labSciScienceVo.getAuditResult().equals("-1")|| labSciScienceVo.getAuditResult().equals("-2")){
			return UPDATE;
		}else if(messageInfo.equals("tab2")){
			return "input1";
		} else if(messageInfo.equals("tab3")){
			return "input2";
		} else if(messageInfo.equals("tab4")){
			return "input3";
		} else{
			return INPUT;
		}
	}
	
	public String updateLabSciScience4Audit()throws GlobalException
	{
		init();
		labSciScienceService.updateLabSciScience4Audit(labSciScienceVo);
		return UPDATE;
	}

	public String deleteLabScience() throws GlobalException {
		labSciScienceService.deleteLabSciScience(labSciScienceVo.getIds());
		return DELETE;
	}

	public String showLabScience() throws GlobalException {
		init();
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("XMLY");
		setAttribute("sourceList", codeList);
		List<LabOrgVo> labOrgList = labOrgService.getLabOrgListByPId("0");
		setAttribute("orgList", labOrgList);
		labSciScienceVo = labSciScienceService
				.getLabSciScienceVoById(labSciScienceVo.getId());
		List<LabUploadVo> uplodeList = labUploadService.getLabUploadList(
				labSciScienceVo.getId(), "lab-science");
		setAttribute("uplodeList", uplodeList);
		return SHOW;
	}

	public String getAllUser() throws GlobalException {
		init();
		List<LabUserVo> list = labUserService.getLabUserList(new LabUserVo());
		setAttribute("userList", list);
		return LIST;
	}
	
	public String showLabSciScience4Select()throws GlobalException
	{
		init();
		List<LabWfFunStepVo> funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
        setAttribute("funStepList", funStepList); 
		pageResult=labSciScienceService.getLabSciSciencePR(pageResult, labSciScienceVo);
		return SHOW;
	}
	
	public String showQueryLabScience()throws GlobalException
	{
		init();
		if (!StrUtils.isBlankOrNull(type)&&"0".equals(type)) {
			labSciScienceVo=labSciScienceService.getLabSciScienceVoById(labSciScienceVo.getId());
			names=labSciFundsService.getQueryLabSciFunds(labSciScienceVo.getId());
			return "show1";
		}
		if (!StrUtils.isBlankOrNull(type)&&"1".equals(type)) {
			labSciScienceVo=labSciScienceService.getLabSciScienceVoById(labSciScienceVo.getId());
			names=labSciAchievementService.getLabSciAchievementShowByType(labSciScienceVo.getId());
			return "show2";
		}
		if (!StrUtils.isBlankOrNull(type)&&"2".equals(type)) {
			LabUserVo userVo=labUserService.getLabUser(userId);
			setAttribute("userVo", userVo);
			names=labSciAchievementService.getLabSciAchievementShowByUser(userId);
			return "show3";
		}
		if (!StrUtils.isBlankOrNull(type)&&"3".equals(type)) {
			List<LabUserVo> userList=labUserService.getLabUserList(new LabUserVo());
			String username="";
			if (null!=userList&&userList.size()>0) {
				for(int i=0;i<userList.size()-1;i++)
				{
					username+="'"+userList.get(i).getName()+"',";
				}
				username+="'"+userList.get(userList.size()-1).getName()+"'";
				labSciScienceVo.setGoal(username);
			}
			List<LabCodeVo> labCodeList=labCodeService.getLabCodeByTypeCode("FBQK");
			String[] code=new String[labCodeList.size()];
			for(int i=0;i<labCodeList.size();i++)
				code[i]=labCodeList.get(i).getName();
			setAttribute("code",code);
			names=labSciAchievementService.getLabSciAchievementShowByPaperType(userList, labCodeList);
			return "show4";
		}
		return SHOW;
	}
}
