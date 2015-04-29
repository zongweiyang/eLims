package cn.labsoft.labos.source.quality.service;

import java.util.List;


import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaCusVisitVo;




public interface ILabQuaCusVisitService {
	/**
	 * 添加客户回访登记对象信息
	 * @param labQuaCusVisitVo 客户回访登记对象
	 * @return 返回类型 客户回访登记对象(LabQuaCusVisitVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaCusVisitVo addLabQuaCusVisit(LabQuaCusVisitVo labQuaCusVisitVo)throws GlobalException;
	/**
	 * 修改客户回访登记对象信息
	 * @param labQuaCusVisitVo 客户回访登记对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaCusVisit(LabQuaCusVisitVo labQuaCusVisitVo)throws GlobalException;
	/**
	 * 软删除客户回访登记对象信息
	 * @param labQuaCusVisitVo 客户回访登记对象
	 * @return 返回类型 boolean 
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaCusVisit(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得客户回访登记对象信息
	 * @param id 客户回访登记Id
	 * @return 返回类型 客户回访登记对象(LabQuaCusVisitVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaCusVisitVo getLabQuaCusVisit(String id)throws GlobalException;
	/**
	 * 获得客户回访登记对象信息列表
	 * @param labQuaCusVisitVo 客户回访登记对象
	 * @return 返回类型 客户回访登记对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaCusVisitVo> getLabQuaCusVisitList(LabQuaCusVisitVo labQuaCusVisitVo)throws GlobalException;
	/**
	 * 根据pageResult获得客户回访登记对象分页信息列表，labQuaCusVisitVo为备用参数
	 * @param labQuaCusVisitVo 客户回访登记对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaCusVisitPR(LabQuaCusVisitVo labQuaCusVisitVo,PageResult pageResult )throws GlobalException;
}
