package cn.labsoft.labos.base.logs.service;

import cn.labsoft.labos.base.logs.vo.LabLogExceptionVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

/**
 * 系统日志服务层对象接口
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
public interface ILabLogExceptionService {
	/**
	 * 获取系统异常分页信息对象
	 * @param labLogExceptionVo 异常信息对象
	 * @param pageResult  分页信息对象
	 * @return 分页信息对象
	 * @throws GlobalException
	 */
	public PageResult getLabLogExceptionPR(LabLogExceptionVo labLogExceptionVo, PageResult pageResult) throws GlobalException;
	/**
	 * 增加系统异常信息
	 * @param labLogExceptionVo 异常信息对象
	 * @return 异常信息对象
	 * @throws GlobalException
	 */
	public LabLogExceptionVo addLabLogException(LabLogExceptionVo labLogExceptionVo) throws GlobalException;

}