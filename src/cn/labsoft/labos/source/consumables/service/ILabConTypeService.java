package cn.labsoft.labos.source.consumables.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.vo.LabConTypeVo;

public interface ILabConTypeService {
	/**
	 * 添加耗材类别
	 * <p>addLabConType 添加耗材类别<br>
	 * 需要传入LabConTypeVo labConTypeVo
	 * @param LabConTypeVo labConTypeVo
	 * @return LabConTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConTypeVo addLabConType(LabConTypeVo labConTypeVo)
			throws GlobalException;

	/**
	 * 软删除耗材类别
	 * <p>deleteLabConType 软删除耗材类别<br>
	 * 需要传入String[] ids
	 * @param String[] ids
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConType(String[] ids) throws GlobalException;

	/**
	 * 修改耗材类别
	 * 
	 * <p>updateLabConType 修改耗材类别<br>
	 * 需要传入LabConTypeVo labConTypeVo
	 * @param LabConTypeVo labConTypeVo
	 * @return LabConTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConTypeVo updateLabConType(LabConTypeVo labConTypeVo)
			throws GlobalException;

	/**
	 * 根据id查询耗材类别
	 * 
	 * <p>getLabConType 根据id查询耗材类别<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabConTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConTypeVo getLabConType(String id) throws GlobalException;

	/**
	 * 获得耗材类别信息列表
	 * <p>getLabConTypeList 获得耗材类别信息列表<br>
	 * 需要传入LabConTypeVo labConTypeVo
	 * @param LabConTypeVo labConTypeVo
	 * @return List<LabConTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConTypeVo> getLabConTypeList(LabConTypeVo labConTypeVo)
			throws GlobalException;

	/**
	 * 根据耗材父id获得耗材类别信息列表
	 * <p>getLabConTypeByPid 根据耗材父id获得耗材类别信息列表<br>
	 * 需要传入String pid
	 * @param String pid
	 * @return List<LabConTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConTypeVo> getLabConTypeByPid(String pid)
			throws GlobalException;

	/**
	 * 根据耗材类型对象查找类型
	 * <p>getLabConTypeByVo 根据耗材类型对象查找类型<br>
	 * 需要传入LabConTypeVo labConTypeVo
	 * @param LabConTypeVo labConTypeVo
	 * @return LabConTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConTypeVo getLabConTypeByVo(LabConTypeVo labConTypeVo)
			throws GlobalException;
	
	
	/**
	 * 得到耗材类型树形菜单select
	 * <p>getLabConTypeList 得到耗材类型树形菜单select<br>
	 * 需要传入String id,int x
	 * @param String id,int x
	 * @return List<LabConTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConTypeVo> getLabConTypeList(String id,int x) throws GlobalException;
}
