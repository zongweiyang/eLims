package cn.labsoft.labos.base.user.dao;

import java.util.List;
import cn.labsoft.labos.base.user.entity.LabUserTrain;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户培训计划关系数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabUserTrainDAO extends IBaseDAO {
	/**
	 * 增加用户培训计划持久对象
	 * @param labUserTrain 用户培训计划持久对象
	 * @return 已持久的用户培训计划对象
	 * @throws GlobalException 
	 */
	public LabUserTrain addLabUserTrain(LabUserTrain labUserTrain) throws GlobalException;
	/**
	 * 删除用户培训计划
	 * @param ids 对象id
	 * @return 删除结果true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserTrain(String ids[]) throws GlobalException;
	/**
	 * 修改用户培训计划
	 * @param labUserTrain 用户培训计划持久对象
	 * @return 修改结果true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabUserTrain(LabUserTrain labUserTrain) throws GlobalException;
	/**
	 * 查询用户培训计划
	 * @param id 对象id
	 * @return 已持久的用户培训计划对象
	 * @throws GlobalException 
	 */
	public LabUserTrain getLabUserTrain(String id) throws GlobalException;
	/**
	 * 获取用户培训计划分页信息
	 * @param hql 组合的查询语句
	 * @param pageResult 带有分页信息的集合对象
	 * @return PageResult 带有查询结果分页信息的集合对象
	 * @throws GlobalException 
	 */
	public PageResult getLabUserTrainPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 获取用户培训计划集合
	 * @param hql 组合的查询语句
	 * @return 用户培训计划信息集合
	 * @throws GlobalException 
	 */
	public List<LabUserTrain> getLabUserTrainList(String hql) throws GlobalException;
	
}
