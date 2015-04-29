package cn.labsoft.labos.source.quality.service;

import java.util.List;


import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaInitAuditPlanVo;





public interface ILabQuaInitAuditPlanService {
	/**
	 * 添加初始化评审计划对象信息
	 * @param labQuaInitAuditPlanVo 初始化评审计划对象
	 * @return 返回类型 初始化评审计划对象(LabQuaInitAuditPlanVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaInitAuditPlanVo addLabQuaInitAuditPlan(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo)throws GlobalException;
	/**
	 * 修改初始化评审计划对象信息
	 * @param labQuaInitAuditPlanVo 初始化评审计划对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaInitAuditPlan(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo)throws GlobalException;
	/**
	 * 软删除初始化评审计划对象信息
	 * @param ids  初始化评审计划Ids
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaInitAuditPlan(String[] ids)throws GlobalException;
	/**
	 * 根据id获得初始化评审计划对象信息
	 * @param id 初始化评审计划Id
	 * @return 返回类型 初始化评审计划对象(LabQuaInitAuditPlanVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaInitAuditPlanVo getLabQuaInitAuditPlanVo(String id)throws GlobalException;
	/**
	 * 获得根据id获得初始化评审计划对象信息列表
	 * @param labQuaInitAuditPlanVo
	 * @return 返回类型 初始化评审计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaInitAuditPlanVo> getLabQuaInitAuditPlanList(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo)throws GlobalException;
	/**
	 * 根据pageResult获得初始化评审计划对象分页信息列表，labQuaInitAuditPlanVo为备用参数
	 * @param labQuaInitAuditPlanVo 初始化评审计划对象 
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaInitAuditPlanPR(LabQuaInitAuditPlanVo labQuaInitAuditPlanVo, PageResult pageResult)throws GlobalException;
	/**
	 * 根据planId和month获得初始化评审计划对象信息
	 * @param planId 计划Id
	 * @param month 月份
	 * @return 返回类型 初始化评审计划对象集合(List) 
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaInitAuditPlanVo> getLabQuaInitAuditPlanList(String planId,String month)throws GlobalException;
	
}
