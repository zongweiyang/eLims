package cn.labsoft.labos.common.workflow.dao;


import java.util.List;

import cn.labsoft.labos.common.workflow.entity.LabWfPathVar;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;


/**
 * 迁移变量关联定义 数据访问层接口
 * @author Quinn
 */
public interface ILabWfPathVarDAO extends IBaseDAO {
	
	
	/**
	 * 查询流程线路变量关系集合
	 * @param busid业务id
	 * @return 流程线路变量关系集合
	 * @throws GlobalException 
	 */
	public List<LabWfPathVar> getPathVarDefList(String pathId) throws GlobalException;
	/**
	 * 获取某流程所有线路上关联的条件
	 * @param busid 业务id
	 * @return 流程线路变量关系集合
	 * @throws GlobalException 
	 */
	public List<LabWfPathVar> getPathVarListByBusId(String busId) throws GlobalException;
	
	/**
	 * 删除流程线路变量关系
	 * @param busid 业务id
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabWfPathVar(LabWfPathVar labWfPathVar) throws GlobalException;
	
	/**
	 * 新增流程线路变量关系
	 * @param busid 业务id
	 * @return 流程线路变量关系对象
	 * @throws GlobalException 
	 */
	public LabWfPathVar addLabWfPathVar(LabWfPathVar labWfPathVar) throws GlobalException;
}