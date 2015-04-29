package cn.labsoft.labos.common.report.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.report.entity.LabReportTag;
import cn.labsoft.labos.common.report.dao.ILabReportTagDAO;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labReportTagDAO")
public class LabReportTagDAOImpl extends BaseDAO implements ILabReportTagDAO {
	
	@Override
	public LabReportTag addLabReportTag(LabReportTag labReportTag) throws GlobalException {
		try {
			labReportTag.setIsDel(Constants_Base.N);
			labReportTag.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labReportTag);
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReportTag;
	}

	@Override
	public boolean deleteLabReportTag(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabReportTag(id));
			}
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabReportTag getLabReportTag(String id) throws GlobalException {
		try {
			LabReportTag labReportTag = (LabReportTag) super.findById(LabReportTag.class, id);
			return labReportTag;
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabReportTag(LabReportTag labReportTag) throws GlobalException {
		try {
			super.update(labReportTag);
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabReportTag> getLabReportTagList(String hql) throws GlobalException {
		try {
			List<LabReportTag> userList = super.find(hql);
			return userList;
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabReportTagPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabReportTag(LabReportTag labReportTag) throws GlobalException {
		try {
			super.delete(labReportTag);
		} catch (Exception ex) {
			Log4J.error("labReportTagDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
}
