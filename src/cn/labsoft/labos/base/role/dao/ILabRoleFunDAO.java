package cn.labsoft.labos.base.role.dao;

import java.util.List;

import cn.labsoft.labos.base.role.entity.LabRoleFun;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabRoleFunDAO extends IBaseDAO {
	/**
	 * 添加角色-功能关系对象信息
	 * @param labRoleFun 角色-功能关系对象
	 * @return 返回类型 角色-功能关系对象(LabRoleFun)
	 * @throws GlobalException 
	 */
	public LabRoleFun addLabRoleFun(LabRoleFun labRoleFun) throws GlobalException;
	/**
	 * 删除角色-功能关系对象信息
	 * @param labRoleFun  角色-功能关系对象
	 * @return 返回类型 boolean
	 * @throws GlobalException 
	 */
	public boolean delLabRoleFun(LabRoleFun labRoleFun) throws GlobalException;
	/**
	 * 修改角色-功能关系对象信息
	 * @param labRoleFun 角色-功能关系对象 
	 * @return 返回类型 boolean 
	 * @throws GlobalException 
	 */
	public boolean updateLabRoleFun(LabRoleFun labRoleFun) throws GlobalException;
	/**
	 * 根据roleFunId获取角色-功能关系对象信息
	 * @param roleFunId 角色-功能关系Id
	 * @return 返回类型 角色-功能关系对象(LabRoleFun) 
	 * @throws GlobalException 
	 */
	public LabRoleFun getLabRoleFun(String roleFunId) throws GlobalException;
	/**
	 * 根据roleId获取角色-功能关系对象信息列表
	 * @param roleId 角色Id
	 * @return 返回类型 角色-功能关系对象(LabRoleFun)  
	 * @throws GlobalException 
	 */
	public List<LabRoleFun> getLabRoleFunByRoleId(String roleId) throws GlobalException;
	/**
	 * 根据funId获取角色-功能关系对象信息列表
	 * @param funId 功能Id
	 * @return 返回类型 角色-功能关系对象(LabRoleFun) 
	 * @throws GlobalException 
	 */
	public List<LabRoleFun> getLabRoleFunByFunId(String funId) throws GlobalException;
}
