package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaManageCheckPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheckPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaManageCheckPlanService;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckPlanVo;
import cn.labsoft.labos.utils.StrUtils;


@Service("labQuaManageCheckPlanService")
public class LabQuaManageCheckPlanServiceImpl extends BaseService implements ILabQuaManageCheckPlanService{
	private ILabQuaManageCheckPlanDAO labQuaManageCheckPlanDAO;
	
	@Resource
	public void setLabQuaManageCheckPlanDAO(ILabQuaManageCheckPlanDAO labQuaManageCheckPlanDAO) {
		this.labQuaManageCheckPlanDAO = labQuaManageCheckPlanDAO;
	}

	@Override
	public boolean update2DelLabQuaManageCheckPlan(String[] ids) throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaManageCheckPlan labQuaManageCheckPlan=labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(id);
				labQuaManageCheckPlan.setIsDel(Constants_Source.Y);
				labQuaManageCheckPlanDAO.updateLabQuaManageCheckPlan(labQuaManageCheckPlan);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaManageCheckPlanVo> getLabQuaManageCheckPlanList(
			LabQuaManageCheckPlanVo labQuaManageCheckPlanVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaManageCheckPlan WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getRecTime())){
			hql += " AND recTime = '"+labQuaManageCheckPlanVo.getRecTime()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getOrgSearch())){
			hql += " AND org.id = '"+labQuaManageCheckPlanVo.getOrgSearch()+"' ";
		}
		List<LabQuaManageCheckPlan> labQuaManageCheckPlanList=labQuaManageCheckPlanDAO.getLabQuaManageCheckPlanByHQL(hql);
		List<LabQuaManageCheckPlanVo> labQuaManageCheckPlanVoList=new ArrayList<LabQuaManageCheckPlanVo>();
		if (null!=labQuaManageCheckPlanList && labQuaManageCheckPlanList.size()>0) {
			for (LabQuaManageCheckPlan labQuaManageCheckPlan : labQuaManageCheckPlanList) {
				LabQuaManageCheckPlanVo labQuaManageCheckPlanVoOne=new LabQuaManageCheckPlanVo();
				BeanUtils.copyProperties(labQuaManageCheckPlan, labQuaManageCheckPlanVoOne, new String[]{"isDel"});
				if(null != labQuaManageCheckPlan.getOrg()){
					labQuaManageCheckPlanVoOne.setOrgId(labQuaManageCheckPlan.getOrg().getId());
					labQuaManageCheckPlanVoOne.setOrgName(labQuaManageCheckPlan.getOrg().getName());
				}
				labQuaManageCheckPlanVoList.add(labQuaManageCheckPlanVoOne);
			} 
		}
		return labQuaManageCheckPlanVoList;
	}

	@Override
	public PageResult getLabQuaManageCheckPlanPR(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo, PageResult pageResult)
			throws GlobalException {
			String hql = "FROM LabQuaManageCheckPlan WHERE isDel= '"+Constants_Source.N+"'";
			if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getRecTime())){
				hql += " AND recTime = '"+labQuaManageCheckPlanVo.getRecTime()+"'";
			}
			if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getAccStatus())){
				hql += " AND accStatus = '"+labQuaManageCheckPlanVo.getAccStatus()+"'";
			}
			if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getOrgSearch())){
				hql += " AND org.id = '"+labQuaManageCheckPlanVo.getOrgSearch()+"' ";
			}
			pageResult=labQuaManageCheckPlanDAO.getLabQuaManageCheckPlanPR(hql, pageResult);
			List<LabQuaManageCheckPlan> labQuaManageCheckPlanList=pageResult.getResultList();
			List<LabQuaManageCheckPlanVo> labQuaManageCheckPlanVoList=new ArrayList<LabQuaManageCheckPlanVo>();
			if (null!=labQuaManageCheckPlanList && labQuaManageCheckPlanList.size()>0) {
				for (LabQuaManageCheckPlan labQuaManageCheckPlan : labQuaManageCheckPlanList) {
					LabQuaManageCheckPlanVo labQuaManageCheckPlanVoOne=new LabQuaManageCheckPlanVo();
					BeanUtils.copyProperties(labQuaManageCheckPlan, labQuaManageCheckPlanVoOne, new String[]{"isDel"});
					if(null != labQuaManageCheckPlan.getOrg()){
						labQuaManageCheckPlanVoOne.setOrgId(labQuaManageCheckPlan.getOrg().getId());
						labQuaManageCheckPlanVoOne.setOrgName(labQuaManageCheckPlan.getOrg().getName());
					}
					labQuaManageCheckPlanVoList.add(labQuaManageCheckPlanVoOne);
				} 
				pageResult.setResultList(labQuaManageCheckPlanVoList);
			}
			return pageResult;
		
	}

	@Override
	public LabQuaManageCheckPlanVo getLabQuaManageCheckPlan(String id)
			throws GlobalException {
		LabQuaManageCheckPlan labQuaManageCheckPlan=labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(id);
		LabQuaManageCheckPlanVo labQuaManageCheckPlanVo=new LabQuaManageCheckPlanVo();
		BeanUtils.copyProperties(labQuaManageCheckPlan, labQuaManageCheckPlanVo, new String[]{"isDel"});
		if(null != labQuaManageCheckPlan.getOrg()){
			labQuaManageCheckPlanVo.setOrgId(labQuaManageCheckPlan.getOrg().getId());
			labQuaManageCheckPlanVo.setOrgName(labQuaManageCheckPlan.getOrg().getName());
		}
		return labQuaManageCheckPlanVo;
		
	}

	@Override
	public LabQuaManageCheckPlanVo addLabQuaManageCheckPlan(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo)
			throws GlobalException {
		LabQuaManageCheckPlan labQuaManageCheckPlan=new LabQuaManageCheckPlan();
		BeanUtils.copyProperties(labQuaManageCheckPlanVo, labQuaManageCheckPlan, new String[] {"isDel"});
		if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getOrgId())){
			LabOrg unitOrg=(LabOrg)labQuaManageCheckPlanDAO.findById(LabOrg.class, labQuaManageCheckPlanVo.getOrgId());
			labQuaManageCheckPlan.setOrg(unitOrg);
		}
		labQuaManageCheckPlan.setStatus("00");
		labQuaManageCheckPlan.setAccStatus("0");
		labQuaManageCheckPlan.setCreateUserId(getSessionContainer().getUserId());
		labQuaManageCheckPlanDAO.addLabQuaManageCheckPlan(labQuaManageCheckPlan);
		labQuaManageCheckPlanVo.setId(labQuaManageCheckPlan.getId());
		return labQuaManageCheckPlanVo;
	}

	@Override
	public boolean updateLabQuaManageCheckPlan(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo)
			throws GlobalException {
		LabQuaManageCheckPlan labQuaManageCheckPlan=labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(labQuaManageCheckPlanVo.getId());
		BeanUtils.copyProperties(labQuaManageCheckPlanVo, labQuaManageCheckPlan, new String[] {"isDel","createTime","tenantId","createUserId","accStatus"});
		if(!StrUtils.isBlankOrNull(labQuaManageCheckPlanVo.getOrgId())){
			LabOrg unitOrg=(LabOrg)labQuaManageCheckPlanDAO.findById(LabOrg.class, labQuaManageCheckPlanVo.getOrgId());
			labQuaManageCheckPlan.setOrg(unitOrg);
		}
		labQuaManageCheckPlanDAO.updateLabQuaManageCheckPlan(labQuaManageCheckPlan);
		return true;
	}
	
}
