package cn.labsoft.labos.source.customer.action;

import java.io.InputStream;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.template.service.ILabTemplateService;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.customer.service.ILabCustomerService;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;

@Controller
@Scope("prototype")
public class LabCustomerAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private ILabCustomerService labCustomerService;
	private ILabCodeService labCodeService;
	private ILabNumberService labNumberService;
	private ILabTemplateService labTemplateService;
	
	private LabCustomerVo labCustomerVo;
	private String reagentIds;
	private String fileName;
	private InputStream excelStream;
	private List<String> amountList;
	

	public List<String> getAmountList() {
		return amountList;
	}
	public void setAmountList(List<String> amountList) {
		this.amountList = amountList;
	}
	@Resource
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}
	@Resource
	public void setLabCustomerService(ILabCustomerService labCustomerService) {
		this.labCustomerService = labCustomerService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
	public LabCustomerVo getLabCustomerVo() {
		return labCustomerVo;
	}
	public String getReagentIds() {
		return reagentIds;
	}

	public void setReagentIds(String reagentIds) {
		this.reagentIds = reagentIds;
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

	public void setLabCustomerVo(LabCustomerVo labCustomerVo) {
		this.labCustomerVo = labCustomerVo;
	}

	public String listLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		List<LabCodeVo> levelList = labCodeService.getLabCodeByTypeCode("KHJB");
		setAttribute("levelList", levelList);
		List<LabCodeVo> typeList = labCodeService.getLabCodeByTypeCode("KHLX");
		setAttribute("typeList", typeList);
		pageResult = labCustomerService.getLabCustomerPR(labCustomerVo, pageResult);
		String path = labTemplateService.getLabTemplateByBusId(getSessionContainer().getFunId());
		labCustomerVo.setFilePath(path);
		return LIST;
	}

	@SuppressWarnings("unchecked")
	public String preAddLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		List<LabCodeVo> levelList = labCodeService.getLabCodeByTypeCode("KHJB");
		setAttribute("levelList", levelList);
		List<LabCodeVo> typeList = labCodeService.getLabCodeByTypeCode("KHLX");
		setAttribute("typeList", typeList);
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,null, Constants_Source.CODE_KH, new String[] {},Constants_Source.CODE_USE_INIT);
		try {
			labCustomerVo.setNum(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return PREADD;
	}

	@SuppressWarnings("unchecked")
	public String addLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,null,Constants_Source.CODE_KH, new String[] {},Constants_Source.CODE_USE_RUN);
		try {
			labCustomerVo.setNum(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		labCustomerVo = labCustomerService.addLabCustomer(labCustomerVo);
		return ADD;
	}

	public String preUpdateLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		labCustomerVo = labCustomerService.getLabCustomer(labCustomerVo.getId());
		List<LabCodeVo> levelList = labCodeService.getLabCodeByTypeCode("KHJB");
		setAttribute("levelList", levelList);
		List<LabCodeVo> typeList = labCodeService.getLabCodeByTypeCode("KHLX");
		setAttribute("typeList", typeList);
		amountList=labCustomerService.getEveryMonthProgrammAmount(labCustomerVo.getId());
		return PREUPDATE;
	}

	public String updateLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		@SuppressWarnings("unused")
		boolean key = labCustomerService.updateLabCustomer(labCustomerVo);
		return UPDATE;
	}

	public String showLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		labCustomerVo = labCustomerService.getLabCustomer(labCustomerVo.getId());
		List<LabCodeVo> levelList = labCodeService.getLabCodeByTypeCode("KHJB");
		setAttribute("levelList", levelList);
		List<LabCodeVo> typeList = labCodeService.getLabCodeByTypeCode("KHLX");
		setAttribute("typeList", typeList);
		labCustomerVo.setBalance(0.0);
		labCustomerVo.setBusinessNum(2);
		labCustomerVo.setConsume(0.0);
		amountList=labCustomerService.getEveryMonthProgrammAmount(labCustomerVo.getId());
		return SHOW;
	}

	public String deleteLabCustomer() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		labCustomerService.deleteLabCustomer(labCustomerVo.getIds());
		return DELETE;
	}

	public String updateLabCustomer4Del() throws GlobalException {
		if (null == labCustomerVo)
			labCustomerVo = new LabCustomerVo();
		labCustomerService.updateLabCustomer4Del(labCustomerVo.getIds());
		return DELETE;
	}

//	@SuppressWarnings("deprecation")
//	public String exportLabCustomer() throws GlobalException {
//		if (null == labCustomerVo)
//			labCustomerVo = new LabCustomerVo();
//		List<LabCustomerVo> list = labCustomerService.getLabCustomerList(labCustomerVo);
//		labCustomerVo.setListCustomer(list);
//		String path = getRequest().getParameter("path");
//		Map<String, LabCustomerVo> beans = new HashMap<String, LabCustomerVo>();
//		beans.put("data", labCustomerVo);
//
//		try {
//			fileName = new String(("客户信息目录").getBytes(), "ISO8859_1") + ".xls";
//		} catch (UnsupportedEncodingException e) {
//			Log4J.error("格式转换错误" + e.getMessage());
//		}
//
//		String templateFileName = ServletActionContext.getRequest().getRealPath("/") + path.replace("\\", File.separator);
//		String destFileName = ServletActionContext.getRequest().getRealPath("/") + "templates" + File.separator + "temp" + File.separator + System.currentTimeMillis() + "." + "xls";
//
//		String realPath = ServletActionContext.getRequest().getRealPath("/") + "templates" + File.separator + "temp" + File.separator;
//		File file = new File(realPath);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//
//		XLSTransformer transformer = new XLSTransformer();
//		try {
//			transformer.transformXLS(templateFileName, beans, destFileName);
//			File targetFile = new File(destFileName);
//			excelStream = new BufferedInputStream(new FileInputStream(targetFile), 16 * 1024);
//			path = targetFile.getPath();
//			cn.labsoft.labos.utils.FileUtils.delAllFile(realPath);
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		return "export";
//	}
}
