package cn.labsoft.labos.source.quality.service;

import java.util.List;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaProficiencyPlanVo;





public interface ILabQuaProficiencyPlanService {
	/**
	 * 添加比对验证计划对象信息
	 * @param labQuaProficiencyPlanVo 比对验证计划对象 
	 * @return 返回类型 比对验证计划对象(LabQuaProficiencyPlanVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiencyPlanVo addLabQuaProficiencyPlan(LabQuaProficiencyPlanVo labQuaProficiencyPlanVo)throws GlobalException;
	/**
	 * 修改比对验证计划对象信息
	 * @param labQuaProficiencyPlanVo 比对验证计划对象  
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaProficiencyPlan(LabQuaProficiencyPlanVo labQuaProficiencyPlanVo)throws GlobalException;
	/**
	 * 软删除比对验证计划对象信息
	 * @param ids 比对验证计划对象Id
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaProficiencyPlan(String[] ids)throws GlobalException;
	/**
	 * 根据id获取比对验证计划对象信息
	 * @param id 比对验证计划对象Id 
	 * @return 返回类型 比对验证计划对象(LabQuaProficiencyPlanVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiencyPlanVo getLabQuaProficiencyPlan(String id)throws GlobalException;
	/**
	 * 获得比对验证计划对象信息列表
	 * @param labQuaProficiencyPlanVo 比对验证计划对象 
	 * @return 返回类型 比对验证计划对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaProficiencyPlanVo> getLabQuaProficiencyPlanList(LabQuaProficiencyPlanVo labQuaProficiencyPlanVo)throws GlobalException;
	/**
	 * 根据pageResult获得比对验证计划对象分页信息列表，labQuaProficiencyPlanVo为备用参数
	 * @param labQuaProficiencyPlanVo 比对验证计划对象  
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaProficiencyPlanPR(LabQuaProficiencyPlanVo labQuaProficiencyPlanVo, PageResult pageResult)throws GlobalException;
	
}
