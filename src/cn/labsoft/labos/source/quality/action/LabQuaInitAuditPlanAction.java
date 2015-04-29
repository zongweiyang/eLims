package cn.labsoft.labos.source.quality.action;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.service.ILabQuaInitAuditPlanService;
import cn.labsoft.labos.source.quality.vo.LabQuaInitAuditPlanVo;

@Controller
@Scope("prototype")
public class LabQuaInitAuditPlanAction extends BaseAction {
	
	private ILabQuaInitAuditPlanService labQuaInitAuditPlanService;
    private LabQuaInitAuditPlanVo labQuaInitAuditPlanVo;
    private String parentIds;
    private String rank;
    
    public String getParentIds() {
		return parentIds;
	}
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	@Resource 
    public void setLabQuaInitAuditPlanService(
			ILabQuaInitAuditPlanService labQuaInitAuditPlanService) {
		this.labQuaInitAuditPlanService = labQuaInitAuditPlanService;
	}
	public LabQuaInitAuditPlanVo getLabQuaInitAuditPlanVo() {
		return labQuaInitAuditPlanVo;
	}
	public void setLabQuaInitAuditPlanVo(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo) {
		this.labQuaInitAuditPlanVo = labQuaInitAuditPlanVo;
	}
	public String listLabQuaInitAuditPlan() throws GlobalException{
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		pageResult=labQuaInitAuditPlanService.getLabQuaInitAuditPlanPR(labQuaInitAuditPlanVo, pageResult);
		return LIST;
	}
    //得到该要素下的子集
    public String listLabQuaInitAuditPlan4Child() throws GlobalException{
    	if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		parentIds=labQuaInitAuditPlanVo.getParentId();
		rank = labQuaInitAuditPlanVo.getRank();
		String key = labQuaInitAuditPlanVo.getKey();
		pageResult=labQuaInitAuditPlanService.getLabQuaInitAuditPlanPR(labQuaInitAuditPlanVo, pageResult);
		labQuaInitAuditPlanVo=labQuaInitAuditPlanService.getLabQuaInitAuditPlanVo(parentIds);
		labQuaInitAuditPlanVo.setParentId(parentIds);
		labQuaInitAuditPlanVo.setKey(key);
		if(rank.equals("2")){
			return "contentList";
		}else{
			return "keyList";
		}
	}
    public String preAddLabQuaInitAuditPlan() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
	    return PREADD;
	}
	public String preAddLabQuaInitAuditPlan4Child() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		rank = labQuaInitAuditPlanVo.getRank();
		parentIds = labQuaInitAuditPlanVo.getParentId();
		String key = labQuaInitAuditPlanVo.getKey();
		labQuaInitAuditPlanVo=labQuaInitAuditPlanService.getLabQuaInitAuditPlanVo(labQuaInitAuditPlanVo.getParentId());
		labQuaInitAuditPlanVo.setParentId(parentIds);
		labQuaInitAuditPlanVo.setRank(rank);
		labQuaInitAuditPlanVo.setKey(key);
		if(rank.equals("2")){
			return "preAddContent";
		}else{
			return "preAddKey";
		}
	}
	public String addLabQuaInitAuditPlan() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		labQuaInitAuditPlanService.addLabQuaInitAuditPlan(labQuaInitAuditPlanVo);
		return ADD;
	}
	public String addLabQuaInitAuditPlan4Child() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		String key = labQuaInitAuditPlanVo.getKey();
		labQuaInitAuditPlanService.addLabQuaInitAuditPlan(labQuaInitAuditPlanVo);
		labQuaInitAuditPlanVo.setKey(key);
		return ADD;
	}
    
	public String preUpdateLabQuaInitAuditPlan() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		labQuaInitAuditPlanVo=labQuaInitAuditPlanService.getLabQuaInitAuditPlanVo(labQuaInitAuditPlanVo.getId());
		 return PREUPDATE;
	}
	
	public String preUpdateLabQuaInitAuditPlan4Child() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		String key = labQuaInitAuditPlanVo.getKey();
		labQuaInitAuditPlanVo=labQuaInitAuditPlanService.getLabQuaInitAuditPlanVo(labQuaInitAuditPlanVo.getId());
		labQuaInitAuditPlanVo.setKey(key);
        if(labQuaInitAuditPlanVo.getRank().equals("2"))
	        return "preUpdateContent";
		else
			return "preUpdateKey";
	}
	
	public String updateLabQuaInitAuditPlan() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		labQuaInitAuditPlanService.updateLabQuaInitAuditPlan(labQuaInitAuditPlanVo);
		return UPDATE;
	}
	public String updateLabQuaInitAuditPlan4Child() throws GlobalException {
		if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		String key = labQuaInitAuditPlanVo.getKey();
		labQuaInitAuditPlanService.updateLabQuaInitAuditPlan(labQuaInitAuditPlanVo);
		labQuaInitAuditPlanVo.setKey(key);
		return UPDATE;
	}
    public String deleteLabQuaInitAuditPlan() throws GlobalException {
    	if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
    	labQuaInitAuditPlanService.update2DelLabQuaInitAuditPlan(labQuaInitAuditPlanVo.getIds());
		return DELETE;
	}
	
    public String deleteLabQuaInitAuditPlan4Child() throws GlobalException {
    	if(null==labQuaInitAuditPlanVo) labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
    	String key = labQuaInitAuditPlanVo.getKey();
    	labQuaInitAuditPlanService.update2DelLabQuaInitAuditPlan(labQuaInitAuditPlanVo.getIds());
    	labQuaInitAuditPlanVo.setKey(key);
    	return DELETE;
	}
}
