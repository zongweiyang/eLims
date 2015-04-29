package cn.labsoft.labos.source.supplier.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.source.supplier.entity.LabSupEvaluate;
import cn.labsoft.labos.source.supplier.dao.ILabSupEvaluateDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labSupEvaluateDAO")
public class LabSupEvaluateDAOImpl extends BaseDAO implements
		ILabSupEvaluateDAO {

	@Override
	public LabSupEvaluate addLabSupEvaluate(LabSupEvaluate labSupEvaluate) throws GlobalException {
		try {
			labSupEvaluate.setIsDel(Constants_Source.N);
			labSupEvaluate.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labSupEvaluate);
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labSupEvaluate;
	}

	@Override
	public boolean deleteLabSupEvaluate(String ids[]) throws GlobalException {
		try {
			for (String id : ids) {
				super.delete(this.getLabSupEvaluate(id));
			}
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabSupEvaluate getLabSupEvaluate(String id) throws GlobalException {
		try {
			LabSupEvaluate labSupEvaluate = (LabSupEvaluate) super.findById(
					LabSupEvaluate.class, id);
			return labSupEvaluate;
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSupEvaluate(LabSupEvaluate labSupEvaluate) throws GlobalException {
		try {
			super.update(labSupEvaluate);
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public boolean deleteLabSupEvaluate(LabSupEvaluate labSupEvaluate) throws GlobalException {
		try {
			super.delete(labSupEvaluate);
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabSupEvaluate> getLabSupEvaluateList(String hql) throws GlobalException {
		try {
			List<LabSupEvaluate> userList = super.find(hql);
			return userList;
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabSupEvaluatePR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labSupEvaluateDAOImpl error...." + ex.getMessage(),
							ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
