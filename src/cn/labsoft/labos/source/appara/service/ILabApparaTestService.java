package cn.labsoft.labos.source.appara.service;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaTestVo;

public interface ILabApparaTestService {
	/**
	 *  新增 仪器检定信息
	 * <p>addLabApparaTest 新增 仪器检定信息 <br>
	 * 需要传入LabApparaTestVo labApparaTestVo 
	 * @param LabApparaTestVo 传入LabApparaTestVo vo 
	 * @return null
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaTestVo addLabApparaTest(LabApparaTestVo labApparaTestVo) throws GlobalException;
	/**
	 *  修改 仪器检定信息
	 * <p>updateLabApparaTest 修改 仪器检定信息 <br>
	 * 需要传入LabApparaTestVo labApparaTestVo 
	 * @param LabApparaTestVo 传入LabApparaTestVo vo 
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaTestVo updateLabApparaTest(LabApparaTestVo labApparaTestVo) throws GlobalException;
	/**
	 *  获取 仪器检定信息通过ID
	 * <p>getLabApparaTestById 获取 仪器检定信息通过ID <br>
	 * 需要传入String id
	 * @param String id
	 * @return LabApparaTestVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public LabApparaTestVo getLabApparaTestById(String id) throws GlobalException;
	/**
	 *  删除 仪器检定信息
	 * <p>deleteLabApparaTest 获取 仪器检定信息通过ID <br>
	 * 需要传入LabApparaTestVo labApparaTestVo
	 * @param LabApparaTestVo labApparaTestVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public boolean deleteLabApparaTest(LabApparaTestVo labApparaTestVo) throws GlobalException;
	/**
	 *  获取 仪器检定分页信息
	 * <p>getLabApparaTestList 获取 仪器检定分页信息 <br>
	 * 需要传入LabApparaTestVo labApparaTestVo, PageResult pageResult
	 * @param LabApparaTestVo labApparaTestVo, PageResult pageResult
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaTestList(LabApparaTestVo labApparaTestVo, PageResult pageResult) throws GlobalException ;
}
