package cn.labsoft.labos.source.customer.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.source.customer.dao.ILabCustomerDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labCustomerDAO")
public class LabCustomerDAOImpl extends BaseDAO implements ILabCustomerDAO {

	@Override
	public LabCustomer addLabCustomer(LabCustomer labCustomer) throws GlobalException {
		try {
			labCustomer.setIsDel(Constants_Source.N);
			labCustomer.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labCustomer);
		} catch (Exception ex) {
			Log4J.error("labCustomerDAOImpl addLabCustomer error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labCustomer;
	}

	@Override
	public boolean deleteLabCustomer(String ids[]) throws GlobalException {
		try {
			for (String id : ids) {
				LabCustomer labCustomer=this.getLabCustomer(id);
				labCustomer.setIsDel(Constants_Source.Y);
				super.update(labCustomer);
			}
		} catch (Exception ex) {
			Log4J.error("labCustomerDAOImpl deleteLabCustomer error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabCustomer getLabCustomer(String id) throws GlobalException {
		try {
			LabCustomer labCustomer = (LabCustomer) super.findById(
					LabCustomer.class, id);
			return labCustomer;
		} catch (Exception ex) {
			Log4J.error("labCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabCustomer(LabCustomer labCustomer) throws GlobalException {
		try {
			super.update(labCustomer);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labCustomerDAOImpl updateLabCustomer error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabCustomer> getLabCustomerList(String hql) throws GlobalException {
		try {
			List<LabCustomer> userList = super.find(hql);
			return userList;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labCustomerDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabCustomerPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labCustomerDAOImpl getLabCustomerPR error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
