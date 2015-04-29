package cn.labsoft.labos.source.quality.service;

import java.util.List;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaManageCheckPlanVo;




public interface ILabQuaManageCheckPlanService {
	/**
	 * 添加管理评审计划对象信息
	 * @param labQuaManageCheckPlanVo 管理评审计划对象
	 * @return 返回类型 管理评审计划对象(LabQuaManageCheckPlanVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaManageCheckPlanVo addLabQuaManageCheckPlan(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo)throws GlobalException;
	/**
	 * 修改管理评审计划对象信息
	 * @param labQuaManageCheckPlanVo 管理评审计划对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaManageCheckPlan(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo)throws GlobalException;
	/**
	 * 软删除管理评审计划对象信息
	 * @param labQuaManageCheckPlanVo 管理评审计划对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaManageCheckPlan(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得管理评审计划对象信息
	 * @param id 管理评审计划Id
	 * @return 返回类型 管理评审计划对象(LabQuaManageCheckPlanVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaManageCheckPlanVo getLabQuaManageCheckPlan(String id)throws GlobalException;
	/**
	 * 获得管理评审计划对象信息列表
	 * @param labQuaManageCheckPlanVo 管理评审计划对象 
	 * @return 返回类型 管理评审计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaManageCheckPlanVo> getLabQuaManageCheckPlanList(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo)throws GlobalException;
	/**
	 * 根据pageResult获得管理评审计划对象分页信息列表，labQuaManageCheckPlanVo为备用参数
	 * @param labQuaManageCheckPlanVo 管理评审计划对象 
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaManageCheckPlanPR(LabQuaManageCheckPlanVo labQuaManageCheckPlanVo,PageResult pageResult )throws GlobalException;
}
