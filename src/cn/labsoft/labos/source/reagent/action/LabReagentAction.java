package cn.labsoft.labos.source.reagent.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.common.encoder.service.ILabEncoderService;
import cn.labsoft.labos.common.encoder.vo.LabEncoderVo;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.template.service.ILabTemplateService;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.listener.SpringInitListener;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reagent.service.ILabReaTypeService;
import cn.labsoft.labos.source.reagent.service.ILabReagentService;
import cn.labsoft.labos.source.reagent.vo.LabReaTypeVo;
import cn.labsoft.labos.source.reagent.vo.LabReagentVo;
import cn.labsoft.labos.source.supplier.entity.LabSupplier;
import cn.labsoft.labos.source.supplier.service.ILabSupplierService;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;
@Controller
@Scope("prototype")
public class LabReagentAction extends BaseAction {

	
	private static final long serialVersionUID = -4426724627153314947L;
	private LabReagentVo labReagentVo;
	private LabReaTypeVo labReaTypeVo;
	private List<LabReagentVo> labReagentVoList;
	private ILabNumberService labNumberService;
	private ILabReagentService labReagentService;
	private ILabReaTypeService labReaTypeService ;
	private ILabCodeService labCodeService;
	private ILabOrgService labOrgService ;
	private ILabUploadService labUploadService;
	private ILabConfigService labConfigService;
	private ILabEncoderService labEncoderService;
	private ILabTemplateService labTemplateService;
	private ILabSupplierService labSupplierService;

	private static Log log = LogFactory.getLog(SpringInitListener.class);
	private String fileName;
	private InputStream excelStream;

	private String fileUrl;
	private File upload;
	private String uploadFileName;
	private LabEncoderVo labEncoderVo;
	
    @Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}
    
    @Resource
    public void setLabSupplierService(ILabSupplierService labSupplierService) {
		this.labSupplierService = labSupplierService;
	}


	public static void setLog(Log log) {
		LabReagentAction.log = log;
	}


	@Resource
	public void setLabReagentService(ILabReagentService labReagentService) {
		this.labReagentService = labReagentService;
	}
    @Resource
	public void setLabReaTypeService(ILabReaTypeService labReaTypeService) {
		this.labReaTypeService = labReaTypeService;
	}
    @Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
    @Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
    @Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
    @Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
    @Resource
	public void setLabEncoderService(ILabEncoderService labEncoderService) {
		this.labEncoderService = labEncoderService;
	}
    @Resource
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}
	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public LabReaTypeVo getLabReaTypeVo() {
		return labReaTypeVo;
	}

	public void setLabReaTypeVo(LabReaTypeVo labReaTypeVo) {
		this.labReaTypeVo = labReaTypeVo;
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

	public List<LabReagentVo> getLabReagentVoList() {
		return labReagentVoList;
	}

	public void setLabReagentVoList(List<LabReagentVo> labReagentVoList) {
		this.labReagentVoList = labReagentVoList;
	}

	public LabReagentVo getLabReagentVo() {
		return labReagentVo;
	}

	public void setLabReagentVo(LabReagentVo labReagentVo) {
		this.labReagentVo = labReagentVo;
	}

	public LabReaTypeVo getLabReagentTypeVo() {
		return labReaTypeVo;
	}

	public void setLabReagentTypeVo(LabReaTypeVo labReaTypeVo) {
		this.labReaTypeVo = labReaTypeVo;
	}

	public LabEncoderVo getLabEncoderVo() {
		return labEncoderVo;
	}
	public void setLabEncoderVo(LabEncoderVo labEncoderVo) {
		this.labEncoderVo = labEncoderVo;
	}

	public String listLabReagent() throws GlobalException {
		if (null == labReagentVo)
			labReagentVo = new LabReagentVo();
		if (StrUtils.isBlankOrNull(labReagentVo.getReagentTypeId())) {
			labReagentVo.setReagentTypeId("0");
		}
		LabReaTypeVo type = labReaTypeService.getLabReaType(labReagentVo.getReagentTypeId().trim());
		labReagentVo.setReagentTypeName(type.getName());
		pageResult = labReagentService.getLabReagentPR(labReagentVo, pageResult);
		String path = labTemplateService.getLabTemplateByBusId(getSessionContainer().getFunId());
		labReagentVo.setFilePath(path);
		return LIST;
	}

	public String showLabReagent() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgListByPId("0");
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		labReagentVo = labReagentService
				.getLabReagentById(labReagentVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labReagentVo.getId(), "lab-reagent");
		setAttribute("loadList", loadList);
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType("试剂");
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return SHOW;
	}

