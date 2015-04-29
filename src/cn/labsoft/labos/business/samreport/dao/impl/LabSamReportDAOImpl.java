package cn.labsoft.labos.business.samreport.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.samreport.entity.LabSamReport;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamReportDAO")
public class LabSamReportDAOImpl extends BaseDAO implements ILabSamReportDAO {
	
	@Override
	public LabSamReport addLabSamReport(LabSamReport labSamReport) {
		try {
			labSamReport.setIsDel(Constants_Business.N);
			labSamReport.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSamReport);
		} catch (Exception ex) {
			Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
			return null;
		}
		return labSamReport;
	}

	@Override
	public boolean deleteLabSamReport(String ids[]){
		try {
			for (String id : ids) {
				super.delete(this.getLabSamReport(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	@Override
	public LabSamReport getLabSamReport(String id) {
		try {
			LabSamReport labSamReport = (LabSamReport) super.findById(LabSamReport.class, id);
			return labSamReport;
		} catch (Exception ex) {
			Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
			return null;
		}
	}

	@Override
	public boolean updateLabSamReport(LabSamReport labSamReport) {
		try {
			super.update(labSamReport);
		} catch (Exception ex) {
			Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
			return false;
		}
		return true;
	}
	@Override
	public boolean deleteLabSamReport(LabSamReport labSamReport) {
		try {
			super.delete(labSamReport);
		} catch (Exception ex) {
			Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
			return false;
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamReport> getLabSamReportList(String hql) {
	try {
		List<LabSamReport> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
		return null;
	}
	}

	@Override
	public PageResult getLabSamReportPR(String hql, PageResult pageResult) {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSamReportDAOImpl error...." + ex.getMessage(), ex);
			return pageResult;
		}
	}
	
}
