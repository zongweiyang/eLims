package cn.labsoft.labos.business.sam.dao;

import java.util.List;

import cn.labsoft.labos.business.sam.entity.LabSam;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 样品管理DAO
 * @author danlee Li
 *
 */
public interface ILabSamDAO extends IBaseDAO{

	/**
	 * 增加样品对象信息
	 * 
	 * @param labSam
	 * 			   要操作的对象
	 * @return 返回类型 LabSam 要操作的对象
	 * @throws GlobalException 
	 */
	public LabSam addLabSam(LabSam labSam) throws GlobalException;
	
	/**
	 * 修改样品对象信息
	 * 
	 * @param labSam
	 * 			   要操作的对象
	 * @return boolean
	 * @throws GlobalException 
	 * 
	 */
	public boolean updateLabSam(LabSam labSam) throws GlobalException;
	
	/**
	 * 删除样品对象信息
	 * 
	 * @param labSam
	 * 			   要操作的对象
	 * @return boolean
	 * @throws GlobalException 
	 * 
	 */
	public boolean deleteLabSam(LabSam labSam) throws GlobalException;
	
	/**
	 * 根据Id查询样品对象信息
	 * 
	 * @param samId
	 * 			  
	 * @return 返回类型 LabSam
	 * @throws GlobalException 
	 */
	public LabSam getLabSam(String samId) throws GlobalException;
	
	/**
	 * 根据hql语句获取样品对象分页列表信息
	 * 
	 * @param hql
	 * 			HQL语句
	 * @param pageResult			  
	 * @return 返回类型 PageResult 返回分页对象
	 * @throws GlobalException 
	 */
	public PageResult getLabSamPR(String hql, PageResult pageResult) throws GlobalException;
	
	/**
	 * 根据hql语句获取样品列表
	 * 
	 * @param hql
	 * 			HQL语句
	 * 		  
	 * @return 返回类型 List 查询获得的LabSam集合
	 * @throws GlobalException 
	 */
	public List<LabSam> getLabSamList(String hql) throws GlobalException;
}
