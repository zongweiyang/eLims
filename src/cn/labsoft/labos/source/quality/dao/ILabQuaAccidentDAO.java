package cn.labsoft.labos.source.quality.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaAccident;

public interface ILabQuaAccidentDAO extends IBaseDAO{
	/**
	 * 添加事故报告对象信息
	 * @param labQuaAccident 事故报告对象
	 * @return 返回类型 事故报告对象(LabQuaAccident)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAccident addLabQuaAccident(LabQuaAccident labQuaAccident)throws GlobalException;
	/**
	 * 修改事故报告对象信息
	 * @param labQuaAccident 事故报告对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAccident(LabQuaAccident labQuaAccident)throws GlobalException;
	/**
	 * 根据客户Id获得事故报告对象信息
	 * @param id 事故报告对象Id
	 * @return 返回类型 事故报告对象(LabQuaAccident) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAccident getLabQuaAccident(String id)throws GlobalException;
	/**
	 * 删除事故报告对象信息
	 * @param labQuaAccident 事故报告对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaAccident(LabQuaAccident labQuaAccident)throws GlobalException;
	/**
	 * 根据hql获取事故报告对象分页信息列表
	 * @param hql 事故报告sql
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAccidentPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取事故报告对象信息列表
	 * @param hql 事故报告sql
	 * @return 返回类型 事故报告对象集合(List) 
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAccident> getLabQuaAccidentByHQL(String hql)throws GlobalException;
}