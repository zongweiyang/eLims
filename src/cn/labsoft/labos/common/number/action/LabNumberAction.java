package cn.labsoft.labos.common.number.action;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.common.number.entity.LabNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.number.vo.LabNumberVo;

@Controller
@Scope("prototype")
public class LabNumberAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ILabNumberService labNumberService;
	private ILabCodeService labCodeService;
	private List<LabNumberVo> listLabNumberVo;
	private LabNumberVo labNumberVo;
	public List<LabNumberVo> getListLabNumberVo() {
		return listLabNumberVo;
	}
	public void setListLabNumberVo(List<LabNumberVo> listLabNumberVo) {
		this.listLabNumberVo = listLabNumberVo;
	}
	public LabNumberVo getLabNumberVo() {
		return labNumberVo;
	}
	public void setLabNumberVo(LabNumberVo labNumberVo) {
		this.labNumberVo = labNumberVo;
	}
	public String listLabNumber() throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		pageResult=labNumberService.getLabNumberPR(labNumberVo, pageResult);
		return LIST;
	}
	public String preAddLabNumber() throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		List<LabCodeVo> listLabCode=labCodeService.getLabCodeByTypeCode(LabNumber.CODE);
		getRequest().setAttribute("listLabCode", listLabCode);
		return PREADD;
	}
	public String addLabNumber() throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		labNumberVo=labNumberService.addLabNumber(labNumberVo);
		return ADD;
	}
	public String preUpdateLabNumber() throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		labNumberVo=labNumberService.getLabNumber(labNumberVo.getId());
		List<LabCodeVo> listLabCode=labCodeService.getLabCodeByTypeCode(LabNumber.CODE);
		getRequest().setAttribute("listLabCode", listLabCode);
		return PREUPDATE;
	}
	public String updateLabNumber() throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		labNumberService.updateLabNumber(labNumberVo);
		return UPDATE;
	}
	public String showLabNumber()  throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		labNumberVo=labNumberService.getLabNumber(labNumberVo.getId());
		return SHOW;
	}
	public String deleteLabNumber() throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		labNumberService.deleteLabNumber(labNumberVo.getIds());
		return DELETE;
	}
	public String updateLabNumber4Del()throws GlobalException{
		if(null==labNumberVo)labNumberVo=new LabNumberVo();
		labNumberService.updateLabNumber4Del(labNumberVo.getIds());
		return DELETE;
	}
	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	
}

