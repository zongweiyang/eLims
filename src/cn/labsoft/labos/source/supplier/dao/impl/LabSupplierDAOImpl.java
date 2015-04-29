package cn.labsoft.labos.source.supplier.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.source.supplier.entity.LabSupplier;
import cn.labsoft.labos.source.supplier.dao.ILabSupplierDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labSupplierDAO")
public class LabSupplierDAOImpl extends BaseDAO implements ILabSupplierDAO {

	@Override
	public LabSupplier addLabSupplier(LabSupplier labSupplier) throws GlobalException {
		try {
			labSupplier.setIsDel(Constants_Source.N);
			labSupplier.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSupplier);
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSupplier;
	}

	@Override
	public boolean deleteLabSupplier(String ids[]) throws GlobalException {
		try {
			for (String id : ids) {
				LabSupplier labSupplier = this.getLabSupplier(id);
				labSupplier.setIsDel(Constants_Source.Y);
				super.update(labSupplier);
			}
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSupplier getLabSupplier(String id) throws GlobalException {
		try {
			LabSupplier labSupplier = (LabSupplier) super.findById(
					LabSupplier.class, id);
			return labSupplier;
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSupplier(LabSupplier labSupplier) throws GlobalException {
		try {
			super.update(labSupplier);
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public boolean deleteLabSupplier(LabSupplier labSupplier) throws GlobalException {
		try {
			labSupplier.setIsDel(Constants_Source.Y);
			super.update(labSupplier);
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSupplier> getLabSupplierList(String hql) throws GlobalException {
		try {
			List<LabSupplier> userList = super.find(hql);
			return userList;
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabSupplierPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSupplierDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
