package cn.labsoft.labos.common.formula.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.common.formula.service.ILabFormulaApplyService;
import cn.labsoft.labos.common.formula.service.ILabFormulaService;
import cn.labsoft.labos.common.formula.vo.LabFormulaApplyVo;
import cn.labsoft.labos.common.formula.vo.LabFormulaVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;

@Controller
@Scope("prototype")
public class LabFormulaApplyAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ILabFormulaApplyService labFormulaApplyService;
	private ILabFormulaService labFormulaService;
	private List<LabFormulaApplyVo> listLabFormulaApplyVo;
	private List<LabFormulaVo> listLabFormulaVo;
	private LabFormulaApplyVo labFormulaApplyVo;
	private LabFormulaVo labFormulaVo;
	private String result;

	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
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
	public List<LabFormulaApplyVo> getListLabFormulaApplyVo() {
		return listLabFormulaApplyVo;
	}
	public void setListLabFormulaApplyVo(List<LabFormulaApplyVo> listLabFormulaApplyVo) {
		this.listLabFormulaApplyVo = listLabFormulaApplyVo;
	}
	public LabFormulaApplyVo getLabFormulaApplyVo() {
		return labFormulaApplyVo;
	}
	public void setLabFormulaApplyVo(LabFormulaApplyVo labFormulaApplyVo) {
		this.labFormulaApplyVo = labFormulaApplyVo;
	}
	public void initialise(){
		if(null==labFormulaApplyVo)labFormulaApplyVo=new LabFormulaApplyVo();
		if(null==labFormulaVo)labFormulaVo=new LabFormulaVo();
	}
	public String listLabFormulaApply() throws GlobalException{
		initialise();
		pageResult=labFormulaApplyService.getLabFormulaApplyPR(labFormulaApplyVo, pageResult);
		return LIST;
	}
	public String preAddLabFormulaApply() throws GlobalException{
		initialise();
		listLabFormulaVo=labFormulaService.getLabFormulaList(labFormulaVo);
		setAttribute("listLabFormula", listLabFormulaVo);
		return PREADD;
	}
	public String addLabFormulaApply() throws GlobalException{
		initialise();
		labFormulaApplyVo=labFormulaApplyService.addLabFormulaApply(labFormulaApplyVo);
		return ADD;
	}
	public String preUpdateLabFormulaApply() throws GlobalException{
		initialise();
		labFormulaApplyVo=labFormulaApplyService.getLabFormulaApply(labFormulaApplyVo.getId());
		listLabFormulaVo=labFormulaService.getLabFormulaList(labFormulaVo);
		setAttribute("listLabFormula", listLabFormulaVo);
		return PREUPDATE;
	}
	public String updateLabFormulaApply() throws GlobalException{
		initialise();
		labFormulaApplyService.updateLabFormulaApply(labFormulaApplyVo);
		return UPDATE;
	}
	public String showLabFormulaApply()  throws GlobalException{
		initialise();
		labFormulaApplyVo=labFormulaApplyService.getLabFormulaApply(labFormulaApplyVo.getId());
		return SHOW;
	}
	public String deleteLabFormulaApply(){
		initialise();
		labFormulaApplyService.deleteLabFormulaApply(new String[]{labFormulaApplyVo.getId()});
		return DELETE;
	}
	public String deleteBatchLabFormulaApply(){
		initialise();
		labFormulaApplyService.deleteLabFormulaApply(labFormulaApplyVo.getIds());
		return DELETEBATCH;
	}
	public void ajaxReckonFormula() throws IOException, GlobalException{
		initialise();
		String number="";
		number=labFormulaApplyService.getResult(labFormulaApplyVo.getResult(), labFormulaApplyVo.getId());
		outPutString(number);
	}
	public void ajaxFormulaApply() throws GlobalException, IOException{
		initialise();
		labFormulaApplyVo=labFormulaApplyService.getLabFormulaApply(labFormulaApplyVo.getId());
		outPutString(JSONSerializer.toJSON(labFormulaApplyVo).toString());
	}
	@Resource
	public void setLabFormulaApplyService(
			ILabFormulaApplyService labFormulaApplyService) {
		this.labFormulaApplyService = labFormulaApplyService;
	}
	@Resource
	public void setLabFormulaService(ILabFormulaService labFormulaService) {
		this.labFormulaService = labFormulaService;
	}

	
}
