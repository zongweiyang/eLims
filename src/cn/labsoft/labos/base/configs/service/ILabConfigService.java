package cn.labsoft.labos.base.configs.service;

import java.util.List;

import cn.labsoft.labos.base.configs.vo.LabConfigVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 系统配置服务层对象接口
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
public interface ILabConfigService {
	/**
	 * 获取所有配置信息对象集合
	 * @return 配置信息集合
	 * @throws GlobalException
	 */
	public List<LabConfigVo> getLabConfigListAll() throws GlobalException;
	/**
	 * 增加配置信息
	 * @param labConfigVo 配置信息对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean addLabConfig(LabConfigVo labConfigVo)throws GlobalException;
	/**
	 * 系统初始化配置信息
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean addLabConfig4InitSystem()throws GlobalException;
	/**
	 * 修改配置信息
	 * @param labConfigVo 配置信息对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabConfig(LabConfigVo labConfigVo)throws GlobalException;
	/**
	 * 假删除配置信息
	 * @param id 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabConfig4Del(String id)throws GlobalException;
	/**
	 * 查询配置信息
	 * @param code 配置编号
	 * @return 配置对象Vo
	 * @throws GlobalException
	 */
	public LabConfigVo getLabConfigByCode(String code)throws GlobalException;	
	
	/**
	 * 
	 * @Title:  
	 * @Description: TODO
	 * @param @param code
	 * @param @return
	 * @param @throws GlobalException  
	 * @return 返回类型 
	 * @throws
	 */
	public String getLabConfigValue(String code)throws GlobalException;	
	/**
	 * 查询配置信息
	 * @param category 参数类型
	 * @return 配置对象Vo
	 * @throws GlobalException
	 */
	public List<LabConfigVo> getLabConfigByCategory(String category)throws GlobalException;
	/**
	 * 验证重复性
	 * @param code 配置编号
	 * @param id 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean hasLabConfig4Code(String code,String id)throws GlobalException;	
	
	/**
	 * 初始化value
	 * @param labConfigVo
	 * @return
	 * @throws GlobalException
	 */
	public boolean initLabConfig4Value(LabConfigVo labConfigVo)throws GlobalException;
}
