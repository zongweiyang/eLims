package cn.labsoft.labos.source.attendance.service;

import java.util.List;
import cn.labsoft.labos.source.attendance.vo.LabAttendanceVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户考勤记录服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
public interface ILabAttendanceService {
	/**
	 * 增加考勤记录信息
	 * @param labAttendanceVo 考勤记录对象
	 * @return 用户考勤记录对象Vo
	 * @throws GlobalException
	 */
	public LabAttendanceVo addLabAttendance(LabAttendanceVo labAttendanceVo) throws GlobalException;;
	/**
	 * 删除考勤信息
	 * @param ids对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabAttendance (String[] ids) throws GlobalException;
	/**
	 * 假删除考勤信息
	 * @param ids对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabAttendance4Del(String[] ids) throws GlobalException;
	/**
	 * 修改考勤信息
	 * @param labAttendanceVo 考勤记录对象
	 * @return 用户考勤记录对象Vo
	 * @throws GlobalException
	 */
	public LabAttendanceVo updateLabAttendance(LabAttendanceVo labAttendanceVo) throws GlobalException;
	/**
	 * 查询考勤信息
	 * @param id 对象id
	 * @return 用户考勤记录对象Vo
	 * @throws GlobalException
	 */
	public LabAttendanceVo getLabAttendance(String id) throws GlobalException;
	/**
	 * 查询考勤信息
	 * @param userId 用户id
	 * @param workData 考勤日期
	 * @return 用户考勤记录对象Vo
	 * @throws GlobalException
	 */
	public LabAttendanceVo getLabAttendance(String userId,String workData) throws GlobalException;
/**
 * 查询考勤信息列表
 * @param labAttendanceVo 考勤记录对象
 * @return 查询结果集合
 * @throws GlobalException
 */
	public List<LabAttendanceVo> getLabAttendanceList(LabAttendanceVo labAttendanceVo) throws GlobalException;
	/**
	 * 
	 * @param labAttendanceVo 考勤记录对象
	 * @param pageResult 带有分页信息对象
	 * @return 带有查询结果的分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabAttendancePR(LabAttendanceVo labAttendanceVo,PageResult pageResult) throws GlobalException;
	/**
	 * 统计个人考勤数据集合或集体考勤人数集合
	 * @param labAttendanceVo 考勤记录对象
	 * @return list1实际出勤/应出勤（天） list2迟到次/人 list3早退次/人 list4补签次/人 list5缺卡次/人 list5缺口次/人
	 * @throws GlobalException
	 */
	public Integer[] getLabAttendanceList4User(LabAttendanceVo labAttendanceVo) throws GlobalException;
	/**
	 * 按部门统计考勤数据集合
	 * @param labAttendanceVo 考勤记录对象
	 * @return String[7] 1人，2部门 3实际出勤 4迟到 5 早退 6补签 7缺卡
	 * @throws GlobalException
	 */
	public List<String[]> getLabAttendanceList4Org(LabAttendanceVo labAttendanceVo) throws GlobalException;
	/**
	 * 补增考勤记录
	 * @param labAttendanceVo 考勤记录对象
	 * @return 用户考勤记录对象Vo
	 * @throws GlobalException
	 */
	public LabAttendanceVo addLabAttendance4History(LabAttendanceVo labAttendanceVo) throws GlobalException;;
	
}
