package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.business.sample.dao.ILabSampRegisterDAO;
import cn.labsoft.labos.business.sample.entity.LabSampCustomer;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaProficiencyPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiencyPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaProficiencyPlanService;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyPlanVo;
import cn.labsoft.labos.utils.StrUtils;


@Service("labQuaProficiencyPlanService")
public class LabQuaProficiencyPlanServiceImpl extends BaseService implements ILabQuaProficiencyPlanService{
	public   ExecutorService pool = Executors. newSingleThreadExecutor();
	
	private ILabQuaProficiencyPlanDAO labQuaProficiencyPlanDAO;
	private ILabSampRegisterDAO labSampRegisterDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabNumberDAO labNumberDAO;
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabQuaProficiencyPlanDAO(
			ILabQuaProficiencyPlanDAO labQuaProficiencyPlanDAO) {
		this.labQuaProficiencyPlanDAO = labQuaProficiencyPlanDAO;
	}
	@Resource
	public void setLabSampRegisterDAO(ILabSampRegisterDAO labSampRegisterDAO) {
		this.labSampRegisterDAO = labSampRegisterDAO;
	}

	@Override
	public boolean update2DelLabQuaProficiencyPlan(String[] ids) throws GlobalException {
		if(ids.length > 0){
			for(String id : ids ){
				LabQuaProficiencyPlan labQuaProficiencyPlan=(LabQuaProficiencyPlan) labQuaProficiencyPlanDAO.findById(LabQuaProficiencyPlan.class, id);
				labQuaProficiencyPlan.setIsDel(Constants_Source.Y);
				labQuaProficiencyPlanDAO.updateLabQuaProficiencyPlan(labQuaProficiencyPlan);
			}
		}
		return true;
	}
	@Override
	public LabQuaProficiencyPlanVo getLabQuaProficiencyPlan(String id)
			throws GlobalException {
		LabQuaProficiencyPlanVo labQuaProficiencyPlanVo = new LabQuaProficiencyPlanVo();
		LabQuaProficiencyPlan labQuaProficiencyPlan=labQuaProficiencyPlanDAO.getLabQuaProficiencyPlan(id);
		BeanUtils.copyProperties(labQuaProficiencyPlan, labQuaProficiencyPlanVo, new String[]{"isDel","status"});
		if( null != labQuaProficiencyPlan.getOrg()){
			labQuaProficiencyPlanVo.setUnitOrgId(labQuaProficiencyPlan.getOrg().getId());
			labQuaProficiencyPlanVo.setUnitOrgName(labQuaProficiencyPlan.getOrg().getName());
		}
		if(null!=labQuaProficiencyPlanVo.getIsTest()&&labQuaProficiencyPlanVo.getIsTest().equals(Constants_Source.Y)){
			String hql="FROM LabSampRegister WHERE isDel='"+Constants_Source.N+"' AND otherType='"+Constants_Source.WF_CODE_QUA_NL+"' AND otherId='"+labQuaProficiencyPlan.getId()+"'";
			LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hql,0);
			if(labSampRegister!=null){
				labQuaProficiencyPlanVo.setSampRegisterNo(labSampRegister.getNo());
				LabSampCustomer customer=labSampRegister.getLabSampCustomer();
				labQuaProficiencyPlanVo.setSampRegisterTel(customer.getTelephone());
				labQuaProficiencyPlanVo.setSampRegisterTitle(customer.getLabCustomerName());
				labQuaProficiencyPlanVo.setSampRegisterUser(customer.getUser());
				labQuaProficiencyPlanVo.setSampRegisterNum(labSampRegister.getSampNum());
				labQuaProficiencyPlanVo.setSampRegisterId(labSampRegister.getId());
				labQuaProficiencyPlanVo.setSampRegisterSampNo(labSampRegister.getSampNo());
			}
		}
		return labQuaProficiencyPlanVo;
	}
	@SuppressWarnings("unchecked")
	@Override
	public LabQuaProficiencyPlanVo addLabQuaProficiencyPlan(LabQuaProficiencyPlanVo labQuaProficiencyPlanVo)
			throws GlobalException {
		LabQuaProficiencyPlan labQuaProficiencyPlan=new LabQuaProficiencyPlan();
		BeanUtils.copyProperties(labQuaProficiencyPlanVo, labQuaProficiencyPlan, new String[] {"isDel","status"});
		labQuaProficiencyPlan.setIsDel(Constants_Source.N);
		labQuaProficiencyPlan.setStatus("00");
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getUnitOrgId())){
			LabOrg org = (LabOrg) labQuaProficiencyPlanDAO.findById(LabOrg.class, labQuaProficiencyPlanVo.getUnitOrgId());
			if( null != org){
				labQuaProficiencyPlan.setOrg(org);
			}
		}
		labQuaProficiencyPlan.setAccStatus("0");
		labQuaProficiencyPlanDAO.addLabQuaProficiencyPlan(labQuaProficiencyPlan);
		labQuaProficiencyPlanVo.setId(labQuaProficiencyPlan.getId());
		
		if(null!=labQuaProficiencyPlanVo.getIsTest()&&labQuaProficiencyPlanVo.getIsTest().equals(Constants_Source.Y)){
			LabSampRegister labSampRegister=new LabSampRegister();
			
			ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
					Constants_Source.CODE_RW, new String[] {},Constants_Source.CODE_USE_RUN);
			try {
				labSampRegister.setNo(pool.submit(threadNumber).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
			ThreadNumber threadNumbers = new ThreadNumber(null,labNumberDAO,
					Constants_Source.CODE_LY, new String[] {},Constants_Source.CODE_USE_RUN);
			try {
				labSampRegister.setSampNo(pool.submit(threadNumbers).get().toString());
			} catch (Exception e) {
				Log4J.error(e.getMessage());
				throw new GlobalException("" + e.getMessage());
			}
			LabSampCustomer labSampCustomer=new LabSampCustomer();
			labSampCustomer.setLabCustomerName(labQuaProficiencyPlanVo.getSampRegisterTitle());
			labSampCustomer.setUser(labQuaProficiencyPlanVo.getSampRegisterUser());
			labSampCustomer.setTelephone(labQuaProficiencyPlanVo.getSampRegisterTel());
			labSampRegisterDAO.addLabSampRegister(labSampRegister);
			labSampRegister.setLabSampCustomer(labSampCustomer);
			labSampRegister.setTaskType("能力验证");
			labSampRegister.setReportType("检测");
			labSampRegister.setReportMake("1");
			labSampRegister.setReportPost("自取");
			labSampRegister.setReportNum("1");
			labSampRegister.setSampNum(labQuaProficiencyPlanVo.getSampRegisterNum());
			labSampRegister.setOtherId(labQuaProficiencyPlan.getId());
			labSampRegister.setOtherType(Constants_Source.WF_CODE_QUA_NL);
			labSampRegisterDAO.addLabSampRegister(labSampRegister);
			// 流程实例
			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns4Other(labSampRegister.getId(),Constants_Source.WF_CODE_BUS_RW);
			if(ins==null){
				throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
			}else{
				labSampRegister.setProcessIns(ins);
				labSampRegisterDAO.updateLabSampRegister(labSampRegister);
			}
			labQuaProficiencyPlanVo.setSampRegisterId(labSampRegister.getId());
		}
		return labQuaProficiencyPlanVo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean updateLabQuaProficiencyPlan(LabQuaProficiencyPlanVo labQuaProficiencyPlanVo)
			throws GlobalException {
		LabQuaProficiencyPlan labQuaProficiencyPlan=labQuaProficiencyPlanDAO.getLabQuaProficiencyPlan(labQuaProficiencyPlanVo.getId());
		BeanUtils.copyProperties(labQuaProficiencyPlanVo, labQuaProficiencyPlan, new String[] {"isDel","createTime","tenantId","createUserId","status","accStatus"});
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getUnitOrgId())){
			LabOrg org = (LabOrg) labQuaProficiencyPlanDAO.findById(LabOrg.class, labQuaProficiencyPlanVo.getUnitOrgId());
			if( null != org){
				labQuaProficiencyPlan.setOrg(org);
			}
		}
		labQuaProficiencyPlanDAO.updateLabQuaProficiencyPlan(labQuaProficiencyPlan);
		if(null!=labQuaProficiencyPlanVo.getIsTest()&&labQuaProficiencyPlanVo.getIsTest().equals(Constants_Source.Y)){
			String hql="FROM LabSampRegister WHERE isDel='"+Constants_Source.N+"' AND otherType='"+Constants_Source.WF_CODE_QUA_NL+"' AND otherId='"+labQuaProficiencyPlan.getId()+"'";
			LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hql,0);
			if(labSampRegister==null){
				labSampRegister = new LabSampRegister();
				ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
						Constants_Source.CODE_RW, new String[] {},Constants_Source.CODE_USE_RUN);
				try {
					labSampRegister.setNo(pool.submit(threadNumber).get().toString());
				} catch (Exception e) {
					Log4J.error(e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
				ThreadNumber threadNumbers = new ThreadNumber(null,labNumberDAO,
						Constants_Source.CODE_LY, new String[] {},Constants_Source.CODE_USE_INIT);
				try {
					labSampRegister.setSampNo(pool.submit(threadNumbers).get().toString());
				} catch (Exception e) {
					Log4J.error(e.getMessage());
					throw new GlobalException("" + e.getMessage());
				}
				LabSampCustomer labSampCustomer=new LabSampCustomer();
				labSampCustomer.setLabCustomerName(labQuaProficiencyPlanVo.getSampRegisterTitle());
				labSampCustomer.setUser(labQuaProficiencyPlanVo.getSampRegisterUser());
				labSampCustomer.setTelephone(labQuaProficiencyPlanVo.getSampRegisterTel());
				labSampRegisterDAO.addLabSampRegister(labSampRegister);
				labSampRegister.setLabSampCustomer(labSampCustomer);
				labSampRegister.setTaskType("能力验证");
				labSampRegister.setReportType("检测");
				labSampRegister.setReportMake("1");
				labSampRegister.setOtherId(labQuaProficiencyPlan.getId());
				labSampRegister.setOtherType(Constants_Source.WF_CODE_QUA_NL);
				labSampRegisterDAO.addLabSampRegister(labSampRegister);
				LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns4Other(labSampRegister.getId(),Constants_Source.WF_CODE_BUS_RW);
				if(ins==null){
					throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
				}else{
					labSampRegister.setProcessIns(ins);
					labSampRegisterDAO.updateLabSampRegister(labSampRegister);
				}
			}else{
				String hqlx="FROM LabWfStepIns WHERE isDel='"+Constants_Source.N+"' AND busId='"+labSampRegister.getId()+"'";
				List list=labWfProcessInsDAO.find4All(hqlx);
				if(list.size()<=1){
					labSampRegister.setSampNum(labQuaProficiencyPlanVo.getSampRegisterNum());
					if(StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getSampRegisterNo())){
						ThreadNumber threadNumber = new ThreadNumber(null,labNumberDAO,
								Constants_Source.CODE_RW, new String[] {},Constants_Source.CODE_USE_RUN);
						try {
							labSampRegister.setNo(pool.submit(threadNumber).get().toString());
						} catch (Exception e) {
							Log4J.error(e.getMessage());
							throw new GlobalException("" + e.getMessage());
						}
					}
					labSampRegisterDAO.updateLabSampRegister(labSampRegister);
					LabSampCustomer labSampCustomer=labSampRegister.getLabSampCustomer();
					labSampCustomer.setLabCustomerName(labQuaProficiencyPlanVo.getSampRegisterTitle());
					labSampCustomer.setUser(labQuaProficiencyPlanVo.getSampRegisterUser());
					labSampCustomer.setTelephone(labQuaProficiencyPlanVo.getSampRegisterTel());
					labSampRegisterDAO.updateLabSampRegister(labSampRegister);
				}
			}
		}else{
			String hql="FROM LabSampRegister WHERE isDel='"+Constants_Source.N+"' AND otherType='"+Constants_Source.WF_CODE_QUA_NL+"' AND otherId='"+labQuaProficiencyPlan.getId()+"'";
			LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hql,0);
			if(labSampRegister!=null){
				labSampRegister.setIsDel(Constants_Source.Y);
				labSampRegisterDAO.updateLabSampRegister(labSampRegister);
				labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labSampRegister.getId());
			}
		}
		return true;
	}


	@Override
	public List<LabQuaProficiencyPlanVo> getLabQuaProficiencyPlanList(
			LabQuaProficiencyPlanVo labQuaProficiencyPlanVo)
			throws GlobalException {
		String hql = "FROM LabQuaProficiencyPlan WHERE isDel = '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getUnitOrgId())){
			hql += " AND org.id ='"+labQuaProficiencyPlanVo.getUnitOrgId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getAuditPeople())){
			hql += " AND auditPeople LIKE '%"+labQuaProficiencyPlanVo.getAuditPeople()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getAccStatus())){
			hql += " AND accStatus = '"+labQuaProficiencyPlanVo.getAccStatus()+"' ";
		}
		List<LabQuaProficiencyPlan> labQuaProficiencyPlanList = labQuaProficiencyPlanDAO.getLabQuaProficiencyPlanByHQL(hql);
		List<LabQuaProficiencyPlanVo> labQuaProficiencyPlanVoList = new ArrayList<LabQuaProficiencyPlanVo>();
		if( null != labQuaProficiencyPlanList && labQuaProficiencyPlanList.size() > 0 ){
			for(LabQuaProficiencyPlan  labQuaProficiencyPlan : labQuaProficiencyPlanList){
				LabQuaProficiencyPlanVo labQuaProficiencyPlanVoOne = new LabQuaProficiencyPlanVo();
				BeanUtils.copyProperties(labQuaProficiencyPlan, labQuaProficiencyPlanVoOne, new String[] {"isDel"});
				if( null != labQuaProficiencyPlan.getOrg()){
					labQuaProficiencyPlanVoOne.setUnitOrgId(labQuaProficiencyPlan.getOrg().getId());
					labQuaProficiencyPlanVoOne.setUnitOrgName(labQuaProficiencyPlan.getOrg().getName());
				}
				labQuaProficiencyPlanVoList.add(labQuaProficiencyPlanVoOne);
			}
		}
		return labQuaProficiencyPlanVoList;
	}


	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabQuaProficiencyPlanPR(
			LabQuaProficiencyPlanVo labQuaProficiencyPlanVo,
			PageResult pageResult) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaProficiencyPlan WHERE isDel = '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getUnitOrgSearch())){
			hql += " AND org.id ='"+labQuaProficiencyPlanVo.getUnitOrgSearch()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getAuditPeople())){
			hql += " AND auditPeople LIKE '%"+labQuaProficiencyPlanVo.getAuditPeople()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getName())){
			hql += " AND name LIKE '%"+labQuaProficiencyPlanVo.getName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyPlanVo.getAccStatus())){
			hql += " AND accStatus = '"+labQuaProficiencyPlanVo.getAccStatus()+"' ";
		}
		pageResult = labQuaProficiencyPlanDAO.getLabQuaProficiencyPlanPR(hql, pageResult);
		List<LabQuaProficiencyPlan> labQuaProficiencyPlanList = pageResult.getResultList();
		List<LabQuaProficiencyPlanVo> resultList = new ArrayList<LabQuaProficiencyPlanVo>();
		if( null != labQuaProficiencyPlanList && labQuaProficiencyPlanList.size() > 0 ){
			for(LabQuaProficiencyPlan  labQuaProficiencyPlan : labQuaProficiencyPlanList){
				LabQuaProficiencyPlanVo labQuaProficiencyPlanVoOne = new LabQuaProficiencyPlanVo();
				BeanUtils.copyProperties(labQuaProficiencyPlan, labQuaProficiencyPlanVoOne, new String[] {"isDel"});
				if( null != labQuaProficiencyPlan.getOrg()){
					labQuaProficiencyPlanVoOne.setUnitOrgId(labQuaProficiencyPlan.getOrg().getId());
					labQuaProficiencyPlanVoOne.setUnitOrgName(labQuaProficiencyPlan.getOrg().getName());
				}
				if(null!=labQuaProficiencyPlan.getIsTest()&&labQuaProficiencyPlan.getIsTest().equals(Constants_Source.Y)){
					String hqls="FROM LabSampRegister WHERE isDel='"+Constants_Source.N+"' AND otherType='"+Constants_Source.WF_CODE_QUA_NL+"' AND otherId='"+labQuaProficiencyPlan.getId()+"'";
					LabSampRegister labSampRegister=(LabSampRegister)labSampRegisterDAO.find(hqls,0);
					if(labSampRegister!=null){
						labQuaProficiencyPlanVoOne.setSampRegisterId(labSampRegister.getId());
					}
				}
				resultList.add(labQuaProficiencyPlanVoOne);
			}
			pageResult.setResultList(resultList);
		}
		return pageResult;
	}
	
}
