package cn.labsoft.labos.base.role.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.role.dao.ILabRoleDAO;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labRoleDAO")
public class LabRoleDAOImpl extends BaseDAO implements ILabRoleDAO {

	@Override
	public LabRole addLabRole(LabRole labRole) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			labRole.setIsDel(Constants_Common.N);
			labRole.setIsUsed(Constants_Common.Y);
			labRole.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labRole);
		} catch (Exception ex) {
			Log4J.error("LabRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());

		}
		return labRole;
	}

	@Override    
	public boolean deleteLabRole(LabRole labRole) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			super.update(labRole);
		} catch (Exception ex) {
			Log4J.error("LabRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabRole getLabRole(String roleId) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			LabRole labRole = (LabRole) super.findById(LabRole.class, roleId);
			return labRole;
		} catch (Exception ex) {
			Log4J.error("LabRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabRole> getLabRoleList(String hql) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			 List<LabRole> labRoleList = super.find(hql);
			 return labRoleList;
		} catch (Exception ex) {
			Log4J.error("LabRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabRolePR(String hql, PageResult pageResult) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			pageResult = super.getPageResult(hql,pageResult.getCurrentPage(),pageResult.getPagerMethod(),pageResult.getPageSize());
			 return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabRole(LabRole labRole) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			super.update(labRole);
		} catch (Exception ex) {
			Log4J.error("LabRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
}
