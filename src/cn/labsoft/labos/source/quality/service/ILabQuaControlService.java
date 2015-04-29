package cn.labsoft.labos.source.quality.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaControlVo;




public interface ILabQuaControlService {
	/**
	 * 添加监督抽查对象信息
	 * @param labQuaControlVo 监督抽查对象
	 * @return 返回类型 监督抽查对象(LabQuaControlVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaControlVo addLabQuaControl(LabQuaControlVo labQuaControlVo)throws GlobalException;
	/**
	 * 修改监督抽查对象信息
	 * @param labQuaControlVo 监督抽查对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaControl(LabQuaControlVo labQuaControlVo)throws GlobalException;
	/**
	 * 软删除监督抽查对象信息
	 * @param labQuaControlVo 监督抽查对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaControl(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得监督抽查对象信息
	 * @param id 监督抽查Id
	 * @return 返回类型 监督抽查对象(LabQuaControlVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaControlVo getLabQuaControl(String id)throws GlobalException;
	/**
	 * 获得监督抽查对象信息列表
	 * @param labQuaControlVo 监督抽查对象  
	 * @return 返回类型 监督抽查对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaControlVo> getLabQuaControlList(LabQuaControlVo labQuaControlVo)throws GlobalException;
	/**
	 * 根据pageResult获得监督抽查对象分页信息列表，labQuaControlVo为备用参数
	 * @param labQuaControlVo 监督抽查对象  
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaControlPR(LabQuaControlVo labQuaControlVo,PageResult pageResult )throws GlobalException;
}
