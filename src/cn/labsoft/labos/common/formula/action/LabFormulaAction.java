package cn.labsoft.labos.common.formula.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.common.formula.service.ILabFormulaService;
import cn.labsoft.labos.common.formula.vo.LabFormulaVo;

@Controller
@Scope("prototype")
public class LabFormulaAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private ILabFormulaService labFormulaService;
	private List<LabFormulaVo> listLabFormulaVo;
	private LabFormulaVo labFormulaVo;
	public List<LabFormulaVo> getListLabFormulaVo() {
		return listLabFormulaVo;
	}
	public void setListLabFormulaVo(List<LabFormulaVo> listLabFormulaVo) {
		this.listLabFormulaVo = listLabFormulaVo;
	}
	public LabFormulaVo getLabFormulaVo() {
		return labFormulaVo;
	}
	public void setLabFormulaVo(LabFormulaVo labFormulaVo) {
		this.labFormulaVo = labFormulaVo;
	}
	public void initialise(){
		if(null==labFormulaVo)labFormulaVo=new LabFormulaVo();
	}
	public String listLabFormula() throws GlobalException{
		initialise();
		pageResult=labFormulaService.getLabFormulaPR(labFormulaVo, pageResult);
		return LIST;
	}
	public String preAddLabFormula(){
		initialise();
		labFormulaVo.setDefineUser(getSessionContainer().getUserName());
		return PREADD;
	}
	public String addLabFormula() throws GlobalException{
		initialise();
		labFormulaVo=labFormulaService.addLabFormula(labFormulaVo);
		return ADD;
	}
	public String preUpdateLabFormula() throws GlobalException{
		initialise();
		labFormulaVo=labFormulaService.getLabFormula(labFormulaVo.getId());
		return PREUPDATE;
	}
	public String updateLabFormula() throws GlobalException{
		initialise();
		labFormulaService.updateLabFormula(labFormulaVo);
		return UPDATE;
	}
	public String showLabFormula()  throws GlobalException{
		initialise();
		labFormulaVo=labFormulaService.getLabFormula(labFormulaVo.getId());
		return SHOW;
	}
	public String deleteLabFormula() throws GlobalException{
		initialise();
		labFormulaService.deleteLabFormula(new String[]{labFormulaVo.getId()});
		return DELETE;
	}
	public String deleteBatchLabFormula() throws GlobalException{
		initialise();
		//labFormulaService.deleteLabFormula(labFormulaVo.getIds());
		labFormulaService.updateLabFormula4Del(labFormulaVo.getIds());
		return DELETEBATCH;
	}
	public void ajaxLabFormulaParameter() throws IOException, GlobalException{
		initialise();
		String name="";
		try{
			labFormulaVo=labFormulaService.getLabFormula(labFormulaVo.getId());
			if(!StrUtils.isBlankOrNull(labFormulaVo.getParameter())){
				name=labFormulaVo.getParameter();
			}
		}catch(Exception e){
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		outPutString(name);
	}
	public String showFormula() throws GlobalException{
		initialise();
		labFormulaVo=labFormulaService.getLabFormula(labFormulaVo.getId());
		return SHOW;
	}

	
}
