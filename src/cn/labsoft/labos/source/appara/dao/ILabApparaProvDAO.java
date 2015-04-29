package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaProv;

public interface ILabApparaProvDAO extends IBaseDAO{
	/**
	 * 添加仪器故障信息
	 * <p>addLabApparaProv 添加仪器故障信息 <br>
	 * 需要传入LabApparaProv po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaProv 传入LabApparaProv po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaProv addLabApparaProv(LabApparaProv po) throws GlobalException;
	/**
	 * 修改仪器故障信息
	 * <p>updateLabApparaProv 修改仪器启用信息 <br>
	 * 需要传入LabApparaProv po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaProv 传入LabApparaProv po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaProv updateLabApparaProv(LabApparaProv po) throws GlobalException;
	/**
	 * 获取仪器故障信息通过ID
	 * <p>getLabApparaProvById 获取仪器故障信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaProv
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaProv getLabApparaProvById(String id) throws GlobalException;
}
