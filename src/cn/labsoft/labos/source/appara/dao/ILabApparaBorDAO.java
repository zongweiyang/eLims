package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaBor;

public interface ILabApparaBorDAO extends IBaseDAO{
	/**
	 * 添加仪器借用
	 * <p>addLabApparaBor 添加仪器借用记录 <br>
	 * 需要传入LabApparaBor po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaBor 传入LabApparaBor po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	
	public LabApparaBor addLabApparaBor(LabApparaBor po) throws GlobalException;
	/**
	 * 修改仪器借用
	 * <p>updateLabApparaBor 修改仪器借用记录 <br>
	 * 需要传入LabApparaBor po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaBor 传入LabApparaBor po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaBor updateLabApparaBor(LabApparaBor po) throws GlobalException;
	/**
	 * 获取仪器借用
	 * <p>addLabApparaBor 添加仪器借用记录 <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaBor
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaBor getLabApparaBorById(String id) throws GlobalException;

}
