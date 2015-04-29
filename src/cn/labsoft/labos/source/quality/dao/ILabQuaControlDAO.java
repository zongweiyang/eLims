package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaControl;


public interface ILabQuaControlDAO extends IBaseDAO{

	/**
	 * 添加监督抽查对象信息
	 * @param labQuaControl 监督抽查对象
	 * @return 返回类型 监督抽查对象(LabQuaControl)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaControl addLabQuaControl(LabQuaControl labQuaControl)throws GlobalException;
	/**
	 * 修改监督抽查对象信息
	 * @param labQuaControl 监督抽查对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaControl(LabQuaControl labQuaControl)throws GlobalException;
	/**
	 * 根据客户Id获得监督抽查对象信息
	 * @param id 监督抽查Id
	 * @return 返回类型 监督抽查对象(LabQuaControl)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaControl getLabQuaControl(String id)throws GlobalException;
	/**
	 * 删除监督抽查对象信息
	 * @param labQuaControl 监督抽查对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaControl(LabQuaControl labQuaControl)throws GlobalException;
	/**
	 * 根据hql获取监督抽查对象分页信息列表
	 * @param hql 抽查对象SQL
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaControlPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取监督抽查对象信息列表
	 * @param hql 监督抽查SQL
	 * @return 返回类型 监督抽查对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaControl> getLabQuaControlByHQL(String hql)throws GlobalException;
	
	
}