package cn.labsoft.labos.source.ambient.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.ambient.service.ILabAmbientService;
import cn.labsoft.labos.source.ambient.vo.LabAmbientVo;

@Controller
@Scope("prototype")
public class LabAmbientAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabAmbientService labAmbientService;
	private ILabCodeService labCodeService;
	private List<LabAmbientVo> listLabAmbientVo;
	private LabAmbientVo labAmbientVo;
	public List<LabAmbientVo> getListLabAmbientVo() {
		return listLabAmbientVo;
	}
	public void setListLabAmbientVo(List<LabAmbientVo> listLabAmbientVo) {
		this.listLabAmbientVo = listLabAmbientVo;
	}
	public LabAmbientVo getLabAmbientVo() {
		return labAmbientVo;
	}
	public void setLabAmbientVo(LabAmbientVo labAmbientVo) {
		this.labAmbientVo = labAmbientVo;
	}
	public String listLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		pageResult=labAmbientService.getLabAmbientPR(labAmbientVo, getPageResult());
		return LIST;
	}
	public String preAddLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		List<LabCodeVo> listLabCode=labCodeService.getLabCodeByTypeCode(Constants_Source.UNIT);
		getRequest().setAttribute("listLabCode", listLabCode);
		return PREADD;
	}
	public String addLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		labAmbientVo=labAmbientService.addLabAmbient(labAmbientVo);
		return ADD;
	}
	public String preUpdateLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		labAmbientVo=labAmbientService.getLabAmbient(labAmbientVo.getId());
		List<LabCodeVo> listLabCode=labCodeService.getLabCodeByTypeCode(Constants_Source.UNIT);
		getRequest().setAttribute("listLabCode", listLabCode);
		return PREUPDATE;
	}
	public String updateLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		boolean key=labAmbientService.updateLabAmbient(labAmbientVo);
		return UPDATE;
	}
	public String showLabAmbient()  throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		labAmbientVo=labAmbientService.getLabAmbient(labAmbientVo.getId());
		return SHOW;
	}
	public String deleteLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		labAmbientService.updateLabAmbient4Del(new String[]{labAmbientVo.getId()});
		return DELETE;
	}
	public String deleteBatchLabAmbient() throws GlobalException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		labAmbientService.updateLabAmbient4Del(labAmbientVo.getIds());
		return DELETEBATCH;
	}
	public void isDeleteLabAmbient() throws GlobalException, IOException{
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		String returnValue=labAmbientService.isDelete4LabAmbient(labAmbientVo.getIds());
		outPutString(returnValue);
	}
	@Resource
	public void setLabAmbientService(ILabAmbientService labAmbientService) {
		this.labAmbientService = labAmbientService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	
}
