package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.user.dao.ILabUserOrgDAO;
import cn.labsoft.labos.base.user.entity.LabUserOrg;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

/**
 * 
 * <strong>Title : SysUserDAOImpl </strong>. <br>
 * <strong>Description : 用户管理数据访问对象</strong> <br>
 * <strong>Create on : 2009-12-11 下午05:34:43 </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author Charles<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
@Repository(value="labUserOrgDAO")
public class LabUserOrgDAOImpl extends BaseDAO implements ILabUserOrgDAO {
	
	@Override
	public LabUserOrg addLabUserOrg(LabUserOrg userOrg) throws GlobalException {
		try {
			super.save(userOrg);
			return userOrg;
		} catch (Exception ex) {
			Log4J.error("LabUserOrgDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean delLabUserOrg(LabUserOrg userOrg) throws GlobalException {
		try {
			super.delete(userOrg);
		} catch (Exception ex) {
			Log4J.error("LabUserOrgDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserOrg> getLabUserOrgListByOrgId(String orgId) throws GlobalException {
		String hql="FROM LabUserOrg WHERE user.isDel='"+Constants_Common.N+"' AND org.id='"+orgId+"'";
		try {
			List<LabUserOrg> list=super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserOrgDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserOrg> getLabUserOrgListByUserId(String userId) throws GlobalException {
		String hql="FROM LabUserOrg WHERE org.isDel='"+Constants_Common.N+"' AND user.id='"+userId+"'";
		try {
			List<LabUserOrg> list=super.find(hql);
			return list;
		} catch (RuntimeException e) {
			Log4J.error("LabUserOrgDAOImpl error...." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	
}
