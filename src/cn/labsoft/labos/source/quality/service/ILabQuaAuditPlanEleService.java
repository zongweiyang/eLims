package cn.labsoft.labos.source.quality.service;

import java.util.List;


import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditPlanEleVo;

public interface ILabQuaAuditPlanEleService {
	
	/**
	 * 增加内审计划对象信息
	 * @param labQuaAuditPlanEleVo 内审计划对象 
	 * @return 返回类型 内审计划对象(LabQuaAuditPlanEleVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditPlanEleVo addLabQuaAuditPlanEle(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo)throws GlobalException;
	/**
	 * 修改内审计划对象信息
	 * @param labQuaAuditPlanEleVo 内审计划对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditPlanEle(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo)throws GlobalException;
	/**
	 * 软删除内审计划对象信息
	 * @param ids 内审计划Ids
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaAuditPlanEle(String[] ids)throws GlobalException;
	/**
	 * 根据id获取内审计划对象信息
	 * @param id 内审计划Id
	 * @return 返回类型 内审计划对象(LabQuaAuditPlanEleVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditPlanEleVo getLabQuaAuditPlanEleVo(String id)throws GlobalException;
	/**
	 * 获得内审计划对象信息列表
	 * @param labQuaAuditPlanEleVo 内审计划对象 
	 * @return 返回类型 内审计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditPlanEleVo> getLabQuaAuditPlanEleList(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo)throws GlobalException;
	/**
	 * 根据pageResult获得内审计划对象分页信息列表，labQuaAuditPlanEleVo为备用参数
	 * @param labQuaAuditPlanEleVo 内审计划对象 
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAuditPlanElePR(LabQuaAuditPlanEleVo labQuaAuditPlanEleVo, PageResult pageResult)throws GlobalException;
}
