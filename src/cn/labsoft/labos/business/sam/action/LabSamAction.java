package cn.labsoft.labos.business.sam.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.annotation.Resource;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.base.org.service.ILabOrgService;
import cn.labsoft.labos.base.org.vo.LabOrgVo;
import cn.labsoft.labos.base.user.service.ILabUserService;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.sam.service.ILabSamService;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamMainVo;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.common.encoder.service.ILabEncoderService;
import cn.labsoft.labos.common.encoder.vo.LabEncoderVo;
import cn.labsoft.labos.common.number.action.ThreadBatchNumber;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;
@Controller
@Scope("prototype")
public class LabSamAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	
	private ILabSamService labSamService;
	private ILabSamTypeService labSamTypeService;
	private ILabOrgService labOrgService;
	private ILabUploadService labUploadService;
	private ILabEncoderService labEncoderService;
	private ILabConfigService labConfigService;
	private ILabUserService labUserService;
	private ILabNumberService labNumberService;
	
	private LabEncoderVo labEncoderVo;
	private LabUserVo labUserVo;
	private String fileUrl;
	private File upload;
	private String uploadFileName;
	private String fileName;
	private InputStream excelStream;
	private LabSamMainVo labSamMainVo;
	private LabSamVo labSamVo;
	private LabSamTypeVo labSamTypeVo;
	private List<LabSamVo> labSamVoList;
	
	@Resource
	public void setLabSamService(ILabSamService labSamService) {
		this.labSamService = labSamService;
	}
	@Resource
	public void setLabSamTypeService(ILabSamTypeService labSamTypeService) {
		this.labSamTypeService = labSamTypeService;
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
	public void setLabEncoderService(ILabEncoderService labEncoderService) {
		this.labEncoderService = labEncoderService;
	}
	@Resource
	public void setLabConfigService(ILabConfigService labConfigService) {
		this.labConfigService = labConfigService;
	}
	@Resource
	public void setLabUserService(ILabUserService labUserService) {
		this.labUserService = labUserService;
	}
	@Resource
	public void setLabNumberService(ILabNumberService labNumberService) {
		this.labNumberService = labNumberService;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public List<LabSamVo> getLabSamVoList() {
		return labSamVoList;
	}

	public void setLabSamVoList(List<LabSamVo> labSamVoList) {
		this.labSamVoList = labSamVoList;
	}

	public LabSamVo getLabSamVo() {
		return labSamVo;
	}

	public void setLabSamVo(LabSamVo labSamVo) {
		this.labSamVo = labSamVo;
	}

	public LabSamTypeVo getLabSamTypeVo() {
		return labSamTypeVo;
	}

	public void setLabSamTypeVo(LabSamTypeVo labSamTypeVo) {
		this.labSamTypeVo = labSamTypeVo;
	}

	public LabEncoderVo getLabEncoderVo() {
		return labEncoderVo;
	}
	public void setLabEncoderVo(LabEncoderVo labEncoderVo) {
		this.labEncoderVo = labEncoderVo;
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

	public LabUserVo getLabUserVo() {
		return labUserVo;
	}

	public void setLabUserVo(LabUserVo labUserVo) {
		this.labUserVo = labUserVo;
	}
	
	public LabSamMainVo getLabSamMainVo() {
		return labSamMainVo;
	}

	public void setLabSamMainVo(LabSamMainVo labSamMainVo) {
		this.labSamMainVo = labSamMainVo;
	}

	public String listLabSam() throws GlobalException {
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		if (null == labSamTypeVo)
			labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
		List<LabSamTypeVo> labSamTypeVoList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		setAttribute("labSamTypeVoList", labSamTypeVoList);
		pageResult = labSamService.getLabSamMainPR(labSamMainVo, pageResult);
		return LIST;
	}

	public String showLabSam() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}

		labSamMainVo = labSamService.getLabSamMainById(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return SHOW;
	}
	public String showLabSam4Division() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}

		labSamMainVo = labSamService.getLabSamMainById(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return SHOW;
	}
	@SuppressWarnings("unchecked")
	public String preAddLabSam() throws GlobalException {
		if (labSamMainVo == null) labSamMainVo = new LabSamMainVo();
		try {
			labSamMainVo.setSampNo(pool.submit(new ThreadNumber(labNumberService,null,Constants_Business.CODE_LY,new String[]{""},Constants_Business.CODE_USE_INIT)).get().toString());
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
		if (null == labSamTypeVo)labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
		List<LabSamTypeVo> labSamTypeVoList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		setAttribute("labSamTypeVoList", labSamTypeVoList);
		if (StrUtils.isBlankOrNull(labSamMainVo.getUuid())) {
			labSamMainVo.setUuid(java.util.UUID.randomUUID().toString().replace("-", ""));
		}
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getUuid(), "sam");
		setAttribute("loadList", loadList);
		return PREADD;
	}

	@SuppressWarnings("unchecked")
	public String addLabSam() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		try {
			labSamMainVo.setSampNo(pool.submit(new ThreadNumber(labNumberService,null,Constants_Business.CODE_LY,new String[]{""},Constants_Business.CODE_USE_RUN)).get().toString());
			labSamMainVo.setListCode(pool.submit(new ThreadBatchNumber(labNumberService,null,Constants_Business.CODE_YP,new String[]{""},"1",labSamMainVo.getLabSamVoList().size())).get());
		} catch (InterruptedException e) {
			Log4J.error("LabSamAction..."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			Log4J.error("LabSamAction..."+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		labSamService.addLabSamMain(labSamMainVo);
		return ADD;
	}

	public String deleteLabSam() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		if (labSamMainVo.getIds() != null && !"".equals(labSamMainVo.getIds())) {
			for (int i = 0; i < labSamMainVo.getIds().length; i++) {
				labSamService.deleteLabSam(labSamMainVo.getIds()[i]);
			}
		}
		return DELETE;
	}

	public String preUpdateLabSam() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
//		List<LabOrgVo> labOrgVoList = labOrgService.getLabOrgTree();
//		labOrgVoList = labOrgService.getUsedLabOrgList(labOrgVoList);
//		setAttribute("labOrgVoList", labOrgVoList);
		if (null == labSamTypeVo){
			labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
		}
		List<LabSamTypeVo> labSamTypeVoList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		setAttribute("labSamTypeVoList", labSamTypeVoList);
		labSamMainVo = labSamService.getLabSamMainById(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return PREUPDATE;
	}

	public String updateLabSam() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		labSamService.updateLabSamMain(labSamMainVo);
		return UPDATE;
	}
	
	public String listLabSam4Division() throws GlobalException {
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		if (null == labSamTypeVo)
			labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
		pageResult = labSamService.getLabSamMainPR4Division(labSamMainVo, pageResult);
		return LIST;
	}
	public String preUpdateLabSam4Division() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		if (null == labSamTypeVo)
			labSamTypeVo = new LabSamTypeVo();
			labSamTypeVo.setPid("0");
		List<LabSamTypeVo> labSamTypeVoList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		setAttribute("labSamTypeVoList", labSamTypeVoList);
		labSamMainVo = labSamService.getLabSamMainById(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return PREUPDATE;
	}
	public String updateLabSam4Division() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		labSamService.updateLabSamMain4Division(labSamMainVo);
		return UPDATE;
	}
	
	public String showLabSam4printTwoCode() throws GlobalException {
		if (labSamMainVo == null) {
			return ERROR;
		}
		labSamVoList=labSamService.getLabSamList(labSamMainVo);
		// 二维码打印
		labEncoderVo = labEncoderService.getLabEncoder4Print(getSessionContainer().getFunId(),labSamVoList, LabEncoderVo.EWM_ENCODER);
		return "print";
	}

	public String showLabSam4printBarCode() throws GlobalException {
		if (labSamMainVo == null) {
			return ERROR;
		}
		labSamVoList=labSamService.getLabSamList(labSamMainVo);
		// 打印条形码
		labEncoderVo = labEncoderService.getLabEncoder4Print(getSessionContainer().getFunId(),labSamVoList, LabEncoderVo.TXM_ENCODER);
		return "print";
	}

	@SuppressWarnings("deprecation")
	public String preImportLabSam() throws GlobalException {
		if (labSamVo == null) {
			labSamVo = new LabSamVo();
		}
		fileUrl = ServletActionContext.getRequest().getRealPath("/") + labConfigService.getLabConfigByCode("sam").getValue() + File.separator + "importSam.xls";
		return PREADD;
	}

	public String importLabSam() throws GlobalException, IOException {
		if (labSamVo == null) {
			labSamVo = new LabSamVo();
		}
		if (null == labSamTypeVo)
			labSamTypeVo = new LabSamTypeVo();

		if (upload == null) {
			addActionError("文件路径不允许为空!");
			return ERROR;
		}
		String str[] = uploadFile();
		String fileUrl = str[1] + "\\" + str[2];
		fileUrl = fileUrl.replace('\\', '/');
		File target = new File(str[1], str[2]);
		try {
			String value[][] = OperationExcel.readExcel(fileUrl, 0);
			if (null != value && value.length > 0) {
				for (int j = 2; j < value.length; j++) {
					labSamVo.setIsDel(Constants_Business.N);
					if (null != labSamVo.getSamTypeId() && !"".equals(labSamVo.getSamTypeId())) {
						labSamTypeVo.setId(labSamVo.getSamTypeId());
						labSamTypeVo = labSamTypeService.getLabSamTypeByVo(labSamTypeVo);
						if (labSamTypeVo != null) {
							labSamVo.setSamTypeId(labSamTypeVo.getId());
							labSamVo.setSamTypeName(labSamTypeVo.getName());
						} else {
							labSamVo.setSamTypeId("0");
						}
					}

					if (null != value[j][1] && !"".equals(value[j][1])) {
						labSamVo.setName(value[j][1]);
					}

					if (null != value[j][2] && !"".equals(value[j][2])) {
						labSamVo.setEname(value[j][2]);
					}

					if (null != value[j][3] && !"".equals(value[j][3])) {
						labSamVo.setSaveMode(value[j][3]);
					}

					if (null != value[j][4] && !"".equals(value[j][4])) {
						labSamVo.setPosition(value[j][4]);
					}

					if (null != value[j][5] && !"".equals(value[j][5])) {
						labSamVo.setSafeDate(Integer.valueOf(value[j][5]));
					}

					if (null != value[j][6] && !"".equals(value[j][6])) {
						labSamVo.setTotal(value[j][6]);
					}
					if (null != value[j][7] && !"".equals(value[j][7])) {
						labSamVo.setShape(value[j][7]);
					}
					if (null != value[j][8] && !"".equals(value[j][8])) {
						LabOrgVo orgVo = new LabOrgVo();
						orgVo.setName(value[j][8]);
						LabOrgVo labOrgVo = labOrgService.getLabOrgByVo(orgVo);
						if (labOrgVo != null) {
							labSamVo.setSaveOrg(labOrgVo.getId());
							labSamVo.setSaveOrgName(labOrgVo.getName());
						}
					}
					if (null != value[j][9] && !"".equals(value[j][9])) {
						labSamVo.setSaveUser(value[j][9]);
					}

					if (null != value[j][10] && !"".equals(value[j][10])) {
						labSamVo.setRemark(value[j][10]);
					}
					labSamService.addLabSam(labSamVo);
				}
			}

		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		target.delete();
		return LIST;
	}

	private String[] uploadFile() throws GlobalException, IOException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext().getRealPath("/uploadDoc/sam");
		String FileType = trueName.substring(trueName.lastIndexOf(".") + 1, trueName.length());
		String temp = Long.toString(new Date().getTime());
		String targetFileName = temp + "." + FileType;
		File file = new File(uploadDirectory, targetFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			FileUtils.copyFile(upload, file);
		} catch (IOException e) {
			Log4J.error("样品导入异常---" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		result[0] = trueName;
		result[1] = uploadDirectory;
		result[2] = targetFileName;
		return result;
	}

	@SuppressWarnings("deprecation")
	public String listLabSam4Export() throws GlobalException {
		if (labSamVo == null) {
			labSamVo = new LabSamVo();
		}
		labSamVo.setSamTypeId(null);
		List<LabSamVo> alertListForExcel = labSamService.getLabSamList(labSamVo);
		labSamVo.setLabSamVoList(alertListForExcel);
		Map<String, LabSamVo> beans = new HashMap<String, LabSamVo>();
		beans.put("data", labSamVo);

		try {
			fileName = new String(("样品信息目录").getBytes(), "ISO8859_1") + ".xls";
		} catch (UnsupportedEncodingException e) {
			Log4J.error("格式转换错误" + e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}

		String template = "exportLabSam.xls";
		String templateFileName = ServletActionContext.getRequest().getRealPath("/") + labConfigService.getLabConfigByCode("sam").getValue() + File.separator + template;

		String destFileName = ServletActionContext.getRequest().getRealPath("/") + "templates" + File.separator + "temp" + File.separator + System.currentTimeMillis() + "." + "xls";

		String reflPath = ServletActionContext.getRequest().getRealPath("/") + "templates" + File.separator + "temp" + File.separator;
		File file = new File(reflPath);
		if (!file.exists()) {
			file.mkdir();
		}

		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(templateFileName, beans, destFileName);
			File targetFile = new File(destFileName);
			excelStream = new BufferedInputStream(new FileInputStream(targetFile), 16 * 1024);
			cn.labsoft.labos.utils.FileUtils.delAllFile(reflPath);
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return "alertExcel";
	}

	// 根据orgId获取用户列表
	public String showLabUser4Select() throws GlobalException {
		if (null == labUserVo) {
			labUserVo = new LabUserVo();
		}
		List<LabUserVo> userList = labUserService.getLabUserListByOrgId(labUserVo.getOrgId());
		setAttribute("userList", userList);
		return LIST;
	}
	
	/**
	 * 样品流转
	 * @Title:  
	 * @Description: TODO
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	
	public String listLabSam4Out() throws GlobalException {
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		pageResult = labSamService.getLabSamMainPR4Out(labSamMainVo, pageResult);
		return LIST;
	}
	public String preUpdateLabSam4Out() throws GlobalException {
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		labSamMainVo = labSamService.getLabSamMain4Out(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return PREUPDATE;
	}
	public String updateLabSam4Out() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		labSamService.updateLabSamMain4Out(labSamMainVo);
		return UPDATE;
	}
	public String showLabSam4Out() throws GlobalException {
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		labSamMainVo = labSamService.getLabSamMain4Out(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return SHOW;
	}
	public String listLabSam4Destory() throws GlobalException {
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		pageResult = labSamService.getLabSamMainPR4Destory(labSamMainVo, pageResult);
		return LIST;
	}
	public String showLabSam4Destory() throws  GlobalException{
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		labSamMainVo = labSamService.getLabSamMain4Destory(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return SHOW;
	}
	public String preUpdateLabSam4Destory() throws  GlobalException{
		if (null == labSamMainVo)
			labSamMainVo = new LabSamMainVo();
		labSamMainVo = labSamService.getLabSamMain4Destory(labSamMainVo.getId());
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamMainVo.getId(), "sam");
		setAttribute("loadList", loadList);
		return PREUPDATE;
	}
	public String updateLabSam4Destory() throws GlobalException {
		if (labSamMainVo == null) {
			labSamMainVo = new LabSamMainVo();
		}
		labSamService.updateLabSamMain4Destory(labSamMainVo);
		return UPDATE;
	}
	
}
