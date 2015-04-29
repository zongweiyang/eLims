package cn.labsoft.labos.business.samtest.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.configs.service.ILabConfigService;
import cn.labsoft.labos.business.samtest.service.ILabSamTestService;
import cn.labsoft.labos.business.samtest.vo.LabSamTestBeatchVo;
import cn.labsoft.labos.business.samtest.vo.LabSamTestVo;
import cn.labsoft.labos.common.upload.sevice.ILabUploadService;
import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.ambient.service.ILabAmbientInfoService;
import cn.labsoft.labos.source.ambient.service.ILabAmbientService;
import cn.labsoft.labos.source.ambient.vo.LabAmbientInfoVo;
import cn.labsoft.labos.source.ambient.vo.LabAmbientVo;
import cn.labsoft.labos.source.appara.service.ILabApparaUseService;
import cn.labsoft.labos.source.appara.vo.LabApparaUseVo;
import cn.labsoft.labos.source.klg.service.ILabMethodService;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.source.reagent.service.ILabReaOutMainService;
import cn.labsoft.labos.source.reagent.vo.LabReaOutDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaOutMainVo;
import cn.labsoft.labos.source.reagent.vo.LabReaPurDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaPurMainVo;
import cn.labsoft.labos.source.reference.service.ILabRefOutMainService;
import cn.labsoft.labos.source.reference.vo.LabRefOutDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefOutMainVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
/**
 * stone
 * @author Administrator
 * 检测管理---数据管理（数据录入 数据校验，数据汇总）
 */
