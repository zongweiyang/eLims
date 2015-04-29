package cn.labsoft.labos.source.klg.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.entity.LabStandardItemMethod;


public interface ILabStandardItemMethodDAO extends IBaseDAO {
	/**
	 * 根据Id获取其标准-项目-方法关系列表
	 * @param id 标准-项目-方法关系Id
	 * @return 返回 标准-项目-方法关系对象(LabStandardItemMethod) 类型
	 * @author amy
	 */
	public LabStandardItemMethod getLabStandardItemMethod(String id);
	/**
	 * 修改标准-项目-方法关系
	 * @param labStandardItemMethod 标准-项目-方法关系
	 * @return 返回 boolean 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateLabStandardItemMethod(LabStandardItemMethod labStandardItemMethod) throws GlobalException;
	
	/**
	 * 根据标准Id获取其标准-项目-方法标准关系列表
	 * @param standId 标准Id
	 * @return 返回 标准-项目-方法关系集合(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItemMethod> getListByStandardId(String standId) throws GlobalException;
	/**
	 * 根据项目Id获取其产品标准-项目-方法标准关系列表
	 * @param itemId 项目Id
	 * @return 返回 标准-项目-方法关系集合(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItemMethod> getListByItemId(String itemId) throws GlobalException;
	/**
	 * 根据标准Id和项目Id获取其产品标准中某项目的检测方法列表
	 * @param standId 标准Id
	 * @param itemId 项目Id
	 * @return  返回 标准-项目-方法关系集合(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItemMethod> getListByStandardIdAndItemId(String standId,String itemId) throws GlobalException;
	/**
	 * 根据方法Id获取其产品标准中标准-项目-方法标准关系列表
	 * @param methodId 方法Id
	 * @return 返回 标准-项目-方法关系集合(List) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItemMethod> getListByMethodId(String methodId) throws GlobalException;
	/**
	 * 根据方法Id和项目Id和类型Id获取其产品标准中标准-项目-方法标准关系列表
	 * @param sampTypeId 类型Id
	 * @param itemId 项目Id
	 * @param methodId 方法Id
	 * @return 返回 标准-项目-方法关系对象(LabStandardItemMethod) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabStandardItemMethod getListBysampIdAndItemIdAndMethodId(String sampTypeId,String itemId,String methodId) throws GlobalException;
	/**
	 * 添加标准-项目-方法标准关系
	 * @param labStandardItemMethod 标准-项目-方法关系对象
	 * @return 返回 boolean 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean addLabStandardItemMethod(LabStandardItemMethod labStandardItemMethod) throws GlobalException;
	/**
	 * 删除标准-项目-方法标准关系
	 * @param listLabStandardItemMethod 标准-项目-方法关系集合
	 * @author amy
	 */
	public void deleteAll(List<LabStandardItemMethod> listLabStandardItemMethod);
}
