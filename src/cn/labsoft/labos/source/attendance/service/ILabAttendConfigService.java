package cn.labsoft.labos.source.attendance.service;

import java.util.List;
import cn.labsoft.labos.source.attendance.vo.LabAttendConfigVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 用户考勤配置服务层对象接口
 * @author Quinn
 * @version  8.0
 * @Since 8.0
 */
public interface ILabAttendConfigService {
	/**
	 * 增加考勤配置
	 * @param labAttendConfigVo 考勤配置对象
	 * @return 考勤配置信息
	 * @throws GlobalException
	 */
	public LabAttendConfigVo addLabAttendConfig(LabAttendConfigVo labAttendConfigVo) throws GlobalException;;
	/**
	 * 删除考勤配置
	 * @param ids对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean deleteLabAttendConfig (String[] ids) throws GlobalException;
	/**
	 * 假删除考勤配置
	 * @param ids对象id
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabAttendConfig4Del(String[] ids) throws GlobalException;
	/**
	 * 修改考勤配置
	 * @param labAttendConfigVo 考勤配置对象
	 * @return true or false
	 * @throws GlobalException
	 */
	public boolean updateLabAttendConfig(LabAttendConfigVo labAttendConfigVo) throws GlobalException;
	/**
	 * 查询考勤配置
	 * @param id 对象id
	 * @return 考勤配置信息
	 * @throws GlobalException
	 */
	public LabAttendConfigVo getLabAttendConfig(String id) throws GlobalException;
	/**
	 * 查询考勤配置集合
	 * @param labAttendConfigVo 考勤配置对象
	 * @return 查询结果集合
	 * @throws GlobalException
	 */
	public List<LabAttendConfigVo> getLabAttendConfigList(LabAttendConfigVo labAttendConfigVo) throws GlobalException;
	/**
	 * 查询考勤配置信息集合
	 * @param labAttendConfigVo 考勤配置对象
	 * @param pageResult 带有分页信息对象
	 * @return 带有查询结果的分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabAttendConfigPR(LabAttendConfigVo labAttendConfigVo,PageResult pageResult) throws GlobalException;
	
	/**
	 * 得到当前的系统日期的签到配置
	 * @param datetime
	 * @return  考勤配置信息
	 * @throws GlobalException
	 */
	public LabAttendConfigVo getNowLabAttendConfigVo(String datetime)throws GlobalException;
	
     }
