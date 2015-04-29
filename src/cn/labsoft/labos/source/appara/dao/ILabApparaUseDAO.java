package cn.labsoft.labos.source.appara.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaUse;

public interface ILabApparaUseDAO extends IBaseDAO{
	/**
	 * 添加仪器使用信息
	 * <p>addLabApparaUse 添加仪器使用信息 <br>
	 * 需要传入LabApparaUse po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaUse 传入LabApparaUse po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaUse addLabApparaUse(LabApparaUse po) throws GlobalException;
	/**
	 * 修改仪器使用信息
	 * <p>updateLabApparaUse 修改仪器使用信息 <br>
	 * 需要传入LabApparaUse po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaUse 传入LabApparaUse po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaUse updateLabApparaUse(LabApparaUse po) throws GlobalException;
	/**
	 * 获取仪器使用信息通过ID
	 * <p>getLabApparaUseById 获取仪器使用信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaUse
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaUse getLabApparaUseById(String id) throws GlobalException;
	/**
	 * 通过ID 删除信息
	 * <p>deleteLabApparaUseById 删除 仪器信息 <br>
	 * 需要String类型的id 调用BaseDAO中del方法来删除一条记录
	 * @param String[] id 传入String[] id
	 * @return boolean true删除成功false 删除失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabApparaUseById(String[] id) throws GlobalException;
	/**
	 *  删除信息
	 * <p>deleteAllLabApparaUse 删除 仪器信息 <br>
	 * 调用BaseDAO中del方法来删除一条记录
	 * @param listLabApparaUse 传入listLabApparaUse
	 * @return boolean true删除成功false 删除失败
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	
	public boolean deleteAllLabApparaUse(List<LabApparaUse> listLabApparaUse) throws GlobalException;
}
