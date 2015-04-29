package cn.labsoft.labos.source.quality.service;

import java.util.List;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaAccidentVo;


public interface ILabQuaAccidentService {
	/**
	 * 添加事故处理对象信息
	 * @param labQuaAccidentVo 事故处理对象
	 * @return 返回类型 事故处理对象(LabQuaAccidentVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAccidentVo addLabQuaAccident(LabQuaAccidentVo labQuaAccidentVo)throws GlobalException;
	/**
	 * 修改事故处理对象信息
	 * @param labQuaAccidentVo 事故处理对象 
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAccident(LabQuaAccidentVo labQuaAccidentVo)throws GlobalException;
	/**
	 * 软删除事故处理对象信息
	 * @param id 事故处理Id
	 * @return 返回类型 事故处理对象(LabQuaAccidentVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaAccident(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得事故处理对象信息
	 * @param id 事故处理Id
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAccidentVo getLabQuaAccident(String id)throws GlobalException;
	/**
	 * 获得事故处理对象信息列表
	 * @param labQuaAccidentVo 事故处理对象 
	 * @return 返回类型 事故处理对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAccidentVo> getLabQuaAccidentVoList(LabQuaAccidentVo labQuaAccidentVo)throws GlobalException;
	/**
	 * 根据pageResult获得事故处理对象分页信息列表，labQuaAccidentVo为备用参数
	 * @param labQuaAccidentVo 事故处理对象 
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAccidentVoPR(LabQuaAccidentVo labQuaAccidentVo, PageResult pageResult)throws GlobalException;
	
}
