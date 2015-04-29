package cn.labsoft.labos.source.quality.action;


import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.business.sample.service.ILabSampRegisterService;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.quality.service.ILabQuaProficiencyPlanService;
import cn.labsoft.labos.source.quality.service.ILabQuaProficiencyService;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyPlanVo;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabQuaProficiencyPlanAction extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -400224148711739117L;
	private ILabQuaProficiencyPlanService labQuaProficiencyPlanService ;
	private ILabQuaProficiencyService labQuaProficiencyService ;
	private ILabOrgService labOrgService ;
	private ILabNumberService labNumberService;
	private ILabSampRegisterService labSampRegisterService;
	private ILabSamTypeService labSamTypeService;
	
    private List<LabOrgVo>  labOrgList;
    private LabQuaProficiencyPlanVo labQuaProficiencyPlanVo;
    private LabQuaProficiencyVo labQuaProficiencyVo;
    private List<LabQuaProficiencyVo> labQuaProficiencyVoList;
    private LabSampRegisterVo labSampRegisterVo;
    private List<LabSamVo> sampList;
    
    public List<LabSamVo> getSampList() {
		return sampList;
	}
	public void setSampList(List<LabSamVo> sampList) {
		this.sampList = sampList;
	}
	public LabSampRegisterVo getLabSampRegisterVo() {
		return labSampRegisterVo;
	}
	public void setLabSampRegisterVo(LabSampRegisterVo labSampRegisterVo) {
		this.labSampRegisterVo = labSampRegisterVo;
	}
	@Resource
    public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	@Resource
    public void setLabSamTypeService(ILabSamTypeService labSamTypeService) {
		this.labSamTypeService = labSamTypeService;
	}
	@Resource
	public void setLabSampRegisterService(
			ILabSampRegisterService labSampRegisterService) {
		this.labSampRegisterService = labSampRegisterService;
	}

	@Resource 
    public void setLabQuaProficiencyPlanService(
			ILabQuaProficiencyPlanService labQuaProficiencyPlanService) {
		this.labQuaProficiencyPlanService = labQuaProficiencyPlanService;
	}
    @Resource 
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
    @Resource 
    public void setLabQuaProficiencyService(
			ILabQuaProficiencyService labQuaProficiencyService) {
		this.labQuaProficiencyService = labQuaProficiencyService;
	}
	public LabQuaProficiencyVo getLabQuaProficiencyVo() {
		return labQuaProficiencyVo;
	}
	public void setLabQuaProficiencyVo(LabQuaProficiencyVo labQuaProficiencyVo) {
		this.labQuaProficiencyVo = labQuaProficiencyVo;
	}
	public List<LabQuaProficiencyVo> getLabQuaProficiencyVoList() {
		return labQuaProficiencyVoList;
	}
	public void setLabQuaProficiencyVoList(
			List<LabQuaProficiencyVo> labQuaProficiencyVoList) {
		this.labQuaProficiencyVoList = labQuaProficiencyVoList;
	}
	public List<LabOrgVo> getLabOrgList() {
		return labOrgList;
	}
	public void setLabOrgList(List<LabOrgVo> labOrgList) {
		this.labOrgList = labOrgList;
	}
	public LabQuaProficiencyPlanVo getLabQuaProficiencyPlanVo() {
		return labQuaProficiencyPlanVo;
	}
	public void setLabQuaProficiencyPlanVo(
			LabQuaProficiencyPlanVo labQuaProficiencyPlanVo) {
		this.labQuaProficiencyPlanVo = labQuaProficiencyPlanVo;
	}
    public String listLabQuaProficiencyPlan() throws GlobalException{
		if(null==labQuaProficiencyPlanVo) labQuaProficiencyPlanVo=new LabQuaProficiencyPlanVo();
		pageResult=labQuaProficiencyPlanService.getLabQuaProficiencyPlanPR(labQuaProficiencyPlanVo, pageResult);
		labOrgList=labOrgService.getLabOrgVoByRank("1");
		return LIST;
	}
	@SuppressWarnings("unchecked")
	public String preAddLabQuaProficiencyPlan() throws GlobalException {
		if(null==labQuaProficiencyPlanVo){
			labQuaProficiencyPlanVo=new LabQuaProficiencyPlanVo();
			labQuaProficiencyPlanVo.setContPeople(getSessionContainer().getUserName());
			labQuaProficiencyPlanVo.setContDate(DateUtils.getCurrDateStr());
		}
		labOrgList=labOrgService.getLabOrgVoByRank("1");
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,null,
				Constants_Source.CODE_RW, new String[] {},Constants_Source.CODE_USE_INIT);
		try {
			labQuaProficiencyPlanVo.setSampRegisterNo(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			Log4J.error(e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
	    return PREADD;
	}
	public String addLabQuaProficiencyPlan() throws GlobalException {
		if (null == labQuaProficiencyPlanVo) labQuaProficiencyPlanVo = new LabQuaProficiencyPlanVo();
		labQuaProficiencyPlanService.addLabQuaProficiencyPlan(labQuaProficiencyPlanVo);
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getAuditResult()) && labQuaProficiencyPlanVo.getAuditResult().equals(Constants_Source.TRUE)){
			return ADD;
		}else
		return PREUPDATE;
	}
	public String showLabQuaProficiencyPlan() throws GlobalException {
		if (null == labQuaProficiencyPlanVo) labQuaProficiencyPlanVo = new LabQuaProficiencyPlanVo();
		if (null == labQuaProficiencyVo) labQuaProficiencyVo = new LabQuaProficiencyVo();
		labQuaProficiencyPlanVo=labQuaProficiencyPlanService.getLabQuaProficiencyPlan(labQuaProficiencyPlanVo.getId());
		labQuaProficiencyVo.setProficiencyPlanId(labQuaProficiencyPlanVo.getId());
		labQuaProficiencyVoList = labQuaProficiencyService.getLabQuaProficiencyList(labQuaProficiencyVo);
	    return SHOW;
	}
	public String preUpdateLabQuaProficiencyPlan() throws GlobalException {
		if (null == labQuaProficiencyPlanVo) labQuaProficiencyPlanVo = new LabQuaProficiencyPlanVo();
		if (null == labSampRegisterVo) labSampRegisterVo = new LabSampRegisterVo();
		labQuaProficiencyPlanVo=labQuaProficiencyPlanService.getLabQuaProficiencyPlan(labQuaProficiencyPlanVo.getId());
		if(StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getSampRegisterNo())){
			ThreadNumber threadNumber = new ThreadNumber(labNumberService,null,
					Constants_Source.CODE_RW, new String[] {},Constants_Source.CODE_USE_INIT);
			try {
				labQuaProficiencyPlanVo.setSampRegisterNo(pool.submit(threadNumber).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		labOrgList=labOrgService.getLabOrgVoByRank("1");
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getSampRegisterNum())){
			labSampRegisterVo.setId(labQuaProficiencyPlanVo.getSampRegisterId());
			labSampRegisterVo.setSampNo(labQuaProficiencyPlanVo.getSampRegisterSampNo());
			labSampRegisterVo.setSampNum(labQuaProficiencyPlanVo.getSampRegisterNum());
			sampList = labSampRegisterService.getLabSamList(labSampRegisterVo);
			//样品类型列表
			LabSamTypeVo labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
			List<LabSamTypeVo> samTypeList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
			setAttribute("samTypeList", samTypeList);
		}
	    return PREUPDATE;
	}
	public String updateLabQuaProficiencyPlan() throws GlobalException {
		if (null == labQuaProficiencyPlanVo) labQuaProficiencyPlanVo = new LabQuaProficiencyPlanVo();
		if (null == labSampRegisterVo) labSampRegisterVo = new LabSampRegisterVo();
		labQuaProficiencyPlanService.updateLabQuaProficiencyPlan(labQuaProficiencyPlanVo);
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getSampRegisterId())&&!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getSampRegisterNum())&&!labQuaProficiencyPlanVo.getSampRegisterNum().equals("0") ){
			labSampRegisterVo.setId(labQuaProficiencyPlanVo.getSampRegisterId());
			labSampRegisterVo=labSampRegisterService.saveLabSampRegister4Other(labSampRegisterVo,sampList);
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getAuditResult()) && labQuaProficiencyPlanVo.getAuditResult().equals(Constants_Source.TRUE)){
			return UPDATE;
		}else
		return PREUPDATE;
	}
    public String deleteLabQuaProficiencyPlan() throws GlobalException {
    	if (null == labQuaProficiencyPlanVo) labQuaProficiencyPlanVo = new LabQuaProficiencyPlanVo();
    	labQuaProficiencyPlanService.update2DelLabQuaProficiencyPlan(labQuaProficiencyPlanVo.getIds());
		return DELETE;
	}
}
