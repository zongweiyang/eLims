package cn.labsoft.labos.source.consumables.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.consumables.vo.LabConPurDetailVo;
import cn.labsoft.labos.source.consumables.vo.LabConPurMainVo;

public interface ILabConPurMainService  {
	/**
	 * @Title:获取采购单列表
	 * <p>getLabConPurMainPR 获取采购单列表<br>
	 * 需要传入LabConPurMainVo labConPurMainVo,PageResult pageResult
	 * @param LabConPurMainVo labConPurMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabConPurMainPR(LabConPurMainVo labConPurMainVo,PageResult pageResult) throws GlobalException;
	/**
	 * @Title:新增采购单
	 * <p>addLabConPurMain 新增采购单<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return LabConPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurMainVo addLabConPurMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * @Title:相关人员对采购单进行审核
	 * <p>updateLabConPur4Approve 相关人员对采购单进行审批<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabConPur4Audit(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * @Title:相关人员对采购单进行审批
	 * <p>updateLabConPur4Approve 相关人员对采购单进行审批<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabConPur4Approve(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	public LabConPurMainVo getLabConPurMainById(String mainId)
			throws GlobalException;

	/**
	 * @Title:查找采购单中最大单据号
	 * <p>getLabConPurMainMaxReceiptNo 查找采购单中最大单据号<br>
	 * 需要传入null
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public String getLabConPurMainMaxReceiptNo() throws GlobalException;

	/**
	 * 
	 * @Title:删除采购单
	 * <p>deleteLabConPurMain 删除采购单<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabConPurMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * 
	 * @Title:修改已经申请采购单
	 * <p>updateLabConPurMain 修改已经申请采购单<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return LabConPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurMainVo updateLabConPurMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * @Title: 根据相关参数获取采购单详细信息
	 * <p>getLabConPurDetailList 根据相关参数获取采购单详细信息<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return List<LabConPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConPurDetailVo> getLabConPurDetailList(
			LabConPurMainVo labConPurMainVo) throws GlobalException;

	/**
	 * @Title: 将采购单详细信息添加到指定Main下
	 * <p>addLabConPurDetail 将采购单详细信息添加到指定Main下<br>
	 * 需要传入List<LabConPurDetailVo> labConPurDetailVoList, String id
	 * @param List<LabConPurDetailVo> labConPurDetailVoList, String id
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */

	public void addLabConPurDetail(
			List<LabConPurDetailVo> labConPurDetailVoList, String id)
			throws GlobalException;

	/**
	 * @Title: 根据MainId查找采购单详细信息
	 * <p>addLabConPurDetail 将采购单详细信息添加到指定Main下<br>
	 * 需要传入List<LabConPurDetailVo> labConPurDetailVoList, String id
	 * @param List<LabConPurDetailVo> labConPurDetailVoList, String id
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConPurDetailVo> getLabConPurDetailByMainId(String Id)
			throws GlobalException;

	/**
	 * @Title: 获取警戒的耗材采购详细信息集合
	 * <p>getLabConPurDetailList 获取警戒的耗材采购详细信息集合<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabConPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabConPurDetailVo> getLabConPurDetailList()
			throws GlobalException;
	/**
	 * @Title: 删除耗材采购
	 * <p>deleteLabConPurById 删除耗材采购<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabConPurById(String id) throws GlobalException;
	/**
	 * @Title: 新增耗材采购
	 * <p>addLabConPurDetail 新增耗材采购<br>
	 * 需要传入LabConPurDetailVo labConPurDetailVo, String id
	 * @param LabConPurDetailVo labConPurDetailVo, String id
	 * @return LabConPurDetailVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurDetailVo addLabConPurDetail(LabConPurDetailVo labConPurDetailVo, String id)
			throws GlobalException;

	/**
	 * @Title:获取入库单列表
	 * <p>getLabConPurInsPR 获取入库单列表<br>
	 * 需要传入LabConPurMainVo labConPurMainVo,PageResult pageResult
	 * @param LabConPurMainVo labConPurMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabConPurInsPR(LabConPurMainVo labConPurMainVo,PageResult pageResult) throws GlobalException;

	/**
	 * 
	 * @Title:将采购单/入库单进行入库
	 * <p>updateLabConPur4Ins 将采购单/入库单进行入库<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void updateLabConPur4Ins(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * @Title:添加入库单
	 * <p>addLabConPurIns 添加入库单<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return LabConPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurMainVo addLabConPurIns(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * @Title:修改入库单
	 * <p>updateLabConInMain 修改入库单<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return LabConPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabConPurMainVo updateLabConInMain(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

	/**
	 * @Title: 将还未入库的入库单删除
	 * <p>deleteLabConPurIns 将还未入库的入库单删除<br>
	 * 需要传入LabConPurMainVo labConPurMainVo
	 * @param LabConPurMainVo labConPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabConPurIns(LabConPurMainVo labConPurMainVo)
			throws GlobalException;

}
