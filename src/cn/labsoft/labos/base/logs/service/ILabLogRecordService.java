package cn.labsoft.labos.base.logs.service;

import java.util.List;

import cn.labsoft.labos.base.logs.vo.LabLogRecordVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 系统日志服务层对象接口
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
public interface ILabLogRecordService{
	
  public PageResult getlabLogrecordPR(LabLogRecordVo labLogrecordVo,PageResult pageResult)throws GlobalException;

  /**
   * 根据条件得到日志列表
   * @param labLogrecordVo
   * @return 日志对象集合
 * @throws GlobalException 
   */
  public List<LabLogRecordVo> getLabLogrecordList(LabLogRecordVo labLogrecordVo) throws GlobalException;
  /**
   * 其他业务模块 调用日志记录 接口
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
  public LabLogRecordVo addLabLogrecord4Bus(String content,String busId,String busName,String busType,String operator) throws GlobalException;
}