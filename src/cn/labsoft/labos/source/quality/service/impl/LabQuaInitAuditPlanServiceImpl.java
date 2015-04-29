package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDetailDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaInitAuditPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEleDetail;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecord;
import cn.labsoft.labos.source.quality.entity.LabQuaInitAuditPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaInitAuditPlanService;
import cn.labsoft.labos.source.quality.vo.LabQuaInitAuditPlanVo;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;



@Service("labQuaInitAuditPlanService")
public class LabQuaInitAuditPlanServiceImpl extends BaseService implements ILabQuaInitAuditPlanService{
	
	private ILabQuaInitAuditPlanDAO labQuaInitAuditPlanDAO;
	private ILabQuaAuditPlanEleDetailDAO labQuaAuditPlanEleDetailDAO;
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

	@Override
	public boolean update2DelLabQuaInitAuditPlan(String[] ids)throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaInitAuditPlan labQuaInitAuditPlan=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(id);
				labQuaInitAuditPlan.setIsDel(Constants_Source.Y);
				labQuaInitAuditPlanDAO.updateLabQuaInitAuditPlan(labQuaInitAuditPlan);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaInitAuditPlanVo> getLabQuaInitAuditPlanList(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo)throws GlobalException{
		// TODO Auto-generated method stub
		 String hql="FROM LabQuaInitAuditPlan T WHERE 1=1 AND isDel= '"+Constants_Source.N+"' AND rank='"+Constants_Source.TRUE+"' ";
		 List<LabQuaInitAuditPlan>  labQuaInitAuditPlanList=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlanByHQL(hql);
		 List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList=new ArrayList<LabQuaInitAuditPlanVo>();
		 if (null!=labQuaInitAuditPlanList && labQuaInitAuditPlanList.size()>0) {
				for (LabQuaInitAuditPlan labQuaInitAuditPlan : labQuaInitAuditPlanList) {
					labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
					BeanUtils.copyProperties(labQuaInitAuditPlan, labQuaInitAuditPlanVo, new String[]{"isDel","createTime"});
					labQuaInitAuditPlanVoList.add(labQuaInitAuditPlanVo);
				}
			}
		return labQuaInitAuditPlanVoList;
	}

