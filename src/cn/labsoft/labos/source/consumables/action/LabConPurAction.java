package cn.labsoft.labos.source.consumables.action;

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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.consumables.service.ILabConPurMainService;
import cn.labsoft.labos.source.consumables.service.ILabConTypeService;
import cn.labsoft.labos.source.consumables.service.ILabConsumablesService;
import cn.labsoft.labos.source.consumables.vo.LabConPurDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConPurMainVo;
import cn.labsoft.labos.source.consumables.vo.LabConTypeVo;
import cn.labsoft.labos.source.consumables.vo.LabConsumablesVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
@Controller
@Scope("prototype")
public class LabConPurAction extends BaseAction {
	private ILabConPurMainService labConPurMainService;
	private ILabConsumablesService labConsumablesService;
	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private ILabConTypeService labConTypeService;
	private ILabWfProcessInsService labWfProcessInsService;
	private LabConPurMainVo labConPurMainVo;
	private LabConPurDetailVo labConPurDetailVo;
	private List<LabConPurDetailVo> conPurDetailList;
	private LabConTypeVo labConTypeVo;
	private LabConsumablesVo labConsumablesVo;
	private ILabConfigService labConfigService;
	private String fileName;
	private InputStream excelStream;

	@Resource
	public void setLabConPurMainService(ILabConPurMainService labConPurMainService) {
		this.labConPurMainService = labConPurMainService;
	}
	@Resource
	public void setLabConsumablesService(
			ILabConsumablesService labConsumablesService) {
		this.labConsumablesService = labConsumablesService;
	}
	@Resource
	public void setLabConTypeService(ILabConTypeService labConTypeService) {
		this.labConTypeService = labConTypeService;
	}
	@Resource
	public void setLabWfProcessInsService(
			ILabWfProcessInsService labWfProcessInsService) {
		this.labWfProcessInsService = labWfProcessInsService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
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

	public LabConsumablesVo getLabConsumablesVo() {
		return labConsumablesVo;
	}

	public void setLabConsumablesVo(LabConsumablesVo labConsumablesVo) {
		this.labConsumablesVo = labConsumablesVo;
	}

	public List<LabConPurDetailVo> getConPurDetailList() {
		return conPurDetailList;
	}

	public void setConPurDetailList(List<LabConPurDetailVo> conPurDetailList) {
		this.conPurDetailList = conPurDetailList;
	}

	public LabConTypeVo getLabConTypeVo() {
		return labConTypeVo;
	}

	public void setLabConTypeVo(LabConTypeVo labConTypeVo) {
		this.labConTypeVo = labConTypeVo;
	}

	public LabConPurMainVo getLabConPurMainVo() {
		return labConPurMainVo;
	}

	public void setLabConPurMainVo(LabConPurMainVo labConPurMainVo) {
		this.labConPurMainVo = labConPurMainVo;
	}

	public LabConPurDetailVo getLabConPurDetailVo() {
		return labConPurDetailVo;
	}

	public void setLabConPurDetailVo(LabConPurDetailVo labConPurDetailVo) {
		this.labConPurDetailVo = labConPurDetailVo;
	}

	public String listLabConPurMain() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		if (null == labConPurMainVo.getStatus()) {
			labConPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labConPurMainService.getLabConPurMainPR(labConPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String preAddLabConPur() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labConPurMainVo.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labConPurMainVo.setApplicant(sessionContainer.getUserName());
		String receiptNo = labConPurMainService.getLabConPurMainMaxReceiptNo();
		labConPurMainVo.setReceiptno(receiptNo);
		conPurDetailList = labConPurMainService.getLabConPurDetailList();
		if (!StrUtils.isBlankOrNull(labConPurMainVo.getConsumablesId())) {
			conPurDetailList = labConPurMainService.getLabConPurDetailList(labConPurMainVo);
		}
		return PREADD;
	}

	public String addLabConPur() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		try {
			labConPurMainVo=labConPurMainService.addLabConPurMain(labConPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(LabWfConstant.BUS_GO.equals(labConPurMainVo.getAuditResult())){
			return ADD;
		}
		return PREUPDATE;
	}

	public String showLabConPur() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		List<LabConPurDetailVo> resultList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		setAttribute("resultList", resultList);
		return SHOW;
	}

	public String exportLabConPur() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		List<LabConPurDetailVo> resultList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		labConPurMainVo.setLabConPurDetailVoList(resultList);
		String path = getRequest().getParameter("path");
		Map<String, LabConPurMainVo> beans = new HashMap<String, LabConPurMainVo>();
		beans.put("data", labConPurMainVo);

		try {
			fileName = new String(("耗材入库信息目录").getBytes(), "ISO8859_1")
					+ ".xls";
		} catch (UnsupportedEncodingException e) {
			log.error("格式转换错误" + e.getMessage());
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

	public String preUpdateLabConPur() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		String consumablesId = labConPurMainVo.getConsumablesId();
		labConPurMainVo = labConPurMainService.getLabConPurMainById(labConPurMainVo.getId());
		conPurDetailList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		labConPurMainVo.setConsumablesId(consumablesId);
		if (null != labConPurMainVo.getConsumablesId()
				&& !"".equals(labConPurMainVo.getConsumablesId())) {
			List<LabConPurDetailVo> detailList = labConPurMainService
					.getLabConPurDetailList(labConPurMainVo);
			if (null != detailList) {
				for (LabConPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabConPurDetailVo oldDetailVo : conPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getConsumablesId().equals(
										lnewDetailVo.getConsumablesId())) {
							flag = true;
						}
					}
					if (!flag) {
						conPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return PREUPDATE;
	}

	public String updateLabConPurMain() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		try {
			labConPurMainService.updateLabConPurMain(labConPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		if(LabWfConstant.BUS_GO.equals(labConPurMainVo.getAuditResult())){
			return UPDATE;
		}
		return PREUPDATE;
	}

	public String deleteLabConPurMain() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainService.deleteLabConPurMain(labConPurMainVo);
		return DELETE;
	}

	public String listLabConPurAudit() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		if (null == labConPurMainVo.getStatus()) {
			labConPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labConPurMainService.getLabConPurMainPR(labConPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String listLabConPurApprove() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		if (null == labConPurMainVo.getStatus()) {
			labConPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labConPurMainService.getLabConPurMainPR(labConPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String showLabConPur4Audit() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		conPurDetailList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		return SHOW;
	}

	public String showLabConPur4Approve() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		conPurDetailList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		return SHOW;
	}

	public String updateLabConPur4Audit() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainService.updateLabConPur4Audit(labConPurMainVo);
		return UPDATE;
	}

	public String updateLabConPur4Approve() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainService.updateLabConPur4Approve(labConPurMainVo);
		return UPDATE;
	}

	public String showLabCon4Select() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
		}
		List<LabConTypeVo> labConTypeVoList = labConTypeService
				.getLabConTypeList("0",0);
		setAttribute("labConTypeVoList", labConTypeVoList);
		pageResult = labConsumablesService.getLabConsumablesPR(
				labConsumablesVo, pageResult);
		return SHOW;
	}

	public String listLabConPurIns() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		if (null == labConPurMainVo.getStatus()) {
			labConPurMainVo.setStatus(getSessionContainer().getFunId());
		}
		funId = getSessionContainer().getFunId();
		pageResult = labConPurMainService.getLabConPurInsPR(labConPurMainVo,
				pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}

	public String preUpdateLabConPurIns() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		String consumablesId = labConPurMainVo.getConsumablesId();
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		conPurDetailList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		labConPurMainVo.setConsumablesId(consumablesId);
		if (null != labConPurMainVo.getConsumablesId()
				&& !"".equals(labConPurMainVo.getConsumablesId())) {
			List<LabConPurDetailVo> detailList = labConPurMainService
					.getLabConPurDetailList(labConPurMainVo);
			if (null != detailList && detailList.size() > 0) {
				for (LabConPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabConPurDetailVo oldDetailVo : conPurDetailList) {
						if (null != oldDetailVo
								&& oldDetailVo.getConsumablesId().equals(
										lnewDetailVo.getConsumablesId())) {
							flag = true;
						}
					}
					if (!flag) {
						conPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return PREUPDATE;
	}

	public String updateLabConPurIns() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		try {
			labConPurMainService.updateLabConInMain(labConPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String showLabConPur4Ins() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String consumablesId = labConPurMainVo.getConsumablesId();
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		labConPurMainVo.setInTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labConPurMainVo.setInPerson(sessionContainer.getUserName());
		conPurDetailList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		labConPurMainVo.setConsumablesId(consumablesId);
		if (null != labConPurMainVo.getConsumablesId()
				&& !"".equals(labConPurMainVo.getConsumablesId())) {
			List<LabConPurDetailVo> detailList = labConPurMainService
					.getLabConPurDetailList(labConPurMainVo);
			if (null != detailList) {
				for (LabConPurDetailVo lnewDetailVo : detailList) {
					boolean flag = false;
					for (LabConPurDetailVo oldDetailVo : conPurDetailList) {
						if (oldDetailVo != null
								&& oldDetailVo.getConsumablesId().equals(
										lnewDetailVo.getConsumablesId())) {
							flag = true;
						}
					}
					if (!flag) {
						conPurDetailList.add(lnewDetailVo);
					}
				}
			}
		}
		return SHOW;
	}

	public String updateLabConPur4Ins() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		try {
			labConPurMainService.updateLabConPur4Ins(labConPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return UPDATE;
	}

	public String preAddLabConPurIns() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext
				.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		labConPurMainVo.setCreateTime(DateUtils.format(new Date(),
				DateUtils.formatStr_yyyyMMdd));
		labConPurMainVo.setApplicant(sessionContainer.getUserName());
		String receiptNo = labConPurMainService.getLabConPurMainMaxReceiptNo();
		labConPurMainVo.setReceiptno(receiptNo);
		conPurDetailList = labConPurMainService.getLabConPurDetailList();
		if (!StrUtils.isBlankOrNull(labConPurMainVo.getConsumablesId())) {
			conPurDetailList = labConPurMainService
					.getLabConPurDetailList(labConPurMainVo);
		}
		return PREADD;
	}

	public String addLabConPurIns() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		try {
			labConPurMainService.addLabConPurIns(labConPurMainVo);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return ADD;
	}

	public String deleteLabConPurIns() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainService.deleteLabConPurIns(labConPurMainVo);
		return DELETE;
	}

	public String showAllLabConPurMainExcel() throws GlobalException {
		if (null == labConPurMainVo) {
			labConPurMainVo = new LabConPurMainVo();
		}
		labConPurMainVo = labConPurMainService
				.getLabConPurMainById(labConPurMainVo.getId());
		List<LabConPurDetailVo> resultList = labConPurMainService
				.getLabConPurDetailByMainId(labConPurMainVo.getId());
		setAttribute("resultList", resultList);
		return SHOW;
	}
}
