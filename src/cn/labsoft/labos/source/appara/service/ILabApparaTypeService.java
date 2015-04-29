package cn.labsoft.labos.source.appara.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.vo.LabApparaTypeVo;

public interface ILabApparaTypeService {
	/**
	 * 添加仪器类别
	 * <p>addLabApparaType 添加仪器类别 <br>
	 * 需要传入LabApparaTypeVo labApparaTypeVo 
	 * @param labApparaTypeVo 传入labApparaTypeVo vo 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabApparaTypeVo addLabApparaType(LabApparaTypeVo labApparaTypeVo)
			throws GlobalException;

	/**
	 * 软删除仪器类别
	 * <p>deleteLabApparaType 软删除仪器类别 <br>
	 * 需要传入String[] ids
	 * @param String[] ids
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabApparaType(String[] ids) throws GlobalException;

	/**
	 * 修改仪器类别
	 * <p>updateLabApparaType 修改仪器类别 <br>
	 * 需要传入LabApparaTypeVo labApparaTypeVo
	 * @param LabApparaTypeVo labApparaTypeVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabApparaTypeVo updateLabApparaType(LabApparaTypeVo labApparaTypeVo)
			throws GlobalException;

	/**
	 * 根据id查询仪器类别
	 * <p>getLabApparaType 根据id查询仪器类别 <br>
	 * 需要传入String id
	 * @param String id
	 * @return LabApparaTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabApparaTypeVo getLabApparaType(String id) throws GlobalException;


	/**
	 * 根据仪器父id获得仪器类别信息列表
	 * <p>getLabApparaTypeByPid 根据仪器父id获得仪器类别信息列表 <br>
	 * 需要传入String pid
	 * @param String pid
	 * @return LabApparaTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabApparaTypeVo> getLabApparaTypeByPid(String pid)throws GlobalException;

}
