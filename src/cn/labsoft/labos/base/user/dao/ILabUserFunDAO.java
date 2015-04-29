package cn.labsoft.labos.base.user.dao;

import java.util.List;

import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.user.entity.LabUserFun;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 用户功能关系数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabUserFunDAO extends IBaseDAO {
	/**
	 * 增加用户功能关系信息
	 * @param userFun 用户功能持久化对象
	 * @return 已持久的用户功能关系对象
	 * @throws GlobalException 
	 */
	public LabUserFun addLabUserFun(LabUserFun userFun) throws GlobalException;
	/**
	 * 删除用户功能关系信息
	 * @param userFun 用户功能持久化对象
	 * @return 删除结果True or False
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserFun(LabUserFun userFun) throws GlobalException;
	/**
	 * 获取用户功能关系列表，roleId为空时表示非角色赋权的功能列表
	 * @param userId 用户id
	 * @param roleId 角色id
	 * @return 用户功能关系对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunList(String userId,String roleId) throws GlobalException;
	/**
	 * 获取用户功能关系列表
	 * @param userId 用户id
	 * @param pFunId 父功能
	 * @return 用户功能关系对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunListByUserIdAndPfunId(String userId,String pFunId) throws GlobalException;
	/**
	 * 获取用户功能关系列表
	 * @param userId 用户id
	 * @param pFunId 父功能id
	 * @param orgId 组织id
	 * @return  用户功能关系对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunListByUserIdAndPfunId(String userId,String pFunId,String orgId) throws GlobalException;
	/**
	 * 获取用户功能关系对象
	 * @param userId 用id
	 * @param funId 功能id
	 * @param orgId 组织id
	 * @return  用户功能关系对象
	 * @throws GlobalException 
	 */
	public LabUserFun getLabUserFunByUserIdOrgIdfunId(String userId,String funId,String orgId) throws GlobalException;
	
	/**
	 * 获取用户功能关系列表
	 * @param userId 用户id
	 * @param funType 功能类型
	 * @param pFunId 父功能id
	 * @return 用户功能关系对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunList(String userId,String funType,String pFunId) throws GlobalException;
	/**
	 * 根据userId查询用户全部功能关系列表
	 * @param userId 用户id
	 * @return 用户功能关系对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunListByUserId(String userId) throws GlobalException;
	/**
	 * 查询用户在某个部门下的功能关系列表
	 * @param userId 用户id
	 * @param orgId 组织id
	 * @return 用户功能关系对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunListByUserIdAndOrgId(String userId,String orgId) throws GlobalException;
	/**
	 * 根据funId查询用户功能关系列表
	 * @param funId 功能id
	 * @return 用户功能关系对象集合 
	 * @throws GlobalException 
	 */
	public List<LabUserFun> getLabUserFunListByFunId(String funId) throws GlobalException;
	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param labUserFun
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public LabUserFun updateLabUserFun(LabUserFun labUserFun) throws GlobalException;
	/**
	 * 根据userId,funId,action查询用户权限
	 * @param userId 用户id
	 * @param funId 功能id
	 * @param action action 方法
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean getLabUserFunHavePower(String userId,String funId,String action) throws GlobalException;
}
