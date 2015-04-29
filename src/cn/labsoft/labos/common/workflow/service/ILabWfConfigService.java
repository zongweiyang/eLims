package cn.labsoft.labos.common.workflow.service;

import java.util.List;
import cn.labsoft.labos.common.workflow.vo.LabWfConfigVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 流程配置逻辑处理层
 * @author Administrator
 * 
 */
public interface ILabWfConfigService {
	/**
	 * 增加流程配置
	 * @param labWfConfigVo 对象
	 * @return 流程配置
	 * @throws GlobalException
	 */
	public LabWfConfigVo addLabWfConfig(LabWfConfigVo labWfConfigVo) throws GlobalException;;
	/**
	 * 删除流程配置
	 * @param ids对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabWfConfig (String[] ids) throws GlobalException;
	/**
	 * 假删除流程配置
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabWfConfig4Del(String[] ids) throws GlobalException;
	/**
	 * 修改流程配置
	 * @param labWfConfigVo 流程对象Vo
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabWfConfig(LabWfConfigVo labWfConfigVo) throws GlobalException;
	/**
	 * 查询流程配置
	 * @param id 对象id
	 * @return 流程配置对象
	 * @throws GlobalException
	 */
	public LabWfConfigVo getLabWfConfig(String id) throws GlobalException;
	/**
	 * 查询流程配置集合
	 * @param labWfConfigVo 流程配置对象
	 * @return 流程配置集合
	 * @throws GlobalException
	 */
	public List<LabWfConfigVo> getLabWfConfigList(LabWfConfigVo labWfConfigVo) throws GlobalException;
	/**
	 * 查询流程分页信息对象
	 * @param labWfConfigVo 流程配置对象
	 * @param pageResult 带有分页信息的对象
	 * @return 流程配置分页集合对象
	 * @throws GlobalException
	 */
	public PageResult getLabWfConfigPR(LabWfConfigVo labWfConfigVo,PageResult pageResult) throws GlobalException;
	
//	public boolean isExist4Code(String id,String code) throws GlobalException;
 }
