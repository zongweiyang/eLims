package cn.labsoft.labos.source.consumables.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.consumables.vo.LabConOutDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConOutMainVo;

public interface ILabConOutMainService  {

	/**
	 * 得到耗材出库Main列表
	 * <p>getLabConOutMainPR 得到耗材出库Main列表<br>
	 * 需要传入LabConOutMainVo labConOutMainVo,PageResult pageResult
	 * @param LabConOutMainVo labConOutMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabConOutMainPR(LabConOutMainVo labConOutMainVo,PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个耗材出库MainVo
	 * <p>getLabConOutMainPR 得到一个耗材出库MainVo<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabConOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConOutMainVo getLabConOutMainById(String id)
			throws GlobalException;

	/**
	 * 添加耗材出库Main
	 * <p>addLabConOutMain 添加耗材出库Main<br>
	 * 需要传入LabConOutMainVo labConOutMainVo
	 * @param LabConOutMainVo labConOutMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean addLabConOutMain(LabConOutMainVo labConOutMainVo)
			throws GlobalException;

	/**
	 * 修改耗材出库MainVo
	 * <p>updateLabConOutMain 修改耗材出库MainVo<br>
	 * 需要传入LabConOutMainVo labConOutMainVo
	 * @param LabConOutMainVo labConOutMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabConOutMain(LabConOutMainVo labConOutMainVo)
			throws GlobalException;

	/**
	 * 删除耗材出库MainVo
	 * <p>deleteLabConOutMain 删除耗材出库MainVo<br>
	 * 需要传入LabConOutMainVo labConOutMainVo
	 * @param LabConOutMainVo labConOutMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConOutMain(LabConOutMainVo labConOutMainVo)
			throws GlobalException;

	/**
	 * 得到当前最大的单据号
	 * <p>getMaxLabConOutMainReceiptno 得到当前最大的单据号<br>
	 * 需要传入null
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public String getMaxLabConOutMainReceiptno() throws GlobalException;

	/**
	 * 根据耗材IDS查询耗材并转换成出库详情
	 * <p>getLabConOutDetailListByConsumablesId 根据耗材IDS查询耗材并转换成出库详情<br>
	 * 需要传入String conOutDetailVoId
	 * @param String conOutDetailVoId
	 * @return List<LabConOutDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	List<LabConOutDetailVo> getLabConOutDetailListByConsumablesId(
			String conOutDetailVoId) throws GlobalException;
}
