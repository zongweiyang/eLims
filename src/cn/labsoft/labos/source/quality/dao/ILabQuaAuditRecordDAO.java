package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecord;


public interface ILabQuaAuditRecordDAO extends IBaseDAO{

	/**
	 * 添加内审记录对象信息
	 * @param labQuaAuditRecord 内审记录对象
	 * @return 返回类型 内审记录对象(LabQuaAuditRecord) 
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditRecord addLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord)throws GlobalException;
	/**
	 * 修改内审记录对象信息
	 * @param labQuaAuditRecord 内审记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord)throws GlobalException;
	/**
	 * 根据客户Id获得内审记录对象信息
	 * @param id 内审记录Id
	 * @return 返回类型 内审记录对象(LabQuaAuditRecord) 
	 * @throws GlobalException
	 * @author amy
	 */ 
	public LabQuaAuditRecord getLabQuaAuditRecord(String id)throws GlobalException;
	/**
	 * 删除内审记录对象信息
	 * @param labQuaAuditRecord 内审记录对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord)throws GlobalException;
	/**
	 * 根据hql获取内审记录对象分页信息列表
	 * @param hql 内审记录sql
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAuditRecordPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取内审记录对象信息列表
	 * @param hql 内审记录sql
	 * @return 返回类型 内审记录对象集合(List) 
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditRecord> getLabQuaAuditRecordByHQL(String hql)throws GlobalException;
	
}