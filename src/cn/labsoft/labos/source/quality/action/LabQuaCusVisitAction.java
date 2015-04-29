package cn.labsoft.labos.source.quality.action;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.customer.service.ILabCustomerService;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;
import cn.labsoft.labos.source.quality.service.ILabQuaCusVisitService;
import cn.labsoft.labos.source.quality.vo.LabQuaCusVisitVo;
import cn.labsoft.labos.utils.StrUtils;



@Controller
@Scope("prototype")
public class LabQuaCusVisitAction extends BaseAction {
	
	private ILabQuaCusVisitService labQuaCusVisitService;
	private ILabOrgService labOrgService ;
	private ILabCustomerService labCustomerService;
	
    private List<LabOrgVo>  unitOrgList;
    private List<LabOrgVo>  labOrgList;
    private LabQuaCusVisitVo labQuaCusVisitVo;
    private LabCustomerVo labCustomerVo;
    private List<LabCustomerVo> labCustomerVoList;
    
    @Resource 
    public void setLabQuaCusVisitService(
			ILabQuaCusVisitService labQuaCusVisitService) {
		this.labQuaCusVisitService = labQuaCusVisitService;
	}
    @Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
    @Resource
	public void setLabCustomerService(ILabCustomerService labCustomerService) {
		this.labCustomerService = labCustomerService;
	}
	public List<LabOrgVo> getUnitOrgList() {
		return unitOrgList;
	}
	public void setUnitOrgList(List<LabOrgVo> unitOrgList) {
		this.unitOrgList = unitOrgList;
	}
	public List<LabOrgVo> getLabOrgList() {
		return labOrgList;
	}
	public void setLabOrgList(List<LabOrgVo> labOrgList) {
		this.labOrgList = labOrgList;
	}
	public LabQuaCusVisitVo getLabQuaCusVisitVo() {
		return labQuaCusVisitVo;
	}
	public void setLabQuaCusVisitVo(LabQuaCusVisitVo labQuaCusVisitVo) {
		this.labQuaCusVisitVo = labQuaCusVisitVo;
	}
    public LabCustomerVo getLabCustomerVo() {
		return labCustomerVo;
	}
	public void setLabCustomerVo(LabCustomerVo labCustomerVo) {
		this.labCustomerVo = labCustomerVo;
	}
	public List<LabCustomerVo> getLabCustomerVoList() {
		return labCustomerVoList;
	}
	public void setLabCustomerVoList(List<LabCustomerVo> labCustomerVoList) {
		this.labCustomerVoList = labCustomerVoList;
	}
	public String listLabQuaCusVisit() throws GlobalException{
		if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
		pageResult=labQuaCusVisitService.getLabQuaCusVisitPR(labQuaCusVisitVo, pageResult);
		unitOrgList=labOrgService.getLabOrgVoByRank("0");
		labOrgList = labOrgService.getLabOrgListByPId(labQuaCusVisitVo.getUnitOrgIdSearch());
		return LIST;
	}
	public String preAddLabQuaCusVisit() throws GlobalException {
		unitOrgList=labOrgService.getLabOrgVoByRank("0");
		labOrgList = labOrgService.getLabOrgList(null);
	    return PREADD;
	}
	public String addLabQuaCusVisit() throws GlobalException {
		if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
		labQuaCusVisitService.addLabQuaCusVisit(labQuaCusVisitVo);
		return ADD;
	}
    
	public String preUpdateLabQuaCusVisit() throws GlobalException {
		if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
		labQuaCusVisitVo=labQuaCusVisitService.getLabQuaCusVisit(labQuaCusVisitVo.getId());
		unitOrgList=labOrgService.getLabOrgVoByRank("0");
		labOrgList = labOrgService.getLabOrgListByPId(labQuaCusVisitVo.getUnitOrgId());
	    return PREUPDATE;
	}	
	public String updateLabQuaCusVisit() throws GlobalException {
		if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
		labQuaCusVisitService.updateLabQuaCusVisit(labQuaCusVisitVo);
		return UPDATE;
	}
    public String deleteLabQuaCusVisit() throws GlobalException {
    	if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
    	labQuaCusVisitService.update2DelLabQuaCusVisit(labQuaCusVisitVo.getIds());
		return DELETE;
	}
    public String showLabQuaCusVisit() throws GlobalException {
    	if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
		labQuaCusVisitVo=labQuaCusVisitService.getLabQuaCusVisit(labQuaCusVisitVo.getId());
    	return SHOW;
    }
    public String ajaxLabQuaCusVisit4LabOrg()throws Exception{
		if(null==labQuaCusVisitVo) labQuaCusVisitVo=new LabQuaCusVisitVo();
		List<LabOrgVo> labOrgVolist= labOrgService.getLabOrgListByPId(labQuaCusVisitVo.getUnitOrgId());
		StringBuffer buffer=new StringBuffer("[");
		if(null!=labOrgVolist&&labOrgVolist.size()>0){
			for(LabOrgVo labOrgVo:labOrgVolist){
				String name=labOrgVo.getName();
				if(!StrUtils.isBlankOrNull(name)){
					name=name.replaceAll("'","‘");
				}
				buffer.append("{'id':'"+labOrgVo.getId()+"' ,'name':'"+name+"'},");
			}
			buffer=new StringBuffer(buffer.substring(0,buffer.length()-1));
		}
		buffer.append("]");
		outPutString(buffer.toString());
		return NONE;
	}
    public String showLabCustomer4select() throws GlobalException{
		if( null == labCustomerVo) labCustomerVo = new LabCustomerVo();
		labCustomerVoList = labCustomerService.getLabCustomerList(labCustomerVo);
		return LIST;
	}
    
}
