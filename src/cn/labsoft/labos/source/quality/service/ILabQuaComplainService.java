package cn.labsoft.labos.source.quality.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaComplainVo;



public interface ILabQuaComplainService {
	
	/**
	 * 添加投诉处理对象信息
	 * @param labQuaComplainVo 投诉处理对象
	 * @return 返回类型 投诉处理对象(LabQuaComplainVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaComplainVo addLabQuaComplain(LabQuaComplainVo labQuaComplainVo)throws GlobalException;
	/**
	 * 修改投诉处理对象信息
	 * @param labQuaComplainVo 投诉处理对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaComplain(LabQuaComplainVo labQuaComplainVo)throws GlobalException;
	/**
	 * 软删除投诉处理对象信息
	 * @param id 投诉处理Id
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaComplain(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得投诉处理对象信息
	 * @param id 投诉处理Id
	 * @return 返回类型 投诉处理对象(LabQuaComplainVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaComplainVo getLabQuaComplain(String id)throws GlobalException;
	/**
	 * 获得投诉处理对象信息列表
	 * @param labQuaComplainVo 投诉处理对象
	 * @return 返回类型 投诉处理对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaComplainVo> getLabQuaComplainVoList(LabQuaComplainVo labQuaComplainVo)throws GlobalException;
	/**
	 * 根据pageResult获得投诉处理对象分页信息列表，labQuaComplainVo为备用参数
	 * @param labQuaComplainVo 投诉处理对象 
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaComplainVoPR(LabQuaComplainVo labQuaComplainVo, PageResult pageResult)throws GlobalException;
	
}
