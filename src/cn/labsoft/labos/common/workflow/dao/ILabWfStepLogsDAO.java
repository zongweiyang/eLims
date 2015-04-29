package cn.labsoft.labos.common.workflow.dao;

import java.util.List;
import cn.labsoft.labos.common.workflow.entity.LabWfStepLogs;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 步骤实例 数据访问层接口
 * @author Quinn
 */
public interface ILabWfStepLogsDAO extends IBaseDAO {

	/**
	 * 根据业务Id获取日志列表id
	 * @param busId 业务id
	 * @return 流程日志集合
	 * @throws GlobalException 
	 */
	public List<LabWfStepLogs> getWfStepLogsListByBusId(String busId) throws GlobalException;
	/**
	 * 获取日志列表id
	 * @param busId 业务id
	 * @param code 节点编码
	 * @return 流程日志集合
	 * @throws GlobalException 
	 */
	public List<LabWfStepLogs> getWfStepLogsList(String busId,String code) throws GlobalException;
}