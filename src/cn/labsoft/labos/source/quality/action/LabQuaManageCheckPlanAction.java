package cn.labsoft.labos.source.quality.action;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.service.ILabQuaManageCheckPlanService;
import cn.labsoft.labos.source.quality.service.ILabQuaManageCheckService;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckPlanVo;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckVo;
import cn.labsoft.labos.utils.DateUtils;


@Controller
@Scope("prototype")
public class LabQuaManageCheckPlanAction extends BaseAction {
	
	private ILabQuaManageCheckPlanService labQuaManageCheckPlanService ;
	private ILabQuaManageCheckService labQuaManageCheckService ;
	private ILabOrgService labOrgService ;

    private List<LabOrgVo>  labOrgVoList;
    private LabQuaManageCheckPlanVo labQuaManageCheckPlanVo;
    private LabQuaManageCheckVo labQuaManageCheckVo;
    private List<LabQuaManageCheckVo>  labQuaManageCheckVoList;
    
    @Resource 
    public void setLabQuaManageCheckPlanService(ILabQuaManageCheckPlanService labQuaManageCheckPlanService) {
		this.labQuaManageCheckPlanService = labQuaManageCheckPlanService;
	}
    @Resource 
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
    @Resource 
    public void setLabQuaManageCheckService(
			ILabQuaManageCheckService labQuaManageCheckService) {
		this.labQuaManageCheckService = labQuaManageCheckService;
	}
	public LabQuaManageCheckVo getLabQuaManageCheckVo() {
		return labQuaManageCheckVo;
	}
	public void setLabQuaManageCheckVo(LabQuaManageCheckVo labQuaManageCheckVo) {
		this.labQuaManageCheckVo = labQuaManageCheckVo;
	}
	public List<LabQuaManageCheckVo> getLabQuaManageCheckVoList() {
		return labQuaManageCheckVoList;
	}
	public void setLabQuaManageCheckVoList(
			List<LabQuaManageCheckVo> labQuaManageCheckVoList) {
		this.labQuaManageCheckVoList = labQuaManageCheckVoList;
	}
	public List<LabOrgVo> getLabOrgVoList() {
		return labOrgVoList;
	}
	public void setLabOrgVoList(List<LabOrgVo> labOrgVoList) {
		this.labOrgVoList = labOrgVoList;
	}
	public LabQuaManageCheckPlanVo getLabQuaManageCheckPlanVo() {
		return labQuaManageCheckPlanVo;
	}
	public void setLabQuaManageCheckPlanVo(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo) {
		this.labQuaManageCheckPlanVo = labQuaManageCheckPlanVo;
	}
	public String listLabQuaManageCheckPlan() throws GlobalException{
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		pageResult=labQuaManageCheckPlanService.getLabQuaManageCheckPlanPR(labQuaManageCheckPlanVo, pageResult);
		labOrgVoList=labOrgService.getLabOrgVoByRank("0");
		return LIST;
	}
	public String preAddLabQuaManageCheckPlan() throws GlobalException {
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labOrgVoList=labOrgService.getLabOrgVoByRank("0");
		labQuaManageCheckPlanVo.setPlanUser(getSessionContainer().getUserName());
		labQuaManageCheckPlanVo.setPlanTime(DateUtils.getCurrDateStr());
	    return PREADD;
	}
	public String addLabQuaManageCheckPlan() throws GlobalException {
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labQuaManageCheckPlanService.addLabQuaManageCheckPlan(labQuaManageCheckPlanVo);
		return ADD;
	}
    
	public String preUpdateLabQuaManageCheckPlan() throws GlobalException {
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labQuaManageCheckPlanVo=labQuaManageCheckPlanService.getLabQuaManageCheckPlan(labQuaManageCheckPlanVo.getId());
		labOrgVoList=labOrgService.getLabOrgVoByRank("0");
	    return PREUPDATE;
	}
	public String updateLabQuaManageCheckPlan() throws GlobalException {
		if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		labQuaManageCheckPlanService.updateLabQuaManageCheckPlan(labQuaManageCheckPlanVo);
		return UPDATE;
	}
    public String deleteLabQuaManageCheckPlan() throws GlobalException {
    	if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
    	labQuaManageCheckPlanService.update2DelLabQuaManageCheckPlan(labQuaManageCheckPlanVo.getIds());
		return DELETE;
	}
    public String showLabQuaManageCheckPlan() throws GlobalException {
    	if(null==labQuaManageCheckPlanVo) labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
    	if(null==labQuaManageCheckVo) labQuaManageCheckVo=new LabQuaManageCheckVo();
		labQuaManageCheckPlanVo=labQuaManageCheckPlanService.getLabQuaManageCheckPlan(labQuaManageCheckPlanVo.getId());
		labQuaManageCheckVo.setQuaManageCheckPlanId(labQuaManageCheckPlanVo.getId());
		labQuaManageCheckVoList = labQuaManageCheckService.getLabQuaManageCheckList(labQuaManageCheckVo);
    	return SHOW;
    }
}
