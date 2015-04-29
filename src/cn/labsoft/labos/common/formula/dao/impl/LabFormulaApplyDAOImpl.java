package cn.labsoft.labos.common.formula.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.formula.dao.ILabFormulaApplyDAO;
import cn.labsoft.labos.common.formula.entity.LabFormulaApply;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labFormulaApplyDAO")
public class LabFormulaApplyDAOImpl extends BaseDAO implements ILabFormulaApplyDAO {
	
	@Override
	public LabFormulaApply addLabFormulaApply(LabFormulaApply labFormulaApply) throws GlobalException {
		try {
			labFormulaApply.setIsDel(Constants_Base.N);
			labFormulaApply.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labFormulaApply);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labFormulaApplyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labFormulaApply;
	}

	@Override
	public boolean deleteLabFormulaApply(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabFormulaApply(id));
			}
		} catch (Exception ex) {
			Log4J.error("labFormulaApplyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabFormulaApply getLabFormulaApply(String id) throws GlobalException {
		try {
			LabFormulaApply labFormulaApply = (LabFormulaApply) super.findById(LabFormulaApply.class, id);
			return labFormulaApply;
		} catch (Exception ex) {
			Log4J.error("labFormulaApplyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabFormulaApply(LabFormulaApply labFormulaApply) throws GlobalException {
		try {
			super.update(labFormulaApply);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labFormulaApplyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabFormulaApply> getLabFormulaApplyList(String hql) throws GlobalException {
	try {
		List<LabFormulaApply> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labFormulaApplyDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabFormulaApplyPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labFormulaApplyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
