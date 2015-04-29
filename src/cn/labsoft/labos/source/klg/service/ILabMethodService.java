package cn.labsoft.labos.source.klg.service;


import java.util.List;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;

@SuppressWarnings("unchecked")
public interface ILabMethodService {

	/**
	 * 根据功能id获得该条记录信息并修改信息后存入数据库
	 * 
	 * @param labMethodVo
	 *            封装了功能信息的视图对象
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabMethod(LabMethodVo labMethodVo)
			throws GlobalException;
	/**
	 * 软删除功能
	 * @param ids 方法Ids
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabMethod(String[] ids)
			throws GlobalException;
	/**
	 * 根据hql获得方法分页信息列表，labMethodVo为备用参数
	 * @param labMethodVo 检测方法对象
	 * @param pageResult
	 * @return 返回类型 PageResult 
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabMethodPR(LabMethodVo labMethodVo,PageResult pageResult)
	throws GlobalException;
	
	/**
	 * 获取方法信息列表
	 * @param labMethodVo 检测方法对象
	 * @param pageResult
	 * @return 返回类型 PageResult 
	 * @author amy
	 * @throws GlobalException 
	 */
	public PageResult getLabMethodList(LabMethodVo labMethodVo, PageResult pageResult) throws GlobalException;
	/**
	 * 判断方法的编号是否存在
	 * @param code 方法编号
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean exist4LabMethodCode(String code)throws GlobalException ;
	
	/**
	 * 根据id查询方法信息对象
	 * @param id 方法id
	 * @return 返回类型 检测方法对象(LabMethodVo)  
	 * @author amy
	 */
	public LabMethodVo getLabMethod(String id);
	
	/**
	 * 增加方法信息对象
	 * @param labMethodVo 检测方法对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabMethod(LabMethodVo labMethodVo) throws GlobalException;
	/**
	 * 根据itemsId查询方法信息对象
	 * @param itemsId 项目Id
	 * @return 返回类型 检测项目集合(List)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabMethodVo> getLabMethodByItemsId(String itemsId) throws GlobalException;
	
	/**
	 * 判断方法名是否存在
	 * @param name 项目名称
	 * @return 返回类型 Boolean
	 * @author amy
	 * @throws GlobalException 
	 */
	public Boolean exist4LabMethodName(String name) throws GlobalException;
	
	/**
	 * 根据项目名称获取检测方法对象列表
	 * @param name 项目名称
	 * @return 返回类型 检测项目集合(List)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabMethodVo> getLabMethodByName(String name) throws GlobalException;
	/**
	 *  根据itemId，standId查询检测方法对象列表
	 * @param itemId 项目Id
	 * @param standId 标准Id
	 * @return 返回类型 检测项目集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabMethodVo> getLabStandardItemMethodByItems(String itemId,String standId)throws GlobalException;

}
