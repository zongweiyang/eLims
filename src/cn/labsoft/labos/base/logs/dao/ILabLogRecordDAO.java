package cn.labsoft.labos.base.logs.dao;

import cn.labsoft.labos.base.logs.entity.LabLogRecord;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 系统日志访问对象接口
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
public abstract interface ILabLogRecordDAO extends IBaseDAO
{
	/**
	 * 增加日志信息对象
	 * @param labLogrecord 日志信息对象
	 * @return 日志信息对象
	 * @throws GlobalException 
	 */
  public abstract LabLogRecord addLabLogrecord(LabLogRecord labLogrecord) throws GlobalException;
  /**
   * 删除日志
   * @param labLogrecord 日志信息对象
   * @return true or false
 * @throws GlobalException 
   */
  public abstract boolean delLabLogrecord(LabLogRecord labLogrecord) throws GlobalException;
  /**
   * 修改日志信息对象
   * @param labLogrecord 日志信息对象
   * @return 日志信息对象
 * @throws GlobalException 
   */
  public abstract boolean updateLabLogrecord(LabLogRecord labLogrecord) throws GlobalException;
  /**
   * 获取日志信息对象
   * @param labLogrecord 日志信息对象
   * @return 日志信息对象
 * @throws GlobalException 
   */
  public abstract LabLogRecord getLabLogrecord(LabLogRecord labLogrecord) throws GlobalException;
  /**
   * 其他业务模块service 层调用日志记录接口
   * @param userId 操作人
   * @param useName 操作人
   * @param content 日志内容
   * @param busId 业务Id
   * @param busName 业务名称
   * @param busType 业务类型
   * @param operator 操作
   * @return 日志信息对象
 * @throws GlobalException 
   */
  public LabLogRecord addLabLogrecord4Bus(String content,String busId,String busName,String busType,String operator) throws GlobalException;
}