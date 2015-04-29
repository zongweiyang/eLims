package cn.labsoft.labos.common.workflow.dao;

import java.util.List;

import cn.labsoft.labos.common.workflow.entity.LabWfPath;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 条件路径定义数据访问层接口
 * @author Quinn
 */
public interface ILabWfPathDAO  extends IBaseDAO{
	
	/**
	 * 增加流程线路
	 * @param po 流程线路持久化对象
	 * @return  流程线路对象
	 * @throws GlobalException 
	 */
	public LabWfPath addPath(LabWfPath po) throws GlobalException;
	/**
	 * 删除流程线路
	 * @param po 流程线路持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean delPath(LabWfPath po) throws GlobalException;
	/**
	 * 获取流程线路list集合
	 * @param busid 业务id
	 * @return 流程线路集合
	 * @throws GlobalException 
	 */
	public List<LabWfPath> getPathListByBusId(String busid) throws GlobalException;
	/**
	 * 获取流程线路list集合
	 * @param busid 业务id
	 * @return 流程线路集合
	 * @throws GlobalException 
	 */
	public List<LabWfPath> getPathList(String startStepId,String endStepId) throws GlobalException;
	
}