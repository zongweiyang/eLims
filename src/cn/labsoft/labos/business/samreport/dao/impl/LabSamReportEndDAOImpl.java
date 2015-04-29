package cn.labsoft.labos.business.samreport.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.samreport.entity.LabSamReportEnd;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportEndDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSamReportEndDAO")
public class LabSamReportEndDAOImpl extends BaseDAO implements ILabSamReportEndDAO {
	
	@Override
	public LabSamReportEnd addLabSamReportEnd(LabSamReportEnd labSamReportEnd) throws GlobalException {
		try {
			labSamReportEnd.setIsDel(Constants_Business.N);
			labSamReportEnd.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSamReportEnd);
		} catch (Exception ex) {
			Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSamReportEnd;
	}

	@Override
	public boolean deleteLabSamReportEnd(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSamReportEnd(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSamReportEnd getLabSamReportEnd(String id) throws GlobalException {
		try {
			LabSamReportEnd labSamReportEnd = (LabSamReportEnd) super.findById(LabSamReportEnd.class, id);
			return labSamReportEnd;
		} catch (Exception ex) {
			Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSamReportEnd(LabSamReportEnd labSamReportEnd) throws GlobalException {
		try {
			super.update(labSamReportEnd);
		} catch (Exception ex) {
			Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSamReportEnd(LabSamReportEnd labSamReportEnd) throws GlobalException {
		try {
			super.delete(labSamReportEnd);
		} catch (Exception ex) {
			Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSamReportEnd> getLabSamReportEndList(String hql) throws GlobalException {
	try {
		List<LabSamReportEnd> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSamReportEndPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSamReportEndDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
