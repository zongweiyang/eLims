package cn.labsoft.labos.source.quality.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiencyPlan;


public interface ILabQuaProficiencyPlanDAO extends IBaseDAO{
	/**
	 * 添加比对验证计划对象信息
	 * @param labQuaProficiencyPlan 比对验证计划对象
	 * @return 返回类型 比对验证计划对象(LabQuaProficiencyPlan)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiencyPlan addLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan)throws GlobalException;
	/**
	 * 修改比对验证计划对象信息
	 * @param labQuaProficiencyPlan 比对验证计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan)throws GlobalException;
	/**
	 * 根据客户Id获得比对验证计划对象信息
	 * @param id 比对验证计划Id
	 * @return 返回类型 比对验证计划对象(LabQuaProficiencyPlan)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiencyPlan getLabQuaProficiencyPlan(String id)throws GlobalException;
	/**
	 * 删除比对验证计划对象信息
	 * @param labQuaProficiencyPlan 比对验证计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaProficiencyPlan(LabQuaProficiencyPlan labQuaProficiencyPlan)throws GlobalException;
	/**
	 * 根据hql获取比对验证计划对象分页信息列表
	 * @param hql 比对验证计划SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaProficiencyPlanPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取比对验证计划对象信息列表
	 * @param hql 比对验证计划SQL
	 * @return 返回类型 比对验证计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaProficiencyPlan> getLabQuaProficiencyPlanByHQL(String hql)throws GlobalException;
}