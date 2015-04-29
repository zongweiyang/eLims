package cn.labsoft.labos.source.quality.action;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.vo.LabReportVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.service.ILabQuaManageCheckPlanService;
import cn.labsoft.labos.source.quality.service.ILabQuaManageCheckService;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckPlanVo;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckVo;
import cn.labsoft.labos.utils.DateUtils;



@Controller
@Scope("prototype")
public class LabQuaManageCheckAction extends BaseAction {
	
	private ILabQuaManageCheckService labQuaManageCheckService;
	private ILabQuaManageCheckPlanService labQuaManageCheckPlanService;
	private ILabWfProcessInsService labWfProcessInsService;
	private ILabReportService labReportService;
	private ILabOrgService labOrgService ;
	
    private List<LabOrgVo>  labOrgList;
    private List<LabQuaManageCheckPlanVo> labQuaManageCheckPlanVoList;
    private List<LabWfFunStepVo> funStepList;	
    private LabQuaManageCheckVo labQuaManageCheckVo;
    private LabQuaManageCheckPlanVo labQuaManageCheckPlanVo;
    private LabReportVo labReportVo;
    
    @Resource 
    public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
    @Resource
	public void setLabQuaManageCheckPlanService(
			ILabQuaManageCheckPlanService labQuaManageCheckPlanService) {
		this.labQuaManageCheckPlanService = labQuaManageCheckPlanService;
	}

	@Resource 
    public void setLabQuaManageCheckService(
			ILabQuaManageCheckService labQuaManageCheckService) {
		this.labQuaManageCheckService = labQuaManageCheckService;
	}
    @Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
    @Resource
	public void setLabReportService(ILabReportService labReportService) {
		this.labReportService = labReportService;
	}
	public LabReportVo getLabReportVo() {
		return labReportVo;
	}
	public void setLabReportVo(LabReportVo labReportVo) {
		this.labReportVo = labReportVo;
	}
	public List<LabOrgVo> getLabOrgList() {
		return labOrgList;
	}
	public void setLabOrgList(List<LabOrgVo> labOrgList) {
		this.labOrgList = labOrgList;
	}
	public LabQuaManageCheckVo getLabQuaManageCheckVo() {
		return labQuaManageCheckVo;
	}
	public void setLabQuaManageCheckVo(LabQuaManageCheckVo labQuaManageCheckVo) {
		this.labQuaManageCheckVo = labQuaManageCheckVo;
	}
	
