package cn.labsoft.labos.source.quality.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDetailDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaInitAuditPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEle;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEleDetail;
import cn.labsoft.labos.source.quality.entity.LabQuaInitAuditPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaAuditPlanEleService;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditPlanEleDetailVo;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditPlanEleVo;
import cn.labsoft.labos.utils.StrUtils;


@Service("labQuaAuditPlanEleService")
public class LabQuaAuditPlanEleServiceImpl extends BaseService implements ILabQuaAuditPlanEleService{
	
	private ILabQuaAuditPlanEleDAO labQuaAuditPlanEleDAO;
	private ILabQuaInitAuditPlanDAO labQuaInitAuditPlanDAO;
	private ILabQuaAuditPlanEleDetailDAO labQuaAuditPlanEleDetailDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	
	@Resource
	public void setLabQuaAuditPlanEleDAO(
			ILabQuaAuditPlanEleDAO labQuaAuditPlanEleDAO) {
		this.labQuaAuditPlanEleDAO = labQuaAuditPlanEleDAO;
	}
	@Resource
	public void setLabQuaInitAuditPlanDAO(
			ILabQuaInitAuditPlanDAO labQuaInitAuditPlanDAO) {
		this.labQuaInitAuditPlanDAO = labQuaInitAuditPlanDAO;
	}
	@Resource
	public void setLabQuaAuditPlanEleDetailDAO(
			ILabQuaAuditPlanEleDetailDAO labQuaAuditPlanEleDetailDAO) {
		this.labQuaAuditPlanEleDetailDAO = labQuaAuditPlanEleDetailDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Override
	public boolean update2DelLabQuaAuditPlanEle(String[] ids)throws GlobalException{
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaAuditPlanEle labQuaAuditPlanEle=labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(id);
				labQuaAuditPlanEle.setIsDel(Constants_Source.Y);
				labQuaAuditPlanEleDAO.updateLabQuaAuditPlanEle(labQuaAuditPlanEle);
				labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(id);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaAuditPlanEleVo> getLabQuaAuditPlanEleList(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo) throws GlobalException {
		 String hql="FROM LabQuaAuditPlanEle WHERE 1=1 AND isDel= '"+Constants_Source.N+"' ";
		 if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getAuditPeopleSearch())){
			hql +=" AND auditPeople like '%"+labQuaAuditPlanEleVo.getAuditPeopleSearch()+"%' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getOrgIdSearch())){
			hql +=" AND org.id = '"+labQuaAuditPlanEleVo.getOrgIdSearch()+"' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getCreatePeople())){
			hql +=" AND createPeople LIKE '%"+labQuaAuditPlanEleVo.getCreatePeople()+"%' ";
		}
		 List<LabQuaAuditPlanEle>  labQuaAuditPlanEleList=labQuaAuditPlanEleDAO.getLabQuaAuditPlanEleByHQL(hql);
		 List<LabQuaAuditPlanEleVo> labQuaAuditPlanEleVoList=new ArrayList<LabQuaAuditPlanEleVo>();
		 if (null!=labQuaAuditPlanEleList && labQuaAuditPlanEleList.size()>0) {
				for (LabQuaAuditPlanEle labQuaAuditPlanEle : labQuaAuditPlanEleList) {
					LabQuaAuditPlanEleVo labQuaAuditPlanEleVoOne=new LabQuaAuditPlanEleVo();
					BeanUtils.copyProperties(labQuaAuditPlanEle, labQuaAuditPlanEleVoOne, new String[]{"isDel"});
					if(null != labQuaAuditPlanEle.getOrg()){
						labQuaAuditPlanEleVoOne.setOrgId(labQuaAuditPlanEle.getOrg().getId());
						labQuaAuditPlanEleVoOne.setOrgName(labQuaAuditPlanEle.getOrg().getName());
					}
					labQuaAuditPlanEleVoList.add(labQuaAuditPlanEleVoOne);
				}
			}
		return labQuaAuditPlanEleVoList;
	}

