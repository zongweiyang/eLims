package cn.labsoft.labos.base.role.dao;

import java.util.List;

import cn.labsoft.labos.base.role.entity.LabRole;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;


public interface ILabRoleDAO extends IBaseDAO {
	
	
	/**
	 * 增加角色对象信息
	 * 
	 * @param labRole
	 * 			   要操作的对象
	 * @return 返回类型 LabRole 要操作的对象
	 * @throws GlobalException 
	 */
	public LabRole addLabRole(LabRole labRole) throws GlobalException;
	
	/**
	 * 修改角色对象信息
	 * 
	 * @param labRole
	 * 			   要操作的对象
	 * @throws GlobalException 
	 * 
	 */
	public boolean updateLabRole(LabRole labRole) throws GlobalException;
	
	/**
	 * 删除角色对象信息
	 * 
	 * @param labRole
	 * 			   要操作的对象
	 * @throws GlobalException 
	 * 
	 */
	public boolean deleteLabRole(LabRole labRole) throws GlobalException;
	
	/**
	 * 根据Id查询角色对象信息
	 * 
	 * @param roleId
	 * 			  
	 * @return 返回类型 LabRole
	 * @throws GlobalException 
	 */
	public LabRole getLabRole(String roleId) throws GlobalException;
	
	/**
	 * 根据hql语句获取角色对象分页列表信息
	 * 
	 * @param hql
	 * 			HQL语句
	 * @param pageResult			  
	 * @return 返回类型 PageResult 返回分页对象
	 * @throws GlobalException 
	 */
	public PageResult getLabRolePR(String hql, PageResult pageResult) throws GlobalException;
	
	/**
	 * 根据hql语句获取角色列表
	 * 
	 * @param hql
	 * 			HQL语句
	 * 		  
	 * @return 返回类型 List 查询获得的LabRole集合
	 * @throws GlobalException 
	 */
	public List<LabRole> getLabRoleList(String hql) throws GlobalException;
	
}
