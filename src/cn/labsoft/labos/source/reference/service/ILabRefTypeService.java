package cn.labsoft.labos.source.reference.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reference.vo.LabRefTypeVo;

public interface ILabRefTypeService {
	/**
	 * 添加标准品类别
	 * 
	 * <p>addLabRefType  添加标准品类别<br>
	 * 需要传入LabRefTypeVo labRefTypeVo
	 * @param LabRefTypeVo labRefTypeVo
	 * @return LabRefTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefTypeVo addLabRefType(LabRefTypeVo labRefTypeVo)
			throws GlobalException;

	/**
	 * 软删除标准品类别
	 * 
	 * <p>deleteLabRefType  软删除标准品类别<br>
	 * 需要传入String[] ids
	 * @param String[] ids
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabRefType(String[] ids) throws GlobalException;

	/**
	 * 修改标准品类别
	 * 
	 * <p>updateLabRefType  软删除标准品类别<br>
	 * 需要传入LabRefTypeVo labRefTypeVo
	 * @param LabRefTypeVo labRefTypeVo
	 * @return LabRefTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefTypeVo updateLabRefType(LabRefTypeVo labRefTypeVo)
			throws GlobalException;

	/**
	 * 根据id查询标准品类别
	 * 
	 * <p>getLabRefType  根据id查询标准品类别<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabRefTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefTypeVo getLabRefType(String id) throws GlobalException;

	/**
	 * 获得标准品类别信息列表
	 * 
	 * <p>getLabRefTypeList  获得标准品类别信息列表<br>
	 * 需要传入LabRefTypeVo labRefTypeVo
	 * @param LabRefTypeVo labRefTypeVo
	 * @return List<LabRefTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * 
	 */
	public List<LabRefTypeVo> getLabRefTypeList(LabRefTypeVo labRefTypeVo)
			throws GlobalException;

	/**
	 * 根据标准品父id获得标准品类别信息列表
	 * 
	 * <p>getLabRefTypeByPid  根据标准品父id获得标准品类别信息列表<br>
	 * 需要传入String pid
	 * @param String pid
	 * @return List<LabRefTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefTypeVo> getLabRefTypeByPid(String pid)
			throws GlobalException;

	/**
	 * 根据标准品类型对象查找类型
	 * 
	 * <p>getLabRefTypeByVo  根据标准品类型对象查找类型<br>
	 * 需要传入LabRefTypeVo labRefTypeVo
	 * @param LabRefTypeVo labRefTypeVo
	 * @return LabRefTypeVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefTypeVo getLabRefTypeByVo(LabRefTypeVo labRefTypeVo)
			throws GlobalException;
	
	/**
	 * 根据标准品类型id查找它下面的标准品类型
	 * <p>getLabRefTypeList  根据标准品类型id查找它下面的标准品类型<br>
	 * 需要传入String id,int x
	 * @param String id,int x
	 * @return List<LabRefTypeVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefTypeVo> getLabRefTypeList(String id,int x)throws GlobalException;
}
