package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaStopVo;


public interface ILabApparaStopService {
	/**
	 *  新增 仪器停用信息
	 * <p>addLabApparaStop 新增 仪器停用信息<br>
	 * 需要传入LabApparaStopVo vo
	 * @param LabApparaStopVo 传入LabApparaStopVo vo 
	 * @return LabApparaStopVo
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaStopVo addLabApparaStop(LabApparaStopVo vo) throws GlobalException;
	/**
	 *  修改 仪器停用信息
	 * <p>updateLabApparaStop 修改 仪器停用信息<br>
	 * 需要传入LabApparaStopVo vo
	 * @param LabApparaStopVo 传入LabApparaStopVo vo 
	 * @return LabApparaStopVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaStopVo updateLabApparaStop(LabApparaStopVo vo)throws GlobalException;
	/**
	 *  获取 仪器停用信息
	 * <p>getLabApparaStop 获取 仪器停用信息<br>
	 * 需要传入LabApparaStopVo vo
	 * @param LabApparaStopVo 传入LabApparaStopVo vo 
	 * @return LabApparaStopVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaStopVo getLabApparaStop(LabApparaStopVo vo)throws GlobalException;
	/**
	 *  获取 仪器停用分页信息
	 * <p>getLabApparaStopPR 获取 仪器停用信息<br>
	 * 需要传入LabApparaStopVo vo,PageResult pageResult
	 * @param LabApparaStopVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaStopPR(LabApparaStopVo vo,PageResult pageResult)throws GlobalException;
}
