package cn.labsoft.labos.base.user.dao;

import java.util.List;
import cn.labsoft.labos.base.user.entity.LabUserOrg;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 用户组织关系数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabUserOrgDAO extends IBaseDAO {
	/**
	 * 增加用户组织关系信息
	 * @param userOrg
	 * @return 用户组织对象
	 * @throws GlobalException 
	 */
	public LabUserOrg addLabUserOrg(LabUserOrg userOrg) throws GlobalException;
	/**
	 * 删除用户组织关系信息
	 * @param userId
	 * @param orgId
	 * @return 删除结果True or False
	 * @throws GlobalException 
	 */
	public boolean delLabUserOrg(LabUserOrg userOrg) throws GlobalException;
	/**
	 * 根据用户Id查询用户组织关系列表
	 * @param userId 用户id
	 * @return 用户组织对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserOrg> getLabUserOrgListByUserId(String userId) throws GlobalException;
	/**
	 * 根据组织Id查询用户组织关系列表
	 * @param orgId 组织id
	 * @return 用户组织对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserOrg> getLabUserOrgListByOrgId(String orgId) throws GlobalException;
}
