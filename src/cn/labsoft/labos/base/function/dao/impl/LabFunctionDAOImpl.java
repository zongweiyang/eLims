package cn.labsoft.labos.base.function.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 系统功能DAO实现类
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@Repository(value = "labFunctionDAO")
public class LabFunctionDAOImpl extends BaseDAO implements ILabFunctionDAO {
	/**
	 * 增加功能对象信息
	 * 
	 * @param labFunction
	 * @return LabFunction
	 */
	@Override
	public LabFunction addLabFunction(LabFunction labFunction) throws GlobalException {
		super.save(labFunction);
		return labFunction;
	}

	/**
	 * 删除功能对象信息
	 * 
	 * @param ids
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteLabFunction(String ids[]) throws GlobalException {
		List<LabFunction> list = super.findByIds(LabFunction.class, ids);
		try {
			super.deleteAll(list);
			return true;
		} catch (Exception e) {
			Log4J.error("删除功能出错");
			throw new GlobalException("" + e.getMessage());
		}
	}

	/**
	 * 根据Id查询功能对象信息
	 * 
	 * @param id
	 * @return LabFunction
	 */
	@Override
	public LabFunction getLabFunction(String id) throws GlobalException {
		LabFunction sysFunction = (LabFunction) super.findById(LabFunction.class, id);
		return sysFunction;
	}

	/**
	 * 根据hql语句获取功能列表
	 * 
	 * @param hql
	 * @return List<LabFunction>
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LabFunction> getLabFunctionList(String hql) throws GlobalException {
		List<LabFunction> list = super.find(hql);
		return list;
	}

	/**
	 * 根据hql语句获取功能对象分页列表信息
	 * 
	 * @param hql
	 *            hql语句
	 * @param pageResult
	 * @return PageResult
	 */
	@Override
	public PageResult getLabFunctionPR(String hql, PageResult pageResult) throws GlobalException {
		return super.getPageResult(hql, pageResult);
	}

	@Override
	public LabFunction updateLabFunction(LabFunction labFunction) throws GlobalException {
		super.update(labFunction);
		return labFunction;
	}
}
