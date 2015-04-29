package cn.labsoft.labos.base.user.dao;

import java.util.List;
import cn.labsoft.labos.base.user.entity.LabUserCred;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户证书 数据访问对象接口
 * @author Quinn
 */
public interface ILabUserCredDAO extends IBaseDAO {
	/**
	 * 增加用户证书信息
	 * @param labUserCred 封装了用户证书的持久化信息对象
	 * @return 已经持久的用户证书信息对象
	 * @throws GlobalException 
	 */
	public LabUserCred addLabUserCred(LabUserCred labUserCred) throws GlobalException;
	/**
	 * 删除用户证书信息
	 * @param ids 要删除的持久化信息对象id集合
	 * @return 删除结果True or False
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserCred(String ids[]) throws GlobalException;
	/**
	 * 修改用户证书信息
	 * @param labUserCred 封装了用户证书的持久化信息对象
	 * @return 修改结果True or False
	 * @throws GlobalException 
	 */
	public boolean updateLabUserCred(LabUserCred labUserCred) throws GlobalException;
	/**
	 * 查询用户证书信息
	 * @param id 用户证书id
	 * @return 代用用户证书信息的对象
	 * @throws GlobalException 
	 */
	public LabUserCred getLabUserCred(String id) throws GlobalException;
	/**
	 * 获取带用分页的用户证书信息集合
	 * @param hql 组合的查询语句
	 * @param pageResult 带有分页信息的集合对象
	 * @return pageResult 带有查询结果分页信息的集合对象
	 * @throws GlobalException 
	 */
	public PageResult getLabUserCredPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 获取用户证书信息的集合
	 * @param hql 组合的查询语句
	 * @return 用户证书信息集合
	 * @throws GlobalException 
	 */
	public List<LabUserCred> getLabUserCredList(String hql) throws GlobalException;
	
}
