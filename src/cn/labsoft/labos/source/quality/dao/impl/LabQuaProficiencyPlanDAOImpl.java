package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaProficiencyPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiencyPlan;



@Repository(value="labQuaProficiencyPlanDAO")
public class LabQuaProficiencyPlanDAOImpl extends BaseDAO implements ILabQuaProficiencyPlanDAO{

	@Override
	public LabQuaProficiencyPlan getLabQuaProficiencyPlan(String id) throws GlobalException {
		try {
			LabQuaProficiencyPlan labQuaProficiencyPlan=(LabQuaProficiencyPlan)super.findById(LabQuaProficiencyPlan.class,id);
			return labQuaProficiencyPlan;
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan)throws GlobalException{
		try {
			super.update(labQuaProficiencyPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaProficiencyPlan addLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan)
			throws GlobalException {
		try {
			super.save(labQuaProficiencyPlan);
			return labQuaProficiencyPlan;
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan)
			throws GlobalException {
		try {
			super.update(labQuaProficiencyPlan);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaProficiencyPlanPR(String hql,PageResult pageResult )throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaProficiencyPlan> getLabQuaProficiencyPlanByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
