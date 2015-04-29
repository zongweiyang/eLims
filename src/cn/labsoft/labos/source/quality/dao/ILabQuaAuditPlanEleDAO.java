package cn.labsoft.labos.source.quality.dao;

import java.util.List;


import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEle;




public interface ILabQuaAuditPlanEleDAO extends IBaseDAO{
	/**
	 * 增加内审计划对象信息
	 * @param labQuaAuditPlanEle 内审计划对象
	 * @return 返回类型 内审计划对象(LabQuaAuditPlanEle)
	 * @throws GlobalException 
	 * @author amy
	 */
	public LabQuaAuditPlanEle addLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle)throws GlobalException;
	/**
	 * 修改内审计划对象信息
	 * @param labQuaAuditPlanEle 内审计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle)throws GlobalException;
	/**
	 * 根据id获取内审计划对象信息
	 * @param labQuaAuditPlanEle  内审计划对象
	 * @return 返回类型 内审计划对象(LabQuaAuditPlanEle)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditPlanEle getLabQuaAuditPlanEle(String id)throws GlobalException;
	
	/**
	 * 删除内审计划对象信息
	 * @param labQuaAuditPlanEle  内审计划对象 
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle)throws GlobalException;
	/**
	 * 根据hql获取内审计划分页信息列表
	 * @param hql 内审计划sql
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAuditPlanElePR(String hql,PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取内审计划对象信息列表
	 * @param hql 内审计划sql
	 * @param pageResult
	 * @return  返回类型 内审计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditPlanEle> getLabQuaAuditPlanEleByHQL(String hql)throws GlobalException;
}