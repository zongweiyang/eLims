package cn.labsoft.labos.source.charge.dao;

import java.util.List;

import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.charge.entity.LabCharge;

public interface ILabChargeDAO extends IBaseDAO{

	/**
	 * 增加费用信息
	 * @param labCharge 收费信息对象
	 * @return 返回类型 收费信息对象(LabCharge)
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabCharge addLabCharge(LabCharge labCharge) throws GlobalException;
	/**
	 * 修改费用信息
	 * @param labCharge 收费信息对象
	 * @return 返回类型 boolean
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean updateLabCharge(LabCharge labCharge) throws GlobalException;
	/**
	 * 根据id获取费用信息
	 * @param id 收费信息对象Id
	 * @return 返回类型 收费信息对象(LabCharge)
	 * @author amy
	 * @throws GlobalException 
	 */
	public LabCharge getLabCharge(String id) throws GlobalException;
	
	/**
	 * 根据id删除费用信息
	 * @param labCharge 收费信息对象
	 * @return 返回类型 boolean
	 * @author amy
	 * @throws GlobalException 
	 */
	public boolean deleteLabCharge(LabCharge labCharge) throws GlobalException;
	
	/**
	 * 根据hql获取费用信息列表
	 * @param hql
	 * @return  返回类型 收费信息对象集合(List<LabCharge>)
	 * @author amy
	 * @throws GlobalException 
	 */
	public List<LabCharge> getLabChargeList(String hql) throws GlobalException;
	
	/**
	 * 根据hql获取费用分页信息列表
	 * @param hql
	 * @param pageResult
	 * @return PageResult
	 * @author amy
	 * @throws GlobalException 
	 */
	public PageResult getLabChargePR(String hql, PageResult pageResult) throws GlobalException ;
	

}
