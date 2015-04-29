package cn.labsoft.labos.source.quality.dao;

import java.util.List;


import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheckPlan;




public interface ILabQuaManageCheckPlanDAO extends IBaseDAO{
	/**
	 * 增加管理评审计划对象信息
	 * @param labQuaManageCheckPlan 管理评审计划对象
	 * @return 返回类型 管理评审计划对象(LabQuaManageCheckPlan) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaManageCheckPlan addLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan)throws GlobalException;
	/**
	 * 修改管理评审计划对象信息
	 * @param labQuaManageCheckPlan 管理评审计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan)throws GlobalException;
	/**
	 * 根据id获取管理评审计划对象信息
	 * @param labQuaManageCheckPlan 管理评审计划对象
	 * @return 返回类型 管理评审计划对象(LabQuaManageCheckPlan) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaManageCheckPlan getLabQuaManageCheckPlan(String id)throws GlobalException;
	
	/**
	 * 删除管理评审计划对象信息
	 * @param labQuaManageCheckPlan 管理评审计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaManageCheckPlan(LabQuaManageCheckPlan labQuaManageCheckPlan)throws GlobalException;
	/**
	 * 根据hql获取管理评审计划对象分页信息列表
	 * @param hql 管理评审计划SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaManageCheckPlanPR(String hql,PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取管理评审计划对象信息列表
	 * @param hql 管理评审计划SQL
	 * @param pageResult
	 * @return 返回类型 管理评审计划对象集合(List) 
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaManageCheckPlan> getLabQuaManageCheckPlanByHQL(String hql)throws GlobalException;
}