	@Override
	public PageResult getLabQuaAuditPlanElePR(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo,PageResult pageResult)
			throws GlobalException {
			String hql="FROM LabQuaAuditPlanEle WHERE 1=1 AND isDel= '"+Constants_Source.N+"'";
			if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getAuditPeopleSearch())){
				hql +=" AND auditPeople like '%"+labQuaAuditPlanEleVo.getAuditPeopleSearch()+"%' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getOrgIdSearch())){
				hql +=" AND org.id = '"+labQuaAuditPlanEleVo.getOrgIdSearch()+"' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getAccStatus())){
				hql +=" AND accStatus = '"+labQuaAuditPlanEleVo.getAccStatus()+"' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getCreatePeople())){
				hql +=" AND createPeople LIKE '%"+labQuaAuditPlanEleVo.getCreatePeople()+"%' ";
			}
			pageResult=labQuaAuditPlanEleDAO.getLabQuaAuditPlanElePR(hql, pageResult);
			List<LabQuaAuditPlanEle> labQuaAuditPlanEleList=pageResult.getResultList();
			List<LabQuaAuditPlanEleVo> resultList=new ArrayList<LabQuaAuditPlanEleVo>();
			if (null!=labQuaAuditPlanEleList && labQuaAuditPlanEleList.size()>0) {
				for (LabQuaAuditPlanEle labQuaAuditPlanEle : labQuaAuditPlanEleList) {
					labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
					BeanUtils.copyProperties(labQuaAuditPlanEle, labQuaAuditPlanEleVo, new String[]{"isDel"});
					if(null != labQuaAuditPlanEle.getOrg()){
						labQuaAuditPlanEleVo.setOrgId(labQuaAuditPlanEle.getOrg().getId());
						labQuaAuditPlanEleVo.setOrgName(labQuaAuditPlanEle.getOrg().getName());
					}
					resultList.add(labQuaAuditPlanEleVo);
				} 
				pageResult.setResultList(resultList);
			}
			return pageResult;
		
	}
	@Override
	public LabQuaAuditPlanEleVo getLabQuaAuditPlanEleVo(String id)
			throws GlobalException {
		LabQuaAuditPlanEle labQuaAuditPlanEle=labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(id);
		LabQuaAuditPlanEleVo labQuaAuditPlanEleVo=new LabQuaAuditPlanEleVo();
		BeanUtils.copyProperties(labQuaAuditPlanEle, labQuaAuditPlanEleVo, new String[]{"isDel"});
		if(null != labQuaAuditPlanEle.getOrg()){
			labQuaAuditPlanEleVo.setOrgId(labQuaAuditPlanEle.getOrg().getId());
			labQuaAuditPlanEleVo.setOrgName(labQuaAuditPlanEle.getOrg().getName());
		}
		BeanUtils.copyProperties(labQuaAuditPlanEle, labQuaAuditPlanEleVo, new String[]{"isDel"});
		if (null!=labQuaAuditPlanEle) {
			String hql="FROM LabQuaAuditPlanEleDetail WHERE 1=1 AND quaAuditPlanEle.id='"+labQuaAuditPlanEle.getId()+"' ";
			List<LabQuaAuditPlanEleDetail> labQuaAuditPlanEleDetailList=labQuaAuditPlanEleDetailDAO.getLabQuaAuditPlanEleDetailByHQL(hql);
			List<LabQuaAuditPlanEleDetailVo> labQuaAuditPlanEleDetailVoList=new ArrayList<LabQuaAuditPlanEleDetailVo>();
			if (null!=labQuaAuditPlanEleDetailList && labQuaAuditPlanEleDetailList.size()>0) {
				for (LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail : labQuaAuditPlanEleDetailList) {
					LabQuaAuditPlanEleDetailVo labQuaAuditPlanEleDetailVo = new LabQuaAuditPlanEleDetailVo();
					BeanUtils.copyProperties(labQuaAuditPlanEleDetail, labQuaAuditPlanEleDetailVo,new String []{""});
					if(null != labQuaAuditPlanEleDetail.getQuaAuditPlanEle()){
						labQuaAuditPlanEleDetailVo.setQuaAuditPlanEleId(labQuaAuditPlanEleDetail.getQuaAuditPlanEle().getId());
					}
					if(null != labQuaAuditPlanEleDetail.getQuaInitAuditPlan()){
						labQuaAuditPlanEleDetailVo.setQuaInitAuditPlanId(labQuaAuditPlanEleDetail.getQuaInitAuditPlan().getId());
						labQuaAuditPlanEleDetailVo.setQuaInitAuditPlanName(labQuaAuditPlanEleDetail.getQuaInitAuditPlan().getName());
						
					}
					labQuaAuditPlanEleDetailVoList.add(labQuaAuditPlanEleDetailVo);
				}
			}
			
			labQuaAuditPlanEleVo.setLabQuaAuditPlanEleDetailVoList(labQuaAuditPlanEleDetailVoList);
			
		} 
		return labQuaAuditPlanEleVo;
	}

	@Override
	public LabQuaAuditPlanEleVo addLabQuaAuditPlanEle(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo)
			throws GlobalException {
		SessionContainer sessionContainer = (SessionContainer) ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
			LabQuaAuditPlanEle labQuaAuditPlanEle=new LabQuaAuditPlanEle();
			BeanUtils.copyProperties(labQuaAuditPlanEleVo, labQuaAuditPlanEle, new String[]{""});
			if(!StrUtils.isBlankOrNull(labQuaAuditPlanEleVo.getOrgId())){
				LabOrg labOrg = (LabOrg)labQuaAuditPlanEleDAO.findById(LabOrg.class, labQuaAuditPlanEleVo.getOrgId());
				labQuaAuditPlanEle.setOrg(labOrg);
			}
			labQuaAuditPlanEle.setStatus("00");
			labQuaAuditPlanEle.setAccStatus("0");
			labQuaAuditPlanEle.setCreateUserId(sessionContainer.getUserId());
			labQuaAuditPlanEleDAO.addLabQuaAuditPlanEle(labQuaAuditPlanEle);
			List<LabQuaAuditPlanEleDetailVo>  labQuaAuditPlanEleDetailVoList=labQuaAuditPlanEleVo.getLabQuaAuditPlanEleDetailVoList();
			if(null!=labQuaAuditPlanEleDetailVoList&&labQuaAuditPlanEleDetailVoList.size()>0){
				for(LabQuaAuditPlanEleDetailVo labQuaAuditPlanEleDetailVo:labQuaAuditPlanEleDetailVoList){
					LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail=new LabQuaAuditPlanEleDetail();
					labQuaAuditPlanEleDetail.setRuleNum(labQuaAuditPlanEleDetailVo.getRuleNum());
					labQuaAuditPlanEleDetail.setMonth(labQuaAuditPlanEleDetailVo.getMonth());
					LabQuaInitAuditPlan labQuaInitAuditPlan=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditPlanEleDetailVo.getQuaInitAuditPlanId());
					labQuaAuditPlanEleDetail.setQuaInitAuditPlan(labQuaInitAuditPlan);
					labQuaAuditPlanEleDetail.setQuaAuditPlanEle(labQuaAuditPlanEle);
					labQuaAuditPlanEleDetail.setGroupLeader(labQuaAuditPlanEleDetailVo.getGroupLeader());
					labQuaAuditPlanEleDetail.setHelpPeople(labQuaAuditPlanEleDetailVo.getHelpPeople());
					labQuaAuditPlanEleDetailDAO.addLabQuaAuditPlanEleDetail(labQuaAuditPlanEleDetail);
				}
			}
			labQuaAuditPlanEleVo.setId(labQuaAuditPlanEle.getId());
		return labQuaAuditPlanEleVo;
	}

	@Override
	public boolean updateLabQuaAuditPlanEle(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo)
			throws GlobalException {
		if(null!=labQuaAuditPlanEleVo){
			LabQuaAuditPlanEle labQuaAuditPlanEle=labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(labQuaAuditPlanEleVo.getId());
			if(null!=labQuaAuditPlanEle){
			BeanUtils.copyProperties(labQuaAuditPlanEleVo, labQuaAuditPlanEle, new String[] {"isDel","tenantId","createUserId","accStatus"});
			labQuaAuditPlanEle.setStatus("00");
			LabOrg labOrg=(LabOrg)labQuaAuditPlanEleDAO.findById(LabOrg.class, labQuaAuditPlanEleVo.getOrgId());
			labQuaAuditPlanEle.setOrg(labOrg);
			labQuaAuditPlanEleDAO.updateLabQuaAuditPlanEle(labQuaAuditPlanEle);
			String hql = "FROM LabQuaAuditPlanEleDetail WHERE quaAuditPlanEle.id= '"+labQuaAuditPlanEle.getId()+"'";
			List<LabQuaAuditPlanEleDetail> labQuaAuditPlanEleDetailList = labQuaAuditPlanEleDetailDAO.find(hql);
			List<LabQuaAuditPlanEleDetailVo>  labQuaAuditPlanEleDetailVoList=labQuaAuditPlanEleVo.getLabQuaAuditPlanEleDetailVoList();
			if(null!=labQuaAuditPlanEleDetailVoList&&labQuaAuditPlanEleDetailVoList.size()>0){
				for(LabQuaAuditPlanEleDetailVo labQuaAuditPlanEleDetailVo:labQuaAuditPlanEleDetailVoList){
					LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail=labQuaAuditPlanEleDetailDAO.getLabQuaAuditPlanEleDetail(labQuaAuditPlanEleDetailVo.getId());
					labQuaAuditPlanEleDetail.setRuleNum(labQuaAuditPlanEleDetailVo.getRuleNum());
					labQuaAuditPlanEleDetail.setMonth(labQuaAuditPlanEleDetailVo.getMonth());
					labQuaAuditPlanEleDetail.setQuaAuditPlanEle(labQuaAuditPlanEle);
					labQuaAuditPlanEleDetail.setGroupLeader(labQuaAuditPlanEleDetailVo.getGroupLeader());
					labQuaAuditPlanEleDetail.setHelpPeople(labQuaAuditPlanEleDetailVo.getHelpPeople());
					labQuaAuditPlanEleDetailDAO.updateLabQuaAuditPlanEleDetail(labQuaAuditPlanEleDetail);
					}
				}
			}
		}
		return true;
	}
}
