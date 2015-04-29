package cn.labsoft.labos.common.workflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.entity.LabWfConfig;
import cn.labsoft.labos.common.workflow.dao.ILabWfConfigDAO;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labWfConfigDAO")
public class LabWfConfigDAOImpl extends BaseDAO implements ILabWfConfigDAO {
	
	@Override
	public LabWfConfig addLabWfConfig(LabWfConfig labWfConfig) throws GlobalException {
		try {
			labWfConfig.setIsDel(Constants_Base.N);
			labWfConfig.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labWfConfig);
		} catch (Exception ex) {
			Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labWfConfig;
	}

	@Override
	public boolean deleteLabWfConfig(String ids[]){
		try {
			for (String id : ids) {
				super.delete(this.getLabWfConfig(id));
			}
		} catch (Exception ex) {
			Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	@Override
	public LabWfConfig getLabWfConfig(String id) throws GlobalException {
		try {
			LabWfConfig labWfConfig = (LabWfConfig) super.findById(LabWfConfig.class, id);
			return labWfConfig;
		} catch (Exception ex) {
			Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabWfConfig(LabWfConfig labWfConfig) throws GlobalException {
		try {
			super.update(labWfConfig);
		} catch (Exception ex) {
			Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabWfConfig(LabWfConfig labWfConfig) throws GlobalException {
		try {
			super.delete(labWfConfig);
		} catch (Exception ex) {
			Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfConfig> getLabWfConfigList(String hql) throws GlobalException {
	try {
		List<LabWfConfig> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabWfConfigPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labWfConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
