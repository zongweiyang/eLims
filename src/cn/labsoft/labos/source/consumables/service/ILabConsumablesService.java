package cn.labsoft.labos.source.consumables.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.consumables.vo.LabConsumablesVo;

public interface ILabConsumablesService {

	/**
	 * 得到耗材列表
	 * <p>getLabConsumablesPR 得到耗材列表<br>
	 * 需要传入LabConsumablesVo labConsumablesVo,PageResult pageResult
	 * @param LabConsumablesVo labConsumablesVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabConsumablesPR(LabConsumablesVo labConsumablesVo,PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个耗材对象
	 * <p>getLabConsumablesList 得到一个耗材对象<br>
	 * 需要传入LabConsumablesVo labConsumablesVo,PageResult pageResult
	 * @param LabConsumablesVo labConsumablesVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabConsumablesList(LabConsumablesVo labConsumablesVo,PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个耗材对象
	 * <p>getLabConsumablesById 得到一个耗材对象<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabConsumablesVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConsumablesVo getLabConsumablesById(String id)
			throws GlobalException;

	/**
	 * 给一个类型下添加一个耗材
	 * <p>addLabConsumables 给一个类型下添加一个耗材<br>
	 * 需要传入LabConsumablesVo labConsumablesVo
	 * @param LabConsumablesVo labConsumablesVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabConsumables(LabConsumablesVo labConsumablesVo)
			throws GlobalException;

	/**
	 * 根据id软删除一个耗材
	 * <p>deleteLabConsumables 根据id软删除一个耗材<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConsumables(String id) throws GlobalException;

	/**
	 * 修改一个耗材的信息
	 * <p>updateLabConsumables 修改一个耗材的信息<br>
	 * 需要传入LabConsumablesVo labConsumablesVo
	 * @param LabConsumablesVo labConsumablesVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabConsumables(LabConsumablesVo labConsumablesVo)
			throws GlobalException;

	/**
	 * 根据耗材类型得到该类型下的所有耗材
	 * <p>getConsumablesListByTypeId 根据耗材类型得到该类型下的所有耗材<br>
	 * 需要传入String typeId
	 * @param String typeId
	 * @return List<LabConsumablesVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConsumablesVo> getConsumablesListByTypeId(String typeId)
			throws GlobalException;

	/**
	 * 根据ids查找耗材对象
	 * <p>getLabConsumablesListByIds 根据ids查找耗材对象<br>
	 * 需要传入String ids
	 * @param String ids
	 * @return List<LabConsumablesVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConsumablesVo> getLabConsumablesListByIds(String ids)
			throws GlobalException;

	/**
	 * 修改一个耗材的类型信息
	 * <p>updateLabConsumablesMove 修改一个耗材的类型信息<br>
	 * 需要传入LabConsumablesVo labConsumablesVo
	 * @param LabConsumablesVo labConsumablesVo
	 * @return LabConsumablesVo
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	public LabConsumablesVo updateLabConsumablesMove(
			LabConsumablesVo labConsumablesVo) throws GlobalException;

	/**
	 * 批量导入耗材
	 * <p>addLabComsumables4Import 批量导入耗材<br>
	 * 需要传入String[][] strings,String typeId
	 * @param String[][] strings,String typeId
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabComsumables4Import(String[][] strings,String typeId)throws GlobalException;

	/***
	 * 根据typeId删除耗材
	 * <p>deleteLabConsumablesByTypeId 根据typeId删除耗材<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConsumablesByTypeId(String id)throws GlobalException;

}
