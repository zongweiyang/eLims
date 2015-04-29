package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaStop;

public interface ILabApparaStopDAO extends IBaseDAO{
	/**
	 * 添加仪器停用信息
	 * <p>addLabApparaStop 添加仪器停用信息 <br>
	 * 需要传入LabApparaStop po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaStop 传入LabApparaStop po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaStop addLabApparaStop(LabApparaStop po) throws GlobalException;
	/**
	 * 修改仪器停用信息
	 * <p>updateLabApparaStop 修改仪器启用信息 <br>
	 * 需要传入LabApparaStop po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaStop 传入LabApparaStop po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaStop updateLabApparaStop(LabApparaStop po) throws GlobalException;
	/**
	 * 获取仪器停用信息通过ID
	 * <p>getLabApparaStopById 获取仪器废弃信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaStop
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaStop getLabApparaStopById(String id) throws GlobalException;

}
