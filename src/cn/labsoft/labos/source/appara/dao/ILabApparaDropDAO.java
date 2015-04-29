package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaDrop;

public interface ILabApparaDropDAO extends IBaseDAO{
	/**
	 * 添加仪器废弃信息
	 * <p>addLabApparaDrop 添加仪器废弃信息 <br>
	 * 需要传入LabApparaDrop po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaDrop 传入LabApparaDrop po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaDrop addLabApparaDrop(LabApparaDrop po) throws GlobalException;
	/**
	 * 修改仪器废弃信息
	 * <p>updateLabApparaDrop 修改仪器核查记录 <br>
	 * 需要传入LabApparaDrop po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaDrop 传入LabApparaDrop po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaDrop updateLabApparaDrop(LabApparaDrop po) throws GlobalException;
	/**
	 * 获取仪器废弃信息通过ID
	 * <p>getLabApparaDropById 获取仪器废弃信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaDrop
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaDrop getLabApparaDropById(String id) throws GlobalException;

}
