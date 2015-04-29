package cn.labsoft.labos.source.appara.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.appara.vo.LabApparaUseVo;

public interface ILabApparaUseService {
	/**
	 *  新增 仪器使用信息
	 * <p>addLabApparaUse 添加仪器使用信息 <br>
	 * 需要传入LabApparaUseVo vo 
	 * @param LabApparaUseVo 传入LabApparaUseVo vo 
	 * @return boolean true 增加成功 false 失败
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public boolean addLabApparaUse(LabApparaUseVo vo) throws GlobalException;
	/**
	 *  修改 仪器使用信息
	 * <p>updateLabApparaUse 修改仪器使用信息 <br>
	 * 需要传入LabApparaUseVo vo
	 * @param LabApparaUseVo 传入LabApparaUseVo vo 
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常  
	 * */
	public boolean updateLabApparaUse(LabApparaUseVo vo) throws GlobalException;
	/**
	 *  获取 仪器使用信息
	 * <p>getLabApparaUse 获取仪器使用信息 <br>
	 * 需要传入LabApparaUseVo vo
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常  
	 * */
	public LabApparaUseVo getLabApparaUse(String id) throws GlobalException;
	/**
	 *  获取 仪器使用分页信息
	 * <p>getLabApparaUsePR 获取 仪器使用分页信息<br>
	 * 需要传入LabApparaUseVo vo ，PageResult pageResult
	 * @param LabApparaUseVo vo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public PageResult getLabApparaUsePR(LabApparaUseVo vo,PageResult pageResult) throws GlobalException;
	/**
	 * 获取 仪器预约LIST
	 * <p>getLabApparaUseList 获取 仪器预约LIST<br>
	 * 需要传入LabApparaUseVo vo
	 * @param LabApparaUseVo vo
	 * @return List<LabApparaUseVo>
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabApparaUseVo> getLabApparaUseList(LabApparaUseVo vo) throws GlobalException;
	/**
	 * 根据批次号
	 * 获取 仪器预约LIST
	 * <p>getLabApparaUseList 获取 仪器预约LIST<br>
	 * 需要传入String busId
	 * @param String busId
	 * @return List<LabApparaUseVo>
	 * @author oldk
	 * @throws GlobalException 
	 * @exception GlobalException 自定义异常
	 * */
	public List<LabApparaUseVo> getLabApparaUseList(String busId) throws GlobalException;
	/**
	 * 删除仪器预约/使用记录
	 * <p>deleteLabApparaUse 删除仪器预约/使用记录<br>
	 * 需要传入LabApparaUseVo vo
	 * @param LabApparaUseVo vo
	 * @return 无
	 * @author oldk
	 * @exception GlobalException 自定义异常 
	 * */
	public void deleteLabApparaUse(LabApparaUseVo vo)throws GlobalException;
	/**
	 *  预约 仪器使用信息
	 * <p>addLabApparaBeUse 预约 仪器使用信息<br>
	 * 需要传入LabApparaUseVo vo
	 * @param LabApparaUseVo vo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常 
	 * */
	public boolean addLabApparaBeUse(LabApparaUseVo vo)throws GlobalException;
	/**
	 *  预约 仪器使用信息
	 * <p>addLabApparaUseList 预约 仪器使用信息<br>
	 * 需要传入List<LabApparaUseVo> listLabApparaUseVo
	 * @param List<LabApparaUseVo> listLabApparaUseVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常 
	 * */
	public boolean addLabApparaUseList(List<LabApparaUseVo> listLabApparaUseVo)throws GlobalException;
	/**
	 *  修改预约仪器使用信息
	 * <p>updateLabApparaUseList 修改预约仪器使用信息<br>
	 * 需要传入List<LabApparaUseVo> listLabApparaUseVo,String samBeatchId
	 * @param List<LabApparaUseVo> listLabApparaUseVo,String samBeatchId
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常 
	 * */
	public boolean updateLabApparaUseList(List<LabApparaUseVo> listLabApparaUseVo,String samBeatchId)throws GlobalException;
	/**
	 *  预约 仪器使用信息
	 * <p>addLabApparaUseList 增加预约仪器使用信息<br>
	 * 需要传入List<LabApparaUseVo> listLabApparaUseVo,String samBeatchId
	 * @param List<LabApparaUseVo> listLabApparaUseVo,String samBeatchId
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常 
	 * */
	public boolean addLabApparaUseList(List<LabApparaUseVo> listLabApparaUseVo, String samBeatchId)throws GlobalException;
}
