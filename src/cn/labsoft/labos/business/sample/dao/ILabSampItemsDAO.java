package cn.labsoft.labos.business.sample.dao;

import java.util.List;

import cn.labsoft.labos.business.sample.entity.LabSampItems;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 检测项目 数据访问对象接口
 * @author Quinn
 */
public interface ILabSampItemsDAO extends IBaseDAO {
	/**
	 * 增加检测项目
	 * @param sampItems 检测项目对象
	 * @return 检测项目对象
	 * @throws GlobalException 
	 */
	public LabSampItems addLabSampItems(LabSampItems sampItems) throws GlobalException;
	/**
	 * 删除检测项目
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabSampItems(String ids[]) throws GlobalException;
	/**
	 * 删除检测项目
	 * @param sampItems 检测项目对象
	 * @return true or false
	 */
	public boolean deleteLabSampItems(LabSampItems sampItems) throws GlobalException;
	/**
	 * 修改检测项目
	 * @param sampItems 检测项目对象
	 * @return true or false
	 */
	public boolean updateLabSampItems(LabSampItems sampItems) throws GlobalException;
	/**
	 * 查询检测项目
	 * @param id 对象id
	 * @return 检测项目对象
	 */
	public LabSampItems getLabSampItems(String id) throws GlobalException;
	/**
	 *  获取检测项目分页信息集合对象
	 * @param hql 组合查询语句
	 * @param pageResult 分页信息集合对象
	 * @return 带有结果的分页信息集合对象
	 */
	public PageResult getLabSampItemsPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 获取检测项目对象集合
	 * @param hql 组合查询语句
	 * @return 结果集合
	 */
	public List<LabSampItems> getLabSampItemsList(String hql) throws GlobalException;
	
}
