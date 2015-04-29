package cn.labsoft.labos.source.reference.action;

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

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.reference.service.ILabRefPurMainService;
import cn.labsoft.labos.source.reference.service.ILabRefTypeService;
import cn.labsoft.labos.source.reference.service.ILabReferenceService;
import cn.labsoft.labos.source.reference.vo.LabRefPurDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefPurMainVo;
import cn.labsoft.labos.source.reference.vo.LabRefTypeVo;
import cn.labsoft.labos.source.reference.vo.LabReferenceVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabRefPurAction extends BaseAction {
	private ILabRefPurMainService labRefPurMainService;
	private ILabReferenceService labReferenceService;
	private ILabRefTypeService labRefTypeService;
	private ILabWfProcessInsService labWfProcessInsService;
	
	private LabRefPurMainVo labRefPurMainVo;
	private LabRefPurDetailVo labRefPurDetailVo;
	private List<LabRefPurDetailVo> refPurDetailList;
	private LabRefTypeVo labRefTypeVo;
	private LabReferenceVo labReferenceVo;
	private String fileName;
	private InputStream excelStream;

	@Resource
	public void setLabRefPurMainService(ILabRefPurMainService labRefPurMainService) {
		this.labRefPurMainService = labRefPurMainService;
	}
	@Resource
	public void setLabReferenceService(ILabReferenceService labReferenceService) {
		this.labReferenceService = labReferenceService;
	}
	@Resource
	public void setLabRefTypeService(ILabRefTypeService labRefTypeService) {
		this.labRefTypeService = labRefTypeService;
	}
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
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

	public LabReferenceVo getLabReferenceVo() {
		return labReferenceVo;
	}

	public void setLabReferenceVo(LabReferenceVo labReferenceVo) {
		this.labReferenceVo = labReferenceVo;
	}

	public List<LabRefPurDetailVo> getRefPurDetailList() {
		return refPurDetailList;
	}

	public void setRefPurDetailList(List<LabRefPurDetailVo> refPurDetailList) {
		this.refPurDetailList = refPurDetailList;
	}

	public LabRefTypeVo getLabRefTypeVo() {
		return labRefTypeVo;
	}

	public void setLabRefTypeVo(LabRefTypeVo labRefTypeVo) {
		this.labRefTypeVo = labRefTypeVo;
	}

	public LabRefPurMainVo getLabRefPurMainVo() {
		return labRefPurMainVo;
	}

	public void setLabRefPurMainVo(LabRefPurMainVo labRefPurMainVo) {
		this.labRefPurMainVo = labRefPurMainVo;
	}

	public LabRefPurDetailVo getLabRefPurDetailVo() {
		return labRefPurDetailVo;
	}

	public void setLabRefPurDetailVo(LabRefPurDetailVo labRefPurDetailVo) {
		this.labRefPurDetailVo = labRefPurDetailVo;
	}

	public String listLabRefPurMain() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		if (null == labRefPurMainVo.getStatus()) {
			labRefPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labRefPurMainService.getLabRefPurMainPR(labRefPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String preAddLabRefPur() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labRefPurMainVo.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labRefPurMainVo.setApplicant(sessionContainer.getUserName());
		String receiptNo = labRefPurMainService.getLabRefPurMainMaxReceiptNo();
		labRefPurMainVo.setReceiptno(receiptNo);
		refPurDetailList = labRefPurMainService.getLabRefPurDetailList();
		refPurDetailList = labRefPurMainService.getLabRefPurDetailList(labRefPurMainVo);
		return PREADD;
	}

	public String addLabRefPur() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		try {
			labRefPurMainVo=labRefPurMainService.addLabRefPurMain(labRefPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(LabWfConstant.BUS_GO.equals(labRefPurMainVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}

	public String showLabRefPur() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		List<LabRefPurDetailVo> resultList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		setAttribute("resultList", resultList);
		return SHOW;
	}

	public String exportLabRefPur() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		List<LabRefPurDetailVo> resultList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		labRefPurMainVo.setLabRefPurDetailVoList(resultList);
		String path = getRequest().getParameter("path");
		Map<String, LabRefPurMainVo> beans = new HashMap<String, LabRefPurMainVo>();
		beans.put("data", labRefPurMainVo);

		try {
			fileName = new String(("标准品入库信息目录").getBytes(), "ISO8859_1")
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

	public String preUpdateLabRefPur() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		String referenceId = labRefPurMainVo.getReferenceId();
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		refPurDetailList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		labRefPurMainVo.setReferenceId(referenceId);
		if (null != labRefPurMainVo.getReferenceId()
				&& !"".equals(labRefPurMainVo.getReferenceId())) {
			List<LabRefPurDetailVo> detailList = labRefPurMainService
					.getLabRefPurDetailList(labRefPurMainVo);
			if (detailList != null) {
				for (LabRefPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabRefPurDetailVo oldDetailVo : refPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getReferenceId().equals(
										lnewDetailVo.getReferenceId())) {
							flag = true;
						}
					}
					if (!flag) {
						refPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return PREUPDATE;
	}

	public String updateLabRefPurMain() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		try {
			labRefPurMainService.updateLabRefPurMain(labRefPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(LabWfConstant.BUS_GO.equals(labRefPurMainVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}

	public String deleteLabRefPurMain() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainService.deleteLabRefPurMain(labRefPurMainVo);
		return DELETE;
	}

	public String listLabRefPurAudit() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		if (labRefPurMainVo.getStatus() == null) {
			labRefPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labRefPurMainService.getLabRefPurMainPR(labRefPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String listLabRefPurApprove() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		if (labRefPurMainVo.getStatus() == null) {
			labRefPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labRefPurMainService.getLabRefPurMainPR(labRefPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String showLabRefPur4Audit() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		refPurDetailList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		return SHOW;
	}

	public String showLabRefPur4Approve() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		refPurDetailList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		return SHOW;
	}

	public String updateLabRefPur4Audit() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainService.updateLabRefPur4Audit(labRefPurMainVo);
		return UPDATE;
	}

	public String updateLabRefPur4Approve() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainService.updateLabRefPur4Approve(labRefPurMainVo);
		return UPDATE;
	}

	public String showLabRef4Select() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
		}
		List<LabRefTypeVo> labRefTypeVoList = labRefTypeService.getLabRefTypeList("0", 0);
		setAttribute("labRefTypeVoList", labRefTypeVoList);
		pageResult = labReferenceService.getLabReferencePR(labReferenceVo,
				pageResult);
		return SHOW;
	}

	public String listLabRefPurIns() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		if (labRefPurMainVo.getStatus() == null) {
			labRefPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labRefPurMainService.getLabRefPurInsPR(labRefPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String preUpdateLabRefPurIns() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		String referenceId = labRefPurMainVo.getReferenceId();
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		refPurDetailList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		labRefPurMainVo.setReferenceId(referenceId);
		if (null != labRefPurMainVo.getReferenceId()
				&& !"".equals(labRefPurMainVo.getReferenceId())) {
			List<LabRefPurDetailVo> detailList = labRefPurMainService
					.getLabRefPurDetailList(labRefPurMainVo);
			if (detailList != null) {
				for (LabRefPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabRefPurDetailVo oldDetailVo : refPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getReferenceId().equals(
										lnewDetailVo.getReferenceId())) {
							flag = true;
						}
					}
					if (!flag) {
						refPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return PREUPDATE;
	}

	public String updateLabRefPurIns() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		try {
			labRefPurMainService.updateLabRefInMain(labRefPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String showLabRefPur4Ins() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String referenceId = labRefPurMainVo.getReferenceId();
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		labRefPurMainVo.setInTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labRefPurMainVo.setInPerson(sessionContainer.getUserName());
		refPurDetailList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		labRefPurMainVo.setReferenceId(referenceId);
		if (null != labRefPurMainVo.getReferenceId()
				&& !"".equals(labRefPurMainVo.getReferenceId())) {
			List<LabRefPurDetailVo> detailList = labRefPurMainService
					.getLabRefPurDetailList(labRefPurMainVo);
			if (detailList != null) {
				for (LabRefPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabRefPurDetailVo oldDetailVo : refPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getReferenceId().equals(
										lnewDetailVo.getReferenceId())) {
							flag = true;
						}
					}
					if (!flag) {
						refPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return SHOW;
	}

	public String updateLabRefPur4Ins() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		try {
			labRefPurMainService.updateLabRefPur4Ins(labRefPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String preAddLabRefPurIns() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labRefPurMainVo.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labRefPurMainVo.setApplicant(sessionContainer.getUserName());
		String receiptNo = labRefPurMainService.getLabRefPurMainMaxReceiptNo();
		labRefPurMainVo.setReceiptno(receiptNo);
		refPurDetailList = labRefPurMainService.getLabRefPurDetailList();
		if (!StrUtils.isBlankOrNull(labRefPurMainVo.getReferenceId())) {
			refPurDetailList = labRefPurMainService
					.getLabRefPurDetailList(labRefPurMainVo);
		}
		return PREADD;
	}

	public String addLabRefPurIns() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		try {
			labRefPurMainService.addLabRefPurIns(labRefPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String deleteLabRefPurIns() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainService.deleteLabRefPurIns(labRefPurMainVo);
		return DELETE;
	}

	public String showAllLabRefPurMainExcel() throws GlobalException {
		if (null == labRefPurMainVo) {
			labRefPurMainVo = new LabRefPurMainVo();
		}
		labRefPurMainVo = labRefPurMainService
				.getLabRefPurMainById(labRefPurMainVo.getId());
		List<LabRefPurDetailVo> resultList = labRefPurMainService
				.getLabRefPurDetailByMainId(labRefPurMainVo.getId());
		setAttribute("resultList", resultList);
		return SHOW;
	}
}
