package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaEditVo;
import cn.labsoft.labos.source.appara.vo.LabApparaVo;

public interface ILabApparaEditService {
	/**
	 *  新增 仪器维修信息
	 * <p>addLabApparaEdit 新增 仪器维修信息<br>
	 * 需要传入LabApparaEditVo vo
	 * @param LabApparaEditVo vo
	 * @return LabApparaEditVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaEditVo addLabApparaEdit(LabApparaEditVo vo)throws GlobalException;
	/**
	 *  修改 仪器维修信息
	 * <p>updateLabApparaEdit 修改 仪器维修信息<br>
	 * 需要传入LabApparaEditVo vo
	 * @param LabApparaEditVo vo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabApparaEdit(LabApparaEditVo vo)throws GlobalException;
	/**
	 *  获取 仪器维修信息
	 * <p>getLabApparaEdit 获取 仪器维修信息<br>
	 * 需要传入LabApparaEditVo vo
	 * @param LabApparaEditVo vo
	 * @return LabApparaEditVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaEditVo getLabApparaEdit(LabApparaEditVo vo)throws GlobalException;
	/**
	 *  获取 仪器维修分页信息
	 * <p>getLabApparaEditPR 获取 仪器维修分页信息<br>
	 * 需要传入LabApparaEditVo vo,PageResult pageResult
	 * @param LabApparaEditVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaEditPR(LabApparaEditVo vo,PageResult pageResult)throws GlobalException;
	/**
	 *  获取 仪器维修信息 通过ID
	 * <p>getLabApparaById 获取 仪器维修信息 通过ID<br>
	 * 需要传入LabApparaEditVo vo
	 * @param LabApparaEditVo vo
	 * @return LabApparaVo
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaVo getLabApparaById(LabApparaEditVo vo) throws GlobalException;
}
