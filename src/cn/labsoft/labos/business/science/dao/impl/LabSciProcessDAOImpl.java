package cn.labsoft.labos.business.science.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.science.entity.LabSciProcess;
import cn.labsoft.labos.business.science.dao.ILabSciProcessDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSciProcessDAO")
public class LabSciProcessDAOImpl extends BaseDAO implements ILabSciProcessDAO {
	
	@Override
	public LabSciProcess addLabSciProcess(LabSciProcess labSciProcess) throws GlobalException {
		try {
			labSciProcess.setIsDel(Constants_Business.N);
			labSciProcess.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSciProcess);
		} catch (Exception ex) {
			Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSciProcess;
	}

	@Override
	public boolean deleteLabSciProcess(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSciProcess(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSciProcess getLabSciProcess(String id) throws GlobalException {
		try {
			LabSciProcess labSciProcess = (LabSciProcess) super.findById(LabSciProcess.class, id);
			return labSciProcess;
		} catch (Exception ex) {
			Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSciProcess(LabSciProcess labSciProcess) throws GlobalException {
		try {
			super.update(labSciProcess);
		} catch (Exception ex) {
			Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSciProcess(LabSciProcess labSciProcess) throws GlobalException {
		try {
			super.delete(labSciProcess);
		} catch (Exception ex) {
			Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSciProcess> getLabSciProcessList(String hql) throws GlobalException {
	try {
		List<LabSciProcess> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSciProcessPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSciProcessDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
