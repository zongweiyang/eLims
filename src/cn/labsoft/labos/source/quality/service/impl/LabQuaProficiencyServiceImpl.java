package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.dao.ILabOrgDAO;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaProficiencyDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaProficiencyPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiency;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiencyPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaProficiencyService;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyVo;
import cn.labsoft.labos.utils.StrUtils;



@Service("labQuaProficiencyService")
public class LabQuaProficiencyServiceImpl extends BaseService implements ILabQuaProficiencyService{
	
	private ILabQuaProficiencyDAO labQuaProficiencyDAO;
	private ILabQuaProficiencyPlanDAO labQuaProficiencyPlanDAO;
	private ILabOrgDAO labOrgDAO;

	@Resource
	public void setLabQuaProficiencyDAO(ILabQuaProficiencyDAO labQuaProficiencyDAO) {
		this.labQuaProficiencyDAO = labQuaProficiencyDAO;
	}
	@Resource
	public void setLabQuaProficiencyPlanDAO(
			ILabQuaProficiencyPlanDAO labQuaProficiencyPlanDAO) {
		this.labQuaProficiencyPlanDAO = labQuaProficiencyPlanDAO;
	}
	@Resource
	public void setLabOrgDAO(ILabOrgDAO labOrgDAO) {
		this.labOrgDAO = labOrgDAO;
	}

	@Override
	public boolean update2DelLabQuaProficiency(String[] ids) throws GlobalException {
		if(ids.length > 0){
			for(String id : ids ){
				LabQuaProficiency labQuaProficiency=(LabQuaProficiency) labQuaProficiencyDAO.findById(LabQuaProficiency.class, id);
				labQuaProficiency.setIsDel(Constants_Source.Y);
				labQuaProficiencyDAO.deleteLabQuaProficiency(labQuaProficiency);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaProficiencyVo> getLabQuaProficiencyList(
			LabQuaProficiencyVo labQuaProficiencyVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = " FROM LabQuaProficiency WHERE isDel= '"+Constants_Source.N+"' ";
		if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getOrgSearch())){
			hql += " AND org.id = '"+labQuaProficiencyVo.getOrgSearch()+"'  ";
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getProficiencyPlanId())){
			hql += " AND labQuaProficiencyPlan.id = '"+labQuaProficiencyVo.getProficiencyPlanId()+"' ";
		}
		List<LabQuaProficiency> labQuaProficiencyList=labQuaProficiencyDAO.getLabQuaProficiencyByHQL(hql);
		List<LabQuaProficiencyVo> labQuaProficiencyVoList=new ArrayList<LabQuaProficiencyVo>();
		if (null!=labQuaProficiencyList && labQuaProficiencyList.size()>0) {
			for (LabQuaProficiency labQuaProficiency : labQuaProficiencyList) {
				LabQuaProficiencyVo labQuaProficiencyVoOne=new LabQuaProficiencyVo();
				BeanUtils.copyProperties(labQuaProficiency, labQuaProficiencyVoOne, new String[]{"isDel"});
				if(null != labQuaProficiency.getOrg()){
					labQuaProficiencyVoOne.setOrgId(labQuaProficiency.getOrg().getId());
					labQuaProficiencyVoOne.setOrgName(labQuaProficiency.getOrg().getName());
				}
				if(null != labQuaProficiency.getLabQuaProficiencyPlan()){
					labQuaProficiencyVoOne.setProficiencyPlanId(labQuaProficiency.getLabQuaProficiencyPlan().getId());
					labQuaProficiencyVoOne.setProficiencyPlanName(labQuaProficiency.getLabQuaProficiencyPlan().getName());
				}
				labQuaProficiencyVoList.add(labQuaProficiencyVoOne);
			} 
		}
		return labQuaProficiencyVoList;
	}