	@Override
	public PageResult getLabQuaInitAuditPlanPR(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo, PageResult pageResult)
			throws GlobalException {
		 String sql="FROM LabQuaInitAuditPlan  WHERE 1=1 AND isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaInitAuditPlanVo.getSearchName())){
			sql += " AND name LIKE '%"+labQuaInitAuditPlanVo.getSearchName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaInitAuditPlanVo.getRank())){
			sql += " AND rank = '"+labQuaInitAuditPlanVo.getRank()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaInitAuditPlanVo.getParentId())){
			sql += " AND quaInitAuditPlan.id = '"+labQuaInitAuditPlanVo.getParentId()+"'";
		}
		 pageResult=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlanPR(sql, pageResult);
			List<LabQuaInitAuditPlan> labQuaInitAuditPlanList=pageResult.getResultList();
			List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList=new ArrayList<LabQuaInitAuditPlanVo>();
			if (null!=labQuaInitAuditPlanList && labQuaInitAuditPlanList.size()>0) {
				for (LabQuaInitAuditPlan labQuaInitAuditPlan : labQuaInitAuditPlanList) {
					LabQuaInitAuditPlanVo labQuaInitAuditPlanVoOne=new LabQuaInitAuditPlanVo();
					BeanUtils.copyProperties(labQuaInitAuditPlan, labQuaInitAuditPlanVoOne, new String[]{"isDel"});
					labQuaInitAuditPlanVoOne.setSearchName(labQuaInitAuditPlan.getName());
					labQuaInitAuditPlanVoList.add(labQuaInitAuditPlanVoOne);
				} 
				pageResult.setResultList(labQuaInitAuditPlanVoList);
			}
			return pageResult;
	}

	@Override
	public LabQuaInitAuditPlanVo getLabQuaInitAuditPlanVo(String id)
			throws GlobalException {
		LabQuaInitAuditPlan labQuaInitAuditPlan=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(id);
		LabQuaInitAuditPlanVo labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
		BeanUtils.copyProperties(labQuaInitAuditPlan, labQuaInitAuditPlanVo, new String[]{"isDel"});
		if(null != labQuaInitAuditPlan.getQuaInitAuditPlan()){
			labQuaInitAuditPlanVo.setParentId(labQuaInitAuditPlan.getQuaInitAuditPlan().getId());
		}
		return labQuaInitAuditPlanVo;
		
	}

	@Override
	public LabQuaInitAuditPlanVo addLabQuaInitAuditPlan(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo)
			throws GlobalException {
		LabQuaInitAuditPlan labQuaInitAuditPlan=new LabQuaInitAuditPlan();
		BeanUtils.copyProperties(labQuaInitAuditPlanVo, labQuaInitAuditPlan, new String[] {"isDel"});
		if(!StrUtils.isBlankOrNull(labQuaInitAuditPlanVo.getParentId())){
			labQuaInitAuditPlan.setQuaInitAuditPlan(labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaInitAuditPlanVo.getParentId()));
		}
		labQuaInitAuditPlanDAO.addLabQuaInitAuditPlan(labQuaInitAuditPlan);
		return labQuaInitAuditPlanVo;
	}

	@Override
	public boolean updateLabQuaInitAuditPlan(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo)
			throws GlobalException {
		LabQuaInitAuditPlan labQuaInitAuditPlan=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaInitAuditPlanVo.getId());
		BeanUtils.copyProperties(labQuaInitAuditPlanVo, labQuaInitAuditPlan, new String[] {"isDel","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labQuaInitAuditPlanVo.getParentId())){
			labQuaInitAuditPlan.setQuaInitAuditPlan(labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaInitAuditPlanVo.getParentId()));
		}
		labQuaInitAuditPlanDAO.updateLabQuaInitAuditPlan(labQuaInitAuditPlan);
		return true;
	}

	@Override
	public List<LabQuaInitAuditPlanVo> getLabQuaInitAuditPlanList(String planId,
			String month) throws GlobalException {
		List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVoList=new ArrayList<LabQuaInitAuditPlanVo>();
		if(!StrUtils.isBlankOrNull(planId)){
		String hql="FROM LabQuaAuditPlanEleDetail WHERE 1=1  AND quaAuditPlanEle.id='"+planId+"'";
		String sql = "FROM LabQuaAuditRecord WHERE month = '"+month+"' AND labQuaAuditPlanEle.id = '"+planId+"'";
		List<LabQuaAuditPlanEleDetail> labQuaAuditPlanEleDetailList=labQuaAuditPlanEleDetailDAO.getLabQuaAuditPlanEleDetailByHQL(hql);
		List<LabQuaAuditRecord> labQuaAuditRecordList = labQuaAuditPlanEleDetailDAO.find(sql);
		if(labQuaAuditRecordList.size() == 0){
		if (null!=labQuaAuditPlanEleDetailList && labQuaAuditPlanEleDetailList.size()>0) {
			for (LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail : labQuaAuditPlanEleDetailList) {
				String months=labQuaAuditPlanEleDetail.getMonth();
				LabQuaInitAuditPlan labQuaInitAuditPlan = null;
				LabQuaInitAuditPlanVo labQuaInitAuditPlanVo=new LabQuaInitAuditPlanVo();
				//得到要素
				if(StrUtils.isBlankOrNull(month)){
					month = DateUtils.getMonth();
				}
				if(!StrUtils.isBlankOrNull(months)){
					String[] str=months.split(", ");
					for(String ss:str){
							if(ss.equals(month)){
								labQuaInitAuditPlan=labQuaAuditPlanEleDetail.getQuaInitAuditPlan();
							}
					}
				}
				if(null != labQuaInitAuditPlan){
					BeanUtils.copyProperties(labQuaInitAuditPlan, labQuaInitAuditPlanVo, new String[]{"isDel","createTime"});
					//得到检查内容
					String hqls="FROM LabQuaInitAuditPlan WHERE 1=1 AND quaInitAuditPlan.id ='"+labQuaInitAuditPlan.getId()+"' AND isDel = '"+Constants_Source.N+"' ";
					List<LabQuaInitAuditPlan> labQuaInitAuditPlanList=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlanByHQL(hqls);
					List<LabQuaInitAuditPlanVo> labQuaInitAuditPlanVo4List=new ArrayList<LabQuaInitAuditPlanVo>();
					if (null!=labQuaInitAuditPlanList && labQuaInitAuditPlanList.size()>0) {
						for (LabQuaInitAuditPlan labQuaInitAuditPlanOne : labQuaInitAuditPlanList) {
							LabQuaInitAuditPlanVo labQuaInitAuditPlan4Vo=new LabQuaInitAuditPlanVo();
							BeanUtils.copyProperties(labQuaInitAuditPlanOne, labQuaInitAuditPlan4Vo, new String[]{"isDel","createTime"});
							//得到检查重点
							String hql3="FROM LabQuaInitAuditPlan WHERE 1=1 AND quaInitAuditPlan.id='"+labQuaInitAuditPlanOne.getId()+"' AND isDel = '"+Constants_Source.N+"' ";
							List<LabQuaInitAuditPlan> KeyList=labQuaInitAuditPlanDAO.getLabQuaInitAuditPlanByHQL(hql3);
							List<LabQuaInitAuditPlanVo> KeyVoList=new ArrayList<LabQuaInitAuditPlanVo>();
							if (null!=KeyList && KeyList.size()>0) {
								for (LabQuaInitAuditPlan KeyPo : KeyList) {
									LabQuaInitAuditPlanVo KeyVo=new LabQuaInitAuditPlanVo();
									BeanUtils.copyProperties(KeyPo, KeyVo, new String[]{"isDel","createTime"});
									KeyVoList.add(KeyVo);
								}
								labQuaInitAuditPlan4Vo.setChildList(KeyVoList);
								labQuaInitAuditPlanVo4List.add(labQuaInitAuditPlan4Vo);
							}
						}
						labQuaInitAuditPlanVo.setChildList(labQuaInitAuditPlanVo4List);
					}
				}
				if(null!=labQuaInitAuditPlanVo){
					labQuaInitAuditPlanVoList.add(labQuaInitAuditPlanVo);
				}
			}
		}
		}
	 }
	return labQuaInitAuditPlanVoList;
   }
}
