package cn.labsoft.labos.common.workflow.dao;

import java.util.List;

import cn.labsoft.labos.common.workflow.entity.LabWfStep;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;


/**
 * 步骤定义 数据访问层接口
 * @author Quinn
 */
public interface ILabWfStepDAO extends IBaseDAO {
	
	/**
	 * 增加流程节点
	 * @param po 流程定义节点
	 * @return 流程定义节点
	 * @throws GlobalException 
	 */
	public LabWfStep addWfStep(LabWfStep po) throws GlobalException;
	/**
	 * 删除流程步骤
	 * @param po 流程定义节点
	 * @return true or false
	 */
	public boolean delWfStep(LabWfStep po)throws GlobalException;
	/**
	 * 修改流程步骤
	 * @param po 流程定义节点
	 * @return true or false
	 */
	public boolean updateWfStep(LabWfStep po)throws GlobalException;
	/**
	 * 获取某一流程步骤详情
	 * @param id 对象id
	 * @return 流程定义节点
	 */
	public LabWfStep getWfStepById(String id)throws GlobalException;
	/**
	 * 获取某一流程步骤详情
	 * @param busId 业务id
	 * @param uuid  
	 * @return  流程定义节点
	 */
	public LabWfStep getWfStepBybusIdAndUuid(String busId,String uuid)throws GlobalException;
	/**
	 * 根据流程id获取该流程所有步骤名称
	 * @param busid 业务id
	 * @return 流程定义节点集合
	 */
	public List<LabWfStep> getStepAllListByBusId(String busid)throws GlobalException;
	/**
	 * 根据流程id获取该流程所有步骤名称(除过开始和结束节点)
	 * @param busid 业务id
	 * @return 流程定义节点集合
	 */
	public List<LabWfStep> getStepListByBusId(String busid)throws GlobalException;

}