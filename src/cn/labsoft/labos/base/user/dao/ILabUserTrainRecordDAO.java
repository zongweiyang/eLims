package cn.labsoft.labos.base.user.dao;

import java.util.List;

import cn.labsoft.labos.base.user.entity.LabUserTrainRecord;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户培训记录关系数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabUserTrainRecordDAO extends IBaseDAO {
	/**
	 * 增加用户培训记录
	 * @param labUserTrainRecord 用户培训记录对象
	 * @return 用户培训记录对象
	 * @throws GlobalException 
	 */
	public LabUserTrainRecord addLabUserTrainRecord(LabUserTrainRecord labUserTrainRecord) throws GlobalException;
	/**
	 * 删除用户培训记录
	 * @param ids 对象id
	 * @return 删除结果true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserTrainRecord(String ids[]) throws GlobalException;
	/**
	 * 删除用户培训对象
	 * @param labUserTrainRecord 用户培训记录对象
	 * @return 删除结果true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabUserTrainRecord(LabUserTrainRecord labUserTrainRecord) throws GlobalException;
	/**
	 * 修改用户培训记录
	 * @param labUserTrainRecord 用户培训记录对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabUserTrainRecord(LabUserTrainRecord labUserTrainRecord) throws GlobalException;
	/**
	 * 查询用户培训记录对象
	 * @param id 对象id
	 * @return 用户培训记录对象
	 * @throws GlobalException 
	 */
	public LabUserTrainRecord getLabUserTrainRecord(String id) throws GlobalException;
	/**
	 * 获取用户培训信息分页集合对象
	 * @param hql 组合的查询语句
	 * @param pageResult 带有分页信息的集合对象
	 * @return 带有查询结果分页信息的集合对象
	 * @throws GlobalException 
	 */
	public PageResult getLabUserTrainRecordPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 查询用户培训记录信息集合
	 * @param hql 组合的查询语句
	 * @return 用户培训记录集合
	 * @throws GlobalException 
	 */
	public List<LabUserTrainRecord> getLabUserTrainRecordList(String hql) throws GlobalException;
	
}
