package cn.labsoft.labos.source.ambient.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.ambient.dao.ILabAmbientDAO;
import cn.labsoft.labos.source.ambient.entity.LabAmbient;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labAmbientDAO")
public class LabAmbientDAOImpl extends BaseDAO implements ILabAmbientDAO {
	@Override
	public LabAmbient addLabAmbient(LabAmbient labAmbient) throws GlobalException {
		try {
			labAmbient.setIsDel(Constants_Source.N);
			labAmbient.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labAmbient);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labAmbientDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labAmbient;
	}

	@Override
	public boolean deleteLabAmbient(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabAmbient(id));
			}
		} catch (Exception ex) {
			Log4J.error("labAmbientDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabAmbient getLabAmbient(String id) throws GlobalException {
		try {
			LabAmbient labAmbient = (LabAmbient) super.findById(LabAmbient.class, id);
			return labAmbient;
		} catch (Exception ex) {
			Log4J.error("labAmbientDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabAmbient(LabAmbient labAmbient) throws GlobalException {
		try {
			super.update(labAmbient);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labAmbientDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabAmbient> getLabAmbientList(String hql) throws GlobalException {
	try {
		List<LabAmbient> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labAmbientDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabAmbientPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labAmbientDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
