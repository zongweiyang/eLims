package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.user.dao.ILabUserFunDAO;
import cn.labsoft.labos.base.user.entity.LabUserFun;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.interceptor.LogCommonInformation;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.StrUtils;

/**
 * 
 * <strong>Title : SysUserRoleDAOImpl </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : 2009-12-28 下午05:28:32 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author RitaYin<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Repository(value="labUserFunDAO")
public class LabUserFunDAOImpl extends BaseDAO implements ILabUserFunDAO {

	@Override
	public LabUserFun addLabUserFun(LabUserFun userFun) throws GlobalException {
		try {
			super.save(userFun);
		} catch (Exception ex) {
			Log4J.error("LabUserFunDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return userFun;
	}

	@Override
	public boolean deleteLabUserFun(LabUserFun userFun) throws GlobalException {
		try {
			super.delete(userFun);
		} catch (Exception ex) {
			Log4J.error("LabUserFunDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunList(String userId, String roleId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE isDel='"+Constants_Common.N+"' AND user.id='" + userId + "'";
		if (!StrUtils.isBlankOrNull(roleId)) {
			hql += " AND role.id='" + roleId + "'";
		} else {
			hql += " AND (role.id='' or role.id is null)";
		}
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunListByFunId(String funId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE function.id='" + funId + "'";
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunListByUserId(String userId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunListByUserIdAndOrgId(String userId,
			String orgId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
		hql += " AND org.id='" + orgId + "'";
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunListByUserIdAndPfunId(String userId,
			String pfunId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
		hql += " AND function.parentFunction.id='" + pfunId + "'";
		hql += " AND function.isDel='" + Constants_Common.N
				+ "' AND function.isDisplay='" + Constants_Common.Y + "'";
		hql += " GROUP BY function.id ";
		hql += " ORDER BY function.sort ASC";
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunList(String userId, String funType,
			String pfunId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
		if (!StrUtils.isBlankOrNull(pfunId)) {
			hql += " AND function.parentFunction.id='" + pfunId + "'";
		}
		if (!StrUtils.isBlankOrNull(funType)) {
			hql += " AND function.type='" + funType + "'";
		}
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean getLabUserFunHavePower(String userId, String funId,
			String action) throws GlobalException {
		
		System.out.println(String.format("userId=%s,funId=%s,action=%s", userId,funId,action));
		
		try {
			if (userId.equals("0")) {
				return true;
			}
			String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
			hql += " AND function.id = '" + funId + "'";
			if (action.equals(LogCommonInformation.DELETE)) {
				hql += " AND isDelete='Y'";
			} else if (action.equals(LogCommonInformation.QUERY)) {
				hql += " AND isShow='Y'";
			} else if (action.equals(LogCommonInformation.ADD)) {
				hql += " AND isAdd='Y'";
			} else if (action.equals(LogCommonInformation.UPDATE)) {
				hql += " AND isUpdate='Y'";
			} else {
			}
			List<LabUserFun> list = super.find(hql);
			if (null != list && 0 < list.size()) {
				return true;
			}
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserFun> getLabUserFunListByUserIdAndPfunId(String userId, String funId, String orgId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
		hql += " AND function.parentFunction.id='" + funId + "'";
		hql += " AND function.isDel='" + Constants_Common.N
				+ "' AND function.isDisplay='" + Constants_Common.Y + "'";
		if(orgId!=null&&!orgId.equals("")){
			hql += " AND org.id='" + orgId + "'";
		}
		hql += " GROUP BY function.id ";
		hql += " ORDER BY function.sort ASC";
		try {
			List<LabUserFun> list = super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabUserFun getLabUserFunByUserIdOrgIdfunId(String userId, String funId, String orgId) throws GlobalException {
		String hql = "FROM LabUserFun WHERE user.id='" + userId + "'";
		hql += " AND function.id='" + funId + "'";
		hql += " AND org.id='" + orgId + "'";
		hql += " AND function.isDel='" + Constants_Common.N+ "' ";
		try {
			LabUserFun po = (LabUserFun)super.find(hql,0);
			return po;
		} catch (RuntimeException e) {
			Log4J.error("LabUserFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public LabUserFun updateLabUserFun(LabUserFun labUserFun)
			throws GlobalException {
		super.update(labUserFun);
		return labUserFun;
	}
}
