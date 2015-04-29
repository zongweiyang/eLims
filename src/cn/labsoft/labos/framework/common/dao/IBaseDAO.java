package cn.labsoft.labos.framework.common.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 
 * <strong>Title : BaseDAO </strong>. <br>
 * <strong>Description : 增加 删除 修改 查询 分页信息 接口类 <strong>Create on : Nov 13, 2009
 * 11:41:35 AM </strong>. <br>
 * <p>
 * <strong>Copyright (C) cn.com.common</strong> <br>
 * </p>
 * 
 * @author TonyLee<br>
 * @version <strong>rsLab v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 * @param <T>
 */

@SuppressWarnings("unchecked")
public interface IBaseDAO{
	
	/**
	 * 通过主键ID返回一个数据对象
	 * @param cla 要查询的实体类型
	 * @param id 可序列化的Id
	 * @return 返回类型 Object 要操作的对象
	 */
	public Object findById(Class cla, Serializable id);

	/**
	 * 返货 list(0)
	 * @Title:  
	 * @Description: TODO
	 * @param @param hql
	 * @param @return  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	public Object find0(String hql) throws GlobalException;
	/**
	 * 执行HQL查询,返回查询的结果下标为 0 的对象 
	 * 如果 i>list.size();则返回第一个得值
	 * @param Object Hql语句 int i 下标
	 * @return 返回类型 List 查询获得的实体集合
	 * @throws GlobalException 
	 */
	public Object find(String hql,int i) throws GlobalException;
	/**
	 * 执行HQL查询,返回查询的结果下标为 0 的对象 (不进行数据)
	 * 如果 i>list.size();则返回第一个得值
	 * @param Object Hql语句 int i 下标
	 * @return 返回类型 List 查询获得的实体集合
	 * @throws GlobalException 
	 */
	public Object find4All(String hql,int i) throws GlobalException;
	/**
	 * 执行HQL查询,返回查询的结果第
	 * @Title:  
	 * @Description: TODO
	 * @param @param hql
	 * @param @return  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	public List find(String hql) throws GlobalException;
	/**
	 * 执行HQL查询,返回查询的结果第(不进行数据)
	 * @Title:  
	 * @Description: TODO
	 * @param @param hql
	 * @param @return  
	 * @return 返回类型 
	 * @throws GlobalException 
	 * @throws
	 */
	public List find4All(String hql) throws GlobalException;

	/**
	 * 执行HQL查询,返回查询的结果(通过使用查询缓存)注意：返回值不能直接是泛型，要强制转换，通常此方法用在报表查询中（有数据更新延时）
	 * @param hql  hql语句
	 * @return 返回类型 List 查询获得的实体集合
	 */
	public List findByCache(String hql);

	/**
	 * 获得Hibernate中的一个session
	 * @return 返回类型 Session 获得一个当前session
	 */
	public Session openSession();

	/**
	 * 获取分页对象
	 * @param hql hql语句
	 * @param currentPage 当前页数
	 * @param pagerMethod 取值为首页(first)、上一页(previous)、下一页(next)、尾页(last)
	 * @param pageSize 页面显示记录条数
	 * @return 返回类型 PageResult 返回分页对象
	 * @throws GlobalException 
	 */
	public PageResult getPageResult(String hql, String currentPage,
			String pagerMethod, int pageSize) throws GlobalException;
	/**
	 * 获取分页对象
	 * @param hql hql语句
	 * @param pageResult 封装了分页信息的对象
	 * @return
	 * @throws GlobalException 
	 */
	public PageResult getPageResult(String hql,PageResult pageResult) ;
	/**
	 * 获取分页对象(不进行底层数据隔离的，用于基础数据的调用)
	 * @param hql hql语句
	 * @param pageResult 封装了分页信息的对象
	 * @return
	 * @throws GlobalException 
	 */
	public PageResult getPageResult4All(String hql,PageResult pageResult) throws GlobalException;
	/**
	 * 获取分页对象 
	 * @param sql sql语句
	 * @param pageResult 封装了分页对象信息
	 * @return
	 */
	public PageResult getPageResultBySQL(String sql,PageResult pageResult);


	/**
	 * 得到记录的count条数
	 * @param Hql Hql语句
	 * @return 返回类型 double 返回记录的条数
	 */
	public double getCount(String Hql);

	/**
	 * 获取表中某列最大值
	 * @param hql  Hql语句
	 * @return 返回类型 Object 要操作的对象
	 */
	public Object getColumnMaxValue(String hql);

	/**
	 * 得到记录的count条数
	 * @param hql  Hql语句
	 * @return 返回类型 long 返回记录条数
	 */
	public long getCountSize(String hql);
	/**
	 * 执行原始SQL 并返回结果集
	 * @param sql Sql查询语句
	 * @return 返回类型 List 返回查找结果的集合
	 */
	public List createSqlQuery(String sql);
	
	/**
	 * 执行原始增加、删除功能的SQL语句
	 * @param sql Sql查询语句
	 * @return 返回类型 List 返回查找结果的集合
	 * @throws GlobalException 
	 */
	public void executeSQL(String sql) throws Exception;
	/**
	 * 
	 * 通过ids数组查询返回对应class的对象集合
	 * @param Class className
	 * @return 返回类型 List 返回查找结果的集合
	 */
	public List findByIds(Class className,String[] ids);
	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param className
	 * @param @param ids
	 * @param @return  
	 * @return 返回类型 
	 * @throws
	 */
	public List findByIds(Class className,String ids);


}
