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
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.source.quality.dao.ILabQuaCusVisitDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaCusVisit;
import cn.labsoft.labos.source.quality.service.ILabQuaCusVisitService;
import cn.labsoft.labos.source.quality.vo.LabQuaCusVisitVo;
import cn.labsoft.labos.utils.StrUtils;



@Service("labQuaCusVisitService")
public class LabQuaCusVisitServiceImpl extends BaseService implements ILabQuaCusVisitService{
	
	public  ExecutorService poolSer = Executors.newSingleThreadExecutor();
	private ILabQuaCusVisitDAO labQuaCusVisitDAO;
	private ILabNumberDAO labNumberDAO;
	
	
	@Resource
	public void setLabQuaCusVisitDAO(ILabQuaCusVisitDAO labQuaCusVisitDAO) {
		this.labQuaCusVisitDAO = labQuaCusVisitDAO;
	}
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}

	@Override
	public boolean update2DelLabQuaCusVisit(String[] ids) throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaCusVisit labQuaCusVisit=labQuaCusVisitDAO.getLabQuaCusVisit(id);
				labQuaCusVisit.setIsDel(Constants_Source.Y);
				labQuaCusVisitDAO.updateLabQuaCusVisit(labQuaCusVisit);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaCusVisitVo> getLabQuaCusVisitList(
			LabQuaCusVisitVo labQuaCusVisitVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaCusVisit WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getCusUnitSearch())){
			hql += " AND labCustomer.name like '%"+labQuaCusVisitVo.getCusUnitSearch()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getNameSearch())){
			hql += " AND name like '%"+labQuaCusVisitVo.getNameSearch()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getUnitOrgId())){
			hql += " AND unitOrg.id = '"+labQuaCusVisitVo.getUnitOrgId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getLabOrgId())){
			hql += " AND labOrg.id = '"+labQuaCusVisitVo.getLabOrgId()+"' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getAccStatus())){
			hql += " AND accStatus = '"+labQuaCusVisitVo.getAccStatus()+"' ";
		}
		List<LabQuaCusVisit> labQuaCusVisitList=labQuaCusVisitDAO.getLabQuaCusVisitByHQL(hql);
		List<LabQuaCusVisitVo> labQuaCusVisitVoList=new ArrayList<LabQuaCusVisitVo>();
		if (null!=labQuaCusVisitList && labQuaCusVisitList.size()>0) {
			for (LabQuaCusVisit labQuaCusVisit : labQuaCusVisitList) {
				LabQuaCusVisitVo labQuaCusVisitVoOne=new LabQuaCusVisitVo();
				BeanUtils.copyProperties(labQuaCusVisit, labQuaCusVisitVo, new String[]{"isDel"});
				if(null != labQuaCusVisit.getUnitOrg()){
					labQuaCusVisitVoOne.setUnitOrgId(labQuaCusVisit.getUnitOrg().getId());
					labQuaCusVisitVoOne.setUnitOrgName(labQuaCusVisit.getUnitOrg().getName());
				}
				if(null != labQuaCusVisit.getLabOrg()){
					labQuaCusVisitVoOne.setLabOrgId(labQuaCusVisit.getLabOrg().getId());
					labQuaCusVisitVoOne.setLabOrgName(labQuaCusVisit.getLabOrg().getName());
				}
				if(null != labQuaCusVisit.getLabCustomer()){
					labQuaCusVisitVoOne.setCusUnitId(labQuaCusVisit.getLabCustomer().getId());
					labQuaCusVisitVoOne.setCusUnit(labQuaCusVisit.getLabCustomer().getName());
				}
				labQuaCusVisitVoList.add(labQuaCusVisitVo);
			} 
		}
		return labQuaCusVisitVoList;
	}

	@Override
	public PageResult getLabQuaCusVisitPR(LabQuaCusVisitVo labQuaCusVisitVo, PageResult pageResult)
			throws GlobalException {
			String hql = "FROM LabQuaCusVisit WHERE isDel= '"+Constants_Source.N+"'";
			if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getCusUnitSearch())){
				hql += " AND labCustomer.name like '%"+labQuaCusVisitVo.getCusUnitSearch()+"%'";
			}
			if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getNameSearch())){
				hql += " AND name like '%"+labQuaCusVisitVo.getNameSearch()+"%'";
			}
			if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getUnitOrgIdSearch())){
				hql += " AND unitOrg.id = '"+labQuaCusVisitVo.getUnitOrgIdSearch()+"'";
			}
			if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getLabOrgIdSearch())){
				hql += " AND labOrg.id = '"+labQuaCusVisitVo.getLabOrgIdSearch()+"' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getAccStatus())){
				hql += " AND accStatus = '"+labQuaCusVisitVo.getAccStatus()+"' ";
			}
			pageResult=labQuaCusVisitDAO.getLabQuaCusVisitPR(hql, pageResult);
			List<LabQuaCusVisit> labQuaCusVisitList=pageResult.getResultList();
			List<LabQuaCusVisitVo> labQuaCusVisitVoList=new ArrayList<LabQuaCusVisitVo>();
			if (null!=labQuaCusVisitList && labQuaCusVisitList.size()>0) {
				for (LabQuaCusVisit labQuaCusVisit : labQuaCusVisitList) {
					LabQuaCusVisitVo labQuaCusVisitVoOne=new LabQuaCusVisitVo();
					BeanUtils.copyProperties(labQuaCusVisit, labQuaCusVisitVoOne, new String[]{"isDel"});
					if(null != labQuaCusVisit.getUnitOrg()){
						labQuaCusVisitVoOne.setUnitOrgId(labQuaCusVisit.getUnitOrg().getId());
						labQuaCusVisitVoOne.setUnitOrgName(labQuaCusVisit.getUnitOrg().getName());
					}
					if(null != labQuaCusVisit.getLabOrg()){
						labQuaCusVisitVoOne.setLabOrgId(labQuaCusVisit.getLabOrg().getId());
						labQuaCusVisitVoOne.setLabOrgName(labQuaCusVisit.getLabOrg().getName());
					}
					if(null != labQuaCusVisit.getLabCustomer()){
						labQuaCusVisitVoOne.setCusUnitId(labQuaCusVisit.getLabCustomer().getId());
						labQuaCusVisitVoOne.setCusUnit(labQuaCusVisit.getLabCustomer().getName());
					}
					labQuaCusVisitVoList.add(labQuaCusVisitVoOne);
				} 
				pageResult.setResultList(labQuaCusVisitVoList);
			}
			return pageResult;
		
	}

	@Override
	public LabQuaCusVisitVo getLabQuaCusVisit(String id)
			throws GlobalException {
		LabQuaCusVisit labQuaCusVisit=labQuaCusVisitDAO.getLabQuaCusVisit(id);
		LabQuaCusVisitVo labQuaCusVisitVo=new LabQuaCusVisitVo();
		BeanUtils.copyProperties(labQuaCusVisit, labQuaCusVisitVo, new String[]{"isDel"});
		if(null != labQuaCusVisit.getUnitOrg()){
			labQuaCusVisitVo.setUnitOrgId(labQuaCusVisit.getUnitOrg().getId());
			labQuaCusVisitVo.setUnitOrgName(labQuaCusVisit.getUnitOrg().getName());
		}
		if(null != labQuaCusVisit.getLabOrg()){
			labQuaCusVisitVo.setLabOrgId(labQuaCusVisit.getLabOrg().getId());
			labQuaCusVisitVo.setLabOrgName(labQuaCusVisit.getLabOrg().getName());
		}
		if(null != labQuaCusVisit.getLabCustomer()){
			labQuaCusVisitVo.setCusUnitId(labQuaCusVisit.getLabCustomer().getId());
			labQuaCusVisitVo.setCusUnit(labQuaCusVisit.getLabCustomer().getName());
		}
		return labQuaCusVisitVo;
		
	}

	@Override
	public LabQuaCusVisitVo addLabQuaCusVisit(LabQuaCusVisitVo labQuaCusVisitVo)
			throws GlobalException {
		LabQuaCusVisit labQuaCusVisit=new LabQuaCusVisit();
		BeanUtils.copyProperties(labQuaCusVisitVo, labQuaCusVisit, new String[] {"isDel"});
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getUnitOrgId())){
			LabOrg unitOrg=(LabOrg)labQuaCusVisitDAO.findById(LabOrg.class, labQuaCusVisitVo.getUnitOrgId());
			labQuaCusVisit.setUnitOrg(unitOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getLabOrgId())){
			LabOrg labOrg=(LabOrg)labQuaCusVisitDAO.findById(LabOrg.class, labQuaCusVisitVo.getLabOrgId());
			labQuaCusVisit.setLabOrg(labOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getCusUnitId())){
			LabCustomer labCustomer=(LabCustomer)labQuaCusVisitDAO.findById(LabCustomer.class, labQuaCusVisitVo.getCusUnitId());
			labQuaCusVisit.setLabCustomer(labCustomer);
		}
		ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_KHHF,new String[]{""},Constants_Source.CODE_USE_RUN);
		try {
			labQuaCusVisit.setNo(poolSer.submit(threadNumber).get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		labQuaCusVisit.setAccStatus("0");
		labQuaCusVisitDAO.addLabQuaCusVisit(labQuaCusVisit);
		labQuaCusVisitVo.setId(labQuaCusVisit.getId());
		return labQuaCusVisitVo;
	}

	@Override
	public boolean updateLabQuaCusVisit(LabQuaCusVisitVo labQuaCusVisitVo)
			throws GlobalException {
		LabQuaCusVisit labQuaCusVisit=labQuaCusVisitDAO.getLabQuaCusVisit(labQuaCusVisitVo.getId());
		BeanUtils.copyProperties(labQuaCusVisitVo, labQuaCusVisit, new String[] {"isDel","createTime","tenantId","createUserId","accStatus"});
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getUnitOrgId())){
			LabOrg unitOrg=(LabOrg)labQuaCusVisitDAO.findById(LabOrg.class, labQuaCusVisitVo.getUnitOrgId());
			labQuaCusVisit.setUnitOrg(unitOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getLabOrgId())){
			LabOrg labOrg=(LabOrg)labQuaCusVisitDAO.findById(LabOrg.class, labQuaCusVisitVo.getLabOrgId());
			labQuaCusVisit.setLabOrg(labOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaCusVisitVo.getCusUnitId())){
			LabCustomer labCustomer=(LabCustomer)labQuaCusVisitDAO.findById(LabCustomer.class, labQuaCusVisitVo.getCusUnitId());
			labQuaCusVisit.setLabCustomer(labCustomer);
		}
		if(StrUtils.isBlankOrNull(labQuaCusVisitVo.getNo())){
			ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_KHHF,new String[]{""},Constants_Source.CODE_USE_RUN);
			try {
				labQuaCusVisit.setNo(poolSer.submit(threadNumber).get().toString());
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
		labQuaCusVisitDAO.updateLabQuaCusVisit(labQuaCusVisit);
		return true;
	}
}
