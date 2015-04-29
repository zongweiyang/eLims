package cn.labsoft.labos.common.workflow.service;

import java.util.List;

import cn.labsoft.labos.common.workflow.vo.LabWfFunStepVo;
import cn.labsoft.labos.common.workflow.vo.LabWfProcessInsVo;
import cn.labsoft.labos.common.workflow.vo.LabWfStepLogsVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 流程实例化逻辑处理层
 * @author Administrator
 * 
 */
public interface ILabWfProcessInsService {
	
	/**
	 * 根据Id获取实例流程
	 * @param processDefVo 流程实例对象
	 * @return 流程实例对象
	 * @throws GlobalException
	 */
	public LabWfProcessInsVo getWfProcessIns(LabWfProcessInsVo vo) throws GlobalException;
//	/**
//	 * 根据Id获取实例流程
//	 * @param processDefVo
//	 * @return
//	 * @throws GlobalException
//	 */
//	public List<LabWfProcessInsVo> getSubProcessInsListByPinsIdAndProcessId(String pInsId,String processId) throws GlobalException;
	/**
	 * 获取流程状态集合
	 * @param funId 当前功能id
	 * @return 流程实例对象集合
	 * @throws GlobalException
	 */
	public List<LabWfFunStepVo> getLabWfFunStepList(String funId) throws GlobalException;
	/**
	 * 获取流程状态集合
	 * @param funId 当前功能id
	 * @return 流程实例对象集合
	 * @throws GlobalException
	 */
	public List<LabWfFunStepVo> getLabWfFunStepList4Bus(String bustype) throws GlobalException;
	/**
	 * 获取每个线路上使用的变量关系集合
	 * @param labWfProcessInsVo 流程实例对象
	 * @return 流程实例对象集合
	 * @throws GlobalException
	 */
	public List<LabWfStepLogsVo> getLabWfStepLogsList(LabWfProcessInsVo labWfProcessInsVo) throws GlobalException;
}
