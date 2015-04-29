package cn.labsoft.labos.source.reagent.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.vo.LabReaTypeVo;

public interface ILabReaTypeService {
	/**
	 * 添加试剂类别
	 * <p>addLabReaType  添加试剂类别<br>
	 * 需要传入LabReaTypeVo labReaTypeVo
	 * @param LabReaTypeVo labReaTypeVo
	 * @return LabReaTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaTypeVo addLabReaType(LabReaTypeVo labReaTypeVo)
			throws GlobalException;

	/**
	 * 软删除试剂类别
	 * <p>deleteLabReaType  软删除试剂类别<br>
	 * 需要传入String[] ids
	 * @param String[] ids
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabReaType(String[] ids) throws GlobalException;

	/**
	 * 修改试剂类别
	 * 
	 * <p>updateLabReaType  修改试剂类别<br>
	 * 需要传入LabReaTypeVo labReaTypeVo
	 * @param LabReaTypeVo labReaTypeVo
	 * @return LabReaTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaTypeVo updateLabReaType(LabReaTypeVo labReaTypeVo)
			throws GlobalException;

	/**
	 * 根据id查询试剂类别
	 * 
	 * <p>getLabReaType  根据id查询试剂类别<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReaTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaTypeVo getLabReaType(String id) throws GlobalException;

	/**
	 * 获得试剂类别信息列表
	 * 
	 * <p>getLabReaTypeList  获得试剂类别信息列表<br>
	 * 需要传入LabReaTypeVo labReaTypeVo
	 * @param LabReaTypeVo labReaTypeVo
	 * @return List<LabReaTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaTypeVo> getLabReaTypeList(LabReaTypeVo labReaTypeVo)
			throws GlobalException;

	/**
	 * 根据试剂父id获得试剂类别信息列表
	 * 
	 * <p>getLabReaTypeByPid  根据试剂父id获得试剂类别信息列表<br>
	 * 需要传入String pid
	 * @param String pid
	 * @return List<LabReaTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaTypeVo> getLabReaTypeByPid(String pid)
			throws GlobalException;

	/**
	 * 根据试剂类型对象查找类型
	 * 
	 * <p>getLabReaTypeByVo  根据试剂类型对象查找类型<br>
	 * 需要传入LabReaTypeVo labReaTypeVo
	 * @param LabReaTypeVo labReaTypeVo
	 * @return LabReaTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaTypeVo getLabReaTypeByVo(LabReaTypeVo labReaTypeVo)
			throws GlobalException;

	/**
	 * 获得试剂类别信息列表
	 * <p>getLabReaTypeList  获得试剂类别信息列表<br>
	 * 需要传入String id, int i
	 * @param String id, int i
	 * @return List<LabReaTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaTypeVo> getLabReaTypeList(String id, int i)throws GlobalException;
}
