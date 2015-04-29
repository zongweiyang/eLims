package cn.labsoft.labos.base.logs.dao;

import cn.labsoft.labos.base.logs.entity.LabLogException;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 * 系统异常访问对象接口
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
public abstract interface ILabLogExceptionDAO extends IBaseDAO
{
	/**
	 * 增加异常信息
	 * @param labLogException 异常信息对象
	 * @return 异常信息对象
	 * @throws GlobalException 
	 */
  public abstract LabLogException addLabLogException(LabLogException labLogException) throws GlobalException;
/**
 * 查询异常信息
 * @param labLogException 异常信息对象
 * @return 异常信息对象
 * @throws GlobalException 
 */
  public abstract LabLogException getLabLogException(LabLogException labLogException) throws GlobalException;
}