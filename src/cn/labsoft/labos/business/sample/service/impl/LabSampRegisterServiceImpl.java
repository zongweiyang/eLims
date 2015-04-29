package cn.labsoft.labos.business.sample.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.charge.dao.ILabChargeDAO;
import cn.labsoft.labos.source.charge.dao.ILabChargeDetailDAO;
import cn.labsoft.labos.source.charge.entity.LabCharge;
import cn.labsoft.labos.source.charge.entity.LabChargeDetail;
import cn.labsoft.labos.source.charge.vo.LabChargeDetailVo;
import cn.labsoft.labos.source.charge.vo.LabChargeVo;
import cn.labsoft.labos.source.customer.dao.ILabCustomerDAO;
import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.source.klg.entity.LabStandard;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.base.code.dao.ILabCodeDAO;
import cn.labsoft.labos.business.sam.dao.ILabSamDAO;
import cn.labsoft.labos.business.sam.dao.ILabSamMainDAO;
import cn.labsoft.labos.business.sam.dao.ILabSamTypeDAO;
import cn.labsoft.labos.business.sam.entity.LabSam;
import cn.labsoft.labos.business.sam.entity.LabSamMain;
import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.business.sam.vo.LabSamVo;
import cn.labsoft.labos.business.sample.dao.ILabSampCustomerDAO;
import cn.labsoft.labos.business.sample.dao.ILabSampItemsDAO;
import cn.labsoft.labos.business.sample.dao.ILabSampRegisterDAO;
import cn.labsoft.labos.business.sample.entity.LabSampCustomer;
import cn.labsoft.labos.business.sample.entity.LabSampItems;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.business.sample.service.ILabSampRegisterService;
import cn.labsoft.labos.business.sample.vo.LabSampCustomerVo;
import cn.labsoft.labos.business.sample.vo.LabSampRegisterVo;
import cn.labsoft.labos.business.samtest.dao.ILabSamTestDAO;
import cn.labsoft.labos.business.samtest.entity.LabSamTest;

@Service("labSampRegisterService")
public class LabSampRegisterServiceImpl extends BaseService implements ILabSampRegisterService {
	public   ExecutorService pool = Executors. newSingleThreadExecutor();
	
	private ILabSampRegisterDAO labSampRegisterDAO;
	private ILabSampItemsDAO labSampItemsDAO;
	private ILabItemDAO labItemDAO;
	private ILabStandardDAO labStandardDAO;
	private ILabMethodDAO labMethodDAO;
	private ILabSamTypeDAO labSamTypeDAO;
	private ILabSamDAO labSamDAO;
	private ILabSamMainDAO labSamMainDAO;
	private ILabSampCustomerDAO labSampCustomerDAO;
	private ILabCustomerDAO labCustomerDAO;
	private ILabCodeDAO labCodeDAO;
	private ILabChargeDAO labChargeDAO;
	private ILabChargeDetailDAO labChargeDetailDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabSamTestDAO labSamTestDAO;
	private ILabNumberDAO labNumberDAO;
	
