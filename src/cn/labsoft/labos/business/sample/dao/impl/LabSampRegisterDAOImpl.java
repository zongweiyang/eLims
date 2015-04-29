package cn.labsoft.labos.business.sample.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.business.sample.dao.ILabSampRegisterDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSampRegisterDAO")
public class LabSampRegisterDAOImpl extends BaseDAO implements ILabSampRegisterDAO {
	
	@Override
	public LabSampRegister addLabSampRegister(LabSampRegister labLabSampRegister) throws GlobalException {
		try {
			labLabSampRegister.setIsDel(Constants_Business.N);
			labLabSampRegister.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labLabSampRegister);
		} catch (Exception ex) {
			Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labLabSampRegister;
	}

	@Override
	public boolean deleteLabSampRegister(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSampRegister(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSampRegister getLabSampRegister(String id) throws GlobalException {
		try {
			LabSampRegister labLabSampRegister = (LabSampRegister) super.findById(LabSampRegister.class, id);
			return labLabSampRegister;
		} catch (Exception ex) {
			Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSampRegister(LabSampRegister labLabSampRegister) throws GlobalException {
		try {
			super.update(labLabSampRegister);
		} catch (Exception ex) {
			Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSampRegister(LabSampRegister labLabSampRegister) throws GlobalException {
		try {
			super.delete(labLabSampRegister);
		} catch (Exception ex) {
			Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSampRegister> getLabSampRegisterList(String hql) throws GlobalException {
	try {
		List<LabSampRegister> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSampRegisterPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSampRegisterDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
