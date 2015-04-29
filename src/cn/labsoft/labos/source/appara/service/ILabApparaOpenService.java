package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaOpenVo;


public interface ILabApparaOpenService {
	/**
	 *  新增 仪器启用信息
	 * <p>addLabApparaOpen 新增 仪器启用信息<br>
	 * 需要传入LabApparaOpenVo vo
	 * @param LabApparaOpenVo vo
	 * @return LabApparaOpenVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaOpenVo addLabApparaOpen(LabApparaOpenVo vo)throws GlobalException;
	/**
	 *  修改 仪器启用信息
	 * <p>updateLabApparaOpen 修改 仪器启用信息<br>
	 * 需要传入LabApparaOpenVo vo
	 * @param LabApparaOpenVo vo
	 * @return LabApparaOpenVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaOpenVo updateLabApparaOpen(LabApparaOpenVo vo)throws GlobalException;
	/**
	 *  获取 仪器启用信息
	 * <p>getLabApparaOpen 获取 仪器启用信息<br>
	 * 需要传入LabApparaOpenVo vo
	 * @param LabApparaOpenVo vo
	 * @return LabApparaOpenVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaOpenVo getLabApparaOpen(LabApparaOpenVo vo)throws GlobalException;
	/**
	 *  获取 仪器启用分页信息
	 * <p>getLabApparaOpenPR 获取 仪器启用分页信息<br>
	 * 需要传入LabApparaOpenVo vo,PageResult pageResult
	 * @param LabApparaOpenVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaOpenPR(LabApparaOpenVo vo,PageResult pageResult) throws GlobalException;

}
