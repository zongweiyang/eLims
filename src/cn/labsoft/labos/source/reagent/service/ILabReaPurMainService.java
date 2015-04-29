package cn.labsoft.labos.source.reagent.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.reagent.vo.LabReaPurDetailVo;
import cn.labsoft.labos.source.reagent.vo.LabReaPurMainVo;

public interface ILabReaPurMainService {
	/**
	 * @Title:获取采购单列表
	 * <p>getLabReaPurMainPR  获取采购单列表<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo,PageResult pageResult
	 * @param LabReaPurMainVo labReaPurMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabReaPurMainPR(LabReaPurMainVo labReaPurMainVo,
			PageResult pageResult) throws GlobalException;
	/**
	 * @Title:新增采购单列表
	 * <p>addLabReaPurMain  新增采购单列表<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return LabReaPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaPurMainVo addLabReaPurMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * @Title:相关人员对采购单进行审核
	 * <p>updateLabReaPur4Audit  相关人员对采购单进行审核<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return boolean true or false
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabReaPur4Audit(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * @Title:相关人员对采购单进行审批
	  * <p>updateLabReaPur4Approve  相关人员对采购单进行审批<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return boolean true or false
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabReaPur4Approve(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;
	/**
	 * @Title:获取采购单信息
	 * <p>getLabReaPurMainById  获取采购单信息<br>
	 * 需要传入String mainId
	 * @param String mainId
	 * @return LabReaPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaPurMainVo getLabReaPurMainById(String mainId)
			throws GlobalException;

	/**
	 * @Title:查找采购单中最大单据号
	 * <p>getLabReaPurMainById  获取采购单信息<br>
	 * 需要传入String mainId
	 * @param String mainId
	 * @return LabReaPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public String getLabReaPurMainMaxReceiptNo() throws GlobalException;

	/**
	 * 
	 * @Title:删除采购单
	 * <p>deleteLabReaPurMain  删除采购单<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabReaPurMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * 
	 * @Title:修改已经申请采购单
	 * <p>updateLabReaPurMain  修改已经申请采购单<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return LabReaPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaPurMainVo updateLabReaPurMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * @Title: 根据相关参数获取采购单详细信息
	 * <p>getLabReaPurDetailList  根据相关参数获取采购单详细信息<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return List<LabReaPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaPurDetailVo> getLabReaPurDetailList(
			LabReaPurMainVo labReaPurMainVo) throws GlobalException;

	/**
	 * @Title: 将采购单详细信息添加到指定Main下
	 * <p>addLabReaPurDetail  将采购单详细信息添加到指定Main下<br>
	 * 需要传入List<LabReaPurDetailVo> labReaPurDetailVoList, String id
	 * @param List<LabReaPurDetailVo> labReaPurDetailVoList, String id
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */

	public void addLabReaPurDetail(
			List<LabReaPurDetailVo> labReaPurDetailVoList, String id)
			throws GlobalException;

	/**
	 * @Title: 根据MainId查找采购单详细信息
	 * <p>getLabReaPurDetailByMainId  根据MainId查找采购单详细信息<br>
	 * 需要传入String Id
	 * @param String Id
	 * @return List<LabReaPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaPurDetailVo> getLabReaPurDetailByMainId(String Id)
			throws GlobalException;

	/**
	 * @Title: 获取警戒的试剂采购详细信息集合
	 * <p>getLabReaPurDetailList  获取警戒的试剂采购详细信息集合<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabReaPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabReaPurDetailVo> getLabReaPurDetailList()
			throws GlobalException;
	/**
	 * @Title: 删除试剂采购信息
	 * <p>deleteLabReaPurById  删除试剂采购信息<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabReaPurById(String id) throws GlobalException;
	/**
	 * @Title: 新增试剂采购信息
	 * <p>addLabReaPurDetail  新增试剂采购信息<br>
	 * 需要传入LabReaPurDetailVo labReaPurDetailVo, String id
	 * @param LabReaPurDetailVo labReaPurDetailVo, String id
	 * @return LabReaPurDetailVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaPurDetailVo addLabReaPurDetail(
			LabReaPurDetailVo labReaPurDetailVo, String id)
			throws GlobalException;

	/**
	 * @Title:获取入库单列表
	 * <p>getLabReaPurInsPR  获取入库单列表<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo,PageResult pageResult
	 * @param LabReaPurMainVo labReaPurMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabReaPurInsPR(LabReaPurMainVo labReaPurMainVo,
			PageResult pageResult) throws GlobalException;

	/**
	 * 
	 * @Title:将采购单/入库单进行入库
	 * <p>updateLabReaPur4Ins  将采购单/入库单进行入库<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void updateLabReaPur4Ins(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * @Title:添加入库单
	 * <p>addLabReaPurIns  添加入库单<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return LabReaPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaPurMainVo addLabReaPurIns(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * @Title:修改入库单
	 * <p>updateLabReaInMain  修改入库单<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return LabReaPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabReaPurMainVo updateLabReaInMain(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

	/**
	 * @Title: 将还未入库的入库单删除
	 * <p>deleteLabReaPurIns  将还未入库的入库单删除<br>
	 * 需要传入LabReaPurMainVo labReaPurMainVo
	 * @param LabReaPurMainVo labReaPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabReaPurIns(LabReaPurMainVo labReaPurMainVo)
			throws GlobalException;

}
