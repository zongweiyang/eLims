package cn.labsoft.labos.common.number.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.number.dao.ILabNumberParDAO;
import cn.labsoft.labos.common.number.entity.LabNumberPar;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labNumberParDAO")
public class LabNumberParDAOImpl extends BaseDAO implements ILabNumberParDAO {

	@Override
	public boolean deleteLabNumberPar(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		try{
			for (String id : ids) {
				super.delete(this.getLabNumberPar(id));
			}
		} catch (Exception ex) {
			Log4J.error("LabNumberParDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	return true;
	}

	@Override
	public LabNumberPar addLabNumberPar(LabNumberPar labNumberPar) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			labNumberPar.setIsDel(Constants_Base.N);
			labNumberPar.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labNumberPar);
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labNumberPar;
	}

	@Override
	public LabNumberPar getLabNumberPar(String id) throws GlobalException {
		try {
			LabNumberPar labNumberPar = (LabNumberPar) super.findById(LabNumberPar.class, id);
			return labNumberPar;
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabNumberPar> getLabNumberParList(String hql) throws GlobalException {
		try {
			List<LabNumberPar> labNumberParList = super.find(hql);
			return labNumberParList;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabNumberParPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabNumberPar(LabNumberPar labNumberPar) throws GlobalException {
		try {
			super.update(labNumberPar);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	

	
}
