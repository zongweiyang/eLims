package cn.labsoft.labos.common.workflow.dao;

import java.util.List;

import cn.labsoft.labos.common.workflow.entity.LabWfConfig;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 流程配置 数据访问层接口
 * @author Quinn
 */
public interface ILabWfConfigDAO extends IBaseDAO {
	/**
	 * 增加流程配置
	 * @param labWfConfig 流程配置信息持久化对象
	 * @return
	 * @throws GlobalException 
	 */
	public LabWfConfig addLabWfConfig(LabWfConfig labWfConfig) throws GlobalException;
	/**
	 * 删除流程配置对象
	 * @param ids 对象id
	 * @return true or false
	 */
	public boolean deleteLabWfConfig(String ids[])throws GlobalException;
	/**
	 * 删除流程配置对象
	 * @param labWfConfig 流程配置信息持久化对象
	 * @return true or false
	 */
	public boolean deleteLabWfConfig(LabWfConfig labWfConfig)throws GlobalException;
	/**
	 * 修改流程配置对象
	 * @param labWfConfig 流程配置信息持久化对象
	 * @return true or false
	 */
	public boolean updateLabWfConfig(LabWfConfig labWfConfig)throws GlobalException;
	/**
	 * 查询流程配置对象
	 * @param id 对象id
	 * @return 流程配置对象
	 */
	public LabWfConfig getLabWfConfig(String id)throws GlobalException;
	/**
	 * 流程配置分页信息对象
	 * @param hql 组合查询语句
	 * @param pageResult 带有分页信息对象
	 * @return 带有查询结果的分页信息对象
	 */
	public PageResult getLabWfConfigPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 流程配置信息集合
	 * @param hql 组合查询语句
	 * @return 流程配置信息集合
	 */
	public List<LabWfConfig> getLabWfConfigList(String hql)throws GlobalException;
	
}
