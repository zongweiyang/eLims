package cn.labsoft.labos.source.reference.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.reference.vo.LabRefOutDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefOutMainVo;

public interface ILabRefOutMainService  {

	/**
	 * @Title: 得到标准品出库Main列表
	 * <p>getLabRefOutMainPR  得到标准品出库Main列表<br>
	 * 需要传入LabRefOutMainVo labRefOutMainVo,PageResult pageResult
	 * @param LabRefOutMainVo labRefOutMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabRefOutMainPR(LabRefOutMainVo labRefOutMainVo,
			PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个标准品出库MainVo
	 * <p>getLabRefOutMainById  得到一个标准品出库MainVo<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabRefOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefOutMainVo getLabRefOutMainById(String id)
			throws GlobalException;
	/**
	 * 得到一个标准品出库MainVo
	 * <p>getLabRefOutMainById  得到一个标准品出库MainVo<br>
	 * 需要传入String id,String refIds
	 * @param String id,String refIds
	 * @return LabRefOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefOutMainVo getLabRefOutMainById(String id,String refIds)
	throws GlobalException;

	/**
	 * 添加标准品出库Main
	 * <p>addLabRefOutMain  添加标准品出库Main<br>
	 * 需要传入String id,String refIds
	 * @param String id,String refIds
	 * @return LabRefOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabRefOutMain(LabRefOutMainVo labRefOutMainVo)
			throws GlobalException;

	/**
	 * 修改标准品出库MainVo
	 * <p>updateLabRefOutMain  修改标准品出库MainVo<br>
	 * 需要传入String id,String refIds
	 * @param String id,String refIds
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabRefOutMain(LabRefOutMainVo labRefOutMainVo)
			throws GlobalException;

	/**
	 * 删除标准品出库MainVo
	 * <p>deleteLabRefOutMain  删除标准品出库MainVo<br>
	 * 需要传入LabRefOutMainVo labRefOutMainVo
	 * @param LabRefOutMainVo labRefOutMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabRefOutMain(LabRefOutMainVo labRefOutMainVo)
			throws GlobalException;

	/**
	 * 得到当前最大的单据号
	 * <p>getMaxLabRefOutMainReceiptno  得到当前最大的单据号<br>
	 * 需要传入
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public String getMaxLabRefOutMainReceiptno() throws GlobalException;

	/**
	 * 根据标准品IDS查询标准品并转换成出库详情
	 * <p>getLabRefOutDetailListByReferenceId  根据标准品IDS查询标准品并转换成出库详情<br>
	 * 需要传入String refOutDetailVoId
	 * @param String refOutDetailVoId
	 * @return List<LabRefOutDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	List<LabRefOutDetailVo> getLabRefOutDetailListByReferenceId(
			String refOutDetailVoId) throws GlobalException;
}
