package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaCheck;

public interface ILabApparaCheckDAO extends IBaseDAO{
	/**
	 *  新增 仪器核查
	 * <p>addLabApparaCheck 添加仪器核查记录 <br>
	 * 需要传入LabApparaCheck po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaCheck 传入LabApparaCheck po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaCheck addLabApparaCheck(LabApparaCheck po) throws GlobalException;
	/**
	 *  修改 仪器核查
	 * <p>updateLabApparaCheck 修改仪器核查记录 <br>
	 * 需要传入LabApparaCheck po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaCheck 传入LabApparaCheck po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaCheck updateLabApparaCheck(LabApparaCheck po) throws GlobalException;
	/**
	 *  获取 仪器核查通过id
	 * <p>getLabApparaCheckById 仪器核查通过id <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaCheck
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaCheck getLabApparaCheckById(String id) throws GlobalException;
}
