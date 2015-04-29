package cn.labsoft.labos.source.consumables.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import cn.labsoft.labos.source.consumables.service.ILabConTypeService;
import cn.labsoft.labos.source.consumables.service.ILabConsumablesService;
import cn.labsoft.labos.source.consumables.vo.LabConTypeVo;
import cn.labsoft.labos.source.consumables.vo.LabConsumablesVo;
import cn.labsoft.labos.source.supplier.service.ILabSupplierService;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;
@Controller
@Scope("prototype")
public class LabConsumablesAction extends BaseAction {

	private LabConsumablesVo labConsumablesVo;
	private LabConTypeVo labConTypeVo;
	private List<LabConsumablesVo> labConsumablesVoList;
	private ILabNumberService labNumberService;
	private ILabConsumablesService labConsumablesService;
	private ILabConTypeService labConTypeService;
	private ILabCodeService labCodeService;
	private ILabOrgService labOrgService;
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
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}
	
	@Resource
	public void setLabSupplierService(ILabSupplierService labSupplierService) {
		this.labSupplierService = labSupplierService;
	}
	
	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
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

	public LabEncoderVo getLabEncoderVo() {
		return labEncoderVo;
	}
	public void setLabEncoderVo(LabEncoderVo labEncoderVo) {
		this.labEncoderVo = labEncoderVo;
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

	public LabConTypeVo getLabConTypeVo() {
		return labConTypeVo;
	}

	public void setLabConTypeVo(LabConTypeVo labConTypeVo) {
		this.labConTypeVo = labConTypeVo;
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

	public List<LabConsumablesVo> getLabConsumablesVoList() {
		return labConsumablesVoList;
	}

	public void setLabConsumablesVoList(
			List<LabConsumablesVo> labConsumablesVoList) {
		this.labConsumablesVoList = labConsumablesVoList;
	}

	public LabConsumablesVo getLabConsumablesVo() {
		return labConsumablesVo;
	}

	public void setLabConsumablesVo(LabConsumablesVo labConsumablesVo) {
		this.labConsumablesVo = labConsumablesVo;
	}

	public LabConTypeVo getLabConsumablesTypeVo() {
		return labConTypeVo;
	}

	public void setLabConsumablesTypeVo(LabConTypeVo labConTypeVo) {
		this.labConTypeVo = labConTypeVo;
	}

	public String listLabConsumables() throws GlobalException {
		if (null == labConsumablesVo)
			labConsumablesVo = new LabConsumablesVo();
		if (StrUtils.isBlankOrNull(labConsumablesVo.getConsumablesTypeId())) {
			labConsumablesVo.setConsumablesTypeId("0");
		}
		LabConTypeVo type = labConTypeService.getLabConType(labConsumablesVo.getConsumablesTypeId().trim());
		
		labConsumablesVo.setConsumablesTypeName(type.getName());
		
		pageResult = labConsumablesService.getLabConsumablesPR(labConsumablesVo, pageResult);
		
		String path = labTemplateService.getLabTemplateByBusId(getSessionContainer().getFunId());
		labConsumablesVo.setFilePath(path);
		return LIST;
	}

	public String showLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgListByPId("0");
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labConsumablesVo.getId(), "lab-consumables");
		setAttribute("loadList", loadList);
		labConsumablesVo = labConsumablesService
				.getLabConsumablesById(labConsumablesVo.getId());
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType("耗材");
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return SHOW;
	}

