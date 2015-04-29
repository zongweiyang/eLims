package cn.labsoft.labos.source.klg.dao;


import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.entity.LabMethod;


public interface ILabMethodDAO extends IBaseDAO {
	/**
	 * 根据ID查询方法信息对象
	 * @param id 标准方法Id、
	 * @return 返回 标准方法对象(LabMethod) 类型 
	 * @author amy
	 */
	public LabMethod getLabMethod(String id);
	
	/**
	 * 根据name查询标准方法对象
	 * @param name 方法名称
	 * @return 返回 标准方法对象(LabMethod) 类型 
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabMethod getLabMethodByName(String name) throws GlobalException;
	/**
	 * 添加标准方法
	 * @param labMethod 标准方法对象
	 * @return 返回 标准方法对象(LabMethod) 类型 
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabMethod addLabMethod(LabMethod labMethod) throws GlobalException;
	/**
	 * 修改标准方法
	 * @param labMethod 标准方法对象
	 * @return 返回 boolean 类型 
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateLabMethod(LabMethod labMethod) throws GlobalException;
	
}
