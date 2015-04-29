package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaComplain;
 

public interface ILabQuaComplainDAO extends IBaseDAO{
	/**
	 * 添加投诉处理对象信息
	 * @param labQuaComplain 投诉处理对象
	 * @return 返回类型 投诉处理对象(LabQuaComplain)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaComplain addLabQuaComplain(LabQuaComplain labQuaComplain)throws GlobalException;
	/**
	 * 修改投诉处理对象信息
	 * @param labQuaComplain 投诉处理对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaComplain(LabQuaComplain labQuaComplain)throws GlobalException;
	/**
	 * 根据客户Id获得投诉处理对象信息
	 * @param id 投诉处理Id
	 * @return 返回类型 投诉处理对象(LabQuaComplain)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaComplain getLabQuaComplain(String id)throws GlobalException;
	/**
	 * 删除投诉处理对象信息
	 * @param labQuaComplain 投诉处理对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaComplain(LabQuaComplain labQuaComplain)throws GlobalException;
	/**
	 * 根据hql获取投诉处理对象分页信息列表
	 * @param hql 投诉处理sql
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaComplainPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取投诉处理对象信息列表
	 * @param hql 投诉处理sql
	 * @return 返回类型 投诉处理对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaComplain> getLabQuaComplainByHQL(String hql)throws GlobalException;
}