	@Resource
	public void setLabStandardDAO(ILabStandardDAO labStandardDAO) {
		this.labStandardDAO = labStandardDAO;
	}
	@Resource
	public void setLabMethodDAO(ILabMethodDAO labMethodDAO) {
		this.labMethodDAO = labMethodDAO;
	}
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}
	@Resource
	public void setLabSampRegisterDAO(ILabSampRegisterDAO labSampRegisterDAO) {
		this.labSampRegisterDAO = labSampRegisterDAO;
	}
	@Resource
	public void setLabSampItemsDAO(ILabSampItemsDAO labSampItemsDAO) {
		this.labSampItemsDAO = labSampItemsDAO;
	}
	@Resource
	public void setLabItemDAO(ILabItemDAO labItemDAO) {
		this.labItemDAO = labItemDAO;
	}
	@Resource
	public void setLabSamTypeDAO(ILabSamTypeDAO labSamTypeDAO) {
		this.labSamTypeDAO = labSamTypeDAO;
	}
	@Resource
	public void setLabSamDAO(ILabSamDAO labSamDAO) {
		this.labSamDAO = labSamDAO;
	}
	@Resource
	public void setLabSamMainDAO(ILabSamMainDAO labSamMainDAO) {
		this.labSamMainDAO = labSamMainDAO;
	}
	@Resource
	public void setLabSampCustomerDAO(ILabSampCustomerDAO labSampCustomerDAO) {
		this.labSampCustomerDAO = labSampCustomerDAO;
	}
	@Resource
	public void setLabCustomerDAO(ILabCustomerDAO labCustomerDAO) {
		this.labCustomerDAO = labCustomerDAO;
	}
	@Resource
	public void setLabCodeDAO(ILabCodeDAO labCodeDAO) {
		this.labCodeDAO = labCodeDAO;
	}
	@Resource
	public void setLabChargeDAO(ILabChargeDAO labChargeDAO) {
		this.labChargeDAO = labChargeDAO;
	}
	@Resource
	public void setLabChargeDetailDAO(ILabChargeDetailDAO labChargeDetailDAO) {
		this.labChargeDetailDAO = labChargeDetailDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabSamTestDAO(ILabSamTestDAO labSamTestDAO) {
		this.labSamTestDAO = labSamTestDAO;
	}
	@SuppressWarnings("unchecked")
	public LabSampRegisterVo saveLabSampRegister4Tab1(LabSampRegisterVo labSampRegisterVo) throws GlobalException {
		//新增方法
		LabSampRegister labSampRegister=new LabSampRegister();
		if(StrUtils.isBlankOrNull(labSampRegisterVo.getId())){
			labSampRegister=this.vo2Po(labSampRegisterVo, labSampRegister);
			try {
				LabSampCustomerVo customerVo=labSampRegisterVo.getLabSampCustomerVo();
				LabSampCustomer customer=new LabSampCustomer();
				BeanUtils.copyProperties(labSampRegisterVo.getLabSampCustomerVo(), customer);
				if(!StrUtils.isBlankOrNull(customerVo.getLabCustomerId())){
					LabCustomer cus=labCustomerDAO.getLabCustomer(customerVo.getLabCustomerId());
					customer.setLabCustomer(cus);
				}else if(StrUtils.isBlankOrNull(labSampRegisterVo.getOtherType())
						&&!StrUtils.isBlankOrNull(customerVo.getLabCustomerName())){
					String hql="FROM LabCustomer WHERE isDel='"+Constants_Business.N+"' AND name like '"+customerVo.getLabCustomerName().trim()+"'";
					LabCustomer cus=(LabCustomer)labCustomerDAO.find(hql,0);
					if(cus==null){
						cus=new LabCustomer();
						cus.setName(customer.getLabCustomerName());
						cus.setAddress(customer.getAddress());
						cus.setPhone(customer.getTelephone());
						cus.setEmail(customer.getEmail());
						cus.setFox(customer.getFax());
						cus.setZipCode(customer.getZipCode());
						cus.setFirPerson(customer.getUser());
						ThreadNumber threadNumber1 = new ThreadNumber(null,labNumberDAO,
								Constants_Business.CODE_KH, new String[] {},Constants_Business.CODE_USE_RUN);
						try {
							cus.setNum(pool.submit(threadNumber1).get().toString());
						} catch (Exception e) {
							Log4J.error(e.getMessage());
						}
						labCustomerDAO.addLabCustomer(cus);
					}else{
						customer.setLabCustomer(cus);
					}
				}
				labSampCustomerDAO.addLabSampCustomer(customer);
				labSampRegister.setLabSampCustomer(customer);
				labSampRegisterDAO.addLabSampRegister(labSampRegister);
			} catch (BeansException e) {
				Log4J.error("SampRegisterServiceImpl addSampRegister  error..."+e.getMessage(), e);
				
			}
			labSampRegisterVo.setId(labSampRegister.getId());
		}else{//修改方法
			labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
			if(StrUtils.isBlankOrNull(labSampRegister.getSampNo())){
				ThreadNumber threadNumber1 = new ThreadNumber(null,labNumberDAO,
						Constants_Business.CODE_LY, new String[] {},Constants_Business.CODE_USE_RUN);
				try {
					labSampRegister.setSampNo(pool.submit(threadNumber1).get().toString());
				} catch (Exception e) {
					Log4J.error(e.getMessage());
				}
			}
			labSampRegister.setSampNum(labSampRegisterVo.getSampNum());
			labSampRegister.setSampDate(labSampRegisterVo.getSampDate());
			labSampRegister.setSampDesc(labSampRegisterVo.getSampDesc());
			labSampRegister.setSampSource(labSampRegisterVo.getSampSource());
			labSampRegister.setSampPack(labSampRegisterVo.getSampPack());
			labSampRegister.setSampUser(labSampRegisterVo.getSampUser());
			labSampRegister.setSaveOrg(labSampRegisterVo.getSaveOrg());
			labSampRegister.setSaveUser(labSampRegisterVo.getSaveUser());
			
			labSampRegister.setAcceptUser(labSampRegisterVo.getAcceptUser());
			labSampRegister.setCreateDate(labSampRegisterVo.getCreateDate());
			labSampRegister.setFinishDate(labSampRegisterVo.getFinishDate());
			labSampRegister.setReportNum(labSampRegisterVo.getReportNum());
			labSampRegister.setReportPost(labSampRegisterVo.getReportPost());
			labSampRegister.setReportType(labSampRegisterVo.getReportType());
			labSampRegister.setReportMake(labSampRegisterVo.getReportMake());
			labSampRegister.setTaskType(labSampRegisterVo.getTaskType());
			labSampRegisterDAO.updateLabSampRegister(labSampRegister);
			//修改客户信息
			LabSampCustomerVo customerVo=labSampRegisterVo.getLabSampCustomerVo();
			LabSampCustomer customer=labSampCustomerDAO.getLabSampCustomer(customerVo.getId());
			BeanUtils.copyProperties(labSampRegisterVo.getLabSampCustomerVo(), customer,new String[]{"id","isDel","createTime","createUserId","tenantId"});
			if(customerVo.getLabCustomerId()==null
					||customerVo.getLabCustomerId()!=null&&null!=customer.getLabCustomer()
					&&!customerVo.getLabCustomerId().equals(customer.getLabCustomer().getId())){
				if(!StrUtils.isBlankOrNull(customerVo.getLabCustomerId())){
					LabCustomer cus=labCustomerDAO.getLabCustomer(customerVo.getLabCustomerId());
					customer.setLabCustomer(cus);
				}else if(StrUtils.isBlankOrNull(labSampRegisterVo.getOtherType())
						&&!StrUtils.isBlankOrNull(customerVo.getLabCustomerName())){
					String hql="FROM LabCustomer WHERE isDel='"+Constants_Business.N+"' AND name like '"+customerVo.getLabCustomerName().trim()+"'";
					LabCustomer cus=(LabCustomer)labCustomerDAO.find(hql,0);
					if(cus==null){
						cus=new LabCustomer();
						cus.setName(customer.getLabCustomerName());
						cus.setAddress(customer.getAddress());
						cus.setPhone(customer.getTelephone());
						cus.setEmail(customer.getEmail());
						cus.setFox(customer.getFax());
						cus.setZipCode(customer.getZipCode());
						cus.setFirPerson(customer.getUser());
						ThreadNumber threadNumber1 = new ThreadNumber(null,labNumberDAO,
								Constants_Business.CODE_KH, new String[] {},Constants_Business.CODE_USE_RUN);
						try {
							cus.setNum(pool.submit(threadNumber1).get().toString());
						} catch (Exception e) {
							Log4J.error(e.getMessage());
						}
						labCustomerDAO.addLabCustomer(cus);
					}else{
						customer.setLabCustomer(cus);
					}
				}
			}
			labSampCustomerDAO.updateLabSampCustomer(customer);
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegisterVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, getSessionContainer().getFunId(), "任务登记提交",
				labSampRegisterVo.getAuditResult());
		if (ins != null) {
			labSampRegister.setProcessIns(ins);
			labSampRegisterDAO.updateLabSampRegister(labSampRegister);
			if(ins.getResult()!=null&&ins.getResult().equals("1")){
				//初始化检测流程业务基础数据
				this.initLabSamTestDate(labSampRegister);
			}
		}else {
			throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
		}
		return labSampRegisterVo;
	}
	@SuppressWarnings("unchecked")
	public LabSampRegisterVo saveLabSampRegister4Tab2(LabSampRegisterVo labSampRegisterVo,List<LabSamVo> sampList) throws GlobalException {
		LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
		//检测样品信息
		if(sampList!=null){
			String hql="FROM LabSamMain WHERE sampNo='"+labSampRegister.getSampNo()+"'";
			LabSamMain samMain=(LabSamMain)labSamMainDAO.find(hql, 0);
			if(samMain==null){
				samMain=new LabSamMain();
				samMain.setSampNo(labSampRegister.getSampNo());
				samMain.setSampDate(labSampRegister.getSampDate());
				samMain.setSampUser(labSampRegister.getSampUser());
				samMain.setSampDesc(labSampRegister.getSampDesc());
				samMain.setSampPack(labSampRegister.getSampPack());
				samMain.setSaveOrg(labSampRegister.getSaveOrg());
				samMain.setSaveUser(labSampRegister.getSaveUser());
				samMain.setBusId(labSampRegister.getId());
				samMain.setNum(labSampRegister.getSampNum());
				samMain.setCustomer(labSampRegister.getLabSampCustomer().getLabCustomerName());
				samMain.setContacts(labSampRegister.getLabSampCustomer().getUser());
				samMain.setContactPhone(labSampRegister.getLabSampCustomer().getTelephone());
				samMain.setRegistDate(DateUtils.getCurrDateTimeStr());
				labSamMainDAO.addLabSamMain(samMain);
			}else{
				samMain.setSampNo(labSampRegister.getSampNo());
				samMain.setSampDate(labSampRegister.getSampDate());
				samMain.setSampUser(labSampRegister.getSampUser());
				samMain.setSampDesc(labSampRegister.getSampDesc());
				samMain.setSampPack(labSampRegister.getSampPack());
				samMain.setSaveOrg(labSampRegister.getSaveOrg());
				samMain.setSaveUser(labSampRegister.getSaveUser());
				samMain.setBusId(labSampRegister.getId());
				samMain.setNum(labSampRegister.getSampNum());
				samMain.setCustomer(labSampRegister.getLabSampCustomer().getLabCustomerName());
				samMain.setContacts(labSampRegister.getLabSampCustomer().getUser());
				samMain.setContactPhone(labSampRegister.getLabSampCustomer().getTelephone());
				labSamMainDAO.updateLabSamMain(samMain);
			}
			//删除已经保存的项目
			try {
				labSampItemsDAO.executeSQL("DELETE FROM lab_Samp_items WHERE bus_id='"+labSampRegister.getId()+"'");
				labSamDAO.executeSQL("DELETE FROM lab_sam WHERE samp_no='"+labSampRegister.getSampNo()+"'");
			} catch (Exception e) {
				Log4J.error("SampRegisterServiceImpl..."+e.getMessage());
			}
			for (int i=0;i<sampList.size();i++) {
				LabSamVo labSamVo=sampList.get(i);
				LabSam sam=new LabSam();
				sam.setMain(samMain);
				sam.setSampNo(labSampRegister.getSampNo());
				sam.setItemId(labSamVo.getItemId());
				sam.setItemName(labSamVo.getItemName());
				sam.setName(labSamVo.getName());
				sam.setSampCode(labSamVo.getSampCode());
				sam.setOldNo(labSamVo.getOldNo());
				sam.setSeqNum(String.valueOf(i));
				LabSamType sampType =labSamTypeDAO.getLabSamType(labSamVo.getSamTypeId());
				sam.setLabSamType(sampType);
				labSamDAO.addLabSam(sam);
				String itemids[]=labSamVo.getItemId().split(",");
				if(itemids!=null&&itemids.length>0){
					for (String itemid : itemids) {
						if(itemid!=null&&itemid.trim().length()>0){
							LabSampItems sampItem=new LabSampItems();
							sampItem.setBusId(labSampRegister.getId());
							sampItem.setLabSamId(sam.getId());
							sampItem.setLabSamName(sam.getName());
							sampItem.setSamCode(sam.getSampCode());
							sampItem.setLabSamTypeId(sampType.getId());
							sampItem.setLabSamTypeName(sampType.getName());
							LabItem item=null;
							if(itemid.contains("*")){
								String idStr[]=itemid.split("\\*");
								if(idStr.length==1){
									item=labItemDAO.getLabItem(idStr[0]);
									sampItem.setPrice(item.getPrice());
									sampItem.setItemId(item.getId());
									sampItem.setItemName(item.getName());
								}else if(idStr.length==2){
									item=labItemDAO.getLabItem(idStr[0]);
									sampItem.setPrice(item.getPrice());
									sampItem.setItemId(item.getId());
									sampItem.setItemName(item.getName());
									LabStandard stand=labStandardDAO.getLabStandard(idStr[1]);
									if(stand!=null){
										sampItem.setStandardId(stand.getId());
										sampItem.setStandardName(stand.getName());
									}
								}else if(idStr.length==3){
									item=labItemDAO.getLabItem(idStr[0]);
									sampItem.setPrice(item.getPrice());
									sampItem.setItemId(item.getId());
									sampItem.setItemName(item.getName());
									LabStandard stand=labStandardDAO.getLabStandard(idStr[1]);
									if(stand!=null){
										sampItem.setStandardId(stand.getId());
										sampItem.setStandardName(stand.getName());
									}
									LabMethod method =labMethodDAO.getLabMethod(idStr[2]);
									if(method!=null){
										sampItem.setMethodId(method.getId());
										sampItem.setMethodName(method.getName());
									}
								}
							}else{
								item=labItemDAO.getLabItem(itemid);
								sampItem.setPrice(item.getPrice());
								sampItem.setItemId(item.getId());
								sampItem.setItemName(item.getName());
							}
							labSampItemsDAO.addLabSampItems(sampItem);
						}
					}
				}
			}
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegisterVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, getSessionContainer().getFunId(), "任务登记提交",
				labSampRegisterVo.getAuditResult());
		if (null == ins) {
			throw new GlobalException("SampRegisterServiceImpl...流程实例化出错！");
		}else if(ins.getResult()!=null&&ins.getResult().equals("1")){
			//初始化检测流程业务基础数据
			this.initLabSamTestDate(labSampRegister);
		}
		return labSampRegisterVo;
	}
	@SuppressWarnings("unchecked")
	public LabSampRegisterVo saveLabSampRegister4Tab3(LabSampRegisterVo labSampRegisterVo,LabChargeVo labChargeVo) throws GlobalException {
		
		LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
		if(null!=labSampRegisterVo.getIsCharge()&&labSampRegisterVo.getIsCharge().equals(Constants_Business.Y)){
			String hql=" FROM LabCharge WHERE isDel='"+Constants_Business.N+"' AND code like '"+labChargeVo.getCode()+"'";
			List<LabCharge> chargeList=labChargeDAO.find(hql);
			LabCharge charge=null;
			if(chargeList!=null&&chargeList.size()>0){
				charge=chargeList.get(0);
				charge.setTotalMoney(labChargeVo.getTotalMoney());
				charge.setDiscount(labChargeVo.getDiscount());
				charge.setPreferential(labChargeVo.getPreferential());
				charge.setPayMoney(labChargeVo.getPayMoney());
				
				charge.setPaymentUnit(labSampRegister.getLabSampCustomer().getLabCustomerName());
				charge.setPaymentUnitUrl(labSampRegister.getLabSampCustomer().getAddress());
				
				charge.setPayName(labSampRegister.getNo());
				charge.setCollectionUnit(getSessionContainer().getOrgUnit());
				labChargeDAO.updateLabCharge(charge);
			}else{
				charge=new LabCharge();
				ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
						Constants_Business.CODE_SF, new String[] {},Constants_Business.CODE_USE_RUN);
				try {
					charge.setCode(pool.submit(threadNumber).get().toString());
				} catch (Exception e) {
					Log4J.error(e.getMessage());
				}
				charge.setBusId(labSampRegister.getId());
				charge.setBusType(Constants_Business.WF_CODE_BUS_RW);
				charge.setTotalMoney(labChargeVo.getTotalMoney());
				charge.setDiscount(labChargeVo.getDiscount());
				charge.setPreferential(labChargeVo.getPreferential());
				charge.setPayMoney(labChargeVo.getPayMoney());
				charge.setPaymentUnit(labSampRegister.getLabSampCustomer().getLabCustomerName());
				charge.setPaymentUnitUrl(labSampRegister.getLabSampCustomer().getAddress());
				charge.setPayName(labSampRegister.getNo());
				charge.setCollectionUnit(getSessionContainer().getOrgUnit());
				charge.setTax(0.0);
				charge.setTaxMoney(0.0);
				labChargeDAO.addLabCharge(charge);
			}
			try {
				labChargeDetailDAO.executeSQL("delete from lab_charge_detail where code='"+labChargeVo.getCode()+"'");
			} catch (Exception e1) {
				Log4J.error("labSampRegisterService..."+e1.getMessage());
			}
			List<LabChargeDetailVo> itemCharList=labChargeVo.getChargeList();
			if(itemCharList!=null){
				for (LabChargeDetailVo cdVo : itemCharList) {
					if(cdVo==null)continue;
					LabChargeDetail cd=new LabChargeDetail();
					cd.setBusId(labSampRegister.getId());
					cd.setCode(charge.getCode().trim());
					cd.setPayInfo("item");
					cd.setPayName(cdVo.getPayName().trim());
					cd.setPrice(cdVo.getPrice());
					cd.setNum(cdVo.getNum());
					cd.setPayMoney(cdVo.getPayMoney());
					labChargeDetailDAO.addLabChargeDetail(cd);
				}
			}
			List<LabChargeDetailVo> qtList=labChargeVo.getQtList();
			if(qtList!=null){
				for (LabChargeDetailVo cdVo : qtList) {
					if(cdVo==null)continue;
					LabChargeDetail cd=new LabChargeDetail();
					cd.setBusId(labSampRegister.getId().trim());
					cd.setCode(charge.getCode().trim());
					cd.setPayInfo("other");
					cd.setPayName(cdVo.getPayName().trim());
					cd.setPrice(cdVo.getPrice());
					cd.setPayMoney(cdVo.getPrice());
					cd.setNum(1);
					cd.setPayMoney(cdVo.getPrice());
					labChargeDetailDAO.addLabChargeDetail(cd);
				}
			}
			try{
				labSampRegister.setCharge(charge.getPayMoney());
				labSampRegister.setChargeType(labSampRegisterVo.getChargeType());
				labSampRegister.setChargeNo(charge.getCode());
				labSampRegister.setIsCharge(Constants_Business.Y);
				labSampRegisterDAO.updateLabSampRegister(labSampRegister);
			}catch(Exception e){
				Log4J.error("SampRegisterServiceImpl updateSampRegister  error..."+e.getMessage(), e);
			}
		}else{
			String hql=" FROM LabCharge WHERE code like '"+labChargeVo.getCode()+"'";
			List<LabCharge> chargeList=labChargeDAO.find(hql);
			if(chargeList!=null&&chargeList.size()>0){
				LabCharge charge=chargeList.get(0);
				charge.setIsDel(Constants_Business.Y);
				labChargeDAO.updateLabCharge(charge);
				try {
					labChargeDetailDAO.executeSQL("update lab_charge_detail set is_del='"+Constants_Business.Y+"' where code='"+labChargeVo.getCode()+"'");
				} catch (Exception e) {
					Log4J.error("labSampRegisterService..."+e.getMessage());
				}
			}
			labSampRegister.setCharge(labChargeVo.getPayMoney());
			labSampRegister.setIsCharge(Constants_Business.N);
			labSampRegisterDAO.updateLabSampRegister(labSampRegister);
		}
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegisterVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, getSessionContainer().getFunId(), "任务登记提交",
				labSampRegisterVo.getAuditResult());
		if (null == ins) {
			throw new GlobalException("SampRegisterServiceImpl...流程实例化出错！");
		}else if(ins.getResult()!=null&&ins.getResult().equals("1")){
			//初始化检测流程业务基础数据
			this.initLabSamTestDate(labSampRegister);
		}
		return labSampRegisterVo;
	}
	@SuppressWarnings("unchecked")
	public void initLabSamTestDate(LabSampRegister labSampRegister) throws GlobalException{
		String hqls="FROM LabSampItems WHERE isDel='"+Constants_Business.N+"' AND busId='"+labSampRegister.getId()+"'";
		List<LabSampItems> listLabSampItems=labSampItemsDAO.find(hqls);
		if(listLabSampItems!=null&&listLabSampItems.size()>0){
			for(LabSampItems labSampItems:listLabSampItems){
				if(labSampItems!=null){
					LabSamTest labSamTest=new LabSamTest();
					labSamTest.setSampCode(labSampItems.getSamCode());
					labSamTest.setLabSamId(labSampItems.getLabSamId());
					labSamTest.setLabSamName(labSampItems.getLabSamName());
					labSamTest.setLabSamTypeId(labSampItems.getLabSamTypeId());
					labSamTest.setLabSamTypeName(labSampItems.getLabSamTypeName());
					labSamTest.setItemId(labSampItems.getItemId());
					labSamTest.setItemName(labSampItems.getItemName());
					labSamTest.setStandardId(labSampItems.getStandardId());
					labSamTest.setStandardName(labSampItems.getStandardName());
					labSamTest.setMethodId(labSampItems.getMethodId());
					labSamTest.setMethodName(labSampItems.getMethodName());
					labSamTest.setTaskId(labSampRegister.getId());
					labSamTest.setTaskNo(labSampRegister.getNo());
					labSamTest.setTaskType(labSampRegister.getTaskType());
					labSamTest.setFinishDate(labSampRegister.getFinishDate());
					labSamTest.setIsSued(Constants_Business.N);
					labSamTest.setIsTask(Constants_Business.N);
					labSamTest.setIsTest(Constants_Business.N);
					labSamTest.setIsCheck(Constants_Business.N);
					labSamTest.setIsBack(Constants_Business.N);
					labSamTestDAO.addLabSamTest(labSamTest);
				}
			}
		}
	}
	@Override
	public boolean deleteLabSampRegister(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labSampRegisterDAO.deleteLabSampRegister(ids);
				for (String id : ids) {
					labWfProcessInsDAO.delLabWfProcessInsByBusId(id);
				}
			}
		}catch(Exception e){
			key=false;
			Log4J.error("SampRegisterServiceImpl deleteSampRegister  error..."+e.getMessage(), e);
		}
		return key;
	}

	@Override
	public LabSampRegisterVo getLabSampRegister(String id) throws GlobalException {
		LabSampRegisterVo labSampRegisterVo=new LabSampRegisterVo();
		if(!StrUtils.isBlankOrNull(id)){
			LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(id);
			labSampRegisterVo=this.po2Vo(labSampRegister, labSampRegisterVo);
			LabSampCustomerVo customerVo=new LabSampCustomerVo();
			BeanUtils.copyProperties(labSampRegister.getLabSampCustomer(), customerVo);
			labSampRegisterVo.setLabSampCustomerVo(customerVo);
		}
		return labSampRegisterVo;
	}

	@Override
	public List<LabSampRegisterVo> getLabSampRegisterList(LabSampRegisterVo labSampRegisterVo) throws GlobalException {
		String wereHql="";
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getNo())){
			wereHql+=" AND no like '%"+labSampRegisterVo.getNo().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getStartDate())){
			wereHql+=" AND createTime>='"+labSampRegisterVo.getStartDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getEndDate())){
			wereHql+=" AND createTime<='"+labSampRegisterVo.getEndDate()+"'";
		}
		if((labSampRegisterVo.getLabSampCustomerVo()!=null)&&!StrUtils.isBlankOrNull(labSampRegisterVo.getLabSampCustomerVo().getLabCustomerName())){
			wereHql+=" AND labSampCustomer.labCustomerName like '%"+labSampRegisterVo.getLabSampCustomerVo().getLabCustomerName().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getSampUser())){
			wereHql+=" AND sampUser like '%"+labSampRegisterVo.getSampUser().trim()+"%'";
		}
		return this.getSampRegisterVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSampRegisterPR(LabSampRegisterVo labSampRegisterVo, PageResult pageResult)
			throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		
		String hql="FROM LabSampRegister WHERE isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getNo())){
			hql+=" AND no like '%"+labSampRegisterVo.getNo().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getStartDate())){
			hql+=" AND createTime>='"+labSampRegisterVo.getStartDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getEndDate())){
			hql+=" AND createTime<='"+labSampRegisterVo.getEndDate()+"'";
		}
		if((labSampRegisterVo.getLabSampCustomerVo()!=null)&&!StrUtils.isBlankOrNull(labSampRegisterVo.getLabSampCustomerVo().getLabCustomerName())){
			hql+=" AND labSampCustomer.labCustomerName like '%"+labSampRegisterVo.getLabSampCustomerVo().getLabCustomerName().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getSampUser())){
			hql+=" AND sampUser like '%"+labSampRegisterVo.getSampUser().trim()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSampRegisterVo.getStatus())
				&& son.getFunId().equals(labSampRegisterVo.getStatus())) {//当前功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status<>'"+LabWfConstant.BUS_PROCESS_END+"' ";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSampRegisterVo.getStatus())
				&&labSampRegisterVo.getStatus().equals(LabWfConstant.BUS_PROCESS_END)){//已完结
			String subhql="SELECT busId FROM LabWfProcessIns WHERE isDel='"+Constants_Business.N+"' AND type='"+Constants_Business.WF_CODE_BUS_RW+"' AND status='"+LabWfConstant.BUS_PROCESS_END+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSampRegisterVo.getStatus())
				&&!son.getFunId().equals(labSampRegisterVo.getStatus())){//其他功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status='"+LabWfConstant.BUS_PROCESS_END+"'";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND process.status like'%"+labSampRegisterVo.getStatus()+"%'";
			subhql+=" AND userId ='"+son.getUserId()+"' ";
			hql += " AND id in(" + subhql+ ")";
		}else{
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND code='"+son.getFunId()+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql+ ")";
		}
		pageResult=labSampRegisterDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabSampRegisterVo> sampRegisterVoList=new ArrayList<LabSampRegisterVo>();
			List<LabSampRegister> listSampRegister=new ArrayList<LabSampRegister>();
			listSampRegister=pageResult.getResultList();
			for(LabSampRegister labSampRegister:listSampRegister){
				LabSampRegisterVo vo=new LabSampRegisterVo();
				vo=this.po2Vo(labSampRegister, vo);
				if(vo.getCharge()==null){
					vo.setCharge(0.0);
				}
				LabSampCustomerVo customerVo=new LabSampCustomerVo();
				BeanUtils.copyProperties(labSampRegister.getLabSampCustomer(), customerVo);
				vo.setLabSampCustomerVo(customerVo);
				LabWfProcessIns ins=labSampRegister.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag=labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSampRegister.getId(),labSampRegister.getId(),getSessionContainer().getFunId());
							if(flag){
								vo.setIsBack(Constants_Business.Y);
							}else{
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(labSampRegister.getProcessIns().getId());
						vo.setStatus(str);
					}
				}
				sampRegisterVoList.add(vo);
			}
			pageResult.setResultList(sampRegisterVoList);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabSampRegister(LabSampRegisterVo labSampRegisterVo) throws GlobalException {
		LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
		boolean key=true;
		labSampRegister.setUnit(labSampRegisterVo.getUnit());
		labSampRegister.setAcceptUser(labSampRegisterVo.getAcceptUser());
		labSampRegister.setTaskType(labSampRegisterVo.getTaskType());
		labSampRegister.setCreateDate(labSampRegisterVo.getCreateDate());
		labSampRegister.setItemIds(labSampRegisterVo.getItemIds());
		labSampRegister.setItemNames(labSampRegisterVo.getItemNames());
		labSampRegister.setTestStands(labSampRegisterVo.getTestStands());
		labSampRegister.setReportType(labSampRegisterVo.getReportType());
		labSampRegister.setFinishDate(labSampRegisterVo.getFinishDate());
		labSampRegister.setReportNum(labSampRegisterVo.getReportNum());
		labSampRegister.setReportPost(labSampRegisterVo.getReportPost());
		try{
			labSampRegisterDAO.updateLabSampRegister(labSampRegister);
		}catch(Exception e){
			key=false;
			Log4J.error("SampRegisterServiceImpl updateSampRegister  error..."+e.getMessage(), e);
		}
		return key;
	}

	@Override
	public boolean updateLabSampRegister4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(id);
					labSampRegister.setIsDel(Constants_Business.Y);
					labSampRegisterDAO.updateLabSampRegister(labSampRegister);
					labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labSampRegister.getId());
					LabSampCustomer labSampCustomer=labSampCustomerDAO.getLabSampCustomer(labSampRegister.getLabSampCustomer().getId());
					labSampCustomer.setIsDel(Constants_Business.Y);
					labSampCustomerDAO.updateLabSampCustomer(labSampCustomer);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("SampRegisterServiceImpl updateSampRegister4Del  error..."+e.getMessage(), e);
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabSampRegisterVo> getSampRegisterVoListByWhere(String wereHql) throws GlobalException{
		List<LabSampRegisterVo> sampRegisterVoList=new ArrayList<LabSampRegisterVo>();
		String hql="FROM LabSampRegister WHERE isDel='"+Constants_Business.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabSampRegister> sampRegisterList=labSampRegisterDAO.find(hql);
		if(sampRegisterList!=null&&sampRegisterList.size()>0){
			for(LabSampRegister labSampRegister:sampRegisterList){
				LabSampRegisterVo labSampRegisterVo=new LabSampRegisterVo();
				labSampRegisterVo=this.po2Vo(labSampRegister, labSampRegisterVo);
				if(labSampRegisterVo.getCharge()==null){
					labSampRegisterVo.setCharge(0.0);
				}
				LabSampCustomerVo customerVo=new LabSampCustomerVo();
				BeanUtils.copyProperties(labSampRegister.getLabSampCustomer(), customerVo);
				labSampRegisterVo.setLabSampCustomerVo(customerVo);
				sampRegisterVoList.add(labSampRegisterVo);
			}
		}
		return sampRegisterVoList;
	}
	public LabSampRegister vo2Po(LabSampRegisterVo labSampRegisterVo,LabSampRegister labSampRegister){
		BeanUtils.copyProperties(labSampRegisterVo, labSampRegister,new String[]{"id","isDel","createTime","createUserId","tenantId"});
		return labSampRegister;
	}
	public LabSampRegisterVo po2Vo(LabSampRegister labSampRegister,LabSampRegisterVo labSampRegisterVo){
		try {
			BeanUtils.copyProperties(labSampRegister, labSampRegisterVo,new String[]{"charge"});
			if(labSampRegisterVo.getCharge()==null){
				labSampRegisterVo.setCharge(0.0);
			}
		} catch (BeansException e) {
			Log4J.error("SampRegisterServiceImpl updateSampRegister4Del  error..."+e.getMessage(), e);
		}
		return labSampRegisterVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabChargeVo getLabCharge(LabSampRegisterVo labSampRegisterVo)
			throws GlobalException {
		LabChargeVo chargeVo=new LabChargeVo();
		if(labSampRegisterVo==null||labSampRegisterVo.getChargeType()==null){
			return chargeVo;
		}
		String hql="";
		if(labSampRegisterVo.getChargeType().equals("1")){
			hql="SELECT itemName,count(*),price,itemId FROM LabSampItems WHERE isDel='"+Constants_Business.N+"'";
			hql+=" AND busId='"+labSampRegisterVo.getId()+"'";
			hql+=" GROUP BY itemId ";
		}else if(labSampRegisterVo.getChargeType().equals("2")){
			hql="SELECT name,count(*) FROM LabSam WHERE isDel='"+Constants_Business.N+"'";
			hql+=" AND sampNo='"+labSampRegisterVo.getSampNo()+"'";
			hql+=" GROUP BY name ";
		}else if(labSampRegisterVo.getChargeType().equals("3")){
			
		}
		List<Object[]> list=labSampItemsDAO.find(hql);
		List<LabChargeDetailVo> itemList=new ArrayList<LabChargeDetailVo>();
		if(list!=null&&list.size()>0){
			for (Object[] objects : list) {
				LabChargeDetailVo detailVo=new LabChargeDetailVo();
				if(objects.length==2){
					detailVo.setPayName(String.valueOf(objects[0]));
					detailVo.setNum(Integer.valueOf(String.valueOf(objects[1])));
					String chaHql="select price FROM LabChargeDetail WHERE busId='"+labSampRegisterVo.getId()+"'";
					chaHql+=" AND payName like '"+detailVo.getPayName()+"'";
					Object obj=labChargeDetailDAO.find(chaHql, 0);
					if(obj!=null){
						detailVo.setPrice(Double.valueOf(String.valueOf(obj)));
					}else{
						detailVo.setPrice(0.0);
					}
				}else if(objects.length==4){
					detailVo.setPayName(String.valueOf(objects[0]));
					detailVo.setPrice(Double.valueOf(String.valueOf(objects[2]==null?"0":objects[2])));
					if(objects[3]!=null){
						LabItem item=labItemDAO.getLabItem(String.valueOf((objects[3])));
						if(item.getPriceType().equals("1")){
							detailVo.setNum(Integer.valueOf(String.valueOf(objects[1])));
						}else if(item.getPriceType().equals("0")){
							detailVo.setNum(1);
						}
					}
					
				}
				itemList.add(detailVo);
			}
		}
		chargeVo.setChargeList(itemList);//项目费用
		//检测费用  其他费用条目
		String qthql="select po.name,(select price from lab_charge_detail WHERE is_del='"+Constants_Business.N+"' AND pay_name=po.name AND "+
					"bus_id='"+labSampRegisterVo.getId()+"') From lab_code_code po left join lab_code_type ct on "+
					"po.typeid=ct.id  where ct.code='SAMP_QTFY'  order by po.sort";
		List<Object[]> QTFYlist=labCodeDAO.createSqlQuery(qthql);
		List<LabChargeDetailVo> otherList=new ArrayList<LabChargeDetailVo>();
		if(QTFYlist!=null&&QTFYlist.size()>0){
			for (Object[] obj : QTFYlist) {
				LabChargeDetailVo chageVo=new LabChargeDetailVo();
				chageVo.setPayName(String.valueOf(obj[0]));
				if(StrUtils.isNull(obj[1])){
					chageVo.setPrice(0.0);
				}else{
					chageVo.setPrice(Double.valueOf(String.valueOf((obj[1]))));
				}
				otherList.add(chageVo);
			}
		}
		chargeVo.setQtList(otherList);
		String chql="FROM LabCharge WHERE code like '"+labSampRegisterVo.getChargeNo()+"'";
		LabCharge charge=(LabCharge)labChargeDAO.find(chql,0);
		if(charge!=null){
			chargeVo.setDiscount(charge.getDiscount());
			chargeVo.setPreferential(charge.getPreferential());
			chargeVo.setTotalMoney(charge.getTotalMoney());
			chargeVo.setPayMoney(charge.getPayMoney());
		}else{
			chargeVo.setDiscount(100.0);
			chargeVo.setPreferential(0.0);
		}
		return chargeVo;
	}
	@Override
	public List<LabSamVo> getLabSamList(LabSampRegisterVo labSampRegisterVo)
			throws GlobalException {
		String subhql="FROM LabSam WHERE sampNo='"+labSampRegisterVo.getSampNo()+"'";
		List<LabSam> list=labSamDAO.getLabSamList(subhql);
		List<LabSamVo> volist=new ArrayList<LabSamVo>();
		if(labSampRegisterVo==null){
			return volist;
		}
		if(list!=null&&list.size()>0){
			LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
			int newNum=0;
			if(!StrUtils.isBlankOrNull(labSampRegister.getSampNum())){
				try {
					newNum=Integer.valueOf(labSampRegister.getSampNum());
				} catch (NumberFormatException e) {
					newNum=0;
				}
			}
			if(newNum<=list.size()){
				for (int i=0;i<newNum;i++) {
					LabSam labSam=list.get(i);
					LabSamVo vo=new LabSamVo();
					BeanUtils.copyProperties(labSam, vo);
					vo.setSamTypeId(labSam.getLabSamType().getId());
					vo.setSamTypeName(labSam.getLabSamType().getName());
					volist.add(vo);
				}
			}else{
				for (LabSam labSam : list) {
					LabSamVo vo=new LabSamVo();
					BeanUtils.copyProperties(labSam, vo);
					vo.setSamTypeId(labSam.getLabSamType().getId());
					vo.setSamTypeName(labSam.getLabSamType().getName());
					volist.add(vo);
				}
				for (int i=list.size();i<newNum;i++) {
					LabSamVo vo=new LabSamVo();
					vo.setSampCode(labSampRegister.getSampNo()+"-"+(i+1));
					volist.add(vo);
				}
			}
		} else{
			LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
			if(!StrUtils.isBlankOrNull(labSampRegister.getSampNum())){
				int m=0;
				try {
					m=Integer.valueOf(labSampRegister.getSampNum());
				} catch (NumberFormatException e) {
					m=0;
				}
				for (int n=0;n<m;n++) {
					LabSamVo vo=new LabSamVo();
					vo.setSampCode(labSampRegister.getSampNo()+"-"+(n+1));
					volist.add(vo);
				}
			}
		}
		return volist;
	}
	@SuppressWarnings("unchecked")
	public PageResult getLabSampRegisterPR4Audit(LabSampRegisterVo labSampRegisterVo, PageResult pageResult)
		throws GlobalException {
		String hql="FROM LabSampRegister WHERE isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getNo())){
			hql+=" AND no like '%"+labSampRegisterVo.getNo().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getStartDate())){
			hql+=" AND createTime>='"+labSampRegisterVo.getStartDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getEndDate())){
			hql+=" AND createTime<='"+labSampRegisterVo.getEndDate()+"'";
		}
		if((labSampRegisterVo.getLabSampCustomerVo()!=null)&&!StrUtils.isBlankOrNull(labSampRegisterVo.getLabSampCustomerVo().getLabCustomerName())){
			hql+=" AND labSampCustomer.labCustomerName like '%"+labSampRegisterVo.getLabSampCustomerVo().getLabCustomerName().trim()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSampRegisterVo.getSampUser())){
			hql+=" AND sampUser like '%"+labSampRegisterVo.getSampUser().trim()+"%'";
		}
		if (null != labSampRegisterVo.getStatus()
				&& !"".equals(labSampRegisterVo.getStatus())) {
			String subhql="SELECT stepBusId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND status<>'"
			+LabWfConstant.BUS_PROCESS_END+"' AND code='"+labSampRegisterVo.getStatus()+"'";
			subhql+=" AND tenantId LIKE '"+getSessionContainer().getFunDataStr()+"%' ";
			hql += " AND id in(" + subhql+ ")";
		}
		hql+=" ORDER BY createDate DESC";
		pageResult=labSampRegisterDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabSampRegisterVo> sampRegisterVoList=new ArrayList<LabSampRegisterVo>();
			List<LabSampRegister> listSampRegister=new ArrayList<LabSampRegister>();
			listSampRegister=pageResult.getResultList();
			for(LabSampRegister labSampRegister:listSampRegister){
				LabSampRegisterVo vo=new LabSampRegisterVo();
				vo=this.po2Vo(labSampRegister, vo);
				if(vo.getCharge()==null){
					vo.setCharge(0.0);
				}
				LabSampCustomerVo customerVo=new LabSampCustomerVo();
				BeanUtils.copyProperties(labSampRegister.getLabSampCustomer(), customerVo);
				vo.setLabSampCustomerVo(customerVo);
				if (labSampRegister.getProcessIns() != null) {
					String status = labSampRegister.getProcessIns().getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							vo.setIsOper("Y");
						}
						String str = labWfProcessInsDAO
								.getLabWfStepStrByInsId(labSampRegister.getProcessIns()
										.getId());
						vo.setStatus(str);
					}
				}
				sampRegisterVoList.add(vo);
			}
			pageResult.setResultList(sampRegisterVoList);
		}
		return pageResult;
	}
	@Override
	public boolean updateLabSampRegister4Approve(LabSampRegisterVo labSampRegisterVo)
			throws GlobalException {
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegisterVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, getSessionContainer().getFunId(),labSampRegisterVo.getAuditMessage(),
				labSampRegisterVo.getAuditResult());
		if (null == ins) {
			throw new GlobalException("SampRegisterServiceImpl...流程实例化出错！");
		}
		return true;
	}
	@Override
	public boolean updateLabSampRegister4Audit(LabSampRegisterVo labSampRegisterVo)
			throws GlobalException {
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegisterVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, getSessionContainer().getFunId(),labSampRegisterVo.getAuditMessage(),
				labSampRegisterVo.getAuditResult());
		
		if (null == ins) {
			throw new GlobalException("SampRegisterServiceImpl...流程实例化出错！");
		}
		return true;
	}
	@Override
	public boolean updateLabSampRegister4Check(LabSampRegisterVo labSampRegisterVo)
			throws GlobalException {
		// 流程实例
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSampRegisterVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, getSessionContainer().getFunId(),labSampRegisterVo.getAuditMessage(),
				labSampRegisterVo.getAuditResult());
		if (null == ins) {
			throw new GlobalException("SampRegisterServiceImpl...流程实例化出错！");
		}
		return false;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean ajaxLabSampItem4Exsit(String id) throws GlobalException {
		String hqls="FROM LabSampItems WHERE isDel='"+Constants_Business.N+"' AND busId='"+id+"'";
		List<LabSampItems> listLabSampItems=labSampItemsDAO.find(hqls);
		if(listLabSampItems!=null&&listLabSampItems.size()>0){
			return true;
		}else{
			return false;
		}
	}
	@SuppressWarnings("unchecked")
	public LabSampRegisterVo saveLabSampRegister4Other(LabSampRegisterVo labSampRegisterVo,List<LabSamVo> sampList) throws GlobalException {
		LabSampRegister labSampRegister=labSampRegisterDAO.getLabSampRegister(labSampRegisterVo.getId());
		//检测样品信息
		if(sampList!=null){
			String hql="FROM LabSamMain WHERE sampNo='"+labSampRegister.getSampNo()+"'";
			LabSamMain samMain=(LabSamMain)labSamMainDAO.find(hql, 0);
			if(samMain==null){
				samMain=new LabSamMain();
				samMain.setSampNo(labSampRegister.getSampNo());
				samMain.setSampDate(labSampRegister.getSampDate());
				samMain.setSampUser(labSampRegister.getSampUser());
				samMain.setSampDesc(labSampRegister.getSampDesc());
				samMain.setSampPack(labSampRegister.getSampPack());
				samMain.setSaveOrg(labSampRegister.getSaveOrg());
				samMain.setSaveUser(labSampRegister.getSaveUser());
				samMain.setBusId(labSampRegister.getId());
				samMain.setNum(labSampRegister.getSampNum());
				samMain.setCustomer(labSampRegister.getLabSampCustomer().getLabCustomerName());
				samMain.setContacts(labSampRegister.getLabSampCustomer().getUser());
				samMain.setContactPhone(labSampRegister.getLabSampCustomer().getTelephone());
				samMain.setRegistDate(DateUtils.getCurrDateTimeStr());
				labSamMainDAO.addLabSamMain(samMain);
			}else{
				samMain.setSampNo(labSampRegister.getSampNo());
				samMain.setSampDate(labSampRegister.getSampDate());
				samMain.setSampUser(labSampRegister.getSampUser());
				samMain.setSampDesc(labSampRegister.getSampDesc());
				samMain.setSampPack(labSampRegister.getSampPack());
				samMain.setSaveOrg(labSampRegister.getSaveOrg());
				samMain.setSaveUser(labSampRegister.getSaveUser());
				samMain.setBusId(labSampRegister.getId());
				samMain.setNum(labSampRegister.getSampNum());
				samMain.setCustomer(labSampRegister.getLabSampCustomer().getLabCustomerName());
				samMain.setContacts(labSampRegister.getLabSampCustomer().getUser());
				samMain.setContactPhone(labSampRegister.getLabSampCustomer().getTelephone());
				labSamMainDAO.updateLabSamMain(samMain);
			}
			//删除已经保存的项目
			try {
				labSampItemsDAO.executeSQL("DELETE FROM lab_Samp_items WHERE bus_id='"+labSampRegister.getId()+"'");
				labSamDAO.executeSQL("DELETE FROM lab_sam WHERE samp_no='"+labSampRegister.getSampNo()+"'");
			} catch (Exception e) {
				Log4J.error("SampRegisterServiceImpl..."+e.getMessage());
			}
			for (int i=0;i<sampList.size();i++) {
				LabSamVo labSamVo=sampList.get(i);
				LabSam sam=new LabSam();
				sam.setMain(samMain);
				sam.setSampNo(labSampRegister.getSampNo());
				sam.setItemId(labSamVo.getItemId());
				sam.setItemName(labSamVo.getItemName());
				sam.setName(labSamVo.getName());
				sam.setSampCode(labSamVo.getSampCode());
				sam.setOldNo(labSamVo.getOldNo());
				sam.setSeqNum(String.valueOf(i));
				LabSamType sampType =labSamTypeDAO.getLabSamType(labSamVo.getSamTypeId());
				sam.setLabSamType(sampType);
				labSamDAO.addLabSam(sam);
				String itemids[]=labSamVo.getItemId().split(",");
				if(itemids!=null&&itemids.length>0){
					for (String itemid : itemids) {
						if(itemid!=null&&itemid.trim().length()>0){
							LabSampItems sampItem=new LabSampItems();
							sampItem.setBusId(labSampRegister.getId());
							sampItem.setLabSamId(sam.getId());
							sampItem.setLabSamName(sam.getName());
							sampItem.setSamCode(sam.getSampCode());
							sampItem.setLabSamTypeId(sampType.getId());
							sampItem.setLabSamTypeName(sampType.getName());
							LabItem item=null;
							if(itemid.contains("*")){
								String idStr[]=itemid.split("\\*");
								if(idStr.length>=3){
									item=labItemDAO.getLabItem(idStr[0]);
									LabStandard stand=labStandardDAO.getLabStandard(idStr[1]);
									if(stand!=null){
										sampItem.setStandardId(stand.getId());
										sampItem.setStandardName(stand.getName());
									}
									LabMethod method =labMethodDAO.getLabMethod(idStr[2]);
									if(method!=null){
										sampItem.setMethodId(method.getId());
										sampItem.setMethodName(method.getName());
									}
								}
							}else{
								item=labItemDAO.getLabItem(itemid);
							}
							sampItem.setPrice(item.getPrice());
							sampItem.setItemId(item.getId());
							sampItem.setItemName(item.getName());
							labSampItemsDAO.addLabSampItems(sampItem);
						}
					}
				}
			}
		}
		return labSampRegisterVo;
	}
}
