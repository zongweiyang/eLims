package cn.labsoft.labos.source.reference.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
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
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.service.ILabRefTypeService;
import cn.labsoft.labos.source.reference.service.ILabReferenceService;
import cn.labsoft.labos.source.reference.vo.LabRefTypeVo;
import cn.labsoft.labos.source.reference.vo.LabReferenceVo;
import cn.labsoft.labos.source.supplier.service.ILabSupplierService;
import cn.labsoft.labos.source.supplier.vo.LabSupplierVo;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;

@Controller
@Scope("prototype")
public class LabReferenceAction extends BaseAction {

	private ILabEncoderService labEncoderService;
	private ILabConfigService labConfigService;
	private ILabUploadService labUploadService;
	private ILabOrgService labOrgService;
	private ILabCodeService labCodeService;
	private ILabRefTypeService labRefTypeService;
	private ILabReferenceService labReferenceService;
	private ILabNumberService labNumberService;
	private ILabTemplateService labTemplateService;
	private ILabSupplierService labSupplierService;
	
	private LabReferenceVo labReferenceVo;
	private LabRefTypeVo labRefTypeVo;
	private List<LabReferenceVo> labReferenceVoList;
	private String fileName;
	private InputStream excelStream;

	private String fileUrl;
	private File upload;
	private String uploadFileName;
	private LabEncoderVo labEncoderVo;
	
	
	@Resource
	public void setLabSupplierService(ILabSupplierService labSupplierService) {
		this.labSupplierService = labSupplierService;
	}
	@Resource
	public void setLabTemplateService(ILabTemplateService labTemplateService) {
		this.labTemplateService = labTemplateService;
	}
	@Resource
	public void setLabEncoderService(ILabEncoderService labEncoderService) {
		this.labEncoderService = labEncoderService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
	@Resource
	public void setLabUploadService(ILabUploadService labUploadService) {
		this.labUploadService = labUploadService;
	}
	@Resource
	public void setLabOrgService(ILabOrgService labOrgService) {
		this.labOrgService = labOrgService;
	}
	@Resource
	public void setLabCodeService(ILabCodeService labCodeService) {
		this.labCodeService = labCodeService;
	}
	@Resource
	public void setLabRefTypeService(ILabRefTypeService labRefTypeService) {
		this.labRefTypeService = labRefTypeService;
	}
	@Resource
	public void setLabReferenceService(ILabReferenceService labReferenceService) {
		this.labReferenceService = labReferenceService;
	}
	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
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

	public LabRefTypeVo getLabRefTypeVo() {
		return labRefTypeVo;
	}

	public void setLabRefTypeVo(LabRefTypeVo labRefTypeVo) {
		this.labRefTypeVo = labRefTypeVo;
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

	public List<LabReferenceVo> getLabReferenceVoList() {
		return labReferenceVoList;
	}

	public void setLabReferenceVoList(List<LabReferenceVo> labReferenceVoList) {
		this.labReferenceVoList = labReferenceVoList;
	}

	public LabReferenceVo getLabReferenceVo() {
		return labReferenceVo;
	}

	public void setLabReferenceVo(LabReferenceVo labReferenceVo) {
		this.labReferenceVo = labReferenceVo;
	}

	public LabRefTypeVo getLabReferenceTypeVo() {
		return labRefTypeVo;
	}

	public void setLabReferenceTypeVo(LabRefTypeVo labRefTypeVo) {
		this.labRefTypeVo = labRefTypeVo;
	}

	public String listLabReference() throws GlobalException {
		if (null == labReferenceVo)
			labReferenceVo = new LabReferenceVo();
		if (StrUtils.isBlankOrNull(labReferenceVo.getReferenceTypeId())) {
			labReferenceVo.setReferenceTypeId("0");
		}
		LabRefTypeVo type = labRefTypeService.getLabRefType(labReferenceVo.getReferenceTypeId().trim());
		
		labReferenceVo.setReferenceTypeName(type.getName());
		pageResult = labReferenceService.getLabReferencePR(labReferenceVo,
				pageResult);
		String path = labTemplateService.getLabTemplateByBusId(getSessionContainer().getFunId());
		labReferenceVo.setFilePath(path);
		return LIST;
	}

	public String showLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgListByPId("0");
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		if (StrUtils.isBlankOrNull(labReferenceVo.getUuid())) {
			labReferenceVo.setUuid(java.util.UUID.randomUUID().toString()
					.replace("-", ""));
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labReferenceVo.getId(), "lab-reference");
		setAttribute("loadList", loadList);
		labReferenceVo = labReferenceService.getLabReferenceById(labReferenceVo
				.getId());
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType( getText("standard.goods") );
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return SHOW;
	}

//	@SuppressWarnings("deprecation")
//	public String exportLabReference() throws GlobalException {
//		if (null == labReferenceVo)
//			labReferenceVo = new LabReferenceVo();
//		labReferenceVo.setReferenceTypeId(null);
//		String path = getRequest().getParameter("path");
//		List<LabReferenceVo> alertListForExcel = labReferenceService
//				.getLabReferenceList(labReferenceVo);
//		labReferenceVo.setLabReferenceList(alertListForExcel);
//		Map<String, LabReferenceVo> beans = new HashMap<String, LabReferenceVo>();
//		beans.put("data", labReferenceVo);
//
//		try {
//			fileName = new String(("标准品信息目录").getBytes(), "ISO8859_1") + ".xls";
//		} catch (UnsupportedEncodingException e) {
//			Log4J.error("格式转换错误" + e.getMessage());
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
//		String reflPath = ServletActionContext.getRequest().getRealPath("/")
//				+ "templates" + File.separator + "temp" + File.separator;
//		File file = new File(reflPath);
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
//			cn.labsoft.labos.utils.FileUtils.delAllFile(reflPath);
//		} catch (Exception e) {
//			//e.printStackTrace();
//		}
//		return EXPORT;
//	}

	public String preAddLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_BZP, new String[] {},Constants_Source.CODE_USE_INIT);
		try {
			labReferenceVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		if (StrUtils.isBlankOrNull(labReferenceVo.getUuid())) {
			labReferenceVo.setUuid(java.util.UUID.randomUUID().toString()
					.replace("-", ""));
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labReferenceVo.getUuid(), "lab-reference");
		setAttribute("loadList", loadList);
		if (null != labReferenceVo.getReferenceTypeId()) {
			LabRefTypeVo labRefTypeVo = labRefTypeService
					.getLabRefType(labReferenceVo.getReferenceTypeId().trim());
			labReferenceVo.setReferenceTypeName(labRefTypeVo.getName());
		}
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType( getText("standard.goods") );
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return PREADD;
	}

