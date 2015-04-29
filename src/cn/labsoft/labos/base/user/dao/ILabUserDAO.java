package cn.labsoft.labos.base.user.dao;

import java.util.List;

import cn.labsoft.labos.base.user.entity.LabUser;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 用户管理数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabUserDAO extends IBaseDAO {
	/**
	 * 增加用户对象信息
	 * @param user 用户对象
	 * @return 带有用户序列id的对象
	 * @throws GlobalException 
	 */
	public LabUser addLabUser(LabUser user) throws GlobalException;
	/**
	 * 删除用户对象信息
	 * @param sysUser
	 * @return 删除结果True or False
	 * @throws GlobalException 
	 */
	public boolean delLabUser(String ids[]) throws GlobalException;
	/**
	 * 修改用户对象信息
	 * @param user
	 * @return  true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabUser(LabUser user) throws GlobalException;
	/**
	 * 根据Id查询用户对象信息
	 * @param userId
	 * @return 用户信息对象
	 * @throws GlobalException 
	 */
	public LabUser getLabUser(String userId) throws GlobalException;
	/**
	 * 获取带用分页的用户信息集合
	 * @param hql 组合的查询语句
	 * @param pageResult 带有分页信息的集合对象
	 * @return PageResult 带有查询结果分页信息的集合对象
	 * @throws GlobalException 
	 */
	public PageResult getLabUserPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 根据hql语句获取用户列表
	 * @param hql hql语句
	 * @return 用户信息集合
	 * @throws GlobalException 
	 */
	public List<LabUser> getLabUserList(String hql) throws GlobalException;
	
}
