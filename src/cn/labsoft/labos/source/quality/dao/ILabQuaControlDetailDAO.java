package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaControlDetail;

public interface ILabQuaControlDetailDAO extends IBaseDAO{
	/**
	 * 添加监督抽查详情对象信息
	 * @param labQuaControlDetail 监督抽查详情对象
	 * @return 返回类型 监督抽查详情对象(LabQuaControlDetail)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaControlDetail addLabQuaControlDetail(LabQuaControlDetail labQuaControlDetail)throws GlobalException;
	/**
	 * 修改监督抽查详情对象信息
	 * @param labQuaControlDetail 监督抽查详情对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaControlDetail(LabQuaControlDetail labQuaControlDetail)throws GlobalException;
	/**
	 * 根据客户Id获得监督抽查详情对象信息
	 * @param id 监督抽查详情Id
	 * @return 返回类型 监督抽查详情对象(LabQuaControlDetail) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaControlDetail getLabQuaControlDetail(String id)throws GlobalException;
	/**
	 * 删除监督抽查详情对象信息
	 * @param labQuaControlDetail 监督抽查详情对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaControlDetail(LabQuaControlDetail labQuaControlDetail)throws GlobalException;
	/**
	 * 根据hql获取监督抽查详情对象分页信息列表
	 * @param hql 监督抽查详情SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaControlDetailPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取监督抽查详情对象信息列表
	 * @param hql 监督抽查详情SQL
	 * @return 返回类型 监督抽查详情对象集合(List)  
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaControlDetail> getLabQuaControlDetailByHQL(String hql)throws GlobalException;
}