//	public String exportLabConsumables() throws GlobalException {
//		if (null == labConsumablesVo)
//			labConsumablesVo = new LabConsumablesVo();
//		String path = getRequest().getParameter("path");
//		List<LabConsumablesVo> alertListForExcel = labConsumablesService
//				.getLabConsumablesList(labConsumablesVo);
//		labConsumablesVo.setLabConsumablesList(alertListForExcel);
//		Map<String, LabConsumablesVo> beans = new HashMap<String, LabConsumablesVo>();
//		beans.put("data", labConsumablesVo);
//
//		try {
//			fileName = new String(("耗材信息目录").getBytes(), "ISO8859_1") + ".xls";
//		} catch (UnsupportedEncodingException e) {
//			log.error("格式转换错误" + e.getMessage());
//		}
//
//		String templateFileName = ServletActionContext.getRequest()
//				.getRealPath("/")
//				+ path.replace("\\", File.separator);
//
//		String destFileName = ServletActionContext.getRequest()
//				.getRealPath("/")
//				+ "templates"
//				+ File.separator
//				+ "temp"
//				+ File.separator
//				+ System.currentTimeMillis() + "." + "xls";
//
//		String conlPath = ServletActionContext.getRequest().getRealPath("/")
//				+ "templates" + File.separator + "temp" + File.separator;
//		File file = new File(conlPath);
//		if (!file.exists()) {
//			file.mkdir();
//		}
//
//		XLSTransformer transformer = new XLSTransformer();
//		try {
//			transformer.transformXLS(templateFileName, beans, destFileName);
//			File targetFile = new File(destFileName);
//			excelStream = new BufferedInputStream(new FileInputStream(
//					targetFile), 16 * 1024);
//			path = targetFile.getPath();
//			cn.labsoft.labos.utils.FileUtils.delAllFile(conlPath);
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		return EXPORT;
//	}

	public String preAddLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_HC, new String[] {},Constants_Source.CODE_USE_INIT);
		try {
			labConsumablesVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			//e.printStackTrace();
			}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		if (StrUtils.isBlankOrNull(labConsumablesVo.getUuid())) {
			labConsumablesVo.setUuid(java.util.UUID.randomUUID().toString()
					.replace("-", ""));
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labConsumablesVo.getUuid(), "lab-consumables");
		setAttribute("loadList", loadList);
		if (null != labConsumablesVo.getConsumablesTypeId()) {
			LabConTypeVo labConTypeVo = labConTypeService
					.getLabConType(labConsumablesVo.getConsumablesTypeId()
							.trim());
			labConsumablesVo.setConsumablesTypeName(labConTypeVo.getName());
		}
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType(getText("consumables.name"));
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return PREADD;
	}

	public String addLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_HC, new String[] {},Constants_Source.CODE_USE_RUN);
		try {
			labConsumablesVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			//e.printStackTrace();
			}
		labConsumablesVo.setIsDel(Constants_Source.N);
		labConsumablesService.addLabConsumables(labConsumablesVo);
		return ADD;
	}

	public String deleteLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		if (null != labConsumablesVo.getIds()
				&& !"".equals(labConsumablesVo.getIds())) {
			for (int i = 0; i < labConsumablesVo.getIds().length; i++) {
				labConsumablesService.deleteLabConsumables(labConsumablesVo
						.getIds()[i]);
			}
		}
		return DELETE;
	}

	public String preUpdateLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		labConsumablesVo = labConsumablesService
				.getLabConsumablesById(labConsumablesVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labConsumablesVo.getId(), "lab-consumables");
		setAttribute("loadList", loadList);
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType(getText("consumables.name"));
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return PREUPDATE;
	}

	public String updateLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		labConsumablesService.updateLabConsumables(labConsumablesVo);
		return UPDATE;
	}

	public String preLabConTypeTree() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		return PRETREE;
	}

	public String updateLabConsumablesMove() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
			labConsumablesVo.setConsumablesTypeId("0");
		}
		labConsumablesService.updateLabConsumablesMove(labConsumablesVo);
		return UPDATE;
	}

	public String showLabCon4Select() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
		}
		pageResult = labConsumablesService.getLabConsumablesList(labConsumablesVo, pageResult);
		List<LabConTypeVo> labConTypeVoList = labConTypeService.getLabConTypeList("0",0);
		setAttribute("labConTypeVoList", labConTypeVoList);
		return SHOW;
	}

	public String showTwoDimension() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		labConsumablesVoList = new ArrayList<LabConsumablesVo>();
		String[] ids = labConsumablesVo.getIds();
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				labConsumablesVoList.add(labConsumablesService
						.getLabConsumablesById(id));
			}
			labConsumablesVo.setConsumablesTypeId(labConsumablesService
					.getLabConsumablesById(ids[0]).getConsumablesTypeId());
		}
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labConsumablesVoList,
				LabEncoderVo.EWM_ENCODER);
		return SHOW;
	}

	public String showBarCode() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		labConsumablesVoList = new ArrayList<LabConsumablesVo>();
		String[] ids = labConsumablesVo.getIds();
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labConsumablesVoList.add(labConsumablesService
						.getLabConsumablesById(id));
			}
			labConsumablesVo.setConsumablesTypeId(labConsumablesService
					.getLabConsumablesById(ids[0]).getConsumablesTypeId());
		}
		// 二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labConsumablesVoList,
				LabEncoderVo.TXM_ENCODER);
		return SHOW;
	}

	public String importLabConsumables() throws GlobalException, IOException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		if (null == labConTypeVo) {
			labConTypeVo = new LabConTypeVo();
		}
		// 上传文件
		if (upload == null) {
			addActionError(getText("file.path.unnull"));
			return ERROR;
		}
		String str[] = uploadFile();
		String fileUrl = str[1] + "\\" + str[2];
		fileUrl = fileUrl.replace('\\', '/');
		File target = new File(str[1], str[2]);
		try {
			String value[][] = OperationExcel.readExcel(fileUrl, 0);
			labConsumablesService.addLabComsumables4Import(value,labConsumablesVo.getConsumablesTypeId());
		} catch (Exception e) {
			log.error("耗材批量导入异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		target.delete();
		return IMPORT;
	}

	private String[] uploadFile() throws GlobalException, IOException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName
				.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext()
				.getRealPath("/uploadDoc/lab-consumables");
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
			log.error("耗材清单上传异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		result[0] = trueName;
		result[1] = uploadDirectory;
		result[2] = targetFileName;
		return result;
	}

	public String preImportLabConsumables() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		fileUrl = labConfigService.getLabConfigByCode("consumables").getValue()
				+ File.separator + "importConsumables.xls";
		return PREIMPORT;
	}

	public String showLabConsumables4printTwoCode() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		labConsumablesVoList = new ArrayList<LabConsumablesVo>();
		String[] ids = labConsumablesVo.getId().split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labConsumablesVoList.add(labConsumablesService
						.getLabConsumablesById(id));
			}
		}
		// 二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labConsumablesVoList,
				LabEncoderVo.EWM_ENCODER);
		return SHOW;
	}

	public String showLabConsumables4printBarCode() throws GlobalException {
		if (null == labConsumablesVo) {
			labConsumablesVo = new LabConsumablesVo();
		}
		labConsumablesVoList = new ArrayList<LabConsumablesVo>();
		String[] ids = labConsumablesVo.getId().split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labConsumablesVoList.add(labConsumablesService
						.getLabConsumablesById(id));
			}
		}
		// 打印条形码
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labConsumablesVoList,
				LabEncoderVo.TXM_ENCODER);
		return SHOW;
	}



}
