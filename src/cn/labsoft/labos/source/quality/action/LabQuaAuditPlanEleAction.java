package cn.labsoft.labos.source.quality.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.service.ILabQuaAuditPlanEleService;
import cn.labsoft.labos.source.quality.service.ILabQuaAuditRecordService;
import cn.labsoft.labos.source.quality.service.ILabQuaInitAuditPlanService;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditPlanEleVo;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditRecordVo;
import cn.labsoft.labos.source.quality.vo.LabQuaInitAuditPlanVo;

@Controller
@Scope("prototype")
public class LabQuaAuditPlanEleAction extends BaseAction {
	
	private ILabQuaAuditPlanEleService labQuaAuditPlanEleService;
	private ILabQuaInitAuditPlanService labQuaInitAuditPlanService;
	private ILabQuaAuditRecordService labQuaAuditRecordService;
	private ILabOrgService labOrgService ;
    private LabQuaAuditPlanEleVo labQuaAuditPlanEleVo;
    private LabQuaAuditRecordVo labQuaAuditRecordVo;
    private List<LabOrgVo> orgList;
    private List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList;
    private List<LabQuaAuditRecordVo> labQuaAuditRecordVoList;
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
    public void setLabQuaAuditRecordService(
			ILabQuaAuditRecordService labQuaAuditRecordService) {
		this.labQuaAuditRecordService = labQuaAuditRecordService;
	}
	public LabQuaAuditRecordVo getLabQuaAuditRecordVo() {
		return labQuaAuditRecordVo;
	}
	public void setLabQuaAuditRecordVo(LabQuaAuditRecordVo labQuaAuditRecordVo) {
		this.labQuaAuditRecordVo = labQuaAuditRecordVo;
	}
	public List<LabQuaAuditRecordVo> getLabQuaAuditRecordVoList() {
		return labQuaAuditRecordVoList;
	}
	public void setLabQuaAuditRecordVoList(
			List<LabQuaAuditRecordVo> labQuaAuditRecordVoList) {
		this.labQuaAuditRecordVoList = labQuaAuditRecordVoList;
	}
	public LabQuaAuditPlanEleVo getLabQuaAuditPlanEleVo() {
		return labQuaAuditPlanEleVo;
	}
	public void setLabQuaAuditPlanEleVo(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo) {
		this.labQuaAuditPlanEleVo = labQuaAuditPlanEleVo;    
	}
    public List<LabOrgVo> getOrgList() {
		return orgList;
	}
	public void setOrgList(List<LabOrgVo> orgList) {
		this.orgList = orgList;
	}
	public List<LabQuaInitAuditPlanVo> getLabQuaInitAuditPlanVoList() {
		return labQuaInitAuditPlanVoList;
	}
	public void setLabQuaInitAuditPlanVoList(
			List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList) {
		this.labQuaInitAuditPlanVoList = labQuaInitAuditPlanVoList;
	}
	public String listLabQuaAuditPlanEle() throws GlobalException{
		if(null==labQuaAuditPlanEleVo) labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
		pageResult=labQuaAuditPlanEleService.getLabQuaAuditPlanElePR(labQuaAuditPlanEleVo, pageResult);
		orgList=labOrgService.getLabOrgVoByRank("1");
		return LIST;
	}
	public String preAddLabQuaAuditPlanEle() throws GlobalException {
		labQuaInitAuditPlanVoList=labQuaInitAuditPlanService.getLabQuaInitAuditPlanList(null);
		orgList=labOrgService.getLabOrgVoByRank("1");
		return PREADD;
	} 
	public String addLabQuaAuditPlanEle() throws GlobalException {
		if(null==labQuaAuditPlanEleVo) labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
		labQuaAuditPlanEleService.addLabQuaAuditPlanEle(labQuaAuditPlanEleVo);
		return ADD;
	}
	public String preUpdateLabQuaAuditPlanEle() throws GlobalException {
		if(null==labQuaAuditPlanEleVo) labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
		labQuaAuditPlanEleVo=labQuaAuditPlanEleService.getLabQuaAuditPlanEleVo(labQuaAuditPlanEleVo.getId());
		orgList=labOrgService.getLabOrgVoByRank("1");
	    return PREUPDATE;
	}
	public String updateLabQuaAuditPlanEle() throws GlobalException {
		if(null==labQuaAuditPlanEleVo) labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
		labQuaAuditPlanEleService.updateLabQuaAuditPlanEle(labQuaAuditPlanEleVo);
		return UPDATE;
	}
	public String showLabQuaAuditPlanEle() throws GlobalException {
		if(null==labQuaAuditPlanEleVo) labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
		if(null==labQuaAuditRecordVo) labQuaAuditRecordVo=new LabQuaAuditRecordVo();
		labQuaAuditPlanEleVo=labQuaAuditPlanEleService.getLabQuaAuditPlanEleVo(labQuaAuditPlanEleVo.getId());
		labQuaAuditRecordVo.setQuaAuditPlanEleId(labQuaAuditPlanEleVo.getId());
		labQuaAuditRecordVoList = labQuaAuditRecordService.getLabQuaAuditRecordList(labQuaAuditRecordVo);
		return SHOW;
	}
    public String deleteLabQuaAuditPlanEle() throws GlobalException {
    	if(null==labQuaAuditPlanEleVo) labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
    	labQuaAuditPlanEleService.update2DelLabQuaAuditPlanEle(labQuaAuditPlanEleVo.getIds());
		return DELETE;
	}
    
}
