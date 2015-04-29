package cn.labsoft.labos.business.sample.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.sample.entity.LabSampItems;
import cn.labsoft.labos.business.sample.dao.ILabSampItemsDAO;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labSampItemsDAO")
public class LabSampItemsDAOImpl extends BaseDAO implements ILabSampItemsDAO {
	
	@Override
	public LabSampItems addLabSampItems(LabSampItems sampItems) throws GlobalException {
		try {
			sampItems.setIsDel(Constants_Business.N);
			sampItems.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(sampItems);
		} catch (Exception ex) {
			Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return sampItems;
	}

	@Override
	public boolean deleteLabSampItems(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabSampItems(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSampItems getLabSampItems(String id) throws GlobalException {
		try {
			LabSampItems sampItems = (LabSampItems) super.findById(LabSampItems.class, id);
			return sampItems;
		} catch (Exception ex) {
			Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSampItems(LabSampItems sampItems) throws GlobalException {
		try {
			super.update(sampItems);
		} catch (Exception ex) {
			Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabSampItems(LabSampItems sampItems) throws GlobalException {
		try {
			super.delete(sampItems);
		} catch (Exception ex) {
			Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabSampItems> getLabSampItemsList(String hql) throws GlobalException {
	try {
		List<LabSampItems> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabSampItemsPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSampItemsDAO error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