	public String addLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		ThreadNumber threadNumber = new ThreadNumber(labNumberService,
				null,Constants_Source.CODE_BZP, new String[] {},Constants_Source.CODE_USE_RUN);
		try {
			labReferenceVo.setCode(pool.submit(threadNumber).get().toString());
		} catch (Exception e) {
			//e.printStackTrace();
		}
		labReferenceVo.setIsDel(Constants_Source.N);
		labReferenceService.addLabReference(labReferenceVo);
		return ADD;
	}

	public String deleteLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		if (null != labReferenceVo.getIds()
				&& !"".equals(labReferenceVo.getIds())) {
			for (int i = 0; i < labReferenceVo.getIds().length; i++) {
				labReferenceService
						.deleteLabReference(labReferenceVo.getIds()[i]);
			}
		}
		return DELETE;
	}

	public String preUpdateLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		List<LabCodeVo> codeList = labCodeService.getLabCodeByTypeCode("WXDJ");
		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
		setAttribute("codeList", codeList);
		setAttribute("labOrgVoList", labOrgVoList);
		labReferenceVo = labReferenceService.getLabReferenceById(labReferenceVo
				.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(
				labReferenceVo.getId(), "lab-reference");
		setAttribute("loadList", loadList);
		LabSupplierVo labSupplierVo=new LabSupplierVo();
		labSupplierVo.setGoodsType( getText("standard.goods") );
		List<LabSupplierVo> supplierList=labSupplierService.getLabSupplierList(labSupplierVo);
		setAttribute("supplierList", supplierList);
		return PREUPDATE;
	}

	public String updateLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		labReferenceService.updateLabReference(labReferenceVo);
		return UPDATE;
	}

	public String preLabRefTypeTree() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		return PRETREE;
	}

	public String updateLabReferenceMove() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
			labReferenceVo.setReferenceTypeId("0");
		}
		labReferenceService.updateLabReferenceMove(labReferenceVo);
		return UPDATE;
	}

	public String showLabRef4Select() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
		}
		pageResult = labReferenceService.getLabReferencePR(labReferenceVo,
				pageResult);
		/*List<LabReferenceVo> labReferenceVoList = labReferenceService
		.getLabReferenceList(labReferenceVo);
			setAttribute("labReferenceVoList", labReferenceVoList);*/
		List<LabRefTypeVo> labRefTypeVoList = labRefTypeService.getLabRefTypeList("0", 0);
		setAttribute("labRefTypeVoList", labRefTypeVoList);
		return SHOW;
	}

	public String showTwoDimension() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		labReferenceVoList = new ArrayList<LabReferenceVo>();
		String[] ids = labReferenceVo.getIds();
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				labReferenceVoList.add(labReferenceService
						.getLabReferenceById(id));
			}
			labReferenceVo.setReferenceTypeId(labReferenceService
					.getLabReferenceById(ids[0]).getReferenceTypeId());
		}
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labReferenceVoList,
				LabEncoderVo.EWM_ENCODER);
		return SHOW;
	}

	public String showBarCode() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		labReferenceVoList = new ArrayList<LabReferenceVo>();
		String[] ids = labReferenceVo.getIds();
		if (null != ids && ids.length > 0) {
			for (String id : ids) {
				labReferenceVoList.add(labReferenceService
						.getLabReferenceById(id));
			}
			labReferenceVo.setReferenceTypeId(labReferenceService
					.getLabReferenceById(ids[0]).getReferenceTypeId());
		}
		// 二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labReferenceVoList,
				LabEncoderVo.TXM_ENCODER);
		return SHOW;
	}

	public String importLabReference() throws GlobalException, IOException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		if (null == labRefTypeVo) {
			labRefTypeVo = new LabRefTypeVo();
		}

		// 上传文件
		if (null == upload) {
			addActionError( getText("file.path.unnull") );
			return ERROR;
		}
		String str[] = uploadFile();
		String fileUrl = str[1] + "\\" + str[2];
		fileUrl = fileUrl.replace('\\', '/');
		File target = new File(str[1], str[2]);
		try {
			String value[][] = OperationExcel.readExcel(fileUrl, 0);
			labReferenceService.addLabReference4Import(value,labReferenceVo.getReferenceTypeId());

		} catch (Exception e) {
			Log4J.error("标准品导入error---" + e.getMessage());
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
				.getRealPath("/uploadDoc/lab-reference");
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
			Log4J.error("标准品清单上传异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		result[0] = trueName;
		result[1] = uploadDirectory;
		result[2] = targetFileName;
		return result;
	}

	public String preImportLabReference() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		fileUrl = labConfigService.getLabConfigByCode("reference").getValue()
				+ File.separator + "importReference.xls";
		return PREIMPORT;
	}

	public String showLabReference4printTwoCode() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		labReferenceVoList = new ArrayList<LabReferenceVo>();
		String[] ids = labReferenceVo.getId().split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labReferenceVoList.add(labReferenceService
						.getLabReferenceById(id));
			}
		}
		// 二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labReferenceVoList,
				LabEncoderVo.EWM_ENCODER);
		return SHOW;
	}

	public String showLabReference4printBarCode() throws GlobalException {
		if (null == labReferenceVo) {
			labReferenceVo = new LabReferenceVo();
		}
		labReferenceVoList = new ArrayList<LabReferenceVo>();
		String[] ids = labReferenceVo.getId().split(",");
		if (ids != null && ids.length > 0) {
			for (String id : ids) {
				labReferenceVoList.add(labReferenceService.getLabReferenceById(id));
			}
		}
		// 打印条形码
		labEncoderVo = labEncoderService.getLabEncoder4Print(
				getSessionContainer().getFunId(), labReferenceVoList,
				LabEncoderVo.TXM_ENCODER);
		return SHOW;
	}
}
