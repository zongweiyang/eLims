package cn.labsoft.labos.source.reagent.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.common.report.service.ILabReportService;
import cn.labsoft.labos.common.report.vo.LabReportVo;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.reagent.service.ILabReaPurMainService;
import cn.labsoft.labos.source.reagent.service.ILabReaTypeService;
import cn.labsoft.labos.source.reagent.service.ILabReagentService;
import cn.labsoft.labos.source.reagent.vo.LabReaPurDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaPurMainVo;
import cn.labsoft.labos.source.reagent.vo.LabReaTypeVo;
import cn.labsoft.labos.source.reagent.vo.LabReagentVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Controller
@Scope("prototype")
public class LabReaPurAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5734874030628759783L;
	private ILabReaPurMainService labReaPurMainService;
	private ILabReagentService labReagentService;
	private ILabWfProcessInsService labWfProcessInsService;
	private ILabReaTypeService labReaTypeService;
	private ILabReportService labReportService;

	private LabReaPurMainVo labReaPurMainVo;
	private LabReaPurDetailVo labReaPurDetailVo;
	private List<LabReaPurDetailVo> reaPurDetailList;
	private LabReaTypeVo labReaTypeVo;
	private LabReagentVo labReagentVo;
	private LabReportVo labReportVo;

	private String fileName;
	private InputStream excelStream;
	@Resource
	public void setLabReaPurMainService(ILabReaPurMainService labReaPurMainService) {
		this.labReaPurMainService = labReaPurMainService;
	}
    @Resource
	public void setLabReagentService(ILabReagentService labReagentService) {
		this.labReagentService = labReagentService;
	}
    @Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
    @Resource
	public void setLabReaTypeService(ILabReaTypeService labReaTypeService) {
		this.labReaTypeService = labReaTypeService;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getExcelStream() {
		return excelStream;
	}

	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}

	public LabReagentVo getLabReagentVo() {
		return labReagentVo;
	}

	public void setLabReagentVo(LabReagentVo labReagentVo) {
		this.labReagentVo = labReagentVo;
	}

	public List<LabReaPurDetailVo> getReaPurDetailList() {
		return reaPurDetailList;
	}

	public void setReaPurDetailList(List<LabReaPurDetailVo> reaPurDetailList) {
		this.reaPurDetailList = reaPurDetailList;
	}

	public LabReaTypeVo getLabReaTypeVo() {
		return labReaTypeVo;
	}

	public void setLabReaTypeVo(LabReaTypeVo labReaTypeVo) {
		this.labReaTypeVo = labReaTypeVo;
	}

	public LabReaPurMainVo getLabReaPurMainVo() {
		return labReaPurMainVo;
	}

	public void setLabReaPurMainVo(LabReaPurMainVo labReaPurMainVo) {
		this.labReaPurMainVo = labReaPurMainVo;
	}

	public LabReaPurDetailVo getLabReaPurDetailVo() {
		return labReaPurDetailVo;
	}

	public void setLabReaPurDetailVo(LabReaPurDetailVo labReaPurDetailVo) {
		this.labReaPurDetailVo = labReaPurDetailVo;
	}

	public String listLabReaPurMain() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		if (null == labReaPurMainVo.getStatus()) {
			labReaPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labReaPurMainService.getLabReaPurMainPR(labReaPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String preAddLabReaPur() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labReaPurMainVo.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labReaPurMainVo.setApplicant(sessionContainer.getUserName());
		String receiptNo = labReaPurMainService.getLabReaPurMainMaxReceiptNo();
		labReaPurMainVo.setReceiptno(receiptNo);
		reaPurDetailList = labReaPurMainService.getLabReaPurDetailList();
		reaPurDetailList = labReaPurMainService.getLabReaPurDetailList(labReaPurMainVo);
		return PREADD;
	}

	public String addLabReaPur() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		try {
			labReaPurMainVo=labReaPurMainService.addLabReaPurMain(labReaPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(LabWfConstant.BUS_GO.equals(labReaPurMainVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}

	public String showLabReaPur() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		List<LabReaPurDetailVo> resultList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		setAttribute("resultList", resultList);
		return SHOW;
	}

	@SuppressWarnings("deprecation")
	public String exportLabReaPur() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		String path = getRequest().getParameter("path");
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		List<LabReaPurDetailVo> resultList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		labReaPurMainVo.setLabReaPurDetailVoList(resultList);
		Map<String, LabReaPurMainVo> beans = new HashMap<String, LabReaPurMainVo>();
		beans.put("data", labReaPurMainVo);

		try {
			fileName = new String(("试剂入库信息目录").getBytes(), "ISO8859_1")
					+ ".xls";
		} catch (UnsupportedEncodingException e) {
			Log4J.error("格式转换错误" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		String templateFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ path.replace("\\", File.separator);
		String destFileName = ServletActionContext.getRequest()
				.getRealPath("/")
				+ "templates"
				+ File.separator
				+ "temp"
				+ File.separator
				+ System.currentTimeMillis() + "." + "xls";
		String realPath = ServletActionContext.getRequest().getRealPath("/")
				+ "templates" + File.separator + "temp" + File.separator;
		File file = new File(realPath);
		if (!file.exists()) {
			file.mkdir();
		}
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(templateFileName, beans, destFileName);
			File targetFile = new File(destFileName);
			excelStream = new BufferedInputStream(new FileInputStream(
					targetFile), 16 * 1024);
			path = targetFile.getPath();
			cn.labsoft.labos.utils.FileUtils.delAllFile(realPath);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return EXPORT;
	}

	public String preUpdateLabReaPur() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		String reagentId = labReaPurMainVo.getReagentId();
		labReaPurMainVo = labReaPurMainService.getLabReaPurMainById(labReaPurMainVo.getId());
		reaPurDetailList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		labReaPurMainVo.setReagentId(reagentId);
		if (null != labReaPurMainVo.getReagentId()
				&& !"".equals(labReaPurMainVo.getReagentId())) {
			List<LabReaPurDetailVo> detailList = labReaPurMainService
					.getLabReaPurDetailList(labReaPurMainVo);
			if (detailList != null) {
				for (LabReaPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabReaPurDetailVo oldDetailVo : reaPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getReagentId().equals(
										lnewDetailVo.getReagentId())) {
							flag = true;
						}
					}
					if (!flag) {
						reaPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return PREUPDATE;
	}

	public String updateLabReaPurMain() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		try {
			labReaPurMainService.updateLabReaPurMain(labReaPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(LabWfConstant.BUS_GO.equals(labReaPurMainVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}

	public String deleteLabReaPurMain() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainService.deleteLabReaPurMain(labReaPurMainVo);
		return DELETE;
	}

	public String listLabReaPurAudit() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		if (null == labReaPurMainVo.getStatus()) {
			labReaPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labReaPurMainService.getLabReaPurMainPR(labReaPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String listLabReaPurApprove() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		if (null == labReaPurMainVo.getStatus()) {
			labReaPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labReaPurMainService.getLabReaPurMainPR(labReaPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String showLabReaPur4Audit() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainVo = labReaPurMainService.getLabReaPurMainById(labReaPurMainVo.getId());
		reaPurDetailList = labReaPurMainService.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		return SHOW;
	}

	public String showLabReaPur4Approve() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		reaPurDetailList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		return SHOW;
	}

	public String updateLabReaPur4Audit() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainService.updateLabReaPur4Audit(labReaPurMainVo);
		return UPDATE;
	}

	public String updateLabReaPur4Approve() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainService.updateLabReaPur4Approve(labReaPurMainVo);
		return UPDATE;
	}

	public String showLabRea4Select() throws GlobalException {
    	if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		if (null == labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
		}
		List<LabReaTypeVo> labReaTypeVoList = labReaTypeService.getLabReaTypeList("0",0);
		setAttribute("labReaTypeVoList", labReaTypeVoList);
		
		pageResult = labReagentService.getLabReagentPR(labReagentVo, pageResult);
		
		return SHOW;
	}

	public String listLabReaPurIns() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		if (null == labReaPurMainVo.getStatus()) {
			labReaPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult = labReaPurMainService.getLabReaPurInsPR(labReaPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String preUpdateLabReaPurIns() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		String reagentId = labReaPurMainVo.getReagentId();
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		reaPurDetailList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		labReaPurMainVo.setReagentId(reagentId);
		if (null != labReaPurMainVo.getReagentId()
				&& !"".equals(labReaPurMainVo.getReagentId())) {
			List<LabReaPurDetailVo> detailList = labReaPurMainService
					.getLabReaPurDetailList(labReaPurMainVo);
			if (detailList != null) {
				for (LabReaPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabReaPurDetailVo oldDetailVo : reaPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getReagentId().equals(
										lnewDetailVo.getReagentId())) {
							flag = true;
						}
					}
					if (!flag) {
						reaPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return PREUPDATE;
	}

	public String updateLabReaPurIns() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		try {
			labReaPurMainService.updateLabReaInMain(labReaPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String showLabReaPur4Ins() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String reagentId = labReaPurMainVo.getReagentId();
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		labReaPurMainVo.setInTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labReaPurMainVo.setInPerson(sessionContainer.getUserName());
		reaPurDetailList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		labReaPurMainVo.setReagentId(reagentId);
		if (null != labReaPurMainVo.getReagentId()
				&& !"".equals(labReaPurMainVo.getReagentId())) {
			List<LabReaPurDetailVo> detailList = labReaPurMainService
					.getLabReaPurDetailList(labReaPurMainVo);
			if (null != detailList) {
				for (LabReaPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabReaPurDetailVo oldDetailVo : reaPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getReagentId().equals(
										lnewDetailVo.getReagentId())) {
							flag = true;
						}
					}
					if (!flag) {
						reaPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return SHOW;
	}

	public String updateLabReaPur4Ins() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		try {
			labReaPurMainService.updateLabReaPur4Ins(labReaPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String preAddLabReaPurIns() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labReaPurMainVo.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labReaPurMainVo.setApplicant(sessionContainer.getUserName());
		String receiptNo = labReaPurMainService.getLabReaPurMainMaxReceiptNo();
		labReaPurMainVo.setReceiptno(receiptNo);
		reaPurDetailList = labReaPurMainService.getLabReaPurDetailList();
		reaPurDetailList = labReaPurMainService.getLabReaPurDetailList(labReaPurMainVo);
		return PREADD;
	}

	public String addLabReaPurIns() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		try {
			labReaPurMainService.addLabReaPurIns(labReaPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String deleteLabReaPurIns() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainService.deleteLabReaPurIns(labReaPurMainVo);
		return DELETE;
	}

	public String showAllLabReaPurMainExcel() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		List<LabReaPurDetailVo> resultList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		setAttribute("resultList", resultList);
		return SHOW;
	}

	// 业务模块调用方法
	public String showLabReport4Bus() throws GlobalException {
		if (null == labReaPurMainVo) {
			labReaPurMainVo = new LabReaPurMainVo();
		}
		String funId = getParameter("funId");
		String busId = getParameter("busId");
		labReaPurMainVo.setId(busId);
		// 获取业务数据
		labReaPurMainVo = labReaPurMainService
				.getLabReaPurMainById(labReaPurMainVo.getId());
		reaPurDetailList = labReaPurMainService
				.getLabReaPurDetailByMainId(labReaPurMainVo.getId());
		// 获取报告模版信息
		if (null == labReportVo) {
			labReportVo = new LabReportVo();
		}
		if (!StrUtils.isBlankOrNull(funId)) {
			labReportVo.setBusId(funId);
		}
		if (!StrUtils.isBlankOrNull(busId)) {
			labReportVo.setBusInsId(busId);
		}
		labReportVo = labReportService.getLabReport4Bus(labReportVo);
		labReportVo.setEditType("1");
		List<LabReportVo> reportTempList = labReportService
				.getLabReportList(labReportVo);
		setAttribute("reportTempList", reportTempList);
		return SHOW;
	}
}
