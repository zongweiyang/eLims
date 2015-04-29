package cn.labsoft.labos.common.query.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.query.entity.LabParameter;
import cn.labsoft.labos.common.query.entity.LabQuery;
import cn.labsoft.labos.common.query.dao.ILabQueryDAO;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labQueryDAO")
public class LabQueryDAOImpl extends BaseDAO implements ILabQueryDAO {
	@Override
	public LabQuery addLabQuery(LabQuery labQuery) throws GlobalException {
		try {
			labQuery.setIsDel(Constants_Base.N);
			labQuery.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labQuery;
	}

	@Override
	public boolean deleteLabQuery(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabQuery(id));
			}
		} catch (Exception ex) {
			Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabQuery getLabQuery(String id) throws GlobalException {
		try {
			LabQuery labQuery = (LabQuery) super.findById(LabQuery.class, id);
			return labQuery;
		} catch (Exception ex) {
			Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuery(LabQuery labQuery) throws GlobalException {
		try {
			super.update(labQuery);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabQuery> getLabQueryList(String hql) throws GlobalException {
	try {
		List<LabQuery> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabQueryPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labQueryDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public void deleteAll(List<LabParameter> listLabParameter) {
		// TODO Auto-generated method stub
		super.deleteAll(listLabParameter);
	}
}
