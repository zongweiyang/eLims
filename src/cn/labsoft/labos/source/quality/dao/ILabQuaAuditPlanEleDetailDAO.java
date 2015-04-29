package cn.labsoft.labos.source.quality.dao;

import java.util.List;


import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEleDetail;




public interface ILabQuaAuditPlanEleDetailDAO extends IBaseDAO{
	/**
	 * 添加内审计划详情对象信息
	 * @param labQuaInitAuditPlan 内审计划详情对象
	 * @return 返回类型 内审计划详情对象(LabQuaAuditPlanEleDetail) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditPlanEleDetail addLabQuaAuditPlanEleDetail(LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail)throws GlobalException;
	/**
	 * 修改内审计划详情对象信息
	 * @param labQuaInitAuditPlan
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditPlanEleDetail(LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail)throws GlobalException;
	/**
	 * 根据id获取内审计划详情对象信息
	 * @param id 内审计划Id
	 * @return 返回类型 内审计划详情对象(LabQuaAuditPlanEleDetail)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditPlanEleDetail getLabQuaAuditPlanEleDetail(String id)throws GlobalException;
	/**
	 * 根据hql获取内审计划详情对象信息
	 * @param hql 内审计划sql
	 * @return 返回类型 内审计划详情对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditPlanEleDetail> getLabQuaAuditPlanEleDetailByHQL(String hql)throws GlobalException;
}