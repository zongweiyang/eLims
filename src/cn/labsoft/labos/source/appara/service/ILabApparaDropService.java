package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaDropVo;


public interface ILabApparaDropService {
	/**
	 *  新增 仪器报废信息
	 * <p>addLabApparaDrop 新增 仪器报废信息<br>
	 * 需要传入LabApparaDropVo vo
	 * @param LabApparaDropVo vo
	 * @return LabApparaDropVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaDropVo addLabApparaDrop(LabApparaDropVo vo)throws GlobalException;
	/**
	 *  修改 仪器报废信息
	 * <p>updateLabApparaDrop 修改 仪器报废信息<br>
	 * 需要传入LabApparaDropVo vo
	 * @param LabApparaDropVo vo
	 * @return LabApparaDropVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaDropVo updateLabApparaDrop(LabApparaDropVo vo)throws GlobalException;
	/**
	 *  获取 仪器报废信息
	 * <p>getLabApparaDrop 获取 仪器报废信息<br>
	 * 需要传入LabApparaDropVo vo
	 * @param LabApparaDropVo vo
	 * @return LabApparaDropVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaDropVo getLabApparaDrop(LabApparaDropVo vo)throws GlobalException;
	/**
	 *  获取 仪器报废分页信息
	 * <p>getLabApparaDrop 获取 仪器报废分页信息<br>
	 * 需要传入LabApparaDropVo vo,PageResult pageResult
	 * @param LabApparaDropVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaDropPR(LabApparaDropVo vo,PageResult pageResult) throws GlobalException;
}
