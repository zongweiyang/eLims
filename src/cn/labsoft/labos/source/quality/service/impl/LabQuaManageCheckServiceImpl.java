package cn.labsoft.labos.source.quality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaManageCheckDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaManageCheckPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheck;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheckPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaManageCheckService;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckVo;
import cn.labsoft.labos.utils.StrUtils;


@Service("labQuaManageCheckService")
public class LabQuaManageCheckServiceImpl extends BaseService implements ILabQuaManageCheckService {

	public  ExecutorService poolSer = Executors.newSingleThreadExecutor();
	private ILabQuaManageCheckDAO labQuaManageCheckDAO;
	private ILabQuaManageCheckPlanDAO labQuaManageCheckPlanDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabNumberDAO labNumberDAO;

	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabQuaManageCheckDAO(ILabQuaManageCheckDAO labQuaManageCheckDAO) {
		this.labQuaManageCheckDAO = labQuaManageCheckDAO;
	}
	@Resource
	public void setLabQuaManageCheckPlanDAO(
			ILabQuaManageCheckPlanDAO labQuaManageCheckPlanDAO) {
		this.labQuaManageCheckPlanDAO = labQuaManageCheckPlanDAO;
	}
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}
	@Override
	public boolean update2DelLabQuaManageCheck(String[] ids) throws GlobalException {
		if(ids.length > 0 ){
			for(String id : ids ){
				LabQuaManageCheck labQuaManageCheck=labQuaManageCheckDAO.getLabQuaManageCheck(id);
				labQuaManageCheck.setIsDel(Constants_Source.Y);
				labQuaManageCheckDAO.updateLabQuaManageCheck(labQuaManageCheck);
//				labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labQuaManageCheck.getId());
			}
		}
		return true;
	}

	@Override
	public List<LabQuaManageCheckVo> getLabQuaManageCheckList(
			LabQuaManageCheckVo labQuaManageCheckVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaManageCheck WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getQuaManageCheckPlanId())){
			hql += " AND labQuaManageCheckPlan.id = '"+labQuaManageCheckVo.getQuaManageCheckPlanId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getQuaManageCheckPlanName())){
			hql += " AND labQuaManageCheckPlan.name LIKE '%"+labQuaManageCheckVo.getQuaManageCheckPlanName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getRecordTime())){
			hql += " AND recordTime = '"+labQuaManageCheckVo.getRecordTime()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getOrgId())){
			hql += " AND org.id = '"+labQuaManageCheckVo.getOrgId()+"'";
		}
		List<LabQuaManageCheck> labQuaManageCheckList=labQuaManageCheckDAO.getLabQuaManageCheckByHQL(hql);
		List<LabQuaManageCheckVo> labQuaManageCheckVoList=new ArrayList<LabQuaManageCheckVo>();
		if (null!=labQuaManageCheckList && labQuaManageCheckList.size()>0) {
			for (LabQuaManageCheck labQuaManageCheck : labQuaManageCheckList) {
				LabQuaManageCheckVo labQuaManageCheckVoOne=new LabQuaManageCheckVo();
				BeanUtils.copyProperties(labQuaManageCheck, labQuaManageCheckVoOne, new String[]{"isDel"});
				if(null != labQuaManageCheck.getOrg()){
					labQuaManageCheckVoOne.setOrgId(labQuaManageCheck.getOrg().getId());
					labQuaManageCheckVoOne.setOrgName(labQuaManageCheck.getOrg().getName());
				}
				if(null != labQuaManageCheck.getLabQuaManageCheckPlan()){
					labQuaManageCheckVoOne.setQuaManageCheckPlanId(labQuaManageCheck.getLabQuaManageCheckPlan().getId());
					labQuaManageCheckVoOne.setQuaManageCheckPlanName(labQuaManageCheck.getLabQuaManageCheckPlan().getName());
					labQuaManageCheckVoOne.setRecTime(labQuaManageCheck.getLabQuaManageCheckPlan().getRecTime());
					labQuaManageCheckVoOne.setGroupLeader(labQuaManageCheck.getLabQuaManageCheckPlan().getGroupLeader());
					labQuaManageCheckVoOne.setGroupMember(labQuaManageCheck.getLabQuaManageCheckPlan().getGroupMember());
					labQuaManageCheckVoOne.setFoundation(labQuaManageCheck.getLabQuaManageCheckPlan().getFoundation());
					labQuaManageCheckVoOne.setContents(labQuaManageCheck.getLabQuaManageCheckPlan().getContents());
					labQuaManageCheckVoOne.setAddress(labQuaManageCheck.getLabQuaManageCheckPlan().getAddress());
				}
				labQuaManageCheckVoList.add(labQuaManageCheckVoOne);
			} 
		}
		return labQuaManageCheckVoList;
	}

	@Override
	public PageResult getLabQuaManageCheckPR(LabQuaManageCheckVo labQuaManageCheckVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabQuaManageCheck WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getQuaManageCheckPlanId())){
			hql += " AND labQuaManageCheckPlan.id = '"+labQuaManageCheckVo.getQuaManageCheckPlanId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getQuaManageCheckPlanName())){
			hql += " AND labQuaManageCheckPlan.name LIKE '%"+labQuaManageCheckVo.getQuaManageCheckPlanName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getStatus())){
			hql += " AND processIns.status ='" + labQuaManageCheckVo.getStatus()+ "'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getRecordTime())){
			hql += " AND recordTime = '"+labQuaManageCheckVo.getRecordTime()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getOrgId())){
			hql += " AND org.id = '"+labQuaManageCheckVo.getOrgId()+"'";
		}
		pageResult = labQuaManageCheckDAO.getLabQuaManageCheckPR(hql, pageResult);
		List<LabQuaManageCheck> labQuaManageCheckList=pageResult.getResultList();
		List<LabQuaManageCheckVo> labQuaManageCheckVoList=new ArrayList<LabQuaManageCheckVo>();
		if (null!=labQuaManageCheckList && labQuaManageCheckList.size()>0) {
			for (LabQuaManageCheck labQuaManageCheck : labQuaManageCheckList) {
				LabQuaManageCheckVo labQuaManageCheckVoOne=new LabQuaManageCheckVo();
				BeanUtils.copyProperties(labQuaManageCheck, labQuaManageCheckVoOne, new String[]{"isDel"});
				if(null != labQuaManageCheck.getOrg()){
					labQuaManageCheckVoOne.setOrgId(labQuaManageCheck.getOrg().getId());
					labQuaManageCheckVoOne.setOrgName(labQuaManageCheck.getOrg().getName());
				}
				if(null != labQuaManageCheck.getLabQuaManageCheckPlan()){
					labQuaManageCheckVoOne.setQuaManageCheckPlanId(labQuaManageCheck.getLabQuaManageCheckPlan().getId());
					labQuaManageCheckVoOne.setQuaManageCheckPlanName(labQuaManageCheck.getLabQuaManageCheckPlan().getName());
					labQuaManageCheckVoOne.setRecTime(labQuaManageCheck.getLabQuaManageCheckPlan().getRecTime());
					labQuaManageCheckVoOne.setAddress(labQuaManageCheck.getLabQuaManageCheckPlan().getAddress());
				}
				if (labQuaManageCheck.getProcessIns() != null) {
					String status = labQuaManageCheck.getProcessIns().getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						labQuaManageCheckVoOne.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							labQuaManageCheckVoOne.setIsOper("Y");
						}
						String str = labWfProcessInsDAO
								.getLabWfStepStrByInsId(labQuaManageCheck.getProcessIns()
										.getId());
						labQuaManageCheckVoOne.setStatus(str);
					}
				}
				labQuaManageCheckVoList.add(labQuaManageCheckVoOne);
			} 
			pageResult.setResultList(labQuaManageCheckVoList);
		}
		return pageResult;

	}

	@Override
	public LabQuaManageCheckVo getLabQuaManageCheck(String id) throws GlobalException {
		LabQuaManageCheck labQuaManageCheck = labQuaManageCheckDAO.getLabQuaManageCheck(id);
		LabQuaManageCheckVo labQuaManageCheckVo = new LabQuaManageCheckVo();
		BeanUtils.copyProperties(labQuaManageCheck, labQuaManageCheckVo, new String[] { "isDel"});
		if(null != labQuaManageCheck.getOrg()){
			labQuaManageCheckVo.setOrgId(labQuaManageCheck.getOrg().getId());
			labQuaManageCheckVo.setOrgName(labQuaManageCheck.getOrg().getName());
		}
		if(null != labQuaManageCheck.getLabQuaManageCheckPlan()){
			labQuaManageCheckVo.setQuaManageCheckPlanId(labQuaManageCheck.getLabQuaManageCheckPlan().getId());
			labQuaManageCheckVo.setQuaManageCheckPlanName(labQuaManageCheck.getLabQuaManageCheckPlan().getName());
			labQuaManageCheckVo.setRecTime(labQuaManageCheck.getLabQuaManageCheckPlan().getRecTime());
			labQuaManageCheckVo.setGroupLeader(labQuaManageCheck.getLabQuaManageCheckPlan().getGroupLeader());
			labQuaManageCheckVo.setGroupMember(labQuaManageCheck.getLabQuaManageCheckPlan().getGroupMember());
			labQuaManageCheckVo.setFoundation(labQuaManageCheck.getLabQuaManageCheckPlan().getFoundation());
			labQuaManageCheckVo.setContents(labQuaManageCheck.getLabQuaManageCheckPlan().getContents());
			labQuaManageCheckVo.setAddress(labQuaManageCheck.getLabQuaManageCheckPlan().getAddress());
			labQuaManageCheckVo.setTrackPeople(labQuaManageCheck.getLabQuaManageCheckPlan().getTrackPeople());
		}
		return labQuaManageCheckVo;
	}

	@Override
	public LabQuaManageCheckVo addLabQuaManageCheck(LabQuaManageCheckVo labQuaManageCheckVo) throws GlobalException {
		LabQuaManageCheck labQuaManageCheck = new LabQuaManageCheck();
		BeanUtils.copyProperties(labQuaManageCheckVo, labQuaManageCheck, new String[] {"isDel"});
		labQuaManageCheck.setIsDel(Constants_Source.N);
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getQuaManageCheckPlanId())){
			LabQuaManageCheckPlan labQuaManageCheckPlan = labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(labQuaManageCheckVo.getQuaManageCheckPlanId());
			labQuaManageCheck.setLabQuaManageCheckPlan(labQuaManageCheckPlan);
			labQuaManageCheckPlan.setStatus("10");
			labQuaManageCheckPlanDAO.updateLabQuaManageCheckPlan(labQuaManageCheckPlan);
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getOrgId())){
			LabOrg labOrg = (LabOrg)labQuaManageCheckDAO.findById(LabOrg.class, labQuaManageCheckVo.getOrgId());
			labQuaManageCheck.setOrg(labOrg);
		}
		labQuaManageCheck.setCreateUserId(getSessionContainer().getUserId());
		ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_GL,new String[]{""},Constants_Source.CODE_USE_RUN);
		try {
			labQuaManageCheck.setReportNo(poolSer.submit(threadNumber).get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		labQuaManageCheck.setIsFile("1");
		labQuaManageCheckDAO.addLabQuaManageCheck(labQuaManageCheck);
		labQuaManageCheckVo.setId(labQuaManageCheck.getId());
		
//		// 流程实例
//		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labQuaManageCheck
//				.getId(), Constants.WF_CODE_QUA_MANAGE, getSessionContainer().getFunId(),
//				labQuaManageCheckVo.getAuditMessage(), labQuaManageCheckVo.getAuditResult());
//		if (ins != null) {
//			labQuaManageCheck.setProcessIns(ins);
//			labQuaManageCheckDAO.updateLabQuaManageCheck(labQuaManageCheck);
//		} else {
//			throw new GlobalException("LabQuaManageCheckServiceImpl...流程实例化出错！");
//		}
		return labQuaManageCheckVo;
	}

	@Override
	public boolean updateLabQuaManageCheck(LabQuaManageCheckVo labQuaManageCheckVo) throws GlobalException {
		LabQuaManageCheck labQuaManageCheck = labQuaManageCheckDAO.getLabQuaManageCheck(labQuaManageCheckVo.getId());
		BeanUtils.copyProperties(labQuaManageCheckVo, labQuaManageCheck, new String[] {"isDel","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getQuaManageCheckPlanId())){
			LabQuaManageCheckPlan labQuaManageCheckPlan = labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(labQuaManageCheckVo.getQuaManageCheckPlanId());
			labQuaManageCheck.setLabQuaManageCheckPlan(labQuaManageCheckPlan);
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckVo.getOrgId())){
			LabOrg labOrg = (LabOrg)labQuaManageCheckDAO.findById(LabOrg.class, labQuaManageCheckVo.getOrgId());
			labQuaManageCheck.setOrg(labOrg);
		}
		if(StrUtils.isBlankOrNull(labQuaManageCheckVo.getReportNo())){
			ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_GL,new String[]{""},Constants_Source.CODE_USE_RUN);
			try {
				labQuaManageCheck.setReportNo(poolSer.submit(threadNumber).get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		labQuaManageCheckDAO.updateLabQuaManageCheck(labQuaManageCheck);
		// 流程实例
//		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
//				labQuaManageCheck.getId(),  Constants.WF_CODE_QUA_MANAGE, getSessionContainer()
//						.getFunId(), labQuaManageCheckVo.getAuditMessage(),
//						labQuaManageCheckVo.getAuditResult());
//		if (ins == null) {
//			throw new GlobalException("LabQuaManageCheckServiceImpl...流程实例化出错！");
//		}
		return true;
	}
	
	@Override
	public boolean updateLabQuaManageCheck4Report(LabQuaManageCheckVo labQuaManageCheckVo) throws GlobalException {
		LabQuaManageCheck labQuaManageCheck=new LabQuaManageCheck();
		boolean key=true;
		try{
			labQuaManageCheck=labQuaManageCheckDAO.getLabQuaManageCheck(labQuaManageCheckVo.getId());
			labQuaManageCheck.setReportTempId(labQuaManageCheckVo.getReportTempId());
			String path=labQuaManageCheckVo.getReportPath();
			path=path.replace("\\", "/");
			labQuaManageCheck.setReportPath(path);
			labQuaManageCheckDAO.updateLabQuaManageCheck(labQuaManageCheck);
		}catch(Exception e){
			key=false;
			Log4J.error("LabQuaManageCheckServiceImpl  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
}