    public List<LabWfFunStepVo> getFunStepList() {
		return funStepList;
	}
	public void setFunStepList(List<LabWfFunStepVo> funStepList) {
		this.funStepList = funStepList;
	}
	public List<LabQuaManageCheckPlanVo> getLabQuaManageCheckPlanVoList() {
		return labQuaManageCheckPlanVoList;
	}
	public void setLabQuaManageCheckPlanVoList(
			List<LabQuaManageCheckPlanVo> labQuaManageCheckPlanVoList) {
		this.labQuaManageCheckPlanVoList = labQuaManageCheckPlanVoList;
	}
	public LabQuaManageCheckPlanVo getLabQuaManageCheckPlanVo() {
		return labQuaManageCheckPlanVo;
	}
	public void setLabQuaManageCheckPlanVo(
			LabQuaManageCheckPlanVo labQuaManageCheckPlanVo) {
		this.labQuaManageCheckPlanVo = labQuaManageCheckPlanVo;
	}
	public String listLabQuaManageCheck() throws GlobalException{
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
//		if (null == labQuaManageCheckVo.getStatus()) {
//			labQuaManageCheckVo.setStatus(getSessionContainer().getFunId());
//		}
		pageResult=labQuaManageCheckService.getLabQuaManageCheckPR(labQuaManageCheckVo, pageResult);
		labOrgList=labOrgService.getLabOrgVoByRank("0");
		labQuaManageCheckPlanVoList = labQuaManageCheckPlanService.getLabQuaManageCheckPlanList(new LabQuaManageCheckPlanVo());
		// 获取流程状态集合
//		funStepList = labWfProcessInsService.getLabWfFunStepList(getSessionContainer().getFunId());
		return LIST;
	}
	public String showLabQuaManageCheckPlan4select()throws GlobalException{
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labQuaManageCheckPlanVoList = labQuaManageCheckPlanService.getLabQuaManageCheckPlanList(labQuaManageCheckPlanVo);
		labOrgList=labOrgService.getLabOrgVoByRank("0");
		return SHOW;
	}
	public String preAddLabQuaManageCheck() throws GlobalException {
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labQuaManageCheckPlanVo = labQuaManageCheckPlanService.getLabQuaManageCheckPlan(labQuaManageCheckVo.getQuaManageCheckPlanId());
		labOrgList=labOrgService.getLabOrgVoByRank("0");
		labQuaManageCheckVo.setRecordPeople(getSessionContainer().getUserName());
		labQuaManageCheckVo.setRecordTime(DateUtils.getCurrDateStr());
		labQuaManageCheckVo.setReportPeople(getSessionContainer().getUserName());
		labQuaManageCheckVo.setReportTime(DateUtils.getCurrDateStr());
		labQuaManageCheckVo.setCheckPeople(getSessionContainer().getUserName());
		labQuaManageCheckVo.setCheckTime(DateUtils.getCurrDateStr());
		
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
			labReportVo.setBusId(getSessionContainer().getFunId());
		}
		List<LabReportVo> reportTempList = labReportService.getLabReportList(labReportVo);
		setAttribute("tempList", reportTempList);
	    return PREADD;
	}
	public String addLabQuaManageCheck() throws GlobalException {
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		labQuaManageCheckService.addLabQuaManageCheck(labQuaManageCheckVo);
		return ADD;
	}
    
	public String preUpdateLabQuaManageCheck() throws GlobalException {
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		labQuaManageCheckVo=labQuaManageCheckService.getLabQuaManageCheck(labQuaManageCheckVo.getId());
		labOrgList=labOrgService.getLabOrgVoByRank("0");
		labQuaManageCheckVo.setReportPeople(getSessionContainer().getUserName());
		labQuaManageCheckVo.setReportTime(DateUtils.getCurrDateStr());
		labQuaManageCheckVo.setFilePeople(getSessionContainer().getUserName());
		labQuaManageCheckVo.setFileTime(DateUtils.getCurrDateStr());
		
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
			labReportVo.setBusId(getSessionContainer().getFunId());
		}
		List<LabReportVo> reportTempList = labReportService.getLabReportList(labReportVo);
		setAttribute("tempList", reportTempList);
	    return PREUPDATE;
	}
	public String updateLabQuaManageCheck() throws GlobalException {
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		labQuaManageCheckService.updateLabQuaManageCheck(labQuaManageCheckVo);
		return UPDATE;
	}
    public String deleteLabQuaManageCheck() throws GlobalException {
    	if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
    	labQuaManageCheckService.update2DelLabQuaManageCheck(labQuaManageCheckVo.getIds());
		return DELETE;
	}
    public String showLabQuaManageCheck() throws GlobalException {
    	if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
    	if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labQuaManageCheckVo=labQuaManageCheckService.getLabQuaManageCheck(labQuaManageCheckVo.getId());
    	return SHOW;
    }
    /**
	 * 报告查看
	 */
	public String showLabReport4Bus() throws GlobalException {
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		if( null == labReportVo) labReportVo = new LabReportVo();
		labQuaManageCheckVo=labQuaManageCheckService.getLabQuaManageCheck(labReportVo.getBusInsId());
		
		labReportVo = labReportService.getLabReport4Bus(labReportVo);
		labReportVo.setEditType("1");
		return SHOW;
	}
	/**
	 * 报告生成
	 */
	public String addLabReport4Bus() throws GlobalException {
		if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		if( null == labReportVo) labReportVo = new LabReportVo();
		labQuaManageCheckVo=labQuaManageCheckService.getLabQuaManageCheck(labReportVo.getBusInsId());
		// 获取报告模版信息
		labReportVo = labReportService.addLabReport4Bus(labReportVo);
		labQuaManageCheckVo.setReportTempId(labReportVo.getId());
		labQuaManageCheckVo.setReportPath(labReportVo.getPath());
		labQuaManageCheckService.updateLabQuaManageCheck4Report(labQuaManageCheckVo);
		return ADD;
	}
}
