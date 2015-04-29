package cn.labsoft.labos.source.attendance.dao;

import java.util.List;

import cn.labsoft.labos.source.attendance.entity.LabAttendance;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户考勤记录数据访问对象接口
 * @author Quinn
 * @version 8.0
 * @Since 8.0
 */
public interface ILabAttendanceDAO extends IBaseDAO {
	/**
	 * 增加考勤信息
	 * @param labAttendance 考勤信息持久化对象
	 * @return 考勤信息对象
	 * @throws GlobalException 
	 */
	public LabAttendance addLabAttendance(LabAttendance labAttendance) throws GlobalException;
	/**
	 * 删除考勤信息
	 * @param ids
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabAttendance(String ids[]) throws GlobalException;
	/**
	 * 删除考勤信息
	 * @param labAttendance 考勤信息持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean deleteLabAttendance(LabAttendance labAttendance) throws GlobalException;
	/**
	 * 修改考勤信息
	 * @param labAttendance 考勤信息持久化对象
	 * @return true or false
	 * @throws GlobalException 
	 */
	public boolean updateLabAttendance(LabAttendance labAttendance) throws GlobalException;
	/**
	 * 查询考勤信息
	 * @param id 对象id
	 * @return 考勤信息对象
	 * @throws GlobalException 
	 */
	public LabAttendance getLabAttendance(String id) throws GlobalException;
	/**
	 * 获取考勤记录分页信息集合对象
	 * @param hql 组合查询语句
	 * @param pageResult 带有分页信息的查询对象
	 * @return  带有查询结果的分页信息对象
	 * @throws GlobalException 
	 */
	public PageResult getLabAttendancePR(String hql, PageResult pageResult) throws GlobalException;
	/**
	 * 获取考勤记录集合
	 * @param hql 组合查询语句
	 * @return 查询的结果集合
	 * @throws GlobalException 
	 */
	public List<LabAttendance> getLabAttendanceList(String hql) throws GlobalException;
	
}