	@Override
	public PageResult getLabQuaProficiencyPR(LabQuaProficiencyVo labQuaProficiencyVo, PageResult pageResult)
			throws GlobalException {
			String hql = " FROM LabQuaProficiency WHERE isDel= '"+Constants_Source.N+"'";
			if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getOrgSearch())){
				hql += " AND org.id = '"+labQuaProficiencyVo.getOrgSearch()+"'";
			}
			if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getProficiencyPlanId())){
				hql += " AND labQuaProficiencyPlan.id = '"+labQuaProficiencyVo.getProficiencyPlanId()+"' ";
			}
 			pageResult=labQuaProficiencyDAO.getLabQuaProficiencyPR(hql, pageResult);
			List<LabQuaProficiency> labQuaProficiencyList=pageResult.getResultList();
			List<LabQuaProficiencyVo> labQuaProficiencyVoList=new ArrayList<LabQuaProficiencyVo>();
			if (null!=labQuaProficiencyList && labQuaProficiencyList.size()>0) {
				for (LabQuaProficiency labQuaProficiency : labQuaProficiencyList) {
					LabQuaProficiencyVo labQuaProficiencyVoOne=new LabQuaProficiencyVo();
					BeanUtils.copyProperties(labQuaProficiency, labQuaProficiencyVoOne, new String[]{"isDel"});
					if(null != labQuaProficiency.getOrg()){
						labQuaProficiencyVoOne.setOrgId(labQuaProficiency.getOrg().getId());
						labQuaProficiencyVoOne.setOrgName(labQuaProficiency.getOrg().getName());
					}
					if(null != labQuaProficiency.getLabQuaProficiencyPlan()){
						labQuaProficiencyVoOne.setProficiencyPlanId(labQuaProficiency.getLabQuaProficiencyPlan().getId());
						labQuaProficiencyVoOne.setProficiencyPlanName(labQuaProficiency.getLabQuaProficiencyPlan().getName());
					}
					labQuaProficiencyVoList.add(labQuaProficiencyVoOne);
				} 
				pageResult.setResultList(labQuaProficiencyVoList);
			}
			return pageResult;
	}

	
	@Override
	public LabQuaProficiencyVo getLabQuaProficiency(String id) throws GlobalException {
		LabQuaProficiency labQuaProficiency=labQuaProficiencyDAO.getLabQuaProficiency(id);
		LabQuaProficiencyVo labQuaProficiencyVo=new LabQuaProficiencyVo();
		BeanUtils.copyProperties(labQuaProficiency, labQuaProficiencyVo, new String[]{"isDel"});
		if(null != labQuaProficiency.getOrg()){
			labQuaProficiencyVo.setOrgId(labQuaProficiency.getOrg().getId());
			labQuaProficiencyVo.setOrgName(labQuaProficiency.getOrg().getName());
		}
		if(null != labQuaProficiency.getLabQuaProficiencyPlan()){
			labQuaProficiencyVo.setProficiencyPlanId(labQuaProficiency.getLabQuaProficiencyPlan().getId());
			labQuaProficiencyVo.setProficiencyPlanName(labQuaProficiency.getLabQuaProficiencyPlan().getName());
		}
		return labQuaProficiencyVo;
		
	}

	@Override
	public LabQuaProficiencyVo addLabQuaProficiency(LabQuaProficiencyVo labQuaProficiencyVo)
			throws GlobalException {
		LabQuaProficiency labQuaProficiency=new LabQuaProficiency();
		BeanUtils.copyProperties(labQuaProficiencyVo, labQuaProficiency, new String[]{"isDel"});
		labQuaProficiency.setIsDel(Constants_Source.N);
		if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getOrgId())){
			LabOrg labOrg = labOrgDAO.getLabOrg(labQuaProficiencyVo.getOrgId());
			labQuaProficiency.setOrg(labOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getProficiencyPlanId())){
			LabQuaProficiencyPlan labQuaProficiencyPlan = labQuaProficiencyPlanDAO.getLabQuaProficiencyPlan(labQuaProficiencyVo.getProficiencyPlanId());
			labQuaProficiency.setLabQuaProficiencyPlan(labQuaProficiencyPlan);
			labQuaProficiencyPlan.setStatus("10");
			labQuaProficiencyPlanDAO.updateLabQuaProficiencyPlan(labQuaProficiencyPlan);
		}
		labQuaProficiencyDAO.addLabQuaProficiency(labQuaProficiency);
		labQuaProficiencyVo.setId(labQuaProficiency.getId());
		return labQuaProficiencyVo;
	}

	@Override
	public boolean updateLabQuaProficiency(LabQuaProficiencyVo labQuaProficiencyVo)
			throws GlobalException {
		LabQuaProficiency labQuaProficiency=labQuaProficiencyDAO.getLabQuaProficiency(labQuaProficiencyVo.getId());
		BeanUtils.copyProperties(labQuaProficiencyVo, labQuaProficiency, new String[] {"isDel","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getOrgId())){
			LabOrg labOrg = labOrgDAO.getLabOrg(labQuaProficiencyVo.getOrgId());
			labQuaProficiency.setOrg(labOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaProficiencyVo.getProficiencyPlanId())){
			LabQuaProficiencyPlan labQuaProficiencyPlan = labQuaProficiencyPlanDAO.getLabQuaProficiencyPlan(labQuaProficiencyVo.getProficiencyPlanId());
			labQuaProficiency.setLabQuaProficiencyPlan(labQuaProficiencyPlan);
		}
		labQuaProficiencyDAO.updateLabQuaProficiency(labQuaProficiency);
		return true;
	}
} 
