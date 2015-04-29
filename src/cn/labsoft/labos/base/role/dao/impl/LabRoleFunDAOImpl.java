package cn.labsoft.labos.base.role.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.role.dao.ILabRoleFunDAO;
import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.base.role.entity.LabRoleFun;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labRoleFunDAO")
public class LabRoleFunDAOImpl extends BaseDAO implements ILabRoleFunDAO {

	@Override
	public LabRoleFun addLabRoleFun(LabRoleFun labRoleFun) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			labRoleFun.setIsDel(Constants_Common.N);
			labRoleFun.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labRoleFun);
		} catch (Exception ex) {
			Log4J.error("LabRoleFunDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labRoleFun;
	}

	@Override
	public boolean delLabRoleFun(LabRoleFun labRoleFun) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			super.delete(labRoleFun);
		} catch (Exception ex) {
			Log4J.error("LabRoleFunDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabRoleFun getLabRoleFun(String roleFunId) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			LabRoleFun labRoleFun = (LabRoleFun) super.findById(LabRole.class, roleFunId);
			return labRoleFun;
		} catch (Exception ex) {
			Log4J.error("LabRoleFunDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabRoleFun(LabRoleFun labRoleFun) throws GlobalException {
		// TODO Auto-generated method stub
		try {
			super.update(labRoleFun);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabRoleFunDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabRoleFun> getLabRoleFunByFunId(String funId) throws GlobalException {
		String hql="FROM LabRoleFun WHERE function.id='"+funId+"'";
		try {
			List<LabRoleFun> rfList=super.find(hql);
			return rfList;
		} catch (RuntimeException e) {
			Log4J.error("LabRoleFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabRoleFun> getLabRoleFunByRoleId(String roleId) throws GlobalException {
		String hql="FROM LabRoleFun WHERE role.id='"+roleId+"' AND function.isDel='"+Constants_Common.N+"'";
		try {
			List<LabRoleFun> rfList=super.find(hql);
			return rfList;
		} catch (RuntimeException e) {
			Log4J.error("LabRoleFunDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}
}
