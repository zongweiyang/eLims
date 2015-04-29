package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;


import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaInitAuditPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaInitAuditPlan;
import cn.labsoft.labos.utils.DateUtils;


@Repository(value="labQuaInitAuditPlanDAO")
public class LabQuaInitAuditPlanDAOImpl extends BaseDAO implements ILabQuaInitAuditPlanDAO{

	@Override
	public LabQuaInitAuditPlan getLabQuaInitAuditPlan(String id) throws GlobalException {
		try {
		LabQuaInitAuditPlan labQuaInitAuditPlan=(LabQuaInitAuditPlan)super.findById(LabQuaInitAuditPlan.class,id);
		return labQuaInitAuditPlan;
		} catch (Exception ex) {
			Log4J.error("LabQuaInitAuditPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan)throws GlobalException{
		try {
			super.update(labQuaInitAuditPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaInitAuditPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaInitAuditPlan addLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan)
			throws GlobalException {
		try {
			labQuaInitAuditPlan.setIsDel(Constants_Source.N);
			labQuaInitAuditPlan.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labQuaInitAuditPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaInitAuditPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labQuaInitAuditPlan;
	}

	@Override
	public boolean updateLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan)
			throws GlobalException {
		try {
			super.update(labQuaInitAuditPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaInitAuditPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public PageResult getLabQuaInitAuditPlanPR(String hql, PageResult pageResult)throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaInitAuditPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaInitAuditPlan> getLabQuaInitAuditPlanByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
