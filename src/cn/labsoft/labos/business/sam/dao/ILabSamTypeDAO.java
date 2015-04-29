package cn.labsoft.labos.business.sam.dao;

import java.util.List;

import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 样品分类管理DAO
 * @author danlee Li
 *
 */
public interface ILabSamTypeDAO extends IBaseDAO{
	/**
	 * 增加样品分类对象信息
	 * @param labSamType
	 * @return LabSamType
	 * 				 	封装样品分类视图
	 * @throws GlobalException 
	 */
	public LabSamType addLabSamType(LabSamType labSamType) throws GlobalException;
	/**
	 * 删除样品分类对象信息
	 * @param id	样品分类ID
	 * @return boolean
	 * @throws GlobalException 
	 */
	public boolean deleteLabSamType(String id) throws GlobalException;
	/**
	 * 修改样品分类对象信息
	 * @param labSamType
	 * @return boolean
	 * @throws GlobalException 
	 */
	public boolean updateLabSamType(LabSamType labSamType) throws GlobalException;
	/**
	 * 根据Id查询样品分类对象信息
	 * @param samTypeId
	 * @return LabSamType
	 * @throws GlobalException 
	 */
	public LabSamType getLabSamType(String samTypeId) throws GlobalException;
	/**
	 * 根据hql语句获取样品分类分页列表信息
	 * @param hql
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException 
	 */
	public PageResult getLabSamTypePR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 根据hql语句获取样品分类列表
	 * @param hql
	 * @return List<LabSamType>
	 * @throws GlobalException 
	 */
	public List<LabSamType> getLabSamTypeList(String hql) throws GlobalException;
	
	/**
	 * 树的下级深度、
	 * @param labSamType
	 * @param node 节点
	 * @return int 返回样品深度
	 */
	public int getLevel(LabSamType labSamType,Integer node)throws GlobalException;
}
