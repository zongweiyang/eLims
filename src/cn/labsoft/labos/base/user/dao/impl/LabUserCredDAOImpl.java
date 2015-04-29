package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.user.entity.LabUserCred;
import cn.labsoft.labos.base.user.dao.ILabUserCredDAO;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labUserCredDAO")
public class LabUserCredDAOImpl extends BaseDAO implements ILabUserCredDAO {
	
	@Override
	public LabUserCred addLabUserCred(LabUserCred labUserCred) throws GlobalException {
		try {
			labUserCred.setIsDel(Constants_Common.N);
			labUserCred.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labUserCred);
		} catch (Exception ex) {
			Log4J.error("labUserCredDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labUserCred;
	}

	@Override
	public boolean deleteLabUserCred(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabUserCred(id));
			}
		} catch (Exception ex) {
			Log4J.error("labUserCredDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabUserCred getLabUserCred(String id) throws GlobalException {
		try {
			LabUserCred labUserCred = (LabUserCred) super.findById(LabUserCred.class, id);
			return labUserCred;
		} catch (Exception ex) {
			Log4J.error("labUserCredDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabUserCred(LabUserCred labUserCred) throws GlobalException {
		try {
			super.update(labUserCred);
		} catch (Exception ex) {
			Log4J.error("labUserCredDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserCred> getLabUserCredList(String hql) throws GlobalException {
	try {
		List<LabUserCred> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labUserCredDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabUserCredPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labUserCredDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
