package cn.labsoft.labos.business.sample.dao;

import java.util.List;

import cn.labsoft.labos.business.sample.entity.LabSampRegister;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 任务管理 数据访问对象接口
 * @author Quinn
 */
public interface ILabSampRegisterDAO extends IBaseDAO {
	/**
	 * 增加任务对象
	 * @param labSampRegister 任务信息对象
	 * @return 任务信息对象
	 * @throws GlobalException 
	 */
	public LabSampRegister addLabSampRegister(LabSampRegister labSampRegister) throws GlobalException;
	/**
	 * 删除任务信息
	 * @param ids
	 * @return true or false
	 */
	public boolean deleteLabSampRegister(String ids[]) throws GlobalException;
	/**
	 * 删除任务信息
	 * @param labSampRegister 任务信息对象
	 * @return true or false
	 */
	public boolean deleteLabSampRegister(LabSampRegister labSampRegister)throws GlobalException;
	/**
	 * 修改任务信息
	 * @param labSampRegister 任务信息对象
	 * @return true or false
	 */
	public boolean updateLabSampRegister(LabSampRegister labSampRegister)throws GlobalException;
	/**
	 * 查询任务信息
	 * @param id 对象id
	 * @return 任务信息对象
	 */
	public LabSampRegister getLabSampRegister(String id)throws GlobalException;
	/**
	 *  获取任务分页信息集合对象
	 * @param hql 组合查询语句
	 * @param pageResult 分页信息集合对象
	 * @return  带有结果的分页信息集合对象
	 */
	public PageResult getLabSampRegisterPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 获取任务分页信息
	 * @param hql 组合查询语句
	 * @return 任务分页信息集合
	 */
	public List<LabSampRegister> getLabSampRegisterList(String hql)throws GlobalException;
	
}
