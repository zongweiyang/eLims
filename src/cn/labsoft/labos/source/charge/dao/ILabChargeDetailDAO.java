package cn.labsoft.labos.source.charge.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.charge.entity.LabChargeDetail;

public interface ILabChargeDetailDAO extends IBaseDAO{

	/**
	 * 增加费用详情信息
	 * @param labChargeDetail 费用详情对象
	 * @return 返回类型 费用详情对象(LabChargeDetail)
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabChargeDetail addLabChargeDetail(LabChargeDetail labChargeDetail) throws GlobalException;
	/**
	 * 修改费用详情信息
	 * @param labChargeDetail 费用详情对象
	 * @return 返回类型 boolean
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateLabChargeDetail(LabChargeDetail labChargeDetail) throws GlobalException;
	/**
	 * 根据id获取费用详情信息
	 * @param id 费用详情对象Id
	 * @return 返回类型 费用详情对象(LabChargeDetail)
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabChargeDetail getLabChargeDetail(String id) throws GlobalException;
	
	/**
	 * 根据id删除费用详情信息
	 * @param labChargeDetail
	 * @return 返回类型 boolean
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean deleteLabChargeDetail(LabChargeDetail labChargeDetail) throws GlobalException;
	
	/**
	 * 根据hql获取费用详情信息列表
	 * @param hql
	 * @return 返回类型 费用详情对象(LabChargeDetail)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabChargeDetail> getLabChargeDetailList(String hql) throws GlobalException;
	
	/**
	 * 根据hql获取费用详情分页信息列表
	 * @param hql
	 * @param pageResult
	 * @return PageResult
	 * @author amy
	 * @throws GlobalException 
	 */
	public PageResult getLabChargeDetailPR(String hql, PageResult pageResult) throws GlobalException ;
	

}
