package cn.labsoft.labos.business.sample.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import cn.labsoft.labos.business.sample.entity.LabSampCustomer;
import cn.labsoft.labos.business.sample.dao.ILabSampCustomerDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSampCustomerDAO")
public class LabSampCustomerDAOImpl extends BaseDAO implements ILabSampCustomerDAO {
	
	@Override
	public LabSampCustomer addLabSampCustomer(LabSampCustomer labLabSampCustomer) throws GlobalException {
		try {
			labLabSampCustomer.setIsDel(Constants_Business.N);
			labLabSampCustomer.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labLabSampCustomer);
		} catch (Exception ex) {
			Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labLabSampCustomer;
	}

	@Override
	public boolean deleteLabSampCustomer(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSampCustomer(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSampCustomer getLabSampCustomer(String id) throws GlobalException {
		try {
			LabSampCustomer labLabSampCustomer = (LabSampCustomer) super.findById(LabSampCustomer.class, id);
			return labLabSampCustomer;
		} catch (Exception ex) {
			Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSampCustomer(LabSampCustomer labLabSampCustomer) throws GlobalException {
		try {
			super.update(labLabSampCustomer);
		} catch (Exception ex) {
			Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSampCustomer(LabSampCustomer labLabSampCustomer) throws GlobalException {
		try {
			super.delete(labLabSampCustomer);
		} catch (Exception ex) {
			Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSampCustomer> getLabSampCustomerList(String hql) throws GlobalException {
	try {
		List<LabSampCustomer> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSampCustomerPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSampCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
