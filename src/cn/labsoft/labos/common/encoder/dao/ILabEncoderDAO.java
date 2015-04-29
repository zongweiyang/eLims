package cn.labsoft.labos.common.encoder.dao;

import java.util.List;

import cn.labsoft.labos.common.encoder.entity.LabEncoder;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
/**
 * 条码管理DAO
 * @author danlee Li
 *
 */
public interface ILabEncoderDAO extends IBaseDAO {
	/**
	 * 新增条码
	 * @param labEncoder
	 * @return LabEncoder
	 */
	public LabEncoder addLabEncoder(LabEncoder labEncoder) throws GlobalException;
	
	/**
	 * 根据ids删除条码信息
	 * @param ids
	 * @return boolean
	 */
	public boolean deleteLabEncoder(String ids[])throws GlobalException;
	/**
	 * 修改条码信息
	 * @param labEncoder
	 * @return boolean
	 */
	
	public boolean updateLabEncoder(LabEncoder labEncoder)throws GlobalException;
	/**
	 * 根据id获得条码信息
	 * @param id
	 * @return LabEncoder
	 */

	
	public LabEncoder getLabEncoder(String id)throws GlobalException;
	/**
	 * 获得条码分页列表
	 * @param hql
	 * @param pageResult
	 * @return PageResult
	 */
	public PageResult getLabEncoderPR(String hql, PageResult pageResult)throws GlobalException;
	/**
	 * 获得条码信息列表
	 * @param hql	
	 * 				HQL语句
	 * @return List<LabEncoder>
	 */
	public List<LabEncoder> getLabEncoderList(String hql)throws GlobalException;
	
}
