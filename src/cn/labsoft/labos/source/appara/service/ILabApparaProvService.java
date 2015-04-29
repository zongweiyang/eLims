package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaProvVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;

public interface ILabApparaProvService {
	/**
	 *  新增 仪器故障信息
	 * <p>addLabApparaProv 新增 仪器故障信息<br>
	 * 需要传入LabApparaProvVo vo
	 * @param LabApparaProvVo vo
	 * @return LabApparaProvVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaProvVo addLabApparaProv(LabApparaProvVo vo)throws GlobalException;
	/**
	 *  修改 仪器故障信息
	 * <p>updateLabApparaProv 修改 仪器故障信息<br>
	 * 需要传入LabApparaProvVo vo
	 * @param LabApparaProvVo vo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabApparaProv(LabApparaProvVo vo)throws GlobalException;
	/**
	 *  获取 仪器故障信息
	 * <p>getLabApparaProv 获取 仪器故障信息<br>
	 * 需要传入LabApparaProvVo vo
	 * @param LabApparaProvVo vo
	 * @return LabApparaProvVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaProvVo getLabApparaProv(LabApparaProvVo vo)throws GlobalException;
	/**
	 *  获取 仪器故障分页信息
	 * <p>getLabApparaProvPR 获取 仪器故障分页信息<br>
	 * 需要传入LabApparaProvVo vo,PageResult pageResult
	 * @param LabApparaProvVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaProvPR(LabApparaProvVo vo,PageResult pageResult) throws GlobalException;
	/**
	 * 获取 仪器故障信息 通过ID
	 * <p>getLabApparaVoById 获取 仪器故障信息 通过ID<br>
	 * 需要传入LabApparaProvVo vo
	 * @param LabApparaProvVo vo
	 * @return LabApparaVo
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaVo getLabApparaVoById(LabApparaProvVo vo) throws GlobalException;
}
