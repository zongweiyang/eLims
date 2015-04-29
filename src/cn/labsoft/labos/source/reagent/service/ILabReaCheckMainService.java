package cn.labsoft.labos.source.reagent.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.reagent.vo.LabReaCheckDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaCheckMainVo;

public interface ILabReaCheckMainService {

	/**
	 * 根据条件找到一个对象
	 * <p>getLabReaCheckMain  根据条件找到一个对象<br>
	 * 需要传入LabReaCheckMainVo labReaCheckMainVo
	 * @param LabReaCheckMainVo labReaCheckMainVo
	 * @return LabReaCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabReaCheckMainVo getLabReaCheckMain(LabReaCheckMainVo labReaCheckMainVo)
			throws GlobalException;

	/**
	 * 添加一个盘点一次试剂的记录
	 * <p>addLabReaCheckMain  添加一个盘点一次试剂的记录<br>
	 * 需要传入LabReaCheckMainVo labReaCheckMainVo
	 * @param LabReaCheckMainVo labReaCheckMainVo
	 * @return LabReaCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabReaCheckMainVo addLabReaCheckMain(LabReaCheckMainVo labReaCheckMainVo)
			throws GlobalException;

	/**
	 * 查询所有的盘点记录
	 * <p>getLabReaCheckMainPR  查询所有的盘点记录<br>
	 * 需要传入LabReaCheckMainVo labReaCheckMainVo,PageResult pageResult
	 * @param LabReaCheckMainVo labReaCheckMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	PageResult getLabReaCheckMainPR(LabReaCheckMainVo labReaCheckMainVo,PageResult pageResult) throws GlobalException;

	/**
	 * 创建一个盘点记录Vo
	 * <p>getNewLabReaCheckMainVo  创建一个盘点记录Vo<br>
	 * 需要传入null
	 * @param null
	 * @return LabReaCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabReaCheckMainVo getNewLabReaCheckMainVo() throws GlobalException;

	/**
	 * 得到最大的盘点号
	 * <p>getReaCheckstoreMainMaxCheckNo  得到最大的盘点号<br>
	 * 需要传入null
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	String getReaCheckstoreMainMaxCheckNo() throws GlobalException;

	/**
	 * 创建一批试剂盘点对象
	 * <p>getAllLabReaCheckDetailVoList  创建一批试剂盘点对象<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabReaCheckDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	List<LabReaCheckDetailVo> getAllLabReaCheckDetailVoList()
			throws GlobalException;

}
