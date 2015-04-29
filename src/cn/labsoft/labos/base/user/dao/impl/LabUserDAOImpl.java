package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.user.dao.ILabUserDAO;
import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

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
@Repository(value="labUserDAO")
public class LabUserDAOImpl extends BaseDAO implements ILabUserDAO {
	
	@Override
	public LabUser addLabUser(LabUser sysUser) throws GlobalException {
		try {
			sysUser.setIsDel(Constants_Common.N);
			sysUser.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(sysUser);
		} catch (Exception ex) {
			Log4J.error("SysUserDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return sysUser;
	}

	@Override
	public boolean delLabUser(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabUser(id));
			}
		} catch (Exception ex) {
			Log4J.error("SysUserDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabUser getLabUser(String userId) throws GlobalException {
		try {
			LabUser sysUser = (LabUser) super.findById(LabUser.class, userId);
			return sysUser;
		} catch (Exception ex) {
			Log4J.error("SysUserDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabUser(LabUser sysUser) throws GlobalException {
		try {
			super.update(sysUser);
		} catch (Exception ex) {
			Log4J.error("SysUserDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabUser> getLabUserList(String hql) throws GlobalException {
	try {
		List<LabUser> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("SysUserDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabUserPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("SysUserDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
