package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaOpen;

public interface ILabApparaOpenDAO extends IBaseDAO{
	/**
	 * 添加仪器启用信息
	 * <p>addLabApparaOpen 添加仪器启用信息 <br>
	 * 需要传入LabApparaOpen po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaOpen 传入LabApparaOpen po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaOpen addLabApparaOpen(LabApparaOpen po) throws GlobalException;
	/**
	 * 修改仪器启用信息
	 * <p>updateLabApparaOpen 修改仪器启用信息 <br>
	 * 需要传入LabApparaOpen po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaOpen 传入LabApparaOpen po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaOpen updateLabApparaOpen(LabApparaOpen po) throws GlobalException;
	/**
	 * 获取仪器启用信息
	 * <p>getLabApparaOpenById 获取仪器废弃信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaOpen
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaOpen getLabApparaOpenById(String id) throws GlobalException;

}