@Controller
@Scope("prototype")
public class LabSamTestAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private ILabSamTestService labSamTestService;
	@Resource
	private ILabMethodService labMethodService;
	@Resource
	private ILabWfProcessInsService labWfProcessInsService;
	@Resource
	private ILabApparaUseService labApparaUseService;
	@Resource
	private ILabUploadService labUploadService;
	@Resource
	private ILabConfigService labConfigService;
	@Resource
	private ILabAmbientService labAmbientService;
	@Resource
	private ILabAmbientInfoService labAmbientInfoService;
	@Resource
	private ILabRefOutMainService labRefOutMainService;
	@Resource
	private ILabReaOutMainService labReaOutMainService;
	private LabReaOutMainVo labReaOutMainVo;

	private LabSamTestBeatchVo labSamTestBeatchVo;
	private LabSamTestVo labSamTestVo;
	private LabRefOutMainVo labRefOutMainVo;
	private List<LabReaPurDetailVo> reaPurDetailList;
	private List<LabSamTestVo> listLabSamTestVo;
	private List<LabSamTestVo> listLabSamTestStandVo;
	private List<LabApparaUseVo> listLabApparaUseVo;
	private LabReaPurMainVo labReaPurMainVo;
	private String reagentids;
	private String referenceIds;
	private String reagentIds;
	private File upload;// 实际上传文件
	private String uploadFileName;
	private String uploadAllFileName;
	private String uploadContentType; // 文件的内容类型
	private String fileName;
	private InputStream excelStream;
	private LabAmbientVo labAmbientVo;
	private LabAmbientInfoVo labAmbientInfoVo;
	private List<LabAmbientVo> listLabAmbientVo;
	private String referenceids;
	private String tabNum;
	private String message;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTabNum() {
		return tabNum;
	}
	public void setTabNum(String tabNum) {
		this.tabNum = tabNum;
	}
	public LabAmbientVo getLabAmbientVo() {
		return labAmbientVo;
	}
	public void setLabAmbientVo(LabAmbientVo labAmbientVo) {
		this.labAmbientVo = labAmbientVo;
	}
	
	public LabAmbientInfoVo getLabAmbientInfoVo() {
		return labAmbientInfoVo;
	}
	public void setLabAmbientInfoVo(LabAmbientInfoVo labAmbientInfoVo) {
		this.labAmbientInfoVo = labAmbientInfoVo;
	}
	public List<LabAmbientVo> getListLabAmbientVo() {
		return listLabAmbientVo;
	}
	public void setListLabAmbientVo(List<LabAmbientVo> listLabAmbientVo) {
		this.listLabAmbientVo = listLabAmbientVo;
	}
	public InputStream getExcelStream() {
		return excelStream;
	}
	public void setExcelStream(InputStream excelStream) {
		this.excelStream = excelStream;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	/**
	 * 初始化批次表
	 */
	public void initialiseBeatch(){
		if(labSamTestBeatchVo==null){
			labSamTestBeatchVo=new LabSamTestBeatchVo();
			labSamTestBeatchVo.setStatus(getSessionContainer().getFunId());
		}
	}
	/**
	 * 初始化检测表
	 */
	public void initialiseTest(){
		if(labSamTestVo==null)labSamTestVo=new LabSamTestVo();
	}
	/**
	 * 数据录入 LIST
	 * @return
	 * @throws GlobalException 
	 */
	public String listLabSamTest() throws GlobalException{
		initialiseBeatch();
		pageResult=labSamTestService.getLabSamTestPR(labSamTestBeatchVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 数据录入---项目信息维护 方法，标准
	 * @return
	 * @throws GlobalException 
	 */
	public String preUpdateLabSamTest() throws GlobalException{
		initialiseBeatch();
		tabSave();
		listLabSamTestVo=labSamTestService.getLabSamTestBeatchVo(labSamTestBeatchVo);
		return PREUPDATE;
	}
	/**
	 * ajax检测方法
	 * @throws GlobalException
	 * @throws IOException
	 */
	public void ajaxLabSamTest4Method() throws GlobalException, IOException{
		initialiseTest();
		List<LabMethodVo> listLabMethodVo=labMethodService.getLabStandardItemMethodByItems(labSamTestVo.getItemId(), labSamTestVo.getStandardId());
		outPutString(JSONSerializer.toJSON(listLabMethodVo).toString());
	}
	/**数据录入 --
	 * 项目对应检测方法 检测标准 UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabSamTest()throws GlobalException{
		initialiseBeatch();
		initialiseTest();
		labSamTestService.updateLabSamTest4Standard(labSamTestBeatchVo, listLabSamTestVo);
		if(StrUtils.isBlankOrNull(labSamTestBeatchVo.getAuditResult())){
			return "save";
		}else{
			return UPDATE;
		}
	}
	/**
	 * 数据录入   样品信息显示  preUpdate
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateSamTest4Data() throws GlobalException{
		initialiseBeatch();
		tabSave();
		listLabSamTestVo=labSamTestService.getLabSamTestVo4Date(labSamTestBeatchVo);
		List<LabUploadVo> loadList = labUploadService.getLabUploadList(labSamTestBeatchVo.getId(), "lab_testDate");
		setAttribute("loadList", loadList);
		List<LabUploadVo> loadList1 = labUploadService.getLabUploadList(labSamTestBeatchVo.getId(), "lab_testDate_fj");
		setAttribute("loadList1", loadList1);
		return PREUPDATE;
		
	}
	public String exportLabSamTest() throws Exception{
		initialiseBeatch();
		listLabSamTestVo=labSamTestService.getLabSamTestVo4Date(labSamTestBeatchVo);
		String filePath=labSamTestService.writeExcel(listLabSamTestVo);
		fileName = new String(("检测单").getBytes(), "ISO8859_1") + ".xls";
		excelStream = new BufferedInputStream(new FileInputStream(
				filePath), 16 * 1024);
		return EXPORT;
	}
	public String updateLabSamTest4Validate(){
		initialiseBeatch();
		try {
			String str[] = uploadFile();
			String fileUrl = str[1] +File.separator+ str[2];
			message=labSamTestService.updateLabSamTest4Validate(labSamTestBeatchVo, fileUrl);
			if(StrUtils.isBlankOrNull(message))
				labSamTestService.updateLabSamTest4ImportDate(labSamTestBeatchVo, fileUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return UPDATE;
	}
	public String updateLabSamTest4ImportDate(){
		initialiseBeatch();
		try {
			String str[] = uploadFile();
			String fileUrl = str[1] +File.separator+ str[2];
			labSamTestService.updateLabSamTest4ImportDate(labSamTestBeatchVo, fileUrl);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return UPDATE;
	}
	private String[] uploadFile() throws IOException, GlobalException {
		String result[] = new String[3];
		String trueName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1, uploadFileName.length());
		String uploadDirectory = ServletActionContext.getServletContext().getRealPath(labConfigService.getLabConfigByCode("dataImport").getValue());
		String FileType = trueName.substring(trueName.lastIndexOf(".") + 1, trueName.length());
		String temp = Long.toString(new Date().getTime());
		String targetFileName = temp + "." + FileType;
		File file = new File(uploadDirectory, targetFileName);
		if (!file.exists()) {
			file.createNewFile();
		}
		try {
			org.apache.commons.io.FileUtils.copyFile(upload, file);
		} catch (IOException e) {
			//e.printStackTrace();
		}
		result[0] = trueName;
		result[1] = uploadDirectory;
		result[2] = targetFileName;
		return result;
	}
	public String showLabSamTestInfo() throws GlobalException{
		initialiseBeatch();
		labSamTestBeatchVo=labSamTestService.getLabSamTestBeatchById(labSamTestBeatchVo);
		listLabSamTestVo=labSamTestService.getLabSamTestVo4Appara(labSamTestBeatchVo);
		listLabApparaUseVo=labApparaUseService.getLabApparaUseList(labSamTestBeatchVo.getId());
		listLabSamTestStandVo=labSamTestService.getLabSamTestBeatchVo(labSamTestBeatchVo);
		labAmbientInfoVo=labAmbientInfoService.getLabAmbientInfoByBeatchId(labSamTestBeatchVo.getId());
		labReaOutMainVo = labReaOutMainService.getLabReaOutMainById(labSamTestBeatchVo.getReagentOutId());
		labRefOutMainVo = labRefOutMainService.getLabRefOutMainById(labSamTestBeatchVo.getReferenceOutId());
		return SHOW;
	}
	/**
	 * 数据录入 检测值录入
	 * @return
	 * @throws GlobalException 
	 */
	public String updateLabSamTest4Date() throws GlobalException{
		if(listLabSamTestVo==null)listLabSamTestVo=new ArrayList<LabSamTestVo>();
		initialiseBeatch();
		labSamTestService.updateLabSamTest4Date(labSamTestBeatchVo,listLabSamTestVo);
		if (!StrUtils.isBlankOrNull(labSamTestBeatchVo.getAuditResult())) 
			return LIST;
		return UPDATE;
	}
	/**
	 * 数据录入--仪器信息记录 PREUPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String preUpdateLabSamTest4Appara()throws GlobalException{
		initialiseBeatch();
		tabSave();
		listLabSamTestVo=labSamTestService.getLabSamTestVo4Appara(labSamTestBeatchVo);
		return PREUPDATE;
	}
	/**
	 * 数据录入 ---仪器信息记录 UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabSamTest4Appara()throws GlobalException{
		initialiseBeatch();
		if(listLabApparaUseVo==null)listLabApparaUseVo=new ArrayList<LabApparaUseVo>();
		labApparaUseService.updateLabApparaUseList(listLabApparaUseVo,labSamTestBeatchVo.getId());
		labSamTestService.updateLabSamTestVo4Appara(labSamTestBeatchVo, listLabApparaUseVo);
		return UPDATE;
	}
	/**
	 * 数据录入 ---环境配置
	 * @return
	 * @throws GlobalException 
	 */
	public String preUpdateSamTest4Ambient() throws GlobalException{
		initialiseBeatch();
		tabSave();
		if(null==labAmbientVo)labAmbientVo=new LabAmbientVo();
		if(labAmbientInfoVo==null)labAmbientInfoVo=new LabAmbientInfoVo();
		labAmbientInfoVo=labAmbientInfoService.getLabAmbientInfoByBeatchId(labSamTestBeatchVo.getId());
		if(!StrUtils.isBlankOrNull(labAmbientInfoVo.getId()))labAmbientInfoVo.setTime(DateUtils.format(new Date(), DateUtils.formatStr_yyyyMMddHHmm));
		listLabAmbientVo=labAmbientService.getLabAmbientList(labAmbientVo);
		getRequest().setAttribute("listLabAmbientVo", listLabAmbientVo);
		return PREUPDATE;
	}
	/**
	 * 数据录入 ---环境配置
	 * @return
	 * @throws GlobalException 
	 */
	public String updateSamTest4Ambient() throws GlobalException{
		initialiseBeatch();
		if(null!=labAmbientInfoVo){
			labAmbientInfoVo.setSamBeatchId(labSamTestBeatchVo.getId());
			labAmbientInfoVo=labAmbientInfoService.addLabAmbientInfo(labAmbientInfoVo);
		}
		return UPDATE;
	}
	public String preUpdateSamTest4Reagent() throws GlobalException{
		initialiseBeatch();tabSave();
		if (null == labReaOutMainVo) labReaOutMainVo = new LabReaOutMainVo();
		labSamTestBeatchVo=labSamTestService.getLabSamTestBeatchById(labSamTestBeatchVo);
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getReagentOutId())){
			labReaOutMainVo=labReaOutMainService.getLabReaOutMainById(labSamTestBeatchVo.getReagentOutId(),reagentIds);
		}else{
			labReaOutMainVo.setOutstoreDate(DateUtils.format(new Date(),
					"yyyy-MM-dd"));
			labReaOutMainVo.setOutstorer(getSessionContainer().getUserName());
			String receiptno = labReaOutMainService.getMaxLabReaOutMainReceiptno();
			labReaOutMainVo.setReceiptno(receiptno);
			List<LabReaOutDetailVo> labReaOutDetailVoList = labReaOutMainService.getLabReaOutDetailListByReagentId(reagentIds);
			labReaOutMainVo.setLabReaOutDetailVoList(labReaOutDetailVoList);
		}
		return PREUPDATE;
	}
	/**
	 * 试剂使用保存
	 * @return
	 * @throws GlobalException
	 */
	public String updateSamTest4Reagent() throws GlobalException{
		initialiseBeatch();
		if (null == labReaOutMainVo) {
			labReaOutMainVo = new LabReaOutMainVo();
		}
		labSamTestService.updateLabSamTest4Reagent(labSamTestBeatchVo,labReaOutMainVo);
		return UPDATE;
	}
	public String preUpdateSamTest4Reference() throws GlobalException{
		initialiseBeatch();tabSave();
		if (null == labRefOutMainVo) labRefOutMainVo = new LabRefOutMainVo();
		labSamTestBeatchVo=labSamTestService.getLabSamTestBeatchById(labSamTestBeatchVo);
		if(!StrUtils.isBlankOrNull(labSamTestBeatchVo.getReferenceOutId())){
			labRefOutMainVo=labRefOutMainService.getLabRefOutMainById(labSamTestBeatchVo.getReferenceOutId(),referenceIds);
		}else{
			labRefOutMainVo.setOutstoreDate(DateUtils.format(new Date(),"yyyy-MM-dd"));
			labRefOutMainVo.setOutstorer(getSessionContainer().getUserName());
			String receiptno = labRefOutMainService.getMaxLabRefOutMainReceiptno();
			labRefOutMainVo.setReceiptno(receiptno);
			List<LabRefOutDetailVo> labRefOutDetailVoList = labRefOutMainService.getLabRefOutDetailListByReferenceId(referenceIds);
			labRefOutMainVo.setLabRefOutDetailVoList(labRefOutDetailVoList);
		}
		return PREUPDATE;
	}
	public String updateSamTest4Reference()throws GlobalException{
		initialiseBeatch();
		if (null == labRefOutMainVo) labRefOutMainVo = new LabRefOutMainVo();
		labSamTestService.updateLabSamTestVo4Reference(labSamTestBeatchVo, labRefOutMainVo);
		return UPDATE;
	}
	public void tabSave() throws GlobalException{
		initialiseBeatch();
		initialiseTest();
		if(listLabSamTestVo==null)listLabSamTestVo=new ArrayList<LabSamTestVo>();
		if(!StrUtils.isBlankOrNull(tabNum)&&tabNum.equals("1")){
			labSamTestService.updateLabSamTest4Standard(labSamTestBeatchVo, listLabSamTestVo);
		}else if(!StrUtils.isBlankOrNull(tabNum)&&tabNum.equals("2")){
			if(listLabApparaUseVo==null)listLabApparaUseVo=new ArrayList<LabApparaUseVo>();
			labApparaUseService.updateLabApparaUseList(listLabApparaUseVo,labSamTestBeatchVo.getId());
			labSamTestService.updateLabSamTestVo4Appara(labSamTestBeatchVo, listLabApparaUseVo);
		}else if(!StrUtils.isBlankOrNull(tabNum)&&tabNum.equals("3")){
			//使用试剂
			initialiseBeatch();
			if (null == labReaOutMainVo) labReaOutMainVo = new LabReaOutMainVo();
			labSamTestService.updateLabSamTest4Reagent(labSamTestBeatchVo,labReaOutMainVo);
		}else if(!StrUtils.isBlankOrNull(tabNum)&&tabNum.equals("4")){
			//使用标准品
			if (null == labRefOutMainVo) labRefOutMainVo = new LabRefOutMainVo();
			labSamTestService.updateLabSamTestVo4Reference(labSamTestBeatchVo, labRefOutMainVo);
		}else if(!StrUtils.isBlankOrNull(tabNum)&&tabNum.equals("5")){
			if(null!=labAmbientInfoVo){
				labAmbientInfoVo.setSamBeatchId(labSamTestBeatchVo.getId());
				labAmbientInfoVo=labAmbientInfoService.addLabAmbientInfo(labAmbientInfoVo);
			}
		}else if(!StrUtils.isBlankOrNull(tabNum)&&tabNum.equals("6")){
			labSamTestService.updateLabSamTest4Date(labSamTestBeatchVo,listLabSamTestVo);
		}
	}
	/**
	 * 数据校验 LIST
	 * @return
	 * @throws GlobalException
	 */
	public String listLabSamTest4Check()throws GlobalException{
		initialiseBeatch();
		pageResult=labSamTestService.getLabSamTest4CheckPR(labSamTestBeatchVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 数据校验 PREUPDATE
	 * @return
	 */
	public String preUpdateLabSamTest4Check()throws GlobalException{
		initialiseBeatch();
		listLabSamTestVo=labSamTestService.getLabSamTestVo4Check(labSamTestBeatchVo);
		return PREUPDATE;
	}
	/**
	 * 数据校验  UPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String updateLabSamTest4Check()throws GlobalException{
		initialiseTest();
		labSamTestService.updateLabSamTestVo4Check(labSamTestVo);
		return UPDATE;
	}
	/**
	 * 数据汇总 LIST
	 * @return
	 * @throws GlobalException 
	 */
	public String listLabSamTest4Summary() throws GlobalException{
		if(labSamTestVo==null){
			labSamTestVo=new LabSamTestVo();
			labSamTestVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSamTestService.getLabSamTest4SummaryPR(labSamTestVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	
	/**
	 * 数据汇总 PREUPDATE
	 * @return
	 * @throws GlobalException
	 */
	public String preLabSamTest4Summary()throws GlobalException{
		initialiseBeatch();
		listLabSamTestVo=labSamTestService.getLabSamTest4Summary(labSamTestVo);
		return PREUPDATE;
	}
	public String updateLabSamTest4Summary()throws GlobalException{
		initialiseTest();if(listLabSamTestVo==null)listLabSamTestVo=new ArrayList<LabSamTestVo>();
		labSamTestService.updateLabSamTest4Summary(labSamTestVo, listLabSamTestVo);
		return UPDATE;
	}
	
	public LabSamTestBeatchVo getLabSamTestBeatchVo() {
		return labSamTestBeatchVo;
	}
	public void setLabSamTestBeatchVo(LabSamTestBeatchVo labSamTestBeatchVo) {
		this.labSamTestBeatchVo = labSamTestBeatchVo;
	}
	public List<LabSamTestVo> getListLabSamTestVo() {
		return listLabSamTestVo;
	}
	public void setListLabSamTestVo(List<LabSamTestVo> listLabSamTestVo) {
		this.listLabSamTestVo = listLabSamTestVo;
	}
	public LabSamTestVo getLabSamTestVo() {
		return labSamTestVo;
	}
	public void setLabSamTestVo(LabSamTestVo labSamTestVo) {
		this.labSamTestVo = labSamTestVo;
	}

	public List<LabApparaUseVo> getListLabApparaUseVo() {
		return listLabApparaUseVo;
	}
	public void setListLabApparaUseVo(List<LabApparaUseVo> listLabApparaUseVo) {
		this.listLabApparaUseVo = listLabApparaUseVo;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public List<LabReaPurDetailVo> getReaPurDetailList() {
		return reaPurDetailList;
	}
	public void setReaPurDetailList(List<LabReaPurDetailVo> reaPurDetailList) {
		this.reaPurDetailList = reaPurDetailList;
	}
	public LabReaPurMainVo getLabReaPurMainVo() {
		return labReaPurMainVo;
	}
	public void setLabReaPurMainVo(LabReaPurMainVo labReaPurMainVo) {
		this.labReaPurMainVo = labReaPurMainVo;
	}
	public LabRefOutMainVo getLabRefOutMainVo() {
		return labRefOutMainVo;
	}
	public void setLabRefOutMainVo(LabRefOutMainVo labRefOutMainVo) {
		this.labRefOutMainVo = labRefOutMainVo;
	}
	public String getReferenceIds() {
		return referenceIds;
	}
	public void setReferenceIds(String referenceIds) {
		this.referenceIds = referenceIds;
	}
	public LabReaOutMainVo getLabReaOutMainVo() {
		return labReaOutMainVo;
	}
	public void setLabReaOutMainVo(LabReaOutMainVo labReaOutMainVo) {
		this.labReaOutMainVo = labReaOutMainVo;
	}
	public String getReagentIds() {
		return reagentIds;
	}
	public void setReagentIds(String reagentIds) {
		this.reagentIds = reagentIds;
	}
	public String getReagentids() {
		return reagentids;
	}
	public void setReagentids(String reagentids) {
		this.reagentids = reagentids;
	}
	public String getReferenceids() {
		return referenceids;
	}
	public void setReferenceids(String referenceids) {
		this.referenceids = referenceids;
	}
	public String getUploadAllFileName() {
		return uploadAllFileName;
	}
	public void setUploadAllFileName(String uploadAllFileName) {
		this.uploadAllFileName = uploadAllFileName;
	}
	public List<LabSamTestVo> getListLabSamTestStandVo() {
		return listLabSamTestStandVo;
	}
	public void setListLabSamTestStandVo(List<LabSamTestVo> listLabSamTestStandVo) {
		this.listLabSamTestStandVo = listLabSamTestStandVo;
	}
	
}
