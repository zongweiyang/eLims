package cn.labsoft.labos.business.samreport.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.samreport.entity.LabSamReportData;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportDataDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamReportDataDAO")
public class LabSamReportDataDAOImpl extends BaseDAO implements ILabSamReportDataDAO {
	
	@Override
	public LabSamReportData addLabSamReportData(LabSamReportData labSamReportData) throws GlobalException {
		try {
			labSamReportData.setIsDel(Constants_Business.N);
			labSamReportData.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSamReportData);
		} catch (Exception ex) {
			Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSamReportData;
	}

	@Override
	public boolean deleteLabSamReportData(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSamReportData(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSamReportData getLabSamReportData(String id) throws GlobalException {
		try {
			LabSamReportData labSamReportData = (LabSamReportData) super.findById(LabSamReportData.class, id);
			return labSamReportData;
		} catch (Exception ex) {
			Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSamReportData(LabSamReportData labSamReportData) throws GlobalException {
		try {
			super.update(labSamReportData);
		} catch (Exception ex) {
			Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSamReportData(LabSamReportData labSamReportData) throws GlobalException {
		try {
			super.delete(labSamReportData);
		} catch (Exception ex) {
			Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamReportData> getLabSamReportDataList(String hql) throws GlobalException {
	try {
		List<LabSamReportData> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSamReportDataPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSamReportDataDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
