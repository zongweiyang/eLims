package cn.labsoft.labos.source.quality.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaCusVisit;



public interface ILabQuaCusVisitDAO extends IBaseDAO{
	/**
	 * 添加客户回访登记对象信息
	 * @param labQuaCusVisit 客户回访登记对象
	 * @return 返回类型 客户回访登记对象(LabQuaCusVisit)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaCusVisit addLabQuaCusVisit(LabQuaCusVisit labQuaCusVisit)throws GlobalException;
	/**
	 * 修改客户回访登记对象信息
	 * @param labQuaCusVisit 客户回访登记对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaCusVisit(LabQuaCusVisit labQuaCusVisit)throws GlobalException;
	/**
	 * 根据客户Id获得客户回访登记对象信息
	 * @param id 客户回访登记Id
	 * @return 返回类型 客户回访登记对象(LabQuaCusVisit) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaCusVisit getLabQuaCusVisit(String id)throws GlobalException;
	/**
	 * 删除客户回访登记对象信息
	 * @param labQuaCusVisit 客户回访登记对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaCusVisit(LabQuaCusVisit labQuaCusVisit)throws GlobalException;
	/**
	 * 根据hql获取客户回访登记对象分页信息列表
	 * @param hql 客户回访登记SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaCusVisitPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取客户回访登记对象信息列表
	 * @param hql 客户回访登记SQL
	 * @return 返回类型 客户回访登记对象集合(List) 
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaCusVisit> getLabQuaCusVisitByHQL(String hql)throws GlobalException;
}