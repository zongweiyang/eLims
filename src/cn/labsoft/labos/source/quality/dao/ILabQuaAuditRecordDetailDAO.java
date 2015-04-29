package cn.labsoft.labos.source.quality.dao;

import java.util.List;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecordDetail;


public interface ILabQuaAuditRecordDetailDAO extends IBaseDAO{

	/**
	 * 添加内审记录（详情）信息
	 * @param labQuaAuditRecordDetail
	 * @return
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditRecordDetail addLabQuaAuditRecordDetail(LabQuaAuditRecordDetail labQuaAuditRecordDetail)throws GlobalException;
	/**
	 * 修改内审记录（详情）信息
	 * @param labQuaAuditRecordDetail
	 * @return
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean updateLabQuaAuditRecordDetail(LabQuaAuditRecordDetail labQuaAuditRecordDetail)throws GlobalException;
	/**
	 * 根据客户Id获得内审记录（详情）信息
	 * @param id
	 * @return
	 * @throws GlobalException
	 * @author amy
	 */
	public LabQuaAuditRecordDetail getLabQuaAuditRecordDetail(String id)throws GlobalException;
	/**
	 * 删除内审记录（详情）信息
	 * @param labQuaAuditRecordDetail
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean deleteLabQuaAuditRecordDetail(LabQuaAuditRecordDetail labQuaAuditRecordDetail)throws GlobalException;
	/**
	 * 根据hql获取内审记录（详情）分页信息列表
	 * @param hql
	 * @param pageResult
	 * @return
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabQuaAuditRecordDetailPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 根据hql获取内审记录（详情）信息列表
	 * @param hql
	 * @return
	 * @throws GlobalException
	 * @author amy
	 */
	public List<LabQuaAuditRecordDetail> getLabQuaAuditRecordDetailByHQL(String hql)throws GlobalException;
	
}