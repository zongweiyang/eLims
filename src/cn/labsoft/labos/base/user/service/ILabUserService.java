package cn.labsoft.labos.base.user.service;

import java.io.Serializable;
import java.util.List;

import cn.labsoft.labos.base.role.vo.LabRoleVo;
import cn.labsoft.labos.base.user.vo.LabUserFunVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户管理服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
@SuppressWarnings("unchecked")
public interface ILabUserService {

	/**
	 * 增加用户
	 * @param userVo 用户对象Vo
	 * @return 带有持久化的用户对象
	 * @throws GlobalException
	 */
	public LabUserVo addLabUser(LabUserVo labUserVo)throws GlobalException;
	/**
	 * 修改用户
	 * @param userVo 用户对象Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUser(LabUserVo labUserVo)throws GlobalException;
	/**
	 * 修改用户
	 * @param userVo 用户对象Vo
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUser4Org(LabUserVo labUserVo)throws GlobalException;
	/**
	 * 修改用户
	 * @param userVo 用户对象Vo
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUser4self(LabUserVo labUserVo)throws GlobalException;
	/**
	 * 修改用户权限
	 * @param userVo 用户对象Vo
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUser4Fun(LabUserVo labUserVo)throws GlobalException;
	/**
	 * 删除用户权限（若为父节点，则子节点级联删除）
	 * @param userVo 用户对象Vo
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabUserFun(String userId,String funId)throws GlobalException;
	/**
	 * 增加用户功能关系
	 * @param userVo 用户对象Vo
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean addLabUserFun(LabUserVo labUserVo)throws GlobalException;
	/**
	 * 修改用户登陆信息
	 * @param userId用户id
	 * @param sessionId
	 * @param ip
	 * @return  true or false
	 * @throws GlobalException
	 */
	public boolean updateLabUser4Login(String userId,String sessionId,String ip)throws GlobalException;
	/**
	 * 修改用户退出信息
	 * @param userId用户id
	 * @return true or false
	 * @throws GlobalException
	 */
	
	public boolean updateLabUser4Logout(String userId)throws GlobalException;
	/**
	 * 删除用户信息（包含单个和批量删除）
	 * @param userId 用户id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean delLabUser(String userId[])throws GlobalException;
	/**
	 * 根据Id查看用户信息
	 * @param userId 用户id
	 * @return 用户对象
	 * @throws GlobalException
	 */
	public LabUserVo getLabUser(String userId)throws GlobalException;
	/**
	 * 根据Id查看用户信息
	 * @param userId用户id
	 * @return 用户对象
	 * @throws GlobalException
	 */
	public LabUserVo getLabUser4AllInfo(String userId)throws GlobalException;
	
	/**
	 * 用户赋权获取信息
	 * @param userId 用户id
	 * @return 用户对象
	 * @throws GlobalException
	 */
	public LabUserVo getLabUser4FunByUserId(String userId)throws GlobalException;
	/**
	 * 根据登陆名和密码获取用户信息
	 * @param logName 用户登陆名
	 * @param pwd 用户登陆密码
	 * @return 用户对象
	 * @throws GlobalException
	 */
	public LabUserVo getLabUserByLogInNameAndPwd(String logName,String pwd)throws GlobalException;
	/**
	 * 根据登陆名获取用户信息
	 * @param logName 用户登陆名
	 * @return 用户对象
	 * @throws GlobalException
	 */
	public LabUserVo getLabUserByLogInName(String logName)throws GlobalException;
	/**
	 * 获取用户分页信息
	 * @param userVo 用户对象
	 * @param pageResult 用户分页集合对象
	 * @return 用户分页集合对象
	 * @throws GlobalException
	 */
	public PageResult getLabUserPR(LabUserVo userVo,PageResult pageResult)throws GlobalException;
	/**
	 * 获取用户分页信息（组织）
	 * @param userVo 用户对象
	 * @param pageResult 用户分页集合对象
	 * @return 用户分页集合对象
	 * @throws GlobalException
	 */
	public PageResult getLabUserPR4Org(LabUserVo userVo,PageResult pageResult)throws GlobalException;
	/**
	 * 获取用户列表
	 * @param userVo 用户对象
	 * @return 用户对象集合
	 * @throws GlobalException
	 */
	public List<LabUserVo> getLabUserList(LabUserVo userVo)throws GlobalException;
	/**
	 * 检测用户登陆名和U盾是否匹配
	 * @param loginName 用户登陆名
	 * @param uksn 盾
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean isExist4UserAndUksn(String loginName,String uksn)throws GlobalException;
	
	/**
	 * 检测用户登陆名和IP是否匹配
	 * @param loginName登陆名
	 * @param ip用户id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean isExist4UserAndIp(String loginName,String ip)throws GlobalException;
	/**
	 * 检测登陆名是否重复
	 * @param loginName登陆名
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean isExist4LoginName(String loginName)throws GlobalException;
	/**
	 * 检测登陆名是否重复
	 * @param loginName
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean isTrue4Pwd(String userId,String pwd)throws GlobalException;
	/**
	 * 获取具有某功能的用户列表
	 * @param funId功能id
	 * @return 用户对象集合
	 * @throws GlobalException
	 */
	public List<LabUserVo> getLabUserListByFunId(String funId) throws GlobalException;
	/**
	 * 根据组织id获取的用户列表
	 * @param orgId组织id
	 * @return  用户对象集合
	 * @throws GlobalException
	 */
	public List<LabUserVo> getLabUserListByOrgId(String orgId) throws GlobalException;
	/**
	 * 根据角色名称获取人员 列表
	 * @param roleName 角色名称
	 * @return 用户对象集合
	 * @throws GlobalException 
	 */
	public List<LabUserVo> getLabUserListByRoleName(String roleName) throws GlobalException;
	/**
	 * 角色功能修改  进行同步(Stone)
	 * @param labRoleVo
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserFun4Add(LabRoleVo labRoleVo) throws GlobalException;
	/**
	 * 用户当前组织当前功能下的权限信息
	 * @param userId
	 * @return 用户功能对象信息
	 * @throws GlobalException
	 */
	public LabUserFunVo getLabUserFun(String userId,String orgId,String funId)throws GlobalException;
	
	
}
