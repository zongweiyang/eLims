package cn.labsoft.labos.base.configs.dao;

import cn.labsoft.labos.base.configs.entity.LabConfig;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 系统配置数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
public interface ILabConfigDAO extends IBaseDAO{
	/**
	 * 新增配置
	 * @param po 封装数据持久化对象
	 * @return 持久化对象
	 * @throws GlobalException 
	 */
	public LabConfig addLabConfig(LabConfig po) throws GlobalException;
	/**
	 * 修改配置
	 * @param po 封装数据持久化对象
	 * @return 持久化对象
	 * @throws GlobalException 
	 */
	public LabConfig updateLabConfig(LabConfig po) throws GlobalException;
	/**
	 * 删除配置
	 * @param po 封装数据持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabConfig(LabConfig po) throws GlobalException;
	/**
	 * 查询配置信息
	 * @param id 对象id
	 * @return 持久化对象
	 * @throws GlobalException 
	 */
	public LabConfig getLabConfig(String id) throws GlobalException;
	/**
	 * 查询配置信息
	 * @param code 对象编号
	 * @return 持久化对象
	 * @throws GlobalException
	 */
	public LabConfig getLabConfigByCode(String code)throws GlobalException;
}
