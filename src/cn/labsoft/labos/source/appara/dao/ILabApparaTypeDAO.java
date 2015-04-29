package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaType;

public interface  ILabApparaTypeDAO extends IBaseDAO{

	/**
	 *  新增 仪器信息
	 * <p>addLabApparaType 新增 仪器信息 <br>
	 * 需要传入LabApparaType po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaType 传入LabApparaType po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaType addLabApparaType(LabApparaType po) throws GlobalException;
	/**
	 *  修改 仪器信息
	 * <p>updateLabApparaType 修改 仪器信息 <br>
	 * 需要传入LabApparaType po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaType 传入LabApparaType po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaType updateLabApparaType(LabApparaType po) throws GlobalException;
	/**
	 *  删除 仪器信息
	 * <p>deleteLabApparaType 删除 仪器信息 <br>
	 * 需要传入LabApparaType po 调用BaseDAO中del方法来修改一条记录
	 * @param LabApparaType 传入LabApparaType po 
	 * @return boolean true删除成功false 删除失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabApparaType(LabApparaType po) throws GlobalException;
	/**
	 *  获取 仪器信息通过ID
	 * <p>getLabApparaTypeById 获取 仪器信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaType
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaType getLabApparaTypeById(String id) throws GlobalException;

}
