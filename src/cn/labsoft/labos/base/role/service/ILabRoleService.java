package cn.labsoft.labos.base.role.service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

@SuppressWarnings("unchecked")
public interface ILabRoleService {
	
	
	
	/**
	 * 增加角色信息
	 * 
	 * @param LabRoleVo
	 *            封装了角色信息的视图对象
	 * @throws GlobalException
	 */
	public LabRoleVo addLabRole(LabRoleVo labRoleVo) throws GlobalException;;

	/**
	 * 修改角色信息
	 * 
	 * @param LabRoleVo
	 *            封装了角色信息的视图对象
	 * @throws GlobalException
	 */
	public boolean updateLabRole(LabRoleVo labRoleVo) throws GlobalException;
	
	/**
	 * 软删除角色信息
	 * 
	 * @param roleId
	 *            角色id
	 * @throws GlobalException
	 */
	public boolean updateLabRole4Del(String ids[]) throws GlobalException;

	/**
	 * 根据角色id获得角色信息列表
	 * 
	 * @param id
	 *            角色id
	 * @throws GlobalException
	 * @return 返回类型 LabRoleVo 封装了角色信息的视图对象
	 * 
	 */
	public LabRoleVo getLabRole(Serializable id) throws GlobalException;

	/**
	 * 获得角色信息列表
	 * 
	 * @param LabRoleVo
	 *            封装了角色信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 List 返回角色信息集合
	 * 
	 */
	public List<LabRoleVo> getLabRoleList(LabRoleVo labRoleVo) throws GlobalException;

	/**
	 * 根据pageResult获得角色分页信息列表，LabRoleVo为备用参数
	 * 
	 * @param LabRoleVo
	 *            封装了角色信息的视图对象
	 * @throws GlobalException
	 * @return 返回类型 PageResult 返回带有分页的角色信息列表
	 * 
	 */
	public PageResult getLabRolePR(LabRoleVo labRoleVo, PageResult pageResult) throws GlobalException;

	/**
	 * 获得所有角色的信息列表
	 * 
	 * @throws GlobalException
	 * @return 返回类型 List 返回所有角色信息的集合
	 * 
	 */
	public List<LabRoleVo> getAllLabRoleList() throws GlobalException;
	
	/**
	 * 查看下角色名称是否重复 0可以新增 1 不可
	 * 
	 * @param rolename
	 *            角色名称
	 * @throws GlobalException
	 * @return 返回类型 String 
	 * 
	 */
	public String isExist4RoleName(String name) throws GlobalException;
	
	/**
	* 根据用户id获得角色信息列表
	* 
	* @param userId
	*            用户id
	* @throws GlobalException
	* @return 返回类型 返回具有某一角色的用户列表
	* 
	*/
	public List getLabRoleListByUserId(String userId) throws GlobalException;
	/**
	* 根据用户id和orgId获得角色信息列表
	* @param userId
	*            用户id
	* @throws GlobalException
	* @return 返回类型 返回具有某一角色的用户列表
	* 
	*/
	public List getLabRoleList(String userId,String orgId) throws GlobalException;
	
	/**
	 * 查看下面是否有子类 0可以删除 1 不可以删除
	 * 
	 * @param ids
	 *            角色id
	 * @throws GlobalException
	 * @return 返回类型 String 
	 * 
	 */
	public String checkDeleteBatchRole(String[] ids) throws GlobalException;
	
	/**
	 * 根据用户id获是角色id组成的数组
	 * 
	 * @param userId
	 *            用户id
	 * @throws GlobalException
	 * @return 返回类型 String[] 返回角色id组成的数组
	 * 
	 */
	public String[] getLabRoleIdsArrayByUserId(String userId,String orgId)
	throws GlobalException;
	
	/**
	 * 根据用户id获是角色名组成的数组
	 * 
	 * @param userId
	 *            用户id
	 * @throws GlobalException
	 * @return 返回类型 String[] 返回角色名组成的数组
	 * 
	 */
	public String[] getLabRoleNamesArrayByUserId(String userId,String orgId)
	throws GlobalException;

	/**
	 * 角色功能赋权
	 * 
	 * @param roleId
	 *            角色id
	 * @param funIds
	 *            功能id
	 * @throws GlobalException
	 * 
	 */
	public boolean addLabRoleFuns(String roleId, String funIds)
	throws GlobalException ;
	
	/**
	 * 查询定义  提供下拉框
	 * @return 角色信息Json
	 * @throws GlobalException
	 */
	public String getLabRoleList4Json()throws GlobalException ;

	/**
	 * 获取角色信息中首页展示信息
	 * @param userId
	 * @return
	 * @throws GlobalException
	 */
		public Set getLabPortlets(String userId)throws GlobalException;
}
