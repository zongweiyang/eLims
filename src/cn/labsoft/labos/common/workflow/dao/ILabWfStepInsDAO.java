package cn.labsoft.labos.common.workflow.dao;

import java.util.List;

import cn.labsoft.labos.common.workflow.entity.LabWfStepIns;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 步骤实例 数据访问层接口
 * @author Quinn
 */
public interface ILabWfStepInsDAO extends IBaseDAO {
	/**
	 *  获取某一流程步骤实例详情
	 * @param insId 业务实例化
	 * @param stepId 节点id
	 * @return 流程节点实例
	 * @throws GlobalException 
	 */ 
	public LabWfStepIns getWfStepIns(String insId,String stepId) throws GlobalException;
	/**
	 * 根据实例id获取实例流程节点列表
	 * @param busId 业务id
	 * @return 流程节点实例集合
	 * @throws GlobalException 
	 */
	public List<LabWfStepIns> getWfStepInsList(String busId) throws GlobalException;
	//以下是新模块集成用的
	
//	/**
//	 * 用于集成流程模块业务，获取下一步流程节点,可能有多个选择
//	 * @param busId 与其关联的流程业务ID
//	 * @param stepCode 当前步骤编码
//	 * @return 流程
//	 */
//	public List<LabWfStepIns> getThisNextStepIns(String busId,String stepCode);
}