//	@SuppressWarnings("deprecation")
//	public String exportLabReagent() throws GlobalException {
//		if (null == labReagentVo)
//			labReagentVo = new LabReagentVo();
//		String path = getRequest().getParameter("path");
//		List<LabReagentVo> alertListForExcel = labReagentService
//				.getLabReagentList(labReagentVo);
//		labReagentVo.setLabReagentList(alertListForExcel);
//		Map<String, LabReagentVo> beans = new HashMap<String, LabReagentVo>();
//		beans.put("data", labReagentVo);
//
//		try {
//			fileName = new String(("试剂信息目录").getBytes(), "ISO8859-1") + ".xls";
//		} catch (UnsupportedEncodingException e) {
//			log.error("格式转换错误" + e.getMessage());
//		}
//		String templateFileName = ServletActionContext.getRequest()
//				.getRealPath("/")
//				+ path.replace("\\", File.separator);
//		String destFileName = ServletActionContext.getRequest()
//				.getRealPath("/")
//				+ "templates"
//				+ File.separator
//				+ "temp"
//				+ File.separator
//				+ System.currentTimeMillis() + "." + "xls";
//		String realPath = ServletActionContext.getRequest().getRealPath("/")
//				+ "templates" + File.separator + "temp" + File.separator;
//		File file = new File(realPath);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//		XLSTransformer transformer = new XLSTransformer();
//		try {
//			transformer.transformXLS(templateFileName, beans, destFileName);
//			File targetFile = new File(destFileName);
//			excelStream = new BufferedInputStream(new FileInputStream(
//					targetFile), 16 * 1024);
//			path = targetFile.getPath();
//			cn.labsoft.labos.utils.FileUtils.delAllFile(realPath);
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		return EXPORT;
//	}

	public String preAddLabReagent() throws GlobalException,
			InterruptedException, ExecutionException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_SJ, new String[] {},Constants_Source.CODE_USE_INIT);
		labReagentVo.setCode(pool.submit(threadNumber).get().toString());
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		setAttribute("codeList", codeList);
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
		setAttribute("labOrgVoList", labOrgVoList);
		if (StrUtils.isBlankOrNull(labReagentVo.getUuid())) {
			labReagentVo.setUuid(java.util.UUID.randomUUID().toString().replace("-", ""));
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labReagentVo.getUuid(), "lab-reagent");
		setAttribute("loadList", loadList);
		if (null != labReagentVo.getReagentTypeId()) {
			LabReaTypeVo labReaTypeVo = labReaTypeService
					.getLabReaType(labReagentVo.getReagentTypeId().trim());
			labReagentVo.setReagentTypeName(labReaTypeVo.getName());
		}
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType(getText("reagent.type"));
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return PREADD;
	}

	public String addLabReagent() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		labReagentVo.setIsDel(Constants_Source.N);
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_SJ, new String[] {},Constants_Source.CODE_USE_RUN);
		try {
			labReagentVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			Log4J.error(e.getMessage());
		}
		labReagentService.addLabReagent(labReagentVo);
		return ADD;
	}

	public String deleteLabReagent() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		if (null != labReagentVo.getIds() && !"".equals(labReagentVo.getIds())) {
			for (int i = 0; i < labReagentVo.getIds().length; i++) {
				labReagentService.deleteLabReagent(labReagentVo.getIds()[i]);
			}
		}
		return DELETE;
	}

	public String preUpdateLabReagent() throws GlobalException{
		if (labReagentVo == null) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		labReagentVo = labReagentService.getLabReagentById(labReagentVo.getId());
		
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labReagentVo.getId(), "lab-reagent");
		setAttribute("loadList", loadList);
		labReagentVo.setIsDel(Constants_Source.N);
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType(getText("reagent.type"));
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return PREUPDATE;
	}

	public String updateLabReagent() throws GlobalException{
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		labReagentService.updateLabReagent(labReagentVo);
		return UPDATE;
	}

	public String preLabReaTypeTree() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		return PRETREE;
	}

	public String updateLabReagentMove() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
			labReagentVo.setReagentTypeId("0");
		}
		labReagentService.updateLabReagentMove(labReagentVo);
		return UPDATE;
	}

	public String showLabRea4Select() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		if (null == labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
			if (null== labReaTypeVo.getId()|| "".equals(labReaTypeVo.getId())) {
				labReaTypeVo.setId("0");
			}
		}
		pageResult = labReagentService.getLabReagentPR(labReagentVo, pageResult);

		List<LabReaTypeVo> labReaTypeVoList = labReaTypeService
				.getLabReaTypeList(labReaTypeVo.getId(),0);
		setAttribute("labReaTypeVoList", labReaTypeVoList);
		return SHOW;
	}

	public String showLabReagent4printTwoCode() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		labReagentVoList = new ArrayList<LabReagentVo>();
		String[] ids = labReagentVo.getId().split(",");
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				labReagentVoList.add(labReagentService.getLabReagentById(id));
			}
			labReagentVo.setReagentTypeId(labReagentService.getLabReagentById(
					ids[0]).getReagentTypeId());
		}
		// 二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labReagentVoList,
				LabEncoderVo.EWM_ENCODER);
		return SHOW;
	}

	public String showLabReagent4printBarCode() throws GlobalException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		labReagentVoList = new ArrayList<LabReagentVo>();
		String[] ids = labReagentVo.getId().split(",");
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				labReagentVoList.add(labReagentService.getLabReagentById(id));
			}
			labReagentVo.setReagentTypeId(labReagentService.getLabReagentById(
					ids[0]).getReagentTypeId());
		}
		// 打印条形码
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labReagentVoList,
				LabEncoderVo.TXM_ENCODER);
		return SHOW;
	}

	public String importLabReagent() throws GlobalException, IOException {
		if (null == labReagentVo) {
			labReagentVo = new LabReagentVo();
		}
		if (null == labReaTypeVo) {
			labReaTypeVo = new LabReaTypeVo();
		}

		// 上传文件
		if (null == upload) {
			addActionError(getText("file.path.unnull"));
			return ERROR;
		}
		String str[] = uploadFile();
		String fileUrl = str[1] + "\\" + str[2];
		fileUrl = fileUrl.replace('\\', '/');
		File target = new File(str[1], str[2]);
		try {
			String value[][] = OperationExcel.readExcel(fileUrl, 0);
			labReagentService.addLabReagent4Import(value,labReagentVo.getReagentTypeId());
		} catch (Exception e) {
			log.error("试剂导入error--"+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		target.delete();
		return IMPORT;
	}

	public String preImportLabReagent() throws GlobalException {
		if (labReagentVo == null) {
			labReagentVo = new LabReagentVo();
		}
		fileUrl = labConfigService.getLabConfigByCode("reagent").getValue()
				+ File.separator + "importReagent.xls";
		return PREIMPORT;
	}

	private String[] uploadFile() throws GlobalException, IOException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName
				.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext()
				.getRealPath("/uploadDoc/lab-reagent");
		String FileType = trueName.substring(trueName.lastIndexOf(".") + 1,
				trueName.length());
		String temp = Long.toString(new Date().getTime());
		String targetFileName = temp + "." + FileType;
		File file = new File(uploadDirectory, targetFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			FileUtils.copyFile(upload, file);
		} catch (IOException e) {
			log.error("试剂清单上传异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		result[0] = trueName;
		result[1] = uploadDirectory;
		result[2] = targetFileName;
		return result;
	}
	
	public String updateLabReagentList()throws GlobalException
	{
//		if (labReagentVo == null) {
//			labReagentVo = new LabReagentVo();
//		}
//		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
//				LabReagent.CODE, new String[] {}, "0");
//				ThreadBatchNumber threadBatchNumber=new ThreadBatchNumber(labNumberService,LabReagent.CODE,new String[]{},"0",41);
//			try {
//				List<String> listCode=pool.submit(threadBatchNumber).get();
//				labReagentService.updateLabReagentCodeBatch(listCode);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			} catch (ExecutionException e) {
//				// TODO Auto-generated catch block
//				//e.printStackTrace();
//			}
		return UPDATE;
	}
	
	
}
