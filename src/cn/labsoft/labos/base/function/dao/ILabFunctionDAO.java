package cn.labsoft.labos.base.function.dao;

import java.util.List;

import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 系统功能DAO接口
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
public interface ILabFunctionDAO extends IBaseDAO {
	/**
	 * 增加功能对象信息
	 * 
	 * @param labFunction
	 *            功能对象
	 * @return LabFunction
	 */
	public LabFunction addLabFunction(LabFunction labFunction) throws GlobalException;

	/**
	 * 修改功能对象信息
	 * 
	 * @param labFunction
	 *            功能对象
	 * @return LabFunction
	 */
	public LabFunction updateLabFunction(LabFunction labFunction) throws GlobalException;

	/**
	 * 删除功能对象信息
	 * 
	 * @param ids
	 *            id的数组合集
	 * @return boolean
	 */
	public boolean deleteLabFunction(String ids[]) throws GlobalException;

	/**
	 * 根据Id查询功能对象信息
	 * 
	 * @param id
	 *            功能的ID
	 * @return LabFunction
	 */
	public LabFunction getLabFunction(String id) throws GlobalException;

	/**
	 * 根据hql语句获取功能对象分页列表信息
	 * 
	 * @param hql
	 *            分页语句
	 * @param pageResult
	 *            分页对象
	 * @return PageResult
	 */
	public PageResult getLabFunctionPR(String hql, PageResult pageResult) throws GlobalException;

	/**
	 * 根据hql语句获取功能列表
	 * 
	 * @param hql
	 *            获取对象的hql语句
	 * @return List<LabFunction>
	 */
	public List<LabFunction> getLabFunctionList(String hql) throws GlobalException;

}