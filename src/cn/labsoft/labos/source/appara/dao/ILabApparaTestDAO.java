package cn.labsoft.labos.source.appara.dao;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.appara.entity.LabApparaTest;

public interface ILabApparaTestDAO extends IBaseDAO{
	
	/**
	 *  新增 仪器信息
	 * <p>addLabApparaTest 添加仪器停用信息 <br>
	 * 需要传入LabApparaTest po 调用BaseDAO中save方法来增加一条记录
	 * @param LabApparaTest 传入LabApparaTest po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaTest addLabApparaTest(LabApparaTest po) throws GlobalException;
	/**
	 *  修改 仪器信息
	 * <p>updateLabApparaTest 修改 仪器信息 <br>
	 * 需要传入LabApparaTest po 调用BaseDAO中update方法来修改一条记录
	 * @param LabApparaTest 传入LabApparaTest po 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaTest updateLabApparaTest(LabApparaTest po) throws GlobalException;
	/**
	 *  删除 仪器信息
	 * <p>deleteLabApparaTest 删除 仪器信息 <br>
	 * 需要传入LabApparaTest po 调用BaseDAO中del方法来修改一条记录
	 * @param LabApparaTest 传入LabApparaTest po 
	 * @return boolean true删除成功false 删除失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabApparaTest(LabApparaTest po) throws GlobalException;
	/**
	 *  获取 仪器信息通过ID
	 * <p>getLabApparaTestById 获取 仪器信息通过ID <br>
	 * 需要String类型的id 传入方法调用BaseDAO中findById方法来查询一条记录
	 * @param id 传入String类型id
	 * @return LabApparaTest
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaTest getLabApparaTestById(String id) throws GlobalException;

	
}
