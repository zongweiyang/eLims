package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaAccept;

public interface ILabApparaAcceptDAO extends IBaseDAO{
	/**
	 * 添加仪器验收
	 * <p>addLabApparaAccept 使用增加一条仪器验收记录 <br>
	 * 需要LabApparaAccept 传入方法调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaAccept  需要传入po实体
	 * @return LabApparaAccept 返回null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaAccept addLabApparaAccept(LabApparaAccept po) throws GlobalException;
	/**
	 * 修改仪器验收
	 * *<p>updateLabApparaAccept 使用修改一条仪器验收记录 <br>
	 * 需要LabApparaAccept 传入方法调用BaseDAO中update方法来增加一条记录
	 * @param LabApparaAccept  需要传入po实体
	 * @return LabApparaAccept 返回null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaAccept updateLabApparaAccept(LabApparaAccept po) throws GlobalException;
	/**
	 * 获取仪器验收记录
	 * <p>getLabApparaAcceptById 获取一条仪器验收记录 <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaAccept 返回实体
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaAccept getLabApparaAcceptById(String id) throws GlobalException;

}
