package cn.labsoft.labos.source.reagent.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.reagent.vo.LabReagentVo;

public interface ILabReagentService {

	/**
	 * 得到试剂列表
	 * <p>getLabReagentPR  得到试剂列表<br>
	 * 需要传入LabReagentVo labReagentVo,PageResult pageResult
	 * @param LabReagentVo labReagentVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabReagentPR(LabReagentVo labReagentVo,PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个试剂对象
	 * <p>getLabReagentList  得到一个试剂对象<br>
	 * 需要传入LabReagentVo labReagentVo
	 * @param LabReagentVo labReagentVo
	 * @return List<LabReagentVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReagentVo> getLabReagentList(LabReagentVo labReagentVo)throws GlobalException;

	/**
	 * 得到一个试剂对象
	 * <p>getLabReagentById  得到一个试剂对象<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReagentVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReagentVo getLabReagentById(String id) throws GlobalException;

	/**
	 * 给一个类型下添加一个试剂
	 * <p>addLabReagent  给一个类型下添加一个试剂<br>
	 * 需要传入LabReagentVo labReagentVo
	 * @param LabReagentVo labReagentVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabReagent(LabReagentVo labReagentVo)
			throws GlobalException;

	/**
	 * 根据id软删除一个试剂
	 * <p>deleteLabReagent  根据id软删除一个试剂<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabReagent(String id) throws GlobalException;

	/**
	 * 修改一个试剂的信息
	 * <p>updateLabReagent  修改一个试剂的信息<br>
	 * 需要传入LabReagentVo labReagentVo
	 * @param LabReagentVo labReagentVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabReagent(LabReagentVo labReagentVo)
			throws GlobalException;

	/**
	 * 根据试剂类型得到该类型下的所有试剂
	 * <p>getReagentListByTypeId  根据试剂类型得到该类型下的所有试剂<br>
	 * 需要传入String typeId
	 * @param String typeId
	 * @return List<LabReagentVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReagentVo> getReagentListByTypeId(String typeId)
			throws GlobalException;

	/**
	 * 根据ids查找试剂对象
	 * <p>getLabReagentListByIds  根据ids查找试剂对象<br>
	 * 需要传入String ids
	 * @param String ids
	 * @return List<LabReagentVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReagentVo> getLabReagentListByIds(String ids)
			throws GlobalException;

	/**
	 * 修改一个试剂的类型信息
	 * <p>getLabReagentListByIds  根据ids查找试剂对象<br>
	 * 需要传入String ids
	 * @param String ids
	 * @return List<LabReagentVo>
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 */
	public LabReagentVo updateLabReagentMove(LabReagentVo labReagentVo) throws GlobalException;

	/**
	 * 批量导入试剂
	 * <p>addLabReagent4Import  批量导入试剂<br>
	 * 需要传入String[][] strings,String typeId
	 * @param String[][] strings,String typeId
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabReagent4Import(String[][] strings,String typeId) throws GlobalException;
	
    /**
     * 批量更新试剂编号
     * <p>updateLabReagentList  批量更新试剂编号<br>
	 * 需要传入LabReagentVo labReagentVo
	 * @param LabReagentVo labReagentVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
     */
	public void updateLabReagentList(LabReagentVo labReagentVo)throws GlobalException;
//	/**
//	 * 更新所有编号
//	 * @param labReagentVo
//	 * @return
//	 * @throws GlobalException
//	 */
//	public boolean updateLabReagentCodeBatch(List<String> listCode)
//	throws GlobalException;

	/**
	 * 删除试剂类型时同时删除它下面的试剂
	 * <p>deleteLabReagentByTypeId  删除试剂类型时同时删除它下面的试剂<br>
	 * 需要传入String ids
	 * @param String ids
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常 
	 */
	public boolean deleteLabReagentByTypeId(String ids)throws GlobalException;

}
