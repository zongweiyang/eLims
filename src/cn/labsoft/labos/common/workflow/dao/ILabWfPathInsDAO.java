package cn.labsoft.labos.common.workflow.dao;



import cn.labsoft.labos.common.workflow.entity.LabWfPathIns;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;



/**
 * 条件路径定义实例数据访问层接口
 * @author Quinn
 */
public interface ILabWfPathInsDAO  extends IBaseDAO {
	
	
	/**
	 * 获取流程线路 
	 * @param insId 业务实例化id
	 * @param pathId 线路定义id
	 * @return 返回类型 
	 * @throws GlobalException 
	 */
	public LabWfPathIns getWfPathInsByIds(String insId,String pathId) throws GlobalException;
}