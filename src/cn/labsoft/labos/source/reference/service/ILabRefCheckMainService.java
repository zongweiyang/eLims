package cn.labsoft.labos.source.reference.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.reference.vo.LabRefCheckDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefCheckMainVo;

public interface ILabRefCheckMainService {

	/**
	 * 根据条件找到一个对象
	 * <p>getLabRefCheckMain  根据条件找到一个对象<br>
	 * 需要传入LabRefCheckMainVo labRefCheckMainVo
	 * @param LabRefCheckMainVo labRefCheckMainVo
	 * @return LabRefCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabRefCheckMainVo getLabRefCheckMain(LabRefCheckMainVo labRefCheckMainVo)
			throws GlobalException;

	/**
	 * 添加一个盘点一次标准品的记录
	 * <p>addLabRefCheckMain  添加一个盘点一次标准品的记录<br>
	 * 需要传入LabRefCheckMainVo labRefCheckMainVo
	 * @param LabRefCheckMainVo labRefCheckMainVo
	 * @return LabRefCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabRefCheckMainVo addLabRefCheckMain(LabRefCheckMainVo labRefCheckMainVo)
			throws GlobalException;

	/**
	 * 查询所有的盘点记录
	 * <p>getLabRefCheckMainPR  查询所有的盘点记录<br>
	 * 需要传入LabRefCheckMainVo labRefCheckMainVo,PageResult pageResult
	 * @param LabRefCheckMainVo labRefCheckMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	PageResult getLabRefCheckMainPR(LabRefCheckMainVo labRefCheckMainVo,
			PageResult pageResult) throws GlobalException;

	/**
	 * 创建一个盘点记录Vo
	 * <p>getNewLabRefCheckMainVo  创建一个盘点记录Vo<br>
	 * 需要传入null
	 * @param null
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabRefCheckMainVo getNewLabRefCheckMainVo() throws GlobalException;

	/**
	 * 得到最大的盘点号
	 * <p>getRefCheckstoreMainMaxCheckNo  得到最大的盘点号<br>
	 * 需要传入null
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	String getRefCheckstoreMainMaxCheckNo() throws GlobalException;

	/**
	 * 创建一批标准品盘点对象
	 * <p>getAllLabRefCheckDetailVoList  创建一批标准品盘点对象<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabRefCheckDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	List<LabRefCheckDetailVo> getAllLabRefCheckDetailVoList()
			throws GlobalException;

}
