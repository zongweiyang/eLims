package cn.labsoft.labos.business.sample.action;


import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.labsoft.labos.base.code.service.ILabCodeService;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.business.sample.service.ILabSampRegisterService;
import cn.labsoft.labos.business.sample.vo.LabSampItemsVo;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.service.ILabNumberService;
import cn.labsoft.labos.common.query.service.ILabQueryService;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import cn.labsoft.labos.common.workflow.service.ILabWfProcessInsService;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.charge.vo.LabChargeVo;
import cn.labsoft.labos.source.customer.service.ILabCustomerService;
import cn.labsoft.labos.source.customer.vo.LabCustomerVo;
import cn.labsoft.labos.source.klg.service.ILabItemService;
import cn.labsoft.labos.source.klg.service.ILabStandardItemService;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
/**
 *任务管理操作类
 *@author Quinn
 *@version 8.0
 *@since 8.0
 */
@Controller
@Scope("prototype")
public class LabSampRegisterAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private ILabQueryService labQueryService;
	@Resource
	private ILabSampRegisterService labSampRegisterService;
	@Resource
	private ILabSamTypeService labSamTypeService;
	@Resource
	private ILabCodeService labCodeService;
	@Resource
	private ILabItemService labItemService;
	@Resource
	private ILabStandardItemService labStandardItemService;
	@Resource
	private ILabNumberService labNumberService;
	@Resource
	private ILabCustomerService labCustomerService;
	@Resource
	private ILabWfProcessInsService labWfProcessInsService;
	
	private LabSampRegisterVo labSampRegisterVo;
	private LabItemVo labItemVo;
	private LabCustomerVo labCustomerVo;
	private LabSampItemsVo labSampItemsVo;
	private List<LabSamVo> sampList;
	private LabChargeVo labChargeVo;
	private LabQueryVo labQueryVo;
	public String labSampRegisterQuery() throws GlobalException{
		if(labQueryVo==null)labQueryVo=new LabQueryVo();
		LabQueryVo labQueryVo1=labQueryService.getLabQuery("402881ea46ffbe6e0146ffedab3d0446");
		labQueryVo1.setListQuery(labQueryVo.getListQuery());
		labQueryVo1.setPageVo(labQueryVo.getPageVo());
		labQueryVo=labQueryService.runLabQueryJsp(labQueryVo1);
		getRequest().setAttribute("listQuery", labQueryVo.getListQuery());
 		if(labQueryVo.getQueryType().equals(Constants_Business.TRUE)){
			return "list1";
		}else
			return LIST;
	}
	public LabChargeVo getLabChargeVo() {
		return labChargeVo;
	}
	public void setLabChargeVo(LabChargeVo labChargeVo) {
		this.labChargeVo = labChargeVo;
	}
	public List<LabSamVo> getSampList() {
		return sampList;
	}
	public void setSampList(List<LabSamVo> sampList) {
		this.sampList = sampList;
	}
	public LabSampItemsVo getLabSampItemsVo() {
		return labSampItemsVo;
	}
	public void setLabSampItemsVo(LabSampItemsVo labSampItemsVo) {
		this.labSampItemsVo = labSampItemsVo;
	}
	public LabCustomerVo getLabCustomerVo() {
		return labCustomerVo;
	}
	public void setLabCustomerVo(LabCustomerVo labCustomerVo) {
		this.labCustomerVo = labCustomerVo;
	}
	public LabItemVo getLabItemVo() {
		return labItemVo;
	}
	public void setLabItemVo(LabItemVo labItemVo) {
		this.labItemVo = labItemVo;
	}
	public LabSampRegisterVo getLabSampRegisterVo() {
		return labSampRegisterVo;
	}
	public void setLabSampRegisterVo(LabSampRegisterVo labSampRegisterVo) {
		this.labSampRegisterVo = labSampRegisterVo;
	}
	/**
	 * 任务登记-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabSampRegister() throws GlobalException{
		if(null==labSampRegisterVo){
			labSampRegisterVo=new LabSampRegisterVo();
			labSampRegisterVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSampRegisterService.getLabSampRegisterPR(labSampRegisterVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 登记页面第一个tab
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String preAddLabSampRegister4Tab1() throws GlobalException{
		if(null==labSampRegisterVo){
			labSampRegisterVo=new LabSampRegisterVo();
			labSampRegisterVo.setSampDate(DateUtils.getCurrDateStr());
			labSampRegisterVo.setCreateDate(DateUtils.getCurrDateStr());
			labSampRegisterVo.setReportPost(getText("sample.selfget"));
			labSampRegisterVo.setReportType(getText("sample.check"));
			labSampRegisterVo.setReportMake("2");
			labSampRegisterVo.setReportNum("1");
			labSampRegisterVo.setFinishDate(DateUtils.getNextDate(DateUtils.getCurrDateStr(), 30));
			labSampRegisterVo.setAcceptUser(getSessionContainer().getUserName());
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getId())){
			labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		}
		if(StrUtils.isBlankOrNull(labSampRegisterVo.getSampNo())){
			ThreadNumber threadNumber = new ThreadNumber(labNumberService,null,
					Constants_Business.CODE_LY, new String[] {},Constants_Business.CODE_USE_INIT);
			try {
				labSampRegisterVo.setSampNo(pool.submit(threadNumber).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		//检测类别列表
		List<LabCodeVo> JCLBlist=labCodeService.getLabCodeByTypeCode("SAMP_JCLB");
		setAttribute("JCLBlist", JCLBlist);
		//包装方式列表
		List<LabCodeVo> BZFSlist=labCodeService.getLabCodeByTypeCode("SAMP_BZFS");
		setAttribute("BZFSlist", BZFSlist);
		return PREADD;
	}
	/**
	 * 登记页面第一个tab保存
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String addLabSampRegister4Tab1() throws GlobalException{
		if(null==labSampRegisterVo){
			labSampRegisterVo=new LabSampRegisterVo();
		}
		if(StrUtils.isBlankOrNull(labSampRegisterVo.getId())){
			//生成任务编号
			ThreadNumber threadNumber = new ThreadNumber(labNumberService,null,
					Constants_Business.CODE_RW, new String[] {},Constants_Business.CODE_USE_RUN);
			try {
				labSampRegisterVo.setNo(pool.submit(threadNumber).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
			ThreadNumber threadNumber1 = new ThreadNumber(labNumberService,null,
					Constants_Business.CODE_LY, new String[] {},Constants_Business.CODE_USE_RUN);
			try {
				labSampRegisterVo.setSampNo(pool.submit(threadNumber1).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		labSampRegisterVo=labSampRegisterService.saveLabSampRegister4Tab1(labSampRegisterVo);
		if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab1")){
			return "update1";
		}else if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab2")){
			return "update2";
		}else if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab3")){
			return "update3";
		}else{
			return UPDATE;
		}
	}
	/**
	 * 登记页面第二个tab
	 * @throws GlobalException
	 */
	public String preAddLabSampRegister4Tab2() throws GlobalException{
		if(labSampRegisterVo==null){
			return ERROR;
		}
		labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		sampList=labSampRegisterService.getLabSamList(labSampRegisterVo);
		//样品类型列表
		LabSamTypeVo labSamTypeVo = new LabSamTypeVo();
		labSamTypeVo.setPid("0");
		List<LabSamTypeVo> samTypeList = labSamTypeService.getLabSamTypeList(labSamTypeVo);
		setAttribute("samTypeList", samTypeList);
		return PREADD;
	}
	/**
	 * 登记页面第二个tab保存
	 * @throws GlobalException
	 */
	public String addLabSampRegister4Tab2() throws GlobalException{
		labSampRegisterVo=labSampRegisterService.saveLabSampRegister4Tab2(labSampRegisterVo,sampList);
		if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab1")){
			return "update1";
		}else if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab2")){
			return "update2";
		}else if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab3")){
			return "update3";
		}else{
			return UPDATE;
		}
	}
	/**
	 * 登记页面第三个tab
	 * @throws GlobalException
	 */
	@SuppressWarnings("unchecked")
	public String preAddLabSampRegister4Tab3() throws GlobalException{
		String chargeType="";
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getChargeType())){
			chargeType=labSampRegisterVo.getChargeType();
		}
		labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		if(!StrUtils.isBlankOrNull(chargeType)){
			labSampRegisterVo.setChargeType(chargeType);
		}else if(StrUtils.isBlankOrNull(labSampRegisterVo.getChargeType())){
			labSampRegisterVo.setChargeType("1");
		}
		if(StrUtils.isBlankOrNull(labSampRegisterVo.getIsCharge())){
			labSampRegisterVo.setIsCharge(Constants_Business.Y);
		}
		labChargeVo=labSampRegisterService.getLabCharge(labSampRegisterVo);
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getChargeNo())){
			labChargeVo.setCode(labSampRegisterVo.getChargeNo());
		}else{
			ThreadNumber threadNumber = new ThreadNumber(labNumberService,null,
					Constants_Business.CODE_SF, new String[] {},Constants_Business.CODE_USE_INIT);
			try {
				labChargeVo.setCode(pool.submit(threadNumber).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
		}
		return PREADD;
	}
	/**
	 * 登记页面第三个tab保存
	 * @throws GlobalException
	 */
	public String addLabSampRegister4Tab3() throws GlobalException{
		labSampRegisterVo=labSampRegisterService.saveLabSampRegister4Tab3(labSampRegisterVo,labChargeVo);
		if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab1")){
			return "update1";
		}else if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab2")){
			return "update2";
		}else if(!StrUtils.isNull(labSampRegisterVo.getAuditResult())&&labSampRegisterVo.getAuditResult().equals("tab3")){
			return "update3";
		}else{
			return UPDATE;
		}
	}
	/**
	 * 查看登记页面
	 * @throws GlobalException
	 */
	public String showLabSampRegister()  throws GlobalException{
		if(null==labSampRegisterVo){
			return ERROR;
		}
		labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		sampList=labSampRegisterService.getLabSamList(labSampRegisterVo);
		labChargeVo=labSampRegisterService.getLabCharge(labSampRegisterVo);
		return SHOW;
	}
	/**
	 * 删除登记的任务
	 * @throws GlobalException
	 */
	public String deleteLabSampRegister() throws GlobalException{
		if(null==labSampRegisterVo)labSampRegisterVo=new LabSampRegisterVo();
		//labSampRegisterService.deleteLabSampRegister(labSampRegisterVo.getIds());
		labSampRegisterService.updateLabSampRegister4Del(labSampRegisterVo.getIds());
		return DELETE;
	}
	/**
	 * 假删除登记的任务
	 * @throws GlobalException
	 */
	public String updateLabSampRegister4Del()throws GlobalException{
		if(null==labSampRegisterVo)labSampRegisterVo=new LabSampRegisterVo();
		labSampRegisterService.updateLabSampRegister4Del(labSampRegisterVo.getIds());
		return DELETE;
	}
	
	/**
	 * 登记审核-列表页面方法
	 * @throws GlobalException
	 */
	public String listLabSampRegister4Audit() throws GlobalException{
		if(labSampRegisterVo==null){
			labSampRegisterVo=new LabSampRegisterVo();
			labSampRegisterVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSampRegisterService.getLabSampRegisterPR4Audit(labSampRegisterVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 查看 登记审核
	 * @throws GlobalException
	 */
	public String showLabSampRegister4Audit()  throws GlobalException{
		if(null==labSampRegisterVo){
			return ERROR;
		}
		labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		sampList=labSampRegisterService.getLabSamList(labSampRegisterVo);
		labChargeVo=labSampRegisterService.getLabCharge(labSampRegisterVo);
		return SHOW;
	}
	/**
	 * 登记审核-提交方法
	 * @throws GlobalException
	 */
	public String updateLabSampRegister4Audit()throws GlobalException{
		labSampRegisterService.updateLabSampRegister4Audit(labSampRegisterVo);
		return UPDATE;
	}
	/**
	 * 登记复核-列表页面
	 * @throws GlobalException
	 */
	public String listLabSampRegister4Check() throws GlobalException{
		if(labSampRegisterVo==null){
			labSampRegisterVo=new LabSampRegisterVo();
			labSampRegisterVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSampRegisterService.getLabSampRegisterPR4Audit(labSampRegisterVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 查看 登记复核
	 * @throws GlobalException
	 */
	public String showLabSampRegister4Check()  throws GlobalException{
		if(null==labSampRegisterVo){
			return ERROR;
		}
		labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		sampList=labSampRegisterService.getLabSamList(labSampRegisterVo);
		labChargeVo=labSampRegisterService.getLabCharge(labSampRegisterVo);
		return SHOW;
	}
	/**
	 * 登记复核 提交
	 * @throws GlobalException
	 */
	public String updateLabSampRegister4Check()throws GlobalException{
		labSampRegisterService.updateLabSampRegister4Check(labSampRegisterVo);
		return UPDATE;
	}
	/**
	 * 登记审批
	 * @throws GlobalException
	 */
	public String listLabSampRegister4Approve() throws GlobalException{
		if(labSampRegisterVo==null){
			labSampRegisterVo=new LabSampRegisterVo();
			labSampRegisterVo.setStatus(getSessionContainer().getFunId());
		}
		pageResult=labSampRegisterService.getLabSampRegisterPR4Audit(labSampRegisterVo, pageResult);
		// 获取流程状态集合
		List<LabWfFunStepVo> funStepList = labWfProcessInsService
				.getLabWfFunStepList(getSessionContainer().getFunId());
		setAttribute("funStepList", funStepList);
		return LIST;
	}
	/**
	 * 查看 登记审批
	 * @throws GlobalException
	 */
	public String showLabSampRegister4Approve()  throws GlobalException{
		if(null==labSampRegisterVo){
			return ERROR;
		}
		labSampRegisterVo=labSampRegisterService.getLabSampRegister(labSampRegisterVo.getId());
		sampList=labSampRegisterService.getLabSamList(labSampRegisterVo);
		labChargeVo=labSampRegisterService.getLabCharge(labSampRegisterVo);
		return SHOW;
	}
	/**
	 * 登记审批 提交
	 * @throws GlobalException
	 */
	public String updateLabSampRegister4Approve()throws GlobalException{
		labSampRegisterService.updateLabSampRegister4Approve(labSampRegisterVo);
		return UPDATE;
	}
	
	/**
	 * 项目信息弹出层
	 * @throws GlobalException
	 */
	public String showLabItem4Select() throws GlobalException{
		if(null==labItemVo){
			labItemVo=new LabItemVo();
		}
		List<LabItemVo> labItemList = labItemService.getLabItemList(labItemVo);
		setAttribute("labItemList", labItemList);
		return SHOW;
	}
	/**
	 * 获取检测方法
	 * @throws GlobalException
	 */
	public String ajaxLabMethodList4Select() throws GlobalException{
		List<LabMethodVo> labMethodList = labStandardItemService.getLabMethodList(labItemVo.getStandId(),labItemVo.getId());
		ajax(labMethodList);
		return NONE;
	}
	/**
	 * 客户信息弹出层
	 * @throws GlobalException
	 */
	public String showLabCustomer4Select() throws GlobalException{
		if(null==labCustomerVo){
			labCustomerVo=new LabCustomerVo();
		}
		List<LabCustomerVo> customerList = labCustomerService.getLabCustomerList(labCustomerVo);
		setAttribute("customerList", customerList);
		return SHOW;
	}
	/**
	 * 验证项目是否存在
	 * @throws GlobalException
	 * @throws IOException
	 */
	public String ajaxLabSampItem4Exsit() throws GlobalException, IOException {
		if(StrUtils.isNull(labSampRegisterVo)){
			return NONE;
		}
		boolean flag = labSampRegisterService.ajaxLabSampItem4Exsit(labSampRegisterVo.getId());
		outPutString(flag + "");
		return NONE;
	}
	public LabQueryVo getLabQueryVo() {
		return labQueryVo;
	}
	public void setLabQueryVo(LabQueryVo labQueryVo) {
		this.labQueryVo = labQueryVo;
	}
	public ILabQueryService getLabQueryService() {
		return labQueryService;
	}
	/**
	 * 高级查询labQueryService注入
	 * @param labQueryService
	 */
	@Resource
	public void setLabQueryService(ILabQueryService labQueryService) {
		this.labQueryService = labQueryService;
	}
}
