package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaAcceptVo;


public interface ILabApparaAcceptService {
	/**
	 * 添加仪器验收
	 * <p>addLabApparaAccept  添加仪器验收<br>
	 * 需要传入LabApparaAcceptVo vo
	 * @param LabApparaAcceptVo vo
	 * @return null
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public void addLabApparaAccept(LabApparaAcceptVo vo) throws GlobalException;
	/**
	 * 修改仪器验收
	 * <p>updateLabApparaAccept  修改仪器验收<br>
	 * 需要传入LabApparaAcceptVo vo
	 * @param LabApparaAcceptVo vo
	 * @return LabApparaAcceptVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaAcceptVo updateLabApparaAccept(LabApparaAcceptVo vo)throws GlobalException;
	/**
	 * 修改仪器验收,修改流程
	 * <p>updateLabAppAccept  修改仪器验收,修改流程<br>
	 * 需要传入LabApparaAcceptVo vo
	 * @param LabApparaAcceptVo vo
	 * @return LabApparaAcceptVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaAcceptVo updateLabAppAccept(LabApparaAcceptVo vo)throws GlobalException;
	/**
	 * 获取仪器验收信息
	 * <p>getLabApparaAccept  获取仪器验收信息<br>
	 * 需要传入LabApparaAcceptVo vo
	 * @param LabApparaAcceptVo vo
	 * @return LabApparaAcceptVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaAcceptVo getLabApparaAccept(LabApparaAcceptVo vo)throws GlobalException;
	/**
	 * 获取仪器验收分页信息
	 * <p>getLabApparaAccept  获取仪器验收分页信息<br>
	 * 需要传入LabApparaAcceptVo vo,PageResult pageResult
	 * @param LabApparaAcceptVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaAcceptPR(LabApparaAcceptVo vo,PageResult pageResult)throws GlobalException;

}
