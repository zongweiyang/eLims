package cn.labsoft.labos.source.quality.action;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.service.ILabQuaProficiencyPlanService;
import cn.labsoft.labos.source.quality.service.ILabQuaProficiencyService;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyPlanVo;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyVo;

@Controller
@Scope("prototype")
public class LabQuaProficiencyAction extends BaseAction {
	
	private ILabQuaProficiencyService labQuaProficiencyService ;
	private ILabOrgService labOrgService;
	private ILabQuaProficiencyPlanService labQuaProficiencyPlanService;
    private List<LabOrgVo>  labOrgVoList;
    private LabQuaProficiencyVo labQuaProficiencyVo;
    private LabQuaProficiencyPlanVo labQuaProficiencyPlanVo;
    private List<LabQuaProficiencyPlanVo> labQuaProficiencyPlanVoList;
    
    @Resource 
    public void setLabQuaProficiencyService(
			ILabQuaProficiencyService labQuaProficiencyService) {
		this.labQuaProficiencyService = labQuaProficiencyService;
	}
    @Resource 
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
    @Resource 
	public void setLabQuaProficiencyPlanService(
			ILabQuaProficiencyPlanService labQuaProficiencyPlanService) {
		this.labQuaProficiencyPlanService = labQuaProficiencyPlanService;
	}
	public List<LabOrgVo> getLabOrgVoList() {
		return labOrgVoList;
	}
	public void setLabOrgVoList(List<LabOrgVo> labOrgVoList) {
		this.labOrgVoList = labOrgVoList;
	}
	public LabQuaProficiencyVo getLabQuaProficiencyVo() {
		return labQuaProficiencyVo;
	}
	public void setLabQuaProficiencyVo(LabQuaProficiencyVo labQuaProficiencyVo) {
		this.labQuaProficiencyVo = labQuaProficiencyVo;
	}
	public LabQuaProficiencyPlanVo getLabQuaProficiencyPlanVo() {
		return labQuaProficiencyPlanVo;
	}
	public void setLabQuaProficiencyPlanVo(
			LabQuaProficiencyPlanVo labQuaProficiencyPlanVo) {
		this.labQuaProficiencyPlanVo = labQuaProficiencyPlanVo;
	}
	public List<LabQuaProficiencyPlanVo> getLabQuaProficiencyPlanVoList() {
		return labQuaProficiencyPlanVoList;
	}
	public void setLabQuaProficiencyPlanVoList(
			List<LabQuaProficiencyPlanVo> labQuaProficiencyPlanVoList) {
		this.labQuaProficiencyPlanVoList = labQuaProficiencyPlanVoList;
	}
	private void initHeader()throws GlobalException{
		if(null==labQuaProficiencyVo) labQuaProficiencyVo=new LabQuaProficiencyVo();
		if(null==labQuaProficiencyPlanVo) labQuaProficiencyPlanVo=new LabQuaProficiencyPlanVo();
	} 
	public String listLabQuaProficiency() throws GlobalException{
		initHeader();
		pageResult=labQuaProficiencyService.getLabQuaProficiencyPR(labQuaProficiencyVo, pageResult);
		labOrgVoList=labOrgService.getLabOrgVoByRank("1");
		labQuaProficiencyPlanVoList=labQuaProficiencyPlanService.getLabQuaProficiencyPlanList(labQuaProficiencyPlanVo);
		return LIST;
	}
	public String preAddLabQuaProficiency() throws GlobalException {
		initHeader();
		labOrgVoList=labOrgService.getLabOrgVoByRank("1");
		labQuaProficiencyPlanVo = labQuaProficiencyPlanService.getLabQuaProficiencyPlan(labQuaProficiencyVo.getProficiencyPlanId());
	    return PREADD;
	}
	public String showLabQuaProficiencyPlan4select()throws GlobalException{
		initHeader();
		labQuaProficiencyPlanVoList=labQuaProficiencyPlanService.getLabQuaProficiencyPlanList(labQuaProficiencyPlanVo);
		labOrgVoList=labOrgService.getLabOrgVoByRank("1");
		return SHOW;
	}
	public String addLabQuaProficiency() throws GlobalException {
		initHeader();
		labQuaProficiencyService.addLabQuaProficiency(labQuaProficiencyVo);
		return ADD;
	}
    
	public String preUpdateLabQuaProficiency() throws GlobalException {
		initHeader();
		labQuaProficiencyVo=labQuaProficiencyService.getLabQuaProficiency(labQuaProficiencyVo.getId());
		labOrgVoList=labOrgService.getLabOrgVoByRank("1");
	    return PREUPDATE;
	}
	public String updateaddLabQuaProficiency() throws GlobalException {
		initHeader();
		labQuaProficiencyService.updateLabQuaProficiency(labQuaProficiencyVo);
		return UPDATE;
	}
    public String deleteLabQuaProficiency() throws GlobalException {
    	initHeader();
    	labQuaProficiencyService.update2DelLabQuaProficiency(labQuaProficiencyVo.getIds());
		return DELETE;
	}
    public String showLabQuaProficiency() throws GlobalException {
		initHeader();
		labQuaProficiencyVo=labQuaProficiencyService.getLabQuaProficiency(labQuaProficiencyVo.getId());
	    return SHOW;
	}
}
