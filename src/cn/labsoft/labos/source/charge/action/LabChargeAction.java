package cn.labsoft.labos.source.charge.action;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.vo.LabReportVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.charge.service.ILabChargeService;
import cn.labsoft.labos.source.charge.vo.LabChargeVo;
import cn.labsoft.labos.utils.DateUtils;

@Controller
@Scope("prototype")
public class LabChargeAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	private ILabChargeService labChargeService;
	private ILabCodeService labCodeService;
	private ILabReportService labReportService;
	private LabChargeVo labChargeVo;
	private LabReportVo labReportVo;
	
	@Resource
	public void setLabChargeService(ILabChargeService labChargeService) {
		this.labChargeService = labChargeService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource
	public void setLabReportService(ILabReportService labReportService) {
		this.labReportService = labReportService;
	}
	public LabReportVo getLabReportVo() {
		return labReportVo;
	}
	public void setLabReportVo(LabReportVo labReportVo) {
		this.labReportVo = labReportVo;
	}
	public LabChargeVo getLabChargeVo() {
		return labChargeVo;
	}
	public void setLabChargeVo(LabChargeVo labChargeVo) {
		this.labChargeVo = labChargeVo;
	}
	
	public void initialization(){
		   if(labChargeVo==null)
			   labChargeVo=new LabChargeVo();
	   }
	
	public String listLabCharge ()throws GlobalException{
		if(labChargeVo==null){
			   labChargeVo=new LabChargeVo();
			   pageResult.setOrder(PageResult.ORDER_DESC);
			   pageResult.setOrderColumn("createTime");
		}
		pageResult = labChargeService.getLabChargePR(labChargeVo, pageResult);
		return LIST;
	}
	
	public String preAddLabCharge() throws GlobalException{
		if(labChargeVo==null){
			   labChargeVo=new LabChargeVo();
			   labChargeVo.setTax(0.0);
			   labChargeVo.setTaxMoney(0.0);
			   labChargeVo.setTotalMoney(0.0);
			   labChargeVo.setPreferential(0.0);
			   labChargeVo.setCollectionTime(DateUtils.getCurrDateStr());
		
		}
		//付款类型
		List<LabCodeVo> payTypeList = labCodeService.getLabCodeByTypeCode("FKLX");
		if(null == payTypeList || payTypeList.size() == 0){
			payTypeList = new ArrayList<LabCodeVo>();
		}
		setAttribute("payTypeList", payTypeList);
		
		return PREADD;
	}
	
	public String addLabCharge() throws GlobalException {
		initialization();
		labChargeService.addLabCharge(labChargeVo);
		return ADD;
	}
	
	public String preUpdateLabCharge() throws GlobalException{
		initialization();
		labChargeVo = labChargeService.getLabCharge(labChargeVo.getId());
		List<LabCodeVo> payTypeList = labCodeService.getLabCodeByTypeCode("FKLX");
		if(null == payTypeList || payTypeList.size() == 0){
			payTypeList = new ArrayList<LabCodeVo>();
		}
		setAttribute("payTypeList", payTypeList);
		return PREUPDATE;
	}
	
	public String updateLabCharge() throws GlobalException{
		initialization();
		labChargeService.updateLabCharge(labChargeVo);
		return UPDATE;
	}
	
	public String deleteLabCharge() throws GlobalException{
		initialization();
		
		labChargeService.update2DelLabCharge(labChargeVo.getIds());
		return DELETE;
	}
	
	public String showLabCharge() throws GlobalException{
		labChargeVo = labChargeService.getLabCharge(labChargeVo.getId());
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
		}
		labReportVo.setBusId(getSessionContainer().getFunId());
		List<LabReportVo> reportTempList = labReportService
				.getLabReportList(labReportVo);
		setAttribute("tempList", reportTempList);
		return SHOW;
	}
	
//	public String printLabCharge() throws GlobalException{
//		initialization();
//		labChargeVo = labChargeService.getLabCharge(labChargeVo.getId());
//		return SHOW;
//	}

	// 报告生成
	public String addLabReport4Bus() throws GlobalException {
		// 获取报告模版信息
		labChargeVo = labChargeService.getLabCharge(labReportVo.getBusInsId());
		labReportVo = labReportService.addLabReport4Bus(labReportVo);
		labChargeVo.setReportTempId(labReportVo.getId());
		labChargeVo.setReportPath(labReportVo.getPath());
		labChargeService.updateLabCharge4Rport(labChargeVo);
		return ADD;
	}
	// 报告查看
	public String showLabReport4Bus() throws GlobalException {
		// 获取报告模版信息
		labChargeVo = labChargeService.getLabCharge(labReportVo.getBusInsId());
		labReportVo = labReportService.getLabReport4Bus(labReportVo);
		labReportVo.setEditType("1");
		return SHOW;
	}
}
