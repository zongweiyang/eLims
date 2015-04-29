package cn.labsoft.labos.common.workflow.dao;
import cn.labsoft.labos.common.workflow.entity.LabWfProcess;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 *流程定义 数据访问层接口
 * @author Quinn
 */
public interface ILabWfProcessDAO extends IBaseDAO {
	/**
	 * 流程建模
	 * @param po  流程对象
	 * @return 流程对象
	 * @throws GlobalException 
	 */
	public LabWfProcess addWfProcess(LabWfProcess po) throws GlobalException;
	/**
	 * 删除流程模型
	 * @param po  流程对象
	 * @return true or false
	 */
	public boolean delWfProcess(LabWfProcess po)throws GlobalException;
	/**
	 * 修改流程模型
	 * @param po  流程对象
	 * @return true or false
	 */
	public boolean updateWfProcess(LabWfProcess po)throws GlobalException;
	/**
	 * 获取流程模型
	 * @param id 对象id
	 * @return 流程对象
	 */
	public LabWfProcess getWfProcess(String id)throws GlobalException;
	
	
}