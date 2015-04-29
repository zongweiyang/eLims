package cn.labsoft.labos.common.workflow.service;

import java.util.List;
import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfPathVarVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessVo;
import cn.labsoft.labos.common.workflow.vo.LabWfVariableVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 流程定义
 * @author Administrator
 * 
 */
public interface ILabWfProcessService {
	/**
	 * 获取已建模的流程分页信息
	 * @param processDefVo 流程定义对象
	 * @param pageResult 带有分页信息的查询对象
	 * @return PageResult 流程定义分页集合对象
	 * @throws GlobalException
	 */
	public PageResult getWfProcessPR(LabWfProcessVo processDefVo,PageResult pageResult) throws GlobalException;
	/**
	 * 获取已建模的流程分页信息
	 * @param processDefVo 流程定义对象
	 * @param pageResult 带有分页信息的查询对象
	 * @return PageResult 流程定义分页集合对象
	 * @throws GlobalException
	 */
	public PageResult getWfProcessPR4Logs(LabWfProcessVo processDefVo,PageResult pageResult) throws GlobalException;
	/**
	 * 获取已建模的流程list集合
	 * @param processDefVo 流程定义对象
	 * @return list 流程定义集合
	 * @throws GlobalException
	 */
	public List<LabWfProcessVo> getWfProcessList(LabWfProcessVo processDefVo) throws GlobalException;
	/**
	 * 根据流程源编码获取当前流程源已经建模流程
	 * @param processDefVo 流程定义对象
	 * @return list  
	 * @throws GlobalException
	 */
	public List<LabWfProcessVo> getWfProcessList(String busCode) throws GlobalException;
	/**
	 * 获取流程图 XML 信息
	 * @param busId 业务id
	 * @param status 状态
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public String  getFlowDefXmlByBusId(String busId,String status) throws GlobalException;
	
	/**
	 * @Title:  保存或修改流程图 XML 信息
	 * @param flowXml 模版内容
	 * @param busId 业务id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean  saveOrUpdateFlowDefXml(String flowXml,String busId) throws GlobalException;
	/**
	 * 保存流程模型
	 * @param processDefVo 流程定义对象
	 * @return 流程定义对象
	 * @throws GlobalException
	 */
	public LabWfProcessVo addWfProcess(LabWfProcessVo processDefVo) throws GlobalException;
	/**
	 * 修改流程模型
	 * @param processDefVo 流程定义对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateWfProcess(LabWfProcessVo processDefVo) throws GlobalException;
	/**
	 * 启用流程模型
	 * @param processDefVo 流程定义对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateProcess2Open(LabWfProcessVo processDefVo) throws GlobalException;
	/**
	 * 获取根据Id流程模型
	 * @param id 对象id
	 * @return 流程定义对象
	 * @throws GlobalException
	 */
	public LabWfProcessVo getWfProcess(String id) throws GlobalException;
	/**
	 * 根据Id删除流程模型
	 * @param busId 业务id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean delWfProcess(String busId) throws GlobalException;
	
	/**
	 * 获取当前存储的功能列表
	 * @param processId 流程定义id
	 * @return  流程定义节点步骤集合
	 * @throws GlobalException
	 */
	public List<LabWfFunStepVo> getWfFunStepList(String processId) throws GlobalException;
	
	/**
	 * 获取变量list
	 * @param processId 流程定义id
	 * @return 流程关联变量集合
	 * @throws GlobalException
	 */
	public List<LabWfVariableVo> getWfVariableList(String processId) throws GlobalException;
	/**
	 * 获取每个线路上使用的变量关系集合
	 * @param ids 对象id集合
	 * @return 流程关联变量集合
	 * @throws GlobalException
	 */
	public List<LabWfPathVarVo> getWfPathVarList(String[] ids) throws GlobalException;
	/**
	 * @Title:  该功能是否已经开启可用的流程
	 * @param funId 功能id
	 * @return 返回类型 
	 * @throws GlobalException
	 */
	public boolean isHasOpenedProcess4Fun(String funId) throws GlobalException;
	
	public List<LabWfFunStepVo> getLabWfFunStepVo(LabWfProcessVo processDefVo)throws GlobalException;
	
}
