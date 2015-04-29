package cn.labsoft.labos.common.formula.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.formula.dao.ILabFormulaDAO;
import cn.labsoft.labos.common.formula.entity.LabFormula;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labFormulaDAO")
public class LabFormulaDAOImpl extends BaseDAO implements ILabFormulaDAO {
	
	@Override
	public LabFormula addLabFormula(LabFormula labFormula) throws GlobalException {
		try {
			labFormula.setIsDel(Constants_Base.N);
			labFormula.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labFormula);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labFormulaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labFormula;
	}

	@Override
	public boolean deleteLabFormula(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabFormula(id));
			}
		} catch (Exception ex) {
			Log4J.error("labFormulaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabFormula getLabFormula(String id) throws GlobalException {
		try {
			LabFormula labFormula = (LabFormula) super.findById(LabFormula.class, id);
			return labFormula;
		} catch (Exception ex) {
			Log4J.error("labFormulaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabFormula(LabFormula labFormula) throws GlobalException {
		try {
			super.update(labFormula);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labFormulaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabFormula> getLabFormulaList(String hql) throws GlobalException {
	try {
		List<LabFormula> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labFormulaDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabFormulaPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labFormulaDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
