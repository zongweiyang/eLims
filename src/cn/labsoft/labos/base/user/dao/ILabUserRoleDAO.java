package cn.labsoft.labos.base.user.dao;

import java.util.List;

import cn.labsoft.labos.base.user.entity.LabUserRole;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 用户角色关系数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabUserRoleDAO extends IBaseDAO {
	/**
	 * 增加人员角色的信息
	 * @param LabUserRole 用户角色关系持久化对象
	 * @return 用户角色对象
	 * @throws GlobalException 
	 */
	public LabUserRole addLabUserRole(LabUserRole labUserRole) throws GlobalException;

	/**
	 * 删除人员角色的信息
	 * @param labUserRole  用户角色关系持久化对象
	 * @return 删除结果True or False
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserRole(LabUserRole labUserRole) throws GlobalException;

	/**
	 * 修改人员角色的信息
	 * @param labUserRole 用户角色关系持久化对象
	 * @return True or False
	 * @throws GlobalException 
	 */
	public boolean updateLabUserRole(LabUserRole labUserRole) throws GlobalException;
	
	/**
	 * 根据人员角色对象查询人员角色信息
	 * @param labUserRole 用户角色关系持久化对象
	 * @return 用户角色对象
	 * @throws GlobalException 
	 */
	public LabUserRole getLabUserRole(LabUserRole labUserRole) throws GlobalException;
	/**
	 * 根据用户Id获取用户角色关系列表
	 * @param userId 用户Id
	 * @return 用户角色对象集合 
	 * @throws GlobalException 
	 */
	public List<LabUserRole> getLabUserRoleListByUserId(String userId) throws GlobalException;
	/**
	 * 根据用户Id获取用户角色关系列表
	 * @param userId 用户Id
	 * @param orgId 部门Id
	 * @return 用户角色对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserRole> getLabUserRoleListByUserIdAndOrgId(String userId,String orgId) throws GlobalException;
	/**
	 * 根据角色Id获取用户角色管理列表
	 * @param roleId 角色Id
	 * @return 用户角色对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserRole> getLabUserRoleListByRoleId(String roleId) throws GlobalException;
}
