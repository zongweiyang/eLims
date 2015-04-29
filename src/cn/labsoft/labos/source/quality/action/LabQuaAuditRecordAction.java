package cn.labsoft.labos.source.quality.action;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.vo.LabReportVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.service.ILabQuaAuditPlanEleService;
import cn.labsoft.labos.source.quality.service.ILabQuaAuditRecordService;
import cn.labsoft.labos.source.quality.service.ILabQuaInitAuditPlanService;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditPlanEleVo;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditRecordVo;
import cn.labsoft.labos.source.quality.vo.LabQuaInitAuditPlanVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabQuaAuditRecordAction extends BaseAction {

	private ILabQuaAuditRecordService labQuaAuditRecordService ;
	private ILabQuaAuditPlanEleService labQuaAuditPlanEleService ;
	private ILabQuaInitAuditPlanService labQuaInitAuditPlanService; 
	private ILabCodeService labCodeService ;
	private ILabOrgService labOrgService ;
	private ILabReportService labReportService;
	private ILabWfProcessInsService labWfProcessInsService;

	private List<LabOrgVo> labOrgVoList;
	private List<LabQuaAuditPlanEleVo> labQuaAuditPlanEleVoList;
	private List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList;
	private List<LabCodeVo> labCodeVoList;
	private List<LabWfFunStepVo> funStepList;
	private LabQuaAuditRecordVo labQuaAuditRecordVo;
	private LabQuaAuditPlanEleVo labQuaAuditPlanEleVo; 
	private LabReportVo labReportVo;

	@Resource 
	public void setLabReportService(ILabReportService labReportService) {
		this.labReportService = labReportService;
	}
	@Resource 
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}

	@Resource 
	public void setLabQuaAuditRecordService(
			ILabQuaAuditRecordService labQuaAuditRecordService) {
		this.labQuaAuditRecordService = labQuaAuditRecordService;
	}
	 @Resource 
	public void setLabQuaAuditPlanEleService(
			ILabQuaAuditPlanEleService labQuaAuditPlanEleService) {
		this.labQuaAuditPlanEleService = labQuaAuditPlanEleService;
	}
	 @Resource 
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	 @Resource 
	public void setLabQuaInitAuditPlanService(
			ILabQuaInitAuditPlanService labQuaInitAuditPlanService) {
		this.labQuaInitAuditPlanService = labQuaInitAuditPlanService;
	}
	 @Resource 
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	public List<LabCodeVo> getLabCodeVoList() {
		return labCodeVoList;
	}
	public void setLabCodeVoList(List<LabCodeVo> labCodeVoList) {
		this.labCodeVoList = labCodeVoList;
	}
	public List<LabOrgVo> getLabOrgVoList() {
		return labOrgVoList;
	}

	public void setLabOrgVoList(List<LabOrgVo> labOrgVoList) {
		this.labOrgVoList = labOrgVoList;
	}

	public LabQuaAuditRecordVo getLabQuaAuditRecordVo() {
		return labQuaAuditRecordVo;
	}

	public void setLabQuaAuditRecordVo(LabQuaAuditRecordVo labQuaAuditRecordVo) {
		this.labQuaAuditRecordVo = labQuaAuditRecordVo;
	}

	public List<LabQuaAuditPlanEleVo> getLabQuaAuditPlanEleVoList() {
		return labQuaAuditPlanEleVoList;
	}
	public void setLabQuaAuditPlanEleVoList(
			List<LabQuaAuditPlanEleVo> labQuaAuditPlanEleVoList) {
		this.labQuaAuditPlanEleVoList = labQuaAuditPlanEleVoList;
	}
	
	public List<LabQuaInitAuditPlanVo> getLabQuaInitAuditPlanVoList() {
		return labQuaInitAuditPlanVoList;
	}
	public void setLabQuaInitAuditPlanVoList(
			List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList) {
		this.labQuaInitAuditPlanVoList = labQuaInitAuditPlanVoList;
	}
	public LabQuaAuditPlanEleVo getLabQuaAuditPlanEleVo() {
		return labQuaAuditPlanEleVo;
	}
	public void setLabQuaAuditPlanEleVo(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo) {
		this.labQuaAuditPlanEleVo = labQuaAuditPlanEleVo;
	}
	public LabReportVo getLabReportVo() {
		return labReportVo;
	}
	public void setLabReportVo(LabReportVo labReportVo) {
		this.labReportVo = labReportVo;
	}
	public List<LabWfFunStepVo> getFunStepList() {
		return funStepList;
	}
	public void setFunStepList(List<LabWfFunStepVo> funStepList) {
		this.funStepList = funStepList;
	}
	
	
	public String listLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
//		if (null == labQuaAuditRecordVo.getStatus()) {
//			labQuaAuditRecordVo.setStatus(getSessionContainer().getFunId());
//		}
		pageResult = labQuaAuditRecordService.getLabQuaAuditRecordPR(labQuaAuditRecordVo, pageResult);
		labQuaAuditPlanEleVoList= labQuaAuditPlanEleService.getLabQuaAuditPlanEleList(new LabQuaAuditPlanEleVo());
		labOrgVoList = labOrgService.getLabOrgVoByRank("1");
