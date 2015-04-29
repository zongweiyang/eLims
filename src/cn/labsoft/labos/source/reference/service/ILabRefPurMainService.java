package cn.labsoft.labos.source.reference.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.source.reference.vo.LabRefPurDetailVo;
import cn.labsoft.labos.source.reference.vo.LabRefPurMainVo;

public interface ILabRefPurMainService  {
	/**
	 * @Title:获取采购单列表
	 * <p>getLabRefPurMainPR  获取采购单列表<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo,PageResult pageResult
	 * @param LabRefPurMainVo labRefPurMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabRefPurMainPR(LabRefPurMainVo labRefPurMainVo,PageResult pageResult) throws GlobalException;
	/**
	 * @Title:新增采购单
	 * <p>addLabRefPurMain  新增采购单<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return LabRefPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurMainVo addLabRefPurMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * @Title:相关人员对采购单进行审核
	 * <p>updateLabRefPur4Audit  相关人员对采购单进行审核<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabRefPur4Audit(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * @Title:相关人员对采购单进行审批
	 * <p>updateLabRefPur4Approve  相关人员对采购单进行审批<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean updateLabRefPur4Approve(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;
	/**
	 * @Title:获取采购单通过ID
	 * <p>getLabRefPurMainById  获取采购单通过ID<br>
	 * 需要传入String mainId
	 * @param String mainId
	 * @return LabRefPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurMainVo getLabRefPurMainById(String mainId)
			throws GlobalException;

	/**
	 * @Title:查找采购单中最大单据号
	 * <p>getLabRefPurMainMaxReceiptNo  查找采购单中最大单据号<br>
	 * 需要传入null
	 * @param null
	 * @return String
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public String getLabRefPurMainMaxReceiptNo() throws GlobalException;

	/**
	 * 
	 * @Title:删除采购单
	 * <p>deleteLabRefPurMain  删除采购单<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabRefPurMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * 
	 * @Title:修改已经申请采购单
	 * <p>updateLabRefPurMain  修改已经申请采购单<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return LabRefPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurMainVo updateLabRefPurMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * @Title: 根据相关参数获取采购单详细信息
	 * <p>getLabRefPurDetailList  根据相关参数获取采购单详细信息<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return List<LabRefPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefPurDetailVo> getLabRefPurDetailList(
			LabRefPurMainVo labRefPurMainVo) throws GlobalException;

	/**
	 * @Title: 将采购单详细信息添加到指定Main下
	 * <p>addLabRefPurDetail  将采购单详细信息添加到指定Main下<br>
	 * 需要传入ist<LabRefPurDetailVo> labRefPurDetailVoList, String id
	 * @param ist<LabRefPurDetailVo> labRefPurDetailVoList, String id
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */

	public void addLabRefPurDetail(
			List<LabRefPurDetailVo> labRefPurDetailVoList, String id)
			throws GlobalException;

	/**
	 * @Title: 根据MainId查找采购单详细信息
	 * <p>getLabRefPurDetailByMainId  根据MainId查找采购单详细信息<br>
	 * 需要传入String Id
	 * @param String Id
	 * @return List<LabRefPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefPurDetailVo> getLabRefPurDetailByMainId(String Id)
			throws GlobalException;

	/**
	 * @Title: 获取警戒的标准品采购详细信息集合
	 * <p>getLabRefPurDetailList  获取警戒的标准品采购详细信息集合<br>
	 * 需要传入null
	 * @param null
	 * @return List<LabRefPurDetailVo>
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public List<LabRefPurDetailVo> getLabRefPurDetailList()
			throws GlobalException;
	/**
	 * @Title: 删除标准品采购详细信息
	 * <p>deleteLabRefPurById  删除标准品采购详细信息<br>
	 * 需要传入String id
	 * @param String id
	 * @return boolean true 成功 false 失败
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public boolean deleteLabRefPurById(String id) throws GlobalException;
	/**
	 * @Title: 新增标准品采购详细信息
	 * <p>addLabRefPurDetail  新增标准品采购详细信息<br>
	 * 需要传入LabRefPurDetailVo labRefPurDetailVo, String id
	 * @param LabRefPurDetailVo labRefPurDetailVo, String id
	 * @return LabRefPurDetailVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurDetailVo addLabRefPurDetail(
			LabRefPurDetailVo labRefPurDetailVo, String id)
			throws GlobalException;

	/**
	 * @Title:获取入库单列表
	 * <p>getLabRefPurInsPR  获取入库单列表<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo,PageResult pageResult
	 * @param LabRefPurMainVo labRefPurMainVo,PageResult pageResult
	 * @return PageResult
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public PageResult getLabRefPurInsPR(LabRefPurMainVo labRefPurMainVo,PageResult pageResult) throws GlobalException;

	/**
	 * 
	 * @Title:将采购单/入库单进行入库
	 * <p>updateLabRefPur4Ins  将采购单/入库单进行入库<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void updateLabRefPur4Ins(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * @Title:添加入库单
	 * <p>addLabRefPurIns  添加入库单<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return LabRefPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurMainVo addLabRefPurIns(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * @Title:修改入库单
	 * <p>updateLabRefInMain  修改入库单<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return LabRefPurMainVo
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public LabRefPurMainVo updateLabRefInMain(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

	/**
	 * @Title: 将还未入库的入库单删除
	 * <p>deleteLabRefPurIns  将还未入库的入库单删除<br>
	 * 需要传入LabRefPurMainVo labRefPurMainVo
	 * @param LabRefPurMainVo labRefPurMainVo
	 * @return null
	 * @author oldk
	 * @exception GlobalException 自定义异常
	 */
	public void deleteLabRefPurIns(LabRefPurMainVo labRefPurMainVo)
			throws GlobalException;

}
