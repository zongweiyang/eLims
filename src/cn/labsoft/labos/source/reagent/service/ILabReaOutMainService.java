package cn.labsoft.labos.source.reagent.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.reagent.vo.LabReaOutDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaOutMainVo;

public interface ILabReaOutMainService  {

	/**
	 * 得到试剂出库Main列表
	 * <p>getLabReaOutMainPR  得到试剂出库Main列表<br>
	 * 需要传入LabReaOutMainVo labReaOutMainVo,PageResult pageResult
	 * @param LabReaOutMainVo labReaOutMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabReaOutMainPR(LabReaOutMainVo labReaOutMainVo,
			PageResult pageResult) throws GlobalException;

	/**
	 * 得到一个试剂出库MainVo
	 * <p>getLabReaOutMainById  得到一个试剂出库MainVo<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReaOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaOutMainVo getLabReaOutMainById(String id)
			throws GlobalException;

	
	/**
	 * 添加试剂出库Main
	 * <p>getLabReaOutMainById  添加试剂出库Main<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReaOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaOutMainVo addLabReaOutMain(LabReaOutMainVo labReaOutMainVo)
			throws GlobalException;

	/**
	 * 修改试剂出库MainVo
	 * <p>updateLabReaOutMain  修改试剂出库MainVo<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReaOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabReaOutMain(LabReaOutMainVo labReaOutMainVo)
			throws GlobalException;

	/**
	 * 删除试剂出库MainVo
	 * <p>deleteLabReaOutMain  删除试剂出库MainVo<br>
	 * 需要传入String id
	 * @param String id
	 * @return LabReaOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabReaOutMain(LabReaOutMainVo labReaOutMainVo)
			throws GlobalException;

	/**
	 * 得到当前最大的单据号
	 * <p>getMaxLabReaOutMainReceiptno  得到当前最大的单据号<br>
	 * 需要传入null
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public String getMaxLabReaOutMainReceiptno() throws GlobalException;

	/**
	 * 根据试剂IDS查询试剂并转换成出库详情
	 * <p>getLabReaOutDetailListByReagentId  根据试剂IDS查询试剂并转换成出库详情<br>
	 * 需要传入String reaOutDetailVoId
	 * @param String reaOutDetailVoId
	 * @return List<LabReaOutDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaOutDetailVo> getLabReaOutDetailListByReagentId(
			String reaOutDetailVoId) throws GlobalException;
	/**
	 * 获取试剂出库信息
	 * <p>getLabReaOutMainById  获取试剂出库信息<br>
	 * 需要传入String id,String reaIds
	 * @param String id,String reaIds
	 * @return LabReaOutMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaOutMainVo getLabReaOutMainById(String id,String reaIds)throws GlobalException;
}
