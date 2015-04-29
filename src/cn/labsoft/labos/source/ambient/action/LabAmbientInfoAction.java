package cn.labsoft.labos.source.ambient.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.ambient.service.ILabAmbientInfoService;
import cn.labsoft.labos.source.ambient.service.ILabAmbientService;
import cn.labsoft.labos.source.ambient.vo.LabAmbientInfoVo;
import cn.labsoft.labos.source.ambient.vo.LabAmbientVo;

@Controller
@Scope("prototype")
public class LabAmbientInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabAmbientInfoService labAmbientInfoService;
	private ILabAmbientService labAmbientService;
	private List<LabAmbientInfoVo> listLabAmbientInfoVo;
	private LabAmbientInfoVo labAmbientInfoVo;
	private LabAmbientVo labAmbientVo;
	private List<LabAmbientVo> listLabAmbientVo;
	public LabAmbientVo getLabAmbientVo() {
		return labAmbientVo;
	}
	public void setLabAmbientVo(LabAmbientVo labAmbientVo) {
		this.labAmbientVo = labAmbientVo;
	}
	public List<LabAmbientInfoVo> getListLabAmbientInfoVo() {
		return listLabAmbientInfoVo;
	}
	public void setListLabAmbientInfoVo(List<LabAmbientInfoVo> listLabAmbientInfoVo) {
		this.listLabAmbientInfoVo = listLabAmbientInfoVo;
	}
	public LabAmbientInfoVo getLabAmbientInfoVo() {
		return labAmbientInfoVo;
	}
	public void setLabAmbientInfoVo(LabAmbientInfoVo labAmbientInfoVo) {
		this.labAmbientInfoVo = labAmbientInfoVo;
	}
	public void initialise(){
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
	}
	public String listLabAmbientInfo() throws GlobalException{
		if(labAmbientInfoVo==null)
		labAmbientInfoVo=new LabAmbientInfoVo();
		pageResult=labAmbientInfoService.getLabAmbientInfoPR(labAmbientInfoVo, getPageResult());
		return LIST;
	}
	public String preAddLabAmbientInfo() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		listLabAmbientVo=labAmbientService.getLabAmbientList(labAmbientVo);
		getRequest().setAttribute("listLabAmbientVo", listLabAmbientVo);
		return PREADD;
	}
	public String addLabAmbientInfo() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoVo=labAmbientInfoService.addLabAmbientInfo(labAmbientInfoVo);
		return ADD;
	}
	public String preUpdateLabAmbientInfo() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoVo=labAmbientInfoService.getLabAmbientInfo(labAmbientInfoVo.getId());
		listLabAmbientVo=labAmbientService.getLabAmbientList(labAmbientVo);
		getRequest().setAttribute("listLabAmbientVo", listLabAmbientVo);
		return PREUPDATE;
	}
	public String updateLabAmbientInfo() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		boolean key=labAmbientInfoService.updateLabAmbientInfo(labAmbientInfoVo);
		return UPDATE;
	}
	public String showLabAmbientInfo()  throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoVo=labAmbientInfoService.getLabAmbientInfo(labAmbientInfoVo.getId());
		return SHOW;
	}
	public String deleteLabAmbientInfo() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoService.deleteLabAmbientInfo(new String[]{labAmbientInfoVo.getId()});
		return DELETE;
	}
	public String deleteBatchLabAmbientInfo() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoService.deleteLabAmbientInfo(labAmbientInfoVo.getIds());
		return DELETEBATCH;
	}
	public String listOverrun() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		pageResult=labAmbientInfoService.getLabOverrunPR(labAmbientInfoVo, pageResult);
		return LIST;
	}
	public String showOverrun()  throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoVo=labAmbientInfoService.getLabAmbientInfo(labAmbientInfoVo.getId());
		return SHOW;
	}
	public String listAnalyse() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		pageResult=labAmbientInfoService.getLabAmbientInfoPR(labAmbientInfoVo, pageResult);
		listLabAmbientInfoVo=labAmbientInfoService.getLabAmbientInfoName();
		getRequest().setAttribute("listLabAmbientInfoVo", listLabAmbientInfoVo);
		return LIST;
	}
	public String preJFreeChart() throws GlobalException{
		if(null==labAmbientInfoVo)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoVo=labAmbientInfoService.getLabAmbientInfoInfo(labAmbientInfoVo);
		return LIST;
	}
	public String jFreeChart() throws GlobalException{
		return SHOW;
	}
	@Resource
	public void setLabAmbientInfoService(
			ILabAmbientInfoService labAmbientInfoService) {
		this.labAmbientInfoService = labAmbientInfoService;
	}
	@Resource
	public void setLabAmbientService(ILabAmbientService labAmbientService) {
		this.labAmbientService = labAmbientService;
	}
	public void setListLabAmbientVo(List<LabAmbientVo> listLabAmbientVo) {
		this.listLabAmbientVo = listLabAmbientVo;
	}
	 
	
}
