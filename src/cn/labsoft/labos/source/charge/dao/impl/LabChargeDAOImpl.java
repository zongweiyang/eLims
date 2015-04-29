package cn.labsoft.labos.source.charge.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.charge.dao.ILabChargeDAO;
import cn.labsoft.labos.source.charge.entity.LabCharge;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labChargeDAO")
public class LabChargeDAOImpl extends BaseDAO implements ILabChargeDAO{
	
	@Override
	public LabCharge addLabCharge(LabCharge labCharge) throws GlobalException {
		try {
			labCharge.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labCharge);
		} catch (Exception ex) {
			Log4J.error("LabChargeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labCharge;
	}

	@Override
	public boolean deleteLabCharge(LabCharge labCharge) throws GlobalException {
		try {
			super.update(labCharge);
		} catch (Exception ex) {
			Log4J.error("LabChargeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabCharge> getLabChargeList(String hql) throws GlobalException {
		try {
			 List<LabCharge> labChargeList = super.find(hql);
			 return labChargeList;
		} catch (Exception ex) {
			Log4J.error("LabChargeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabCharge getLabCharge(String id) throws GlobalException {
		try {
			LabCharge labCharge = (LabCharge) super.findById(LabCharge.class, id);
			return labCharge;
		} catch (Exception ex) {
			Log4J.error("LabChargeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabCharge(LabCharge labCharge) throws GlobalException {
		try {
			labCharge.setUpdateDate(DateUtils.getCurrDateTimeStr());
			super.update(labCharge);
		} catch (Exception ex) {
			Log4J.error("LabChargeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public PageResult getLabChargePR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql,pageResult);
			 return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabChargeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}


}
