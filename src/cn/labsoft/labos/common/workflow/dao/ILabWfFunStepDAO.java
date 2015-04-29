package cn.labsoft.labos.common.workflow.dao;


import cn.labsoft.labos.common.workflow.entity.LabWfFunStep;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;


/**
 * 步骤定义 数据访问层接口
 * @author Quinn
 */
public interface ILabWfFunStepDAO extends IBaseDAO {
	
	/**
	 * 增加流程步骤
	 * @param po 流程步骤持久化对象
	 * @return 流程步骤对象
	 * @throws GlobalException 
	 */
	public LabWfFunStep addLabWfFunStep(LabWfFunStep po) throws GlobalException;
	/**
	 * 删除流程步骤
	 * @param po 流程步骤持久化对象
	 * @return true or false
	 */
	public boolean delLabWfFunStep(LabWfFunStep po)throws GlobalException;
	/**
	 * 修改流程步骤
	 * @param po 流程步骤持久化对象
	 * @return true or false
	 */
	public boolean updateLabWfFunStep(LabWfFunStep po)throws GlobalException;
	/**
	 * 获取某一流程步骤详情
	 * @param id
	 * @return 流程步骤对象
	 */
	public LabWfFunStep getLabWfFunStepById(String id)throws GlobalException;

}