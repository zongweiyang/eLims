package cn.labsoft.labos.common.makecode.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.common.makecode.entity.LabMakeCode;
import cn.labsoft.labos.common.makecode.service.ILabMakeCodeService;
import cn.labsoft.labos.common.makecode.vo.GenerateRun;
import cn.labsoft.labos.common.makecode.vo.LabMakeCodeVo;
import cn.labsoft.labos.common.makecode.vo.Packagee;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabMakeCodeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabMakeCodeService labMakeCodeService;
	@Resource
	private ILabWfProcessService labWfProcessService;
	private ILabCodeService labCodeService;
	private List<LabMakeCodeVo> listLabMakeCodeVo;
	private LabMakeCodeVo labMakeCodeVo;
	private Packagee packagee;
	private LabWfProcessVo processDefVo;
	
	public LabWfProcessVo getProcessDefVo() {
		return processDefVo;
	}
	public void setProcessDefVo(LabWfProcessVo processDefVo) {
		this.processDefVo = processDefVo;
	}
	public List<LabMakeCodeVo> getListLabMakeCodeVo() {
		return listLabMakeCodeVo;
	}
	public void setListLabMakeCodeVo(List<LabMakeCodeVo> listLabMakeCodeVo) {
		this.listLabMakeCodeVo = listLabMakeCodeVo;
	}
	public LabMakeCodeVo getLabMakeCodeVo() {
		return labMakeCodeVo;
	}
	public void setLabMakeCodeVo(LabMakeCodeVo labMakeCodeVo) {
		this.labMakeCodeVo = labMakeCodeVo;
	}
	public void initialise(){
		if(null==labMakeCodeVo)labMakeCodeVo=new LabMakeCodeVo();
	}
	public String listLabMakeCode() throws GlobalException{
		initialise();
		pageResult=labMakeCodeService.getLabMakeCodePR(labMakeCodeVo, getPageResult());
		return LIST;
	}
	public String preAddLabMakeCode() throws GlobalException{
		initialise();
		List<LabCodeVo> YZLXList=labCodeService.getLabCodeByTypeCode("YZLX");
		setAttribute("YZLXList", YZLXList);
		List<LabWfProcessVo> processList=labWfProcessService.getWfProcessList(new LabWfProcessVo());
		setAttribute("processList", processList);
		return PREADD;
	}
	@SuppressWarnings("deprecation")
	public String addLabMakeCode() throws GlobalException{
		initialise();
		if(packagee==null)packagee=new Packagee();
		labMakeCodeVo.setName(packagee.getName());
		//List<Prop> listProp=packagee.getClazz().getProp();
		labMakeCodeVo.setNameChin(packagee.getNameChinese());
		labMakeCodeVo.setClassName(packagee.getClazz().getClazzName());
		labMakeCodeVo.setClassNameChin(packagee.getClazz().getClazzCommon());
		labMakeCodeVo=labMakeCodeService.addLabMakeCode(labMakeCodeVo);
		if(!StrUtils.isBlankOrNull(packagee.getFlowId()))
		packagee.setFlowCode(labWfProcessService.getWfProcess(packagee.getFlowId()).getCode());
		String generatePath = getRequest().getRealPath("/").replace("\\WebRoot\\", "");
		GenerateRun.makeCode(generatePath, packagee);
		return ADD;
	}
	public void ajaxLabWfFunStep4Select() throws GlobalException{
		if(processDefVo==null)processDefVo=new LabWfProcessVo();
		List<LabWfFunStepVo> labWfFunStepList=labWfProcessService.getLabWfFunStepVo(processDefVo);
		ajax(labWfFunStepList);
	}
	public String preUpdateLabMakeCode() throws GlobalException{
		initialise();
		labMakeCodeVo=labMakeCodeService.getLabMakeCode(labMakeCodeVo.getId());
		return PREUPDATE;
	}
	public String updateLabMakeCode() throws GlobalException{
		initialise();
		labMakeCodeService.updateLabMakeCode(labMakeCodeVo);
		return UPDATE;
	}
	public String showLabMakeCode()  throws GlobalException{
		initialise();
		labMakeCodeVo=labMakeCodeService.getLabMakeCode(labMakeCodeVo.getId());
		return SHOW;
	}
	public String deleteLabMakeCode() throws GlobalException{
		initialise();
		labMakeCodeService.deleteLabMakeCode(new String[]{labMakeCodeVo.getId()});
		return DELETE;
	}
	public String deleteBatchLabMakeCode() throws GlobalException{
		initialise();
		labMakeCodeService.deleteLabMakeCode(labMakeCodeVo.getIds());
		return DELETEBATCH;
	}
	@SuppressWarnings("unchecked")
	public void ajaxValidate() throws GlobalException{
		List<LabCodeVo> listLabCode=labCodeService.getLabCodeByTypeCode(LabMakeCode.CODE);
		try {
			outPutString(JSONSerializer.toJSON(listLabCode).toString());
		} catch (IOException e) {
			Log4J.error("LabMakeCodeAction...",e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	public Packagee getPackagee() {
		return packagee;
	}
	public void setPackagee(Packagee packagee) {
		this.packagee = packagee;
	}
	@Resource
	public void setLabMakeCodeService(ILabMakeCodeService labMakeCodeService) {
		this.labMakeCodeService = labMakeCodeService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}

	
}
