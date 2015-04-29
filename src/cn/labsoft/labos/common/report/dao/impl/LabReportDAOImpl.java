package cn.labsoft.labos.common.report.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.report.dao.ILabReportDAO;
import cn.labsoft.labos.common.report.entity.LabReport;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labReportDAO")
public class LabReportDAOImpl extends BaseDAO implements ILabReportDAO {
	
	@Override
	public LabReport addLabReport(LabReport labReport) throws GlobalException {
		try {
			labReport.setIsDel(Constants_Base.N);
			labReport.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labReport);
		} catch (Exception ex) {
			Log4J.error("labReportDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReport;
	}

	@Override
	public boolean deleteLabReport(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabReport(id));
			}
		} catch (Exception ex) {
			Log4J.error("labReportDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabReport getLabReport(String id) throws GlobalException {
		try {
			LabReport labReport = (LabReport) super.findById(LabReport.class, id);
			return labReport;
		} catch (Exception ex) {
			Log4J.error("labReportDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabReport(LabReport labReport) throws GlobalException {
		try {
			super.update(labReport);
		} catch (Exception ex) {
			Log4J.error("labReportDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabReport> getLabReportList(String hql) throws GlobalException {
	try {
		List<LabReport> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labReportDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabReportPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labReportDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
