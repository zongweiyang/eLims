package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabAppara;

public interface ILabApparaDAO extends IBaseDAO{
	/**
	 *  新增 仪器信息
	 * <p>addLabAppara 添加仪器记录 <br>
	 * 需要传入LabAppara po 调用BaseDAO中save方法来增加一条记录
	 * @param LabAppara 传入LabAppara po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabAppara addLabAppara(LabAppara po) throws GlobalException;
	/**
	 *  修改 仪器信息
	 * <p>updateLabAppara 修改仪器核查记录 <br>
	 * 需要传入LabAppara po 调用BaseDAO中update方法来修改一条记录
	 * @param LabAppara 传入LabAppara po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabAppara updateLabAppara(LabAppara po) throws GlobalException;
	/**
	 *  获取 仪器信息通过ID
	 * <p>getLabApparaById 获取仪器通过id <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabAppara
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabAppara getLabApparaById(String id) throws GlobalException;

}
