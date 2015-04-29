package cn.labsoft.labos.source.klg.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.entity.LabStandardItem;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;

public interface ILabStandardItemDAO extends IBaseDAO {
	/**
	 * 根据Id查询其标准-项目关系对象
	 * @param id 标准-项目关系Id
	 * @return 返回 标准-项目 LabStandardItem 类型
	 * @author amy
	 */
	public LabStandardItem findById(String id);
	
	/**
	 * 根据检测标准获取其标准-项目关系列表
	 * @param standId 标准Id
	 * @return 返回 标准-项目 List 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItem> getListByStandId(String standId) throws GlobalException;
	/**
	 *  根据项目Id获取其标准-项目关系列表
	 * @param itemId 项目Id
	 * @return 返回 标准-项目 List 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItem> getListByItemId(String itemId) throws GlobalException;
	
	/**
	 * 根据项目父Id标准ID获取其标准-项目子关系列表
	 * @param pItemId 项目父Id
	 * @param standId 标准ID
	 * @return 返回 标准-项目 List 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabStandardItem> getChildrenList(String pItemId,String standId) throws GlobalException;
	/**
	 * 添加标准-项目关系
	 * @param labStandardItem 标准-项目关系对象
	 * @return  返回 标准-项目 boolean 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public  boolean addLabStandardItem(LabStandardItem labStandardItem) throws GlobalException;
	/**
	 * 修改标准-项目关系
	 * @param labStandardItem 标准-项目关系对象
	 * @return 返回 标准-项目 boolean 类型
	 * @author amy
	 * @throws GlobalException 
	 */
	public  boolean updateLabStandardItem(LabStandardItem labStandardItem) throws GlobalException;
	/**
	 * 删除标准-项目关系
	 * @param listLabStandardItem 标准-项目关系集合
	 * @return 返回类型 boolean
	 * @author amy
	 */
	public void deleteAll(List<LabStandardItem> listLabStandardItem);
	/**
	 * 根据项目Id获取其标准-项目子关系列表
	 * @param itemId 项目Id
	 * @return  返回 标准-项目 List 类型
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabStandardItemVo> getLabStandardItemByItemId(String itemId) throws GlobalException;
}
