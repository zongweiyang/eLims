package cn.labsoft.labos.source.attendance.dao;

import java.util.List;

import cn.labsoft.labos.source.attendance.entity.LabAttendConfig;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户考勤配置数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabAttendConfigDAO extends IBaseDAO {
	/**
	 * 增加考勤配置
	 * @param labAttendConfig 考勤配置信息持久化对象
	 * @return 考勤配置信息对象
	 * @throws GlobalException 
	 */
	public LabAttendConfig addLabAttendConfig(LabAttendConfig labAttendConfig) throws GlobalException;
	/**
	 * 删除考勤配置信息
	 * @param ids 对象id
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabAttendConfig(String ids[]) throws GlobalException;
	/**
	 * 删除考勤配置信息
	 * @param labAttendConfig 考勤配置信息持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabAttendConfig(LabAttendConfig labAttendConfig) throws GlobalException;
	/**
	 * 修改考勤配置信息
	 * @param labAttendConfig 考勤配置信息持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabAttendConfig(LabAttendConfig labAttendConfig) throws GlobalException;
	/**
	 * 查询考勤配置信息
	 * @param id 对象id
	 * @return 考勤对象信息
	 * @throws GlobalException 
	 */
	public LabAttendConfig getLabAttendConfig(String id) throws GlobalException;
	/**
	 * 
	 * @param hql 组合查询语句
	 * @param pageResult 带有分页信息的查询对象
	 * @return 带有查询结果的分页信息对象
	 * @throws GlobalException 
	 */
	public PageResult getLabAttendConfigPR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 查询配置信息集合
	 * @param hql 组合查询语句
	 * @return 查询的结果集合
	 * @throws GlobalException 
	 */
	public List<LabAttendConfig> getLabAttendConfigList(String hql) throws GlobalException;
	/**
	 * 获取用于约束month月的考勤配置（若无，则取最近一次配置）
	 * @param month月份
	 * @return 考勤对象信息
	 * @throws GlobalException 
	 */
	public LabAttendConfig getLabAttendConfig4Month(String month) throws GlobalException;
	/**
	 * 获取用于约束今天的考勤配置（若无，则取最近一次配置）
	 * @return 考勤对象信息
	 * @throws GlobalException 
	 */
	public LabAttendConfig getLabAttendConfig4Cur() throws GlobalException;
}
