package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaCheckVo;

public interface ILabApparaCheckService {
	/**
	 *  新增 仪器核查
	 * <p>addLabApparaCheck 新增 仪器核查<br>
	 * 需要传入LabApparaCheckVo vo
	 * @param LabApparaCheckVo vo
	 * @return LabApparaCheckVo
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaCheckVo addLabApparaCheck(LabApparaCheckVo vo) throws GlobalException;
	/**
	 *  修改 仪器核查
	 * <p>updateLabApparaCheck 修改 仪器核查<br>
	 * 需要传入LabApparaCheckVo vo
	 * @param LabApparaCheckVo vo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public void updateLabApparaCheck(LabApparaCheckVo vo)throws GlobalException;
	/**
	 *  获取 仪器核查记录
	 * <p>updateLabApparaCheck 修改 仪器核查<br>
	 * 需要传入getLabApparaCheck vo
	 * @param getLabApparaCheck vo
	 * @return LabApparaCheckVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaCheckVo getLabApparaCheck(LabApparaCheckVo vo)throws GlobalException;
	/**
	 *  获取 仪器核查记录分页信息
	 * <p>getLabApparaCheckPR 获取 仪器核查记录分页信息<br>
	 * 需要传入LabApparaCheckVo vo,PageResult pageResult
	 * @param LabApparaCheckVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaCheckPR(LabApparaCheckVo vo,PageResult pageResult)throws GlobalException;
	
}
