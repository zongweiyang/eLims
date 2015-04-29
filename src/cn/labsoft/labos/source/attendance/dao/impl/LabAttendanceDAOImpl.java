package cn.labsoft.labos.source.attendance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.source.attendance.entity.LabAttendance;
import cn.labsoft.labos.source.attendance.dao.ILabAttendanceDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labAttendanceDAO")
public class LabAttendanceDAOImpl extends BaseDAO implements ILabAttendanceDAO {
	
	@Override
	public LabAttendance addLabAttendance(LabAttendance labAttendance) throws GlobalException {
		try {
			labAttendance.setIsDel(Constants_Source.N);
			labAttendance.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labAttendance);
		} catch (Exception ex) {
			Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labAttendance;
	}

	@Override
	public boolean deleteLabAttendance(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabAttendance(id));
			}
		} catch (Exception ex) {
			Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabAttendance getLabAttendance(String id) throws GlobalException {
		try {
			LabAttendance labAttendance = (LabAttendance) super.findById(LabAttendance.class, id);
			return labAttendance;
		} catch (Exception ex) {
			Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabAttendance(LabAttendance labAttendance) throws GlobalException {
		try {
			super.update(labAttendance);
		} catch (Exception ex) {
			Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabAttendance(LabAttendance labAttendance) throws GlobalException {
		try {
			super.delete(labAttendance);
		} catch (Exception ex) {
			Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabAttendance> getLabAttendanceList(String hql) throws GlobalException {
	try {
		List<LabAttendance> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabAttendancePR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labAttendanceDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
