package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaBorVo;


public interface ILabApparaBorService {
	/**
	 *  新增 仪器借用记录
	 * <p>addLabApparaBor  新增 仪器借用记录<br>
	 * 需要传入LabApparaBorVo vo
	 * @param LabApparaBorVo vo
	 * @return LabApparaBorVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaBorVo addLabApparaBor(LabApparaBorVo vo)throws GlobalException;
	/**
	 *  修改 仪器借用记录
	 * <p>updateLabApparaBor  修改 仪器借用记录<br>
	 * 需要传入LabApparaBorVo vo
	 * @param LabApparaBorVo vo
	 * @return LabApparaBorVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaBorVo updateLabApparaBor(LabApparaBorVo vo)throws GlobalException;
	/**
	 *  获取 仪器借用记录
	 * <p>getLabApparaBor  获取 仪器借用记录<br>
	 * 需要传入LabApparaBorVo vo
	 * @param LabApparaBorVo vo
	 * @return LabApparaBorVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaBorVo getLabApparaBor(LabApparaBorVo vo)throws GlobalException;
	/**
	 *  获取 仪器借用记录分页信息
	 * <p>getLabApparaBorPR  获取 仪器借用记录分页信息<br>
	 * 需要传入LabApparaBorVo vo,PageResult pageResult
	 * @param LabApparaBorVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaBorPR(LabApparaBorVo vo,PageResult pageResult) throws GlobalException;
}
