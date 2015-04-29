package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaInitAuditPlan;


public interface ILabQuaInitAuditPlanDAO extends IBaseDAO{

	/**
	 * 添加初始化评审计划对象信息
	 * @param labQuaInitAuditPlan 初始化评审计划对象
	 * @return 返回类型 初始化评审计划对象(LabQuaInitAuditPlan)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaInitAuditPlan addLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan)throws GlobalException;
	/**
	 * 修改初始化评审计划对象信息
	 * @param labQuaInitAuditPlan 初始化评审计划对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan)throws GlobalException;
	/**
	 * 根据id获得初始化评审计划对象信息
	 * @param id  初始化评审计划Id
	 * @return 返回类型 初始化评审计划对象(LabQuaInitAuditPlan)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaInitAuditPlan getLabQuaInitAuditPlan(String id)throws GlobalException;
	/**
	 * 删除初始化评审计划对象信息
	 * @param labQuaInitAuditPlan 初始化评审计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaInitAuditPlan(LabQuaInitAuditPlan labQuaInitAuditPlan)throws GlobalException;
	/**
	 * 根据hql获取初始化评审计划对象分页信息列表
	 * @param hql 初始化评审计划SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaInitAuditPlanPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取初始化评审计划对象信息列表
	 * @param hql 初始化评审计划SQL 
	 * @return 返回类型 初始化评审计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaInitAuditPlan> getLabQuaInitAuditPlanByHQL(String hql)throws GlobalException;
}