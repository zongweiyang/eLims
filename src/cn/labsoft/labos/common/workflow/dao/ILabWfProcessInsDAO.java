package cn.labsoft.labos.common.workflow.dao;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 流程实例 数据访问层接口
 * @author Quinn
 */
public interface ILabWfProcessInsDAO extends IBaseDAO {
	
	/**
	 * 获取流程实例
	 * @param id 对象id
	 * @return 流程实例
	 */
	public LabWfProcessIns getWfProcessById(String id)throws GlobalException;
	/**
	 * 获取流程实例
	 * @param id 业务id
	 * @return 流程实例
	 * @throws GlobalException 
	 */
	public LabWfProcessIns getWfProcessByBusId(String busId) throws GlobalException;
	
	/**
	 * 获取流程实例当前所处步骤 service层
	 * 用于集成流程模块业务，每个流程业务获取列表页面的循环来调用，
	 * @param  insId 当前业务实例id
	 * @param  返回节点名称字符串
	 * @return 返回类型 
	 */
	public String getLabWfStepStrByInsId(String insId)throws GlobalException;
	/**
	 * 验证当前业务当前节点是否完成
	 * 用于集成流程模块业务，每个流程业务获取列表页面的循环来调用，
	 * @param  insId 当前业务实例id
	 * @param  返回节点名称字符串
	 * @return 返回类型 
	 */
	public String[] getLabWfStepByInsId(String insId)throws GlobalException;
	/**
	 *真删除业务流程的实例化 service层
	 *用于集成流程模块业务，每个流程业务获取列表页面的循环来调用，
	 * @param insId 当前业务实例id
	 * @return 返回类型 
	 * @throws true or false
	 */
	public boolean delLabWfProcessInsByBusId(String busId)throws GlobalException;
	/**
	 *假删除业务流程的实例化 service层
	 *用于集成流程模块业务，每个流程业务获取列表页面的循环来调用，
	 * @param insId 当前业务实例id
	 * @return 返回类型 
	 * @throws true or false
	 */
	public boolean updateLabWfProcessIns4delByBusId(String busId)throws GlobalException;
	/**
	 *查看当前数据是否是退回 (service层)
	 * @param busId 流程实例业务Id
	 * @param stepBusId 节点实例业务Id
	 * @param funId 当前节点id
	 * @return true or false
	 */
	public boolean checkLabWfProcessIns4isBack(String busId,String stepBusId,String funId)throws GlobalException;
	/**
	 *独立流程和节点实例化（每个节点一次操作）
	 *用于集成流程模块业务，每个add方法或者update方法来调用
	 * @param busId     实例流程的业务id
	 * @param busType   业务类型
	 * @param funId 当前功能id
	 * @param msg 审批意见
	 * @param result 审批结果
	 * @return 返回类型 
	 */
	public LabWfProcessIns addLabWfStepIns(String busId,String busType,String funId,String msg,String result)throws GlobalException;
	/**
	 * 流程和节点实例化（该实例业务作为其他模块接口实例时调用）
	 * 会默认初始化实例及流程的第一个节点实例
	 * @param busId     实例流程的业务id
	 * @param busType   实例类型
	 * @return 返回类型 
	 */
	public LabWfProcessIns addLabWfStepIns4Other(String busId,String busType)throws GlobalException;
	
	/**
	 * 流程和节点实例化 （每个节点可能多次操作）
	 * 用于集成流程模块业务，每个add方法或者update方法来调用
	 * @param curBusId 当前实例节点的业务id
	 * @param nextBusId 下一个实例节点的业务id
	 * @param busType 业务类型
	 * @param funId 当前功能id
	 * @param msg 审批意见
	 * @param result 审批结果
	 * @param autoType 提交类型 0全体 1部分
	 * @param nextId 下一步操作者id集合
	 * @param nextType 下一步操作类型 user or org
	 * @see LabWfConstant
	 * @return 返回类型 
	 */
	public LabWfProcessIns addLabWfStepIns(String curBusId,String nextBusId,String busType,String funId,String msg,String result,String autoType,String[] nextId,String nextType)throws GlobalException;
	/**
	 * 给已经结束的节点实例化下一个节点 （每个节点可能多次操作）
	 * 用于集成流程模块业务，每个add方法或者update方法来调用
	 * @param curBusId 当前实例节点的业务id
	 * @param nextBusId 下一个实例节点的业务id
	 * @param busType 业务类型
	 * @param funId 当前功能id
	 * @param msg 业务注释
	 * @param nextId 下一步操作者
	 * @param nextType 下一步操作类型
	 * @return 返回类型 
	 */
	public LabWfProcessIns addLabWfStepIns4Finish(String curBusId,String nextBusId,String funId,String msg,String result,String[] nextId,String nextType)throws GlobalException;
	/**
	 * 用于关闭某个业务在某个节点的多个实例
	 * @param insId 当前业务节点id
	 * @return 返回类型 
	 * @throws true or false
	 */
	public boolean delLabWfStepIns(String curBusId,String funId,String msg)throws GlobalException;
	/**
	 * 用于关闭某个业务的实例节点
	 * @param insId 当前业务节点id
	 * @return 返回类型 
	 * @throws true or false
	 */
	public boolean delLabWfStepIns(String curBusId,String msg)throws GlobalException;
}