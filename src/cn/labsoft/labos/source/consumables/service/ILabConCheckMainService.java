package cn.labsoft.labos.source.consumables.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.consumables.vo.LabConCheckDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConCheckMainVo;

public interface ILabConCheckMainService {

	/**
	 * 根据条件找到一个对象
	 * <p>getLabConCheckMain 根据条件找到一个对象<br>
	 * 需要传入LabConCheckMainVo labConCheckMainVo
	 * @param LabConCheckMainVo labConCheckMainVo
	 * @return LabConCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabConCheckMainVo getLabConCheckMain(LabConCheckMainVo labConCheckMainVo)
			throws GlobalException;

	/**
	 * 添加一个盘点一次耗材的记录
	 * <p>addLabConCheckMain 添加一个盘点一次耗材的记录<br>
	 * 需要传入LabConCheckMainVo labConCheckMainVo
	 * @param LabConCheckMainVo labConCheckMainVo
	 * @return LabConCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabConCheckMainVo addLabConCheckMain(LabConCheckMainVo labConCheckMainVo)
			throws GlobalException;

	/**
	 * 查询所有的盘点记录
	 * <p>getLabConCheckMainPR 查询所有的盘点记录<br>
	 * 需要传入LabConCheckMainVo labConCheckMainVo,PageResult pageResult
	 * @param LabConCheckMainVo labConCheckMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	PageResult getLabConCheckMainPR(LabConCheckMainVo labConCheckMainVo,PageResult pageResult) throws GlobalException;

	/**
	 * 创建一个盘点记录Vo
	 * <p>getNewLabConCheckMainVo 创建一个盘点记录Vo<br>
	 * 需要传入null
	 * @param null
	 * @return LabConCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	LabConCheckMainVo getNewLabConCheckMainVo() throws GlobalException;

	/**
	 * 得到最大的盘点号
	 * <p>getNewLabConCheckMainVo 创建一个盘点记录Vo<br>
	 * 需要传入null
	 * @param null
	 * @return LabConCheckMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	String getConCheckstoreMainMaxCheckNo() throws GlobalException;

	/**
	 * 创建一批耗材盘点对象
	 * <p>getAllLabConCheckDetailVoList 创建一批耗材盘点对象<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabConCheckDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	List<LabConCheckDetailVo> getAllLabConCheckDetailVoList()
			throws GlobalException;

}
