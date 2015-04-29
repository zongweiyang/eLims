package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.user.dao.ILabUserRoleDAO;
import cn.labsoft.labos.base.user.entity.LabUserRole;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

@Repository(value="labUserRoleDAO")
public class LabUserRoleDAOImpl extends BaseDAO implements ILabUserRoleDAO {

	@Override
	public LabUserRole addLabUserRole(LabUserRole labUserRole) throws GlobalException {
		try {
			super.save(labUserRole);
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labUserRole;
	}

	@Override
	public boolean deleteLabUserRole(LabUserRole labUserRole) throws GlobalException {
		try {
			super.delete(labUserRole);
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabUserRole getLabUserRole(LabUserRole labUserRole) throws GlobalException {
		try {
			labUserRole = (LabUserRole) super.findById(LabUserRole.class,
					labUserRole.getId());
			return labUserRole;
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabUserRole(LabUserRole labUserRole) throws GlobalException {
		try {
			super.update(labUserRole);
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserRole> getLabUserRoleListByRoleId(String roleId) throws GlobalException {
		try {
			String hql = " FROM LabUserRole WHERE isDel='"+Constants_Common.N+"' AND user.isDel='"+Constants_Common.N+"' AND role.id = '" + roleId + "'";
			List<LabUserRole> labUserRoleList = super.find(hql);
			return labUserRoleList;
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserRole> getLabUserRoleListByUserId(String userId) throws GlobalException {
		try {
			String hql = " FROM LabUserRole WHERE isDel='"+Constants_Common.N+"'  AND user.id = '" + userId + "'";
			hql+=" ORDER BY org.sort,role.sort ASC";
			List<LabUserRole> labUserRoleList = super.find(hql);
			return labUserRoleList;
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserRole> getLabUserRoleListByUserIdAndOrgId(String userId,
			String orgId) throws GlobalException {
		try {
			String hql = " FROM LabUserRole WHERE isDel='"+Constants_Common.N+"'  AND user.id = '" + userId + "'";
			hql+=" AND org.id='"+orgId+"'";
			hql+=" ORDER BY role.sort";
			List<LabUserRole> labUserRoleList = super.find(hql);
			return labUserRoleList;
		} catch (Exception ex) {
			Log4J.error("LabUserRoleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