//		funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		return LIST;
	}

	public String preAddLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		if( null == labQuaAuditPlanEleVo) labQuaAuditPlanEleVo = new LabQuaAuditPlanEleVo();
		if(StrUtils.isBlankOrNull(labQuaAuditRecordVo.getMonth())){
			labQuaAuditRecordVo.setMonth(DateUtils.getMonth());
		}
		labQuaAuditPlanEleVo = labQuaAuditPlanEleService.getLabQuaAuditPlanEleVo(labQuaAuditRecordVo.getQuaAuditPlanEleId());
		labQuaInitAuditPlanVoList = labQuaInitAuditPlanService.getLabQuaInitAuditPlanList(labQuaAuditRecordVo.getQuaAuditPlanEleId(), labQuaAuditRecordVo.getMonth());
		labOrgVoList = labOrgService.getLabOrgVoByRank("1");
		labCodeVoList=labQuaAuditRecordService.getLabCodeByPlanEle("NSYF",labQuaAuditRecordVo.getQuaAuditPlanEleId());
		if (null != labQuaInitAuditPlanVoList && labQuaInitAuditPlanVoList.size() > 0) {
			String auditPart = "";
			for (LabQuaInitAuditPlanVo labQuaInitAuditPlanVo : labQuaInitAuditPlanVoList) {
				if(!StrUtils.isBlankOrNull(labQuaInitAuditPlanVo.getName())){
					auditPart += labQuaInitAuditPlanVo.getName() + ",";
				}
			}
			if (auditPart.length() > 0)
				auditPart = auditPart.substring(0, auditPart.length() - 1);
			labQuaAuditRecordVo.setAuditPart(auditPart);
		}
		labQuaAuditRecordVo.setWriteMember(getSessionContainer().getUserName());
		labQuaAuditRecordVo.setWriteTime(DateUtils.getCurrDateStr());
		// 获取报告模版信息
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
			labReportVo.setBusId(getSessionContainer().getFunId());
		}
		List<LabReportVo> reportTempList = labReportService.getLabReportList(labReportVo);
		setAttribute("tempList", reportTempList);
		return PREADD;
	}

	public String addLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		labQuaAuditRecordService.addLabQuaAuditRecord(labQuaAuditRecordVo);
		return ADD;
	}

	public String preUpdateLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		labQuaAuditRecordVo = labQuaAuditRecordService.getLabQuaAuditRecord(labQuaAuditRecordVo.getId());
		labCodeVoList=labCodeService.getLabCodeByTypeCode("NSYF");
		labOrgVoList = labOrgService.getLabOrgVoByRank("1");
		labQuaAuditRecordVo.setAuditTime(DateUtils.getCurrDateStr());
		labQuaAuditRecordVo.setAuditPerson(getSessionContainer().getUserName());
		labQuaAuditRecordVo.setFileTime(DateUtils.getCurrDateStr());
		labQuaAuditRecordVo.setFilePeople(getSessionContainer().getUserName());
		
		// 获取报告模版信息
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
			labReportVo.setBusId(getSessionContainer().getFunId());
		}
		List<LabReportVo> reportTempList = labReportService.getLabReportList(labReportVo);
		setAttribute("tempList", reportTempList);
		return PREUPDATE;
	}
	public String showLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		labQuaAuditRecordVo = labQuaAuditRecordService.getLabQuaAuditRecord(labQuaAuditRecordVo.getId());
		return SHOW;
	}
	public String updateLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		labQuaAuditRecordService.updateLabQuaAuditRecord(labQuaAuditRecordVo);
		return UPDATE;
	}
	public String deleteLabQuaAuditRecord() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		labQuaAuditRecordService.update2DelLabQuaAuditRecord(labQuaAuditRecordVo.getIds());
		return DELETE;
	}
	public String showLabQuaAuditPlanEle4select()throws GlobalException{
		if( null == labQuaAuditPlanEleVo) labQuaAuditPlanEleVo = new LabQuaAuditPlanEleVo();
		labQuaAuditPlanEleVoList= labQuaAuditPlanEleService.getLabQuaAuditPlanEleList(labQuaAuditPlanEleVo);
		labOrgVoList=labOrgService.getLabOrgVoByRank("1");
		return SHOW;
	}

	/**
	 * 报告查看
	 */
	public String showLabReport4Bus() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		if( null == labReportVo) labReportVo = new LabReportVo();
		labQuaAuditRecordVo = labQuaAuditRecordService.getLabQuaAuditRecord(labReportVo.getBusInsId());
		
		labReportVo = labReportService.getLabReport4Bus(labReportVo);
		labReportVo.setEditType("1");
		return SHOW;
	}
	/**
	 * 报告生成
	 */
	public String addLabReport4Bus() throws GlobalException {
		if( null == labQuaAuditRecordVo) labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		if( null == labReportVo) labReportVo = new LabReportVo();
		labQuaAuditRecordVo = labQuaAuditRecordService.getLabQuaAuditRecord(labReportVo.getBusInsId());
		// 获取报告模版信息
		labReportVo = labReportService.addLabReport4Bus(labReportVo);
		labQuaAuditRecordVo.setReportTempId(labReportVo.getId());
		labQuaAuditRecordVo.setReportPath(labReportVo.getPath());
		labQuaAuditRecordService.updateLabQuaAuditRecord4Report(labQuaAuditRecordVo);
		return ADD;
	}
}
