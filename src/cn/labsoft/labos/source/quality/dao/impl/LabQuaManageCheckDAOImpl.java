package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaManageCheckDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheck;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labQuaManageCheckDAO")
public class LabQuaManageCheckDAOImpl extends BaseDAO implements ILabQuaManageCheckDAO{

	@Override
	public LabQuaManageCheck getLabQuaManageCheck(String id) throws GlobalException {
		try {
			LabQuaManageCheck quaAuditPlanEle=(LabQuaManageCheck)super.findById(LabQuaManageCheck.class,id);
			return quaAuditPlanEle;
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaManageCheck(LabQuaManageCheck labQuaManageCheck)throws GlobalException{
		try {
			super.update(labQuaManageCheck);
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
		
	}
	
	@Override
	public LabQuaManageCheck addLabQuaManageCheck(LabQuaManageCheck labQuaManageCheck)
			throws GlobalException {
		try {
			labQuaManageCheck.setIsDel(Constants_Source.N);
			labQuaManageCheck.setCreateTime(DateUtils.getCurrDateStr());
			super.save(labQuaManageCheck);
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labQuaManageCheck;
	}

	@Override
	public boolean updateLabQuaManageCheck(LabQuaManageCheck labQuaManageCheck)
			throws GlobalException {
		try {
			super.update(labQuaManageCheck);
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public PageResult getLabQuaManageCheckPR(String hql, PageResult pageResult)throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaManageCheckDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaManageCheck> getLabQuaManageCheckByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}
}
