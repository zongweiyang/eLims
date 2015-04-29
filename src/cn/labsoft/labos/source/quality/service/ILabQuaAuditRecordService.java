package cn.labsoft.labos.source.quality.service;

import java.util.List;

import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditRecordDetailVo;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditRecordVo;


public interface ILabQuaAuditRecordService {
	
	/**
	 * 添加内审记录对象信息
	 * @param labQuaAuditRecordVo 内审记录对象
	 * @return 返回类型 内审记录对象(LabQuaAuditRecordVo) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditRecordVo addLabQuaAuditRecord(LabQuaAuditRecordVo labQuaAuditRecordVo)throws GlobalException;
	/**
	 * 修改内审记录对象信息
	 * @param labQuaAuditRecordVo 内审记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditRecord(LabQuaAuditRecordVo labQuaAuditRecordVo)throws GlobalException;
	/**
	 * 软删除内审记录对象信息
	 * @param labQuaAuditRecordVo 内审记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabQuaAuditRecord(String[] ids)throws GlobalException;
	/**
	 * 根据客户Id获得内审记录对象信息
	 * @param id 内审记录Id
	 * @return 返回类型 内审记录对象(LabQuaAuditRecordVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditRecordVo getLabQuaAuditRecord(String id)throws GlobalException;
	/**
	 * 获得内审记录对象信息列表
	 * @param labQuaAuditRecordVo 内审记录对象
	 * @return 返回类型 内审记录对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditRecordVo> getLabQuaAuditRecordList(LabQuaAuditRecordVo labQuaAuditRecordVo)throws GlobalException;
	/**
	 * 根据pageResult获得内审记录对象分页信息列表，labQuaAuditRecordVo为备用参数
	 * @param labQuaAuditRecordVo 内审记录对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAuditRecordPR(LabQuaAuditRecordVo labQuaAuditRecordVo,PageResult pageResult )throws GlobalException;
	/**
	 * 获得内审记录(详情)对象信息列表
	 * @param labQuaAuditRecordDetailVo 内审记录(详情)对象
	 * @return 返回类型 内审记录(详情)对象集合(List)
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditRecordDetailVo> getLabQuaAuditRecordDetailList(String quaAuditRecordId, String quaAuditPlanEleId, String month, String address)throws GlobalException;
	/**
	 * 修改内审报告
	 * @param labQuaAuditRecordVo 内审记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditRecord4Report(LabQuaAuditRecordVo labQuaAuditRecordVo) throws GlobalException;
	
	/**
	 * 根据code,planId获取公共代码对象信息列表
	 * @param code 公共代码编号
	 * @param planId 计划Id
	 * @return 返回类型 公共代码对象集合(List)
	 * @throws GlobalException
	 */
	public List<LabCodeVo> getLabCodeByPlanEle(String code,String planId)
	throws GlobalException;
}
