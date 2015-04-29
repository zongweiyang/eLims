package cn.labsoft.labos.source.charge.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.charge.dao.ILabChargeDetailDAO;
import cn.labsoft.labos.source.charge.entity.LabChargeDetail;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labChargeDetailDAO")
public class LabChargeDetailDAOImpl extends BaseDAO implements ILabChargeDetailDAO{
	
	@Override
	public LabChargeDetail addLabChargeDetail(LabChargeDetail labChargeDetail) throws GlobalException {
		try {
			labChargeDetail.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labChargeDetail);
		} catch (Exception ex) {
			Log4J.error("LabChargeDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labChargeDetail;
	}

	@Override
	public boolean deleteLabChargeDetail(LabChargeDetail labChargeDetail) throws GlobalException {
		try {
			super.update(labChargeDetail);
		} catch (Exception ex) {
			Log4J.error("LabChargeDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabChargeDetail> getLabChargeDetailList(String hql) throws GlobalException {
		try {
			 List<LabChargeDetail> labChargeDetailList = super.find(hql);
			 return labChargeDetailList;
		} catch (Exception ex) {
			Log4J.error("LabChargeDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabChargeDetail getLabChargeDetail(String id) throws GlobalException {
		try {
			LabChargeDetail labChargeDetail = (LabChargeDetail) super.findById(LabChargeDetail.class, id);
			return labChargeDetail;
		} catch (Exception ex) {
			Log4J.error("LabChargeDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabChargeDetail(LabChargeDetail labChargeDetail) throws GlobalException {
		try {
			super.update(labChargeDetail);
		} catch (Exception ex) {
			Log4J.error("LabChargeDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public PageResult getLabChargeDetailPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql,pageResult);
			 return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabChargeDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}


}
