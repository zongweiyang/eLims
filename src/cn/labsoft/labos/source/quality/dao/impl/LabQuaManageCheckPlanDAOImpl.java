package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaManageCheckPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheckPlan;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labQuaManageCheckPlanDAO")
public class LabQuaManageCheckPlanDAOImpl extends BaseDAO implements ILabQuaManageCheckPlanDAO{

	@Override
	public LabQuaManageCheckPlan getLabQuaManageCheckPlan(String id) throws GlobalException {
		try {
			LabQuaManageCheckPlan quaAuditPlanEle=(LabQuaManageCheckPlan)super.findById(LabQuaManageCheckPlan.class,id);
			return quaAuditPlanEle;
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan)throws GlobalException{
		try {
			super.update(labQuaManageCheckPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
		
	}
	
	@Override
	public LabQuaManageCheckPlan addLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan)
			throws GlobalException {
		try {
			labQuaManageCheckPlan.setIsDel(Constants_Source.N);
			labQuaManageCheckPlan.setCreateTime(DateUtils.getCurrDateStr());
			super.save(labQuaManageCheckPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labQuaManageCheckPlan;
	}

	@Override
	public boolean updateLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan)
			throws GlobalException {
		try {
			super.update(labQuaManageCheckPlan);
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public PageResult getLabQuaManageCheckPlanPR(String hql, PageResult pageResult)throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckPlanDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaManageCheckPlan> getLabQuaManageCheckPlanByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}
}
