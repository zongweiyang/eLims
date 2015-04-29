package cn.labsoft.labos.common.workflow.dao;


import java.util.List;

import cn.labsoft.labos.common.workflow.entity.LabWfVariable;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;


/**
 * 变量定义 数据访问层接口
 * @author Quinn
 */
public interface ILabWfVariableDAO extends IBaseDAO {
	
	/**
	 * 根据业务id获取流程变量list集合
	 * @param busid 业务id
	 * @return 流程变量集合
	 * @throws GlobalException 
	 */
	public List<LabWfVariable> getLabWfVarListByBusId(String busid) throws GlobalException;
	/**
	 * 增加流程变量
	 * @param labWfVariable  流程变量
	 * @return  流程变量
	 * @throws GlobalException 
	 */
	public LabWfVariable addLabWfVariable(LabWfVariable labWfVariable) throws GlobalException;
	/**
	 *  删除流程变量
	 * @param labWfVariable 流程变量
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabWfVariable(LabWfVariable labWfVariable) throws GlobalException;
	
	
	
}