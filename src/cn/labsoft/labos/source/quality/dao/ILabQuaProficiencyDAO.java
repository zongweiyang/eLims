package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiency;


public interface ILabQuaProficiencyDAO extends IBaseDAO{
	/**
	 * 添加比对验证记录对象信息
	 * @param labQuaProficiency 比对验证记录对象
	 * @return 返回类型 比对验证记录对象(LabQuaProficiency)  
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiency addLabQuaProficiency(LabQuaProficiency labQuaProficiency)throws GlobalException;
	/**
	 * 修改比对验证记录对象信息
	 * @param labQuaProficiency 比对验证记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaProficiency(LabQuaProficiency labQuaProficiency)throws GlobalException;
	/**
	 * 根据客户Id获得比对验证记录对象信息
	 * @param id 比对验证记录Id
	 * @return 返回类型 比对验证记录对象(LabQuaProficiency)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaProficiency getLabQuaProficiency(String id)throws GlobalException;
	/**
	 * 删除比对验证记录对象信息
	 * @param labQuaProficiency 比对验证记录对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaProficiency(LabQuaProficiency labQuaProficiency)throws GlobalException;
	/**
	 * 根据hql获取比对验证记录对象分页信息列表
	 * @param hql 比对验证记录SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaProficiencyPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取比对验证记录对象信息列表
	 * @param hql 比对验证记录SQL
	 * @return 返回类型 比对验证记录对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaProficiency> getLabQuaProficiencyByHQL(String hql)throws GlobalException;
}