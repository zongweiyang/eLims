package cn.labsoft.labos.business.sample.dao;

import java.util.List;

import cn.labsoft.labos.business.sample.entity.LabSampCustomer;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 客户信息 数据访问对象接口
 * @author Quinn
 */
public interface ILabSampCustomerDAO extends IBaseDAO {
	/**
	 * 增加登记客户信息
	 * @param labSampCustomer 客户信息持久化对象
	 * @return  客户信息持久化对象
	 * @throws GlobalException 
	 */
	public LabSampCustomer addLabSampCustomer(LabSampCustomer labSampCustomer) throws GlobalException;
	/**
	 * 删除客户关系信息
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSampCustomer(String ids[]) throws GlobalException;
	/**
	 * 删除客户关系信息
	 * @param labSampCustomer 客户信息持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSampCustomer(LabSampCustomer labSampCustomer) throws GlobalException;
	/**
	 * 修改客户信息
	 * @param labSampCustomer 客户信息持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabSampCustomer(LabSampCustomer labSampCustomer) throws GlobalException;
	/**
	 * 查询客户信息
	 * @param id 对象id
	 * @return 客户信息持久化对象
	 * @throws GlobalException 
	 */
	public LabSampCustomer getLabSampCustomer(String id) throws GlobalException;
	/**
	 * 获取客户分页信息集合对象
	 * @param hql 组合查询语句
	 * @param pageResult 分页信息集合对象
	 * @return 带有结果的分页信息集合对象
	 * @throws GlobalException 
	 */
	public PageResult getLabSampCustomerPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 获取客户对象集合
	 * @param hql 组合查询语句
	 * @return 客户信息持久化对象集合
	 * @throws GlobalException 
	 */
	public List<LabSampCustomer> getLabSampCustomerList(String hql) throws GlobalException;
	
}
