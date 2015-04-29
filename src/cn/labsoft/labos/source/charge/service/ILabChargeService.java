package cn.labsoft.labos.source.charge.service;

import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.charge.vo.LabChargeVo;

public interface ILabChargeService {
	/**
	 * 添加收费单信息
	 * @param labChargeVo 收费单对象
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean addLabCharge(LabChargeVo labChargeVo) throws GlobalException;

	/**
	 * 软删除收费单信息
	 * @param ids 收费单对象Id
	 * @return 返回类型 boolean
	 * @throws GlobalException
	 * @author amy
	 */
	public boolean update2DelLabCharge(String[] ids) throws GlobalException;

    /**
     * 修改收费单信息
     * @param labChargeVo 收费单对象
     * @return 返回类型 boolean
     * @throws GlobalException
     * @author amy
     */
	public boolean updateLabCharge(LabChargeVo labChargeVo) throws GlobalException;

	/**
	 * 根据id查询收费信息
	 * @param id 收费单对象Id
	 * @return 返回类型 收费单对象(LabChargeVo)
	 * @throws GlobalException
	 * @author amy
	 */
	public LabChargeVo getLabCharge(String id) throws GlobalException;
	
	/** 
	 * 根据pageResult获得收费分页信息列表，labChargeVo为备用参数
	 * @param labChargeVo 收费单对象
	 * @param pageResult
	 * @return PageResult
	 * @throws GlobalException
	 * @author amy
	 */
	public PageResult getLabChargePR(LabChargeVo labChargeVo, PageResult pageResult) throws GlobalException;

	/**
	 * 获得收费信息列表
	 * @param labChargeVo 收费单对象
	 * @throws GlobalException
	 * @return 返回类型 List 返回收费信息
	 *  @author amy
	 */
	public List<LabChargeVo> getLabChargeList(LabChargeVo labChargeVo) throws GlobalException;
	
	/**
	 * 保存收费信息
	 * @param paymentUnit 付款单位
	 * @param payInfo 款项内容
	 * @param collectionUnit 收款单位
	 * @param collectionName 负责人
	 * @throws GlobalException
	 * @return 返回类型  boolean
	 * @author amy
	 */
	public boolean saveLabCharge(String paymentUnit,String payInfo,String collectionUnit,String collectionName,String busId,String busType)throws GlobalException;

	/**
	 * 根据busID和busType获取收费信息列表
	 * @param busId 业务Id
	 * @param busType 业务类型
	 * @return 返回类型 List 返回收费信息
	 * @throws GlobalException
	 *  @author amy
	 */
	public List<LabChargeVo> getLabChargeByBusIdAndBusType(String busId , String busType)throws GlobalException;
	
	/**
	 * 更新收费单信息
	 * @param paymentUnit 付款单位
	 * @param payInfo 款项内容
	 * @param collectionUnit 收款单位
	 * @param collectionName 负责人
	 * @param busId 业务Id
	 * @param busType 业务类型
	 * @return 返回类型  boolean
	 * @author amy
	 * @throws GlobalException
	 */
	public boolean updateLabCharge(String paymentUnit,String payInfo,String collectionUnit,String collectionName,String busId,String busType )throws GlobalException;
	
	/**
     * 收费单信息打印
     * @param labChargeVo 收费单对象
     * @return 返回类型  boolean
     * @throws GlobalException
     * @author amy
     */
	public boolean updateLabCharge4Rport(LabChargeVo labChargeVo) throws GlobalException;
}
