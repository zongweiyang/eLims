package cn.labsoft.labos.source.klg.dao;



import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.entity.LabItem;

public interface ILabItemDAO extends IBaseDAO {
	/**
	 * 增加项目信息
	 * @param item 标准项目
	 * @return 返回 标准项目对象(LabItem) 类型 
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabItem addLabItem(LabItem item) throws GlobalException;
	/**
	 * 修改项目信息
	 * @param item 标准项目
	 * @return 返回 boolean 类型 
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateLabItem(LabItem item) throws GlobalException;
	/**
	 * 根据id获取项目信息
	 * @param id 标准项目Id
	 * @return 返回 标准项目对象(LabItem) 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabItem getLabItem(String id) throws GlobalException;
	/**
	 * 根据父Id获取子项目列表
	 * @param parentId 标准项目父Id
	 * @return 返回 标准项目List 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabItem> getLabItemListByParentId(String parentId) throws GlobalException;
	/**
	 * 判断当前想法是否有子集
	 * @param itemId 项目Id
	 * @return 返回 boolean 类型 
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean hasChildren(String itemId) throws GlobalException;
	
}
